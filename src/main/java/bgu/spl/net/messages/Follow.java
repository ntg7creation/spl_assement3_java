package bgu.spl.net.messages;

import java.util.LinkedList;
import java.util.List;

public class Follow implements MyMessage {

	private byte F_W;
	private short number;
	private List<String> names = new LinkedList<>();

	public Follow(byte[] b) {
		F_W = b[0];
		number = (short) ((b[1] & 0xff) << 8);
		number += (short) (b[2] & 0xff);

		int start = 3;
		int end = 3;
		while (end < b.length) {

			if (b[end] == '\0') {
				names.add(new String(b, start, end));
				end++;
				start = end;
			} else
				end++;
		}
	}

	@Override
	public Object get1() {
		return F_W;
	}

	@Override
	public Object get2() {
		return number;
	}

	@Override
	public Object get3() {
		return names;
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

	@Override
	public byte[] encode() {
		// TODO Auto-generated method stub
		return null;
	}

}
