package br.ufg.medicina.tele.BO;

import br.ufg.medicina.tele.DAO.DAO;
import br.ufg.medicina.tele.DAO.EspecialidadeDAO;
import br.ufg.medicina.tele.DAOImp.EspecialidadeDAOImp;
import br.ufg.medicina.tele.entidades.EspecialidadeEntidade;

public class EspecialidadeBO extends BO<EspecialidadeEntidade, DAO<EspecialidadeEntidade>> {

	/**
	 * Atributo DAO
	 */
	private EspecialidadeDAO DAO;
	
	/**
	 * Método responsável por retornar o id de uma especialidade
	 * */
	public Integer buscarIdEspecialidadePorNomeEspecialidade(String nome) {
		
		return this.getDAO().buscarIdEspecialidadePorNomeEspecialidade(nome);
	}
	
	@Override
	protected EspecialidadeDAO getDAO() {
		
		if(this.DAO == null){
			
			this.DAO = new EspecialidadeDAOImp();
		}
		
		return this.DAO;
	}
}
