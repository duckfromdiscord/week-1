//Create a login request, then pass it into loginattempt
//ex: LoginRequest (name) = new LoginRequest(name);
//	  DatabaseServer.login(name of LoginRequest object)
public class LoginRequest {
	private final String userID;
	
	public LoginRequest(String userID) {
		this.userID = userID;
	}
	
	public String getUserID() {
		return userID;
	}
	
}
