package br.ufg.medicina.tele.entidades;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the newsletter database table.
 * 
 */
@Entity
@Table(name="newsletter")
public class NewsletterEntidade extends Entidade {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id_newsletter;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date data;

	@Column(nullable=false, length=100)
	private String destinatario;

	@Column(nullable=false, length=500)
	private String link;

	@Column(nullable=false, length=100)
	private String nomeImagem;

	@Column(nullable=false, length=100)
	private String titulo;
	
	
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="emailSucesso_newsletter",
	joinColumns=@JoinColumn(name="id_newsletter"), 
	inverseJoinColumns=@JoinColumn(name="id_emailSucessoEntidade"))
	private List<EmailSucessoEntidade> emailSucessos;
	
	/** Atributo que guarda a quantidade de envio que uma newsletter teve */
	@Transient
	private Integer quantidadeEnvioNewsletter;
	
	@Transient
	private Integer quantidadeDestinatario;
	
	
	
	
	public Integer getQuantidadeDestinatario() {
		return quantidadeDestinatario;
	}



	public void setQuantidadeDestinatario(Integer quantidadeDestinatario) {
		this.quantidadeDestinatario = quantidadeDestinatario;
	}



	public Integer getQuantidadeEnvioNewsletter() {
		return quantidadeEnvioNewsletter;
	}



	public void setQuantidadeEnvioNewsletter(Integer quantidadeEnvioNewsletter) {
		this.quantidadeEnvioNewsletter = quantidadeEnvioNewsletter;
	}



	public NewsletterEntidade() {
    }
	
	

	public List<EmailSucessoEntidade> getEmailSucessos() {
		return emailSucessos;
	}



	public void setEmailSucessos(List<EmailSucessoEntidade> emailSucessos) {
		this.emailSucessos = emailSucessos;
	}



	public int getId_newsletter() {
		return id_newsletter;
	}


	public void setId_newsletter(int id_newsletter) {
		this.id_newsletter = id_newsletter;
	}


	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDestinatario() {
		return this.destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getNomeImagem() {
		return this.nomeImagem;
	}

	public void setNomeImagem(String nomeImagem) {
		this.nomeImagem = nomeImagem;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}