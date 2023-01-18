package br.ufg.medicina.tele.DAO;

import br.ufg.medicina.tele.entidades.EspecialidadeEntidade;

public interface EspecialidadeDAO extends DAO<EspecialidadeEntidade> {
	
	/**
	 * M�todo respons�vel por retornar o id de uma especialidade
	 * */
	public Integer buscarIdEspecialidadePorNomeEspecialidade(String nome);
}
