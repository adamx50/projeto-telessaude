import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;

import br.ufg.medicina.tele.Factory.JPAFactory;
import br.ufg.medicina.tele.entidades.Entidade;



public class TesteRelaciomentoJPA {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("unidade_persistente_telemedicina");
	static EntityManager  em = emf.createEntityManager();
	static Session session = null;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/** OneTOmany */
		/*Time time1 = new Time();
		time1.setNomeTime("Real madri");
		
		Jogador jogador1 = new Jogador();
		Jogador jogador2 = new Jogador();
		Jogador jogador3 = new Jogador();
		Jogador jogador4 = new Jogador();
		
		jogador1.setNomeJogador("Ronaldo");
		jogador2.setNomeJogador("Zidane");
		jogador3.setNomeJogador("figo");
		jogador4.setNomeJogador("raul");
		
		jogador1.setTime(time1);
		jogador2.setTime(time1);
		jogador3.setTime(time1);
		jogador4.setTime(time1);
		
		salvar(time1);
		
		salvar(jogador1);
		salvar(jogador2);
		salvar(jogador3);
		salvar(jogador4);*/
		
		/*Criteria crit = getSession().createCriteria(Jogador.class);
		
		crit.add(Restrictions.eq("time.id", 10));
		
		List<Jogador> lista = crit.list();
		
		for (Jogador jogador : lista) {
			
			System.out.println("Jogador -> "+ jogador.getNomeJogador() + " ## ID-> " + jogador.getTime().getNomeTime());
		}*/
		
		
		/** manyToMany **/
		
		/*adicionar cursos na base de dados
		 * 
		Curso curso1 = new Curso();
		Curso curso2 = new Curso();
		Curso curso3 = new Curso();
		Curso curso4 = new Curso();
		
		curso1.setCodigo(1);
		curso2.setCodigo(2);
		curso3.setCodigo(3);
		curso4.setCodigo(4);
		
		curso1.setNome("Medicina");
		curso2.setNome("Odontologia");
		curso3.setNome("Sistemas de informação");
		curso4.setNome("História");
		
		curso1.setSigla("Med");
		curso2.setSigla("OD");
		curso3.setSigla("SI");
		curso4.setSigla("Hist");
		
		salvar(curso1);
		salvar(curso2);
		salvar(curso3);
		salvar(curso4);*/
		
		
		/** adicionar cursos ao departamento **/
		
		Departamento departamento = new Departamento();
		departamento.setNomeDapartamento("Exatas E humanas");
		departamento.setSigla("ExH");
		
		//busca curso na base de dados
		Curso curso1 = (Curso) buscar(Curso.class, 1);
		Curso curso2 = (Curso) buscar(Curso.class, 2);
		Curso curso3 = (Curso) buscar(Curso.class, 3);
		Curso curso4 = (Curso) buscar(Curso.class, 4);
		
		
		departamento.setCursos(new ArrayList<Curso>());
		
		departamento.getCursos().add(curso1);
		departamento.getCursos().add(curso2);
		departamento.getCursos().add(curso3);
		departamento.getCursos().add(curso4);
		
		
		//persiste o departamento
		salvar(departamento);
		
	}
	
	public static void salvar(Entidade entidade){
		
		try {
			
			getSession().getTransaction().begin();
			
			getSession().save(entidade);
			
			getSession().getTransaction().commit();
			
		} catch (Exception e) {
			
			getSession().getTransaction().rollback();
			e.printStackTrace();
		}
	}
	
	public static Object buscar(Class<Curso> classe, Serializable id){
		
		Object retorno = null;
		
		try {
			
			getSession().getTransaction().begin();
			
			retorno = getSession().get(classe, id);
			
		} catch (Exception e) {
			
			getSession().getTransaction().rollback();
			e.printStackTrace();
		}
		
		
		return retorno;
	}
	
	
	
	public EntityManager getEntityManager(){
		
		return JPAFactory.getEntityManager();
		
	}
	
	public static Session getSession(){
		
		if(session == null){
			
			session = (Session) em.getDelegate();
		}
		
		return session;
	}

}
