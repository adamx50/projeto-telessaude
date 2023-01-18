package br.ufg.medicina.tele.DAOImp;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;
import br.ufg.medicina.tele.DAO.DAO;
import br.ufg.medicina.tele.entidades.Entidade;


/**
 * 
 * Classe responsável por prover Métodos genéricos de persistência.
 * 
 * @author adao
 *
 * @param <T>
 */
public abstract class GenericsDAO<E extends Entidade> implements DAO<E> {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("unidade_persistente_telemedicina");
	private EntityManager em = null;
	private Session session = null;
	Transaction tx = null;
	
	
	/**
	 * Método responsável por salvar uma entidade.
	 * */
	public void salvar(final E entidade) {
		
		try {
			
			tx = this.iniciarTransação();
			
			this.getSession().save(entidade);
			
			tx.commit();
			
		} catch (Exception e) {
			
			this.rollback();
			e.printStackTrace();
			throw e;
			
		}finally{
			
			this.fecharTransação();
		}

	}
	
	
	/**
	 * Método responsável por alterar uma entidade. 
	 * */
	@Override
	public void atualizar(final E entidade){
		
		try {
			
			tx = this.iniciarTransação();
			
			this.getSession().update(entidade);
			
			tx.commit();
			
		} catch (Exception e) {
			
			this.rollback();
			e.printStackTrace();
			throw e;
			
		}finally{
			
			this.fecharTransação();
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public E consultar(final Serializable id) {
		
		final Class<E> tipo = this.getTipoDaEntidade();
		E resultado = null;
		
		try {
			
			resultado = (E) this.getSession().get(tipo, id);
		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		}
		
		return resultado;
	}

	
	/* (non-Javadoc)
	 * @see br.ufg.medicina.tele.DAO.DAO#listarTodos()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<E> listarTodos() {
		
		Criteria criteria = this.novoCriteria(this.getTipoDaEntidade());
		
        return criteria.list();
	}

	@Override
	public void remover(final E entidade) {
		
		this.getSession().delete(entidade);
		this.getSession().flush();
		
	}
	
	
	/**
	 * Método reponsável por retornar um novo criteria para a entidade
	 * */
	public Criteria novoCriteria(final Class<E> classe){
		
		return this.getSession().createCriteria(classe);
	}
	
	
	
	/**
	 * Retorna o tipo da entidade.
	 * 
	 * @return Classe da entidade.
	 */
	@SuppressWarnings("unchecked")
	protected Class<E> getTipoDaEntidade() {
		
		final Type type[] = ((ParameterizedTypeImpl) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments();

		return (Class<E>) type[0];
	}
	
	
	/**
	 * Método responsável por tornar objeto gerenciado.
	 * */
	public void tornarGerenciado(final E entidade){
		
			this.getSession().merge(entidade);
	}
	
	
	
	/**
	 * Método responsável por iniciar uma transação
	 * @return 
	 * */
	private Transaction iniciarTransação(){
		
		return this.getSession().beginTransaction();
	}
	
	/**
	 * Método responsável por fechar uma transação
	 * */
	private void fecharTransação(){
		
		this.getSession().close();
	}	
	
	/**
	 * Método responsável por gera um rollback
	 * */
	public void rollback(){
		
		if(this.tx != null){
			
			this.getSession().beginTransaction().rollback();
		}
	}
	 
	
	
	
	/**
	 * Método responsável por retornar um entity manager.
	 * */
	private EntityManager getEntityManager(){
		
		if(this.em == null){
			
			this.em = this.emf.createEntityManager();
		}
		
		return this.em;
	}
	
	
	/**
	 * Método responsável por retornar uma sessão.
	 * */
	public Session getSession(){
		
		if(this.session == null || !this.session.isOpen()){
			
			if(this.session != null && !this.session.isOpen()){
				
				this.em = null;
			}
			
			this.session = (Session) this.getEntityManager().getDelegate();
		}
		
		return this.session;
	}
	
	
	
}
