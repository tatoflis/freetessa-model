package es.cic.tessa.common.cms;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import es.cic.tessa.common.exceptions.CMSException;
import es.cic.tessa.common.exceptions.TessaException;

@ExtendWith(MockitoExtension.class)
class DatabaseFileStorageServiceTest
{

    @Mock
    private DataSource dataSource;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    // ── uploadFile ──────────────────────────────────────────────────────────

    @Test
    void uploadFile_success_devuelveIdComoString() throws Exception
    {

	when(dataSource.getConnection()).thenReturn(connection);
	when(connection.prepareStatement(anyString(), eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(preparedStatement);
	when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
	when(resultSet.next()).thenReturn(true);
	when(resultSet.getLong(1)).thenReturn(42L);

	DatabaseFileStorageService service = new DatabaseFileStorageService(dataSource);
	String id = service.uploadFile("archivo.txt", "text/plain", "contenido".getBytes());

	assertEquals("42", id);
	verify(preparedStatement).setString(1, "archivo.txt");
	verify(preparedStatement).setBytes(2, "contenido".getBytes());
	verify(preparedStatement).executeUpdate();
    }

    @Test
    void uploadFile_sinClaveGenerada_lanzaCMSException() throws Exception
    {

	when(dataSource.getConnection()).thenReturn(connection);
	when(connection.prepareStatement(anyString(), eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(preparedStatement);
	when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
	when(resultSet.next()).thenReturn(false);

	DatabaseFileStorageService service = new DatabaseFileStorageService(dataSource);

	assertThrows(CMSException.class, () -> service.uploadFile("archivo.txt", "text/plain", "contenido".getBytes()));
    }

    @Test
    void uploadFile_sqlException_lanzaCMSException() throws Exception
    {

	when(dataSource.getConnection()).thenReturn(connection);
	when(connection.prepareStatement(anyString(), eq(Statement.RETURN_GENERATED_KEYS)))
		.thenThrow(new SQLException("Error de base de datos"));

	DatabaseFileStorageService service = new DatabaseFileStorageService(dataSource);

	assertThrows(CMSException.class, () -> service.uploadFile("archivo.txt", "text/plain", "contenido".getBytes()));
    }

    // ── updateFile ──────────────────────────────────────────────────────────

    @Test
    void updateFile_idNulo_lanzaCMSException()
    {

	DatabaseFileStorageService service = new DatabaseFileStorageService(dataSource);

	assertThrows(CMSException.class, () -> service.updateFile(null, "archivo.txt", "text/plain", new byte[0]));
    }

    @Test
    void updateFile_success() throws Exception
    {

	when(dataSource.getConnection()).thenReturn(connection);
	when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
	when(preparedStatement.executeUpdate()).thenReturn(1);

	DatabaseFileStorageService service = new DatabaseFileStorageService(dataSource);

	service.updateFile("1", "archivo.txt", "text/plain", "hola".getBytes());

	verify(preparedStatement).setString(1, "archivo.txt");
	verify(preparedStatement).setLong(3, 1L);
	verify(preparedStatement).executeUpdate();
    }

    @Test
    void updateFile_archivoNoEncontrado_lanzaCMSException() throws Exception
    {

	when(dataSource.getConnection()).thenReturn(connection);
	when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
	when(preparedStatement.executeUpdate()).thenReturn(0);

	DatabaseFileStorageService service = new DatabaseFileStorageService(dataSource);

	assertThrows(CMSException.class,
		() -> service.updateFile("1", "archivo.txt", "text/plain", "hola".getBytes()));
    }

    // ── getFile ─────────────────────────────────────────────────────────────

    @Test
    void getFile_success_devuelveContenido() throws Exception
    {

	when(dataSource.getConnection()).thenReturn(connection);
	when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
	when(preparedStatement.executeQuery()).thenReturn(resultSet);
	when(resultSet.next()).thenReturn(true);
	when(resultSet.getLong("id")).thenReturn(1L);
	when(resultSet.getString("name")).thenReturn("archivo.txt");
	when(resultSet.getBytes("content")).thenReturn("hola".getBytes());

	DatabaseFileStorageService service = new DatabaseFileStorageService(dataSource);
	byte[] resultado = service.getFile("1");

	assertArrayEquals("hola".getBytes(), resultado);
    }

    @Test
    void getFile_idNulo_lanzaCMSException()
    {

	DatabaseFileStorageService service = new DatabaseFileStorageService(dataSource);

	assertThrows(CMSException.class, () -> service.getFile(null));
    }

    @Test
    void getFile_noEncontrado_lanzaTessaException() throws Exception
    {

	when(dataSource.getConnection()).thenReturn(connection);
	when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
	when(preparedStatement.executeQuery()).thenReturn(resultSet);
	when(resultSet.next()).thenReturn(false);

	DatabaseFileStorageService service = new DatabaseFileStorageService(dataSource);

	assertThrows(TessaException.class, () -> service.getFile("999"));
    }

    @Test
    void getFile_sqlException_lanzaCMSException() throws Exception
    {

	when(dataSource.getConnection()).thenReturn(connection);
	when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Error"));

	DatabaseFileStorageService service = new DatabaseFileStorageService(dataSource);

	assertThrows(CMSException.class, () -> service.getFile("1"));
    }

    // ── deleteFile ──────────────────────────────────────────────────────────

    @Test
    void deleteFile_success() throws Exception
    {

	when(dataSource.getConnection()).thenReturn(connection);
	when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

	DatabaseFileStorageService service = new DatabaseFileStorageService(dataSource);
	service.deleteFile("1");

	verify(preparedStatement).setLong(1, 1L);
	verify(preparedStatement).executeUpdate();
    }

    @Test
    void deleteFile_idNulo_lanzaCMSException()
    {

	DatabaseFileStorageService service = new DatabaseFileStorageService(dataSource);

	assertThrows(CMSException.class, () -> service.deleteFile(null));
    }

    @Test
    void deleteFile_sqlException_lanzaCMSException() throws Exception
    {

	when(dataSource.getConnection()).thenReturn(connection);
	when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Error"));

	DatabaseFileStorageService service = new DatabaseFileStorageService(dataSource);

	assertThrows(CMSException.class, () -> service.deleteFile("1"));
    }

    // ── getFileSize ──────────────────────────────────────────────────────────

    @Test
    void getFileSize_success_devuelveTamano() throws Exception
    {

	when(dataSource.getConnection()).thenReturn(connection);
	when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
	when(preparedStatement.executeQuery()).thenReturn(resultSet);
	when(resultSet.next()).thenReturn(true);
	when(resultSet.getLong(1)).thenReturn(1024L);

	DatabaseFileStorageService service = new DatabaseFileStorageService(dataSource);
	long tamano = service.getFileSize("1");

	assertEquals(1024L, tamano);
    }

    @Test
    void getFileSize_idNulo_lanzaCMSException()
    {

	DatabaseFileStorageService service = new DatabaseFileStorageService(dataSource);

	assertThrows(CMSException.class, () -> service.getFileSize(null));
    }

    @Test
    void getFileSize_noEncontrado_lanzaTessaException() throws Exception
    {

	when(dataSource.getConnection()).thenReturn(connection);
	when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
	when(preparedStatement.executeQuery()).thenReturn(resultSet);
	when(resultSet.next()).thenReturn(false);

	DatabaseFileStorageService service = new DatabaseFileStorageService(dataSource);

	assertThrows(TessaException.class, () -> service.getFileSize("999"));
    }

    @Test
    void getFileSize_sqlException_lanzaCMSException() throws Exception
    {

	when(dataSource.getConnection()).thenReturn(connection);
	when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Error"));

	DatabaseFileStorageService service = new DatabaseFileStorageService(dataSource);

	assertThrows(CMSException.class, () -> service.getFileSize("1"));
    }

}
