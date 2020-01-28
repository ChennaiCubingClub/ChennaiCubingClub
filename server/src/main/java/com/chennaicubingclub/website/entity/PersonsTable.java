package com.chennaicubingclub.website.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Persons")
public class PersonsTable {

	@Id
	@Column(name="id")
	public String wcaId;
	
	@Column(name="name")
	public String name;
}
