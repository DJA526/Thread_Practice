package threads;

import java.util.Arrays;

public class SubArray implements Comparable<SubArray> {

	private final int[] fullArray;
	private final int start;
	private int index;
	private final int end;
	private boolean done = false;
	private Thread thread;

	SubArray(int[] fullArray, int start, int end) {
		this.fullArray = fullArray;
		this.index = start;
		this.start = start;
		this.end = end;
		thread = new Thread(()->Arrays.sort(fullArray, start, end));
	}

	public Thread getThread() {
		return thread;
	}

	@Override
	public int compareTo(SubArray o) {
		return fullArray[index] - o.fullArray[o.index];
	}

	int next() {
		int i = fullArray[index];
		index++;
		if (index >= end) {
			done = true;
		}
		return i;
	}

	boolean isDone() {
		return done;
	}

	@Override
	public String toString() {
		int[] subArray = new int[end - start];
		for (int i = 0; i < subArray.length; i++) {
			subArray[i] = fullArray[i + start];
		}
		return Arrays.toString(subArray);
	}

}