package bgu.spl.net.api.bidi;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import bgu.spl.net.messages.MyMessage;
import bgu.spl.net.messages.Notification;
import bgu.spl.net.srv.bidi.ConnectionHandler;

public class Connectionimpl implements Connections<MyMessage> {

	private ConcurrentHashMap<String, Costumer> userMap; // registers
	private ConcurrentHashMap<Integer, String> logedInMap;
	private ConcurrentHashMap<Integer, ConnectionHandler<MyMessage>> handlersMap;

	public Connectionimpl() {
		userMap = new ConcurrentHashMap<>();
		logedInMap = new ConcurrentHashMap<>();
		handlersMap = new ConcurrentHashMap<>();
	}

	@Override
	public boolean send(int connectionId, MyMessage msg) {
		if (handlersMap.contains(connectionId)) {
			try {
				handlersMap.get(connectionId).send(msg);
				return true;
			} catch (Exception e) {
				System.out.println("user probly logout while about to recive a msg");
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean send(String name, String msg, byte type) {
		if (userMap.containsKey(name)) {
			int connectionId = userMap.get(name).getLastConnectedID();
			if (isLogedIn(connectionId) && logedInMap.get(connectionId) == name) {
				if (!send(connectionId, new Notification(type, name, msg)))
					userMap.get(name).addMessage(new Notification(type, name, msg));
			} else
				userMap.get(name).addMessage(new Notification(type, name, msg));
			return true;
		}
		return false;
	}

	@Override
	public void disconnect(int connectionId) {
		logedInMap.remove(connectionId);
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
		if (!isLogedIn(connectionId) && userMap.get(logedInMap.get(connectionId)).cheackPassword(password)) {
			insertToLogedIn(connectionId, userName, password);
			return true;
		}
		return false;
	}

	// @Override
	// public Costumer getCostumer(String userName) {
	// return userMap.get(userName);
	// }

	@Override
	public void post(int connection, String msg, List<String> to) {
		if (isLogedIn(connection)) {

			List<String> myFollwers = userMap.get(logedInMap.get(connection)).getFollwerList();
			for (String name : myFollwers) {
				if (!to.contains(name))
					to.add(name);
			}

			for (String name : to)
				send(name, msg, (byte) 1);

		}
	}

	@Override
	public boolean logout(int connectionId) {
		if (isLogedIn(connectionId)) {
			disconnect(connectionId);
			return true;
		}
		return false;
	}

	@Override
	public boolean insertToUserList(String userName, String password) {
		// Maybe add a sync here
		if (!isInUserList(userName)) {
			userMap.put(userName, new Costumer(userName, password));
			return true;
		}
		return false;
//		else {
//			Costumer newcostumer = new Costumer(userName, password);
//			userMap.put(userName, newcostumer);
//			return true;
//		}
	}

	@Override
	public Set<String> getNames() {
		return userMap.keySet();
	}

	//never used
	@Override
	public void broadcast(MyMessage msg) {
		Set<Integer> all_log_in = logedInMap.keySet();
		for (Integer connetion : all_log_in) {
			send(connetion, msg);
		}

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

	// @Override
	// public Costumer getCostumer(int connectionId) {
	// // TODO Auto-generated method stub // to delete funtion?
	// return null;
	// }


	private Costumer getCostumer(int connectionId) {
		return this.userMap.get(logedInMap.get(connectionId));
	}
	public void AddHandler(int connectionId, ConnectionHandler<MyMessage> handler) {
		handlersMap.put(connectionId, handler);

	}

	@Override
	public void RemoveHandler(int connectionId) {
		handlersMap.remove(connectionId);
	}

}
