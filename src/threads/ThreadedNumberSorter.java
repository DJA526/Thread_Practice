package threads;

import java.util.Arrays;
import java.util.Random;

public class ThreadedNumberSorter {
	static final int TOTAL_NUMS = 11;

	//Complete the method below so that it uses threads to sort the integer array.
	//Try to get the completion time as short as possible.
	//Use the printArray method to check sorting accuracy
	public static void parallelSort(int[] nums) throws InterruptedException {
		long startTime = System.nanoTime();
		//Complete this method starting at this point
		int[] nums1 = new int[(nums.length)/2];
		int[] nums2 = new int[nums.length - (nums.length)/2];
		Thread t1 = new Thread(()->{
			for (int i = 0; i < (nums.length)/2; i++) {
				nums1[i] = nums[i];
			}
			Arrays.sort(nums1);
			System.out.println("Array 1:");
			printArray(nums1);
		});
		t1.start();
		Thread t2 = new Thread(()->{
			for (int i = (nums.length)/2; i < nums.length; i++) {
				nums2[i-(nums.length)/2] = nums[i];
			}
			Arrays.sort(nums2);
			System.out.println("Array 2:");
			printArray(nums2);
		});
		t2.start();
		t1.join();
		t2.join();
		int i = 0;
		int j = 0;
		for (int k = 0; k < nums.length; k++) {
			if (i == nums1.length) {
				nums[k] = nums2[j];
				j++;
			} else if (j == nums2.length) {
				nums[k] = nums1[i];
				i++;
			} else if (nums1[i] < nums2[j]) {
				nums[k] = nums1[i];
				i++;
			} else {
				nums[k] = nums2[j];
				j++;
			}
		}
		long totalTime = System.nanoTime() - startTime;
		double timeInSeconds = (double)totalTime / 1_000_000_000;
		System.out.println("The total computing time in seconds was: " + timeInSeconds);
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
		for(int i = 0; i < nums.length; i++){
			System.out.println(nums[i]);
		}
	}
}
