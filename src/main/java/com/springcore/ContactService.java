package com.springcore;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ContactService {
	
//	@Autowired
	//RestTemplate template;

	public ResponseEntity<String> getMyConnections(String accessToken) {
        String url = "https://people.googleapis.com/v1/{resourceName}/connections"; 
        URI serviceUri = uriWithUrlParams(url, "people/me");
		ResponseEntity<String> result = new RestTemplate().exchange(serviceUri, HttpMethod.GET, entityWithHeaders(accessToken), String.class);	
		return result;
	}
	
	public ResponseEntity<String> getContactDetails(String accessToken,String resourceName) {
        String url = "https://people.googleapis.com/v1/people/{resourceName}"; 
        URI serviceUri = uriWithUrlParams(url, resourceName);
		ResponseEntity<String> result = new RestTemplate().exchange(serviceUri, HttpMethod.GET, entityWithHeaders(accessToken), String.class);	
		return result;
	}
	
	public HttpEntity<String> entityWithHeaders(String accessToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer "+accessToken); 
		HttpEntity<String> entity = new HttpEntity<String>("parameters",headers);
		return entity;
	}
	
	public URI uriWithUrlParams(String url,String name ) {
		Map<String, String> urlParams = new HashMap<>();
	    urlParams.put("resourceName", name);
	  //  String url = "https://people.googleapis.com/v1/people/me";
	 
	    //   Query parameters
	    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
	            // Add query parameter
	            .queryParam("personFields", "names,phoneNumbers"); 
	    return builder.buildAndExpand(urlParams).toUri();
	}
}
