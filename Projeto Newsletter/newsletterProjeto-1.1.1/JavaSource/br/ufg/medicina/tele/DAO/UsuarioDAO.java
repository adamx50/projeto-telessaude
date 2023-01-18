package br.ufg.medicina.tele.DAO;

import java.util.List;

import br.ufg.medicina.tele.entidades.UsuarioEntidade;

public interface UsuarioDAO extends DAO<UsuarioEntidade> {
	
	/**
	 * Método que retorna uma coleção de endereços de emails de usuário que permitem receber newsletter
	 * @return
	 */
	public List<String> listarTodosEnderecoEmails();
	
	/**
	 * Método responsável por listar os endereços de emails de todos os usuários do PSF que permitem
	 * receber emails.
	 * */
	public List<String> listarTodosEnderecoEmailsPSF();
	
	/**
	 * Método responsável por listar os endereços de emails de usuários que permitem receber newsletter, pelo id da profissao 
	 * */
	public List<String> listarEnderecoEmailsPorProfissao(Integer ididProfissao);
	
	/**
	 * Método responsável por listar os endereços de emails de usarios que permitem receber newsletter, pelo id da especialidade
	 * */
	public List<String> listarEnderecoEmailsPorEspecialidade(Integer idEspecialidade);
	
	/**
	 * Método responsável por listar os endereços de emails de todos os Especialistas que permitem receber newsletter
	 * */
	public List<String> listarTodosEnderecoEmailsEspecialista();
	
	
	/**
	 * Método responsável por listar os endereços de emails de todos os usuários acadêmicos
	 * */
	public List<String> listarTodosEnderecoEmailsAcademicos();
	
	
	/**
	 * Método responsável por listar os usuario por classe
	 * */
	public List<UsuarioEntidade> buscarUsuariosPorClasse(int idClasseUsuario);
	
	
	/**
	 * Método responsável por retornar o endereco de emails de usuarios pelo id da classe
	 * */
	public List<String> buscarEmailsPorClasse(int idClasseUsuario);
	
	
}
