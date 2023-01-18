package br.ufg.medicina.tele.Form;

import java.util.Collection;

import br.ufg.medicina.tele.entidades.Entidade;


public class Formulario <E extends Entidade> {
	
	private E entidade;
	
	private Collection<E> entidades;

	
	public E getEntidade() {
		
		return this.entidade;
	}


	public void setEntidade(E entidade) {
		this.entidade = entidade;
	}


	public Collection<E> getEntidades() {
		return entidades;
	}


	public void setEntidades(Collection<E> entidades) {
		this.entidades = entidades;
	}

}
