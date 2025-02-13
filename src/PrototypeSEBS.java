import java.util.Scanner;
@test
public class PrototypeSEBS {
	public void prototype(DatabaseSeverInterface server) {
		//Sign user in
		Scanner sc = new Scanner(System.in);
		String username = sc.next();
		LoginAttempt login = server.login(new LoginRequest(username));
		if(login.isSuccessful())
			System.out.println("Success");
		else
			System.out.println("Fail");
		
		//Accept file from user, import it into database
		boolean storeUserDataSuccess = server.storeUserData();
		System.out.println(storeUserDataSuccess);
		
		//Transmit chosen data to computation server
		int chosenDataID = 0;
		boolean processDataSuccess = server.processData(chosenDataID);
		System.out.println(processDataSuccess);
		
		//Receive data from computation server, put it into database
		int serverDataID = sc.nextInt();
		boolean storeSortedDataSuccess = server.storeSortedData(serverDataID);
		System.out.println(storeSortedDataSuccess);
		
		//Return info to client in the form of a BSV file, both the raw and sorted data
		int chosenDataType = sc.nextInt();
		boolean returnUserDataSuccess = server.returnUserData(chosenDataID, chosenDataType);
		System.out.println(returnUserDataSuccess);
		sc.close();
	}
}
