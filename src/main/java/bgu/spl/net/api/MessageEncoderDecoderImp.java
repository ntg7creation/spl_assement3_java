package bgu.spl.net.api;

import bgu.spl.net.messages.MyMessage;
import bgu.spl.net.messages.Register;

public class MessageEncoderDecoderImp implements MessageEncoderDecoder<MyMessage> {

	private short type;
	private int bytecount = 0;
	private int zerocounter = 0;
	private byte[] buffer = new byte[1 << 10];

	public MessageEncoderDecoderImp() {
		type = -1;
	}

	public MyMessage decodeNextByte(byte nextByte) {

		buffer[bytecount] = nextByte;
		if (bytecount == 2) {
			type = (short) ((buffer[0] & 0xff) << 8);
			type += (short) (buffer[1] & 0xff);
		}
		if (bytecount > 2)
			if (nextByte == '\0')
				zerocounter++;
		switch (type) {
		case 1:
			if (zerocounter == 2) {
				return  new Register(buffer);
			}
			break;
		case 2:
			if (zerocounter == 2) {
			}
			break;
		case 3:
			if (zerocounter == 0) {
			}
			break;
		case 4:
			if (bytecount == 5) {
				short numberofusers = (short) ((buffer[3] & 0xff) << 8);
				numberofusers += (short) (buffer[4] & 0xff);
				if (zerocounter == numberofusers) {
				}
			}
			break;
		case 5:
			if (zerocounter == 1) {
			}
			break;
		case 6:
			if (zerocounter == 2) {
			}
			break;
		case 7:
			if (zerocounter == 0)
				break;
		case 8:
			if (zerocounter == 1)
				break;
		default:
			break;
		}
		return null;
	}

	@Override
	public byte[] encode(MyMessage message) {
		return message.encode();
	}
	private void clear() {}
	
	private void inlarge() {}

}
