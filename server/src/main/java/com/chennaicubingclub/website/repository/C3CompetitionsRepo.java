package com.chennaicubingclub.website.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.chennaicubingclub.website.entity.C3CompetitionsTable;

public interface C3CompetitionsRepo extends CrudRepository<C3CompetitionsTable,Long> {
	
	@Query("select c3comp from C3CompetitionsTable c3comp where c3cup = true")
	List<C3CompetitionsTable> getC3Competitions();
	
	@Query("select c3comp from C3CompetitionsTable c3comp where id = :id")
	C3CompetitionsTable getCompetition(@Param("id") String id);
}