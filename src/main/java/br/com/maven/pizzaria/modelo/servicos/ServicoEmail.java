package br.com.maven.pizzaria.modelo.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maven.pizzaria.modelo.entidades.Email;
import br.com.maven.pizzaria.modelo.repositorios.EmailRepositorio;

@Service
public class ServicoEmail {
	
	@Autowired private ServicoPizzaria servicoPizzaria;
	@Autowired private EmailRepositorio repositorio;

	public List<Email> listar() {
		return repositorio.findByDono(servicoPizzaria.getPizzariaLogada());
	}
	
	public void salvar(Email e) {
		e.setDono(servicoPizzaria.getPizzariaLogada());
		repositorio.save(e);
	}

	public void remover(long id) {
		Email e = this.buscar(id);
		if(e != null) repositorio.delete(e);
	}
	
	public Email buscar(long id) {
		return repositorio.findByIdAndDono(id, servicoPizzaria.getPizzariaLogada());
	}
	
}
