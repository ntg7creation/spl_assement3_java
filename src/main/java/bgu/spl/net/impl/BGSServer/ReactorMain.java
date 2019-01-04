package bgu.spl.net.impl.BGSServer;

import java.util.function.Supplier;

import bgu.spl.net.api.MessageEncoderDecoder;
import bgu.spl.net.api.MessageEncoderDecoderImp;
import bgu.spl.net.api.bidi.BidiMessagingProtocol;
import bgu.spl.net.api.bidi.Connectionimpl;
import bgu.spl.net.api.bidi.Connections;
import bgu.spl.net.api.bidi.MyMessagingProtocol;
import bgu.spl.net.messages.MyMessage;
import bgu.spl.net.srv.Server;
import bgu.spl.net.srv.bidi.MyHandlerFactory;
import bgu.spl.net.srv.bidi.MySupplier;

public class ReactorMain {
	public static void main(String[] args) {
							
		final Supplier<BidiMessagingProtocol<MyMessage>> protocolFactory = new Supplier<BidiMessagingProtocol<MyMessage>>() {
			
			@Override
			public BidiMessagingProtocol<MyMessage> get() {

				return new MyMessagingProtocol();
			}
		};
		final Supplier<MessageEncoderDecoder<MyMessage>> readerFactory = new Supplier<MessageEncoderDecoder<MyMessage>>() {

			@Override
			public MessageEncoderDecoder<MyMessage> get() {
				return new MessageEncoderDecoderImp();
			}
		};
		
		final MySupplier<MyMessage> HandlerFactory = new MyHandlerFactory();
		final Connections<MyMessage> myConnections = new Connectionimpl();
		Server<MyMessage> s = Server.reactor(Integer.parseInt(args[1]), Integer.parseInt(args[0]), protocolFactory, readerFactory, HandlerFactory, myConnections);

	}

}
