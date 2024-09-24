package br.com.daniel.dao;

import java.util.List;

import br.com.daniel.domain.Produto;

public interface IProdutoDAO {
	
	public Integer cadastrar(Produto produto) throws Exception;
	
	public Produto consultar(String codigo) throws Exception;

	public Integer excluir(Produto produtoBD)  throws Exception;
	
	public Integer atualizar(String codigo) throws Exception;

	public List<Produto> buscarTodos() throws Exception;
}
