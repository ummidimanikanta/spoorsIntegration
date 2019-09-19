package com.ayansys.effort.util;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Api {

	public static String toJson(Object object) throws JsonGenerationException,
			JsonMappingException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		Writer strWriter = new StringWriter();
		mapper.writeValue(strWriter, object);
		return strWriter.toString();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object fromJson(String json, Class class1)
			throws JsonParseException, JsonMappingException, IOException 
	{
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.readValue(json, class1);
	}

	@SuppressWarnings("rawtypes")
	public static Object fromJson(String json, TypeReference type)
			throws JsonParseException, JsonMappingException, IOException 
	{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, type);
	}
	
	public static boolean isEmptyString(String string1) {
		if ((string1 != null && string1.trim().length() > 0 && !string1.equals("null"))) {
			return false;
		} else {
			return true;
		}
	}
	
	public static long getTimeDifferenceOfDateTimeInMilies(String dateStart,
			String dateStop) throws ParseException {
		Date start = getDateTimeInUTC(dateStart);
		Date end = getDateTimeInUTC(dateStop);

		long diff = end.getTime() - start.getTime();

		return diff;
	}
	
	public static Date getDateTimeInUTC(String date) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
		return format.parse(date);
	}
	
	public static String getMultiLanguageSupportText(String text){
		String dispositionFileName = "";
		try{
			byte[] fileNameBytes = text.getBytes("utf-8");
			for (byte b: fileNameBytes){
				dispositionFileName += (char)(b & 0xff);
			}
		}catch(Exception e){
			return text;
		}
		return dispositionFileName;
	}

	public static void main(String args[]) throws ParseException{
		String startDate = "2019-08-20 11:20:23";
		String endDate = "2019-08-21 12:05:17";
		long timDiff = getTimeDifferenceOfDateTimeInMilies(startDate,endDate);
		int hours = (int) Math.floor(timDiff / 3600000);
		int minutes = (int) Math.floor((timDiff - hours * 3600000) / 60000);
        
        int seconds = (int) Math.floor((timDiff - hours * 3600000 - minutes * 60000) / 1000);
		
        int milliSecs = (int) Math.floor((timDiff - hours * 3600000 - minutes * 60000 - seconds * 1000) / 1000);
        System.out.println(hours+":"+minutes+":"+seconds+"."+milliSecs);
	}
}