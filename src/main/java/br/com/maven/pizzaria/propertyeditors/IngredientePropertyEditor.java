package br.com.maven.pizzaria.propertyeditors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.maven.pizzaria.modelo.entidades.Ingrediente;
import br.com.maven.pizzaria.modelo.repositorios.IngredienteRepositorio;

@Component
public class IngredientePropertyEditor extends PropertyEditorSupport {

	@Autowired private IngredienteRepositorio ingredienteRepositorio;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		long idIngrediente = Long.parseLong(text);
		Ingrediente ingrediente = ingredienteRepositorio.findOne(idIngrediente);
		// vai converter o id e setar o valor que preciso
		setValue(ingrediente);
	}
}
