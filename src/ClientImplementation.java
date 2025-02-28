public class ClientImplementation implements ClientInterface {
	private final CoordinationComponent coordinator;
	
	public ClientImplementation() {
		this.coordinator = new CoordinationComponent();
	}
	
	@Override
	public boolean transmitUsername(int userId) {
		// empty for now
		return false;
	}
	
	@Override
	public boolean transmitQuery(int query) {
		// empty for now
		return false;
	}
	
	@Override
	public boolean getFile(int file) {
		// empty for now
		return false;
	}

	@Override
	public boolean requestCompuation(String inputLocation, String outputLocation) {
		return coordinator.executeComputation(inputLocation, outputLocation);
	}
}
