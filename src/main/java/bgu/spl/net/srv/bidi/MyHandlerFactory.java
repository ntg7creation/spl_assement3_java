package bgu.spl.net.srv.bidi;

import java.net.Socket;
import java.nio.channels.SocketChannel;

import bgu.spl.net.api.MessageEncoderDecoder;
import bgu.spl.net.api.MessagingProtocol;
import bgu.spl.net.api.bidi.BidiMessagingProtocol;
import bgu.spl.net.api.bidi.Connections;
import bgu.spl.net.api.bidi.MyMessagingProtocol;
import bgu.spl.net.messages.MyMessage;
import bgu.spl.net.srv.MyBlockingConnectionHandler;
import bgu.spl.net.srv.MyNonBlockingConnectionHandler;
import bgu.spl.net.srv.Reactor;

public class MyHandlerFactory implements MySupplier<MyMessage> {

	@Override
	public MyNonBlockingConnectionHandler get(MessageEncoderDecoder<MyMessage> endec,
			MessagingProtocol<MyMessage> protocol, SocketChannel chan, Reactor<MyMessage> reactor,
			Connections<MyMessage> connections) {
		return new MyNonBlockingConnectionHandler(endec, (MyMessagingProtocol) protocol, chan, reactor, connections);

	}

	@Override
	public MyBlockingConnectionHandler get(MessageEncoderDecoder<MyMessage> endec,
			BidiMessagingProtocol<MyMessage> protocol, Socket sock, Connections<MyMessage> connections) {

		return new MyBlockingConnectionHandler(sock, endec, protocol, connections);

	}

}
