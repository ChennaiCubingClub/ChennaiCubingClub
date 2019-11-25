package com.chennaicubingclub.website.data;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="C3CupPoints")
public class C3CupPointsTable implements Serializable {

	@Id
	@Column(name="wca_id", nullable = false)
	public String wcaId;
	
	@Id
	@Column(name="competition_id", nullable = false)
	public String competitionId;

	@Column(name="points")
	public BigDecimal points;
}
