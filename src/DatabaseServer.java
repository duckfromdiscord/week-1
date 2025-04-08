import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class DatabaseServer implements DatabaseServerInterface {
	
	private LoginAttempt login;
	private List<Double> userData = Collections.synchronizedList(new ArrayList<>()); //This will eventually be some sort of database, but since I dont know anything about that, this'll have to do.

	
	public DatabaseServer(LoginRequest loginRequest) {
		this.login = loginMethod(loginRequest);
		
	}

	@Override
	public boolean storeUserData(String fileName) {
		try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            // Process each line at the same time using a parallel stream.
            lines.parallel().forEach(line -> {
                String trimmedLine = line.trim();
                if (trimmedLine.isEmpty()) {
                    return; // Skip empty lines
                }
                String[] elements = trimmedLine.split(",");
                // For each element in the line, parse as double and store.
                for (String element : elements) {
                    try {
                        double number = Double.parseDouble(element.trim());
                        // Simulate storing the number in a database.
                        userData.add(number);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format for element: " + element +
                                           " in line: " + line);
                    }
                }
            });
        } catch (IOException e) {
            System.err.println("Error reading file: " + fileName);
            e.printStackTrace();
            return false;
        }
        // At this point, data has been processed and "stored".
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
