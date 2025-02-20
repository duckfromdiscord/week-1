import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestDatabaseAPI {
	@Test 
	public void testAPIs() {
		DatabaseServer server = new DatabaseServer();
		boolean storeUserData = server.storeUserData();
		Assertions.assertEquals(storeUserData, false);
		
		
		LoginRequest mockRequest = Mockito.mock(LoginRequest.class);
		LoginAttempt attempt = new LoginAttempt(mockRequest);
		String userID = attempt.getUserID();
		Assertions.assertEquals(userID, "user");
		
	}

}