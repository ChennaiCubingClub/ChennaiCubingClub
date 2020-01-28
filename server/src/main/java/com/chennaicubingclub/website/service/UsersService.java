package com.chennaicubingclub.website.service;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.json.JSONObject;

import com.chennaicubingclub.website.entity.C3UsersTable;
import com.chennaicubingclub.website.repository.C3UsersRepo;

@Service("Users")
public class UsersService implements ServiceInterface {
	
	@Autowired
	C3UsersRepo repo;

	@Override
	public void action(String[] components, HttpServletRequest request, HttpServletResponse response) {
		switch (request.getMethod()) {
			case "POST":
				if (components[0].equals("login")) {
					login(request, response);
				} else {
					response.setStatus(404);
				}
				break;
			default:
				response.setStatus(404);
		}
	}
	
	// I/P: username, password
	// O/P: success
	void login(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> responseMap = new HashMap<String, String>();
		if (request.getParameterMap().containsKey("username") && request.getParameterMap().containsKey("password")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			java.util.List<C3UsersTable> users = repo.getUsers(username, getMd5(password));
			if (users.size() == 1) {
				C3UsersTable user = users.get(0);
				user.token = generateToken();
				repo.save(user);
				responseMap.put("success", "true");
				responseMap.put("token", user.token);
				response.setStatus(200);
			} else {
				responseMap.put("success", "false");
				response.setStatus(200);
			}
		} else {
			responseMap.put("error", "Argument missing");
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
	
	private String getMd5(String input) { 
        try { 
  
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    }
	
	private String generateToken() { 
		  
        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(12); 
  
        for (int i = 0; i < 12; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        }
  
        return sb.toString(); 
    }

}
