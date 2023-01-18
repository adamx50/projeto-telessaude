package br.ufg.medicina.tele.DAOImp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.ufg.medicina.tele.DAO.NewsletterDAO;
import br.ufg.medicina.tele.entidades.NewsletterEntidade;

public class NewsletterDAOImp extends GenericsDAO<NewsletterEntidade> implements NewsletterDAO{

	/* (non-Javadoc)
	 * @see br.ufg.medicina.tele.DAO.NewsletterDAO#filtrarNewsletter(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<NewsletterEntidade> filtrarNewsletter(final String fitro) {
		
		Criteria criteria = this.novoCriteria(this.getTipoDaEntidade());
		
		criteria.add(Restrictions.like("titulo", "%" + fitro + "%"));
		
		return criteria.list();
	}
}
