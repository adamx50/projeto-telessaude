package br.ufg.medicina.tele.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the especialidades database table.
 * 
 */
@Entity
@Table(name="especialidades")
public class EspecialidadeEntidade extends Entidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=100)
	private String area;

	@Column(nullable=false, length=100)
	private String nome;

	@Column(name="sufixo_da_tabela", nullable=false, length=100)
	private String sufixoDaTabela;

    public EspecialidadeEntidade() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSufixoDaTabela() {
		return this.sufixoDaTabela;
	}

	public void setSufixoDaTabela(String sufixoDaTabela) {
		this.sufixoDaTabela = sufixoDaTabela;
	}

}