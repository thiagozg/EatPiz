package br.com.maven.pizzaria.modelo.servicos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maven.pizzaria.modelo.entidades.Pizza;
import br.com.maven.pizzaria.modelo.entidades.Pizzaria;
import br.com.maven.pizzaria.modelo.repositorios.PizzaRepositorio;

@Service
public class ServicoPizza {
	
	@Autowired private ServicoPizzaria servicoPizzaria;
	@Autowired private PizzaRepositorio repositorio;

	public void salvar(Pizza pizza) {
		pizza.setDono(servicoPizzaria.getPizzariaLogada());
		repositorio.save(pizza);
	}
	
	public Iterable<Pizza> listar() {
		Pizzaria dono = servicoPizzaria.getPizzariaLogada();
		return repositorio.findAllByDono(dono);
	}
	
	public Pizza buscar(long id) {
		Pizzaria dono = servicoPizzaria.getPizzariaLogada();
		return repositorio.findByIdAndDono(id, dono);
	}
	
	public void remover(long id) {
		Pizza pizza = this.buscar(id);
		if(pizza != null) repositorio.delete(pizza);
	}

	public List<String> listarNomePizzasDisponiveis() {
		List<Pizza> pizzas = repositorio.findAll();
		
		List<String> nomesPizzas = new ArrayList<String>();
		for(Pizza p : pizzas) {
			nomesPizzas.add(p.getNome());
		}
		Collections.sort(nomesPizzas);
		
		// removendo duplicidades
		Set<String> removerDuplicidades = new HashSet<String>();
		removerDuplicidades.addAll(nomesPizzas);
		nomesPizzas.clear();
		nomesPizzas.addAll(removerDuplicidades);
		
		/**
		 * Com vetor de String
		 */
//		String[] nomesPizzas;
//		for(int i=0; i<pizzas.size(); i++) {
//			nomesPizzas[i] = pizzas.get(i).getNome();
//		}
//		Arrays.sort(nomesPizzas);
//		List<String> nomesOrdenados = Arrays.asList(nomesPizzas);
		
		
		/**
		 *  Java 8 - Lambda
		 *  lista de objetos pizzas
		 *  mapeia somente o nome
		 *  (Collection de nomes de pizzas)
		 */
//		List<String> nomesPizzas = pizzas.stream().map((pizza)->{
//			return pizza.getNome();
//		}).sorted().collect(Collectors.toList());
		/**
		 *  sorted = ordena no proprio metodo de mapiamento
		 *  como ele retorna uma stream de string
		 *  collect = convertentedo na lista de String
		 */
		
		return nomesPizzas;
	}
}
