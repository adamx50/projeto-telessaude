import java.util.List;

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
@Table(name="curso")
public class Curso extends Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3187793168728864603L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id_emailFalhaEntidade;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="departamento_curso",
			   joinColumns=@JoinColumn(name="id_curso"), 
			   inverseJoinColumns=@JoinColumn(name="id_departamento"))
	private List<Departamento> departamentos;
	
	
	public List<Departamento> getDepartamentos() {
		return departamentos;
	}
	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}
	@Column(nullable=false, length=50)
	private int codigo;
	
	@Column(nullable=false, length=50)
	private String nome;
	
	@Column(nullable=false, length=50)
	private String sigla;
	
	
	public int getId_emailFalhaEntidade() {
		return id_emailFalhaEntidade;
	}
	public void setId_emailFalhaEntidade(int id_emailFalhaEntidade) {
		this.id_emailFalhaEntidade = id_emailFalhaEntidade;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
}
