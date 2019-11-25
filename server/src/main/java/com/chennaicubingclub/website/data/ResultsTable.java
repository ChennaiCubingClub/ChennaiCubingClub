package com.chennaicubingclub.website.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Results")
public class ResultsTable implements Serializable {

	@Id
	@Column(name="competitionId")
	public String competitionId;
	
	@Id
	@Column(name="eventId")
	public String eventId;
	
	@Id
	@Column(name="personId")
	public String personId;
	
	@Id
	@Column(name="pos")
	public Integer pos;
}
