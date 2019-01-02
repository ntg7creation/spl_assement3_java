package bgu.spl.net.srv.bidi;

import java.nio.channels.SocketChannel;

import bgu.spl.net.api.MessageEncoderDecoder;
import bgu.spl.net.api.MessagingProtocol;
import bgu.spl.net.messages.MyMessage;
import bgu.spl.net.srv.NonBlockingConnectionHandler;
import bgu.spl.net.srv.Reactor;

public class myHandler extends NonBlockingConnectionHandler<MyMessage>  implements ConnectionHandler<MyMessage>  {

	public myHandler(MessageEncoderDecoder<MyMessage> reader, MessagingProtocol<MyMessage> protocol, SocketChannel chan,
			Reactor reactor) {
		super(reader, protocol, chan, reactor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void send(MyMessage msg) {
		// TODO Auto-generated method stub
		
	}

}
