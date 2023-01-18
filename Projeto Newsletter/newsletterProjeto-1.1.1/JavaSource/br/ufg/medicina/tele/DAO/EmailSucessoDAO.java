package br.ufg.medicina.tele.DAO;

import java.util.List;

import br.ufg.medicina.tele.entidades.EmailSucessoEntidade;

public interface EmailSucessoDAO extends DAO<EmailSucessoEntidade>{
	
	/**
	 * M�todo respons�vel por buscar um EmailSucessoEntidade pela descri��o do email
	 * */
	public EmailSucessoEntidade consultarEmailSucessoPorDescricaoEmail(final String descricaoEmail);
	
	/**
	 * M�todo respons�vel por retornar uma lista de emails sucessos para uma determinada
	 * Newsletter.
	 * */
	public List<String> buscarEmailsSucessoPorIdNewsletter(final Integer idNewsletter); 
}
