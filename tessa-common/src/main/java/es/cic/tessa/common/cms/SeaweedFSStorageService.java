package es.cic.tessa.common.cms;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import es.cic.tessa.common.exceptions.TessaException;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;


public class SeaweedFSStorageService implements FileStorageService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(SeaweedFSStorageService.class);

    private final String bucketName;
    private final S3Client s3Client;

    private static final String METADATA_ORIGINAL_FILE_NAME = "original-filename";
    private static final String METADATA_CONTENT_TYPE = "Content-Type";
    private static final String METADATA_FILE_ID = "file-id";

    public SeaweedFSStorageService(String bucketName, S3Client s3Client)
    {

	this.bucketName = bucketName;
	this.s3Client = s3Client;
    }


    public void uploadFileStream(String fileId, String fileName, String contentType, InputStream content, long contentLength)
    {

	requireId(fileId);

	Map<String, String> metadata = buildMetadata(fileId, fileName, contentType);

	try
	{
	    PutObjectRequest req = PutObjectRequest.builder().bucket(bucketName).key(fileId).contentType(contentType).metadata(metadata).build();

	    s3Client.putObject(req, RequestBody.fromInputStream(content, contentLength));

	}
	catch (Exception e)
	{
	    LOGGER.error("Error uploading file {} to SeaweedFS", fileName, e);
	    throw new TessaException("Error uploading file " + fileName + " to SeaweedFS", e);
	}

	LOGGER.info("File uploaded to SeaweedFS successfully (streaming). fileId={}", fileId);
    }


    public ResponseInputStream<GetObjectResponse> getFileStream(String id)
    {

	requireId(id);

	try
	{
	    return s3Client.getObject(GetObjectRequest.builder().bucket(bucketName).key(id).build());
	}
	catch (Exception e)
	{
	    LOGGER.error("Error retrieving file id {} from SeaweedFS (stream)", id, e);
	    throw new TessaException("Error retrieving file id " + id + " from SeaweedFS", e);
	}
    }


    @Override
    public long getFileSize(String id)
    {

	requireId(id);

	try
	{
	    HeadObjectResponse head = s3Client.headObject(HeadObjectRequest.builder().bucket(bucketName).key(id).build());
	    return head.contentLength();
	}
	catch (Exception e)
	{
	    LOGGER.error("Error headObject for file id {} from SeaweedFS", id, e);
	    throw new TessaException("Error reading metadata for file id " + id + " from SeaweedFS", e);
	}
    }


    @Override
    public String uploadFile(String fileName, String contentType, byte[] content)
    {

	if(content == null)
	{
	    throw new IllegalArgumentException("content cannot be null");
	}
	try (InputStream is = new ByteArrayInputStream(content))
	{
	    String fileId = UUID.randomUUID().toString();

	    uploadFileStream(fileId, fileName, contentType, is, content.length);

	    return fileId;
	}
	catch (Exception e)
	{
	    LOGGER.error("Error uploading file {} to SeaweedFS (byte[] wrapper)", fileName, e);
	    throw new TessaException("Error uploading file " + fileName + " to SeaweedFS", e);
	}
    }


    @Override
    public void updateFile(String fileId, String fileName, String contentType, byte[] content)
    {

	requireId(fileId);

	if(content == null)
	{
	    throw new IllegalArgumentException("content cannot be null");
	}

	try (InputStream is = new ByteArrayInputStream(content))
	{
	    // PutObject is an upsert in S3-compatible APIs
	    uploadFileStream(fileId, fileName, contentType, is, content.length);
	}
	catch (Exception e)
	{
	    LOGGER.error("Error updating file {} to SeaweedFS", fileName, e);
	    throw new TessaException("Error updating file " + fileName + " to SeaweedFS", e);
	}
    }


    @Override
    public byte[] getFile(String id)
    {

	try (ResponseInputStream<GetObjectResponse> is = getFileStream(id))
	{
	    return is.readAllBytes();
	}
	catch (Exception e)
	{
	    LOGGER.error("Error retrieving file id {} from SeaweedFS (byte[])", id, e);
	    throw new TessaException("Error retrieving file id " + id + " from SeaweedFS", e);
	}
    }


    @Override
    public void deleteFile(String id)
    {

	requireId(id);

	try
	{
	    s3Client.deleteObject(DeleteObjectRequest.builder().bucket(bucketName).key(id).build());
	}
	catch (Exception e)
	{
	    LOGGER.error("Error deleting file id {} from SeaweedFS", id, e);
	    throw new TessaException("Error deleting file id " + id + " from SeaweedFS", e);
	}
    }


    private Map<String, String> buildMetadata(String fileId, String fileName, String contentType)
    {

	Map<String, String> metadata = new HashMap<>();
	if(fileName != null)
	{
	    metadata.put(METADATA_ORIGINAL_FILE_NAME, fileName);
	}
	metadata.put(METADATA_FILE_ID, fileId);

	if(contentType != null)
	{
	    metadata.put(METADATA_CONTENT_TYPE, contentType);
	}
	return metadata;
    }


    private void requireId(String id)
    {

	if(id == null || id.isBlank())
	{
	    LOGGER.error("ID cannot be null/blank");
	    throw new IllegalArgumentException("ID cannot be null/blank");
	}
    }
}
