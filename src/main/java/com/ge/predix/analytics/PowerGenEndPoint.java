package com.ge.predix.analytics;

import java.io.IOException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ge.predix.model.AssetResponse;
import com.ge.predix.model.Data;
import com.ge.predix.model.Timeseriesoutput;

public class PowerGenEndPoint {
	Logger logger = LoggerFactory.getLogger(PowerGenEndPoint.class);
	
	ObjectMapper mapper = new ObjectMapper();
	
	@SuppressWarnings("unchecked")
	public String calcWatts(String jsonInput) throws IOException
	{
	
		HashMap<String, Integer> jsonDataMap = mapper.readValue(jsonInput, HashMap.class);
		long voltage = jsonDataMap.get("voltage");
		long ampere = jsonDataMap.get("ampere");
		long timeStamp = jsonDataMap.get("timestamp");
		
		Timeseriesoutput tsOutput = new Timeseriesoutput();
		tsOutput.setPowerInWatts(voltage*ampere);
		tsOutput.setTimeStamp(timeStamp);
		Data data = new Data();
		data.setTimeseriesOutput(tsOutput);
		AssetResponse assetResponse = new AssetResponse();
		assetResponse.setData(data);
			
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		
		return mapper.writeValueAsString(assetResponse);
	}
	
}
