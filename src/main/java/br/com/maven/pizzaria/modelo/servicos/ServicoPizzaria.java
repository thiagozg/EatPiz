package br.com.maven.pizzaria.modelo.servicos;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.maven.pizzaria.modelo.entidades.Pizzaria;
import br.com.maven.pizzaria.modelo.repositorios.PizzariaRepositorio;

@Service
@Transactional
public class ServicoPizzaria {

	@Autowired private PizzariaRepositorio pizzariaRepositorio;
	
	public Pizzaria getPizzariaLogada() {
		// obtem o obj principal autenticado
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		if (autenticado == null) throw new AuthenticationCredentialsNotFoundException("Usuário não logado");
		
		// retorna o obj do servicoautenticacao (que no caso é Pizzaria)
		// utilizar o UserDetails, pq é o tipo de obj q o spring conhece
		// e a Pizzaria implementa o UserDetails
		UserDetails usuarioLogado = (UserDetails) autenticado.getPrincipal();
		return pizzariaRepositorio.findOneByLogin(usuarioLogado.getUsername());
	}

	public List<Pizzaria> listarPizzariasQueContem(String nomePizza) {
		return pizzariaRepositorio.listarPizzariasPorNomePizza(nomePizza);
	}

	public void salvar(Pizzaria pizzaria) {
		pizzariaRepositorio.saveAndFlush(pizzaria);
		pizzariaRepositorio.addPermissao(pizzaria.getId());
	}
	
	public void editar(Pizzaria pizzaria) {
		Calendar datac = pizzaria.getDataCadastroCalendario(); 
		String end = pizzaria.getEndereco();
		String nome = pizzaria.getNome();
		String login = pizzaria.getUsuario().getLogin(); 
		String senha = pizzaria.getUsuario().getSenha();
		Long id = pizzaria.getId();
		pizzariaRepositorio.editarDados(datac, end, nome, login, senha, id);
	}
	
}
