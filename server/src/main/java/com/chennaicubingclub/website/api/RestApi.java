package com.chennaicubingclub.website.api;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chennaicubingclub.website.api.ControllerApi;

@RestController
@RequestMapping("/api")
public class RestApi {

	@CrossOrigin(origins = "*")
	@RequestMapping({"/*", "/*/*", "/*/*/*", "/*/*/*/*"})
	public void controller(HttpServletRequest request, HttpServletResponse response) {
		ControllerApi controllerApi;
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
								controllerApi = new C3CupApi();
								break;
							case "c3competitions":
								controllerApi = new C3CompetitionsApi();
								break;
							case "users":
								controllerApi = new UsersApi();
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
			controllerApi.controller(Arrays.copyOfRange(components, 4, components.length), request, response);
		}
	}
	
	boolean isAuthenticated(String token) {
		return true;
	}
}
