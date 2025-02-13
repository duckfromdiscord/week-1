import project.annotations.ConceptualAPIPrototype;

public class SorterPrototype {
	
	@ConceptualAPIPrototype
	public void prototype(Sorter<Integer> sorter) {
		// Initialize a sortable list
		WrappedArrayList<Integer> ints = new WrappedArrayList<Integer>();
		
		// Add some integers
		System.out.println("Sorting ints: ");
		for (int i = 5; i > 0; i--) {
			ints.add(i);
			System.out.print(i + ", ");
		}
		System.out.println();
		
		Integer indexToGet = 2;
		
		// Sort until sorting is complete
		sorter.sortUntilFinished();
		
		WrappedArrayList<Integer> result = sorter.getData();
		Integer indexResult = result.getItemAtIndex(indexToGet);
		
		System.out.println("Result at index " + indexToGet + ": 2");
		
	}
	
}