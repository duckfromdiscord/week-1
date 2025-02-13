public class PrototypeClient{
	public void prototype(ClientInterface client) {
		
		int userId = 0;
		int query = 0;
		int file = 0;
		
		//Transmit Username
		boolean transmitUsername = client.transmitUsername(userId);
		System.out.println(transmitUsername);
		
		//send query
		boolean transmitQuery = client.transmitQuery(query);
		System.out.println(transmitQuery);
		
		//return file
		boolean getFile = client.getFile(file);
		System.out.println(getFile);
		
	}
}
