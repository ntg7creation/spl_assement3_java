package bgu.spl.net.api.bidi;

import java.util.ArrayList;
import java.util.List;

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

			return NotificationAction();
		case Ack:

			return AckAction();
		case Error:

			return ErrorAction();
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
				boolean secLog = this.myConnection.insertToLogedIn(myconnectionID, (String) msg.get1());
				if (!secLog) {
					myConnection.send(myconnectionID, new Error((short) msg.get_type().getValue()));
				}
				else {
					myConnection.send(myconnectionID, new Ack(msg.get_type()));
				}

			}
			else {
				myConnection.send(myconnectionID, new Error((short) msg.get_type().getValue()));
			}
		};
	}

	private Runnable LogoutAction() {
		return () -> {
			if (myConnection.isLogedIn(myconnectionID) && myConnection.logout(myconnectionID))
				myConnection.send(myconnectionID, new Ack(MessageOp.Logout));
			else
				myConnection.send(myconnectionID, new Error((short) MessageOp.Logout.getValue()));
		};
	}

	private Runnable FollowAction() {
		return () -> { 
			myConnection.follow(myConnection.getCostumer(connectionId), names)
		};
	}

	private Runnable PostAction() {
		return () -> {
			if (myConnection.isLogedIn(myconnectionID)) {

				myConnection.post(myconnectionID, (String) msg.get1(), (List<String>) msg.get2());
				myConnection.send(myconnectionID, new Ack(MessageOp.Post));
			} else
				myConnection.send(myconnectionID, new Error((short) MessageOp.Post.getValue()));
		};
	}

	private Runnable PMAction() {
		return () -> {

		};
	}

	private Runnable UserAction() {
		
		
		
		
		return () -> {
			if (myConnection.isLogedIn(myconnectionID)) {
				List<String> users = myConnection.getNames();
				String names = "";
				short number = 0;
				byte[] inbytes = new byte[2];
				// make string
				myConnection.send(myconnectionID, new Ack(MessageOp.User, inbytes, names));
			} else
				myConnection.send(myconnectionID, new Error((short) MessageOp.User.getValue()));
		};
	}

	private Runnable StatsAction() {
		return () -> {
		};
	}

	private Runnable NotificationAction() {
		return () -> {
		};
	}

	private Runnable AckAction() {
		return () -> {
		};
	}

	private Runnable ErrorAction() {
		return () -> {
		};
	}
}
