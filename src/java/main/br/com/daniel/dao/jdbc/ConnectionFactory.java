package br.com.daniel.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static Connection connection;
	
	private ConnectionFactory(Connection connection){
		
	}
	
	//Iniciando conexão com banco de dados
	public static Connection getConnection() throws SQLException {
		if(connection == null) {
			connection = initConection();
		} 
		else if (connection.isClosed()) {
			connection = initConection();
		}
		
		return connection;
	}

	private static Connection initConection() {
		try {
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/Projeto3", "postgres", "admin");
		} catch (SQLException e){
			throw new RuntimeException();
		}
	}
}
