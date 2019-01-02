package bgu.spl.net.api.bidi;

import bgu.spl.net.messages.MyMessage;

public class MyConnections implements Connections<MyMessage> {

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
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean insertToUserList(String userName, String password) {
		// TODO Auto-generated method stub
		//may need sync
		return false;
	}
	

	@Override
	public Costumer getCostumer(String userName) {
		// TODO Auto-generated method stub
		return null;
	}



}
