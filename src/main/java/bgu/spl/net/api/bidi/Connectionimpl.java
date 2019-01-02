package bgu.spl.net.api.bidi;

import java.util.Map;
import java.util.WeakHashMap;

public class Connectionimpl implements Connections{
	
	private Map<String, Costumer> userMap;
	private Map<Integer, String> logedInMap;
	
	
	public Connectionimpl() {
		userMap = new WeakHashMap<>();
		logedInMap = new WeakHashMap<>();
	}
	

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
		return logedInMap.containsKey(connectionId);
	}

	@Override
	public boolean isInUserList(String userName) {
		return userMap.containsKey(userName);
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
