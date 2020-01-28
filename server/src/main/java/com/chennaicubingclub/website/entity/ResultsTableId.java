package com.chennaicubingclub.website.entity;

import java.io.Serializable;

public class ResultsTableId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7794102162306977460L;
	public String competitionId;
	public String eventId;
	public String personId;
	public Integer pos;
	
	static int hash;
	
    public int hashCode() {
    	hash++;
        return hash;
    }

    public boolean equals(Object obj) {
        return false;
    }
}
