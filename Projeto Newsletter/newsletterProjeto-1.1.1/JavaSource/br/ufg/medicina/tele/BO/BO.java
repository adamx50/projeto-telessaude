package br.ufg.medicina.tele.BO;

import java.io.Serializable;
import java.util.List;

import br.ufg.medicina.tele.DAO.DAO;
import br.ufg.medicina.tele.entidades.Entidade;

public abstract class BO<E extends Entidade, D extends DAO<E>> {
	
	protected abstract D getDAO();

	public void salvar(final E entidade) {
		
		this.getDAO().salvar(entidade);
		
	}
	
	/**
	 * M�todo respons�vel por atualizar uma entidade. 
	 * */
	public void atualizar(final E entidade) {
		
		this.getDAO().atualizar(entidade);
		
	}
	
	/**
	 * M�todo respons�vel fazer com que um objeto seja gerenciado pelo contexto
	 * de persist�ncia.
	 * 
	 * 
	 * @param entidade
	 */
	public void tornarGerenciado(E entidade){
		
		this.getDAO().tornarGerenciado(entidade);
	}

	
	/**
	 * M�todo Responsavel por consultar uma entidade no banco
	 * 
	 * @param id
	 * @return
	 */
	public E consultar(final Serializable id) {
		
		return this.getDAO().consultar(id);
		
	}

	public List<E> listarTodos() {
		
		return this.getDAO().listarTodos();
		
	}

	public void remover(final E entidade) {
		
		this.getDAO().remover(entidade);
		
	}
}
