package es.cic.tessa.common.cms;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
import software.amazon.awssdk.services.s3.model.PutObjectResponse;


@ExtendWith(MockitoExtension.class)
class SeaweedFSStorageServiceTest
{

    private static final String BUCKET = "tessa-bucket";

    @Mock
    private S3Client s3Client;

    @Mock
    private ResponseInputStream<GetObjectResponse> responseInputStream;

    private SeaweedFSStorageService service;

    @BeforeEach
    void setUp()
    {

	service = new SeaweedFSStorageService(BUCKET, s3Client);
    }

    // ── uploadFile ───────────────────────────────────────────────────────────


    @Test
    void uploadFile_success_devuelveFileId() throws Exception
    {

	when(s3Client.putObject(any(PutObjectRequest.class), any(RequestBody.class))).thenReturn(PutObjectResponse.builder().build());

	String fileId = service.uploadFile("archivo.txt", "text/plain", "contenido".getBytes());

	assertNotNull(fileId);
	verify(s3Client).putObject(any(PutObjectRequest.class), any(RequestBody.class));
    }


    @Test
    void uploadFile_contenidoNulo_lanzaIllegalArgumentException()
    {

	assertThrows(IllegalArgumentException.class, () -> service.uploadFile("archivo.txt", "text/plain", null));
    }

    // ── updateFile ───────────────────────────────────────────────────────────


    @Test
    void updateFile_success() throws Exception
    {

	when(s3Client.putObject(any(PutObjectRequest.class), any(RequestBody.class))).thenReturn(PutObjectResponse.builder().build());

	service.updateFile("mi-file-id", "archivo.txt", "text/plain", "hola mundo".getBytes());

	verify(s3Client).putObject(any(PutObjectRequest.class), any(RequestBody.class));
    }


    @Test
    void updateFile_idNulo_lanzaIllegalArgumentException()
    {

	assertThrows(IllegalArgumentException.class, () -> service.updateFile(null, "archivo.txt", "text/plain", "hola".getBytes()));
    }


    @Test
    void updateFile_idVacio_lanzaIllegalArgumentException()
    {

	assertThrows(IllegalArgumentException.class, () -> service.updateFile("  ", "archivo.txt", "text/plain", "hola".getBytes()));
    }


    @Test
    void updateFile_contenidoNulo_lanzaIllegalArgumentException()
    {

	assertThrows(IllegalArgumentException.class, () -> service.updateFile("mi-file-id", "archivo.txt", "text/plain", null));
    }

    // ── getFile ──────────────────────────────────────────────────────────────


    @Test
    void getFile_success_devuelveBytes() throws Exception
    {

	byte[] bytesEsperados = "contenido del archivo".getBytes();
	when(s3Client.getObject(any(GetObjectRequest.class))).thenReturn(new ResponseInputStream<>(GetObjectResponse.builder().build(), new ByteArrayInputStream(bytesEsperados)));

	byte[] resultado = service.getFile("mi-file-id");

	assertArrayEquals(bytesEsperados, resultado);
    }


    @Test
    void getFile_idNulo_lanzaIllegalArgumentException()
    {

	assertThrows(TessaException.class, () -> service.getFile(null));
    }


    @Test
    void getFile_idVacio_lanzaIllegalArgumentException()
    {

	assertThrows(TessaException.class, () -> service.getFile(""));
    }


    @Test
    void getFile_s3Exception_lanzaTessaException()
    {

	when(s3Client.getObject(any(GetObjectRequest.class))).thenThrow(new RuntimeException("S3 error"));

	assertThrows(TessaException.class, () -> service.getFile("mi-file-id"));
    }

    // ── deleteFile ───────────────────────────────────────────────────────────


    @Test
    void deleteFile_success()
    {

	service.deleteFile("mi-file-id");

	verify(s3Client).deleteObject(any(DeleteObjectRequest.class));
    }


    @Test
    void deleteFile_idNulo_lanzaIllegalArgumentException()
    {

	assertThrows(IllegalArgumentException.class, () -> service.deleteFile(null));
    }


    @Test
    void deleteFile_idVacio_lanzaIllegalArgumentException()
    {

	assertThrows(IllegalArgumentException.class, () -> service.deleteFile(""));
    }


    @Test
    void deleteFile_s3Exception_lanzaTessaException()
    {

	when(s3Client.deleteObject(any(DeleteObjectRequest.class))).thenThrow(new RuntimeException("S3 error"));

	assertThrows(TessaException.class, () -> service.deleteFile("mi-file-id"));
    }

    // ── uploadFileStream ─────────────────────────────────────────────────────


    @Test
    void uploadFileStream_success()
    {

	when(s3Client.putObject(any(PutObjectRequest.class), any(RequestBody.class))).thenReturn(PutObjectResponse.builder().build());

	byte[] datos = "datos del stream".getBytes();
	service.uploadFileStream("mi-file-id", "archivo.txt", "text/plain", new ByteArrayInputStream(datos), datos.length);

	verify(s3Client).putObject(any(PutObjectRequest.class), any(RequestBody.class));
    }


    @Test
    void uploadFileStream_idNulo_lanzaIllegalArgumentException()
    {

	assertThrows(IllegalArgumentException.class, () -> service.uploadFileStream(null, "archivo.txt", "text/plain", new ByteArrayInputStream(new byte[0]), 0));
    }


    @Test
    void uploadFileStream_idVacio_lanzaIllegalArgumentException()
    {

	assertThrows(IllegalArgumentException.class, () -> service.uploadFileStream("", "archivo.txt", "text/plain", new ByteArrayInputStream(new byte[0]), 0));
    }


    @Test
    void uploadFileStream_s3Exception_lanzaTessaException()
    {

	when(s3Client.putObject(any(PutObjectRequest.class), any(RequestBody.class))).thenThrow(new RuntimeException("S3 error"));

	assertThrows(TessaException.class, () -> service.uploadFileStream("mi-file-id", "archivo.txt", "text/plain", new ByteArrayInputStream("datos".getBytes()), 5));
    }

    // ── getFileStream ────────────────────────────────────────────────────────


    @Test
    void getFileStream_success()
    {

	byte[] bytesEsperados = "datos".getBytes();
	when(s3Client.getObject(any(GetObjectRequest.class))).thenReturn(new ResponseInputStream<>(GetObjectResponse.builder().build(), new ByteArrayInputStream(bytesEsperados)));

	ResponseInputStream<GetObjectResponse> stream = service.getFileStream("mi-file-id");

	assertNotNull(stream);
    }


    @Test
    void getFileStream_idNulo_lanzaIllegalArgumentException()
    {

	assertThrows(IllegalArgumentException.class, () -> service.getFileStream(null));
    }

    // ── getFileSize ──────────────────────────────────────────────────────────


    @Test
    void getFileSize_success_devuelveTamano()
    {

	when(s3Client.headObject(any(HeadObjectRequest.class))).thenReturn(HeadObjectResponse.builder().contentLength(1234L).build());

	long tamano = service.getFileSize("mi-file-id");

	assertEquals(1234L, tamano);
    }


    @Test
    void getFileSize_idNulo_lanzaIllegalArgumentException()
    {

	assertThrows(IllegalArgumentException.class, () -> service.getFileSize(null));
    }


    @Test
    void getFileSize_s3Exception_lanzaTessaException()
    {

	when(s3Client.headObject(any(HeadObjectRequest.class))).thenThrow(new RuntimeException("S3 error"));

	assertThrows(TessaException.class, () -> service.getFileSize("mi-file-id"));
    }
}
