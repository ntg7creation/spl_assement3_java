package bgu.spl.net.api.bidi;

public class Connectionimpl implements Connections{

	@Override
	public boolean send(int connectionId, Object msg) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void broadcast(Object msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disconnect(int connectionId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isLogedIn(int connectionId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInUserList(String userName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertToLogedIn(int connectionId, String userName) {
		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean insertToUserList(String userName) {
		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public Costumer getCostumer(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

}
