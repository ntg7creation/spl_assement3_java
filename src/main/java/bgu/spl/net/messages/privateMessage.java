package bgu.spl.net.messages;

public class privateMessage implements MyMessage {

	private String username;
	private String Content;

	public privateMessage(byte[] b) {
		int start = 0;
		int end = 0;

		while (end < b.length && b[end] != '\0')
			end++;

		username = new String(b, start, end);

		end++;
		start = end;
		while (end < b.length && b[end] != '\0')
			end++;
		Content = new String(b, start, end);
	}

	@Override
	public Object get1() {
		return username;
	}

	@Override
	public Object get2() {
		return Content;
	}

	@Override
	public Object get3() {
		throw new IllegalArgumentException("no item 3 in this type of messge (PM)");

	}

	@Override
	public byte[] encode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageOp get_type() {
		return MessageOp.PM;
	}

}
