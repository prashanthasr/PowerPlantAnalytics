package com.ge.predix.analytics;

import java.io.IOException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PowerGenEndPoint {
	Logger logger = LoggerFactory.getLogger(PowerGenEndPoint.class);
	
	ObjectMapper mapper = new ObjectMapper();
	
	@SuppressWarnings("unchecked")
	public String calcWatts(String jsonInput) throws IOException
	{
	
		HashMap<String, Integer> jsonDataMap = mapper.readValue(jsonInput, HashMap.class);
		long voltage = jsonDataMap.get("voltage");
		long ampere = jsonDataMap.get("ampere");
		
		AdderResponse output = null;
		output = new AdderResponse();
		output.setResult(voltage*ampere);
		
		return mapper.writeValueAsString(output);
	}
}
