package threads;

public class SubArray implements Comparable<SubArray>{
	
	private int[] fullArray;
	private int index;
	private int end;
	private boolean done = false;
	
	SubArray(int[] fullArray, int start, int end) {
		this.fullArray = fullArray;
		this.index =start;
		this.end = end;
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

}