package br.com.maven.pizzaria.modelo.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maven.pizzaria.modelo.entidades.Telefone;
import br.com.maven.pizzaria.modelo.repositorios.TelefoneRepositorio;

@Service
public class ServicoTelefone {
	
	@Autowired private ServicoPizzaria servicoPizzaria;
	@Autowired private TelefoneRepositorio repositorio;

	public List<Telefone> listar() {
		return repositorio.findByDono(servicoPizzaria.getPizzariaLogada());
	}
	
	public void salvar(Telefone t) {
		t.setDono(servicoPizzaria.getPizzariaLogada());
		repositorio.save(t);
	}

	public void remover(long id) {
		Telefone t = this.buscar(id);
		if(t != null) repositorio.delete(t);
	}
	
	public Telefone buscar(long id) {
		return repositorio.findByIdAndDono(id, servicoPizzaria.getPizzariaLogada());
	}
	
}
