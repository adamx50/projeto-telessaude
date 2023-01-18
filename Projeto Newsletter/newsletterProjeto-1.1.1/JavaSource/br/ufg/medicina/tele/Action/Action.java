package br.ufg.medicina.tele.Action;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.ufg.medicina.tele.BO.BO;
import br.ufg.medicina.tele.DAO.DAO;
import br.ufg.medicina.tele.Form.Formulario;
import br.ufg.medicina.tele.entidades.Entidade;
import br.ufg.medicina.tele.services.Service;

/**
 * 
 * Classe Responsável por prover a combinação da aplicação flex com a 
 * aplicação java.
 * 
 * @author adao
 *
 */
public abstract class Action <E extends Entidade> {
	
	protected abstract Service<E, BO<E,DAO<E>>> getService();
	protected abstract Formulario<E> getFormulario();
	
	/**
	 * Salva uma entidade
	 * 
	 * @param entidade
	 */
	public String salvar(){
		
		try {
			
			this.getService().salvar(this.getFormulario().getEntidade());
			
		} catch (Exception e) {
			
			throw e;
		}
		
		return "";
		
	} 
	
	
	/**
	 * Realiza a remoçao.
	 * 
	 * @param entidade
	 */
	public String remover(){
		
		this.getService().remover(this.getFormulario().getEntidade());
		
		return "";
	} 
	
	
	/**
	 * Realiza a edição
	 * 
	 * @param entidade
	 */
	public String alterar(){
		
		this.getService().atualizar(this.getFormulario().getEntidade());
		
		return "";
	}
	
	
	/**
	 * realiza a consulta
	 * 
	 * @param entidade
	 * @return entidade
	 */
	public E consultar(final Serializable id){
		
		return this.getService().consultar(id);
		
	}
	
	
    /**
     * Realiza o retorno de uma lista
     * 
     * @return List<entidade>
     */
    public void listarTodos(){
    	
    	this.getFormulario().setEntidades(this.getService().listarTodos());
    	
    }
    
    
    
    /**
	 * Método responsável por abrir a página de inclusão.
	 * @return
     * @throws ClassNotFoundException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
	 */
	/*public String abreInclusao() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
    	
		this.novaEntidade();
    	return "inclusao";
    }*/
	
	/**
	 * Método responsável por setar um nova entidade
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * */
	/*public void novaEntidade() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		
		Class<?> clazz = Class.forName("br.ufg.medicina.tele.entidades.Entidade");
    	Object entidadeObj = clazz.newInstance();
    	
    	this.getFormulario().setEntidade((E) entidadeObj);
	}*/
    
    
    /**
     * Método responsável por adicionar uma mensagem de sucesso
     * */
    public void addMensagemSucesso(String mensagem){
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, ""));
		
	}
    
    
    /**
     * Método responsável por adicionar uma mensagem de aviso
     * */
    public void addMensagemAviso(String mensagem){
    	
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, ""));
    }
    
    
    /**
     * Método responsável por adicionar uma mensagem de Erro
     * */
    public void addMensagemErro(String mensagem){
    	
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, ""));
    	
    }
    
    
}
