package es.cic.tessa.lookup.expression.model;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.Collection;
import java.util.LinkedList;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.annotation.JsonSerialize;
import es.cic.tessa.model.support.TemporalElement;


@JsonInclude(Include.NON_NULL)
@JsonSerialize
public class ExpressionFunctionBlock implements TemporalElement
{

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpressionFunctionBlock.class);

    private static final long serialVersionUID = 1L;

    @JsonProperty("ID")
    private Long id;

    @JsonProperty("BM")
    private Instant moment = Instant.now();

    @JsonIgnore
    private Collection<ExpressionFunction> expressionFunctions = new LinkedList<>();

    @JsonProperty("B")
    private String expressionFunctionsCompress;

    public ExpressionFunctionBlock()
    {

    }


    public ExpressionFunctionBlock(Long id, Instant moment)
    {

	this.id = id;
	this.moment = moment;
    }


    @Override
    public Long getId()
    {

	return id;
    }


    @Override
    public void setId(Long id)
    {

	this.id = id;

    }


    public Collection<ExpressionFunction> getExpressionFunctions()
    {

	return expressionFunctions;
    }


    public void setExpressionFunctions(Collection<ExpressionFunction> expressionFunctions)
    {

	this.expressionFunctions = expressionFunctions;
    }


    public Instant getTimestamp()
    {

	return moment;
    }


    public void setTimestamp(Instant moment)
    {

	this.moment = moment;
    }


    public String getExpressionFunctionsCompress()
    {

	return expressionFunctionsCompress;
    }


    public void setExpressionFunctionsCompress(String expressionFunctionsCompress)
    {

	this.expressionFunctionsCompress = expressionFunctionsCompress;
    }


    private static String convertExpressionFunctionJSON(ObjectMapper objectMapper, Collection<ExpressionFunction> expressionFunctions)
    {

	String writeValueAsString = null;

	try
	{
	    writeValueAsString = objectMapper.writeValueAsString(expressionFunctions);
	}
	catch (JacksonException e)
	{

	    LOGGER.error("Error convert json expression functions", e);
	}

	return writeValueAsString;
    }


    private static Collection<ExpressionFunction> convertExpressionFunctionsFromJSON(ObjectMapper objectMapper, String expressionFunctionsJson)
    {

	Collection<ExpressionFunction> expressionFunctions = null;

	try
	{
	    expressionFunctions = objectMapper.readValue(expressionFunctionsJson, new TypeReference<Collection<ExpressionFunction>>()
	    {
	    });
	}

	catch (Exception e)
	{

	    LOGGER.error("Error convert object expression functions", e);
	}

	return expressionFunctions;
    }


    private static String compressExpressionFunctionsJSONBase64(String expressionFunctionsJSON) throws IOException
    {

	ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream))
	{
	    byte[] inputBytes = expressionFunctionsJSON.getBytes(StandardCharsets.UTF_8);
	    gzipOutputStream.write(inputBytes);
	}

	byte[] compressedBytes = byteArrayOutputStream.toByteArray();
	return Base64.getEncoder().encodeToString(compressedBytes);

    }


    private static String uncompressExpressionFunctionsJSONBase64(String expressionFunctionsJSON) throws IOException
    {

	byte[] uncompressExpressionFunctions = Base64.getDecoder().decode(expressionFunctionsJSON);

	try (GZIPInputStream gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(uncompressExpressionFunctions)); ByteArrayOutputStream outputStream = new ByteArrayOutputStream())
	{

	    byte[] buffer = new byte[1024];
	    int bytesRead;
	    while ((bytesRead = gzipInputStream.read(buffer)) != -1)
	    {
		outputStream.write(buffer, 0, bytesRead);
	    }

	    return outputStream.toString("UTF-8");
	}

    }


    public static String convertExpressionFunctionBlockToJSON(ObjectMapper objectMapper, ExpressionFunctionBlock expressionFunctionBlock)
    {

	String writeValueAsString = null;

	try
	{

	    String convertExpressionFunctionJSON = convertExpressionFunctionJSON(objectMapper, expressionFunctionBlock.getExpressionFunctions());

	    String compressExpressionFunctionsJSONBase64 = compressExpressionFunctionsJSONBase64(convertExpressionFunctionJSON);

	    expressionFunctionBlock.setExpressionFunctionsCompress(compressExpressionFunctionsJSONBase64);

	    writeValueAsString = objectMapper.writeValueAsString(expressionFunctionBlock);

	}
	catch (Exception e)
	{

	    LOGGER.error("Error convert json signaldata", e);
	}

	return writeValueAsString;
    }


    public static ExpressionFunctionBlock convertExpressionFunctionBlockFromJSON(ObjectMapper objectMapper, String expressionFunctionBlockJson)
    {

	ExpressionFunctionBlock expressionFunctionBlock = null;

	try
	{

	    expressionFunctionBlock = objectMapper.readValue(expressionFunctionBlockJson, ExpressionFunctionBlock.class);

	    String convertExpressionFunctionJSON = uncompressExpressionFunctionsJSONBase64(expressionFunctionBlock.getExpressionFunctionsCompress());

	    expressionFunctionBlock.getExpressionFunctions().addAll(convertExpressionFunctionsFromJSON(objectMapper, convertExpressionFunctionJSON));

	}
	catch (Exception e)
	{

	    LOGGER.error("Error convert json expression function block", e);
	}

	return expressionFunctionBlock;
    }


    @Override
    public String toString()
    {

	return "ExpressionFunctionBlock [moment=" + moment + ", expressionFunctions=" + expressionFunctions.size() + "]";
    }

}
