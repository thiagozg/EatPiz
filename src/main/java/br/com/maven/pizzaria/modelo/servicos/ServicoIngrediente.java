package br.com.maven.pizzaria.modelo.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maven.pizzaria.modelo.entidades.Ingrediente;
import br.com.maven.pizzaria.modelo.entidades.Pizzaria;
import br.com.maven.pizzaria.modelo.repositorios.IngredienteRepositorio;

@Service
public class ServicoIngrediente {
	
	@Autowired private ServicoPizzaria servicoPizzaria;
	@Autowired private IngredienteRepositorio repositorio;

	public void salvar(Ingrediente ingrediente) {
		ingrediente.setDono(servicoPizzaria.getPizzariaLogada());
		repositorio.save(ingrediente);
	}
	
	public Iterable<Ingrediente> listar() {
		Pizzaria dono = servicoPizzaria.getPizzariaLogada();
		return repositorio.findAllByDono(dono);
	}
	
	public Ingrediente buscar(long id) {
		Pizzaria dono = servicoPizzaria.getPizzariaLogada();
		return repositorio.findByIdAndDono(id, dono);
	}
	
	public void remover(long id) {
		Ingrediente ingrediente = this.buscar(id);
		if(ingrediente != null) repositorio.delete(ingrediente);
	}
}
