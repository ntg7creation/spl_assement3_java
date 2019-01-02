package bgu.spl.net.api.bidi;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import bgu.spl.net.messages.MyMessage;

public class Costumer {
	
	private String userName;
	private String password;
	private List<String> followList;
	private ConcurrentLinkedQueue<MyMessage> messageList;
	
	public Costumer (String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	public Boolean cheackPassword(String enteredPassword) {
		return (enteredPassword == password);
	}
	
	public List<String> insertFollow(List<String> newFollow) {
		List<String> added = new ArrayList<String>();
		for (String userFollow: newFollow) {
			if (!followList.contains(userFollow)) {
				followList.add(userFollow);
				added.add(userFollow);
			}
		}
		return added;
		
	}
	
	public List<String> deleteFollow(List<String> unFollow) {
		List<String> deleted = new ArrayList<String>();
		for (String unFollowUser: unFollow) {
			if (followList.contains(unFollowUser)) {
				followList.remove(unFollowUser);
				deleted.add(unFollowUser);
			}
		}
		return deleted;
	}
	
	public void addMessage(MyMessage msg) {
		this.messageList.add(msg);
	}

}
