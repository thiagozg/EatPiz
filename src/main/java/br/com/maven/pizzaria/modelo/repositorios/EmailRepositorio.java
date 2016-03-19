package br.com.maven.pizzaria.modelo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.maven.pizzaria.modelo.entidades.Email;
import br.com.maven.pizzaria.modelo.entidades.Pizzaria;

@Repository
public interface EmailRepositorio extends CrudRepository<Email, Long> {

	@Query("SELECT e FROM Email e WHERE e.dono = :p")
	public List<Email> findByDono(@Param("p") Pizzaria p);
	
	public Email findByIdAndDono(Long id, Pizzaria dono);
	
}
