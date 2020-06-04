package com.springcore;

import java.security.Principal;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

@RestController

public class Controller {   
	   
	   @Autowired
	   ContactService contactService;
	   static String token;
	   
	 @RequestMapping(value = "/user") // call this method first
	   public String user(OAuth2Authentication authentication) {
	        token =	((OAuth2AuthenticationDetails)authentication.getDetails()).getTokenValue();
            return "Hello";
	    } 
	 
	 @GetMapping(value = "/user/getMyConnections") 
	 @ResponseBody
	 public ResponseEntity<String> getMyConnections() {
	        ResponseEntity<String> contacts = contactService.getMyConnections(token);     
	        return contacts;
	    } 
	 
	 // {resourceName} that identifies the contact as returned by getMyConnections list
	 @GetMapping(value = "/user/getContactDetails/people/{resourceName}")// ex:"people/me", personFields = names,birthdays,addresses,...
	 @ResponseBody
	 public ResponseEntity<String> getContactDetails(@PathVariable("resourceName") String name) {
		    
	        ResponseEntity<String> contacts = contactService.getContactDetails(token,name);     
	        return contacts;
	    } 
	 
}
