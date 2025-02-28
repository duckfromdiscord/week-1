

public class LoginAttempt implements LoginAttemptInterface {

	
	private String userID = null;
	private boolean isLoggedIn = false;
	private String message = null;
	
	public LoginAttempt(LoginRequest request) {
		this.userID = request.getUserID();
		this.isLoggedIn = this.isSuccessful();
		this.message = this.getMessage();
	}

	@Override
	public boolean isSuccessful() {
		if(userID != null) {
			return true;
	} else {
			return false;
		}
	}

	@Override
	public String getUserID() {
		return this.userID;
	}

	@Override
	public String getMessage() {
		return "Welcome back, " + userID;
	}
	
	@Override
	public boolean getLoginStatus() {
		return isLoggedIn;
	}
}
