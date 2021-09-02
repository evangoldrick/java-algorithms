package miscAlgorithms;

import java.util.ArrayList;

public class CanSum {

	/**
	 * Computes if there is a way to sum the numbers in the numbers array such that
	 * they sum to targetSum. Numbers can be used more than once. All numbers are
	 * non-negative
	 * 
	 * @param targetSum the target sum
	 * @param numbers array of numbers to be used in the sum
	 * @return true if there is a way to sum the numbers, false otherwise
	 */
	public static boolean canSum(int targetSum, int[] numbers) {
		if (targetSum < 0)
			return false;

		for (int i = 0; i < numbers.length; ++i) {
			if (targetSum == numbers[i])
				return true;
			else if (canSum(targetSum - numbers[i], numbers))
				return true;
		}
		return false;
	}

	public static ArrayList<Integer> howSum(int targetSum, int[] numbers) {
		if (targetSum == 0)
			return new ArrayList<Integer>();
		else if (targetSum < 0)
			return null;
		
		for (int num : numbers) {
			ArrayList<Integer> currentArray = howSum(targetSum - num, numbers);
			if (currentArray != null) {
				currentArray.add(num);
				return currentArray;
			}
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		int[] nums = {3, 11};
		System.out.println(canSum(101, nums));
		
		System.out.println(howSum(101, nums));
		
	}

}
