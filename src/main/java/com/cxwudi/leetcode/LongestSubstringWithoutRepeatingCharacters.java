package main.java.com.cxwudi.leetcode;

import java.util.*;

/**
 * Created by CX无敌 on 2017-06-30.
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        ArrayList<HashSet<Character>> subStringList = new ArrayList<>();
        int longest = 0;
        for (int i = 0; i < s.length(); i++) {
            HashSet<Character> hhh = new HashSet<>();
            hhh.add(s.charAt(i));
            subStringList.add(hhh);
            for (int j = 0; j < subStringList.size();j++) {
                longest = subStringList.get(j).size() > longest? subStringList.get(j).size() : longest;

                if (!subStringList.get(j).equals(hhh) //不是刚加进去的hashset
                        && !subStringList.get(j).contains('\n') //不是已经fail的hashset
                        && !subStringList.get(j).add(s.charAt(i))//尝试加入新的char
                ) {
                    subStringList.get(j).add('\n');//如果失败，加入fail标记---\n
                }
                if (subStringList.get(j).size() < longest && subStringList.get(j).contains('\n')){
                    subStringList.remove(j);//清除掉绝对不可能被选为最长substring的hashset
                    j--;
                }
            }
            //System.out.println(subStringList);
        }
        longest = 0;
        for (HashSet set: subStringList) {
            set.remove('\n');
            longest = set.size() > longest ? set.size() : longest;
        }
        return longest;
    }

    public int lengthOfLongestSubstring2(String s) {
        int longest = 0, index1 = 0, index2 = 0;
        HashMap<Character,Integer> charList = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            index1++;
            Integer lastOne = charList.put(s.charAt(i),i);
            System.out.println(charList);
            if (lastOne != null )
                index2 = index2 > lastOne ? index2 :lastOne + 1;
            longest = index1 - index2 > longest ? index1 - index2 : longest;
            System.out.println(index1 + " - " + index2);
        }
        return longest;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters b = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println(b.lengthOfLongestSubstring("asdasdss"));
        System.out.println(b.lengthOfLongestSubstring("dvdf"));
        System.out.println(b.lengthOfLongestSubstring("pwwekwp"));
        System.out.println(b.lengthOfLongestSubstring("c"));
    }
}
