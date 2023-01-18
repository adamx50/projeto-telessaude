package br.ufg.medicina.tele.DAO;

import br.ufg.medicina.tele.entidades.PsfProfissaoEntidade;

public interface PsfProfissaoDAO extends DAO<PsfProfissaoEntidade>{
	
	/**
	 * Método responsável por retornar o id de uma profissao
	 * */
	public Integer buscarIdProfissaoPorNomeProfissao(String nome);
	
}
