package es.cic.tessa.common.cms;


public interface FileStorageService
{

    String uploadFile(String fileName, String contentType, byte[] content);


    void updateFile(String id, String fileName, String contentType, byte[] content);


    byte[] getFile(String id);


    void deleteFile(String id);


    long getFileSize(String id);
}
