package bgu.spl.net.messages;

public class Notification implements Message {

	private byte[] messageEncode;
	

	public Notification(String message) {
		messageEncode = message.getBytes();
	}
	

	public Object get1() {
		return messageEncode;
	}

	public Object get2() {
		throw new IllegalArgumentException("Notification messege dosent have a 2'th element");
	}

	public Object get3() {
		throw new IllegalArgumentException("Notification messege dosent have a 3'th element");
	}

	public Object get4() {
		throw new IllegalArgumentException("Notification messege dosent have a 4'th element");
	}

	public Object get5() {
		throw new IllegalArgumentException("Notification messege dosent have a 5'th element");
	}
} 
