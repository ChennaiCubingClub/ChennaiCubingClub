package com.chennaicubingclub.website.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ControllerApi {

	public void controller(String[] components, HttpServletRequest request, HttpServletResponse response);
}
