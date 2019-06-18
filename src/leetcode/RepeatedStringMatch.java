package leetcode;

public class RepeatedStringMatch {

	public int repeatedStringMatch(String a, String b) {
        if (a.length() == 0) {
			return -1;
		} else if (b.length() == 0){
			return 1;
		} 
        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();
        int p1 = 0;
        for (; p1 < aChars.length; p1++) {
        	int time = p1 == 0 ? 0 : 1;
        	for (int p2 = 0, p11 = p1; p2 < bChars.length; p2++,p11++) {
    			if (p11 >= aChars.length) {
    				p11 = p11 % aChars.length;
    			}
        		
        		if (aChars[p11] != bChars[p2]) {
    				time = -1;
    				break;
    			} else {
    				if (p11 == 0) {
    					time++;
    				}
    			}
    		}
        	if (time != -1) {
        		return time;
        	}
        	
        }
        
        return -1;
        
    }
	
	public static void main(String[] args) {
		System.out.println(new RepeatedStringMatch().repeatedStringMatch("aaac", "aac"));
	}

}
