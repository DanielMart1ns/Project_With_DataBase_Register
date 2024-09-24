package br.com.daniel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import br.com.daniel.dao.ClienteDAO;
import br.com.daniel.dao.IClienteDAO;
import br.com.daniel.domain.Cliente;

public class ClienteTeste {
	
	@Test
	public void cadastrarTest() throws Exception {
		IClienteDAO dao = new ClienteDAO(); //instanciando métodos do DAO
		
		Cliente cliente = new Cliente(); //Instanciando nosso cliente
		cliente.setCodigo("01");
		cliente.setNome("Daniel M.");
		
		Integer qtd = dao.cadastrar(cliente); //quando cadastrarmos nosso cliente, vai retornar um Integer
		assertTrue(qtd == 1); //Se for criada 1 linha significa que deu certo
		
		//consultando para ver se é o mesmo cadastrado
		Cliente clienteBD = dao.consultar(cliente.getCodigo());
		assertNotNull(clienteBD);
		assertNotNull(clienteBD.getId());
		assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
		assertEquals(cliente.getNome(), clienteBD.getNome());
		
		//deletando oq fizemos do banco
		Integer contDel = dao.excluir(clienteBD);
		assertNotNull(contDel);
	}
	
	@Test
	public void atualizarTest() throws Exception {
		IClienteDAO dao = new ClienteDAO();
		
		//criando cliente
		Cliente cliente = new Cliente();
		cliente.setNome("Daniel");
		cliente.setCodigo("02");
		
		Integer qtd = dao.cadastrar(cliente);
		assertTrue(qtd == 1);
		
		//consultando
		Cliente clienteBD = dao.consultar(cliente.getCodigo());
		assertNotNull(clienteBD);
		assertNotNull(clienteBD.getId());
		assertEquals(cliente.getNome(), clienteBD.getNome());
		assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
		
		//atualizando
		Integer wasUpdated = dao.atualizar(clienteBD.getCodigo());
		assertNotNull(wasUpdated);
		assertTrue(wasUpdated == 1);
		
		//consultando
		clienteBD = dao.consultar("09"); //código atualizado
		assertNotNull(clienteBD);
		assertNotNull(clienteBD.getId());
		
		//deletando
		Integer contDel = dao.excluir(clienteBD);
		assertNotNull(contDel);
	}
	
	@Test
	public void buscarTodosTest() throws Exception {
		IClienteDAO dao = new ClienteDAO();
		
		//Cadastrando clientes
		Cliente cliente1 = new Cliente();
		cliente1.setNome("Maria");
		cliente1.setCodigo("03");
		Integer countCad = dao.cadastrar(cliente1);
		assertTrue(countCad == 1);
		
		Cliente cliente2 = new Cliente();
		cliente2.setNome("Eliane");
		cliente2.setCodigo("04");
		Integer countCad2 = dao.cadastrar(cliente2);
		assertTrue(countCad2 == 1);
		
		//Criando uma lista com todos os clientes
		List<Cliente> list = dao.buscarTodos();
		assertNotNull(list);
		assertEquals(2, list.size());
		
		//Deletando dados
		int countDel = 0;
		
		for(Cliente cliente : list) {
			dao.excluir(cliente);
			countDel++;
		}
		
		assertEquals(list.size(), countDel);
		
		//verificando
		list = dao.buscarTodos();
		assertEquals(0, list.size());
	}
}
