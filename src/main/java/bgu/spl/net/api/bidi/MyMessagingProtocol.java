package bgu.spl.net.api.bidi;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import bgu.spl.net.messages.Ack;
import bgu.spl.net.messages.Error;
import bgu.spl.net.messages.MessageOp;
import bgu.spl.net.messages.MyMessage;

public class MyMessagingProtocol implements BidiMessagingProtocol<MyMessage> {

	private int myconnectionID;
	private Connections<MyMessage> myConnection;
	private Boolean wantToTerminate;
	private MyMessage msg;
	// private MyMessage msgToReturn;

	public MyMessagingProtocol() {
		myconnectionID = -1;
		myConnection = null;
		wantToTerminate = false;
	}

	@Override
	public void start(int connectionId, Connections<MyMessage> connections) {
		myconnectionID = connectionId;
		myConnection = connections;

	}

	@Override
	public void process(MyMessage message) {
		this.msg = message;
		getAction(message.get_type()).run();
	}

	@Override
	public boolean shouldTerminate() {
		return wantToTerminate;
	}

	private Runnable getAction(MessageOp code) {

		switch (code) {
		case Register:
			return RegisterAction();

		case Login:

			return LoginAction();
		case Logout:

			return LogoutAction();
		case Follow:

			return FollowAction();
		case Post:

			return PostAction();
		case PM:

			return PMAction();
		case User:

			return UserAction();
		case Stats:

			return StatsAction();
		case Notification:
		default:

		}

		return null;

	}

	private Runnable RegisterAction() {
		return () -> {
			if (!myConnection.isInUserList((String) msg.get1())
					&& myConnection.insertToUserList((String) msg.get1(), (String) msg.get2()))
				myConnection.send(myconnectionID, new Ack(msg.get_type()));
			else
				myConnection.send(myconnectionID, new Error((short) msg.get_type().getValue()));
		};

	}

	private Runnable LoginAction() {
		return () -> {
			boolean isExist = this.myConnection.isInUserList((String) msg.get1());
			if (isExist) {
				boolean secLog = this.myConnection.insertToLogedIn(myconnectionID, (String) msg.get1(),
						(String) msg.get2());
				if (!secLog) {
					myConnection.send(myconnectionID, new Error((short) msg.get_type().getValue()));
				} else {
					myConnection.send(myconnectionID, new Ack(msg.get_type()));
					ConcurrentLinkedQueue<MyMessage> msgList = myConnection.getCostumer(myconnectionID).getMessageList();
					while (!msgList.isEmpty()) {
						myConnection.send(myconnectionID,msgList.poll());
					}
				}

			} else {
				myConnection.send(myconnectionID, new Error((short) msg.get_type().getValue()));
			}
		};
	}

	private Runnable LogoutAction() {
		return () -> {
			if (myConnection.isLogedIn(myconnectionID) && myConnection.logout(myconnectionID)) {
				myConnection.send(myconnectionID, new Ack(MessageOp.Logout));
				wantToTerminate = true;
			} else
				myConnection.send(myconnectionID, new Error((short) MessageOp.Logout.getValue()));
		};
	}

	private Runnable FollowAction() {
		return () -> {

			if (myConnection.isLogedIn(myconnectionID)) {
				if ((byte) msg.get1() == 0) {
					myConnection.follow(myconnectionID, (List<String>) msg.get3());
				} else {
					myConnection.unfollow(myconnectionID, (List<String>) msg.get3());
				}
			} else {
				myConnection.send(myconnectionID, new Error((short) msg.get_type().getValue()));
			}

		};
	}

	private Runnable PostAction() {
		return () -> {
			if (myConnection.isLogedIn(myconnectionID)) {
				List<String> usernames = new LinkedList<>();
				String msgWithoutnames = "";
				for (String s : ((String) msg.get1()).split(" ")) {
					if (s.charAt(0) == '@')
						usernames.add(s.substring(1));
					else
						msgWithoutnames += s + " ";
				}

				myConnection.post(myconnectionID, (String) msg.get1(), usernames);
				myConnection.send(myconnectionID, new Ack(MessageOp.Post));
			} else
				myConnection.send(myconnectionID, new Error((short) MessageOp.Post.getValue()));
		};
	}

	private Runnable PMAction() {
		return () -> {
			if (myConnection.isLogedIn(myconnectionID) && myConnection.isInUserList((String) msg.get1())) {

				myConnection.send((String) msg.get1(), (String) msg.get2(), (byte) 0);
				myConnection.send(myconnectionID, new Ack(MessageOp.PM));
			} else
				myConnection.send(myconnectionID, new Error((short) MessageOp.PM.getValue()));
		};
	}

	private Runnable UserAction() {
		return () -> {
			if (myConnection.isLogedIn(myconnectionID)) {
				Set<String> users = myConnection.getNames();
				String names = "";
				short number = 0;
				byte[] inbytes = new byte[2];

				inbytes[0] = (byte) (number & 0xff);
				inbytes[1] = (byte) ((number >> 8) & 0xff);

				for (String s : users) {
					names += s + '\0';
				}
				myConnection.send(myconnectionID, new Ack(MessageOp.User, inbytes, names));
			} else
				myConnection.send(myconnectionID, new Error((short) MessageOp.User.getValue()));
		};
	}

	private Runnable StatsAction() {
		return () -> {
			if (myConnection.isLogedIn(myconnectionID)) {
			byte[] bytes = new byte[8];
			short opcode = (short) MessageOp.Stats.getValue();
			bytes[0] = (byte) (opcode & 0xff);
			bytes[1] =  (byte) ((opcode >> 8) & 0xff);
			bytes[2] =
			bytes[3] =
			} else
				myConnection.send(myconnectionID, new Error((short) MessageOp.User.getValue()));
			
		};
	}

}
