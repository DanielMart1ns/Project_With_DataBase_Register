package br.com.daniel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import br.com.daniel.dao.ClienteDAO;
import br.com.daniel.dao.IClienteDAO;
import br.com.daniel.dao.IProdutoDAO;
import br.com.daniel.dao.ProdutoDAO;
import br.com.daniel.domain.Cliente;
import br.com.daniel.domain.Produto;



public class ProdutoTeste {
	
	@Test
	public void cadastrarTest() throws Exception {
		IProdutoDAO dao = new ProdutoDAO(); //instanciando métodos do DAO
		
		Produto produto = new Produto(); //Instanciando nosso produto
		produto.setCodigo("01");
		produto.setNome("Cadeira");
		
		Integer qtd = dao.cadastrar(produto); //quando cadastrarmos nosso produto, vai retornar um Integer
		assertTrue(qtd == 1); //Se for criada 1 linha significa que deu certo
		
		//consultando para ver se é o mesmo cadastrado
		Produto produtoBD = dao.consultar(produto.getCodigo());
		assertNotNull(produtoBD);
		assertNotNull(produtoBD.getId());
		assertEquals(produto.getCodigo(), produtoBD.getCodigo());
		assertEquals(produto.getNome(), produtoBD.getNome());
		
		//deletando oq fizemos do banco
		Integer contDel = dao.excluir(produtoBD);
		assertNotNull(contDel);
	}
	
	
	@Test
	public void atualizarTest() throws Exception {
		IProdutoDAO dao = new ProdutoDAO();
		
		//criando cliente
		Produto produto = new Produto();
		produto.setNome("Mesa");
		produto.setCodigo("02");
		
		Integer qtd = dao.cadastrar(produto);
		assertTrue(qtd == 1);
		
		//consultando
		Produto produtoBD = dao.consultar(produto.getCodigo());
		assertNotNull(produtoBD);
		assertNotNull(produtoBD.getId());
		assertEquals(produto.getNome(), produtoBD.getNome());
		assertEquals(produto.getCodigo(), produtoBD.getCodigo());
		
		//atualizando
		Integer wasUpdated = dao.atualizar(produtoBD.getCodigo());
		assertNotNull(wasUpdated);
		assertTrue(wasUpdated == 1);
		
		//consultando
		produtoBD = dao.consultar("90"); //código atualizado
		assertNotNull(produtoBD);
		assertNotNull(produtoBD.getId());
		
		//deletando
		Integer contDel = dao.excluir(produtoBD);
		assertNotNull(contDel);
	}
	
	@Test
	public void buscarTodosTest() throws Exception {
		IProdutoDAO dao = new ProdutoDAO();
		
		//Cadastrando produtos
		Produto produto1 = new Produto();
		produto1.setNome("Armario de cozinha");
		produto1.setCodigo("03");
		Integer countCad = dao.cadastrar(produto1);
		assertTrue(countCad == 1);
		
		Produto produto2 = new Produto();
		produto2.setNome("Sofá");
		produto2.setCodigo("04");
		Integer countCad2 = dao.cadastrar(produto2);
		assertTrue(countCad2 == 1);
		
		//Criando uma lista com todos os produtos
		List<Produto> list = dao.buscarTodos();
		assertNotNull(list);
		assertEquals(2, list.size());
		
		//Deletando dados
		int countDel = 0;
		
		for(Produto produto : list) {
			dao.excluir(produto);
			countDel++;
		}
		
		assertEquals(list.size(), countDel);
		
		//verificando
		list = dao.buscarTodos();
		assertEquals(0, list.size());
	}
}
