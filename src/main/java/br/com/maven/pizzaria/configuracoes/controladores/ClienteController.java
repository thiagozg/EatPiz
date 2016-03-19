package br.com.maven.pizzaria.configuracoes.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.maven.pizzaria.modelo.entidades.Pizzaria;
import br.com.maven.pizzaria.modelo.servicos.ServicoPizza;
import br.com.maven.pizzaria.modelo.servicos.ServicoPizzaria;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired private ServicoPizza servicoPizza;
	@Autowired private ServicoPizzaria servicoPizzaria;

	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("nomesPizzas", servicoPizza.listarNomePizzasDisponiveis());
		return "cliente/consulta";		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/pizza/{nomePizza}")
	public String index(@PathVariable String nomePizza, Model model) {
		List<Pizzaria> pizzarias = servicoPizzaria.listarPizzariasQueContem(nomePizza);
		model.addAttribute("pizzarias", pizzarias);
		return "cliente/tabela-pizzarias";		
	}
	
}
