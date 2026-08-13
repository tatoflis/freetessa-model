package es.cic.tessa.common.model;


import java.io.Serializable;


public class CMSFile implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Long id;

    private String fileName;

    private byte[] fileContent;

    public Long getId()
    {

	return id;
    }


    public void setId(Long id)
    {

	this.id = id;
    }


    public String getFileName()
    {

	return fileName;
    }


    public void setFileName(String fileName)
    {

	this.fileName = fileName;
    }


    public byte[] getFileContent()
    {

	return fileContent;
    }


    public void setFileContent(byte[] fileContent)
    {

	this.fileContent = fileContent;
    }

}
