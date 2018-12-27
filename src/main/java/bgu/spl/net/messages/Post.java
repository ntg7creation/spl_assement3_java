package bgu.spl.net.messages;

import java.nio.charset.StandardCharsets;

public class Post implements MyMessage {

	private String content;

	public Post(byte[] b) {
		content = new String(b, 0, b.length, StandardCharsets.UTF_8);
	}

	public Object get1() {
		return content;
	}

	public Object get2() {
		throw new IllegalArgumentException("Post messege dosent have a 2'th element");
	}

	public Object get3() {
		throw new IllegalArgumentException("Post messege dosent have a 3'th element");
	}

	public Object get4() {
		throw new IllegalArgumentException("Post messege dosent have a 4'th element");
	}

	public Object get5() {
		throw new IllegalArgumentException("Post messege dosent have a 5'th element");
	}

	@Override
	public byte[] encode() {
		throw new IllegalArgumentException("this message cant be encoded");
	}
}
