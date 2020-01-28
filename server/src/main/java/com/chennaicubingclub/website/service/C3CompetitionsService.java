package com.chennaicubingclub.website.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chennaicubingclub.website.entity.C3CompetitionsTable;
import com.chennaicubingclub.website.repository.C3CompetitionsRepo;

@Service("C3Competitions")
public class C3CompetitionsService implements ServiceInterface {
	
	@Autowired
	C3CompetitionsRepo compRepo;

	@Override
	public void action(String[] components, HttpServletRequest request, HttpServletResponse response) {
		switch (request.getMethod()) {
			case "POST":
				if (components[0].equals("addCompetitionToC3")) {
					addCompetitionToC3(request, response);
				} else if (components[0].equals("addCompetitionToC3Cup")) {
					addCompetitionToC3Cup(request, response);
				} else {
					response.setStatus(404);
				}
				break;
			default:
				response.setStatus(404);
		}
	}
	
	// I/P: competitionId
	// O/P: success
	void addCompetitionToC3(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> responseMap = new HashMap<String, String>();
		if (request.getParameterMap().containsKey("competitionId")) {
			C3CompetitionsTable newComp = new C3CompetitionsTable();
			newComp.id = request.getParameter("competitionId");
			newComp.c3cup = false;
			compRepo.save(newComp);
			responseMap.put("success", "true");
			response.setStatus(200);
		} else {
			responseMap.put("success", "false");
			response.setStatus(503);
		}
		response.setContentType("application/json");
    	response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().print(new JSONObject(responseMap));
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
			response.setStatus(503);
		}
	}
	
	// I/P: competitionId
	// O/P: success
	void addCompetitionToC3Cup(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> responseMap = new HashMap<String, String>();
		if (request.getParameterMap().containsKey("competitionId")) {
			C3CompetitionsTable comp = compRepo.getCompetition(request.getParameter("competitionId"));
			comp.c3cup = true;
			compRepo.save(comp);
			responseMap.put("success", "true");
			response.setStatus(200);
		} else {
			responseMap.put("success", "false");
			response.setStatus(503);
		}
		response.setContentType("application/json");
    	response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().print(new JSONObject(responseMap));
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
			response.setStatus(503);
		}
	}
}
