package com.chennaicubingclub.website.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="C3Competitions")
public class C3CompetitionsTable {

	@Id
	@Column(name="id", nullable = false)
	public String id;
	
	@Column(name="c3cup", nullable = false)
	public Boolean c3cup;
}
