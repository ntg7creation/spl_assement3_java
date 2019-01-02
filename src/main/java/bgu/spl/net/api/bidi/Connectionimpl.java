package bgu.spl.net.api.bidi;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import bgu.spl.net.messages.MyMessage;
import bgu.spl.net.srv.ConnectionHandler;

public class Connectionimpl implements Connections<MyMessage>{
	
	private Map<String, Costumer> userMap;
	private Map<Integer, String> logedInMap;
	private Map<Integer,ConnectionHandler> handlersMap;
	
	
	public Connectionimpl() {
		userMap = new WeakHashMap<>();
		logedInMap = new WeakHashMap<>();
		handlersMap = new  WeakHashMap<>();
	}
	



	@Override
	public void disconnect(int connectionId) {
		handlersMap.remove(connectionId);
		
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
	public Costumer getCostumer(String userName) {
		// TODO Auto-generated method stub
		return null;
	}


	

	@Override
	public boolean send(String name, String msg) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void post(int connectionId, String msg, List to) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean logout(int connectionId) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean insertToUserList(String userName, String password) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public List getNames() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean send(int connectionId, MyMessage msg) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void broadcast(MyMessage msg) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public List<String> follow(int connectionId, List<String> names) {
		
		return null;
	}




	@Override
	public List<String> unfollow(int connectionId, List<String> names) {
		// TODO Auto-generated method stub
		return null;
	}

}
