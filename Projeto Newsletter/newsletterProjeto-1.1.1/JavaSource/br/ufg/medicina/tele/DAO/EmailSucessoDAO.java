package br.ufg.medicina.tele.DAO;

import java.util.List;

import br.ufg.medicina.tele.entidades.EmailSucessoEntidade;

public interface EmailSucessoDAO extends DAO<EmailSucessoEntidade>{
	
	/**
	 * Método responsável por buscar um EmailSucessoEntidade pela descrição do email
	 * */
	public EmailSucessoEntidade consultarEmailSucessoPorDescricaoEmail(final String descricaoEmail);
	
	/**
	 * Método responsável por retornar uma lista de emails sucessos para uma determinada
	 * Newsletter.
	 * */
	public List<String> buscarEmailsSucessoPorIdNewsletter(final Integer idNewsletter); 
}
