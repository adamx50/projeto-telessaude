package br.ufg.medicina.tele.entidades;

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


/**
 * @author adao
 * 
 * Classe persistente para a tabela emailSucesso do banco de dados.
 */
@Entity
@Table(name="emailSucesso")
public class EmailSucessoEntidade extends Entidade {

	/**
	 * Versão Serial
	 */
	private static final long serialVersionUID = -7955661565769760413L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id_emailSucessoEntidade;

	@Column(nullable=false, length=45)
	private String descricaoEmailSucesso;
	
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="emailSucesso_newsletter",
			   joinColumns=@JoinColumn(name="id_emailSucessoEntidade"), 
			   inverseJoinColumns=@JoinColumn(name="id_newsletter"))
	private List<NewsletterEntidade> newsletters;


	public int getId_emailSucessoEntidade() {
		return id_emailSucessoEntidade;
	}


	public void setId_emailSucessoEntidade(int id_emailSucessoEntidade) {
		this.id_emailSucessoEntidade = id_emailSucessoEntidade;
	}

	public String getDescricaoEmailSucesso() {
		return descricaoEmailSucesso;
	}


	public void setDescricaoEmailSucesso(String descricaoEmailSucesso) {
		this.descricaoEmailSucesso = descricaoEmailSucesso;
	}


	public List<NewsletterEntidade> getNewsletters() {
		return newsletters;
	}


	public void setNewsletters(List<NewsletterEntidade> newsletters) {
		this.newsletters = newsletters;
	}
	
}
