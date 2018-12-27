package bgu.spl.net.messages;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Register implements MyMessage{
	
	private String username;
	private String password;
	private byte[] bytesOfUserName = new byte[1 << 10]; //start with 1k
	private byte[] bytesOfPassword = new byte[1 << 10]; //start with 1k
    private int len = 0;

	
	public Register(byte[] b) {
		int counterOfElemnts = 0;
		for (byte currByte : b) {
			if (counterOfElemnts == 0) {
				if (currByte == '\0') {
					counterOfElemnts++;
					username = new String(bytesOfUserName, 0, len, StandardCharsets.UTF_8);
					len =0;
				}
				pushByteOfUserName(currByte);
			}
			else {
				if (currByte == '\0') {
					password = new String(bytesOfPassword, 0, len, StandardCharsets.UTF_8);
				}
				pushByteOfPassword(currByte);
			}
		}
	}
	

	public String get1() {
		return username;
	}
	
	public String get2() {
		return password;
	}
	
	public Object get3() {
		throw new IllegalArgumentException("Register messege dosent have a 3'th element");
	}
	
	public Object get4() {
		throw new IllegalArgumentException("Register messege dosent have a 4'th element");
	}
	
	public Object get5() {
		throw new IllegalArgumentException("Register messege dosent have a 5'th element");
	}


	private void pushByteOfPassword(byte nextByte) {
		if (len >= bytesOfPassword.length) {
			bytesOfPassword = Arrays.copyOf(bytesOfPassword, len * 2);
		}

		bytesOfPassword[len++] = nextByte;
	}

	private void pushByteOfUserName(byte nextByte) {
		if (len >= bytesOfUserName.length) {
			bytesOfUserName = Arrays.copyOf(bytesOfUserName, len * 2);
		}

		bytesOfUserName[len++] = nextByte;
	}


	@Override
	public byte[] encode() {
		throw new IllegalArgumentException("this message cant be encoded");
	}


	
}
