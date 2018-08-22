
import java.util.HashSet;
import java.util.Set;


public class LongestSubstringInTwoStrings {

	Set<String> LongestSubstringInTwoStrings(String aString, String bString) {
		if (aString.length() < bString.length()) {
			return LongestSubstringInTwoStrings(bString, aString);
		}
		int longestLength = 0;
		Set<String> strings = new HashSet<>();
		for (int i = 0; i < aString.length(); i++) {
			int i1 = bString.indexOf(aString.charAt(i));
			if (aString.length() - i <= longestLength) {
				break;
			}
			if (i1 != -1) {
				int  longestSubStringlength = 0, theIndexOfLongest = i1;
				for (int j = i1; j < bString.length() && i + j - i1 < aString.length(); j++) {
					//System.out.println("i = " + i + ", j =" + j + ", i1 = " + i1);
					if (aString.charAt(i + j - i1) == bString.charAt(j)) {
						
						if (j - i1 > longestSubStringlength) {
							longestSubStringlength = j - i1;
							theIndexOfLongest = i1;
						}
						if (longestSubStringlength >= longestLength) {
							System.out.println("LL = "+ longestLength + ", LS = " + longestSubStringlength);
							
							if (longestSubStringlength > longestLength) {
								strings = new HashSet<>();
								longestLength = longestSubStringlength;
							}
							strings.add("\"" + bString.substring(theIndexOfLongest, theIndexOfLongest + longestSubStringlength + 1) + "\"");
						}
					} else if (aString.charAt(i) == bString.charAt(j)) {
						i1 = j;
					} else {
						i1 = j +1;
					}
					
				}
				
				//i += Math.max(0, longestSubStringlength);
				
			}
			System.out.println(i);
		}
		return strings;
		
	}
	public static void main(String[] args) {
		String aString = "komijnhbgfcdxsezawq2a3ws4e5drfe45dr6ft7gy8asdfsdfghu9jiokojnhbgvfcdxsqawz", 
				bString = "azwsexdcfgvhbjioikpmo,lpokij9uh8y7gt6f5rasdfgde4sw3qazaesxd9876543wqazsdxfcgvhbjo09ijuhygtftcdxs";
		System.out.println(aString.length() + ", " + bString.length());
		System.out.println(new LongestSubstringInTwoStrings().LongestSubstringInTwoStrings(aString, bString));
	}

}
