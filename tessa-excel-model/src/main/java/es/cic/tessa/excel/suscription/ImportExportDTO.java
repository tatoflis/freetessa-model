package es.cic.tessa.excel.suscription;


import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import tools.jackson.databind.annotation.JsonSerialize;


@JsonInclude(Include.NON_NULL)
@JsonSerialize
public class ImportExportDTO implements Serializable
{

    private static final long serialVersionUID = 1L;

    private String fileName;
    private Instant instantOperation;
    private Duration durationOperation;
    private String path;
    private String operation;
    private Instant operationDeadline;
    private boolean success = true;
    private List<String> errors;
    private String currentSheetName;
    private String message;

    public ImportExportDTO()
    {

    }


    public ImportExportDTO(String error, String message)
    {

	this.success = false;
	this.errors = new ArrayList<>();
	this.errors.add(error);
	this.message = message;
    }


    public String getFileName()
    {

	return fileName;
    }


    public void setFileName(String fileName)
    {

	this.fileName = fileName;
    }


    public Instant getInstantOperation()
    {

	return instantOperation;
    }


    public void setInstantOperation(Instant instantOperation)
    {

	this.instantOperation = instantOperation;
    }


    public Duration getDurationOperation()
    {

	return durationOperation;
    }


    public void setDurationOperation(Duration durationOperation)
    {

	this.durationOperation = durationOperation;
    }


    public String getPath()
    {

	return path;
    }


    public void setPath(String path)
    {

	this.path = path;
    }


    public boolean isSuccess()
    {

	return success;
    }


    public void setSuccess(boolean success)
    {

	this.success = success;
    }


    public List<String> getErrors()
    {

	return errors;
    }


    public void setErrors(List<String> errors)
    {

	this.errors = errors;
    }


    public synchronized void addError(String errorMessage)
    {

	addError(errorMessage, null);
    }


    public synchronized void addError(String errorMessage, String prefix)
    {

	ensureErrorsInitialized();
	errors.add(buildErrorMessage(errorMessage, prefix));
	success = false;
    }


    public synchronized boolean hasErrors()
    {

	return errors != null && !errors.isEmpty();
    }


    public String getMessage()
    {

	return message;
    }


    public void setMessage(String successMessage, String errorMessage)
    {

	this.message = this.success ? successMessage : errorMessage;
    }


    public String getOperation()
    {

	return operation;
    }


    public void setOperation(String operation)
    {

	this.operation = operation;
    }


    public Instant getOperationDeadline()
    {

	return operationDeadline;
    }


    public void setOperationDeadline(Instant operationDeadline)
    {

	this.operationDeadline = operationDeadline;
    }


    public String getCurrentSheetName()
    {

	return currentSheetName;
    }


    public void setCurrentSheetName(String currentSheetName)
    {

	this.currentSheetName = currentSheetName;
    }


    private void ensureErrorsInitialized()
    {

	if(errors == null)
	{
	    errors = new ArrayList<>();
	}
    }


    private String buildErrorMessage(String errorMessage, String prefix)
    {

	if(errorMessage == null || errorMessage.startsWith("["))
	{
	    return errorMessage;
	}

	String context = buildContext(prefix, currentSheetName);

	return context.isEmpty() ? errorMessage : String.format("[%s] %s", context, errorMessage);
    }


    private String buildContext(String prefix, String sheetName)
    {

	boolean hasPrefix = hasText(prefix);
	boolean hasSheet = hasText(sheetName);

	if(hasPrefix && hasSheet)
	{
	    return String.format("%s - %s", prefix, sheetName);
	}
	else if(hasPrefix)
	{
	    return prefix;
	}
	else if(hasSheet)
	{
	    return sheetName;
	}
	else
	{
	    return "";
	}
    }


    private boolean hasText(String value)
    {

	return value != null && !value.isEmpty();
    }
}
