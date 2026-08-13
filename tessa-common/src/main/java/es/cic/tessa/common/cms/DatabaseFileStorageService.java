package es.cic.tessa.common.cms;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import es.cic.tessa.common.exceptions.CMSException;
import es.cic.tessa.common.exceptions.TessaException;
import es.cic.tessa.common.model.CMSFile;
import jakarta.transaction.Transactional;


@Service("databaseFileStorageService")
@Transactional
@ConditionalOnProperty(name = "tessa.binary-files.file-storage.implementation", havingValue = "Database", matchIfMissing = false)
public class DatabaseFileStorageService implements FileStorageService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseFileStorageService.class);

    private DataSource dataSource;

    public DatabaseFileStorageService(DataSource dataSource)
    {

	this.dataSource = dataSource;
    }


    @Override
    public String uploadFile(String fileName, String contentType, byte[] content)
    {

	try
	{
	    Long save = save(fileName, content);

	    return String.valueOf(save);

	}
	catch (Exception e)
	{
	    LOGGER.error("Error saving file with name {}", fileName, e);
	    throw new CMSException("Error saving file with name: " + fileName, e);
	}

    }


    @Override
    public void updateFile(String id, String fileName, String contentType, byte[] content)
    {

	if(id == null)
	{
	    LOGGER.error("ID binary cannot be null");
	    throw new CMSException("ID binary cannot be null");
	}

	boolean updated = update(Long.parseLong(id), fileName, content);

	if(!updated)
	{
	    LOGGER.error("Error searching for file with ID {}", id);
	    throw new CMSException("Error searching for file with ID " + id);
	}

    }


    @Override
    public void deleteFile(String id)
    {

	if(id == null)
	{
	    LOGGER.error("ID binary cannot be null");
	    throw new CMSException("ID binary cannot be null");

	}

	deleteById(Long.parseLong(id));

    }


    @Override
    public byte[] getFile(String id)
    {

	LOGGER.debug("Get file with ID: {}", id);

	if(id == null)
	{
	    LOGGER.error("ID binary cannot be null");
	    throw new CMSException("ID binary cannot be null");

	}

	Optional<CMSFile> CMSFile = findById(Long.parseLong(id));

	if(CMSFile.isEmpty())
	{
	    LOGGER.error("Cannot find file with ID {} ", id);
	    throw new TessaException("Cannot find file with ID: " + id);
	}
	else
	{
	    LOGGER.debug("File with ID {} retrieved successfully", id);
	    return CMSFile.get().getFileContent();
	}

    }


    private Long save(String fileName, byte[] fileContent)
    {

	String sql = "INSERT INTO tessa_files (name, content) VALUES (?, ?)";

	try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
	{

	    statement.setString(1, fileName);
	    statement.setBytes(2, fileContent);

	    statement.executeUpdate();

	    try (ResultSet resultSet = statement.getGeneratedKeys())
	    {
		if(resultSet.next())
		{
		    return resultSet.getLong(1);
		}
		else
		{
		    throw new CMSException("Error: No ID was generated for the file.");
		}
	    }
	}
	catch (SQLException e)
	{
	    throw new CMSException("Error saving file to database", e);
	}
    }


    private boolean update(Long id, String fileName, byte[] fileContent)
    {

	String sql = "UPDATE tessa_files SET name = ?, content = ? WHERE id = ?";

	try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(sql))
	{

	    statement.setString(1, fileName);
	    statement.setBytes(2, fileContent);
	    statement.setLong(3, id);

	    return statement.executeUpdate() > 0;
	}
	catch (SQLException e)
	{
	    throw new CMSException("Error updating file with ID " + id, e);
	}
    }


    private Optional<CMSFile> findById(Long id)
    {

	String sql = "SELECT id, name, content FROM tessa_files WHERE id = ?";

	try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
	{

	    stmt.setLong(1, id);
	    try (ResultSet rs = stmt.executeQuery())
	    {
		if(rs.next())
		{
		    return Optional.of(mapRowToCMSFile(rs));
		}
	    }
	}
	catch (SQLException e)
	{

	    throw new CMSException("Error searching for file with ID: " + id, e);
	}
	return Optional.empty();
    }


    private CMSFile mapRowToCMSFile(ResultSet rs) throws SQLException
    {

	CMSFile file = new CMSFile();
	file.setId(rs.getLong("id"));
	file.setFileName(rs.getString("name"));
	file.setFileContent(rs.getBytes("content"));
	return file;
    }


    private void deleteById(Long id)
    {

	String sql = "DELETE FROM tessa_files WHERE id = ?";

	try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
	{

	    stmt.setLong(1, id);
	    stmt.executeUpdate();
	}
	catch (SQLException e)
	{
	    LOGGER.error("Error deleting file with ID {} ", id, e);
	    throw new CMSException("Error deleting file with ID " + id, e);
	}
    }


    @Override
    public long getFileSize(String id)
    {

	if(id == null)
	{
	    LOGGER.error("ID binary cannot be null");
	    throw new CMSException("ID binary cannot be null");
	}

	String sql = "SELECT OCTET_LENGTH(content) FROM tessa_files WHERE id = ?";

	try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
	{
	    stmt.setLong(1, Long.parseLong(id));
	    try (ResultSet rs = stmt.executeQuery())
	    {
		if(rs.next())
		{
		    return rs.getLong(1);
		}
		else
		{
		    throw new TessaException("Cannot find file with ID: " + id);
		}
	    }
	}
	catch (SQLException e)
	{
	    throw new CMSException("Error getting file size for ID: " + id, e);
	}
    }

}
