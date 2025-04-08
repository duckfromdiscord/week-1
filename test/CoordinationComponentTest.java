import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.List;

public class CoordinationComponentTest {
    
    //Mock dependencies
    private static class MockDatabaseServer implements CoordinationComponent.DatabaseServerInterface {
        @Override
        public Iterable<Integer> readData(String location) {
            return List.of(1, 2, 3);
        }
        
        @Override
        public boolean writeData(String location, int[] data) {
            return true;
        }
    }
    
    private static class MockSorter implements CoordinationComponent.Sorter {
        @Override
        public int[] sort(Iterable<Integer> data) {
            return new int[] {1, 2, 3};
        }
    }
    
    //creates component with mock dependencies
    private CoordinationComponent createComponent() {
        return new CoordinationComponent(new MockDatabaseServer(), new MockSorter());
    }
    
    @Test
    public void testExecuteComputation_ValidParameters() {
        CoordinationComponent component = createComponent();
        CoordinationComponentThreading
        boolean result = component.executeComputation("user123", "validInput", "validOutput");

        boolean result = component.executeComputation("validInput", "validOutput");
        main
        assertTrue(result, "Computation should succeed with valid parameters");
    }
    
    @Test
    public void testExecuteComputation_NullInputLocation() {
        CoordinationComponent component = createComponent();
        CoordinationComponentThreading
        boolean result = component.executeComputation("user123", null, "validOutput");

        boolean result = component.executeComputation(null, "validOutput");
        main
        assertFalse(result, "Computation should fail with null inputLocation");
    }
    
    @Test
    public void testExecuteComputation_EmptyInputLocation() {
        CoordinationComponent component = createComponent();
        CoordinationComponentThreading
        boolean result = component.executeComputation("user123","", "validOutput");

        boolean result = component.executeComputation("", "validOutput");
        main
        assertFalse(result, "Computation should fail with empty inputLocation");
    }
    
    @Test
    public void testExecuteComputation_NullOutputLocation() {
        CoordinationComponent component = createComponent();
        CoordinationComponentThreading
        boolean result = component.executeComputation("user123","validInput", null);

        boolean result = component.executeComputation("validInput", null);
        main
        assertFalse(result, "Computation should fail with null outputLocation");
    }
    
    @Test
    public void testExecuteComputation_EmptyOutputLocation() {
        CoordinationComponent component = createComponent();
        CoordinationComponentThreading
        boolean result = component.executeComputation("user123","validInput", "");

        boolean result = component.executeComputation("validInput", "");
        main
        assertFalse(result, "Computation should fail with empty outputLocation");
    }
    
    @Test
    public void testExecuteBatchComputation_ValidParameters() {
        CoordinationComponent component = createComponent();
        boolean result = component.executeBatchComputation(
        CoordinationComponentThreading
        	List.of("user1","user2"),

        main
            List.of("input1", "input2"), 
            List.of("output1", "output2")
        );
        assertTrue(result, "Batch computation should succeed with valid parameters");
    }
    
    @Test
    public void testExecuteBatchComputation_MismatchedLists() {
        CoordinationComponent component = createComponent();
        boolean result = component.executeBatchComputation(
        CoordinationComponentThreading
        	List.of("user1","user2"),

        main
            List.of("input1", "input2"), 
            List.of("output1")
        );
        assertFalse(result, "Batch computation should fail with mismatched list sizes");
    }
}
