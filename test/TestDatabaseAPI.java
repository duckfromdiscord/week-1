import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestDatabaseAPI {
	@Test 
	public void testAPIs() {
		DatabaseServer server = null; 
		//Need to declare this outside, otherwise it would be local to the catch block
		
		//Error Logic Integration Test
		try {
			server = new DatabaseServer(new LoginRequest("Evan"));
			System.out.println("LoginRequest Created: " + server.getUserID());
		} catch (IllegalArgumentException e) { //Handles the exception to make troubleshooting easier for the client
			System.err.println("Failed to create request: " + e);
		}
		
		boolean storeUserData = server.storeUserData("src/data.csv");
		Assertions.assertEquals(storeUserData, true);
		
		Assertions.assertEquals(server.processData(0), true); //New Integration test
		
		
		LoginRequest mockRequest = Mockito.mock(LoginRequest.class);
		LoginAttempt attempt = new LoginAttempt(mockRequest);
		String userID = attempt.getUserID();
		Assertions.assertEquals(userID, null);
		
	}

}