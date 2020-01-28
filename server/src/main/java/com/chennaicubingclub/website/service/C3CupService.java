package com.chennaicubingclub.website.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chennaicubingclub.website.entity.C3CompetitionsTable;
import com.chennaicubingclub.website.entity.C3CupPointsTable;
import com.chennaicubingclub.website.entity.CompetitionsTable;
import com.chennaicubingclub.website.entity.ResultsTable;
import com.chennaicubingclub.website.repository.C3CompetitionsRepo;
import com.chennaicubingclub.website.repository.C3CupRepo;
import com.chennaicubingclub.website.repository.CompetitionsRepo;
import com.chennaicubingclub.website.repository.ResultsRepo;

@Service("C3Cup")
public class C3CupService implements ServiceInterface {
	
	@Autowired
	C3CupRepo pointRepo;
	
	@Autowired
	C3CompetitionsRepo compRepo;
	
	@Autowired
	CompetitionsRepo wcaCompRepo;
	
	@Autowired
	ResultsRepo resultsRepo;
	
	@Override
	public void action(String[] components, HttpServletRequest request, HttpServletResponse response) {
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
		if (request.getParameterMap().containsKey("competitionId")) {
			Map<String, BigDecimal> points = new HashMap<String, BigDecimal>();
			Map<String, String> names = new HashMap<String, String>();
			String competitionId = request.getParameter("competitionId");
			List<C3CupPointsTable> pointList = pointRepo.getPointList(competitionId);
			for (C3CupPointsTable point: pointList) {
				pointRepo.delete(point);
			}
			List<String> eventsList = resultsRepo.getEventList(competitionId);
			String[] eventsArray = (String[]) eventsList.toArray(new String[eventsList.size()]);
			for (String event: eventsArray) {
				Long competitorCount = (Long)resultsRepo.getCompetitorCount(competitionId, event);
				BigDecimal x = getX(event, (new BigDecimal(competitorCount)).divide(new BigDecimal(100), 2, RoundingMode.CEILING));
				List<ResultsTable> winnersList = resultsRepo.getWinnersList(competitionId, event);
				ResultsTable[] winnersArray = (ResultsTable[]) winnersList.toArray(new ResultsTable[winnersList.size()]);
				for (ResultsTable winner: winnersArray) {
					if (points.get(winner.personId) == null) {
						points.put(winner.personId, new BigDecimal(0));
					}
					points.put(winner.personId, points.get(winner.personId).add(getPoints(x, winner.pos)));
					names.put(winner.personId, winner.personName);
				}
			}
			for (String key: points.keySet()) {
				C3CupPointsTable newEntry = new C3CupPointsTable();
				newEntry.wcaId = key;
				newEntry.competitionId = competitionId;
				newEntry.points = points.get(key);
				newEntry.personName = names.get(key);
				CompetitionsTable competition = wcaCompRepo.getCompetition(competitionId);
				newEntry.competitionName = competition.name;
				newEntry.year = competition.year;
				newEntry.month = competition.month;
				newEntry.day = competition.day;
				pointRepo.save(newEntry);
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
		Map<String, BigDecimal> points = new HashMap<String, BigDecimal>();
		Map<String, String> names = new HashMap<String, String>();
		Map<String, C3CupPointsTable> competitionDay = new HashMap<String, C3CupPointsTable>();
		List<C3CompetitionsTable> competitionList = compRepo.getC3Competitions();
		C3CompetitionsTable[] competitionsArray = (C3CompetitionsTable[]) competitionList.toArray(new C3CompetitionsTable[competitionList.size()]);
		for (C3CompetitionsTable competition: competitionsArray) {
			List<C3CupPointsTable> competitorList = pointRepo.getPointList(competition.id);
			C3CupPointsTable[] array = (C3CupPointsTable[]) competitorList.toArray(new C3CupPointsTable[competitorList.size()]);
			for (C3CupPointsTable element: array) {
				if (points.get(element.wcaId) == null) {
					points.put(element.wcaId, new BigDecimal(0));
					names.put(element.wcaId, element.personName);
				}
				if (competitionDay.get(element.competitionId) == null) {
					competitionDay.put(element.competitionId, element);
				}
				points.put(element.wcaId, points.get(element.wcaId).add(element.points));
			}
		}
		final Integer size = points.keySet().size();
		JSONObject rankList[] = new JSONObject[size];
		int i = 0;
		for (String key: points.keySet()) {
			try {
				rankList[i] = new JSONObject();
				rankList[i].put("name", names.get(key));
				rankList[i].put("points", points.get(key));
				i = i + 1;
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		// sorting
		for (i = 0; i < rankList.length; i++) {
			int highest = i;
			for (int j = i + 1; j < rankList.length; j++) {
				try {
					if (((BigDecimal)rankList[j].get("points")).compareTo((BigDecimal)rankList[highest].get("points")) == 1) {
						highest = j;
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
		
		// last competition calculation
		C3CupPointsTable lastComp = competitionDay.get(competitionsArray[0].id);
		for (i = 1; i < competitionsArray.length; i++) {
			C3CupPointsTable cur = competitionDay.get(competitionsArray[i].id);
			if (cur.year.intValue() > lastComp.year.intValue() || (cur.year.intValue() == lastComp.year.intValue() && cur.month.intValue() > lastComp.month.intValue()) || (cur.year.intValue() == lastComp.year.intValue() && cur.month.intValue() == lastComp.month.intValue() && cur.day.intValue() > lastComp.day.intValue())) {
				lastComp = cur;
			}
		}
		responseMap.put("lastComp", lastComp.competitionName);
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
	}
	
	// I/P: <none>
	// O/P: success
	void getIndividualScores(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, BigDecimal> points = new HashMap<String, BigDecimal>();
		Map<String, String> names = new HashMap<String, String>();
		Map<String, C3CupPointsTable> competitionDay = new HashMap<String, C3CupPointsTable>();
		List<C3CompetitionsTable> competitionList = compRepo.getC3Competitions();
		C3CompetitionsTable[] competitionsArray = (C3CompetitionsTable[]) competitionList.toArray(new C3CompetitionsTable[competitionList.size()]);
		for (C3CompetitionsTable competition: competitionsArray) {
			List<C3CupPointsTable> competitorList = pointRepo.getPointList(competition.id);
			C3CupPointsTable[] array = (C3CupPointsTable[]) competitorList.toArray(new C3CupPointsTable[competitorList.size()]);
			for (C3CupPointsTable element: array) {
				if (points.get(element.wcaId) == null) {
					points.put(element.wcaId, new BigDecimal(0));
					names.put(element.wcaId, element.personName);
				}
				if (competitionDay.get(element.competitionId) == null) {
					competitionDay.put(element.competitionId, element);
				}
				points.put(element.wcaId, points.get(element.wcaId).add(element.points));
			}
		}
		JSONObject previousCompetitions[] = new JSONObject[competitionsArray.length];
		for (int i = 0; i < competitionsArray.length; i++) {
			C3CupPointsTable lastComp = competitionDay.get(competitionsArray[i].id);
			int lastCompIndex = i;
			for (int j = i + 1; j < competitionsArray.length; j++) {
				C3CupPointsTable cur = competitionDay.get(competitionsArray[j].id);
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
				previousCompetitions[i].put("competitionId", lastComp.competitionId);
				previousCompetitions[i].put("competitionName", lastComp.competitionName);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for (int i = 0; i < competitionsArray.length; i++) {
			try {
				String competitionId = (String)previousCompetitions[i].get("competitionId");
				List<C3CupPointsTable> list = pointRepo.getPointListSorted(competitionId);
				C3CupPointsTable[] array = (C3CupPointsTable[]) list.toArray(new C3CupPointsTable[list.size()]);
				JSONObject pointList[] = new JSONObject[list.size()];
				for (int j = 0; j < array.length; j++) {
					pointList[j] = new JSONObject();
					pointList[j].put("name", names.get(array[j].wcaId));
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
	}

}
