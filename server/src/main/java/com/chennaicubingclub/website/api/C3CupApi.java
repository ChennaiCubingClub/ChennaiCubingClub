package com.chennaicubingclub.website.api;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.json.JSONException;
import org.json.JSONObject;

import com.chennaicubingclub.website.HibernateUtil;
import com.chennaicubingclub.website.data.C3CompetitionsTable;
import com.chennaicubingclub.website.data.C3CupPointsTable;
import com.chennaicubingclub.website.data.CompetitionsTable;
import com.chennaicubingclub.website.data.PersonsTable;
import com.chennaicubingclub.website.data.ResultsTable;

public class C3CupApi implements ControllerApi {

	@Override
	public void controller(String[] components, HttpServletRequest request, HttpServletResponse response) {
		switch (request.getMethod()) {
			case "POST":
				if (components[0].equals("generateCompetitionScores")) {
					generateCompetitionScores(request, response);
				} else if (components[0].equals("getTotalScorecard")) {
					getTotalScorecard(request, response);
				} else if (components[0].equals("getIndividualScores")) {
					getIndividualScores(request, response);
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
	void generateCompetitionScores(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> responseMap = new HashMap<String, String>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		if (request.getParameterMap().containsKey("competitionId")) {
			Map<String, BigDecimal> points = new HashMap<String, BigDecimal>();
			String competitionId = request.getParameter("competitionId");
			Query deleteQuery = session.createQuery("delete from C3CupPointsTable where competitionId = :competitionId");
			deleteQuery.setString("competitionId", competitionId);
			deleteQuery.executeUpdate();
			Query eventsQuery = session.createQuery("select distinct eventId from ResultsTable where competitionId = :competitionId");
			eventsQuery.setString("competitionId", competitionId);
			@SuppressWarnings("unchecked")
			List<String[]> eventsList = eventsQuery.list();
			String[] eventsArray = (String[]) eventsList.toArray(new String[eventsList.size()]);
			for (String event: eventsArray) {
				Query competitorCountQuery = session.createQuery("select count(distinct personId) from ResultsTable where competitionId = :competitionId and eventId = :eventId");
				competitorCountQuery.setString("competitionId", competitionId);
				competitorCountQuery.setString("eventId", event);
				Long competitorCount = (Long)competitorCountQuery.uniqueResult();
				BigDecimal x = getX(event, (new BigDecimal(competitorCount)).divide(new BigDecimal(100), 2, RoundingMode.CEILING));
				Query winnersListQuery = session.createQuery("from ResultsTable where competitionId = :competitionId and eventId = :eventId and (roundTypeId = 'c' or roundTypeId = 'f') and pos <= 10 and (value1 > 0 or value2 > 0 or value3 > 0 or value4 > 0 or value5 > 0)");
				winnersListQuery.setString("competitionId", competitionId);
				winnersListQuery.setString("eventId", event);
				@SuppressWarnings("unchecked")
				List<ResultsTable[]> winnersList = winnersListQuery.list();
				ResultsTable[] winnersArray = (ResultsTable[]) winnersList.toArray(new ResultsTable[winnersList.size()]);
				for (ResultsTable winner: winnersArray) {
					if (points.get(winner.personId) == null) {
						points.put(winner.personId, new BigDecimal(0));
					}
					points.put(winner.personId, points.get(winner.personId).add(getPoints(x, winner.pos)));
				}
			}
			for (String key: points.keySet()) {
				C3CupPointsTable newEntry = new C3CupPointsTable();
				newEntry.wcaId = key;
				newEntry.competitionId = competitionId;
				newEntry.points = points.get(key);
				session.save(newEntry);
			}
			try {
				session.getTransaction().commit();
			} catch (HibernateException e) {
				//
			} finally {
				//
			}
			responseMap.put("success", "true");
			response.setStatus(200);
		} else {
			responseMap.put("error", "No email argument");
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
	
	private BigDecimal getX(String eventId, BigDecimal y) {
		if (Arrays.asList("222", "333", "333oh", "pyram", "skewb").contains(eventId)) {
			return y.add(new BigDecimal(1));
		} else if (Arrays.asList("444", "555", "333bf", "minx", "sq1", "333ft", "clock", "333fm").contains(eventId)) {
			return y.add(new BigDecimal(1.2));
		} else if (Arrays.asList("666", "777", "444bf", "555bf", "333mbf").contains(eventId)) {
			return y.add(new BigDecimal(1.4));
		} else {
			return new BigDecimal(0);
		}
	}
	
	private BigDecimal getPoints(BigDecimal x, Integer pos) {
		switch (pos) {
			case 1:
				return x.multiply(new BigDecimal(25));
			case 2:
				return x.multiply(new BigDecimal(18));
			case 3:
				return x.multiply(new BigDecimal(15));
			case 4:
				return x.multiply(new BigDecimal(12));
			case 5:
				return x.multiply(new BigDecimal(10));
			case 6:
				return x.multiply(new BigDecimal(8));
			case 7:
				return x.multiply(new BigDecimal(6));
			case 8:
				return x.multiply(new BigDecimal(4));
			case 9:
				return x.multiply(new BigDecimal(2));
			case 10:
				return x;
			default:
				return new BigDecimal(0);
		}
	}
	
	// I/P: <none>
	// O/P: success
	void getTotalScorecard(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		System.out.println("1");
		Map<String, BigDecimal> points = new HashMap<String, BigDecimal>();
		Query competitionListQuery = session.createQuery("from C3CompetitionsTable where c3cup = true");
		System.out.println("2");
		@SuppressWarnings("unchecked")
		List<C3CompetitionsTable[]> competitionList = competitionListQuery.list();
		C3CompetitionsTable[] competitionsArray = (C3CompetitionsTable[]) competitionList.toArray(new C3CompetitionsTable[competitionList.size()]);
		System.out.println("3");
		for (C3CompetitionsTable competition: competitionsArray) {
			Query c3cupList = session.createQuery("from C3CupPointsTable where competitionId = :competitionId");
			c3cupList.setString("competitionId", competition.id);
			System.out.println("4");
			@SuppressWarnings("unchecked")
			List<C3CupPointsTable[]> competitorList = c3cupList.list();
			C3CupPointsTable[] array = (C3CupPointsTable[]) competitorList.toArray(new C3CupPointsTable[competitorList.size()]);
			for (C3CupPointsTable element: array) {
				if (points.get(element.wcaId) == null) {
					points.put(element.wcaId, new BigDecimal(0));
				}
				points.put(element.wcaId, points.get(element.wcaId).add(element.points));
			}
		}
		System.out.println("5");
		final Integer size = points.keySet().size();
		JSONObject rankList[] = new JSONObject[size];
		int i = 0;
		for (String key: points.keySet()) {
			try {
				rankList[i] = new JSONObject();
				rankList[i].put("name", this.getPersonName(session, key));
				rankList[i].put("points", points.get(key));
				i = i + 1;
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("6");
		// sorting
		for (i = 0; i < rankList.length; i++) {
			int highest = i;
			for (int j = i + 1; j < rankList.length; j++) {
				try {
					if (((BigDecimal)rankList[j].get("points")).compareTo((BigDecimal)rankList[highest].get("points")) == 1) {
						highest = j;
						System.out.println("7");
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			try {
				String tempId = (String)rankList[i].get("name");
				BigDecimal tempPoints = (BigDecimal)rankList[i].get("points");
				rankList[i].put("name", rankList[highest].get("name"));
				rankList[i].put("points", rankList[highest].get("points"));
				rankList[highest].put("name", tempId);
				rankList[highest].put("points", tempPoints);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (rankList.length > 1) {
			try {
				rankList[0].put("rank", 1);
				for (i = 1; i < rankList.length; i++) {
					if (((BigDecimal)rankList[i].get("points")).equals(((BigDecimal)rankList[i - 1].get("points")))) {
						rankList[i].put("rank", rankList[i - 1].get("rank"));
					} else {
						rankList[i].put("rank", i + 1);
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("8");
		
		// last competition calculation
		Query firstComp = session.createQuery("from CompetitionsTable where id = :id");
		firstComp.setString("id", competitionsArray[0].id);
		CompetitionsTable lastComp = (CompetitionsTable)firstComp.uniqueResult();
		System.out.println("9");
		for (i = 1; i < competitionsArray.length; i++) {
			Query getComp = session.createQuery("from CompetitionsTable where id = :id");
			getComp.setString("id", competitionsArray[i].id);
			CompetitionsTable cur = (CompetitionsTable)getComp.uniqueResult();
			if (cur.year.intValue() > lastComp.year.intValue() || (cur.year.intValue() == lastComp.year.intValue() && cur.month.intValue() > lastComp.month.intValue()) || (cur.year.intValue() == lastComp.year.intValue() && cur.month.intValue() == lastComp.month.intValue() && cur.day.intValue() > lastComp.day.intValue())) {
				lastComp = cur;
			}
		}
		System.out.println("10");
		responseMap.put("lastComp", lastComp.name);
		responseMap.put("list", rankList);
		response.setStatus(200);
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
	
	// I/P: <none>
	// O/P: success
	void getIndividualScores(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Map<String, BigDecimal> points = new HashMap<String, BigDecimal>();
		Query competitionListQuery = session.createQuery("from C3CompetitionsTable where c3cup = true");
		@SuppressWarnings("unchecked")
		List<C3CompetitionsTable[]> competitionList = competitionListQuery.list();
		C3CompetitionsTable[] competitionsArray = (C3CompetitionsTable[]) competitionList.toArray(new C3CompetitionsTable[competitionList.size()]);
		for (C3CompetitionsTable competition: competitionsArray) {
			Query c3cupList = session.createQuery("from C3CupPointsTable where competitionId = :competitionId");
			c3cupList.setString("competitionId", competition.id);
			@SuppressWarnings("unchecked")
			List<C3CupPointsTable[]> competitorList = c3cupList.list();
			C3CupPointsTable[] array = (C3CupPointsTable[]) competitorList.toArray(new C3CupPointsTable[competitorList.size()]);
			for (C3CupPointsTable element: array) {
				if (points.get(element.wcaId) == null) {
					points.put(element.wcaId, new BigDecimal(0));
				}
				points.put(element.wcaId, points.get(element.wcaId).add(element.points));
			}
		}
		
		JSONObject previousCompetitions[] = new JSONObject[competitionsArray.length];
		for (int i = 0; i < competitionsArray.length; i++) {
			Query lastCompQuery = session.createQuery("from CompetitionsTable where id = :id");
			lastCompQuery.setString("id", competitionsArray[i].id);
			CompetitionsTable lastComp = (CompetitionsTable)lastCompQuery.uniqueResult();
			int lastCompIndex = i;
			for (int j = i + 1; j < competitionsArray.length; j++) {
				Query getComp = session.createQuery("from CompetitionsTable where id = :id");
				getComp.setString("id", competitionsArray[j].id);
				CompetitionsTable cur = (CompetitionsTable)getComp.uniqueResult();
				if (cur.year.intValue() > lastComp.year.intValue() || (cur.year.intValue() == lastComp.year.intValue() && cur.month.intValue() > lastComp.month.intValue()) || (cur.year.intValue() == lastComp.year.intValue() && cur.month.intValue() == lastComp.month.intValue() && cur.day.intValue() > lastComp.day.intValue())) {
					lastComp = cur;
					lastCompIndex = j;
				}
			}
			C3CompetitionsTable temp = competitionsArray[i];
			competitionsArray[i] = competitionsArray[lastCompIndex];
			competitionsArray[lastCompIndex] = temp;
			previousCompetitions[i] = new JSONObject();
			try {
				previousCompetitions[i].put("competitionId", lastComp.id);
				previousCompetitions[i].put("competitionName", lastComp.name);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for (int i = 0; i < competitionsArray.length; i++) {
			try {
				String competitionId = (String)previousCompetitions[i].get("competitionId");
				Query c3CupListQuery = session.createQuery("from C3CupPointsTable where competitionId = :competitionId order by points desc");
				c3CupListQuery.setString("competitionId", competitionId);
				@SuppressWarnings("unchecked")
				List<C3CupPointsTable[]> list = c3CupListQuery.list();
				C3CupPointsTable[] array = (C3CupPointsTable[]) list.toArray(new C3CupPointsTable[list.size()]);
				JSONObject pointList[] = new JSONObject[list.size()];
				for (int j = 0; j < array.length; j++) {
					pointList[j] = new JSONObject();
					pointList[j].put("name", this.getPersonName(session, array[j].wcaId));
					pointList[j].put("points", array[j].points);
				}
				previousCompetitions[i].put("c3scores", pointList);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		responseMap.put("list", previousCompetitions);
		response.setStatus(200);
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

	private String getPersonName(Session session, String wcaId) {
		Query query = session.createQuery("from PersonsTable where wcaId = :wcaId");
		query.setString("wcaId", wcaId);
		PersonsTable result = (PersonsTable)query.uniqueResult();
		return result.name;
	}
}
