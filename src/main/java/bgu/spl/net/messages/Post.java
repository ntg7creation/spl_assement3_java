package bgu.spl.net.messages;

import java.nio.charset.StandardCharsets;

public class Post implements MyMessage{ 
	
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


	
	@Override
	public byte[] encode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageOp get_type() {
		return MessageOp.Post;
	}
} 
