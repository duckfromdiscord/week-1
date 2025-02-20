import project.annotations.ProcessAPI;

@ProcessAPI
public interface DatabaseSeverInterface {
	
	boolean storeUserData();
	
	LoginAttemptInterface login(LoginRequest loginRequest);

	boolean processData(int chosenDataID);

	boolean storeSortedData(int dataID);

	boolean returnUserData(int chosenDataID, int chosenDataType);

	

}
