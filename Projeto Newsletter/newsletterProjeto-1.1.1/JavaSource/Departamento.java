import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.ufg.medicina.tele.entidades.Entidade;

@Entity
@Table(name="departamento")
public class Departamento extends Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6036733579249826473L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id_emailFalhaEntidade;
	
	@Column(nullable=false, length=50)
	private String nomeDapartamento;
	
	@Column(nullable=false, length=50)
	private String sigla;
	
	
	@ManyToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="departamento_curso",
			   joinColumns=@JoinColumn(name="id_departamento"), 
			   inverseJoinColumns=@JoinColumn(name="id_curso"))
	private List<Curso> cursos;
	
	
	
	public int getId_emailFalhaEntidade() {
		return id_emailFalhaEntidade;
	}

	public void setId_emailFalhaEntidade(int id_emailFalhaEntidade) {
		this.id_emailFalhaEntidade = id_emailFalhaEntidade;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public String getNomeDapartamento() {
		return nomeDapartamento;
	}
	
	public void setNomeDapartamento(String nomeDapartamento) {
		this.nomeDapartamento = nomeDapartamento;
	}
	
	public String getSigla() {
		return sigla;
	}
	
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
}
