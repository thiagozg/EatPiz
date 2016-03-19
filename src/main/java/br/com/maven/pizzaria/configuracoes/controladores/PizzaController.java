package br.com.maven.pizzaria.configuracoes.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.maven.pizzaria.modelo.entidades.Ingrediente;
import br.com.maven.pizzaria.modelo.entidades.Pizza;
import br.com.maven.pizzaria.modelo.enumeracoes.CategoriaDePizza;
import br.com.maven.pizzaria.modelo.excecoes.PizzaInvalidoException;
import br.com.maven.pizzaria.modelo.servicos.ServicoIngrediente;
import br.com.maven.pizzaria.modelo.servicos.ServicoPizza;
import br.com.maven.pizzaria.propertyeditors.IngredientePropertyEditor;

@Controller
// padrãod e url q esse controller via funcionar
@RequestMapping("/pizzarias/pizzas")
public class PizzaController {

	// passando parametro "nome" pela url
	// @PathVariable = recebendo o nome
	// @ResponseBody = pega o objeto de resposta e escreve para o usuario (usar qdo utilizar json)
	//	@RequestMapping("/ola/{nome}")
	//	@ResponseBody
	//	public String ola(@PathVariable String nome) {
	//		return "Olá, " + nome; 
	//	}

	// irá dizer para o controller que ele precisa criar um objeto do tipo PizzaRepositorio
	@Autowired private ServicoPizza servicoPizza;
	@Autowired private ServicoIngrediente servicoIngrediente;
	
	@Autowired private IngredientePropertyEditor ingredientePropertyEditor;
	
	@RequestMapping(method=RequestMethod.GET)
	public String listarPizzas(Model model) {
		model.addAttribute("titulo", "Listagem de Pizzas");
		model.addAttribute("pizzas", servicoPizza.listar());
		model.addAttribute("categorias", CategoriaDePizza.values());
		model.addAttribute("ingredientes", servicoIngrediente.listar());
		model.addAttribute("page", "listagem");
		
		return "pizzaria/pizza/listagem";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String salvarPizza(
			Model model,
			@Valid @ModelAttribute Pizza pizza,
			BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			throw new PizzaInvalidoException();
		} else {
			servicoPizza.salvar(pizza);
		}
		
		model.addAttribute("pizzas", servicoPizza.listar());
		model.addAttribute("page", "listagem");
		
		return "pizzaria/pizza/tabela-pizzas";
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{pizzaId}")
	public ResponseEntity<String> deletarPizza(@PathVariable Long pizzaId) {
		try {
			servicoPizza.remover(pizzaId);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// @ResponseBody = metódos que retornam JSON ou XML
	@RequestMapping(method=RequestMethod.GET, value="/{pizzaId}")
	@ResponseBody
	public ResponseEntity<Pizza> buscarPizza(@PathVariable Long pizzaId) {
		Pizza pizza = servicoPizza.buscar(pizzaId);
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	
//	@RequestMapping("/quantas")
//	@ResponseBody
//	public String quantasPizzas() {
//		return "Atualmente há " +pizzaRepositorio.count()+ " cadastradas!";
//	}
	
	// quando houver a inserção de um ingrediente, ele irá utilizar esse ingredientePropertyEditor
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Ingrediente.class, ingredientePropertyEditor);
	}
}
