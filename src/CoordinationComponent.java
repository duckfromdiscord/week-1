import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CoordinationComponent {
    // Thread synchronization mechanism
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    
    // Uncommented dependencies
    private final DatabaseServerInterface databaseServer;
    private final Sorter computeEngine;
    
    // Dependency Injection for easy testing
    public CoordinationComponent(DatabaseServerInterface databaseServer, Sorter computeEngine) {
        this.computeEngine = computeEngine;
        this.databaseServer = databaseServer;
    }
    
    public boolean executeComputation(String inputLocation, String outputLocation) {
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
            
            // Validate input data
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
            return writeToStorage(outputLocation, results);
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
     * Executes multiple computations concurrently
     * @param inputLocations List of input locations
     * @param outputLocations List of output locations
     * @return true if all computations succeeded, false otherwise
     */
    
    public boolean executeBatchComputation(List<String> inputLocations, List<String> outputLocations) {
        if (inputLocations == null || outputLocations == null) {
            logError("Input and output location lists cannot be null");
            return false;
        }
        
        if (inputLocations.size() != outputLocations.size()) {
            logError("Input and output location lists must be the same size");
            return false;
        }
        
        // Process all computations
        boolean allSucceeded = true;
        for (int i = 0; i < inputLocations.size(); i++) {
            allSucceeded &= executeComputation(inputLocations.get(i), outputLocations.get(i));
        }
        return allSucceeded;
    }
    
    protected boolean writeToStorage(String outputLocation, int[] results) {
        lock.writeLock().lock();
        try {
            if (results == null) {
                logError("Error: cannot write null to storage");
                return false;
            }
            
            try {
                // Use the database server to write data
                return databaseServer.writeData(outputLocation, results);
            } catch (Exception e) {
                logError("Error writing to storage: " + e.getMessage());
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
            // Use the compute engine to process data
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
                // Use the database server to read data
                return databaseServer.readData(inputLocation);
            } catch (Exception e) {
                logError("Error reading from storage: " + e.getMessage());
                throw new RuntimeException("Failed to read from storage", e);
            }
        } finally {
            lock.readLock().unlock();
        }
    }
    
     //Thread-safe error logging
    private synchronized void logError(String message) {
        System.err.println(message);
    }
    
     //Thread-safe info logging
    private synchronized void logInfo(String message) {
        System.out.println(message);
    }
    

     //Interface for database server operations
    public interface DatabaseServerInterface {
        Iterable<Integer> readData(String location) throws Exception;
        boolean writeData(String location, int[] data) throws Exception;
    }
    
    /**
     * Interface for computation engine
     */
    public interface Sorter {
        int[] sort(Iterable<Integer> data) throws Exception;
    }
}
