package com.cxwudi.leetcode;

import java.util.HashMap;

/**
 * Created by CX无敌 on 2017-07-13.
 */
public class LongestSubstringwithAtLeastKRepeatingCharacters {
    public int longestSubstringOLD(String s, int k) {
        HashMap<Character,Integer> charMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++)
            charMap.put(s.charAt(i), charMap.get(s.charAt(i)) == null? 1 :charMap.get(s.charAt(i))+1);
        System.out.println(charMap);


        int index1 = 0, longest = 0;
        for (int i = 0; i < s.length(); i++) {
            index1 = charMap.get(s.charAt(i)) < k? i: index1;
            HashMap<Character,Integer> substring = new HashMap<>();
            if (i- index1 +1>= longest){
                boolean flag = true;
                for (int j = index1; j <= i ; j++)
                    substring.put(s.charAt(j), charMap.get(s.charAt(j)) == null? 1 :charMap.get(s.charAt(j))+1);
                for (Character key:substring.keySet())
                    if (substring.get(key) < k)
                        flag = false;
                if (flag)
                    longest = i- index1 +1;
            }
        }
        return longest;

    }

    public int longestSubstring(String s, int k) {
        System.out.println(s);
        if (s.equals(""))
            return 0;

        HashMap<Character,Integer> charMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            charMap.put(s.charAt(i), charMap.get(s.charAt(i)) == null? 1 :charMap.get(s.charAt(i))+1);
        }
        System.out.println(charMap);
        int i1 = 0, i2 = -1, longest = 0;
        boolean flag = false;
        for (int i = 0; i < s.length(); i++) {
            int length = 0;
            if (charMap.get(s.charAt(i)) < k){
                flag = true;
                i1 = i2+1;
                i2 = i;
                length = longestSubstring(s.substring(i1,i2),k);
            } else if (i == s.length() -1 && i2!=-1){//到头 && 到头时不是整个string---i2不在起始位置
                length = longestSubstring(s.substring(i2+1,s.length()),k);
            }
            longest = length > longest? length : longest;
        }
        
        return flag? longest : s.length();
        
    }
    public static void main(String[] args) {
        System.out.println(new LongestSubstringwithAtLeastKRepeatingCharacters().longestSubstring("abaabbcbbbacabba",3));
        System.out.println(new LongestSubstringwithAtLeastKRepeatingCharacters().longestSubstring("bbaaacbd",3));
        System.out.println(new LongestSubstringwithAtLeastKRepeatingCharacters().longestSubstring("ababbc",2));
    }
}
