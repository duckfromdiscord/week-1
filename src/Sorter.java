import project.annotations.ConceptualAPI;

@ConceptualAPI
public interface Sorter<T extends Comparable<T>> {
	
	// Will contain bozo sort logic
	boolean step();
	
	boolean checkSorted();
	
	boolean setRandomSeed(long seed);
	
	boolean setData(WrappedArrayList<T> data, int index);
	
	WrappedArrayList<T> getData();
	
	boolean sortUntilFinished();
	
}