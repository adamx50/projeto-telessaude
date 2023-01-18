package br.ufg.medicina.tele.DAO;

import java.util.List;

import br.ufg.medicina.tele.entidades.UsuarioEntidade;

public interface UsuarioDAO extends DAO<UsuarioEntidade> {
	
	/**
	 * M�todo que retorna uma cole��o de endere�os de emails de usu�rio que permitem receber newsletter
	 * @return
	 */
	public List<String> listarTodosEnderecoEmails();
	
	/**
	 * M�todo respons�vel por listar os endere�os de emails de todos os usu�rios do PSF que permitem
	 * receber emails.
	 * */
	public List<String> listarTodosEnderecoEmailsPSF();
	
	/**
	 * M�todo respons�vel por listar os endere�os de emails de usu�rios que permitem receber newsletter, pelo id da profissao 
	 * */
	public List<String> listarEnderecoEmailsPorProfissao(Integer ididProfissao);
	
	/**
	 * M�todo respons�vel por listar os endere�os de emails de usarios que permitem receber newsletter, pelo id da especialidade
	 * */
	public List<String> listarEnderecoEmailsPorEspecialidade(Integer idEspecialidade);
	
	/**
	 * M�todo respons�vel por listar os endere�os de emails de todos os Especialistas que permitem receber newsletter
	 * */
	public List<String> listarTodosEnderecoEmailsEspecialista();
	
	
	/**
	 * M�todo respons�vel por listar os endere�os de emails de todos os usu�rios acad�micos
	 * */
	public List<String> listarTodosEnderecoEmailsAcademicos();
	
	
	/**
	 * M�todo respons�vel por listar os usuario por classe
	 * */
	public List<UsuarioEntidade> buscarUsuariosPorClasse(int idClasseUsuario);
	
	
	/**
	 * M�todo respons�vel por retornar o endereco de emails de usuarios pelo id da classe
	 * */
	public List<String> buscarEmailsPorClasse(int idClasseUsuario);
	
	
}
