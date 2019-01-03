package bgu.spl.net.api.bidi;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

import bgu.spl.net.messages.MyMessage;
import bgu.spl.net.srv.ConnectionHandler;

public class Connectionimpl implements Connections<MyMessage>{
	
	private ConcurrentHashMap<String, Costumer> userMap;
	private ConcurrentHashMap<Integer, String> logedInMap;
	private ConcurrentHashMap<Integer,ConnectionHandler<MyMessage>> handlersMap;
	
	
	public Connectionimpl() {
		userMap = new ConcurrentHashMap<>();
		logedInMap = new ConcurrentHashMap<>();
		handlersMap = new  ConcurrentHashMap<>();
	}
	



	@Override
	public void disconnect(int connectionId) {
		logedInMap.remove(connectionId);
		//handlersMap.remove(connectionId);
		
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
	public boolean insertToLogedIn(int connectionId, String userName, String password) {
		if (this.isLogedIn(connectionId) && this.getCostumer(connectionId).cheackPassword(password) && !this.isLogedIn(connectionId)) {
			this.insertToLogedIn(connectionId, userName, password);
			return true;
		}
		return false;
	}



	@Override
	public Costumer getCostumer(String userName) {
		return userMap.get(userName);
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




	@Override
	public Costumer getCostumer(int connectionId) {
		// TODO Auto-generated method stub
		return null;
	}

}
