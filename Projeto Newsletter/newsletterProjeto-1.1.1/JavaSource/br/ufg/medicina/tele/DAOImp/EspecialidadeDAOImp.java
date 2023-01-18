package br.ufg.medicina.tele.DAOImp;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.ufg.medicina.tele.DAO.EspecialidadeDAO;
import br.ufg.medicina.tele.entidades.EspecialidadeEntidade;

public class EspecialidadeDAOImp extends GenericsDAO<EspecialidadeEntidade> implements EspecialidadeDAO {

	@Override
	public Integer buscarIdEspecialidadePorNomeEspecialidade(String nome) {
		
		Criteria criteria = this.novoCriteria(this.getTipoDaEntidade());
		
		criteria.add(Restrictions.eq("nome", nome.trim()));
		
		EspecialidadeEntidade especialidade = (EspecialidadeEntidade) criteria.uniqueResult();
		
		return especialidade.getId();
	}

	
}
