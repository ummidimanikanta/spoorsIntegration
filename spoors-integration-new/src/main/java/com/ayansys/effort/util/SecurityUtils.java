package com.ayansys.effort.util;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

public class SecurityUtils {
	
	public static final int INPUT_TYPE_NAME = 1;
	public static final int INPUT_TYPE_ADDRESS = 2;
	public static final int INPUT_TYPE_EMAIL = 3;
	public static final int INPUT_TYPE_COMPANY_NAME = 4;
	public static final int INPUT_TYPE_PHONE = 5;
	public static final int INPUT_TYPE_COMPANY_LABEL = 6;
	public static final int INPUT_TYPE_TEXT = 7;
	public static final int INPUT_TYPE_FORM_OR_ENTITY_FIELD_LABEL = 8;
	public static final int INPUT_TYPE_FORM_OR_ENTITY_TITLE = 9;
	public static final int INPUT_TYPE_OTHER_TITLE = 10;
	public static final int INPUT_TYPE_COMPANY_LEVEL_ID = 11;
	public static final int INPUT_TYPE_CAPTCHA = 12;
	public static final int INPUT_TYPE_NUMBER = 13;
	public static final int INPUT_TYPE_NUMBER_CSV = 14;
	public static final int INPUT_TYPE_ALPAHBET = 15;
	public static final int INPUT_TYPE_ALPAHBET_WITH_UNDERSCORE = 16;
	public static final int INPUT_TYPE_ALPAHBET_WITH_HYPHEN = 17;
	public static final int INPUT_TYPE_ALPAHBET_WITH_HYPHEN_AND_UNDERSCORE = 18;
	public static final int INPUT_TYPE_DATE_TIME = 19;
	public static final int INPUT_TYPE_JSON = 20;
	public static final int INPUT_TYPE_BOOLEAN = 21;
	public static final int INPUT_TYPE_LOCATION = 22;
	public static final int INPUT_TYPE_SQL_ORDER = 23;
	public static final int INPUT_TYPE_URL = 24;
	public static final int INPUT_TYPE_ALPHA_NUMERIC = 25;
	public static final int INPUT_TYPE_EXPRESSION = 26;
	public static final int INPUT_TYPE_POSITIVE_NUMBER_CSV = 27;
	
	public static final String OTHER_LANGUAGES_EXPR_STRING = "\\x{0C00}-\\x{0C7F}\\x{0980}-\\x{09FF}\\x{0900}-\\x{097F}\\x{0B80}-\\x{0BFF}\\x{0600}-\\x{06FF}\\x{0C80}-\\x{0CFF}\\x{00A0}-\\x{00FF}\\x{0100}-\\x{017F}\\x{0180}-\\x{024F}\\x{0A80}-\\x{0AFF}\\x{0370}-\\x{03FF}\\x{0400}-\\x{04FF}\\x{0500}-\\x{052F}\\x{0E00}-\\x{0E7F}\\x{1000}-\\x{109F}\\x{10A0}-\\x{10FF}\\x{1200}-\\x{137F}\\x{0B00}-\\x{0B7F}\\x{1E00}-\\x{1EFF}\\x{20A0}-\\x{20CF}\\x{0D00}-\\x{0D7F}\\x{1F00}-\\x{1FFF}\\x{1780}-\\x{17FF}\\x{19E0}-\\x{19FF}\\x{10300}-\\x{1032F}\\x{4E00}-\\x{9FFF}\\x{3400}-\\x{4DBF}\\x{20000}-\\x{2A6DF}\\x{2A700}-\\x{2B73F}\\x{2B740}-\\x{2B81F}\\x{2B820}-\\x{2CEAF}\\x{F900}-\\x{FAFF}\\x{2F800}-\\x{2FA1F}";
	public static final String EXPR_FOR_TYPE_NAME = "^((?!["+ OTHER_LANGUAGES_EXPR_STRING +"a-zA-Z0-9~@#&$^*()!_+|,.;: -]).)*$";
	public static final String EXPR_FOR_TYPE_ADDRESS = "^((?!["+ OTHER_LANGUAGES_EXPR_STRING +"a-zA-Z0-9~@#&$^*()!_+{}|,.;\"?: -]).)*$";
	public static final String EXPR_FOR_TYPE_EMAIL = "^((?![a-zA-Z0-9.!#$%&*+-/?^_`{|}~]).)*$";
	public static final String EXPR_FOR_TYPE_COMPANY_NAME = "^((?!["+ OTHER_LANGUAGES_EXPR_STRING +"a-zA-Z0-9~@#&$^*()!_+{}|,.;\"?: -]).)*$";
	public static final String EXPR_FOR_TYPE_PHONE = "^((?![0-9\\(\\)+ -]).)*$";
	public static final String EXPR_FOR_TYPE_COMPANY_LABEL = "^((?!["+ OTHER_LANGUAGES_EXPR_STRING +"a-zA-Z0-9~@#&$^*()!_+{}|,.;\"?: -]).)*$";
	public static final String EXPR_FOR_TYPE_TEXT = "^((?!["+ OTHER_LANGUAGES_EXPR_STRING +"a-zA-Z0-9~@#&$^*()!_+{}|,.;/?%: -]).)*$";
	public static final String EXPR_FOR_TYPE_FORM_OR_ENTITY_FIELD_LABEL = "^((?!["+ OTHER_LANGUAGES_EXPR_STRING +"a-zA-Z0-9~@#&$^*()!_+{}|,.;?: -]).)*$";
	public static final String EXPR_FOR_TYPE_FORM_OR_ENTITY_TITLE = "^((?!["+ OTHER_LANGUAGES_EXPR_STRING +"a-zA-Z0-9~@#&$^*()!_+{}|,.;?: -]).)*$";
	public static final String EXPR_FOR_TYPE_OTHER_TITLE = "^((?!["+ OTHER_LANGUAGES_EXPR_STRING +"a-zA-Z0-9~@#&$^*()!_+{}|,.;?: -]).)*$";
	public static final String EXPR_FOR_TYPE_COMPANY_LEVEL_ID = "^((?!["+ OTHER_LANGUAGES_EXPR_STRING +"a-zA-Z0-9~@#&$^*()!_+{}|,.;?: -]).)*$";
	public static final String EXPR_FOR_TYPE_CAPTCHA = "^((?![a-zA-Z0-9]).)*$";
	public static final String EXPR_FOR_TYPE_NUMBER = "^((?![0-9.-]).)*$";
	public static final String EXPR_FOR_TYPE_NUMBER_CSV = "^((?![0-9.,-]).)*$";
	public static final String EXPR_FOR_TYPE_ALPHABET = "^((?![a-zA-Z ]).)*$";
	public static final String EXPR_FOR_TYPE_ALPAHBET_WITH_UNDERSCORE = "^((?![a-zA-Z _]).)*$";
	public static final String EXPR_FOR_TYPE_ALPAHBET_WITH_HYPHEN = "^((?![a-zA-Z -]).)*$";
	public static final String EXPR_FOR_TYPE_ALPAHBET_WITH_HYPHEN_AND_UNDERSCORE = "^((?![a-zA-Z_ -]).)*$";
	public static final String EXPR_FOR_TYPE_DATE_TIME = "^((?![a-zA-Z0-9./: -]).)*$";
	public static final String EXPR_FOR_TYPE_JSON = "^((?!["+ OTHER_LANGUAGES_EXPR_STRING +"a-zA-Z0-9~@#&$^*()!/_+{}\\[\\]|\\,.;\"//?: -]).)*$";
	public static final String EXPR_FOR_TYPE_BOOLEAN = "^((?!(y|e|s|Y|E|S|n|o|N|O|t|r|u|T|R|U|F|A|L|f|a|l|1|0)).)*$";
	public static final String EXPR_FOR_TYPE_LOCATION = "^((?![0-9., -]).)*$";
	public static final String EXPR_FOR_TYPE_SQL_ORDER = "^((?!(A|S|C|D|E|a|s|c|d|e)).)*$";
	public static final String EXPR_FOR_TYPE_URL = "^((?![a-zA-Z0-9@#&_+!,.;?|/: -]).)*$";
	public static final String EXPR_FOR_TYPE_ALPHA_NUMBER = "^((?![a-zA-Z0-9 ]).)*$";
	public static final String EXPR_FOR_TYPE_EXPRESSION = "^((?![a-zA-Z0-9< >,_]).)*$";
	public static final String EXPR_FOR_TYPE_POSITIVE_NUMBER_CSV = "^((?![0-9,]).)*$";
	
	/*public static final String EXPR_FOR_TYPE_NAME = "^((?![a-zA-Z0-9~@#&$^*()!_+|\\,.;: -]).)*$";
	public static final String EXPR_FOR_TYPE_ADDRESS = "^((?![a-zA-Z0-9~@#&$^*()!_+{}|\\,.';\"?: -]).)*$";
	public static final String EXPR_FOR_TYPE_EMAIL = "^((?![a-zA-Z0-9.!#$%&'*+-/?^_`{|}~]).)*$";
	public static final String EXPR_FOR_TYPE_COMPANY_NAME = "^((?![a-zA-Z0-9~@#&$^*()!_+{}|\\,.';\"?: -]).)*$";
	public static final String EXPR_FOR_TYPE_PHONE = "^((?![0-9\\(\\)+ -]).)*$";
	public static final String EXPR_FOR_TYPE_COMPANY_LABEL = "^((?![a-zA-Z0-9~@#&$^*()!_+{}|\\,.';\"?: -]).)*$";
	public static final String EXPR_FOR_TYPE_TEXT = "^((?![a-zA-Z0-9~@#&$^*()!_+{}|\\,.';\"?: -]).)*$";
	public static final String EXPR_FOR_TYPE_FORM_OR_ENTITY_FIELD_LABEL = "^((?![a-zA-Z0-9~@#&$^*()!_+{}|\\,.';?: -]).)*$";
	public static final String EXPR_FOR_TYPE_FORM_OR_ENTITY_TITLE = "^((?![a-zA-Z0-9~@#&$^*()!_+{}|\\,.';?: -]).)*$";
	public static final String EXPR_FOR_TYPE_OTHER_TITLE = "^((?![a-zA-Z0-9~@#&$^*()!_+{}|\\,.';?: -]).)*$";
	public static final String EXPR_FOR_TYPE_COMPANY_LEVEL_ID = "^((?![a-zA-Z0-9~@#&$^*()!_+{}|\\,.;?: -]).)*$";
	public static final String EXPR_FOR_TYPE_CAPTCHA = "^((?![a-zA-Z0-9]).)*$";
	public static final String EXPR_FOR_TYPE_NUMBER = "^((?![0-9.-]).)*$";
	public static final String EXPR_FOR_TYPE_NUMBER_CSV = "^((?![0-9.,-]).)*$";
	public static final String EXPR_FOR_TYPE_ALPHABET = "^((?![a-zA-Z ]).)*$";
	public static final String EXPR_FOR_TYPE_ALPAHBET_WITH_UNDERSCORE = "^((?![a-zA-Z _]).)*$";
	public static final String EXPR_FOR_TYPE_ALPAHBET_WITH_HYPHEN = "^((?![a-zA-Z -]).)*$";
	public static final String EXPR_FOR_TYPE_ALPAHBET_WITH_HYPHEN_AND_UNDERSCORE = "^((?![a-zA-Z_ -]).)*$";
	public static final String EXPR_FOR_TYPE_DATE_TIME = "^((?![a-zA-Z0-9./: -]).)*$";
	public static final String EXPR_FOR_TYPE_JSON = "^((?![a-zA-Z0-9~@#&$^*()!/_+{}\\[\\]|\\,.';\"//?: -]).)*$";
	public static final String EXPR_FOR_TYPE_BOOLEAN = "^((?!(y|e|s|Y|E|S|n|o|N|O|t|r|u|T|R|U|F|A|L|f|a|l|1|0)).)*$";
	public static final String EXPR_FOR_TYPE_LOCATION = "^((?![0-9., -]).)*$";
	public static final String EXPR_FOR_TYPE_SQL_ORDER = "^((?!(A|S|C|D|E|a|s|c|d|e)).)*$";
	public static final String EXPR_FOR_TYPE_URL = "^((?![a-zA-Z0-9@#&_+!,.;?|/: -]).)*$";*/
	
	/*Date: 2016-05-05
	*Method Purpose: to skip new lines
	*Resource: Deva*/
	public static final String EXPR_FOR_SKIP_NEW_LINE = "^([\n\t\r\f])*$";  // dont use [\\s*] it includes space also
	                                                                                                                       /*Date: 2016-05-05 (EXPR_FOR_SKIP_NEW_LINE)
	                                                                                                                       *Above variable() Purpose: to skip new lines not
	//Getting from xml file                                                                                                                    *Resource: Deva*/
	public static String exprForTypeName;
	public static String exprForTypeAddress; 
	public static String exprForTypeEmail;
	public static String exprForTypeCompanyName; 
	public static String exprForTypePhone;
	public static String exprForTypeCompanyLabel;
	public static String exprForTypeText;
	public static String exprForTypeFormOrEntityFieldlabel;
	public static String exprForTypeFormOrEntityTitle;
	public static String exprForTypeOtherTitle;
	public static String exprForTypeCompanyLevelId;
	public static String exprForTypeCaptcha;
	public static String exprForTypeNumber; 
	public static String exprForTypeNumberCsv; 
	public static String exprForTypeAlphabet; 
	public static String exprForTypeAlpahbetWithUnderscore; 
	public static String exprForTypeAlpahbetWithHyphen; 
	public static String exprForTypeAlpahbetWithHyphenAndUnderscore; 
	public static String exprForTypeDatetime; 
	public static String exprForTypeJson;
	public static String exprForTypeBoolean; 
	public static String exprForTypeLocation; 
	public static String exprForTypeSqlOrder; 
	public static String exprForSkipNewLine;
	public static String exprForTypeUrl;
	public static String exprForTypeAlphaNumeric;
	public static String exprForTypeExpression;
	public static String exprForTypePositiveNumberCsv; 
	


	public static String stripInvalidCharacters(String value, int userInputType) 
	{
		 if (value != null) 
		 {
            value = value.replaceAll("", "");
            value = value.trim();
           /* if(userInputType != INPUT_TYPE_JSON || userInputType != INPUT_TYPE_CAPTCHA) {
            	value = value.replace("'", "").replace("\"", "");
            }*/
            
            String newValue = "";
            switch (userInputType) 
            {
				case INPUT_TYPE_NAME:
					
					for(int i=0;i<value.length();i++)
					{
						 //newValue += value.substring(i, i+1).replaceAll(EXPR_FOR_TYPE_NAME, "");
						newValue += value.substring(i, i+1).replaceAll(exprForTypeName, "");
						
					}
					value = newValue;
					break;

				case INPUT_TYPE_ADDRESS:
					for(int i=0;i<value.length();i++)
					{
						//newValue += value.substring(i, i+1).replaceAll(EXPR_FOR_TYPE_ADDRESS,"");
						newValue += value.substring(i, i+1).replaceAll(exprForTypeAddress,"");
					}
					value = newValue;
					break;
					
				case INPUT_TYPE_EMAIL:
					for(int i=0;i<value.length();i++)
					{
						newValue += value.substring(i, i+1).replaceAll(exprForTypeEmail,"");
					}
					value = newValue;
					break;	
					
				case INPUT_TYPE_COMPANY_NAME:
					for(int i=0;i<value.length();i++)
					{
						//newValue += value.substring(i, i+1).replaceAll(EXPR_FOR_TYPE_COMPANY_NAME,"");
						newValue += value.substring(i, i+1).replaceAll(exprForTypeCompanyName,"");
					}
					value = newValue;
					break;
					
				case INPUT_TYPE_PHONE:
					for(int i=0;i<value.length();i++)
					{
						//newValue += value.substring(i, i+1).replaceAll(EXPR_FOR_TYPE_PHONE,"");
						newValue += value.substring(i, i+1).replaceAll(exprForTypePhone,"");
					}
					value = newValue;
					break;
					
				case INPUT_TYPE_COMPANY_LABEL:
					for(int i=0;i<value.length();i++)
					{
						//newValue += value.substring(i, i+1).replaceAll(EXPR_FOR_TYPE_COMPANY_LABEL,"");
						newValue += value.substring(i, i+1).replaceAll(exprForTypeCompanyLabel,"");
					}
					value = newValue;
					break;
					
				case INPUT_TYPE_TEXT:
					for(int i=0;i<value.length();i++)
					{
						//newValue += value.substring(i, i+1).replaceAll(EXPR_FOR_TYPE_TEXT,"");
						newValue += value.substring(i, i+1).replaceAll(exprForTypeText,"");
					}
					value = newValue;
					break;
					
				case INPUT_TYPE_FORM_OR_ENTITY_FIELD_LABEL:
					for(int i=0;i<value.length();i++)
					{
						//newValue += value.substring(i, i+1).replaceAll(EXPR_FOR_TYPE_FORM_OR_ENTITY_FIELD_LABEL,"");
						newValue += value.substring(i, i+1).replaceAll(exprForTypeFormOrEntityFieldlabel,"");
					}
					value = newValue;
					break;
				
				case INPUT_TYPE_FORM_OR_ENTITY_TITLE:
					for(int i=0;i<value.length();i++)
					{
						/*newValue += value.substring(i, i+1).replaceAll(EXPR_FOR_TYPE_FORM_OR_ENTITY_TITLE,"")
								                            .replaceAll(EXPR_FOR_SKIP_NEW_LINE, "");*/
						newValue += value.substring(i, i+1).replaceAll(exprForTypeFormOrEntityTitle,"")
	                            .replaceAll(exprForSkipNewLine, "");
					}
					value = newValue;
					break;
					
				case INPUT_TYPE_OTHER_TITLE:
					for(int i=0;i<value.length();i++)
					{
						/*newValue += value.substring(i, i+1).replaceAll(EXPR_FOR_TYPE_OTHER_TITLE,"")
								                            .replaceAll(EXPR_FOR_SKIP_NEW_LINE, "");*/
						newValue += value.substring(i, i+1).replaceAll(exprForTypeOtherTitle,"")
	                            .replaceAll(exprForSkipNewLine, "");
					}
					value = newValue;
					break;
					
				case INPUT_TYPE_COMPANY_LEVEL_ID:
					for(int i=0;i<value.length();i++)
					{
						//newValue += value.substring(i, i+1).replaceAll(EXPR_FOR_TYPE_COMPANY_LEVEL_ID,"");
						newValue += value.substring(i, i+1).replaceAll(exprForTypeCompanyLevelId,"");
					}
					value = newValue;
					break;
				
				case INPUT_TYPE_CAPTCHA:
					for(int i=0;i<value.length();i++)
					{
						//newValue += value.substring(i, i+1).replaceAll(EXPR_FOR_TYPE_CAPTCHA,"");
						newValue += value.substring(i, i+1).replaceAll(exprForTypeCaptcha,"");
					}
					value = newValue;
					break;
					
				case INPUT_TYPE_NUMBER:
					for(int i=0;i<value.length();i++)
					{
						//newValue += value.substring(i, i+1).replaceAll(EXPR_FOR_TYPE_NUMBER,"");
						newValue += value.substring(i, i+1).replaceAll(exprForTypeNumber,"");
					}
					value = newValue;
					break;
					
				case INPUT_TYPE_NUMBER_CSV:
					for(int i=0;i<value.length();i++)
					{
						//newValue += value.substring(i, i+1).replaceAll(EXPR_FOR_TYPE_NUMBER_CSV,"");
						newValue += value.substring(i, i+1).replaceAll(exprForTypeNumberCsv,"");
					}
					value = newValue;
					break;
				case INPUT_TYPE_ALPAHBET:
					for(int i=0;i<value.length();i++)
					{
						//newValue += value.substring(i, i+1).replaceAll(EXPR_FOR_TYPE_ALPHABET,"");
						newValue += value.substring(i, i+1).replaceAll(exprForTypeAlphabet,"");
					}
					value = newValue;
					break;
				case INPUT_TYPE_ALPAHBET_WITH_UNDERSCORE:
					for(int i=0;i<value.length();i++)
					{
						//newValue += value.substring(i, i+1).replaceAll(EXPR_FOR_TYPE_ALPAHBET_WITH_UNDERSCORE,"");
						newValue += value.substring(i, i+1).replaceAll(exprForTypeAlpahbetWithUnderscore,"");
					}
					value = newValue;
					break;
				case INPUT_TYPE_ALPAHBET_WITH_HYPHEN:
					for(int i=0;i<value.length();i++)
					{
						//newValue += value.substring(i, i+1).replaceAll(EXPR_FOR_TYPE_ALPAHBET_WITH_HYPHEN,"");
						newValue += value.substring(i, i+1).replaceAll(exprForTypeAlpahbetWithHyphen,"");
					}
					value = newValue;
					break;
				case INPUT_TYPE_ALPAHBET_WITH_HYPHEN_AND_UNDERSCORE:
					for(int i=0;i<value.length();i++)
					{
						//newValue += value.substring(i, i+1).replaceAll(EXPR_FOR_TYPE_ALPAHBET_WITH_HYPHEN_AND_UNDERSCORE,"");
						newValue += value.substring(i, i+1).replaceAll(exprForTypeAlpahbetWithHyphenAndUnderscore,"");
					}
					value = newValue;
					break;
					
				case INPUT_TYPE_DATE_TIME:
					for(int i=0;i<value.length();i++)
					{
						//newValue += value.substring(i, i+1).replaceAll(EXPR_FOR_TYPE_DATE_TIME,"");
						newValue += value.substring(i, i+1).replaceAll(exprForTypeDatetime,"");
					}
					value = newValue;
					break;
					
				case INPUT_TYPE_JSON:
					for(int i=0;i<value.length();i++)
					{
						//newValue += value.substring(i, i+1).replaceAll(EXPR_FOR_TYPE_JSON,"");
						newValue += value.substring(i, i+1).replaceAll(exprForTypeJson,"");
					}
					value = newValue;
					break;

				case INPUT_TYPE_BOOLEAN:
					for(int i=0;i<value.length();i++)
					{
						//newValue += value.substring(i, i+1).replaceAll(EXPR_FOR_TYPE_BOOLEAN,"");
						newValue += value.substring(i, i+1).replaceAll(exprForTypeBoolean,"");
					}
					value = newValue;
					break;
				case INPUT_TYPE_LOCATION:
					for(int i=0;i<value.length();i++)
					{
						//newValue += value.substring(i, i+1).replaceAll(EXPR_FOR_TYPE_LOCATION,"");
						newValue += value.substring(i, i+1).replaceAll(exprForTypeLocation,"");
					}
					value = newValue;
					break;
				case INPUT_TYPE_SQL_ORDER:
					for(int i=0;i<value.length();i++)
					{
						//newValue += value.substring(i, i+1).replaceAll(EXPR_FOR_TYPE_SQL_ORDER,"");
						newValue += value.substring(i, i+1).replaceAll(exprForTypeSqlOrder,"");
					}
					if(value != null && value.equalsIgnoreCase(newValue))
					{
						value = newValue;
					}
					else
					{
						value = "ASC";
					}
					break;
				case INPUT_TYPE_URL:
					for(int i=0; i<value.length(); i++)
					{
						newValue += value.substring(i, i+1).replaceAll(exprForTypeUrl, "");
					}
					value = newValue;
					break;
				case INPUT_TYPE_ALPHA_NUMERIC:
					for(int i=0; i<value.length(); i++)
					{
						newValue += value.substring(i, i+1).replaceAll(exprForTypeAlphaNumeric, "");
					}
					value = newValue;
					break;
				case INPUT_TYPE_EXPRESSION:
					for(int i=0; i<value.length(); i++)
					{
						newValue += value.substring(i, i+1).replaceAll(exprForTypeExpression, "");
					}
					value = newValue;
					break;
				case INPUT_TYPE_POSITIVE_NUMBER_CSV:
					for(int i=0;i<value.length();i++)
					{
						//newValue += value.substring(i, i+1).replaceAll(EXPR_FOR_TYPE_NUMBER_CSV,"");
						newValue += value.substring(i, i+1).replaceAll(exprForTypePositiveNumberCsv,"");
					}
					value = newValue;
					break;
				default:
					break;
			}
            
            if(value!=null && !Api.isEmptyString(value))
			{
            	value = stripXSSAndSql(value, true);
			}
            
	     }
	       return value;
	}
	/***** This method strip XSS and SQL codes by removing malicious code*/
	public static String stripXSSAndSql(String value, boolean noQuotes) {
        if (value != null) {
            // NOTE: It's highly recommended to use the ESAPI library and uncomment the following line to
            // avoid encoded attacks.
            // value = ESAPI.encoder().canonicalize(value);

            // Avoid null characters
            value = value.replaceAll("", "");

            // Avoid anything between script tags
            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            
         // Avoid anything in a alert() functions type of expression
            scriptPattern = Pattern.compile("alert\\((.*)\\)", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            
            /*Date: 2016-05-09
			*Method Purpose: hadling confirm and prompt 
			*Resource: Deva*/
            // Avoid anything in a alert() functions type of expression
            scriptPattern = Pattern.compile("confirm\\((.*)\\)", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            
            // Avoid anything in a alert() functions type of expression
            scriptPattern = Pattern.compile("prompt\\((.*)\\)", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            

            // Avoid anything in a src='...' type of expression
            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Remove any lonesome </script> tag
            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Remove any lonesome <script ...> tag
            scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid eval(...) expressions
            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid expression(...) expressions
            scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid javascript:... expressions
            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid vbscript:... expressions
            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid onload= expressions
            scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            
         // Avoid onclick= expressions
            scriptPattern = Pattern.compile("onclick(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            
            
            String ipPingPattern  = "ping(.*)(?:[0-9]{1,3}\\.){3}[0-9]{1,3}"; //OScommand injection
            scriptPattern = Pattern.compile(ipPingPattern, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            
            if(noQuotes)
            {
	            value = value.replace("\"", "**spoorsDoubleQuote**");
	            value = value.replace("&", "**spoorsAmp**");
            }
            value = stringUtilEscapeHtml(value);
            value = escapeSql(value);
            value = value.replaceAll("&#39;", "'");
            

            if(noQuotes)
            {
	            value = value.replace("**spoorsDoubleQuote**", "\"");
	            value = value.replace("**spoorsAmp**", "&");
            }
            value = StringUtils.replaceEach(value, new String[]{"&lt;", "&gt;"}, new String[]{"<", ">"});
        }
        return value;
    }

	public static String stringUtilEscapeHtml(String value)
	{
		if (value != null) 
		{
			//return StringUtils.replaceEach(value, new String[]{"&", "\"", "<", ">","'"}, new String[]{"&amp;", "&quot;", "&lt;", "&gt;","&#39;"});
			return StringUtils.replaceEach(value, new String[]{"&", "\"", "<", ">","'","’","”"}, new String[]{"&amp;", "&quot;", "&lt;", "&gt;","&#39;","",""});
		}
		return value;
	}
	
	
	public static String escapeSql(String value)
	{
		if (value != null) 
		{
			return StringEscapeUtils.escapeSql(value);
		}
		return value;
	}
}
