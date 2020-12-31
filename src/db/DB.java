package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Connection conn = null;
	
	public static Connection getConnection() { // fzd uma conexão com o banco de dados
		if (conn == null) {
			try {
				Properties props = loadProperties(); // retornando as props da função loadPro... esse loadProperties foi criada por mim no fim dessa class
				String url = props.getProperty("dburl"); // pegando o local 
				conn = DriverManager.getConnection(url, props); // fzd a conexão 
				}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
				}
		}
		return conn;
	}
	
	public static void closeConnection() { // fechando uma conec��o 
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	private static Properties loadProperties() { // carregando as propiedades da conec��o
		try (FileInputStream fs = new FileInputStream("db.properties")) { // com esse try e esse elementos posso ler um arquivo sem precisar fecha-lo
			Properties props = new Properties(); // repare o tipo da variavel
			props.load(fs); // aq estamos carregando as propiedades especificadas no arquivo db.properties
			return props; // retorna essas props
		}
		catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	// fechando as conec��es
	
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	// fechando as conec��es
	
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
