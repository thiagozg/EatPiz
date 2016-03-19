package br.com.maven.pizzaria.modelo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.maven.pizzaria.modelo.entidades.Pizzaria;
import br.com.maven.pizzaria.modelo.entidades.Telefone;

@Repository
public interface TelefoneRepositorio extends CrudRepository<Telefone, Long> {

	@Query("SELECT t FROM Telefone t WHERE t.dono = :p")
	public List<Telefone> findByDono(@Param("p") Pizzaria p);
	
	public Telefone findByIdAndDono(Long id, Pizzaria dono);
	
}
