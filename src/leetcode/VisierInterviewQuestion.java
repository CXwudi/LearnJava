package leetcode;

import java.util.Arrays;

public class VisierInterviewQuestion {
	//input: a list of int (0 - max), 
	//for each value in the list, I calculate the product of every other value in the list
	/*
	 * ex: [1,2,3] -> [6, 3, 2]
	 */
	public int[] question(int[] input) {
		if (input.length == 0) {
			return new int[0];
		} else if (input.length == 1) {
			return input;
		}
		int zeroCount = 0, totalWithoutZero = 1, firstZeroIndex = -1;
		boolean findFirstZero = false;
		for (int i = 0; i < input.length; i++) {
			if (input[i] == 0) {
				zeroCount++;
				if (!findFirstZero) {
					firstZeroIndex = i;
					findFirstZero = true;
				}
			} else {
				totalWithoutZero *= input[i];
			}
		}
		if (zeroCount >= 2) {
			int[] output = new int[input.length];
			Arrays.fill(output, 0);
			return output;
		}
		
		else if (zeroCount == 1) {
			int[] output = new int[input.length];
			Arrays.fill(output, 0);
			output[firstZeroIndex] = totalWithoutZero;
			return output;
		}
		
		else if(zeroCount == 0) {
			int[] output = new int[input.length];
			for (int i = 0; i < output.length; i++) {
				output[i] = totalWithoutZero / input[i];
			}
			return output;
		} else {
			System.err.println("this shouldn't happen");
			return null;
		}
	}
}
