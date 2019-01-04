package bgu.spl.net.srv.bidi;

import java.net.Socket;
import java.nio.channels.SocketChannel;

import bgu.spl.net.api.MessageEncoderDecoder;
import bgu.spl.net.api.MessagingProtocol;
import bgu.spl.net.api.bidi.BidiMessagingProtocol;
import bgu.spl.net.api.bidi.Connections;
import bgu.spl.net.messages.MyMessage;
import bgu.spl.net.srv.BlockingConnectionHandler;
import bgu.spl.net.srv.MyBlockingConnectionHandler;
import bgu.spl.net.srv.MyNonBlockingConnectionHandler;
import bgu.spl.net.srv.Reactor;

public interface MySupplier<T> {

	MyNonBlockingConnectionHandler get(MessageEncoderDecoder<T> endec, MessagingProtocol<T> protocol,
			SocketChannel sock, Reactor<T> reactor, Connections<T> connections);

	MyBlockingConnectionHandler get(MessageEncoderDecoder<T> endec, BidiMessagingProtocol<T> protocol,
			Socket chan, Connections<T> connections);
//test


}
