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
 * Classe Respons�vel por prover a combina��o da aplica��o flex com a 
 * aplica��o java.
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
	 * Realiza a remo�ao.
	 * 
	 * @param entidade
	 */
	public String remover(){
		
		this.getService().remover(this.getFormulario().getEntidade());
		
		return "";
	} 
	
	
	/**
	 * Realiza a edi��o
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
	 * M�todo respons�vel por abrir a p�gina de inclus�o.
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
	 * M�todo respons�vel por setar um nova entidade
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
     * M�todo respons�vel por adicionar uma mensagem de sucesso
     * */
    public void addMensagemSucesso(String mensagem){
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, ""));
		
	}
    
    
    /**
     * M�todo respons�vel por adicionar uma mensagem de aviso
     * */
    public void addMensagemAviso(String mensagem){
    	
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, ""));
    }
    
    
    /**
     * M�todo respons�vel por adicionar uma mensagem de Erro
     * */
    public void addMensagemErro(String mensagem){
    	
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, ""));
    	
    }
    
    
}
