package leetcode;

import java.util.Arrays;
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
			//int[] currentResult = new int[levelI.size()];
			for (int j = 0; j < levelI.size(); j++) {
				results[j] = levelI.get(j) + Math.min(results[j], results[j+1]);
			}
			//results = currentResult;
		}
		return results[0];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
