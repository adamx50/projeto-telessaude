package br.ufg.medicina.tele.DAO;

import java.io.Serializable;
import java.util.List;

import br.ufg.medicina.tele.entidades.Entidade;


/**
 * Classe responsável por prover algumas funcionalidades técnicas de persistencia
 * 
 * @author adao
 *
 * @param <E>
 */
public interface DAO<E extends Entidade> {
	
	/**
	 * Método responsável fazer com que um objeto seja gerenciado pelo contexto
	 * de persistência.
	 * 
	 * @param entidade
	 */
	public void tornarGerenciado(final E entidade);
	
	
	/**
	 * Método Responsavel por salvar a entidade no banco
	 * 
	 * @param entidade
	 */
	public void salvar(final E entidade);  
	
	/**
	 * Método Responsável por atualizar uma entidade já existente no banco
	 * 
	 * @param entidade
	 */
	public void atualizar(final E entidade);
	
	
	/**
	 * Método Responsavel por consultar uma entidade no banco
	 * 
	 * @param id
	 * @return T
	 */
	public E consultar(final Serializable id); 
	
	
    /**
     * Método responsavel por retornar uma lista de entidades
     * 
     * @return List<entidade>
     */
    public List<E> listarTodos();
    
    
    /**
     * Método responsavel por remover uma entidade no banco
     * 
     * @param entidade
     */
    public void remover(final E entidade); 
	
}
