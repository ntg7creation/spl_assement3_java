package bgu.spl.net.messages;

public class Error implements MyMessage {

	private byte[] messageOpcode;

	public Error(Short messageType) {
		messageOpcode = new byte[2];
		messageOpcode[0] = (byte) ((messageType >> 8) & 0xFF);
		messageOpcode[1] = (byte) (messageType & 0xFF);
	}

	public Object get1() {
		return messageOpcode;
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

	@Override
	public byte[] encode() {
		throw new IllegalArgumentException("Error has no use to encode function, the constructor does it");
	}

	@Override
	public MessageOp get_type() {
		return MessageOp.Error;
	}

}
