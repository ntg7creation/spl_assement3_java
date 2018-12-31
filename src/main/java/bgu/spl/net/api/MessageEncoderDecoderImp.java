package bgu.spl.net.api;

import java.util.Arrays;

import bgu.spl.net.messages.MyMessage;
import bgu.spl.net.messages.Post;
import bgu.spl.net.messages.Register;

public class MessageEncoderDecoderImp implements MessageEncoderDecoder<MyMessage> {

	private short type;
	private int bytecount = 0;
	private int zerocounter = 0;
	private byte[] buffer = new byte[1 << 10];
	private MyMessage m;
	

	public MessageEncoderDecoderImp() {
		type = -1;
	}

	public MyMessage decodeNextByte(byte nextByte) {

		buffer[bytecount] = nextByte;
		bytecount++;
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
				m =  new Register(Arrays.copyOfRange(buffer, 2,buffer.length));
				clear();
				return m;
			}
			break;
		case 2:
			if (zerocounter == 2) {
				//return new login(buffer);
			}
			break;
		case 3:
			if (zerocounter == 0) {
				//return new logout(buffer);
			}
			break;
		case 4:
			if (bytecount == 5) {
				short numberofusers = (short) ((buffer[3] & 0xff) << 8);
				numberofusers += (short) (buffer[4] & 0xff);
				if (zerocounter == numberofusers) {
					//return new followmsg(buffer[2],buffer)
				}
			}
			break;
		case 5:
			if (zerocounter == 1) {
				m= new Post(Arrays.copyOfRange(buffer, 2,buffer.length));
				clear();
				return m;
			}
			break;
		case 6:
			if (zerocounter == 2) {
				//return new pm(Arrays.copyOfRange(buffer, 2,buffer.length));
			}
			break;
		case 7:
			if (zerocounter == 0)
				//return new user list
				break;
		case 8:
			if (zerocounter == 1)
				//return new stat();
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
		bytecount =0;
		zerocounter =0;
		buffer = new byte[1<<10];
	}
	
	private void inlarge() {}

}
