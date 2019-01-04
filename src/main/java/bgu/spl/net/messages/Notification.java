package bgu.spl.net.messages;

public class Notification implements MyMessage {

	private String message;
	private byte Privete_Post;
	// private char type;
	// private String Posting_user;
	// private String Content;

	public Notification(byte type, String Posting_user, String Content) {
		// this.type = type;
		// this.Posting_user = Posting_user;
		// this.Content = Content;
		Privete_Post = type;
		message = Posting_user + '\0' + Content + '\0';

	}

	@Override
	public Object get1() {
		throw new IllegalArgumentException("Notification messege dosent have a 2'th element");

	}

	@Override
	public Object get2() {
		throw new IllegalArgumentException("Notification messege dosent have a 2'th element");

	}

	@Override
	public Object get3() {
		throw new IllegalArgumentException("Notification messege dosent have a 2'th element");

	}

	@Override
	public byte[] encode() {

		short acknumber = (short) MessageOp.Ack.getValue();
		byte[] encodedmsg = new byte[3 + message.getBytes().length];
		encodedmsg[0] = (byte) (acknumber & 0xff);
		encodedmsg[1] = (byte) ((acknumber >> 8) & 0xff);
		encodedmsg[2] = Privete_Post;
		int fill = 3;
		for (byte c : message.getBytes()) {
			encodedmsg[fill] = c;
			fill++;
		}	return encodedmsg;
	}

	@Override
	public MessageOp get_type() {
		return MessageOp.Notification;
	}


}
