package com.chennaicubingclub.website.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.chennaicubingclub.website.entity.PersonsTable;

public interface PersonsRepo extends CrudRepository<PersonsTable,Long> {
	
	@Query("select p from PersonsTable p where name=:name")
	List<PersonsTable> findPersonByName(@Param("name") String name);

}
