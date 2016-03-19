package br.com.maven.pizzaria.modelo.excecoes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class IngredienteInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 3373505304400890326L;

}
