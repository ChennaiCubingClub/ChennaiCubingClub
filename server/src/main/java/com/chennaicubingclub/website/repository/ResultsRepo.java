package com.chennaicubingclub.website.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.chennaicubingclub.website.entity.ResultsTable;

public interface ResultsRepo extends CrudRepository<ResultsTable,Long> {
	
	@Query("select distinct eventId from ResultsTable where competitionId = :competitionId")
	List<String> getEventList(@Param("competitionId") String competitionId);
	
	@Query("select count(distinct personId) from ResultsTable where competitionId = :competitionId and eventId = :eventId")
	Long getCompetitorCount(@Param("competitionId") String competitionId, @Param("eventId") String eventId);
	
	@Query("select results from ResultsTable results where competitionId = :competitionId and eventId = :eventId and (roundTypeId = 'c' or roundTypeId = 'f') and pos <= 10 and (value1 > 0 or value2 > 0 or value3 > 0 or value4 > 0 or value5 > 0)")
	List<ResultsTable> getWinnersList(@Param("competitionId") String competitionId, @Param("eventId") String eventId);

}