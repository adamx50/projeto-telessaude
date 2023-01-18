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
 * Classe responsável por intermediar os dados da tela com a regra de negócio
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
	 * Método responsável por filtrar newsletter
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
			
			this.addMensagemSucesso("Newsletter excluída com sucesso!");
			
			inicio = this.abreInicio();
			
		} catch (Exception e) {
			
			this.addMensagemErro("ERRO! Newsletter não pode ser excluída!");
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
			
			this.getFormulario().setSomDestinatarioValor("Profissão: " + this.getFormulario().getSomProfissoesValor());
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
			
			this.addMensagemErro("A Newsletter não pôde ser salva!");
			
			if(this.getFormulario().getNewsletterEntidade().getNomeImagem() == null){
				
				this.addMensagemErro("Escolha uma imagem para upload!");
			}
			
			if(!isURL(link)){
				
				this.addMensagemErro("URL Inválida!");
			}
			
			retorno = this.abreInclusao();
			
			e.printStackTrace();
		}
		
		
		return retorno;
		
	}
	
	/**
	 * Método responsável por validar uma URL
	 * */
	private boolean isURL(String url){
		
		String urlPattern = "^http(s{0,1})://[a-zA-Z0-9_/\\-\\.]+\\.([A-Za-z/]{2,5})[a-zA-Z0-9_/\\&\\?\\=\\-\\.\\~\\%]*";
		
		return url.matches(urlPattern);
	}
	
	@Override
	public String alterar() {
		
		String link = "";
		
		if(this.getFormulario().getSomDestinatarioValor().equals(this.getFormulario().getOpcaoPsfProfissoes())){
			
			this.getFormulario().setSomDestinatarioValor("Profissão: " + this.getFormulario().getSomProfissoesValor());
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
			
			this.addMensagemErro("A Newsletter não pôde ser atualizada!");
			
			if(this.getFormulario().getNewsletterEntidade().getNomeImagem() == null){
				
				this.addMensagemErro("Escolha uma imagem para upload!");
			}
			
			if(!isURL(link)){
				
				this.addMensagemErro("URL Inválida!");
			}
			
			retorno = this.abreAlteracao();
			
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	
	/**
	 * Método responsável por enviar newsletter
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
	 * Método responsável por listar Emails que não receberam newsletter
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
	 * Método responsável por setar a lista de Descriçao de emails 
	 * */
	private void setarListaDescricaoEmailsUsuarios(String destinatario){
		
		if(destinatario.equals(this.getFormulario().getOpcaoTodos())){
			
			this.getFormulario().setListaDescricaoEmailsUsuarios(this.getService().consultarEnderecoEmails());
		}else if(destinatario.contains("Profissão")){
			
			//divide a string em duas partes
			String stringDestinatarioInteiro [] = destinatario.split(":");
			
			String segundaParteStringDestinatario = stringDestinatarioInteiro[1];
			
			//busca o id da profissao pelo nome;
			Integer idProfissao = this.getService().buscarIdProfissaoPorNomeProfissao(segundaParteStringDestinatario);
			
			//lista as descrições de email pelo id da profissao;
			List<String> descricaoEmailsUsuarioDeProfissao = this.getService().listarEnderecoEmailsPorProfissao(idProfissao);
			
			//seta a lista de descriçoes;
			this.getFormulario().setListaDescricaoEmailsUsuarios(descricaoEmailsUsuarioDeProfissao);
		
		} else if (destinatario.contains("Especialidade")){
			
			//divide a string em duas partes
			String stringDestinatarioInteiro [] = destinatario.split(":");
			
			String segundaParteStringDestinatario = stringDestinatarioInteiro[1];
			
			//busca o id da especialidade pelo nome;
			Integer idEspecialidade = this.getService().buscarIdEspecialidadePorNomeEspecialidade(segundaParteStringDestinatario);
			
			//lista as descrições de email pelo id da profissao;
			List<String> descricaoEmailsUsuarioDeEspecialidade = this.getService().listarEnderecoEmailsPorEspecialista(idEspecialidade);
			
			//seta a lista de descriçoes;
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
	 * Método responsável por abrir a página de inclusão.
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
	 * Método responsável por abrir página de visualizacao de newsletter.
	 * */
	public String abreVisualizacao(){
		
		this.abreAlteracao();
		
		return "visualizacao";
	}
	
	
	/**
	 * Método responsável por abrir a página inicial
	 * */
	public String abreInicio(){
		
		this.listarTodos();
		
		this.montarQuantidadeNewsletterEnviadas();
		
		return "inicio";
		
	}
	
	
	/**
	 * Método responsável por abrir a página de alteração
	 * */
	public String abreAlteracao(){
		
		this.getFormulario().setSomProfissaoRenderiza(false);
		this.getFormulario().setSomEspecialistasRenderiza(false);
		
		boolean isDestinatarioProfissao = false;
		boolean isDestinatarioEspecialidade = false;
		
		if(this.getFormulario().getEntidade().getDestinatario().contains("Profissão")){
			
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
	 * Método responsável
	 * */
	public String voltarInicio(){
		
		return this.abreInicio();
	}
	
	
	/**
	 * Método responsável por retornar uma nova Entidade
	 * */
	public void novaEntidade(){
		
		this.getFormulario().setNewsletterEntidade(new NewsletterEntidade());
	}
	
	
	/**
	 * Método responsável por chamar as combos solicitadas
	 */ 
	public void montarComboGeral(){
		
		//ocultar combos
		this.ocultarComboProfissoes();
		this.ocultarComboEspecialistas();
		
		//ocultar painel com combos dinamicas
		this.ocultarCombosDinamicas();
		
		//Chama combo profissões
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
	 * Método Responsável por carregar a combo de profissões do psf
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
	 * Método Responsável por carregar a combo de Especialistas
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
	 * Método responsável por ocultar a combo de profissões
	 */
	private void ocultarComboProfissoes(){
		
		this.getFormulario().setSomProfissaoRenderiza(false);
		
	}
	
	/**
	 * Método responsável por ocultar a combo de Especilistas
	 */
	private void ocultarComboEspecialistas(){
		
		this.getFormulario().setSomEspecialistasRenderiza(false);
		
	}
	
	
	/**
	 * Método responsável por mostrar o painel onde estão as combos dinamicas
	 */
	private void mostrarCombosDinamicas(){
		
		this.getFormulario().setPgComboDinamicasRenderiza(true);
		
	}
	
	/**
	 * Método responsável por ocultar o painel onde estão as combos dinamicas
	 */
	private void ocultarCombosDinamicas(){
		
		this.getFormulario().setPgComboDinamicasRenderiza(false);
		
	}
	
	
	
	/**
	 * Método responsável por carregar a combo de destinatarios
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
	 * Método responsavel por trazer o arquivo para o servidor
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
			
			addMensagemErro("Não foi possível fazer o upload! " + e.getMessage());
			
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
