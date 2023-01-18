package br.ufg.medicina.tele.BO;

import java.util.List;

import br.ufg.medicina.tele.DAO.DAO;
import br.ufg.medicina.tele.DAO.EmailSucessoDAO;
import br.ufg.medicina.tele.DAOImp.EmailSucessoDAOImp;
import br.ufg.medicina.tele.entidades.EmailSucessoEntidade;

public class EmailSucessoBO extends BO<EmailSucessoEntidade, DAO<EmailSucessoEntidade>>{
	
	public EmailSucessoDAO DAO;
	
	
	/**
	 * M�todo respons�vel por buscar um EmailSucessoEntidade pela descri��o do email
	 * */
	public EmailSucessoEntidade consultarEmailSucessoPorDescricaoEmail(String descricaoEmail){
		
		return this.getDAO().consultarEmailSucessoPorDescricaoEmail(descricaoEmail);
	}
	
	/**
	 * M�todo respons�vel por retornar uma lista de emails sucessos para uma determinada
	 * Newsletter.
	 * */
	public List<String> buscarEmailsSucessoPorIdNewsletter(final Integer idNewsletter) {
		
		return this.getDAO().buscarEmailsSucessoPorIdNewsletter(idNewsletter);
	}
	
	@Override
	protected EmailSucessoDAO getDAO() {
		
		if(this.DAO == null){
			
			this.DAO = new EmailSucessoDAOImp();
		}
		
		return this.DAO;
	}

}
