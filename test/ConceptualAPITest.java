
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConceptualAPITest {
	
	@Test
	public void smokeTest() {
		
		// Create our sorter
		// TODO: add a random seed option
		BozoSorter bozoSorter = new BozoSorter();
		
		// Make a list (out of order) of two items
		WrappedArrayList<Integer> list = new WrappedArrayList<Integer>();
		list.add(2);
		list.add(1);
		
		// Provide the list to our sorter
		bozoSorter.setData(list, 0);
		
		// Store our sorting step results
		ArrayList<Integer> firstIndexResults = new ArrayList<Integer>();
		
		// Do some random tests
		for (int i = 0; i < 100; i++) {
			bozoSorter.step();
			firstIndexResults.add(bozoSorter.getData().getItemAtIndex(0));
			if (bozoSorter.getData().getItemAtIndex(0) == 1) {
				// The list is sorted, make sure the API agrees
				Assertions.assertEquals(bozoSorter.checkSorted(), true);
			}
			
		}
		
		// Make sure the step changes the content of the list
		Assertions.assertEquals(firstIndexResults.contains(1), true);
		
	}
	
}
