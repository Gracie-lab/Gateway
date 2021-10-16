package com.gateway.gate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Iterator;
import java.util.Map.Entry;

public class RequestBodyHelper {


	public static MultiValueMap<String, String> convertJsonToQueryParamMap( String json ) {

		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = null;
		try {
			jsonNode = mapper.readTree(json);
		}
		catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		Iterator<Entry<String, JsonNode>> fields = jsonNode.fields();

		while ( fields.hasNext() ){
			Entry<String, JsonNode> entry = fields.next();
			multiValueMap.add(entry.getKey(), entry.getValue().asText());
		}

		return multiValueMap;
	}



}
