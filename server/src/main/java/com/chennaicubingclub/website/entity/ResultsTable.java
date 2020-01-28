package com.chennaicubingclub.website.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity @IdClass(ResultsTableId.class)
@Table(name="Results")
public class ResultsTable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6573503439197053470L;

	@Id
	@Column(name="competitionId")
	public String competitionId;
	
	@Id
	@Column(name="eventId")
	public String eventId;
	
	@Id
	@Column(name="personId")
	public String personId;
	
	@Column(name="personName")
	public String personName;
	
	@Id
	@Column(name="pos")
	public Integer pos;
}
