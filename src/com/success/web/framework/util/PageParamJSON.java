package com.success.web.framework.util;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PageParamJSON {

	public static Map<String, String> parseParam(String str) throws JSONException{
		Map<String, String> map = new HashMap<String, String>();
		if(str == null || "".equals(str)){
			return map;
		}
		JSONArray jsonArray = new JSONArray(str);
		int iSize = jsonArray.length();
		for (int i = 0; i < iSize; i++) {
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			map.put((String)jsonObj.get("name"), (String)jsonObj.get("value"));
		}
		return map;
	}
}
