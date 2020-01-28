package com.chennaicubingclub.website.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chennaicubingclub.website.entity.PersonsTable;
import com.chennaicubingclub.website.repository.PersonsRepo;

@Service("One")
public class PersonServiceImplementation {
	
	@Autowired
	PersonsRepo repo;
	
	public String findUserByName(String name) {
		String result="Service One\n";
		List<PersonsTable> list = repo.findPersonByName(name);
		for(PersonsTable p: list)
		{
			result+=p.wcaId;
		}
		return result;
	}

}
