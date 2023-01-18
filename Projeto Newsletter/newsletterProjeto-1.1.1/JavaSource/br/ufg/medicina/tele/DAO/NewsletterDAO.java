package br.ufg.medicina.tele.DAO;

import java.util.List;

import br.ufg.medicina.tele.entidades.NewsletterEntidade;

public interface NewsletterDAO extends DAO<NewsletterEntidade>{
	
	/**
	 * M�todo respons�vel por filtrar newsletter
	 * */
	public List<NewsletterEntidade> filtrarNewsletter(final String fitro);
}
