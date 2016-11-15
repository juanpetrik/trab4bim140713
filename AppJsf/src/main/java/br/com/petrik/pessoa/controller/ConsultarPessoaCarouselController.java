package br.com.petrik.pessoa.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.petrik.model.PessoaModel;
import br.com.petrik.repository.PessoaRepository;

/**
 *
 * @author Juan, 15 de nov de 2016
 *
 *Classe responsável por controlar o Carousel..
 */
@Named(value="consultarPessoaCarouselController")
@ViewScoped
public class ConsultarPessoaCarouselController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject transient
	private PessoaRepository pessoaRepository;

	@Produces
	private List<PessoaModel> pessoas;

	public List<PessoaModel> getPessoas() {
		return pessoas;
	}

	@PostConstruct
	private void init(){

		this.pessoas = pessoaRepository.GetPessoas();
	}




}