package br.ufg.medicina.tele.BO;

import br.ufg.medicina.tele.DAO.DAO;
import br.ufg.medicina.tele.DAO.PsfProfissaoDAO;
import br.ufg.medicina.tele.DAOImp.PsfProfissaoDAOImp;
import br.ufg.medicina.tele.entidades.PsfProfissaoEntidade;

public class PsfProfissaoBO extends BO<PsfProfissaoEntidade, DAO<PsfProfissaoEntidade>>{
	
	/**
	 * Atributo DAO
	 */
	private PsfProfissaoDAO DAO;
	
	
	/**
	 * Método responsável por retornar o id de uma profissao
	 * */
	public Integer buscarIdProfissaoPorNomeProfissao(String nome) {
		
		return this.getDAO().buscarIdProfissaoPorNomeProfissao(nome);
	}
	
	
	
	/* (non-Javadoc)
	 * @see br.ufg.medicina.tele.BO.BO#getDAO()
	 */
	@Override
	protected PsfProfissaoDAO getDAO() {
		
		if(this.DAO == null){
			
			this.DAO = new PsfProfissaoDAOImp();
		}
		
		return this.DAO;
	}
}
