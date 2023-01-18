import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.ufg.medicina.tele.entidades.Entidade;

@Entity
@Table(name="time")
public class Time extends Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8459730516998441793L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id_emailFalhaEntidade;
	
	@Column(nullable=false, length=50)
	private String nomeTime;
	
	
	@OneToMany(mappedBy = "time", targetEntity = Jogador.class, 
			   fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Jogador> jogadores;
	
	

	public int getId_emailFalhaEntidade() {
		return id_emailFalhaEntidade;
	}


	public void setId_emailFalhaEntidade(int id_emailFalhaEntidade) {
		this.id_emailFalhaEntidade = id_emailFalhaEntidade;
	}


	public List<Jogador> getJogadores() {
		return jogadores;
	}


	public void setJogadores(List<Jogador> jogadores) {
		this.jogadores = jogadores;
	}


	public String getNomeTime() {
		return nomeTime;
	}


	public void setNomeTime(String nomeTime) {
		this.nomeTime = nomeTime;
	}
	
	
}
