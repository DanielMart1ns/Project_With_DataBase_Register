package br.com.daniel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.daniel.dao.jdbc.ConnectionFactory;
import br.com.daniel.domain.Cliente;

public class ClienteDAO implements IClienteDAO{
	
	@Override
//	Cadastrando meu cliente no banco de dados
	public Integer cadastrar(Cliente cliente) throws Exception{
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection(); //Iniciando conexão com o banco de dados
			String sqlCommand = "INSERT INTO TB_CLIENTE (id, codigo, nome) VALUES (nextval('sq_id_cliente'), ?, ?)"; //comando de inserção
			
			stm = connection.prepareStatement(sqlCommand); //compilando nosso comando
			
			//setando os valores de código e nome
			stm.setString(1, cliente.getCodigo());
			stm.setString(2, cliente.getNome());
			
			return stm.executeUpdate(); //retornando o resultado da execução
		} 
		catch(Exception e) {
			throw e;
		} 
		finally {
			//fechando conexão
			if(stm != null && !stm.isClosed()) {
				stm.close();
			}
			if(connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}

	@Override
	public Cliente consultar(String codigo) throws Exception{
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Cliente cliente = null;
		try {
			connection = ConnectionFactory.getConnection(); //Iniciando conexão com o banco de dados
			String sqlCommand = "SELECT * FROM tb_cliente where codigo = ?"; //comando de inserção
			
			stm = connection.prepareStatement(sqlCommand); //compilando nosso comando
			
			//setando os valores de código e nome
			stm.setString(1, codigo);
			
			rs = stm.executeQuery(); //jogando o resultado para o "rs"
			
			if(rs.next()) {
				cliente = new Cliente();
				cliente.setId(rs.getLong("id"));
				cliente.setCodigo(rs.getString("codigo"));
				cliente.setNome(rs.getString("nome"));
			}
			return cliente;
			
		} 
		catch(Exception e) {
			throw e;
		} 
		finally {
			//fechando conexão
			if(stm != null && !stm.isClosed()) {
				stm.close();
			}
			if(connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}

	@Override
	public Integer excluir(Cliente cliente) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sqlCommand = "DELETE FROM tb_cliente WHERE codigo = ?";
			
			stm = connection.prepareStatement(sqlCommand);
			
			stm.setString(1, cliente.getCodigo());
			
			return stm.executeUpdate();
		} 
		catch (Exception e) {
			throw e;
		} 
		finally {
			if(stm != null && !stm.isClosed()) {
				stm.close();
			}
			
			if(connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}

	@Override
	public Integer atualizar(String codigo) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			String sqlCommand = "UPDATE tb_cliente SET nome = ?, codigo = ? WHERE codigo = ?";
			
			stm = connection.prepareStatement(sqlCommand);
			
			stm.setString(1, "Daniel Martins");
			stm.setString(2, "09");
			stm.setString(3, codigo);
			
			return stm.executeUpdate();
		} 
		catch(Exception e) {
			throw e;
		} 
		finally {
			if(stm != null && !stm.isClosed()) {
				stm.close();
			}
			
			if(connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}
}
