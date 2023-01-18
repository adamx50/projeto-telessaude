import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;


public class TesteHerancaoPorSubclasse {
	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("unidade_persistente_telemedicina");
		EntityManager em = emf.createEntityManager();
		Session session = (Session) em.getDelegate();
		
		/*PessoaFisica pf = new PessoaFisica();
		
		pf.setIdPessoa(1);
		pf.setNome("Adão");
		pf.setCpf("123");*/
		
		/*PessoaJuridica pj = new PessoaJuridica();
		
		pj.setIdPessoa(2);
		pj.setNome("Adao Juridico");
		pj.setCnpj("4444");
		
		session.beginTransaction();
		
		session.save(pj);
		
		session.beginTransaction().commit();*/
		
		
		String query = "SELECT telemedicina.pessoa_pai.nome FROM telemedicina.pessoa_pai";
		
		List<String> lista = session.createSQLQuery(query).list();
		
		System.out.println("************************************");
		for (String nomePessoa : lista) {
			
			System.out.println(nomePessoa);
		}
	}
}
