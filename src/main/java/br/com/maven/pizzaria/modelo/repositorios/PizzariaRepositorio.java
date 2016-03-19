package br.com.maven.pizzaria.modelo.repositorios;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.maven.pizzaria.modelo.entidades.Pizzaria;

@Repository
public interface PizzariaRepositorio extends CrudRepository<Pizzaria, Long>, JpaRepository<Pizzaria, Long> {

	@Query("SELECT p FROM Pizzaria p WHERE p.usuario.login = ?")
	public Pizzaria findOneByLogin(String login);
	
	// @Query = comando direto para o banco
	// ? = parametro vindo do metódo
	@Query("SELECT p1 FROM Pizzaria p1 INNER JOIN p1.pizzas p2 WHERE p2.nome = ?")
	public List<Pizzaria> listarPizzariasPorNomePizza(String nomePizza);
	
	@Modifying
	@Query(value = "INSERT INTO Pizzaria_Permissao (Pizzaria_id, permissoes_id) VALUES (?, 1)", nativeQuery = true)
	public void addPermissao(Long idPizzaria);
	
	@Modifying
	@Query(value = "UPDATE Pizzaria SET dataCadastro = ?, endereco = ?, nome = ?, login = ?, senha = ? WHERE id = ?", nativeQuery = true)
	public void editarDados(Calendar datac, String end, String nome, String login, String senha, Long id);
	
}
