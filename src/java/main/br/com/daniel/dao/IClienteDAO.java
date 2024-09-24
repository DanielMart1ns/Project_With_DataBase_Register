package br.com.daniel.dao;

import java.util.List;

import br.com.daniel.domain.Cliente;

public interface IClienteDAO {
	
	public Integer cadastrar(Cliente cliente) throws Exception;
	
	public Cliente consultar(String codigo) throws Exception;

	public Integer excluir(Cliente clienteBD)  throws Exception;
	
	public Integer atualizar(String codigo) throws Exception;

	public List<Cliente> buscarTodos() throws Exception;
}
