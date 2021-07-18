package com.ing.rest;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.rest.data.ATMLocation;
import com.ing.rest.data.Address;
import com.ing.rest.exception.DataNotFoundException;
import com.ing.rest.exception.JsonMapException;
import com.ing.rest.exception.JsonProcessException;


@RestController
@ControllerAdvice
@RequestMapping(value="/api/locator")
public class ATMLoacationController 
{
    @Autowired
	RestTemplate restTemplate;
    
	
	@GetMapping(value="/atms",produces={"application/json"})
	public List<Address>  getATMLocations()
   {
		
		HttpEntity<String> entity = httpDetails();
		ResponseEntity<String> rensponseEntity = restTemplate.exchange("https://www.ing.nl/api/locator/atms/",HttpMethod.GET, entity , String.class);
		ObjectMapper objMapper = new ObjectMapper();
		List<Address> addressList = new ArrayList<>();
		try 
		{
			ATMLocation[] atmLocation=objMapper.readValue(rensponseEntity.getBody().substring(rensponseEntity.getBody().indexOf('\n')+1),ATMLocation[].class);
			List<ATMLocation> locationList = Arrays.asList(atmLocation);
			addressList= locationList.stream().map(ATMLocation::getAddress).collect(Collectors.toList());
			if(addressList.isEmpty()) throw new DataNotFoundException("No Data found");
		} 
		catch (JsonMappingException e) 
		{
			throw new JsonMapException("JSon Mapping Exception");
		} 
		catch (JsonProcessingException e) 
		{
			throw new JsonProcessException("JSon Processing Exception");
		}
		return addressList;
	   
   }
	
	
	@GetMapping(value="/atms/cities",produces={"application/json"})
	public List<Address>  getATMLocationByCity(@RequestParam(value = "cityName", required = true) String cityName)
   {
		HttpEntity<String> entity = httpDetails();
		ResponseEntity<String> rensponseEntity = restTemplate.exchange("https://www.ing.nl/api/locator/atms/",HttpMethod.GET, entity , String.class);
		ObjectMapper objMapper = new ObjectMapper();
		List<Address> addressList = new ArrayList<>();
		try 
		{
			ATMLocation[] atmLocation=objMapper.readValue(rensponseEntity.getBody().substring(rensponseEntity.getBody().indexOf('\n')+1),ATMLocation[].class);
			List<ATMLocation> locationList = Arrays.asList(atmLocation);
			addressList= locationList.stream().map(ATMLocation::getAddress).filter(cit -> cit.getCity().equalsIgnoreCase(cityName)).collect(Collectors.toList());
			if(addressList.isEmpty()) throw new DataNotFoundException("No Data Found");
		} 
		catch (JsonMappingException e) 
		{
			throw new JsonMapException("JSon Mapping Exception");
		} 
		catch (JsonProcessingException e) 
		{
			throw new JsonProcessException("JSon Processing Exception");
		}
		
		return addressList;
	   
   }
	
	public HttpEntity<String> httpDetails()
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return entity;
	}
}
