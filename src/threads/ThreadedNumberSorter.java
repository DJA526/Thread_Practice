package threads;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

public class ThreadedNumberSorter {
	static int totalNums = 100;
	static int numThreads = 1;
	static int subArraySize = totalNums;

	//Complete the method below so that it uses threads to sort the integer array.
	//Try to get the completion time as short as possible.
	//Use the printArray method to check sorting accuracy
	public static void parallelSort(int[] nums) throws InterruptedException {
		//System.out.println(Arrays.toString(nums));		
		//Complete this method starting at this point
		PriorityQueue<SubArray> queue = new PriorityQueue<SubArray>();
		SubArray[] subArrays = new SubArray[numThreads];
		for (int i = 0, j = 0; i < totalNums; i += subArraySize, j++) {
			SubArray s = new SubArray(nums, i, i + subArraySize);
			subArrays[j] = s;
			s.getThread().start();
		}
		for (SubArray s : subArrays) {
			s.getThread().join();
		}
		for (SubArray s : subArrays) {
			queue.add(s);
		}
		//System.out.println(Arrays.toString(nums));
		//for (SubArray s : queue) {
		//	System.out.println(s.toString());
		//}
		int[] nums2 = new int[nums.length];
		for (int i = 0; i < nums2.length; i++) {
			if (queue.isEmpty()) {
				break;
			}
			SubArray s = queue.remove();
			nums2[i] = s.next();
			if (!s.isDone()) {
				queue.add(s);
			}
		}
		for (int i = 0; i < nums2.length; i++) {
			nums[i] = nums2[i];
		}
		
		//System.out.println(Arrays.toString(nums));
		}

	public static void main(String[] args) throws InterruptedException{
		int[] nums = new int[totalNums];

		Random randGen = new Random();
		for (int i = 0; i < totalNums; i++) {
			nums[i] = randGen.nextInt(totalNums);
		}

		printArray(nums);
		parallelSort(nums);
		printArray(nums);
	}
	
	public static void setTotalNums(int nums) {
		totalNums = nums;
		subArraySize = totalNums/numThreads;
	}
	
	public static void setNumThreads(int threads) {
		numThreads = threads;
		subArraySize = totalNums/numThreads;
	}
	
	private static void printArray(int[] nums){
		System.out.println(Arrays.toString(nums));
	}
}
