package com.chennaicubingclub.website.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="C3CupPoints")
public class C3CupPointsTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	public Integer id;
	
	@Column(name="wca_id", nullable = false)
	public String wcaId;
	
	@Column(name="competition_id", nullable = false)
	public String competitionId;
	
	@Column(name="competition_name", nullable = false)
	public String competitionName;
	
	@Column(name="person_name", nullable = false)
	public String personName;
	
	@Column(name="year", nullable = false)
	public Integer year;
	
	@Column(name="month", nullable = false)
	public Integer month;
	
	@Column(name="day", nullable = false)
	public Integer day;

	@Column(name="points")
	public BigDecimal points;
}
