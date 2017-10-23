package threads;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

public class ThreadedNumberSorter {
	static final int TOTAL_NUMS = 10;
	static final int NUM_THREADS = 2;
	static final int subArraySize = TOTAL_NUMS/NUM_THREADS;

	//Complete the method below so that it uses threads to sort the integer array.
	//Try to get the completion time as short as possible.
	//Use the printArray method to check sorting accuracy
	public static void parallelSort(int[] nums) throws InterruptedException {
		long startTime = System.nanoTime();
		//Complete this method starting at this point
		PriorityQueue<SubArray> queue = new PriorityQueue<SubArray>();
		for (int i = 0, j = 0; i < TOTAL_NUMS; i += subArraySize, j++) {
			SubArray s = new SubArray(nums, i, i + subArraySize);
			queue.add(s);
			s.getThread().start();
		}
		for (SubArray s : queue) {
			s.getThread().join();
		}
		System.out.println(Arrays.toString(nums));
		for (SubArray s : queue) {
			System.out.println(s.toString());
		}
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
		
		long totalTime = System.nanoTime() - startTime;
		double timeInSeconds = (double)totalTime / 1_000_000_000;
		System.out.println("The total computing time in seconds was: " + timeInSeconds);
	}

	private static int[] sort(int[] nums, int start, int end) {
		int[] nums2 = new int[end - start + 1];
		for (int i = start; i < end; i++) {
			nums2[i - start] = nums[i];
		}
		Arrays.sort(nums2);
		return nums2;
	}

	public static void main(String[] args) throws InterruptedException{
		int[] nums = new int[TOTAL_NUMS];

		Random randGen = new Random();
		for (int i = 0; i < TOTAL_NUMS; i++) {
			nums[i] = randGen.nextInt(TOTAL_NUMS);
		}

		printArray(nums);
		parallelSort(nums);
		printArray(nums);
	}
	
	private static void printArray(int[] nums){
		System.out.println(Arrays.toString(nums));
	}
}
