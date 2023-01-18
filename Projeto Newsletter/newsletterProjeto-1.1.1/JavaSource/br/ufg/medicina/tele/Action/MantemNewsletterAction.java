package br.ufg.medicina.tele.Action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.swing.JOptionPane;

import org.primefaces.event.FileUploadEvent;

import br.ufg.medicina.tele.Form.MantemNewsletterForm;
import br.ufg.medicina.tele.Util.Constantes;
import br.ufg.medicina.tele.entidades.EspecialidadeEntidade;
import br.ufg.medicina.tele.entidades.NewsletterEntidade;
import br.ufg.medicina.tele.entidades.PsfProfissaoEntidade;
import br.ufg.medicina.tele.services.MantemNewsletterService;

/**
 * Classe respons�vel por intermediar os dados da tela com a regra de neg�cio
 * @author adao
 * 
 * **/
@ManagedBean(name="mantemNewsletterAction")
@SessionScoped
public class MantemNewsletterAction extends Action<NewsletterEntidade>{

	private MantemNewsletterService service;
	
	private MantemNewsletterForm formulario;
	
	

	public MantemNewsletterAction() {
		
		
		/**
		 * Carrega a grid de newsletter
		 * */
		this.listarTodos();
		
		/**
		 * Monta a quantidade de newsletter enviadas
		 * */
		this.montarQuantidadeNewsletterEnviadas();
		
		
		/**
		 * Carrega a combo.
		 * */
		this.montarComboDestinatario();
		
		abreInicio();
		
		
	}
	
	
	/**
	 * M�todo respons�vel por filtrar newsletter
	 * @param fitro
	 */
	public void filtrarNewsletter() {
		
		String filtroNewsletter = this.getFormulario().getFitroNewsletter();
		
		this.getFormulario().setEntidades(this.getService().filtrarNewsletter(filtroNewsletter));
	}
	
	public void teste() {
		
		JOptionPane.showMessageDialog(null, "Teste");
		System.out.println("teste");
	}
	
	
	/**
	 * Monta a quantidade de newsletter enviadas
	 * */
	public void montarQuantidadeNewsletterEnviadas(){
		
		for (NewsletterEntidade newsletter : this.getFormulario().getEntidades()) {
    		
			newsletter.setQuantidadeEnvioNewsletter(newsletter.getEmailSucessos().size());
			
			this.setarListaDescricaoEmailsUsuarios(newsletter.getDestinatario());
			
			newsletter.setQuantidadeDestinatario(this.getFormulario().getListaDescricaoEmailsUsuarios().size());
			
		}
		
	}


	
	
	/* (non-Javadoc)
	 * @see br.ufg.medicina.tele.Action.Action#remover()
	 */
	@Override
	public String remover() {
		
		String inicio = "";
		
		try {
			
			super.remover();
			
			this.addMensagemSucesso("Newsletter exclu�da com sucesso!");
			
			inicio = this.abreInicio();
			
		} catch (Exception e) {
			
			this.addMensagemErro("ERRO! Newsletter n�o pode ser exclu�da!");
		}
		
		return inicio;
	}
	
	/* (non-Javadoc)
	 * @see br.ufg.medicina.tele.Action.Action#salvar()
	 */
	@Override
	public String salvar() {
		
		String retorno = "";
		
		String link = "";
		
		if(this.getFormulario().getSomDestinatarioValor().equals(this.getFormulario().getOpcaoPsfProfissoes())){
			
			this.getFormulario().setSomDestinatarioValor("Profiss�o: " + this.getFormulario().getSomProfissoesValor());
		}else if(this.getFormulario().getSomDestinatarioValor().equals(this.getFormulario().getOpcaoEspecialistas())){
			
			this.getFormulario().setSomDestinatarioValor("Especialidade: " + this.getFormulario().getSomEspecialistasValor());
		}
		
		this.getFormulario().getNewsletterEntidade().setDestinatario(this.getFormulario().getSomDestinatarioValor());
		
		this.getFormulario().getNewsletterEntidade().setData(new Date());
		
		this.getFormulario().getNewsletterEntidade().setNomeImagem(this.getFormulario().getNomeImagem());
		
		//setar o endereco da imagem para poder mostrar a imagem
		this.getFormulario().setEnderecoImagem(Constantes.diretorioImgImagemNewsletter + this.getFormulario().getNomeImagem());
		
		try {
			
			String nomeImagem = this.getFormulario().getNewsletterEntidade().getNomeImagem();
			
			link = this.getFormulario().getNewsletterEntidade().getLink();
			
			if(nomeImagem == null || !isURL(link)){
				
				throw new Exception();
			}
			
			super.salvar();
			
			this.addMensagemSucesso("Newsletter salva com sucesso!");
			
			retorno = this.abreInicio();
			
		} catch (Exception e) {
			
			this.addMensagemErro("A Newsletter n�o p�de ser salva!");
			
			if(this.getFormulario().getNewsletterEntidade().getNomeImagem() == null){
				
				this.addMensagemErro("Escolha uma imagem para upload!");
			}
			
			if(!isURL(link)){
				
				this.addMensagemErro("URL Inv�lida!");
			}
			
			retorno = this.abreInclusao();
			
			e.printStackTrace();
		}
		
		
		return retorno;
		
	}
	
	/**
	 * M�todo respons�vel por validar uma URL
	 * */
	private boolean isURL(String url){
		
		String urlPattern = "^http(s{0,1})://[a-zA-Z0-9_/\\-\\.]+\\.([A-Za-z/]{2,5})[a-zA-Z0-9_/\\&\\?\\=\\-\\.\\~\\%]*";
		
		return url.matches(urlPattern);
	}
	
	@Override
	public String alterar() {
		
		String link = "";
		
		if(this.getFormulario().getSomDestinatarioValor().equals(this.getFormulario().getOpcaoPsfProfissoes())){
			
			this.getFormulario().setSomDestinatarioValor("Profiss�o: " + this.getFormulario().getSomProfissoesValor());
		}else if(this.getFormulario().getSomDestinatarioValor().equals(this.getFormulario().getOpcaoEspecialistas())){
			
			this.getFormulario().setSomDestinatarioValor("Especialidade: " + this.getFormulario().getSomEspecialistasValor());
		}
		
		this.getFormulario().getNewsletterEntidade().setDestinatario(this.getFormulario().getSomDestinatarioValor());
		
		if(this.getFormulario().getNomeImagem() != null){
			
			this.getFormulario().getNewsletterEntidade().setNomeImagem(this.getFormulario().getNomeImagem());
		}
		
		String retorno;
		try {
			
			String nomeImagem = this.getFormulario().getNewsletterEntidade().getNomeImagem();
			
			link = this.getFormulario().getNewsletterEntidade().getLink();
			
			if(nomeImagem == null || !isURL(link)){
				
				throw new Exception();
			}
			
			
			super.alterar();
			
			this.addMensagemSucesso("Newsletter atualizada com sucesso!");
			
			retorno = this.abreInicio();
		} catch (Exception e) {
			
			this.addMensagemErro("A Newsletter n�o p�de ser atualizada!");
			
			if(this.getFormulario().getNewsletterEntidade().getNomeImagem() == null){
				
				this.addMensagemErro("Escolha uma imagem para upload!");
			}
			
			if(!isURL(link)){
				
				this.addMensagemErro("URL Inv�lida!");
			}
			
			retorno = this.abreAlteracao();
			
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	
	/**
	 * M�todo respons�vel por enviar newsletter
	 * @throws Exception 
	 */
	public String enviarNewsletter() throws Exception {
		
		NewsletterEntidade newsletter = this.consultar(this.getFormulario().getEntidade().getId_newsletter());
		
		this.setarListaDescricaoEmailsUsuarios(newsletter.getDestinatario());
			
		this.getService().enviarNewsletter(newsletter, this.getFormulario().getListaDescricaoEmailsUsuarios());
		
		this.listarEmailsNaoReceberamNewsletter();
		
		this.montarQuantidadeNewsletterEnviadas();
		
		int quantidadeEnvioNewsletter = newsletter.getQuantidadeEnvioNewsletter();
		
		if(quantidadeEnvioNewsletter == 1){
			
			this.addMensagemSucesso(quantidadeEnvioNewsletter + " Newsletter enviada com sucesso!");
		}else if (quantidadeEnvioNewsletter > 1) {
			
			this.addMensagemSucesso(quantidadeEnvioNewsletter + " Newsletters enviadas com sucesso!");
		}else if (quantidadeEnvioNewsletter == 0){
			
			this.addMensagemErro("Nenhuma newsletter foi enviada!");
		}
		
		return "inicio";
	}
	
	
	/**
	 * M�todo respons�vel por listar Emails que n�o receberam newsletter
	 * */
	public void listarEmailsNaoReceberamNewsletter(){
		
		Integer idNewsletter = this.getFormulario().getEntidade().getId_newsletter();
		
		NewsletterEntidade newsletter = this.getService().consultar(idNewsletter);
		
		String destinatario = newsletter.getDestinatario();
		
		this.setarListaDescricaoEmailsUsuarios(destinatario);
		
		List<String> emailsNaoReceberamNewsletter = this.getService().listarEmailsNaoReceberamNewsletter(this.getFormulario().getListaDescricaoEmailsUsuarios(), newsletter.getId_newsletter());
		
		this.getFormulario().setListaDescricaoEmailsNaoReceberamNewsletter(emailsNaoReceberamNewsletter);
		
	}
	
	
	/**
	 * M�todo respons�vel por setar a lista de Descri�ao de emails 
	 * */
	private void setarListaDescricaoEmailsUsuarios(String destinatario){
		
		if(destinatario.equals(this.getFormulario().getOpcaoTodos())){
			
			this.getFormulario().setListaDescricaoEmailsUsuarios(this.getService().consultarEnderecoEmails());
		}else if(destinatario.contains("Profiss�o")){
			
			//divide a string em duas partes
			String stringDestinatarioInteiro [] = destinatario.split(":");
			
			String segundaParteStringDestinatario = stringDestinatarioInteiro[1];
			
			//busca o id da profissao pelo nome;
			Integer idProfissao = this.getService().buscarIdProfissaoPorNomeProfissao(segundaParteStringDestinatario);
			
			//lista as descri��es de email pelo id da profissao;
			List<String> descricaoEmailsUsuarioDeProfissao = this.getService().listarEnderecoEmailsPorProfissao(idProfissao);
			
			//seta a lista de descri�oes;
			this.getFormulario().setListaDescricaoEmailsUsuarios(descricaoEmailsUsuarioDeProfissao);
		
		} else if (destinatario.contains("Especialidade")){
			
			//divide a string em duas partes
			String stringDestinatarioInteiro [] = destinatario.split(":");
			
			String segundaParteStringDestinatario = stringDestinatarioInteiro[1];
			
			//busca o id da especialidade pelo nome;
			Integer idEspecialidade = this.getService().buscarIdEspecialidadePorNomeEspecialidade(segundaParteStringDestinatario);
			
			//lista as descri��es de email pelo id da profissao;
			List<String> descricaoEmailsUsuarioDeEspecialidade = this.getService().listarEnderecoEmailsPorEspecialista(idEspecialidade);
			
			//seta a lista de descri�oes;
			this.getFormulario().setListaDescricaoEmailsUsuarios(descricaoEmailsUsuarioDeEspecialidade);
			
		}else if(destinatario.equals(this.getFormulario().getOpcaoTodosEspecialistas())){
			
			this.getFormulario().setListaDescricaoEmailsUsuarios(this.getService().listarTodosEnderecoEmailsEspecialistas());
		}else if(destinatario.equals(this.getFormulario().getOpcaoAcademicos())){
			
			this.getFormulario().setListaDescricaoEmailsUsuarios(this.getService().listarTodosEnderecoEmailsAcademicos());
		}else if(destinatario.equals(this.getFormulario().getOpcaoTodoPsf())){
			
			this.getFormulario().setListaDescricaoEmailsUsuarios(this.getService().listarTodosEnderecoEmailsPsf());
		}else if(destinatario.equals(this.getFormulario().getOpcaoAdmSistTelessaude())){
			
			this.getFormulario().setListaDescricaoEmailsUsuarios(this.getService().buscarEmailsPorClasse(1));
		}
	}
	
	
	/**
	 * M�todo respons�vel por abrir a p�gina de inclus�o.
	 * @return
	 */
	public String abreInclusao(){
		
		this.getFormulario().setNewsletterEntidade(new NewsletterEntidade());
		
		this.getFormulario().setEnderecoImagem(null);
		
		this.getFormulario().setNomeImagem(null);
		
		this.getFormulario().setSomDestinatarioValor(null);
		
		this.getFormulario().setSomProfissoesValor(null);
		
		this.getFormulario().setSomEspecialistasValor(null);
		
		this.getFormulario().setSomProfissaoRenderiza(false);
		
		this.getFormulario().setSomEspecialistasRenderiza(false);
    	
    	return "inclusao";
    }
	
	
	/**
	 * M�todo respons�vel por abrir p�gina de visualizacao de newsletter.
	 * */
	public String abreVisualizacao(){
		
		this.abreAlteracao();
		
		return "visualizacao";
	}
	
	
	/**
	 * M�todo respons�vel por abrir a p�gina inicial
	 * */
	public String abreInicio(){
		
		this.listarTodos();
		
		this.montarQuantidadeNewsletterEnviadas();
		
		return "inicio";
		
	}
	
	
	/**
	 * M�todo respons�vel por abrir a p�gina de altera��o
	 * */
	public String abreAlteracao(){
		
		this.getFormulario().setSomProfissaoRenderiza(false);
		this.getFormulario().setSomEspecialistasRenderiza(false);
		
		boolean isDestinatarioProfissao = false;
		boolean isDestinatarioEspecialidade = false;
		
		if(this.getFormulario().getEntidade().getDestinatario().contains("Profiss�o")){
			
			this.getFormulario().setProfissoes(new ArrayList<String>());
			
			for (PsfProfissaoEntidade entidade : this.getService().listarPsfProfissoes()) {
				
				this.getFormulario().getProfissoes().add(entidade.getNome());
			}
			
			this.getFormulario().setSomDestinatarioValor(this.getFormulario().getOpcaoPsfProfissoes());
			
			String [] destinatario = this.getFormulario().getEntidade().getDestinatario().split(":");
			
			this.getFormulario().setSomProfissoesValor(destinatario[1].trim());

			this.getFormulario().setSomProfissaoRenderiza(true);
			
			isDestinatarioProfissao = true;
		}else if(this.getFormulario().getEntidade().getDestinatario().contains("Especialidade")){
			
			this.getFormulario().setEspecialidades(new ArrayList<String>());
			
			for (EspecialidadeEntidade entidade : this.getService().listarEspecialidades()) {
				
				this.getFormulario().getEspecialidades().add(entidade.getNome());
			}
			
			this.getFormulario().setSomDestinatarioValor(this.getFormulario().getOpcaoEspecialistas());
			
			String [] destinatario = this.getFormulario().getEntidade().getDestinatario().split(":");
			
			this.getFormulario().setSomEspecialistasValor(destinatario[1].trim());
			
			this.getFormulario().setSomEspecialistasRenderiza(true);
			
			isDestinatarioEspecialidade = true;
		}
		
		this.getFormulario().setEnderecoImagem(this.getFormulario().getEntidade().getNomeImagem());
		
		if(!isDestinatarioProfissao && !isDestinatarioEspecialidade){
			
			this.getFormulario().setSomDestinatarioValor(this.getFormulario().getEntidade().getDestinatario());
		}
		
		
		return "alteracao";
	}
	
	
	
	/**
	 * M�todo respons�vel
	 * */
	public String voltarInicio(){
		
		return this.abreInicio();
	}
	
	
	/**
	 * M�todo respons�vel por retornar uma nova Entidade
	 * */
	public void novaEntidade(){
		
		this.getFormulario().setNewsletterEntidade(new NewsletterEntidade());
	}
	
	
	/**
	 * M�todo respons�vel por chamar as combos solicitadas
	 */ 
	public void montarComboGeral(){
		
		//ocultar combos
		this.ocultarComboProfissoes();
		this.ocultarComboEspecialistas();
		
		//ocultar painel com combos dinamicas
		this.ocultarCombosDinamicas();
		
		//Chama combo profiss�es
		if(this.getFormulario().getSomDestinatarioValor().equals(this.getFormulario().getOpcaoPsfProfissoes())){
			
			this.getFormulario().setSomProfissoesValor(null);
			
			this.mostrarCombosDinamicas();
			
			this.montarComboProfissoes();
			
		}else if(this.getFormulario().getSomDestinatarioValor().equals(this.getFormulario().getOpcaoEspecialistas())){ //chama combo Especialistas
			
			this.getFormulario().setSomEspecialistasValor(null);
			
			this.mostrarCombosDinamicas();
			
			this.montarComboEspecialistas();
		}
	}
	
	/**
	 * M�todo Respons�vel por carregar a combo de profiss�es do psf
	 */
	private void montarComboProfissoes(){
			
		ArrayList<String> psfProfissoes = new ArrayList<String>();
		
		for (PsfProfissaoEntidade entidade : this.getService().listarPsfProfissoes()) {
			
			psfProfissoes.add(entidade.getNome());
		}
		
		this.getFormulario().setProfissoes(psfProfissoes);
		
		//Habilita combo profissoes
		this.getFormulario().setSomProfissaoRenderiza(true);
			
	}
	
	/**
	 * M�todo Respons�vel por carregar a combo de Especialistas
	 */
	private void montarComboEspecialistas(){
		
		ArrayList<String> especialidades = new ArrayList<String>();
		
		for (EspecialidadeEntidade entidade : this.getService().listarEspecialidades()) {
			
			especialidades.add(entidade.getNome());
		}
		
		this.getFormulario().setEspecialidades(especialidades);
		
		//Habilita combo especialistas
		this.getFormulario().setSomEspecialistasRenderiza(true);
		
	}
	
	
	/**
	 * M�todo respons�vel por ocultar a combo de profiss�es
	 */
	private void ocultarComboProfissoes(){
		
		this.getFormulario().setSomProfissaoRenderiza(false);
		
	}
	
	/**
	 * M�todo respons�vel por ocultar a combo de Especilistas
	 */
	private void ocultarComboEspecialistas(){
		
		this.getFormulario().setSomEspecialistasRenderiza(false);
		
	}
	
	
	/**
	 * M�todo respons�vel por mostrar o painel onde est�o as combos dinamicas
	 */
	private void mostrarCombosDinamicas(){
		
		this.getFormulario().setPgComboDinamicasRenderiza(true);
		
	}
	
	/**
	 * M�todo respons�vel por ocultar o painel onde est�o as combos dinamicas
	 */
	private void ocultarCombosDinamicas(){
		
		this.getFormulario().setPgComboDinamicasRenderiza(false);
		
	}
	
	
	
	/**
	 * M�todo respons�vel por carregar a combo de destinatarios
	 */
	private void montarComboDestinatario(){
		
		this.getFormulario().setDestinatarios(new ArrayList<String>());
		
		
		this.getFormulario().getDestinatarios().add(this.getFormulario().getOpcaoTodos());
		this.getFormulario().getDestinatarios().add(this.getFormulario().getOpcaoTodoPsf());
		this.getFormulario().getDestinatarios().add(this.getFormulario().getOpcaoPsfProfissoes());
		this.getFormulario().getDestinatarios().add(this.getFormulario().getOpcaoTodosEspecialistas());
		this.getFormulario().getDestinatarios().add(this.getFormulario().getOpcaoEspecialistas());
		this.getFormulario().getDestinatarios().add(this.getFormulario().getOpcaoAcademicos());
		this.getFormulario().getDestinatarios().add(this.getFormulario().getOpcaoAdmSistTelessaude());
		
	}
	

	/**
	 * M�todo responsavel por trazer o arquivo para o servidor
	 * 
	 * @throws IOException 
	 */
	public void fazerUpload(FileUploadEvent event) {
		
		try {
			
			String nomeArquivo = this.getService().fazerUpload(event);
			
			this.getFormulario().setNomeImagem(nomeArquivo);
			
			this.getFormulario().setEnderecoImagem(this.getFormulario().getNomeImagem());
			
			this.addMensagemSucesso("Arquivo " + nomeArquivo + " carregado com sucesso!");
			
		} catch (Exception e) {
			
			addMensagemErro("N�o foi poss�vel fazer o upload! " + e.getMessage());
			
			e.printStackTrace();
		}
	}
	

	public MantemNewsletterForm getFormulario() {
		
		if(this.formulario == null){
			
			this.formulario = new MantemNewsletterForm();
			
		}
		
		return this.formulario;
	}


	@Override
	protected MantemNewsletterService getService() {
		
		if(this.service == null){
			
			this.service = new MantemNewsletterService();
			
		}
		
		return this.service;
	}

}
