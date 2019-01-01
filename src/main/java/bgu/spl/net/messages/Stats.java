package bgu.spl.net.messages;

public class Stats implements MyMessage {

	private String username;

	public Stats(byte[] b) {
		int start = 0;
		int end = 0;

		while (end < b.length && b[end] != '\0')
			end++;

		username = new String(b, start, end);
	}

	@Override
	public Object get1() {
		return username;
	}

	@Override
	public Object get2() {
		throw new IllegalArgumentException("no item 2 in this type of messge (stats)");
		
	}

	@Override
	public Object get3() {
		throw new IllegalArgumentException("no item 3 in this type of messge (stats)");
		
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

	@Override
	public MessageOp get_type() {
		return MessageOp.Stats;
	}

}
