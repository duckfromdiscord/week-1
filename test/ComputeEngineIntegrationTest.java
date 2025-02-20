
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ComputeEngineIntegrationTest {
	
	private ArrayList<Integer> slowSort(ArrayList<Integer> inputData) {
		inputData.sort((e1, e2) -> e1.compareTo(e2));
		return inputData;
	}
	
	@Test
	public void computeEngineIntegrationTest() {
		
		DatabaseMemoryImplementation inMemoryDB = new DatabaseMemoryImplementation();
		TestInputConfig initialInput = new TestInputConfig();
		// TODO: inMemoryDB should take the initialInput
		
		ArrayList<Integer> inputData = new ArrayList<Integer>();
		inputData.addAll(initialInput.getInputData());
		
		// Create our sorter
		BozoSorter bozoSorter = new BozoSorter();
		bozoSorter.setRandomSeed(1);
		
		int index = inputData.get(0);
		inputData.remove(0);
		WrappedArrayList<Integer> wrappedData = new WrappedArrayList<Integer>();
		wrappedData.setItems(inputData);
		bozoSorter.setData(wrappedData, index);
		bozoSorter.sortUntilFinished();
		
		Assertions.assertEquals(bozoSorter.getData().getItemAtIndex(index), slowSort(inputData).get(index));
		
	}
	
}
