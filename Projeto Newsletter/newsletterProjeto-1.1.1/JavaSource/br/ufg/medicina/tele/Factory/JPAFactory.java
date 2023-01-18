package br.ufg.medicina.tele.Factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe Respons�vel por disponibilizar um gerenciador de entidades: EntityManager.
 * 
 * @author adao
 *
 */
public final class JPAFactory {
	
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	
	/**
	 * M�todo respons�vel por retornar um Entity Manager.
	 * 
	 * @return EntityManager
	 */
	public static EntityManager getEntityManager(){
		
		
		if(entityManager == null){
			
			entityManager = JPAFactory.getEntityManagerFactory().createEntityManager();
		}	
			
		return entityManager;
				
	}
	
	/**
	 * M�todo respons�vel por retornar uma F�BRICA de Entity Manager
	 * 
	 * @return EntityManagerFactory
	 */
	public static EntityManagerFactory getEntityManagerFactory(){
		
		if(JPAFactory.entityManagerFactory == null){
			
			JPAFactory.entityManagerFactory = Persistence.createEntityManagerFactory("unidade_persistente_telemedicina");
			
		}
		
		return JPAFactory.entityManagerFactory;
				
	}
	
}
