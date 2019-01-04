package bgu.spl.net.api.bidi;

import java.util.List;
import java.util.Set;

import bgu.spl.net.srv.bidi.ConnectionHandler;

public interface Connections<T> {

	boolean send(int connectionId, T msg);

	boolean send(String name, String msg, byte type);

	void broadcast(T msg);

	void disconnect(int connectionId);

	boolean isLogedIn(int connectionId);

	void post(int connectionId, String msg, List<String> to);

	boolean logout(int connectionId);

	boolean isInUserList(String userName);

	boolean insertToLogedIn(int connectionId, String userName, String password);

	boolean insertToUserList(String userName, String password);

	List<String> follow(int connectionId, List<String> names);

	List<String> unfollow(int connectionId, List<String> names);

	// Costumer getCostumer(String userName);

	//Costumer getCostumer(int connectionId);

	Set<String> getNames();

	// -------------------------------------
	void AddHandler(int connectionId, ConnectionHandler<T> handler);

	void RemoveHandler(int connectionId);
}