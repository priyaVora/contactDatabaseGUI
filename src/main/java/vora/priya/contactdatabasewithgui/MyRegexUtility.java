package vora.priya.contactdatabasewithgui;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyRegexUtility implements RegexUtility {

	public boolean isValidHumanName(String name) {
		String regex = "^(Mr|Ms|Miss|Mrs|Dr|[a-zA-Z]{1,5}\\.)?(\\s?+[a-zA-Z]+)(\\s[A-Z]\\.?\\s[a-zA-Z]+|\\s[a-zA-Z]+\\s[a-zA-Z]+|\\s[a-zA-Z]+\\s?$)";
		return Pattern.matches(regex, name);
	}

	public boolean isValidEmailAddress(String email) {
		String regex = "^([a-zA-Z])((\\w+|(\\.)+))(@)([a-zA-Z])(([a-zA-Z0-9]+)(\\.+))+([a-zA-z]{3,4})$";
		return Pattern.matches(regex, email);
	}

	public boolean isValidPhoneNumber(String phone) {
		String regex = "^([+]?[\\d]{0,3})([-])([(]?[\\d]{3}[)]?)([-])([\\d]{3})([-])([\\d]{4})$";
		return Pattern.matches(regex, phone);
	}

	public boolean isValidSSN(String ssn) {
		String regex = "^((?!000|666)[0-8][0-9]{2}-)((?!00)[0-9]{2}-)((?!0000)[0-9]{4})$";
		return Pattern.matches(regex, ssn);
	}

	public boolean isValidUSStreetAddress(String street) {
		String regex = "(\\s)?([\\d]+)(\\sNorth|\\sSouth|\\sEast|\\sWest)?(\\s)([a-zA-Z]+|[0-9]*)?(\\s[a-zA-Z]+)?(\\s)(Apt#\\.|Suite#\\.|#[\\.]?)(\\s)?([\\d]+)(\\n)([a-zA-z\\s]+)(,)(\\s)?([A-Z]{2})(\\n|\\s)?([\\d]{5}(-\\d{4})?)";
		return Pattern.matches(regex, street);
	}

	public boolean validatePasswordComplexity(String password, int minLength, int minUpper, int minLower,
			int minNumeric, int minSymbols) {
		
		String regex = "^(?=.*[A-Z]{" + minUpper+ "})(?=.*[a-z]{" + minLower + "})(?=.*[0-9]{"+ minNumeric + "})(?=.*[@#$%^&+=]{" + minSymbols + "})(?=\\S+$).{" + minLength + ",}$";
		System.out.println("Password Validation");
		return Pattern.matches(regex, password);
	}

	public int countContains(String needle, String haystack) {
		//needle is sentence
		//haystack is the term you wanna count for
		int count = 0;
		
		Pattern pattern = Pattern.compile(haystack);
		Matcher match = pattern.matcher(needle);
		
		while(match.find()) { 
			count++;
		}
		
		return count;
	}

	public String getHTMLTagContents(String html, String tagName) {
		String rawRegex = "<" + tagName + ">(.*)<\\/" + tagName + ">";
		Pattern p = Pattern.compile(rawRegex);
		Matcher m = p.matcher(html);
		
		
		if(m.find()) {
			System.out.println("Group Count: " + m.groupCount());
			for(int i= 0; i <=m.groupCount();i++) { 
				System.out.println("Group "+ i + ": "+ m.group(i));
			}			
		}
		
		
		return m.group(1);
	}

	public String[] getHTMLTagsContents(String html, String tagName) {
		String rawRegex = "<" + tagName + ">(.*?)<\\/" + tagName + ">";
		Pattern p = Pattern.compile(rawRegex);
		Matcher m = p.matcher(html);
		
		int count = 0;
		ArrayList<String> ids = new ArrayList<String>();
		
		while(m.find()) {
			String match = m.group(1);
			ids.add(match);
			count++;
		}
			String [] regexArray = new String[ids.size()];
			for (int i = 0; i < count; i++) {
				regexArray[i] = ids.get(i);
				System.out.println(regexArray[i]);
			}		
		return regexArray;
	}

	public String[] getHTMLLinkURL(String html) {
		String link = "<a>(.*?)<\\/a>";
		Pattern p = Pattern.compile(link);
		Matcher m = p.matcher(html);
		
		int count = 0;
		ArrayList<String> linkList = new ArrayList<String>();
		
		while(m.find()) {
			String match = m.group(1);
			linkList.add(match);
			count++;
		}
			String [] linkArray = new String[linkList.size()];
			for (int i = 0; i < count; i++) {
				linkArray[i] = linkList.get(i);
				System.out.println(linkArray[i]);
			}		
		return linkArray;
	}

}
