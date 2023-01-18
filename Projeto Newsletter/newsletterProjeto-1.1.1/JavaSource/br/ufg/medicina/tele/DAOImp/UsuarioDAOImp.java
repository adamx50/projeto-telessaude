package br.ufg.medicina.tele.DAOImp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.ufg.medicina.tele.DAO.UsuarioDAO;
import br.ufg.medicina.tele.entidades.UsuarioEntidade;

public class UsuarioDAOImp extends GenericsDAO<UsuarioEntidade> implements UsuarioDAO {

	
	
	/* (non-Javadoc)
	 * @see br.ufg.medicina.tele.DAO.UsuarioDAO#consultarEnderecoEmails()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> listarTodosEnderecoEmails() {
		
		String query = "SELECT usuarios.email " +
			   	   "FROM usuarios " +
				   "WHERE (usuarios.receber_novidades = 1)";
	
		return getSession().createSQLQuery(query).list();
		
		
		/*TEste
		List<String> listaEmails = new ArrayList<String>();
		
		listaEmails.add("cdpcrafa29@gmail.com.br"); 
		listaEmails.add("testex88@gmail.com"); 
		listaEmails.add("adamx3000@gmail.com"); 
		listaEmails.add("adamx50@hotmail.com"); 
		
		return listaEmails;*/
	}
	
	

	/* (non-Javadoc)
	 * @see br.ufg.medicina.tele.DAO.UsuarioDAO#listarTodosEnderecoEmailsPSF()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> listarTodosEnderecoEmailsPSF() {
		
		String query = "SELECT usuarios.email " +
					   "FROM usuarios " +
					   "WHERE (usuarios.classe = 3)" +
						  "AND (usuarios.receber_novidades = 1)";
		
		
		return getSession().createSQLQuery(query).list();
	}

	/* (non-Javadoc)
	 * @see br.ufg.medicina.tele.DAO.UsuarioDAO#listarEnderecoEmailsPorProfissao(java.io.Serializable)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> listarEnderecoEmailsPorProfissao(Integer idProfissao) {
		String query = "SELECT usuarios.email " +
				   "FROM usuarios " +
					   "INNER JOIN us_psf ON (usuarios.id=us_psf.usuario)" +
					   "INNER JOIN psf_profissoes " +
					   		"ON (us_psf.profissao=psf_profissoes.id)" +
				   "WHERE(us_psf.profissao = " + idProfissao + ") " +
				   "AND (usuarios.receber_novidades = 1)";
	
		return getSession().createSQLQuery(query).list();
	}
	

	/* (non-Javadoc)
	 * @see br.ufg.medicina.tele.DAO.UsuarioDAO#listarTodosEnderecoEmailsEspecialistas()
	 */
	@Override
	public List<String> listarTodosEnderecoEmailsEspecialista() {
		
		List<String> listaEmailsEspecilista = null;
		
		List<UsuarioEntidade> listaUsuarios = buscarUsuariosPorClasse(2);
		
		if(listaUsuarios != null && !listaUsuarios.isEmpty()){
			
			listaEmailsEspecilista = new ArrayList<>();
			
			for (UsuarioEntidade usuario : listaUsuarios) {
				
				listaEmailsEspecilista.add(usuario.getEmail());
			}
		}
		
		return listaEmailsEspecilista;
	}
	
	

	/* (non-Javadoc)
	 * @see br.ufg.medicina.tele.DAO.UsuarioDAO#listarEnderecoEmailsPorEspecialistas()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> listarEnderecoEmailsPorEspecialidade(Integer idEspecialidade) {
		
		String query = "SELECT usuarios.email " +
					   "FROM usuarios " +
					   "INNER JOIN us_especialistas ON (usuarios.id=us_especialistas.usuario) " +
					   "INNER JOIN especialidades ON (us_especialistas.especialidade=especialidades.id) " +
					   "WHERE (especialidades.id = " + idEspecialidade + ") " +
					   "AND (usuarios.receber_novidades = 1)";
	
		return getSession().createSQLQuery(query).list();
	}

	/* (non-Javadoc)
	 * @see br.ufg.medicina.tele.DAO.UsuarioDAO#listarTodosEnderecoEmailsAcademicos()
	 */
	@Override
	public List<String> listarTodosEnderecoEmailsAcademicos() {
		
		List<String> listaEmailsAcademicos = null;
		
		List<UsuarioEntidade> listaUsuarios = buscarUsuariosPorClasse(4);
		
		if(listaUsuarios != null && !listaUsuarios.isEmpty()){
			
			listaEmailsAcademicos = new ArrayList<>();
			
			for (UsuarioEntidade usuario : listaUsuarios) {
				
				listaEmailsAcademicos.add(usuario.getEmail());
			}
		}
		
		return listaEmailsAcademicos;
	}



	/* (non-Javadoc)
	 * @see br.ufg.medicina.tele.DAO.UsuarioDAO#buscarUsuariosPorClasse(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UsuarioEntidade> buscarUsuariosPorClasse(int idClasseUsuario) {

		Criteria criteria = this.novoCriteria(this.getTipoDaEntidade());
		
		Criterion classe = Restrictions.eq("classe", idClasseUsuario);
		Criterion receberNovidades = Restrictions.eq("receberNovidades", (byte) idClasseUsuario);
		
		criteria.add(Restrictions.and(classe, receberNovidades));
		
		return criteria.list();
	}



	/* (non-Javadoc)
	 * @see br.ufg.medicina.tele.DAO.UsuarioDAO#buscarEmailsPorClasse(int)
	 */
	@Override
	public List<String> buscarEmailsPorClasse(int idClasseUsuario) {
		
		List<UsuarioEntidade> listaUsuarios = this.buscarUsuariosPorClasse(idClasseUsuario);
		
		List<String> listaEmailsUsuario = null;
		
		if(listaUsuarios != null){
			
			listaEmailsUsuario = new ArrayList<>();
			
			for (UsuarioEntidade usuarioEntidade : listaUsuarios) {
				
				listaEmailsUsuario.add(usuarioEntidade.getEmail());
			}
		}
		
		return listaEmailsUsuario;
	}
	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("unidade_persistente_telemedicina");
		EntityManager em = emf.createEntityManager();
		Session session = (Session) em.getDelegate();
		
		String query = "SELECT usuarios.nome " +
			   	   "FROM usuarios " ;
	
		
		List<String> lista = session.createSQLQuery(query).list();
		
		for (String entidadeNome : lista) {
			
			System.out.println(entidadeNome);
			
		}
		
	}
}
