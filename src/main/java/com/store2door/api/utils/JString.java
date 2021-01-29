package com.store2door.api.utils;

import java.util.Random;

/**
 *
 * @author janardhan
 */
public class JString {
    /**
     * changes every occurance of inSearchForThis with inReplaceWithThis. If
     * InIgnoreCase is true, all searches ignore case.
     */
    public static String replace(String argString, boolean argIgnoreCase,
        String argSearchForThis, String argReplaceWithThis) {
        int numberOfCharsToCompare = argSearchForThis.length();
        int substringStartingPosition = 0;
        int index = 0;
        StringBuffer outString = new StringBuffer();

        // declare local versions of the input sreings so we can
        // upper them to support IgnoreCase replacement.
        String sourceString;
        String searchForThis;

        // if the user wants to ignore case
        if (argIgnoreCase) {
            sourceString = argString.toLowerCase();
            searchForThis = argSearchForThis.toLowerCase();
        } else {
            // use the input "as is"
            sourceString = argString;
            searchForThis = argSearchForThis;
        }

        substringStartingPosition = sourceString.indexOf(searchForThis);

        // if the input string does not contain a single quote
        if (substringStartingPosition == -1) {
            // just return the input string
            return argString;
        }

        // loop thru the string until we have no more single quotes
        for (index = 0; substringStartingPosition != -1;) {
            // append everything up to this single quote
            outString.append(argString.substring(index,
                    substringStartingPosition));

            // add the string we want to supply instead
            outString.append(argReplaceWithThis);

            // start the next search after the single quote we just found
            index = substringStartingPosition + numberOfCharsToCompare;

            // try to find another single quote
            substringStartingPosition = sourceString.indexOf(searchForThis,
                    index);
        } // end loop thru the string

        // append the remainder of the input string
        outString.append(argString.substring(index, argString.length()));

        return outString.toString();
    } // end replace;

    /**
     * changes single quotes to double quotes so the text can be used in a SQL
     * statement.
     */
    public static String sqlString(String argString) {
        int nextQuotePosition = 0;
        int index = 0;
        StringBuffer outString = new StringBuffer();
        String doubleQuote = "''";

        // if we have a null string
        if ((argString == null) || (argString.length() == 0)) {
            return "NULL";
        }

        // try to find the location of the first single quote
        nextQuotePosition = argString.indexOf('\'');

        // if the input string does not contain a single quote
        if (nextQuotePosition == -1) {
            // just return the input string
            return "'" + argString + "'";
        }

        // loop thru the string until we have no more single quotes
        for (index = 0; nextQuotePosition != -1;) {
            // append everything up to this single quote
            outString.append(argString.substring(index, nextQuotePosition));

            // add a double quote where the single quote was
            outString.append(doubleQuote);

            // start the next search after the single quote we just found
            index = nextQuotePosition + 1;

            // try to find another single quote
            nextQuotePosition = argString.indexOf('\'', index);
        } // end loop thru the string

        // append the remainder of the input string
        outString.append(argString.substring(index, argString.length()));

        // return the string with single quotes replaced by double quotes
        return "'" + outString.toString() + "'";
    } // end sqlString;

    /**
     * this method extracts the digits (0,1,...9) from the current JString and
     * returns them. If JString contains "9AB76-56", GetNumbers will return
     * "97656". GetString is handy for removing punctuation from social security
     * numbers and phone numbers.
     */
    public static String getNumbers(String argString) {
        StringBuffer outString = new StringBuffer();

        try {
            // if we have a null string
            if ((argString == null) || (argString.length() == 0)) {
                return "";
            }

            // loop thru each character in the string
            for (int index = 0; index <= (argString.length() - 1); index++) {
                // if this character is a digit
                if (Character.isDigit(argString.charAt(index))) {
                    // append this digit
                    outString.append(argString.charAt(index));
                }
            }

            // return the string with single quotes replaced by double quotes
            return outString.toString();
        } catch (Exception e) {
            return "";
        }
    } // end getNumbers;

    /**
     * This method will always return a non null String. If the input String is
     * not null, the input string is returned. If the input is null, a 1
     * character blank String is returned.
     *
     * This method should be used in cases where a field is optional on a web
     * page, but is declared as a not null field in the DB.
     */
    public static String blankIfNull(String argString) {
        // if the caller wants to ignore any $, % etc
        if ((argString == null) || (argString.length() == 0)) {
            return "";
        } else {
            return argString;
        }
    } // end blankIfNull;
    
    public static String blankIfSpecial(String argString) {
        // if the caller wants to ignore any $, % etc
        if ((argString != null) &&  (argString.equalsIgnoreCase("-1")) ) {
            return "";
        } else {
            return argString;
        }
    } // end blankIfNull;

    public static boolean isEmpty(String argString) {
        // if the caller wants to ignore any $, % etc
        if ((argString == null) || (argString.length() == 0)) {
            return true;
        } else {
            return false;
        }
    } // end blankIfNull;
    public static boolean isEmptyString(String argString) {
        // if the caller wants to ignore any $, % etc
        if ((argString.equalsIgnoreCase(""))) {
            return true;
        } else {
            return false;
        }
    } 
    public static boolean isEmpty(Object argString) {
        // if the caller wants to ignore any $, % etc
        if ((argString == null) ) {
            return true;
        } else {
            return false;
        }
    } // end blankIfNull;
    public static boolean isCompletlyEmpty(String argString) {
        if ((argString == null) || (argString.trim().length() == 0) || (argString.equalsIgnoreCase("null"))) {
            return true;
        } else {
            return false;
        }
    }
    public static String negIfNull(String argString) {
        if ((argString == null) || (argString.length() == 0)) {
            return "-1";
        } else {
            return argString;
        }
    } // end negIfNull;

    /**
     * This method will always return a non null String. If the input String is
     * not null, the input string is returned. If the input is null, a 1
     * character blank String is returned.
     *
     * This method should be used in cases where a field is optional on a web
     * page, but is declared as a not null field in the DB.
     */
    public static String htmlBlankIfNull(String argString) {
        // if the caller wants to ignore any $, % etc
        if ((argString == null) || (argString.length() == 0)) {
            return "&nbsp;";
        } else {
            return argString;
        }
    } // end htmlBlankIfNull;

       public static double convertStringToDouble(String arg) {
    	
    	double result = 0;
     // check whether arg is null or blank
    	if(arg != null){
    		arg = arg.trim();
    		if( arg != "")
    		result = Double.parseDouble(arg);
    	}
     return result;
    } // end 
    public static String formatAsPhoneNumber(String argPhone) {
        // if input is not the exact length for a phone number
        if ((argPhone == null) || (argPhone.length() != 10)) {
            // can't be formatted correctly, so just return it
            return argPhone;
        }

        // put punctuation between each section and return it
        return ("" + argPhone.substring(0, 3) + "-" + argPhone.substring(3, 6) +
        "-" + argPhone.substring(6, 10));
    } // end formatAsPhoneNumber;

     
   
       public static String convertCurrecyFormatToRegular(String currency){
    	String regularString="0";
    	if(!isEmpty(currency)){
    		regularString= currency.replaceAll(",([^,]*)$", "$1");
    		regularString.replaceAll("\\s","");
    	}
    	return regularString.trim();
    }
    public static int getNumeric(String doubleValue){
    	int i=0;
    	if(!isEmpty(doubleValue)){
    		i=Double.valueOf(doubleValue).intValue();
    		
    	}
    	return i;
    	
    }
   public static String firstLetterCapitalize(String str){
        
        if(isCompletlyEmpty(str))
            return "";
        
        if(str.length() >= 1)
          //  return str.toUpperCase();
            str= str.substring(0, 1).toUpperCase() + str.substring(1);
    return str;
    
    }
 public static String capitalize(String str){
        
        if(isCompletlyEmpty(str))
            return "";
        else
        	str=str.toUpperCase();

    return str;
    
    }
  public static int parseIntegerIfNotNull(String input){
	 int output = 0;
	 try{
		 output = isEmpty(input)?0:Integer.parseInt(input);
	 }catch(Exception ex){
		 throw ex;
	 }
	 return output;
 }
 
 public static Long parseLongIfNotNull(String input){
	 Long output = 0l;
	 try{
		 output = isEmpty(input)?output:Long.valueOf(input);
	 }catch (Exception e) {
		throw e;
	}
	return output;
 }
 public static String getRandomString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
		StringBuilder random = new StringBuilder();
		Random rnd = new Random();
		while (random.length() < 7) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			random.append(SALTCHARS.charAt(index));
		}
		String saltStr = random.toString();
		return saltStr;
	}

} // end JString

