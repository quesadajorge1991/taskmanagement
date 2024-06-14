package taskmanagement.taskmanagement.entity;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

/**
 * The persistent class for the authorities database table.
 * 
 */
@Entity
@Table(name = "authorities")
@NamedQuery(name = "Authority.findAll", query = "SELECT a FROM Authority a")
public class Authority implements Serializable,GrantedAuthority{
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String authority;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;

	public Authority() {
	}

	public Authority(User username) {
		this.user = username;
	}

	public Authority(String authority, User username) {
		this.authority = authority;
		this.user = username;
	}

	public Authority(Integer id) {
		this.id = id;
	}

	public Authority(Integer id, String authority) {
		this.id = id;
		this.authority = authority;
	}

	public Authority(String authority) {
		this.authority = authority;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Authority(Integer id, User username) {
		this.id = id;
		this.user = username;
	}

	public Authority(Integer id, String authority, User username) {
		this.id = id;
		this.authority = authority;
		this.user = username;
	}
}