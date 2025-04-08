import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CoordinationComponent {
    // Thread synchronized
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    
    // Uncommented dependencies, previously abstracted
    private final DatabaseServerInterface databaseServer;
    private final Sorter computeEngine;
   
    public CoordinationComponent(DatabaseServerInterface databaseServer, Sorter computeEngine) {
        this.computeEngine = computeEngine;
        this.databaseServer = databaseServer;
    }
    
    public boolean executeComputation(String userId, String inputLocation, String outputLocation) {
    	if (userId == null || userId.trim().isEmpty()) {
            logError("Error: userId cannot be null or empty");
            return false;
        }
    	
        if (inputLocation == null || inputLocation.trim().isEmpty()) {
            logError("Error: inputLocation cannot be null or empty");
            return false;
        }
        
        if (outputLocation == null || outputLocation.trim().isEmpty()) {
            logError("Error: outputLocation cannot be null or empty");
            return false;
        }
        
        try {
            // Read database data
            Iterable<Integer> inputData = readFromStorage(inputLocation);
            
            // Validate data
            if (inputData == null) {
                logError("Error: Failed to read data from " + inputLocation);
                return false;
            }
            
            // Process data with compute engine
            int[] results = processData(inputData);
            
            if (results == null) {
                logError("Error: Failed to process data");
                return false;
            }
            
            // Write results
            return writeToStorage(outputLocation, results, userId);
        } catch (RuntimeException e) {
        	
            // Runtime Exceptions
            logError("Runtime Error occurred: " + e.getMessage());
            e.printStackTrace(); // For debugging
            return false;
        } catch (Exception e) {
            // Regular Exceptions
            logError("Error occurred: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Multi-thread implementation
     */
    
    public boolean executeBatchComputation(List<String>userIds, List<String> inputLocations, List<String> outputLocations) {
        
    	if (userIds == null || inputLocations == null || outputLocations == null) {
            logError("Input and output location lists cannot be null");
            return false;
        }
        
        if (inputLocations.size() != outputLocations.size() || userIds.size() != inputLocations.size()) {
            logError("Input and output location lists must be the same size");
            return false;
        }
        
        // Process computations
        boolean allSucceeded = true;
        for (int i = 0; i < inputLocations.size(); i++) {
            allSucceeded &= executeComputation(userIds.get(i), inputLocations.get(i), outputLocations.get(i));
        }
        return allSucceeded;
    }
    
    protected boolean writeToStorage(String outputLocation, int[] results, String userId) {
        lock.writeLock().lock();
        try {
            if (results == null) {
                logError("[" + userId + "]Error: cannot write null to storage");
                return false;
            }
            
            try {
                // database writes data
                return databaseServer.writeData(outputLocation, results);
            } catch (Exception e) {
                logError("[" + userId + "]Error writing to storage: " + e.getMessage());
                return false;
            }
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    protected int[] processData(Iterable<Integer> inputData) {
        if (inputData == null) {
            throw new IllegalArgumentException("Input data cannot be null");
        }
        
        try {
            // engine process data
            return computeEngine.sort(inputData);
        } catch (Exception e) {
            logError("Error processing data: " + e.getMessage());
            throw new RuntimeException("Failed to process data", e);
        }
    }
    
    protected Iterable<Integer> readFromStorage(String inputLocation) {
        lock.readLock().lock();
        try {
            if (inputLocation == null) {
                throw new IllegalArgumentException("Input Location cannot be null");
            }
            
            try {
                // database server read data
                return databaseServer.readData(inputLocation);
            } catch (Exception e) {
                logError("Error reading from storage: " + e.getMessage());
                throw new RuntimeException("Failed to read from storage", e);
            }
        } finally {
            lock.readLock().unlock();
        }
    }
    
     //Thread protections
    
     //error logging
    private synchronized void logError(String message) {
        System.err.println(message);
    }
    
     //info logging
    private synchronized void logInfo(String message) {
        System.out.println(message);
    }
    

     /*abstracted example database interfaces for now
      *will connect components later
      */
     
    public interface DatabaseServerInterface {
        Iterable<Integer> readData(String location) throws Exception;
        boolean writeData(String location, int[] data) throws Exception;
    }
    
    /*abstracted example sorter interface for now
     * will connect components later
     */
    public interface Sorter {
        int[] sort(Iterable<Integer> data) throws Exception;
    }
}
