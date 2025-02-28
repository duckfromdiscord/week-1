import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class DatabaseMemoryImplementation implements DatabaseServerInterface {

	private ArrayList<Integer> userData = new ArrayList<Integer>(); 
	private int user = 0;
	private Random random = new Random();
	
	public DatabaseMemoryImplementation() {
		this.user = random.nextInt();
	}


	@Override
	public boolean processData(int chosenDataID) {
		// Code that sends the user data to the computation side of the project 
		return false;
	}

	@Override
	public boolean storeSortedData(int dataID) {
		Scanner sc = new Scanner(System.in); 
		while (sc.hasNextLine()) {
		    String line = sc.nextLine();
		    String[] tokens = line.split(",");
		    for (String token : tokens) {
		        // Parse the token as an int and add it to userData
		        userData.add(Integer.parseInt(token.trim()));
		    }
		}
		sc.close();
		return false;
	}

	@Override
	public boolean returnUserData(int chosenDataID, int chosenDataType) {
		System.out.println(userData.get(chosenDataID));
		return false;
	}


	@Override
	public LoginAttemptInterface loginMethod(LoginRequest loginRequest) {
		return null;
	}


	@Override
	public boolean wipeUserData() {
		return false;
	}


	@Override
	public boolean storeUserData(String fileName) {
		Scanner sc = new Scanner(System.in);
		userData.add(sc.nextInt());
		return false;
	}

}
