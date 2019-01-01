package bgu.spl.net.api;

import java.util.Arrays;

import bgu.spl.net.messages.Follow;
import bgu.spl.net.messages.Login;
import bgu.spl.net.messages.MyMessage;
import bgu.spl.net.messages.Post;
import bgu.spl.net.messages.Register;
import bgu.spl.net.messages.Stats;
import bgu.spl.net.messages.privateMessage;

public class MessageEncoderDecoderImp implements MessageEncoderDecoder<MyMessage> {

	private short type= -1;
	private int bytecount = 0;
	private int zerocounter = 0;
	private byte[] buffer = new byte[1 << 10];
	private MyMessage m;

	public MessageEncoderDecoderImp() {
		
	}

	public MyMessage decodeNextByte(byte nextByte) {

		buffer[bytecount] = nextByte;
		bytecount++;
		if (bytecount >= buffer.length)
			inlarge();
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
				m = new Register(Arrays.copyOfRange(buffer, 2, buffer.length));
				clear();
				return m;
			}
			break;
		case 2:
			if (zerocounter == 2) {
				m = new Login(Arrays.copyOfRange(buffer, 2, buffer.length));
				clear();
				return m;
			}
			break;
		case 3:
			if (zerocounter == 0) {
				// return new logout(buffer);
			}
			break;
		case 4:
			if (bytecount == 5) {
				short numberofusers = (short) ((buffer[3] & 0xff) << 8);
				numberofusers += (short) (buffer[4] & 0xff);
				if (zerocounter == numberofusers) {
					m = new Follow(Arrays.copyOfRange(buffer, 2, buffer.length));
					clear();
					return m;
				}
			}
			break;
		case 5:
			if (zerocounter == 1) {
				m = new Post(Arrays.copyOfRange(buffer, 2, buffer.length));
				clear();
				return m;
			}
			break;
		case 6:
			if (zerocounter == 2) {
				m = new privateMessage(Arrays.copyOfRange(buffer, 2, buffer.length));
				clear();
				return m;
			}
			break;
		case 7:
			if (zerocounter == 0)
				// return new user list
				break;
		case 8:
			if (zerocounter == 1) {
				m = new Stats(Arrays.copyOfRange(buffer, 2, buffer.length));
				clear();
				return m;
			}
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

	private void clear() {
		type = 0;
		bytecount = 0;
		zerocounter = 0;
		buffer = new byte[1 << 10];
	}

	private void inlarge() {
		buffer = Arrays.copyOfRange(buffer, 0, buffer.length * 2);
	}

}
