import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DatabaseServer implements DatabaseServerInterface {
	
	private LoginAttempt login;
	private ArrayList<Double> userData = new ArrayList<>(); //This will eventually be some sort of database, but since I dont know anything about that, this'll have to do.

	
	public DatabaseServer(LoginRequest loginRequest) {
		this.login = loginMethod(loginRequest);
		
	}

	@Override
	public boolean storeUserData(String fileName) {
		String csvFilePath = fileName; // Update this path to your CSV file location
        try {
            Scanner scanner = new Scanner(new File(csvFilePath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                // Skip empty lines
                if (line.isEmpty()) {
                	continue;
                	}
                
                String[] element = line.split(",");
                double[] numbers = new double[element.length];
                // Parse each token as a double
                for (int i = 0; i < element.length; i++) {
                    try {
                        numbers[i] = Double.parseDouble(element[i].trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format for element: " + element[i] + " in line: " + line);
                        //Making this more readable for others by naming it element instead of token. 
                        //I also dont know how it turned into System.err.println. maybe a misclick when autofilling.
                        numbers[i] = 0.0; 
                    }
                }
                
                for(int i = 0; i < numbers.length; i++) {
                	userData.add(numbers[i]);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) { //This exception is thrown by scanner if file not found. Just have to catch and fix it.
            System.err.println("CSV file not found: " + csvFilePath);
            e.printStackTrace();
        }
		return true;
	}

	@Override
	public LoginAttempt loginMethod(LoginRequest loginRequest) {
		LoginAttempt currentLogin = new LoginAttempt(loginRequest);
		return currentLogin;
	}

	@Override
	public boolean processData(int chosenDataID) { //chosenDataID assumes our database can store multiple sets of numbers for users
		System.out.println(userData); //This basically does nothing so far. This is the communication between the computation and the database
		return true; //I'm thinking the return type for this is going to have to be a list and it'll be returning the arraylist to the computation server
	}

	@Override
	public boolean storeSortedData(int dataID) { //dataID allows users to assgin an ID to this set of data, assuming multiple sets can be stored
		// TODO I'm also completely unsure of what goes here. 
		return true;
	}

	@Override
	public boolean returnUserData(int chosenDataID, int chosenDataType) {
		// TODO This should turn the userData into a CSV or BSV file based on chosenDatatype. No clue how to approach this either
		return false;
	}

	@Override
	public boolean wipeUserData() {
		userData.removeAll(userData);
		return true;
	}
	
	public String getUserID() {
		return login.getUserID();
	}

}
