import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestDatabaseAPI {
	@Test 
	public void testAPIs() {
		DatabaseServer server = new DatabaseServer(new LoginRequest("Evan"));
		boolean storeUserData = server.storeUserData("src/data.csv");
		Assertions.assertEquals(storeUserData, true);
		
		Assertions.assertEquals(server.processData(0), true); //New Integration test 2.27.2025. Tests methods implemented in DatabaseServer.
		
		
		LoginRequest mockRequest = Mockito.mock(LoginRequest.class);
		LoginAttempt attempt = new LoginAttempt(mockRequest);
		String userID = attempt.getUserID();
		Assertions.assertEquals(userID, null);
		
	}

}