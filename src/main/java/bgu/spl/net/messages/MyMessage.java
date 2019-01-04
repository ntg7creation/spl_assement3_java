package bgu.spl.net.messages;

public interface MyMessage {
	

	

	public Object get1();
	public Object get2();
	public Object get3();

	public MessageOp get_type();
	
	public byte[] encode();
	

}
