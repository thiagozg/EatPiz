package br.com.maven.pizzaria.modelo.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.maven.pizzaria.modelo.entidades.Pizza;
import br.com.maven.pizzaria.modelo.entidades.Pizzaria;

@Repository
public interface PizzaRepositorio extends CrudRepository<Pizza, Long> {
	
	public List<Pizza> findAllByDono(Pizzaria dono);
	
	public Pizza findByIdAndDono(Long id, Pizzaria dono);
	
	public List<Pizza> findAll();
}
