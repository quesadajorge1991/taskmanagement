package taskmanagement.taskmanagement.entity;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The primary key class for the authorities database table.
 * 
 */
@Embeddable
public class AuthorityPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private long id;

	private String authority;

	public AuthorityPK() {
	}
	public long getId() {
		return this.id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAuthority() {
		return this.authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AuthorityPK)) {
			return false;
		}
		AuthorityPK castOther = (AuthorityPK)other;
		return 
			(this.id == castOther.id)
			&& this.authority.equals(castOther.authority);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.id ^ (this.id >>> 32)));
		hash = hash * prime + this.authority.hashCode();
		
		return hash;
	}
}