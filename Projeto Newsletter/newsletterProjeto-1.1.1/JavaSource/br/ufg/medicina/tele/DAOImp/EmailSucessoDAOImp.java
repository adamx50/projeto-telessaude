package br.ufg.medicina.tele.DAOImp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.ufg.medicina.tele.DAO.EmailSucessoDAO;
import br.ufg.medicina.tele.entidades.EmailSucessoEntidade;

public class EmailSucessoDAOImp extends GenericsDAO<EmailSucessoEntidade> implements EmailSucessoDAO{

	/* (non-Javadoc)
	 * @see br.ufg.medicina.tele.DAO.EmailSucessoDAO#consultarEmailSucessoPorDescricaoEmail(java.lang.String)
	 */
	@Override
	public EmailSucessoEntidade consultarEmailSucessoPorDescricaoEmail(final String descricaoEmail) {
		
		Criteria criteria = this.novoCriteria(this.getTipoDaEntidade());
		
		criteria.add(Restrictions.eq("descricaoEmailSucesso", descricaoEmail));
		
		return (EmailSucessoEntidade) criteria.uniqueResult();
	}

	/* (non-Javadoc)
	 * @see br.ufg.medicina.tele.DAO.EmailSucessoDAO#buscarEmailsSucessoPorIdNewsletter(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> buscarEmailsSucessoPorIdNewsletter(Integer idNewsletter) {
		
		String query = "SELECT " +
							"emailsucesso.descricaoEmailSucesso " +
					   "FROM " +
					   		"emailsucesso_newsletter " +
					   "INNER JOIN emailsucesso " +
					   		"ON (emailsucesso_newsletter.id_emailSucessoEntidade=emailsucesso.id_emailSucessoEntidade) " +
					   		"WHERE(emailsucesso_newsletter.id_newsletter = " + idNewsletter + ")";
		
		
		
		return this.getSession().createSQLQuery(query).list();
	}
	
}
