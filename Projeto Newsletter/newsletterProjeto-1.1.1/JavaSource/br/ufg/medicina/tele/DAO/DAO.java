package br.ufg.medicina.tele.DAO;

import java.io.Serializable;
import java.util.List;

import br.ufg.medicina.tele.entidades.Entidade;


/**
 * Classe respons�vel por prover algumas funcionalidades t�cnicas de persistencia
 * 
 * @author adao
 *
 * @param <E>
 */
public interface DAO<E extends Entidade> {
	
	/**
	 * M�todo respons�vel fazer com que um objeto seja gerenciado pelo contexto
	 * de persist�ncia.
	 * 
	 * @param entidade
	 */
	public void tornarGerenciado(final E entidade);
	
	
	/**
	 * M�todo Responsavel por salvar a entidade no banco
	 * 
	 * @param entidade
	 */
	public void salvar(final E entidade);  
	
	/**
	 * M�todo Respons�vel por atualizar uma entidade j� existente no banco
	 * 
	 * @param entidade
	 */
	public void atualizar(final E entidade);
	
	
	/**
	 * M�todo Responsavel por consultar uma entidade no banco
	 * 
	 * @param id
	 * @return T
	 */
	public E consultar(final Serializable id); 
	
	
    /**
     * M�todo responsavel por retornar uma lista de entidades
     * 
     * @return List<entidade>
     */
    public List<E> listarTodos();
    
    
    /**
     * M�todo responsavel por remover uma entidade no banco
     * 
     * @param entidade
     */
    public void remover(final E entidade); 
	
}
