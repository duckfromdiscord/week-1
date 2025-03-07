import java.util.List;

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
		if(inputLocation == null || inputLocation.trim().isEmpty()) {
			System.err.println("Error: inputLocation cannot be null or empty");
			return false;
		}
		
		if(outputLocation == null || outputLocation.trim().isEmpty()) {
			System.err.println("Error: outputLocation cannot be null or empty");
			return false;
		}
		
		try {
			//read database data
			Iterable<Integer> inputData = readFromStorage(inputLocation);
			
			//Validate input data
			if(inputData == null) {
				System.err.println("Error: Failed to read data from " + inputLocation);
				return false;
			}
			
			//process data with compute engine
			int[] results = processdata(inputData);
			
			if (results == null) {
				System.err.println("Error: Failed to process data");
				return false;
			}
			
			//write results
			return writeToStorage(outputLocation, results);
		} catch (RuntimeException e) {
			//Runtime Exceptions
			System.err.println("Runtime Error occured:" + e.getMessage());
			e.printStackTrace(); //debugging
			return false;
		} catch (Exception e) {
			//Regular Exceptions
			System.err.println("Error occured:" + e.getMessage());
			return false;
		}
	}
	
	protected boolean writeToStorage(String outputLocation, int[] results) {
		if(results == null) {
			System.err.println("Error: cannot write null to storage");
			return false;
		}
		
		try {
			for(int i = 0; i < results.length; i++) {
				System.out.println(results[i]);
			}
			return true;
		} catch (Exception e) {
			System.err.println("Error writing to storage: " + e.getMessage());
			return false;
		}
		
	}
	

	protected int[] processdata(Iterable<Integer> inputData) {
		if(inputData == null) {
			throw new IllegalArgumentException("Input data cannot be null");
		}
		
		try {
			System.err.println(inputData);
			//would be actual data but is dummy for now
			return new int[] {1,2,3};
		} catch (Exception e) {
			System.err.println("Error processing data: " + e.getMessage());
			throw new RuntimeException("Failed to process data", e);
		}
	}
	
	protected Iterable<Integer> readFromStorage(String inputLocation) {
		if(inputLocation == null) {
			throw new IllegalArgumentException("Input Loaction cannot be null");
		}
		
		try {
			System.out.println(inputLocation);
			//would be actual data but is dummy for now
			return List.of(1, 2, 3);
		} catch (Exception e) {
			System.err.println("Error reading from storage: " + e.getMessage());
			throw new RuntimeException("Failed to read from storage", e);
		}
	}
	
}
