package bgu.spl.net.srv;

import bgu.spl.net.impl.newsfeed.NewsFeed;
import bgu.spl.net.impl.rci.ObjectEncoderDecoder;
import bgu.spl.net.impl.rci.RemoteCommandInvocationProtocol;

public class ServerMain {

	public static void main(String[] args) {

		// if (args[0] == 0) {
		//
		// Server.threadPerClient(
		// 7777, //port
		// () -> new RemoteCommandInvocationProtocol<>(feed), //protocol factory
		// ObjectEncoderDecoder::new //message encoder decoder factory
		// ).serve();
		// }
		// else {
		//
		// Server.reactor(
		// Runtime.getRuntime().availableProcessors(),
		// 7777, //port
		// () -> new RemoteCommandInvocationProtocol<>(feed), //protocol factory
		// ObjectEncoderDecoder::new //message encoder decoder factory
		// ).serve();

	}
}
