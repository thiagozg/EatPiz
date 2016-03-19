package br.com.maven.pizzaria.configuracoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import br.com.maven.pizzaria.modelo.servicos.ServicoAutenticacao;

@Configuration
@EnableWebSecurity
public class ConfiguracaoSeguranca extends WebSecurityConfigurerAdapter {
	
	@Autowired private ServicoAutenticacao servicoAutenticacao;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// quando um usuario for admin e senha admin, 
		// ele poderá ter todas as regras de uma "PIZZARIA"
//		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("PIZZARIA");
		
		// utiliza um objeto customizado
		auth
			.userDetailsService(servicoAutenticacao)
			.passwordEncoder(encoder());
	}
	

	// verificar se o usuario esta autenticado, caso não esteja o browser vai 
	// criar um pop-up requisitando um usuario e senha para o servidor
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().csrfTokenRepository(csrfTokenRepository());
		// todos os padrões de url q ele deve filtrar
		// necessita ter essa ordem, está escrito:
		// qualquer requisição poderá ser acessada, porém pizzas e ingredientes não!
		// se fosse na ordem ao contrária ele sobrescreveria a regra
		http
			.authorizeRequests()
				.antMatchers("/pizzarias/pizzas/**", "/pizzarias/ingredientes/**", "/pizzarias/home", "/pizzarias/editar", "/pizzarias/contatos").hasRole("PIZZARIA")
				.anyRequest().permitAll()
		.and()
			.formLogin()
				.loginPage("/pizzarias/login")
				.loginProcessingUrl("/autenticar")
				.defaultSuccessUrl("/pizzarias/home")
				.failureUrl("/pizzarias/login?semacesso=true")
				.usernameParameter("usuario")
				.passwordParameter("senha")
		.and()
			.logout()
				.logoutUrl("/sair")
				.logoutSuccessUrl("/pizzarias/login?saiu=true");
		
		
//		.and().httpBasic(); sem autenticação
//		.and().csrf().disable(); caso nao tivesse tantas tela (ex.: fosse REST) seria interessante utilizar
	}
	
	private CsrfTokenRepository csrfTokenRepository() { 
	    HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository(); 
	    repository.setSessionAttributeName("_csrf");
	    return repository; 
	}
	
	// @Bean = bean do spring
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	// gerando o encode da senha
//	public static void main(String[] args) {
//		BCryptPasswordEncoder e = new BCryptPasswordEncoder();
//		System.out.println(e.encode("1"));
//	}
	
}
