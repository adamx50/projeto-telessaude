package br.ufg.medicina.tele.BO;

import java.util.ArrayList;
import java.util.List;

import br.ufg.medicina.tele.DAO.DAO;
import br.ufg.medicina.tele.DAO.UsuarioDAO;
import br.ufg.medicina.tele.DAOImp.UsuarioDAOImp;
import br.ufg.medicina.tele.entidades.UsuarioEntidade;

public class UsuarioBO extends BO<UsuarioEntidade, DAO<UsuarioEntidade>> {
	
	/**
	 * Atributo DAO
	 */
	private UsuarioDAO DAO;
	
	public List<String> listarTodosEnderecoEmailsPsf() {
		
		return this.getDAO().listarTodosEnderecoEmailsPSF();
	}
	
	public List<String> listarEnderecoEmailsPorProfissao(Integer id) {

		return this.getDAO().listarEnderecoEmailsPorProfissao(id);
	}

	public List<String> listarTodosEnderecoEmailsEspecialistas() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> listarEnderecoEmailsPorEspecialidade(Integer id) {
		
		return this.getDAO().listarEnderecoEmailsPorEspecialidade(id);
	}

	public List<String> listarTodosEnderecoEmailsAcademicos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<String> buscarEmailsPorClasse(Integer idClasseUsuario) {
		
		return this.getDAO().buscarEmailsPorClasse(idClasseUsuario);
	}
	
	
	/**
	 * Método que retorna uma coleção de endereços de emails de usuário que permitem receber newsletter
	 * @return
	 */
	public List<String> consultarEnderecoEmails(){
		
		List<String> listaEnderecoEmails = new ArrayList<String>();
		
		try {
			
			listaEnderecoEmails.addAll(this.getDAO().listarTodosEnderecoEmails());
			
		} catch (Exception e) {
			
			throw e;
		}
		
		
		return listaEnderecoEmails;
		
	}
	
	/* (non-Javadoc)
	 * @see br.ufg.medicina.tele.BO.BO#getDAO()
	 */
	@Override
	protected UsuarioDAO getDAO() {
		
		if(this.DAO == null){
			
			this.DAO = new UsuarioDAOImp();
		}
			
		return this.DAO;
	}
}
