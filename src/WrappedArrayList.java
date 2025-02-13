import java.util.ArrayList;

public class WrappedArrayList<T extends Comparable<T>> {
	
	private ArrayList<T> arrayList;
	private boolean needToInitialize = true;

	private void initialize() {
		arrayList = new ArrayList<T>();
	}

	private Boolean initializeIfNeeded() {
		if (needToInitialize) {
			initialize();
			needToInitialize = false;
			return true;
		}
		return false;
	}
	
	public T getItemAtIndex(int index) {
		initializeIfNeeded();
		return arrayList.get(index);
	}
	
	public void swapItems(int a, int b) {
		initializeIfNeeded();
		T first = arrayList.get(a);
		T second = arrayList.get(b);
		arrayList.set(a, second);
		arrayList.set(b, first);
	}
	
	public void setItems(ArrayList<T> items) {
		arrayList = items;
		needToInitialize = false;
	}
	
	public void set(int index, T item) {
		initializeIfNeeded();
		arrayList.set(index, item);
	}
	
	public Boolean add(T item) {
		initializeIfNeeded();
		return arrayList.add(item);
	}
	
	public ArrayList<T> getItems() {
		initializeIfNeeded();
		return arrayList;
	}
	
}
