import java.util.Random;

public class BozoSorter implements Sorter<Integer> {

	private WrappedArrayList<Integer> data;
	private DatabaseServerInterface database;
	private Random random;
	
	private boolean swapItems(int item1, int item2) {
		int data1 = data.getItemAtIndex(item1);
		int data2 = data.getItemAtIndex(item2);
		data.set(item1, data2);
		data.set(item2, data1);
		return true;
	}
	
	@Override
	public boolean step() {
		int item1 = random.nextInt(0, data.size());
		int item2 = random.nextInt(0, data.size());
		return this.swapItems(item1, item2);
	}

	@Override
	public boolean checkSorted() {
		// Edge case: if there are no items the array is sorted
		if (data.size() < 1) {
			return true;
		}
		
		// since we checked, this should never be null
		int lastValue = data.getItemAtIndex(0);
		
		for (int i = 1; i < data.size(); i++) {
			// check if ascending
			if (data.getItemAtIndex(i) < lastValue) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public boolean setData(WrappedArrayList<Integer> data, int index) {
		this.data = data;
		return true;
	}

	@Override
	public WrappedArrayList<Integer> getData() {
		return this.data;
	}

	@Override
	public boolean sortUntilFinished() {
		while (!this.checkSorted()) {
			this.step();
		}
		return this.checkSorted();
	}

	@Override
	public boolean setRandomSeed(long seed) {
		this.random = new Random();
		this.random.setSeed(seed);
		return true;
	}

}
