package com.chennaicubingclub.website.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.chennaicubingclub.website.entity.CompetitionsTable;

public interface CompetitionsRepo extends CrudRepository<CompetitionsTable,Long> {

	@Query("select comp from CompetitionsTable comp where id = :id")
	CompetitionsTable getCompetition(@Param("id") String id);
}