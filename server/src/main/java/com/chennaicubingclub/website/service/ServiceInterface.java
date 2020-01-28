package com.chennaicubingclub.website.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ServiceInterface {

	public void action(String[] components, HttpServletRequest request, HttpServletResponse response);
}
