package br.ufg.medicina.tele.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@Table(name="usuarios")
public class UsuarioEntidade extends Entidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=50)
	private String bairro;

	@Column(length=15)
	private String cep;

	@Column(length=50)
	private String cidade;

	@Column(nullable=false)
	private int classe;

	@Column(length=255)
	private String complemento;

	@Column(nullable=false, length=15)
	private String cpf;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="data_cadastro")
	private Date dataCadastro;

	@Column(name="data_de_nascimento", length=15)
	private String dataDeNascimento;

	@Column(nullable=false, length=100)
	private String email;

	@Column(length=255)
	private String endereco;

	@Column(nullable=false, length=2)
	private String estado;

	@Column(nullable=false)
	private byte liberado;

	@Column(nullable=false, length=100)
	private String nome;

	@Column(name="nome_url", nullable=false, length=255)
	private String nomeUrl;

	@Column(length=20)
	private String numero;

	@Column(name="receber_novidades", nullable=false)
	private byte receberNovidades;

	@Column(nullable=false, length=64)
	private String senha;

	@Column(name="tel_cel", length=15)
	private String telCel;

	@Column(name="tel_com", length=15)
	private String telCom;

	@Column(name="tel_res", length=15)
	private String telRes;

    public UsuarioEntidade() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public int getClasse() {
		return this.classe;
	}

	public void setClasse(int classe) {
		this.classe = classe;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getDataDeNascimento() {
		return this.dataDeNascimento;
	}

	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public byte getLiberado() {
		return this.liberado;
	}

	public void setLiberado(byte liberado) {
		this.liberado = liberado;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeUrl() {
		return this.nomeUrl;
	}

	public void setNomeUrl(String nomeUrl) {
		this.nomeUrl = nomeUrl;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public byte getReceberNovidades() {
		return this.receberNovidades;
	}

	public void setReceberNovidades(byte receberNovidades) {
		this.receberNovidades = receberNovidades;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelCel() {
		return this.telCel;
	}

	public void setTelCel(String telCel) {
		this.telCel = telCel;
	}

	public String getTelCom() {
		return this.telCom;
	}

	public void setTelCom(String telCom) {
		this.telCom = telCom;
	}

	public String getTelRes() {
		return this.telRes;
	}

	public void setTelRes(String telRes) {
		this.telRes = telRes;
	}

}