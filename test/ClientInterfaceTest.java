import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClientInterfaceTest {
	//smoke testing
	@Test
	void testTransmitUsername() {
		ClientInterface client = new ClientImplementation();
		boolean clientResult = client.transmitUsername(1);
		Assertions.assertEquals(clientResult, false);
	}
	
	@Test
	void testTransmitQuery() {
		ClientInterface client = new ClientImplementation();
		boolean clientResult = client.transmitQuery(2);
		Assertions.assertEquals(clientResult, false);
	}
	
	@Test
	void testGetFile() {
		ClientInterface client = new ClientImplementation();
		boolean clientResult = client.getFile(3);
		Assertions.assertEquals(clientResult, false);
	}

}
