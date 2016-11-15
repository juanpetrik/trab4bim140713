package br.com.petrik.pessoa.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.petrik.model.PessoaModel;
import br.com.petrik.repository.PessoaRepository;

/**
 *
 * @author Juan, 10 de nov de 2016
 *
 *         Classe controladora, responsável pelas consultas de pessoa.
 *
 */
@Named(value = "consultarPessoaController")
@ViewScoped
public class ConsultarPessoaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	transient private PessoaModel pessoaModel;

	@Produces
	private List<PessoaModel> pessoas;

	@Inject
	transient private PessoaRepository pessoaRepository;

	public List<PessoaModel> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<PessoaModel> pessoas) {
		this.pessoas = pessoas;
	}

	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}

	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}

	/***
	 * Aqui vamos carregar todas as pessoas, logo na inicialização
	 */
	@PostConstruct
	public void init() {
		this.pessoas = pessoaRepository.GetPessoas();
	}

	/***
	 * Método que vai carregar as inforamações de uma pessoa...
	 */
	public void Editar(PessoaModel pessoaModel){

		pessoaModel.setSexo(pessoaModel.getSexo().substring(0, 1));

		this.pessoaModel = pessoaModel;

	}

	/***
	 * Método que atualiza o registro..
	 */
	public void AlterarRegistro(){

		this.pessoaRepository.AlterarRegistro(this.pessoaModel);

		this.init();
	}

	/***
	 * Métodoq que exclui um registro do banco de dados..
	 */
	public void ExcluirPessoa(PessoaModel pessoaModel){

		this.pessoaRepository.ExcluirRegistro(pessoaModel.getCodigo());

		this.pessoas.remove(pessoaModel);
	}
}