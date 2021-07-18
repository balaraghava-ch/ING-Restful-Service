package com.ing.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ing.rest.data.ATMLocation;
public class ATMLoacationControllerTest extends AbstractTest 
{
	   @Override
	   @Before
	   public void setUp() {
	      super.setUp();
	   }
	   @Test
	   public void getATMLocationList() throws Exception 
	   {
	      String uri = "/api/locator/atms";
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      ATMLocation[] atmLocationlist = super.mapFromJson(content, ATMLocation[].class);
	      assertTrue(atmLocationlist.length > 0);
	   }
	   
}
