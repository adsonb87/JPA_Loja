package br.com.alura.jpa_loja.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.jpa_loja.modelo.Categoria;
import br.com.alura.jpa_loja.modelo.Produto;

public class CategoriaDAO {

	private EntityManager em;

	public CategoriaDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Categoria categoria) {
		this.em.persist(categoria);
	}
	
	public void atualizar(Categoria categoria) {
		this.em.merge(categoria);
	}
	
	public void remover(Categoria categoria) {
		categoria = em.merge(categoria);
		this.em.remove(categoria);
	}
	
	public Categoria buscarPorId(Long id) {
		return em.find(Categoria.class, id);
	}
	
	public List<Categoria> buscarTodos() {
		String jpql = "SELECT c FROM Categoria c";
		return em.createQuery(jpql, Categoria.class).getResultList();
	}
	
	public List<Categoria> buscarPorNome(String nome) {
		String jpql = "SELECT c FROM Categoria c WHERE c.nome = :nome";
		return em.createQuery(jpql, Categoria.class)
				.setParameter("nome", nome)
				.getResultList();
	}
	
}
