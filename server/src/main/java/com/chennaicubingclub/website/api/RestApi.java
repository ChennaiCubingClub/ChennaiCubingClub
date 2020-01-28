package com.chennaicubingclub.website.api;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chennaicubingclub.website.service.C3CompetitionsService;
import com.chennaicubingclub.website.service.C3CupService;
import com.chennaicubingclub.website.service.UsersService;

@RestController
@RequestMapping("/api")
public class RestApi {
	
	@Autowired
	@Qualifier("C3Competitions")
	C3CompetitionsService c3Competitions;
	
	@Autowired
	@Qualifier("Users")
	UsersService users;
	
	@Autowired
	@Qualifier("C3Cup")
	C3CupService c3cup;

	@CrossOrigin(origins = "*")
	@RequestMapping({"/*", "/*/*", "/*/*/*", "/*/*/*/*"})
	public void controller(HttpServletRequest request, HttpServletResponse response) {
		if (!isAuthenticated(request.getHeader("Authorization"))) {
			response.setStatus(401);
		} else {
			String[] components = request.getRequestURI().split("/");
			if (components.length < 4) {
				response.setStatus(404);
				return;
			} else {
				switch (components[2]) {
					case "v1":
						switch (components[3]) {
							case "c3cup":
								c3cup.action(Arrays.copyOfRange(components, 4, components.length), request, response);
								break;
							case "c3competitions":
								c3Competitions.action(Arrays.copyOfRange(components, 4, components.length), request, response);
								break;
							case "users":
								users.action(Arrays.copyOfRange(components, 4, components.length), request, response);
								break;
							default:
								response.setStatus(404);
								return;
						}
						break;
					default:
						response.setStatus(404);
						return;
				}
			}
		}
	}
	
	boolean isAuthenticated(String token) {
		return true;
	}
}
