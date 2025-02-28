import java.util.Scanner;

import project.annotations.ProcessAPIPrototype;

public class DatabaseServerPrototype {

	@ProcessAPIPrototype
	public void prototype(DatabaseServerInterface server) {
		//Sign user in
		Scanner sc = new Scanner(System.in);
		String username = sc.next();
		DatabaseServer theServer = new DatabaseServer(new LoginRequest(username));

		
		//Accept file from user, import it into database
		boolean storeUserDataSuccess = theServer.storeUserData("src/data.csv");
		System.out.println(storeUserDataSuccess);
		
		//Transmit chosen data to computation server
		int chosenDataID = 0;
		boolean processDataSuccess = theServer.processData(chosenDataID);
		System.out.println(processDataSuccess);
		
		//Receive data from computation server, put it into database
		int serverDataID = sc.nextInt();
		boolean storeSortedDataSuccess = theServer.storeSortedData(serverDataID);
		System.out.println(storeSortedDataSuccess);
		
		//Return info to client in the form of a BSV file, both the raw and sorted data
		int chosenDataType = sc.nextInt();
		boolean returnUserDataSuccess = theServer.returnUserData(chosenDataID, chosenDataType);
		System.out.println(returnUserDataSuccess);
		sc.close();
	}
}
