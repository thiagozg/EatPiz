package br.com.maven.pizzaria.configuracoes.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.maven.pizzaria.modelo.entidades.Ingrediente;
import br.com.maven.pizzaria.modelo.enumeracoes.CategoriaDeIngrediente;
import br.com.maven.pizzaria.modelo.excecoes.IngredienteInvalidoException;
import br.com.maven.pizzaria.modelo.servicos.ServicoIngrediente;

// /app/ingredientes (metodo GET) -> listarIngredientes
// /app/ingredientes (metodo POST) -> salvarIngrediente
@Controller
@RequestMapping("/pizzarias/ingredientes")
public class IngredienteController {
	
	@Autowired private ServicoIngrediente servicoIngrediente;

	//	/WEB-INF/ingrediente/listagem.jsp
	@RequestMapping(method=RequestMethod.GET)
	public String listarIngredientes(Model model) {
		Iterable<Ingrediente> ingredientes = servicoIngrediente.listar();
		
		model.addAttribute("titulo", "Listagem de Ingredientes");
		model.addAttribute("ingredientes", ingredientes);
		model.addAttribute("categorias", CategoriaDeIngrediente.values());
		model.addAttribute("page", "listagem");
		
		return "pizzaria/ingrediente/listagem";
	}
	
	/**
	 *  @Valid = verifica se o objeto esta correto, caso não chama o bindingResult
	 *  @ModelAttribute = mapeia os atributos de um formulario para um objeto
	 *  RedirectAttributes = irá exibir a mensagem do atributo salvo 
	 */
	@RequestMapping(method=RequestMethod.POST)
	public String salvarIngrediente(
			@Valid @ModelAttribute Ingrediente ingrediente, 
			BindingResult bindingResult,
			Model model) {
		
		if(bindingResult.hasErrors()) {
			throw new IngredienteInvalidoException();
		} else {
			servicoIngrediente.salvar(ingrediente);
		}
		
		// para não precisar repetir o codigo do listarIngredientes
		// irá usar o redirect para o metodo (do POST -> GET)
		// return "redirect:/app/ingredientes";
		model.addAttribute("ingredientes", servicoIngrediente.listar());
		model.addAttribute("categorias", CategoriaDeIngrediente.values());
		model.addAttribute("page", "listagem");
		
		return "pizzaria/ingrediente/tabela-ingredientes";
	}
	
	/* sem o ingredientes.js
	   public String salvarIngrediente(
			@Valid @ModelAttribute Ingrediente ingrediente, 
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			// add o objeto na seção, apos o usuario ler, ele é apagado
			FieldError error = bindingResult.getFieldErrors().get(0);
			redirectAttributes.addFlashAttribute("mensagemErro", "Não foi possível salvar o Ingrediente. "
					+error.getField()+" "+error.getDefaultMessage());
		} else {
			ingredienteRepositorio.save(ingrediente);
			redirectAttributes.addFlashAttribute("mensagemInfo", "O ingrediente foi salvo corretamente.");
		}
		
		// para não precisar repetir o codigo do listarIngredientes
		// irá usar o redirect para o metodo (do POST -> GET)
		return "redirect:/app/ingredientes";
	}
	 */
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<String> deletarIngrediente(@PathVariable Long id) {
		try {
			servicoIngrediente.remover(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// @ResponseBody = metódos que retornam JSON ou XML
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	@ResponseBody
	public Ingrediente buscarIndregiente(@PathVariable Long id) {
		Ingrediente ingrediente = servicoIngrediente.buscar(id);
		return ingrediente;
	}
}
