package br.ufg.medicina.tele.DAOImp;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.ufg.medicina.tele.DAO.PsfProfissaoDAO;
import br.ufg.medicina.tele.entidades.PsfProfissaoEntidade;

public class PsfProfissaoDAOImp extends GenericsDAO<PsfProfissaoEntidade> implements PsfProfissaoDAO {
	
	/* (non-Javadoc)
	 * @see br.ufg.medicina.tele.DAO.PsfProfissaoDAO#buscarIdProfissaoPorNomeProfissao()
	 */
	@Override
	public Integer buscarIdProfissaoPorNomeProfissao(String nome) {
		
		Criteria criteria = this.novoCriteria(this.getTipoDaEntidade());
		
		criteria.add(Restrictions.eq("nome", nome.trim()));
		
		PsfProfissaoEntidade profissao = (PsfProfissaoEntidade) criteria.uniqueResult();
		
		return profissao.getId();
	}
}	
