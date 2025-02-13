import project.annotations.NetworkAPI;

@NetworkAPI
public interface ClientInterface {

	boolean transmitUsername(int userId);

	boolean transmitQuery(int query);

	boolean getFile(int file);
	
}
