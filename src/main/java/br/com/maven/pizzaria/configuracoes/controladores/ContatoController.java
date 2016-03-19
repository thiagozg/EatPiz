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

import br.com.maven.pizzaria.modelo.entidades.Email;
import br.com.maven.pizzaria.modelo.entidades.Telefone;
import br.com.maven.pizzaria.modelo.excecoes.PizzariaInvalidoException;
import br.com.maven.pizzaria.modelo.servicos.ServicoEmail;
import br.com.maven.pizzaria.modelo.servicos.ServicoTelefone;

@Controller
@RequestMapping("/pizzarias/contatos")
public class ContatoController {
	
	@Autowired private ServicoTelefone servicoTelefone;
	@Autowired private ServicoEmail servicoEmail;
	
	/**
	 * CONTATOS (listagem.jsp)
	 */
	@RequestMapping(method = RequestMethod.GET)
    public String listarContatos(Model model) {
		model.addAttribute("telefones", servicoTelefone.listar());
		model.addAttribute("emails", servicoEmail.listar());
		return "pizzaria/contato/listagem";
    }
	
	/**
	 * CONTATOS -> TELEFONE
	 */
	@RequestMapping(method=RequestMethod.POST, value="/telefone")
	public String salvarTelefone(
			@Valid @ModelAttribute Telefone telefone,
			BindingResult bindingResult,
			Model model) {

		if(bindingResult.hasErrors()) {
			throw new PizzariaInvalidoException();
		} else {
			servicoTelefone.salvar(telefone);
		}
		
		model.addAttribute("telefones", servicoTelefone.listar());
		
		return "pizzaria/contato/tabela-telefones";
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/telefone/{id}")
	public ResponseEntity<String> deletarTelefone(@PathVariable Long id) {
		try {
			servicoTelefone.remover(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/telefone/{id}")
	@ResponseBody
	public Telefone buscarTelefone(@PathVariable Long id) {
		Telefone telefone = servicoTelefone.buscar(id);
		return telefone;
	}

	/**
	 * CONTATOS -> EMAIL
	 */
	@RequestMapping(method=RequestMethod.POST, value="/email")
	public String salvarEmail(
			@Valid @ModelAttribute Email email,
			BindingResult bindingResult,
			Model model) {

		if(bindingResult.hasErrors()) {
			throw new PizzariaInvalidoException();
		} else {
			servicoEmail.salvar(email);
		}
		
		model.addAttribute("emails", servicoEmail.listar());
		
		return "pizzaria/contato/tabela-emails";
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/email/{id}")
	public ResponseEntity<String> deletarEmail(@PathVariable Long id) {
		try {
			servicoEmail.remover(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/email/{id}")
	@ResponseBody
	public Email buscarEmail(@PathVariable Long id) {
		Email email = servicoEmail.buscar(id);
		return email;
	}
	
}
