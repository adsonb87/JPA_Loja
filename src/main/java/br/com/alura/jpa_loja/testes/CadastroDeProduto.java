package br.com.alura.jpa_loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.jpa_loja.dao.ProdutoDAO;
import br.com.alura.jpa_loja.modelo.Produto;
import br.com.alura.jpa_loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {

		Produto celular = new Produto();
		celular.setNome("Xiaomi Redmi");
		celular.setDescricao("Redmi pro");
		celular.setPreco(new BigDecimal("800"));

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO dao = new ProdutoDAO(em);

		em.getTransaction().begin();
		dao.cadastrar(celular);
		em.getTransaction().commit();
		em.close();

	}
}
