package es.cic.tessa.excel.common;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import org.springframework.scheduling.annotation.Async;
import es.cic.tessa.common.filter.Filter;
import es.cic.tessa.common.model.types.OperationType;
import es.cic.tessa.excel.suscription.ImportExportDTO;


public interface CsvService
{

    public ImportExportDTO importCSV(InputStream file, ImportExportConfig importExportConfig, OperationType operation);


    public ByteArrayOutputStream exportCSV(Filter filter, ImportExportConfig importExportConfig);


    @Async
    public void importAsyncCSV(InputStream file, ImportExportConfig importExportConfig, OperationType operation);


    @Async
    public void exportAsyncCSV(Filter filter, ImportExportConfig importExportConfig);
}
