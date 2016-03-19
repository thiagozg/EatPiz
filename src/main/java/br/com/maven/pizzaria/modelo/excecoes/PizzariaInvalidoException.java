package br.com.maven.pizzaria.modelo.excecoes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class PizzariaInvalidoException extends RuntimeException {
	
	private static final long serialVersionUID = 6607424547925603197L;

}
