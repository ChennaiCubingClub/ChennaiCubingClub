package com.chennaicubingclub.website.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Competitions")
public class CompetitionsTable {

	@Id
	@Column(name="id", nullable = false)
	public String id;
	
	@Column(name="name")
	public String name;
	
	@Column(name="day")
	public Integer day;
	
	@Column(name="month")
	public Integer month;
	
	@Column(name="year")
	public Integer year;
}
