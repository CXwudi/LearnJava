package main.java.com.cxwudi.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class PartitionLabels {
	public List<Integer> partitionLabels(String S) {
        if (S.equals("")) return null;
        ArrayList<Integer> num = new ArrayList<>();
        
        int pointer2 = -1, previousP2 = 0;
        HashSet<Character> done = new HashSet<>();
        for (int pointer1 = 0; pointer1 != S.length(); pointer1++) {
        	
        	char currentChar = S.charAt(pointer1);
        	if (done.add(currentChar)) {
        		int lastIndexofChar = S.length() - 1;
            	while (S.charAt(lastIndexofChar) != currentChar && lastIndexofChar != pointer2) {//if we find it, or we hit the pointer2 then break
            		lastIndexofChar--;
            	}
            	if (lastIndexofChar != pointer2) {//if the while-loop ends because of we find the lastIndexofChar, not because it hit pointer2.
            		pointer2 = lastIndexofChar;
            	}
			}
        	
        	
        	if(pointer1 == pointer2) { // we find one part, go record and re-initialize pointers
        		done.clear();
        		num.add(pointer2 - previousP2 + 1);
        		previousP2 = pointer2 + 1;
        		pointer2 = -1;
        		continue;
        	}
        	if (pointer2 == S.length() - 1) {//if pointer2 hit the tail of the String, which means we done
        		num.add(pointer2 - previousP2 + 1);
        		break;
			}
        }
        return num;
    }
	/**
	 * better way
	 */
	public List<Integer> partitionLabels2(String S) {
		if (S.equals("")) return null;
        ArrayList<Integer> num = new ArrayList<>();
        // a map of char::last index, that's why this is faster than my solution
        //btw, hashmap is MUCH SLOWER than array.
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
			map.put(S.charAt(i), i);
		}
        int pointer2 = -1, previousP2 = 0;
        for (int pointer1 = 0; pointer1 < S.length(); pointer1++) {
			pointer2 = Math.max(pointer2, map.get(S.charAt(pointer1)));
			
			if (pointer2 == pointer1) {
				num.add(pointer2 - previousP2 + 1);
				previousP2 = pointer2 + 1;
			}
		}
        return num;
	}
}
