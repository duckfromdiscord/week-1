//Create a login request, then pass it into loginattempt
//ex: LoginRequest (name) = new LoginRequest(name);
//	  DatabaseServer.login(name of LoginRequest object)
public class LoginRequest {
	private final String userID;
	
	public LoginRequest(String userID) {
		if(userID == null) { //Checking for usernames that are either blank, only spaces, or null
			throw new IllegalArgumentException("UserID Cannot be null!");
		} else if (userID.isBlank()) {
			throw new IllegalArgumentException("UserID Cannot be blank!");
		} else {
			this.userID = userID;
		}
	}
	
	public String getUserID() {
		return userID;
	}
	
}
