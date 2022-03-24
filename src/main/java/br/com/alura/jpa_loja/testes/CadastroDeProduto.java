package br.com.alura.jpa_loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.jpa_loja.dao.CategoriaDAO;
import br.com.alura.jpa_loja.dao.ProdutoDAO;
import br.com.alura.jpa_loja.modelo.Categoria;
import br.com.alura.jpa_loja.modelo.CategoriaEnum;
import br.com.alura.jpa_loja.modelo.Produto;
import br.com.alura.jpa_loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {

		cadastrarProduto();

		Long id = 1l;
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDao = new ProdutoDAO(em);
		
		Produto p = produtoDao.buscarPorId(id);
		System.out.println(p.getDescricao());
		
		List<Produto> todos = produtoDao.buscarTodos();
		todos.forEach(produto -> System.out.println(produto.getNome()));
		
		List<Produto> todosPorNome = produtoDao.buscarPorNome("Xiaomi Redmi");
		todosPorNome.forEach(produto -> System.out.println(produto.getNome()));
		
		List<Produto> todosPorNomeDaCategoria = produtoDao.buscarPorNomeDaCategoria("CELULAR");
		todosPorNomeDaCategoria.forEach(produto -> System.out.println(produto.getDescricao()));
		
		BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("Xiaomi Redmi");
		System.out.println(precoDoProduto);
		
		//for (Produto produto : todos) { System.out.println(produto.getNome()); }
		
		/*
		 * //Processo de mudança de estado de transação da entidade
		 * em.getTransaction().begin();
		 * 
		 * //Executando update na entidade em.persist(celulares);
		 * celulares.setNome("SONY");
		 * 
		 * em.flush(); 
		 * em.clear();
		 * 
		 * //Voltando para o estado managed e executando o pudate celulares =
		 * em.merge(celulares); 
		 * celulares.setNome("Samsung"); 
		 * em.flush();
		 * 
		 * //Removendo a entidade 
		 * em.remove(celulares); 
		 * em.flush();
		 */

	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULAR");

		Produto celular = new Produto("Xiaomi Redmi", "Redmi pro", new BigDecimal("800"), celulares);

		EntityManager em = JPAUtil.getEntityManager();
		CategoriaDAO categoriaDao = new CategoriaDAO(em);
		ProdutoDAO produtoDao = new ProdutoDAO(em);

		em.getTransaction().begin();

		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);

		em.getTransaction().commit();
		em.close();
	}
}
