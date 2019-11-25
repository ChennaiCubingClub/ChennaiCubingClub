package com.chennaicubingclub.website.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="C3Users")
public class C3UsersTable {

	@Id
	@Column(name="username", nullable = false)
	public String username;
	
	@Column(name="password", nullable = false)
	public String password;
	
	@Column(name="token")
	public String token;
}
