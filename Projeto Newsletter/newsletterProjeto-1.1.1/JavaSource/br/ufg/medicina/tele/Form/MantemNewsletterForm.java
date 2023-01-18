package br.ufg.medicina.tele.Form;

import java.util.ArrayList;
import java.util.List;

import br.ufg.medicina.tele.entidades.NewsletterEntidade;

/**
 * Classe Responsável por centralizar os dados da aplicaçao
 * 
 * @author adao Oliveira
 * 
 * */
/**
 * @author adao
 *
 */
/**
 * @author adao
 *
 */
public class MantemNewsletterForm extends Formulario<NewsletterEntidade>{
	
	/**
	 * Atributo newsletterEntidade.
	 * */
	private NewsletterEntidade newsletterEntidade;
	
	/**
	 * Atributo listaDescricaoEmailsNaoReceberamNewsletter.
	 * */
	private List<String> listaDescricaoEmailsNaoReceberamNewsletter;
	
	/**
	 * Atributo listaDescricaoEmailsUsuarios.
	 * */
	private List<String> listaDescricaoEmailsUsuarios;
	
	/**
	 * Atributo Lista de Destinatários
	 * */
	private ArrayList<String> destinatarios;
	
	/**
	 * Atributo lista de Profissões
	 * */
	private List<String> profissoes;
	
	
	/**
	 * Atributo lista de Especialistas
	 * */
	private ArrayList<String> especialidades;
	
	
	/**
	 * Atributo que recebe o valor da combo destinatario
	 * */
	private String somDestinatarioValor;
	
	/**
	 * Atributo que recebe o valor da combo Profissões
	 * */
	private String somProfissoesValor;
	
	/**
	 * Atributo que recebe o valor da combo Especialistas
	 * */
	private String somEspecialistasValor;
	
	/**
	 * Atributo que recebe um valor booleano para renderizar uma combo
	 * */
	private Boolean somProfissaoRenderiza;
	
	/**
	 * Atributo que recebe um valor booleano para renderizar uma combo
	 */
	private Boolean somEspecialistasRenderiza;
	
	
	/**
	 * Atributo que recebe um valor booleano para renderizar uma combo
	 */
	private Boolean pgComboDinamicasRenderiza;
	
	
	private String opcaoTodos = "TODOS";
	private String opcaoTodoPsf = "TODOS PSF";
	private String opcaoPsfProfissoes = "PSF - PROFISSÕES ->";
	private String opcaoTodosEspecialistas = "TODOS ESPECIALISTAS";
	private String opcaoEspecialistas = "ESPECIALISTAS ->";
	private String opcaoAcademicos = "TODOS ACADEMICOS";
	private String opcaoAdmSistTelessaude = "ADMINISTRADORES SISTEMA TELESSAÚDE";
	
	

	/**
	 * Atributo responsavel por receber o endereco da imagem
	 * */
	private String enderecoImagem;
	
	
	/**
	 * Atributo responsavel por receber o nome de uma imagem
	 * */
	private String nomeImagem; 
	
	/***
	 * Atributo responsável por receber o valor do campo de pesquisa filtroNewsletter
	 * */
	private String fitroNewsletter;
	

	public String getFitroNewsletter() {
		return fitroNewsletter;
	}

	public void setFitroNewsletter(String fitroNewsletter) {
		this.fitroNewsletter = fitroNewsletter;
	}

	public String getOpcaoAdmSistTelessaude() {
		return opcaoAdmSistTelessaude;
	}

	public void setOpcaoAdmSistTelessaude(String opcaoAdmSistTelessaude) {
		this.opcaoAdmSistTelessaude = opcaoAdmSistTelessaude;
	}



	public List<String> getListaDescricaoEmailsUsuarios() {
		return listaDescricaoEmailsUsuarios;
	}



	public void setListaDescricaoEmailsUsuarios(
			List<String> listaDescricaoEmailsUsuarios) {
		this.listaDescricaoEmailsUsuarios = listaDescricaoEmailsUsuarios;
	}



	public List<String> getListaDescricaoEmailsNaoReceberamNewsletter() {
		return listaDescricaoEmailsNaoReceberamNewsletter;
	}



	public void setListaDescricaoEmailsNaoReceberamNewsletter(
			List<String> listaDescricaoEmailsNaoReceberamNewsletter) {
		this.listaDescricaoEmailsNaoReceberamNewsletter = listaDescricaoEmailsNaoReceberamNewsletter;
	}



	public String getEnderecoImagem() {
		return enderecoImagem;
	}



	public void setEnderecoImagem(String enderecoImagem) {
		this.enderecoImagem = enderecoImagem;
	}



	public String getNomeImagem() {
		return nomeImagem;
	}



	public void setNomeImagem(String nomeImagem) {
		this.nomeImagem = nomeImagem;
	}



	public Boolean getPgComboDinamicasRenderiza() {
		return pgComboDinamicasRenderiza;
	}



	public void setPgComboDinamicasRenderiza(Boolean pgComboDinamicasRenderiza) {
		this.pgComboDinamicasRenderiza = pgComboDinamicasRenderiza;
	}



	public ArrayList<String> getDestinatarios() {
		return destinatarios;
	}



	public void setDestinatarios(ArrayList<String> destinatarios) {
		this.destinatarios = destinatarios;
	}



	public List<String> getProfissoes() {
		return profissoes;
	}



	public void setProfissoes(List<String> profissoes) {
		this.profissoes = profissoes;
	}



	public ArrayList<String> getEspecialidades() {
		return especialidades;
	}



	public void setEspecialidades(ArrayList<String> especialidades) {
		this.especialidades = especialidades;
	}



	public Boolean getSomEspecialistasRenderiza() {
		return somEspecialistasRenderiza;
	}



	public void setSomEspecialistasRenderiza(Boolean somEspecialistasRenderiza) {
		this.somEspecialistasRenderiza = somEspecialistasRenderiza;
	}



	public NewsletterEntidade getNewsletterEntidade() {
		
		if(this.newsletterEntidade == null){
			
			this.newsletterEntidade = new NewsletterEntidade();
			
		}
		
		return this.newsletterEntidade;
	}



	public void setNewsletterEntidade(NewsletterEntidade newsletterEntidade) {
		this.newsletterEntidade = newsletterEntidade;
	}



	public String getSomDestinatarioValor() {
		return somDestinatarioValor;
	}



	public void setSomDestinatarioValor(String somDestinatarioValor) {
		this.somDestinatarioValor = somDestinatarioValor;
	}
	

	public String getSomProfissoesValor() {
		return somProfissoesValor;
	}

	public void setSomProfissoesValor(String somProfissoesValor) {
		this.somProfissoesValor = somProfissoesValor;
	}


	public String getSomEspecialistasValor() {
		return somEspecialistasValor;
	}



	public void setSomEspecialistasValor(String somEspecialistasValor) {
		this.somEspecialistasValor = somEspecialistasValor;
	}



	public Boolean getSomProfissaoRenderiza() {
		return somProfissaoRenderiza;
	}



	public void setSomProfissaoRenderiza(Boolean somProfissaoRenderiza) {
		this.somProfissaoRenderiza = somProfissaoRenderiza;
	}



	
	public String getOpcaoTodos() {
		return opcaoTodos;
	}



	public String getOpcaoTodoPsf() {
		return opcaoTodoPsf;
	}



	public String getOpcaoPsfProfissoes() {
		return opcaoPsfProfissoes;
	}



	public String getOpcaoTodosEspecialistas() {
		return opcaoTodosEspecialistas;
	}



	public String getOpcaoEspecialistas() {
		return opcaoEspecialistas;
	}



	public String getOpcaoAcademicos() {
		return opcaoAcademicos;
	}

	@Override
	public NewsletterEntidade getEntidade() {
		
		if(this.newsletterEntidade == null){
			
			this.newsletterEntidade = new NewsletterEntidade();
			
		}
		
		return this.newsletterEntidade;
	}
	
}
