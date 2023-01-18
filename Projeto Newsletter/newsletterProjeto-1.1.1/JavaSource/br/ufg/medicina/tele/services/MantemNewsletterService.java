package br.ufg.medicina.tele.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.primefaces.event.FileUploadEvent;

import br.ufg.medicina.tele.BO.BO;
import br.ufg.medicina.tele.BO.EmailSucessoBO;
import br.ufg.medicina.tele.BO.EspecialidadeBO;
import br.ufg.medicina.tele.BO.NewsletterBO;
import br.ufg.medicina.tele.BO.PsfProfissaoBO;
import br.ufg.medicina.tele.BO.UsuarioBO;
import br.ufg.medicina.tele.DAO.DAO;
import br.ufg.medicina.tele.entidades.EmailSucessoEntidade;
import br.ufg.medicina.tele.entidades.EspecialidadeEntidade;
import br.ufg.medicina.tele.entidades.NewsletterEntidade;
import br.ufg.medicina.tele.entidades.PsfProfissaoEntidade;



public class MantemNewsletterService extends Service<NewsletterEntidade, BO<NewsletterEntidade,DAO<NewsletterEntidade>>>{
	
	//Atributos
	/**
	 * Atributo BO 
	 */
	private NewsletterBO BO;
	
	/**
	 * Atributo UsuarioBO 
	 */
	private UsuarioBO usuarioBO;
	
	/**
	 * Atributo psfProfissaoBO 
	 */
	
	private PsfProfissaoBO psfProfissaoBO;
	
	/**
	 * Atributo especialidadeBO
	 */
	private EspecialidadeBO especialidadeBO;
	
	/**
	 * Atributo emailSucessoBO
	 */
	private EmailSucessoBO emailSucessoBO;
	
	
	
	//Fim Atributos
	
	
	//Métodos
	
	/**
	 * Método responsável por filtrar newsletter
	 * @param fitro
	 * 
	 * @return List<NewsletterEntidade>
	 */
	public List<NewsletterEntidade> filtrarNewsletter(final String fitro) {
		
		return this.getBO().filtrarNewsletter(fitro);
	}
	
	public String fazerUpload(FileUploadEvent event) throws IOException{
		
		return this.getBO().fazerUpload(event);
	}
	
	public List<String> listarEnderecoEmailsPorProfissao(Integer id) {
		
		return this.getUsuarioBO().listarEnderecoEmailsPorProfissao(id);
	}
	
	public List<String> listarEnderecoEmailsPorEspecialista(Integer id) {
		
		return this.getUsuarioBO().listarEnderecoEmailsPorEspecialidade(id);
	}
	
	public List<String> listarTodosEnderecoEmailsEspecialistas() {
		
		return null;
	}


	public List<String> listarTodosEnderecoEmailsAcademicos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<String> listarTodosEnderecoEmailsPsf() {
		
		return this.getUsuarioBO().listarTodosEnderecoEmailsPsf();
	}
	
	public List<String> buscarEmailsPorClasse(Integer idClasseUsuario) {
		
		return this.getUsuarioBO().buscarEmailsPorClasse(idClasseUsuario);
	}
	
	/**
	 * Método responsável por retornar o id de uma profissao
	 * */
	public Integer buscarIdProfissaoPorNomeProfissao(String nome){
		
		return this.getPsfProfissaoBO().buscarIdProfissaoPorNomeProfissao(nome);
	}
	
	/**
	 * Método responsável por retornar o id de uma especialidade
	 * */
	public Integer buscarIdEspecialidadePorNomeEspecialidade(String nome){
		
		return this.getEspecialidadeBO().buscarIdEspecialidadePorNomeEspecialidade(nome);
	}
	
	/**
	 * Método responsável por buscar um EmailSucessoEntidade pela descriçao do email
	 * */
	public EmailSucessoEntidade consultarEmailSucessoPorDescricaoEmail(String descricaoEmail){
		
		return this.getEmailSucessoBO().consultarEmailSucessoPorDescricaoEmail(descricaoEmail);
	}
	
	/**
	 * Método responsável por listar Emails que não receberam newsletter
	 * */
	public List<String> listarEmailsNaoReceberamNewsletter(final List<String> listaDescricaoEmails, Integer idNewsletter){
		
		//Busca uma lista de emailsSucesso por newsletter
		List<String> listaEmailSucessos = this.getEmailSucessoBO().buscarEmailsSucessoPorIdNewsletter(idNewsletter);
		List<String> listaEmailNaoReceberamNewsletter = new ArrayList<>();
		boolean recebeuNewsletter;
		
		//percorrer a lista de descriçao e verificar quais emails nao na receberam newsletter
		for (int i = 0; i < listaDescricaoEmails.size(); i++) {
			
			recebeuNewsletter = false;
			for (int j = 0; j < listaEmailSucessos.size(); j++) {
				
				
				if(listaDescricaoEmails.get(i).equals(listaEmailSucessos.get(j))){
				
					recebeuNewsletter = true;
				}
				
				if(recebeuNewsletter){
					
					break;
				}
				
			}
			
			if(!recebeuNewsletter){
				
				listaEmailNaoReceberamNewsletter.add(listaDescricaoEmails.get(i)); 
			}
		}
		
		return listaEmailNaoReceberamNewsletter;
	}
	
	
	/**
	 * Método Responsável por listar todas as profissões do psf
	 */
	public List<PsfProfissaoEntidade> listarPsfProfissoes(){
		
		return this.getPsfProfissaoBO().listarTodos();
	}
	
	/**
	 * Método Responsável por listar todas as especialidades dos profissionais
	 */
	public Collection<EspecialidadeEntidade> listarEspecialidades() {
		
		return this.getEspecialidadeBO().listarTodos();
	}
	
	
	/**
	 * Método responsável por enviar newsletter
	 */
	public void enviarNewsletter(final NewsletterEntidade newsletterEntidade, final List<String> listaDescricaoEmails) throws Exception {
		
		
		for (int i = 0; i < listaDescricaoEmails.size(); i++) {
			
			EmailSucessoEntidade emailSucessoEntidade = this.consultarEmailSucessoPorDescricaoEmail(listaDescricaoEmails.get(i));
			
			//se existir emailSucessoEntidade
			if(emailSucessoEntidade != null){
				
				//se newsletter tiver esse email em sua lista, então não envie, continua no laço
				if(this.existeEmailSucessoDentroNewsletter(emailSucessoEntidade, newsletterEntidade)){
					
					//Newsletter já foi enviada para este email
					continue;
				}
			}
			
			try {
				
					
				this.getBO().enviarNewsletter(newsletterEntidade, listaDescricaoEmails.get(i));
				//this.tornarGerenciado(newsletterEntidade);
				
				//Se emailSucessoEntidade não existir, cria um novo e salva
				if(emailSucessoEntidade == null){
					
					emailSucessoEntidade = this.obterNovoEmailSucesso();
					
					emailSucessoEntidade.setDescricaoEmailSucesso(listaDescricaoEmails.get(i));
					
					this.addEmailSucessoEmNewsletter(emailSucessoEntidade, newsletterEntidade);
					
					this.emailSucessoBO.salvar(emailSucessoEntidade);
						
					this.atualizar(newsletterEntidade);
					
				}else{ 
					
					//emailSucessoEntidade existe para outra newsletter
					//Agora será adicionada nesta newsletter
					
					this.addEmailSucessoEmNewsletter(emailSucessoEntidade, newsletterEntidade);
					
					this.atualizar(newsletterEntidade);
				}
				
			} catch (Exception e) {
				
				e.printStackTrace();
				//throw e;
			}
			
		}
	}
	
	
	/**
	 * Método responsável por verificar se existe um email sucesso dentro de uma newsletter
	 * */
	private boolean existeEmailSucessoDentroNewsletter(final EmailSucessoEntidade emailSucessoEntidade, final NewsletterEntidade newsletterEntidade){

		List<EmailSucessoEntidade> listaEmailsSucessosDaNewsletter = newsletterEntidade.getEmailSucessos();
		int quantidadeEmailSucessoDentroNewsletter = newsletterEntidade.getEmailSucessos().size();
		
		boolean retorno = false;
		int i = 0;
		
		while(i < quantidadeEmailSucessoDentroNewsletter){
			int id_emailSucesso = emailSucessoEntidade.getId_emailSucessoEntidade();
			int id_emailSucessoDentroNewsletter = listaEmailsSucessosDaNewsletter.get(i).getId_emailSucessoEntidade();
			
			//se o email está presente na lista de emails da newsletter 
			if(id_emailSucesso == id_emailSucessoDentroNewsletter){
				//já existe para essa newsletter
				
				retorno = true;
				break;
			}
			
			i++;
		}
		
		return retorno;
	}
	
	/**
	 * Método responsável por obter um novo emailSucesso
	 * */
	private EmailSucessoEntidade obterNovoEmailSucesso(){
		
		EmailSucessoEntidade emailSucessoEntidade = new EmailSucessoEntidade();
		
		return emailSucessoEntidade;
	}
	
	/**
	 * Método responsável por adicionar um Email na lista de emais da newsletter.
	 * */
	private void addEmailSucessoEmNewsletter(final EmailSucessoEntidade emailSucessoEntidade, final NewsletterEntidade newsletterEntidade){
		
		if(newsletterEntidade.getEmailSucessos() == null){
			
			newsletterEntidade.setEmailSucessos(new ArrayList<EmailSucessoEntidade>());
		}
		
		newsletterEntidade.getEmailSucessos().add(emailSucessoEntidade);
	}
	
	
	
	
	/**
	 * Método que retorna uma coleção de endereços de emails de usuário que permitem receber newsletter
	 * */
	public List<String> consultarEnderecoEmails() {
		
		return this.getUsuarioBO().consultarEnderecoEmails();
	}
	
	
	//Fim Métodos
	
	
	/**
	 * Método que retorna um BO
	 * */
	protected UsuarioBO getUsuarioBO() {
		
		if(this.usuarioBO == null){
			
			this.usuarioBO = new UsuarioBO();
		}
		
		return this.usuarioBO;
	}

	

	protected EmailSucessoBO getEmailSucessoBO() {
		
		if(this.emailSucessoBO == null){
			
			this.emailSucessoBO = new EmailSucessoBO();
		}
		
		return this.emailSucessoBO;
	}


	/* (non-Javadoc)
	 * @see br.ufg.medicina.tele.services.Service#getBO()
	 */
	@Override
	protected NewsletterBO getBO() {

		if(this.BO == null){
			
			this.BO = new NewsletterBO();
		}
		
		return this.BO;
	}


	/**
	 * Método que retorna um BO
	 * */
	protected PsfProfissaoBO getPsfProfissaoBO() {
		
		if(this.psfProfissaoBO == null){
			
			this.psfProfissaoBO = new PsfProfissaoBO();
			
		}
		
		return this.psfProfissaoBO;
	}
	
	/**
	 * Método que retorna um BO
	 * */
	protected EspecialidadeBO getEspecialidadeBO() {
		
		if(this.especialidadeBO == null){
			
			this.especialidadeBO = new EspecialidadeBO();
			
		}
		
		return this.especialidadeBO;
	}

}
