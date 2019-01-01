package bgu.spl.net.messages;

public  enum  MessageOp {

	Register(1),
	Login(2),
	Logout(3),
	Follow(4),
	Post(5),
	PM(6),
	User(7),
	Stats(8),
	Notification(9),
	Ack(10),
	Error(11);
	
	
	  private int value;    

	  private MessageOp(int value) {
	    this.value = value;
	  }

	  public int getValue() {
	    return value;
	  }
}
