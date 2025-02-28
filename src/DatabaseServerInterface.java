import project.annotations.ProcessAPI;

@ProcessAPI
public interface DatabaseServerInterface {
	
	LoginAttemptInterface loginMethod(LoginRequest loginRequest);

	boolean processData(int chosenDataID);

	boolean storeSortedData(int dataID);

	boolean returnUserData(int chosenDataID, int chosenDataType);
	
	boolean wipeUserData();

	boolean storeUserData(String fileName);

	

}
