package bgu.spl.net.api.bidi;

import bgu.spl.net.messages.MessageOp;
import bgu.spl.net.messages.MyMessage;

public class MyMessagingProtocol implements BidiMessagingProtocol<MyMessage> {

	private int myconnectionID;
	private Connections<MyMessage> myConnection;
	private Boolean wantToTerminate;

	public MyMessagingProtocol() {
		myconnectionID = -1;
		myConnection = null;
		wantToTerminate =false;
	}

	@Override
	public void start(int connectionId, Connections<MyMessage> connections) {
		myconnectionID = connectionId;
		myConnection = connections;

	}

	@Override
	public void process(MyMessage message) {
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
		};
	}

	private Runnable LoginAction() {
		return () -> {
		};
	}

	private Runnable LogoutAction() {
		return () -> {
		};
	}

	private Runnable FollowAction() {
		return () -> {
		};
	}

	private Runnable PostAction() {
		return () -> {
		};
	}

	private Runnable PMAction() {
		return () -> {
		};
	}

	private Runnable UserAction() {
		return () -> {
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
