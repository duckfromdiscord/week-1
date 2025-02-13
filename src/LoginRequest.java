
public class LoginRequest {
	private final String userID;
	
	public LoginRequest(String userID) {
		this.userID = userID;
	}
	
	public String getUserID() {
		return userID;
	}
	
}
