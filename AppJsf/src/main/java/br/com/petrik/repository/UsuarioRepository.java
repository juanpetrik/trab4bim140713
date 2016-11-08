package br.com.petrik.repository;

import java.io.Serializable;

import javax.persistence.Query;

import br.com.petrik.model.UsuarioModel;
import br.com.petrik.repository.entity.UsuarioEntity;
import br.petrik.uteis.Uteis;

/**
 *
 * @author Juan, 8 de nov de 2016
 *
 * Classe que fará a autenticação do usuário.
 */
public class UsuarioRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	// Método que fará validação do usuário
	public UsuarioEntity ValidaUsuario(UsuarioModel usuarioModel){

		try {

			// Aqui temos a query que será executada..
			Query query = Uteis.JpaEntityManager().createNamedQuery("UsuarioEntity.findUser");

			// Parâmetros
			query.setParameter("usuario", usuarioModel.getUsuario());
			query.setParameter("senha", usuarioModel.getSenha());

			// Devolvendo o usuário.
			return (UsuarioEntity) query.getSingleResult();

		} catch (Exception e) {

			return null;
		}



	}
}