package br.com.maven.pizzaria;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Aplicacao implements WebApplicationInitializer {

	// ServletContext = servlet inicial da aplicação
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// Contexto que vai gerenciar os objetos do projeto
		AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
		webApplicationContext.setConfigLocation("br.com.maven.pizzaria.configuracoes");
		
		// DispatcherServlet = frontcontroller do sprint
		// caso não passe o "applicationContext" o Dispatcher não vai conhecer nenhuma classe da minha spring
		Dynamic appServlet = servletContext.addServlet("appServlet", new DispatcherServlet(webApplicationContext));
		appServlet.setLoadOnStartup(1);
		// quais urls ele irá ouvir
		// tudo oq for dentro de /app do Dispatche vai intercpetar e procurar um controllador para trabalhar com ela
		appServlet.addMapping("/");
		/**
		 * quando o acesso fica direto não se deve colocar o *
		 * como era antes
		 * appServlet.addMapping("/app/*");
		 */
		
		// toda vez que ele verifica que deve criar um controllador novo, ele cria a instancia automatico
		servletContext.addListener(new ContextLoaderListener(webApplicationContext));
		
		// filtro para o relacionamento de pizza -> ingrediente
		FilterRegistration.Dynamic filter = servletContext.addFilter("openEntityManagerFilter", buildOpenEntityManagerFilter());
		filter.addMappingForUrlPatterns(getDispatcherTypes(), false, "/*");
	}
	
	private OpenEntityManagerInViewFilter buildOpenEntityManagerFilter() {
		OpenEntityManagerInViewFilter openEntityManagerInViewFilter = new OpenEntityManagerInViewFilter();
		openEntityManagerInViewFilter.setEntityManagerFactoryBeanName("entityManagerFactory");
		return openEntityManagerInViewFilter;
	}
	
	private EnumSet<DispatcherType> getDispatcherTypes() {
		return EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ASYNC);
	}
}
