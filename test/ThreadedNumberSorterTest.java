import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;

import threads.ThreadedNumberSorter;

class ThreadedNumberSorterTest {

	@Test
	void testParallelSort() throws InterruptedException {
		ThreadedNumberSorter.setTotalNums(10);
		ThreadedNumberSorter.setNumThreads(2);
		
		int[] nums = new int[10];
		int[] nums2 = new int[10];

		Random randGen = new Random();
		for (int i = 0; i < 10; i++) {
			nums[i] = randGen.nextInt(10);
		}
		
		for (int i = 0; i < nums2.length; i++) {
			nums2[i] = nums[i];
		}
		
		Arrays.sort(nums);
		ThreadedNumberSorter.parallelSort(nums2);
		
		if (!Arrays.equals(nums, nums2)) {
			fail("Arrays not equal");
		}
		assertTrue(Arrays.equals(nums, nums2));
	}
	
	@Test
	void testNumThreads() throws InterruptedException {
		ThreadedNumberSorter.setTotalNums(100000);	
		normalSort();
		tryNumThreads(2);
		tryNumThreads(4);
		/*tryNumThreads(10);
		tryNumThreads(25);
		tryNumThreads(50);
		tryNumThreads(100);
		tryNumThreads(500);
		tryNumThreads(1000);
		tryNumThreads(5000);
		tryNumThreads(10000);*/
	}

	private void tryNumThreads(int threads) throws InterruptedException {
		ThreadedNumberSorter.setNumThreads(threads);
		double total = 0.0;
		double sqrTotal = 0.0;
		for (int i = 0; i < 100; i++) {
			int[] nums = new int[100000];
			Random randGen = new Random();
			for (int j = 0; j < 100000; j++) {
				nums[j] = randGen.nextInt(100000);
			}
			long startTime = System.nanoTime();
			ThreadedNumberSorter.parallelSort(nums);
			long totalTime = System.nanoTime() - startTime;
			double timeInSeconds = (double)totalTime / 1_000_000_000;
			total += timeInSeconds;
			sqrTotal += timeInSeconds*timeInSeconds;
		}
		double mean = total/100;
		double sd = Math.sqrt(sqrTotal/100 - mean*mean);
		System.out.println("Time for " + threads + " threads:");
		System.out.println("Mean: " + mean);
		System.out.println("Standard deviation: " + sd);
	}
	
	private void normalSort() {
		double total = 0.0;
		double sqrTotal = 0.0;
		for (int i = 0; i < 100; i++) {
			int[] nums = new int[100000];
			Random randGen = new Random();
			for (int j = 0; j < 100000; j++) {
				nums[j] = randGen.nextInt(100000);
			}
			long startTime = System.nanoTime();
			Arrays.sort(nums);
			long totalTime = System.nanoTime() - startTime;
			double timeInSeconds = (double)totalTime / 1_000_000_000;
			total += timeInSeconds;
			sqrTotal += timeInSeconds*timeInSeconds;
		}
		double mean = total/100;
		double sd = Math.sqrt(sqrTotal/100 - mean*mean);
		System.out.println("Time for normal Array sort:");
		System.out.println("Mean: " + mean);
		System.out.println("Standard deviation: " + sd);
	}

}
