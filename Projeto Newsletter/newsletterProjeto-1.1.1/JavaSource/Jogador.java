import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.ufg.medicina.tele.entidades.Entidade;

@Entity
@Table(name="jogador")
public class Jogador extends Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3273387950574479517L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id_emailFalhaEntidade;
	
	private String nomeJogador;
	
	
	
	@ManyToOne
	@JoinColumn(name="time_id")
	private Time time;
	

	public int getId_emailFalhaEntidade() {
		return id_emailFalhaEntidade;
	}

	public void setId_emailFalhaEntidade(int id_emailFalhaEntidade) {
		this.id_emailFalhaEntidade = id_emailFalhaEntidade;
	}

	public String getNomeJogador() {
		return nomeJogador;
	}

	public void setNomeJogador(String nomeJogador) {
		this.nomeJogador = nomeJogador;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}
	
}
