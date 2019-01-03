package bgu.spl.net.messages;

public class Ack implements MyMessage {

	private MessageOp hisType;
	private String opption;

	public Ack(MessageOp mytype, byte[] moreStuff, String opption) {
		this.hisType = mytype;
		this.opption = opption;
	}

	public Ack(MessageOp mytype) {
		this.hisType = mytype;
		this.opption = "";
	}

	@Override
	public Object get1() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get3() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public byte[] encode() {

		short acknumber = (short) MessageOp.Ack.getValue();
		short number = ((short) hisType.getValue());
		byte[] b = new byte[2 + 2 + opption.getBytes().length];
		b[0] = (byte) (acknumber & 0xff);
		b[1] = (byte) ((acknumber >> 8) & 0xff);
		b[2] = (byte) (number & 0xff);
		b[3] = (byte) ((number >> 8) & 0xff);
		int fill = 4;
		for (byte c : opption.getBytes()) {
			b[fill] = c;
			fill++;
		}
		return b;
	}

	@Override
	public MessageOp get_type() {
		return MessageOp.Ack;
	}

}
