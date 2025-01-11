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
	
	
	private static Connection conn = null;// objeto de conexão com banco de dados do jdbc
	// esse Connection faz conecção com varios sistema, porém vamos utilizar o
	// java.sql.Connection que faz com conexão com banco de dados sql
	// isso inclui o MySql, conn é o nome do objeto e null é só para ele iniciar com 
	// alguma coisa
	
	public static Connection getConnection() { // Fazendo a conexão com o banco
		if (conn == null) {
			try { Properties props = loadProperties();
			String url = props.getProperty("dburl");
			conn = DriverManager.getConnection(url, props); // DriverManager
			// é uma classe do JDBC que pega informações para serem inseridas
			// no Connection (aqui meio que estamos conectando como o banco de dados
			// quando chamarmos esse metodo agora ele, vai fazer a conexão com o banco
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage()); // essa exceção
			// é adicionada porque o DriveManager acusa que ele pode gerar
				// um SQLException
			}
		}
		return conn;
	}
	
	
	// Metódo para fechar a conexão
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			}
			catch (SQLException e ) {
				throw new DbException(e.getMessage());// estamos lancando DBException
				// porque essa Classe DBException é deveridado (implementa) runtimeException 
				// onde eu n tenho necessidade de tratar toda exceção
				// se usar direto a SQLException pede para tratar qualquer tipo de excecao
				// e tem que ficar colocando try catch toda hora
			}
		}
	}
	
	
	// Método que carrega o arquivo de configuração do banco de dados
	private static Properties loadProperties() {
	
		try (FileInputStream fs = new FileInputStream("db.properties")){
			Properties props = new Properties(); // Properties que é um recurso do java.util
			props.load(fs);
			return props;// Aqui vamos ler o arquivo db.properties de conexão com o banco e salvar dentro do método
		}
		catch (IOException e) {
				throw new DbException(e.getMessage());
		}
	}
	
	// Método para fechar recursos Statement
	public static void closeStatement(Statement st) {
		if (st != null) {  // ou seja se tiver instanciado{
			try {
			st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}	
		}
	}
		//Método para fechar recurso ResultSet
		public static void closeResultSet(ResultSet rt) {
			if (rt != null) {
				try {
				rt.close();
			} catch (SQLException s) {
				throw new DbException(s.getMessage());
			}
				
		}
		
		
	}
}

