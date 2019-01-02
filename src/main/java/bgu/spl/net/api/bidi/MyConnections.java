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

}
