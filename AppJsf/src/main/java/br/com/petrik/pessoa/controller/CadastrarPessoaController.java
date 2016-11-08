package br.com.petrik.pessoa.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.petrik.model.PessoaModel;
import br.com.petrik.repository.PessoaRepository;
import br.com.petrik.usuario.controller.UsuarioController;
import br.petrik.uteis.Uteis;

/**
 *
 * @author Juan, 8 de nov de 2016
 *
 *         Controlador responsável por encaminhar as requisições
 *
 */
@Named(value = "cadastrarPessoaController")
@RequestScoped
public class CadastrarPessoaController {

	@Inject
	PessoaModel pessoaModel;

	@Inject
	UsuarioController usuarioController;

	@Inject
	PessoaRepository pessoaRepository;

	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}

	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}

	/**
	 * Salvando um novo registro por input
	 */
	public void SalvarNovaPessoa() {

		pessoaModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());

		pessoaModel.setOrigemCadastro("I");

		pessoaRepository.SalvarNovoRegistro(this.pessoaModel);

		this.pessoaModel = null;

		Uteis.MensagemInfo("Registro cadastrado com sucesso");

	}

}