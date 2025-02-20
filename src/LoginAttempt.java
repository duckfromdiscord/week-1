

public class LoginAttempt implements LoginAttemptInterface {

	
	private String userID = null;
	
	public LoginAttempt(LoginRequest request) {
		this.userID = request.getUserID();
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
		// TODO Auto-generated method stub
		return "user";
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
