package es.cic.tessa.common.utils;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CompressUtils
{

    private static final Logger LOGGER = LoggerFactory.getLogger(CompressUtils.class);

    private CompressUtils()
    {

    }


    public static byte[] compressString(String data)
    {

	if(data == null || data.isEmpty())
	{
	    return new byte[0];
	}

	try (ByteArrayOutputStream byteStream = new ByteArrayOutputStream(); GZIPOutputStream gzipStream = new GZIPOutputStream(byteStream))
	{

	    gzipStream.write(data.getBytes("UTF-8"));
	    gzipStream.close();
	    return byteStream.toByteArray();

	}
	catch (IOException e)
	{
	    LOGGER.error("Error compressing data", e);
	    return new byte[0];
	}
    }


    public static String decompressString(byte[] compressedData) throws IOException
    {

	if(compressedData == null || compressedData.length == 0)
	    return "";
	try (ByteArrayInputStream byteStream = new ByteArrayInputStream(compressedData); GZIPInputStream gzipStream = new GZIPInputStream(byteStream))
	{
	    return new String(gzipStream.readAllBytes(), StandardCharsets.UTF_8);
	}
    }
}
