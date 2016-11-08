package br.com.petrik.filters;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
/***
 *
 * @author Juan, 8 de nov de 2016
 *
 * Todas vezes que as requisições chegarem no Servlet, este filter será chamado!
 * */
@WebFilter(servletNames ={ "Faces Servlet" })
public class JPAFilter implements Filter {

	private EntityManagerFactory entityManagerFactory;

	private String persistence_unit_name = "unit_app";

    public JPAFilter() {

    }

	public void destroy() {

		this.entityManagerFactory.close();
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		// Aqui vamos criar um Entity Manager
		EntityManager entityManager =  this.entityManagerFactory.createEntityManager();

		// Adicionando o entityManager na requisição
		request.setAttribute("entityManager", entityManager);

		// Iniciando uma transação
		entityManager.getTransaction().begin();

		// Iniciando de fato o faces servlet
		chain.doFilter(request, response);

		try {

			// Se estiver tudo ok, vamos commitar!
			entityManager.getTransaction().commit();

		} catch (Exception e) {

			// Agora, se houve algum erro.. daremos rollback..
			entityManager.getTransaction().rollback();
		}
		finally{

			// Fechando então o entityManager
			entityManager.close();
		}
	}

	// Método de inicialização do Filter..
	public void init(FilterConfig fConfig) throws ServletException {
		// Criando o entityManager com os parâmetros..
		this.entityManagerFactory = Persistence.createEntityManagerFactory(this.persistence_unit_name);
	}

}
