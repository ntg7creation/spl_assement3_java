package bgu.spl.net.api.bidi;

import java.util.List;

public interface Connections<T> {

	boolean send(int connectionId, T msg);

	boolean send(String name,String msg);
	
	
	
	void broadcast(T msg);

	void disconnect(int connectionId);

	boolean isLogedIn(int connectionId);

	void post(int connectionId,String msg,List<String> to);
	
	boolean logout(int connectionId);

	boolean isInUserList(String userName);

	boolean insertToLogedIn(int connectionId, String userName);

	boolean insertToUserList(String userName, String password);


	List<String> follow(int connectionId, List<String> names);
	
	List<String> unfollow(int connectionId,List<String> names);

	
	Costumer getCostumer(String userName);

	Costumer getCostumer(int connectionId);
	
	List<String> getNames();
}