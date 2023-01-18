package br.ufg.medicina.tele.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the psf_profissoes database table.
 * 
 */
@Entity
@Table(name="psf_profissoes")
public class PsfProfissaoEntidade extends Entidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="envia_diagnostico", nullable=false)
	private byte enviaDiagnostico;

	@Column(name="nivel_superior", nullable=false)
	private byte nivelSuperior;

	@Column(nullable=false, length=100)
	private String nome;

    public PsfProfissaoEntidade() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getEnviaDiagnostico() {
		return this.enviaDiagnostico;
	}

	public void setEnviaDiagnostico(byte enviaDiagnostico) {
		this.enviaDiagnostico = enviaDiagnostico;
	}

	public byte getNivelSuperior() {
		return this.nivelSuperior;
	}

	public void setNivelSuperior(byte nivelSuperior) {
		this.nivelSuperior = nivelSuperior;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}