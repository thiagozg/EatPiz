package br.com.maven.pizzaria.configuracoes;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * @EnableWebMvc = habilita aplicação web
 * @Configuration = classe de configuração
 * @ComponentScan = diz para o spring quais pacotes ele precisa scanear
					para procurar classes que sejam objetos configurados por ele
 * @author thiagozg
 *
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages="br.com.maven.pizzaria")
public class ConfiguracaoWeb extends WebMvcConfigurerAdapter {

	/**
	 *  objeto pega as strings do controlador e transforma elas 
	 *  em uma view para ser rendereziado
	 *  
	 *  Ex.: iria acessar /app/ingredientes -> /WEB-INF/"STRING".jsp
	 *  fica: /WEB-INF/ingredientes.jsp
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// procura as view baseada na url
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setPrefix("/WEB-INF/");
		viewResolver.setSuffix(".jsp");
		// para não precisar escrever scriplet, se utiliza essa biblioteca
		viewResolver.setViewClass(JstlView.class);
		registry.viewResolver(viewResolver);
	}	
	
	/**
	 * INTERNACIONALIZAÇÃO:
	 * Intercepta a requisição, verifica o locale do usuario
	 * e retorna a linguagem correta
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
		// parametro para defirnir a nossa linguagem
		localeInterceptor.setParamName("lang");
		registry.addInterceptor(localeInterceptor);
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		// para saber qual a linguagem q ele está utilizando
		// vamos salvar "lang" na sessão
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(new Locale("pt_BR"));
		return resolver;
	}
	
	// em qual lugar procurar o arquivo com as linguagens
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/I18n/messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
	
	// registra controllers que não precisam receber informações
	// apenas redirecionam para view
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// primeira view que o usuario irá acessar
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/clientes").setViewName("cliente/consulta");
		registry.addViewController("/pizzarias/login").setViewName("pizzaria/login");
	}
	
	// configurando o spring para entender tudo que for "static" ele não deve controlar
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}
}
