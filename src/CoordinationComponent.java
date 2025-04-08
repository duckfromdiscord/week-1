import java.util.List;

public class CoordinationComponent {

	 private final DatabaseServerInterface databaseServer; 
	 // get interface later 
	 private final Sorter computeEngine;
	 
	 //Dependency Injection for easy testing public
	 public CoordinationComponent(DatabaseServerInterface databaseServer, Sorter computeEngine) { 
		 this.computeEngine = computeEngine; 
		 this.databaseServer = databaseServer; 
	}
	
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
	
	protected int[] processdata(Iterable<Integer> inputData) throws InterruptedException, ExecutionException {
	    List<Integer> inputList = new ArrayList<>();
	    inputData.forEach(inputList::add);
	    
	    int threadCount = 4;
	    ExecutorService executor = Executors.newFixedThreadPool(threadCount);
	    List<Future<int[]>> futures = new ArrayList<>();
	    
	    int chunkSize = (int) Math.ceil(inputList.size() / (double) threadCount);
	    for (int i = 0; i < inputList.size(); i += chunkSize) {
	        List<Integer> chunk = inputList.subList(i, Math.min(i + chunkSize, inputList.size()));
	        futures.add(executor.submit(() -> {
	            // Placeholder for actual computation logic, e.g.:
	            return computeEngine.sort(chunk);
	        }));
	    }

	    List<Integer> result = new ArrayList<>();
	    for (Future<int[]> future : futures) {
	        int[] partial = future.get();
	        for (int num : partial) result.add(num);
	    }

	    executor.shutdown();

	    // Convert List<Integer> back to array
	    return result.stream().mapToInt(i -> i).toArray();
	}

	

	protected int[] processdata(Iterable<Integer> inputData) {
		List<Integer> inputList = new ArrayList<>();
		inputData.forEach(inputList::add);
		
		int threadCount = 4;
		ExecutorService executor = 
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
