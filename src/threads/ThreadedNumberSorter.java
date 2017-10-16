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
		Thread[] threads = new Thread[NUM_THREADS];
		PriorityQueue<SubArray> queue = new PriorityQueue<SubArray>();
		for (int i = 0, j = 0; i < TOTAL_NUMS; i += subArraySize, j++) {
			final int iFinal = i;
			threads[j] = new Thread(()->{
				int[] subArray = sort(nums, iFinal, iFinal + subArraySize);
				SubArray s = new SubArray(subArray, 0, subArraySize);
				queue.add(s);
			});
			threads[j].start();
		}
		for (int j = 0; j < threads.length; j++) {
			threads[j].join();
		}
		for (int i = 0; i < nums.length; i++) {
			if (queue.isEmpty()) {
				break;
			}
			SubArray s = queue.remove();
			nums[i] = s.next();
			if (!s.isDone()) {
				queue.add(s);
			}
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
//		int[] nums = new int[TOTAL_NUMS];
		int[] nums = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

//		Random randGen = new Random();
//		for (int i = 0; i < TOTAL_NUMS; i++) {
//			nums[i] = randGen.nextInt(TOTAL_NUMS);
//		}

		printArray(nums);
		parallelSort(nums);
		printArray(nums);
	}
	
	private static void printArray(int[] nums){
		for(int i = 0; i < nums.length; i++){
			System.out.println(nums[i]);
		}
	}
}
