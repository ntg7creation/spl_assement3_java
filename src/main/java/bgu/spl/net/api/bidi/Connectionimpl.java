package bgu.spl.net.api.bidi;

import java.util.ArrayList;
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
		if (userMap.containsKey(userName))
		return false;
		else {
			Costumer newcostumer = new Costumer(userName, password);
			userMap.put(userName, newcostumer);
			return true;
		}
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
		List<String> added = new ArrayList<String>();
		added =	this.getCostumer(connectionId).insertFollow(names);
		return added;
	}




	@Override
	public List<String> unfollow(int connectionId, List<String> names) {
		List<String> deleted = new ArrayList<String>();
		deleted = this.getCostumer(connectionId).deleteFollow(names);
		return deleted;
	}




	@Override
	public Costumer getCostumer(int connectionId) {
		return this.userMap.get(logedInMap.get(connectionId));
	}

}
