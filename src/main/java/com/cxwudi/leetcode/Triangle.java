package com.cxwudi.leetcode;

import java.util.List;
import java.util.Objects;

public class Triangle {
	public int minimumTotal(List<List<Integer>> triangle) {
		Objects.requireNonNull(triangle);
		if (triangle.isEmpty()) return 0;
		// create a new array that represent the new list after the last list in triangle, initially all 0.
		int[] results = new int[triangle.get(triangle.size() - 1).size() + 1]; 
		for (int i = triangle.size() - 1; i >= 0; i--) {
			List<Integer> levelI = triangle.get(i);
			for (int j = 0; j < levelI.size(); j++) {
				//you can overwrite the results because in this for loop, no loop back on i-1.
				results[j] = levelI.get(j) + Math.min(results[j], results[j+1]);
			}
		}
		return results[0];
		
	}
	public static void main(String[] args) {

	}

}
