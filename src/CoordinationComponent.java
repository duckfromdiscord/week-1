
public class CoordinationComponent {
	/*
	 * // error for now 
	 * private final DatabaseServerInterface databaseServer; 
	 * // get interface later 
	 * private final Sorter computeEngine;
	 * 
	 * //Dependency Injection for easy testing public
	 * CoordinationComponent(DatabaseServerInterface databaseServer, Sorter
	 * computeEngine) { this.computeEngine = computeEngine; this.databaseServer =
	 * databaseServer; }
	 */
	
	public boolean executeComputation(String inputLocation, String outputLocation) {
		try {
			//read database data
			Iterable<Integer> inputData = readFromStorage(inputLocation);
			
			//process data with compute engine
			int[] results = processdata(inputData);
			
			//write results
			return writeToStorage(outputLocation, results);
		} catch (Exception e) {
			System.err.println("Error occured:" + e.getMessage());
			return false;
		}
	}
	
	private boolean writeToStorage(String outputLocation, int[] results) {
		for(int i = 0; i<results.length ; i++) {
			System.out.println(results[i]);
		}
		return true;
	}
	
	//would return int[]
	private void processdata(Iterable<Integer> inputData) {
		System.out.println(inputData);
	}
	
	//would return Iterable<Integer>
	private void readFromStorage(String inputLocation) {
		System.out.println(inputLocation);
	}
	
}
