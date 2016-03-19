package br.com.maven.pizzaria.modelo.entidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Pizzaria implements UserDetails {

	private static final long serialVersionUID = -8086622907346635607L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	/**
	 *  @ElementCollection = criar outra tabela para armazenar os emails (e telefones)
	 * 
	 *  @Embedded = todos atributos da classe usuario
	 *  serão colunas que essa classe irá gerar
	 */
	@Embedded
	@NotNull
	private Usuario usuario;
	
	@NotNull
	private Calendar dataCadastro;
	
	@NotNull
	@NotEmpty
	private String nome;

	@NotNull
	@NotEmpty
	private String endereco;
	
	@OneToMany(mappedBy = "dono")
	private Set<Email> emails;
	
	@OneToMany(mappedBy = "dono")
	private Set<Telefone> telefones;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Permissao> permissoes;
	
	@OneToMany(mappedBy = "dono")
	private Set<Pizza> pizzas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getDataCadastro() {
		String dataFormatada = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			dataFormatada = sdf.format(dataCadastro.getTime());
			dataFormatada = dataFormatada.replace('-', '/');
		} catch (Exception e) {
			System.out.println("getDataCadastro, erro = " + e);
		}
		
		return dataFormatada;
	}
	
	public Calendar getDataCadastroCalendario() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) throws ParseException {
		dataCadastro = dataCadastro.replace('/', '-');
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(dataCadastro));
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String aux = sdf.format(c.getTime());
			c.setTime(sdf.parse(aux));
		} catch (Exception e) {
			System.out.println("setDataCadastro, erro = " + e);
		}
		this.dataCadastro = c;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Set<Email> getEmails() {
		return emails;
	}

	public void setEmails(Set<Email> email) {
		this.emails = email;
	}

	public Set<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<Telefone> telefones) {
		this.telefones = telefones;
	}

	public Set<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(Set<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	public Set<Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(Set<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	@Override
	public String getPassword() {
		return usuario.getSenha();
	}

	@Override
	public String getUsername() {
		return usuario.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pizzaria other = (Pizzaria) obj;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> autorizacoes = new ArrayList<GrantedAuthority>();
		
		// lista de autorizações aqui dentro
		for(Permissao permissao : getPermissoes()) {
			autorizacoes.add(new SimpleGrantedAuthority(permissao.getNome()));
		}
		
		return autorizacoes;
	}

}
