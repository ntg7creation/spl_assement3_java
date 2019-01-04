package bgu.spl.net.messages;

public class Notification implements MyMessage {

	private byte[] messageEncode;
	private String message;
	
//	private char type;
//	private String Posting_user;
//	private String Content;

	public Notification(String message) {
		messageEncode = message.getBytes();
	}

	public Notification(char type, String Posting_user, String Content) {
//		this.type = type;
//		this.Posting_user = Posting_user;
//		this.Content = Content;
		message = type + Posting_user + '\0' + Content + '\0';
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


	@Override
	public byte[] encode() {
	//test
	byte[] encodedmsg = new byte[2 + messageEncode.length];
	
		// TODO Auto-generated method stub
		return "9".getBytes() ;
	}

	@Override
	public MessageOp get_type() {
		return MessageOp.Notification;
	}

	@Override
	public Object get4() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get5() {
		// TODO Auto-generated method stub
		return null;
	}
}
