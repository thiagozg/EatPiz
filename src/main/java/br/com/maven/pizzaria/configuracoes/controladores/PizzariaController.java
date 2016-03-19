package br.com.maven.pizzaria.configuracoes.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.maven.pizzaria.modelo.entidades.Pizzaria;
import br.com.maven.pizzaria.modelo.servicos.ServicoPizzaria;

@Controller
@RequestMapping("/pizzarias")
public class PizzariaController {
	
	@Autowired private ServicoPizzaria servicoPizzaria;
	@Autowired private IngredienteController ic;
	@Autowired private PizzaController pc;
	
	@RequestMapping(method = RequestMethod.GET, value="/cadastrar")
    public String showFormCadastrar(Model model) {
		model.addAttribute("pizzaria", new Pizzaria());
		return "pizzaria/cadastrar";
    }
	
	@RequestMapping(method = RequestMethod.POST, value="/cadastrar")
	public String salvarPizzaria(
					@Valid @ModelAttribute Pizzaria pizzaria, 
					BindingResult bindingResult,
					RedirectAttributes redirectAttributes) {
		
		if(pizzaria.getUsuario().getSenha().equals(pizzaria.getUsuario().getConfirmacaoSenha())) {
			if(bindingResult.hasErrors()) {
				redirectAttributes.addFlashAttribute("mensagemErro", "mensagemErro");
			} else {
				BCryptPasswordEncoder e = new BCryptPasswordEncoder();
				pizzaria.getUsuario().setSenha(e.encode(pizzaria.getUsuario().getSenha()));
				servicoPizzaria.salvar(pizzaria);
				redirectAttributes.addFlashAttribute("mensagemInfo", "mensagemInfo");
				return  "redirect:/pizzarias/login";
			}
		} else {
			redirectAttributes.addFlashAttribute("mensagemSenha", "mensagemSenha");
		}

		return "redirect:/pizzarias/cadastrar";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/home")
	public String homePizzaria(Model model) {
		ic.listarIngredientes(model);
		pc.listarPizzas(model);
		model.addAttribute("pizzaria", servicoPizzaria.getPizzariaLogada());
		model.addAttribute("page", "home");
		return "pizzaria/home";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/editar")
    public String showFormEditar(Model model) {
		model.addAttribute("pizzaria", servicoPizzaria.getPizzariaLogada());
		return "pizzaria/editar";
    }
	
	@RequestMapping(method = RequestMethod.POST, value="/editar")
	public String editarPizzaria(
				@Valid @ModelAttribute Pizzaria pizzaria, 
				BindingResult bindingResult,
				RedirectAttributes redirectAttributes) {
		
		if(pizzaria.getUsuario().getSenha().equals(pizzaria.getUsuario().getConfirmacaoSenha())) {
			if(bindingResult.hasErrors()) {
				redirectAttributes.addFlashAttribute("mensagemErro", "mensagemErro");
			} else {
				BCryptPasswordEncoder e = new BCryptPasswordEncoder();
				pizzaria.getUsuario().setSenha(e.encode(pizzaria.getUsuario().getSenha()));
				servicoPizzaria.editar(pizzaria);
				redirectAttributes.addFlashAttribute("mensagemInfo", "mensagemInfo");
			}
		} else {
			redirectAttributes.addFlashAttribute("mensagemSenha", "mensagemSenha");
		}

		return "redirect:/pizzarias/editar";
	}
	
}