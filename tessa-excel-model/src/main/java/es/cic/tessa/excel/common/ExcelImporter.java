package es.cic.tessa.excel.common;


import java.io.IOException;
import java.io.InputStream;
import org.springframework.scheduling.annotation.Async;
import es.cic.tessa.excel.suscription.ImportExportDTO;


public interface ExcelImporter
{

    @Async
    public void executeAsync(String fileName, InputStream inputStream, ImportExportConfig importExportConfig) throws IOException;


    public ImportExportDTO execute(String fileName, InputStream inputStream, ImportExportConfig importExportConfig) throws IOException;

}
