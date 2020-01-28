package com.chennaicubingclub.website.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.chennaicubingclub.website.entity.C3UsersTable;

public interface C3UsersRepo extends CrudRepository<C3UsersTable,Long> {

	@Query("select user from C3UsersTable user where username = :username and password = :password")
	List<C3UsersTable> getUsers(@Param("username") String username, @Param("password") String password);
}
