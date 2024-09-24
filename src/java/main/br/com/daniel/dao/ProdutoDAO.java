package br.com.daniel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.daniel.dao.jdbc.ConnectionFactory;
import br.com.daniel.domain.Produto; 

public class ProdutoDAO implements IProdutoDAO{

	@Override
	public Integer cadastrar(Produto produto) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection(); //Iniciando conexão com o banco de dados
			String sqlCommand = "INSERT INTO tb_produto (id, codigo, nome) VALUES (nextval('id_produto'), ?, ?)"; //comando de inserção
			
			stm = connection.prepareStatement(sqlCommand); //compilando nosso comando
			
			//setando os valores de código e nome
			stm.setString(1, produto.getCodigo());
			stm.setString(2, produto.getNome());
			
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
	public Produto consultar(String codigo) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Produto produto = null;
		try {
			connection = ConnectionFactory.getConnection(); //Iniciando conexão com o banco de dados
			String sqlCommand = "SELECT * FROM tb_produto where codigo = ?"; //comando de inserção
			
			stm = connection.prepareStatement(sqlCommand); //compilando nosso comando
			
			//setando os valores de código e nome
			stm.setString(1, codigo);
			
			rs = stm.executeQuery(); //jogando o resultado para o "rs"
			
			if(rs.next()) {
				produto = new Produto();
				produto.setId(rs.getLong("id"));
				produto.setCodigo(rs.getString("codigo"));
				produto.setNome(rs.getString("nome"));
			}
			return produto;
			
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
	public Integer excluir(Produto produtoBD) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sqlCommand = "DELETE FROM tb_produto WHERE codigo = ?";
			
			stm = connection.prepareStatement(sqlCommand);
			
			stm.setString(1, produtoBD.getCodigo());
			
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
			String sqlCommand = "UPDATE tb_produto SET nome = ?, codigo = ? WHERE codigo = ?";
			
			stm = connection.prepareStatement(sqlCommand);
			
			stm.setString(1, "Mesa Jantar");
			stm.setString(2, "90");
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

	@Override
	public List<Produto> buscarTodos() throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet result = null;
		
		List<Produto> list = new ArrayList<>();
		Produto produto = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			String sqlCommand = "SELECT * FROM tb_produto";
			
			stm = connection.prepareStatement(sqlCommand);
			result = stm.executeQuery();
			
			while(result.next()) {
				produto = new Produto();
				
				Long id = result.getLong("id");
				String nome = result.getString("nome");
				String codigo = result.getString("codigo");
				
				produto.setId(id);
				produto.setNome(nome);
				produto.setCodigo(codigo);
				
				list.add(produto);
			}
			
			return list;
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
