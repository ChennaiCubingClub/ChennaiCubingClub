package com.chennaicubingclub.website.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.chennaicubingclub.website.entity.C3CupPointsTable;

public interface C3CupRepo extends CrudRepository<C3CupPointsTable,Long> {

	@Query("select c3cup from C3CupPointsTable c3cup where competitionId = :competitionId")
	List<C3CupPointsTable> getPointList(@Param("competitionId") String competitionId);
	
	@Query("select c3cup from C3CupPointsTable c3cup where competitionId = :competitionId order by points desc")
	List<C3CupPointsTable> getPointListSorted(@Param("competitionId") String competitionId);
}
