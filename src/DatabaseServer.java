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
                
                String[] tokens = line.split(",");
                double[] numbers = new double[tokens.length];
                
                // Parse each token as a double
                for (int i = 0; i < tokens.length; i++) {
                    try {
                        numbers[i] = Double.parseDouble(tokens[i].trim());
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid number format for token: " + tokens[i] + " in line: " + line);
                        // You might choose to handle this error differently.
                        numbers[i] = 0.0; // Default value in case of error
                    }
                }
                
                for(int i = 0; i < numbers.length; i++) {
                	userData.add(numbers[i]);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
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

}
