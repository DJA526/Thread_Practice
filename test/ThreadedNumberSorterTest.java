import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;

import threads.ThreadedNumberSorter;

class ThreadedNumberSorterTest {

	@Test
	void testParallelSort() throws InterruptedException {
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

}
