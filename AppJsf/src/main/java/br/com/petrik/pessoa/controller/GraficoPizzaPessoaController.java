package br.com.petrik.pessoa.controller;

import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;

import br.com.petrik.repository.PessoaRepository;

/**
 *
 * @author Juan, 15 de nov de 2016
 *
 *         Controlador responsável pelo gráfico de pizza..
 *
 */
@Named(value = "graficoPizzaPessoaController")
@RequestScoped
public class GraficoPizzaPessoaController {

	@Inject
	private PessoaRepository pessoaRepository;

	private PieChartModel pieChartModel;

	public PieChartModel getPieChartModel() {
		return pieChartModel;
	}

	@PostConstruct
	public void init() {

		this.pieChartModel = new PieChartModel();

		this.MontaGrafico();
	}

	/***
	 * Método responsável por montar o gráfico na página
	 */
	private void MontaGrafico() {

		// Aqui vamos consultar os dados para mostrar...
		Hashtable<String, Integer> hashtableRegistros = pessoaRepository.GetOrigemPessoa();

		// Montando os dados..
		hashtableRegistros.forEach((chave, valor) -> {

			pieChartModel.set(chave, valor);

		});

		pieChartModel.setTitle("Total de Pessoas cadastrado por Tipo");
		pieChartModel.setShowDataLabels(true);
		pieChartModel.setLegendPosition("e");
	}
}