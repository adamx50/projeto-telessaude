package br.ufg.medicina.tele.services;

import java.io.Serializable;
import java.util.Collection;

import br.ufg.medicina.tele.BO.BO;
import br.ufg.medicina.tele.DAO.DAO;
import br.ufg.medicina.tele.entidades.Entidade;

public abstract class Service<E extends Entidade, bo extends BO<E, DAO<E>>> {
	
	
	/**
	 * Retorna o valor do atributo BO
	 */
	protected abstract bo getBO();

	public void salvar(final E entidade) {
		
		this.getBO().salvar(entidade);
		
	}
	
	/**
	 * Método responsável fazer com que um objeto seja gerenciado pelo contexto
	 * de persistência.
	 * 
	 * 
	 * @param entidade
	 */
	public void tornarGerenciado(E entidade){
		
		this.getBO().tornarGerenciado(entidade);
	}

	/**
	 * Método responsável por atualizar uma entidade. 
	 * */
	public void atualizar(final E entidade) {
		
		this.getBO().atualizar(entidade);
		
	}
	
	/**
	 * Método Responsavel por consultar uma entidade no banco
	 * 
	 * @param id
	 * @return
	 */
	public E consultar(final Serializable id) {
		
		return this.getBO().consultar(id);
		
	}

	public Collection<E> listarTodos() {
		
		return this.getBO().listarTodos();
		
	}

	public void remover(E entidade) {
		
		this.getBO().remover(entidade);
		
	}
}
