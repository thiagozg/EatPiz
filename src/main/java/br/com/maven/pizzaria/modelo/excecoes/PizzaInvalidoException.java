package br.com.maven.pizzaria.modelo.excecoes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class PizzaInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 5700164220854606844L;

}
