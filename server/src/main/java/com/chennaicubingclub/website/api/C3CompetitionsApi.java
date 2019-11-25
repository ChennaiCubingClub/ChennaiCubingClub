package com.chennaicubingclub.website.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.json.JSONObject;

import com.chennaicubingclub.website.HibernateUtil;
import com.chennaicubingclub.website.data.C3CompetitionsTable;

public class C3CompetitionsApi implements ControllerApi {

	@Override
	public void controller(String[] components, HttpServletRequest request, HttpServletResponse response) {
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
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		if (request.getParameterMap().containsKey("competitionId")) {
			C3CompetitionsTable newComp = new C3CompetitionsTable();
			newComp.id = request.getParameter("competitionId");
			newComp.c3cup = false;
			session.save(newComp);
			session.getTransaction().commit();
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
		session.close();
	}
	
	// I/P: competitionId
	// O/P: success
	void addCompetitionToC3Cup(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> responseMap = new HashMap<String, String>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		if (request.getParameterMap().containsKey("competitionId")) {
			Query query = session.createQuery("from C3CompetitionsTable where id = :competitionId");
			query.setString("competitionId", request.getParameter("competitionId"));
			C3CompetitionsTable comp = (C3CompetitionsTable)query.uniqueResult();
			comp.c3cup = true;
			session.save(comp);
			session.getTransaction().commit();
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
		session.close();
	}
	
}
