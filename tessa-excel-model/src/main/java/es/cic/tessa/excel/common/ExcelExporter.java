package es.cic.tessa.excel.common;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.springframework.scheduling.annotation.Async;
import es.cic.tessa.common.filter.Filter;


public interface ExcelExporter
{

    @Async
    public void executeAsync(Filter filter, ImportExportConfig importExportConfig) throws IOException;


    public ByteArrayOutputStream execute(Filter filter, ImportExportConfig importExportConfig) throws IOException;

}
