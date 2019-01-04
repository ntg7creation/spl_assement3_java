package bgu.spl.net.impl.BGSServer;

import java.util.function.Supplier;

import bgu.spl.net.api.MessageEncoderDecoder;
import bgu.spl.net.api.MessageEncoderDecoderImp;
import bgu.spl.net.api.MessagingProtocol;
import bgu.spl.net.api.bidi.BidiMessagingProtocol;
import bgu.spl.net.api.bidi.MyMessagingProtocol;
import bgu.spl.net.impl.rci.ObjectEncoderDecoder;
import bgu.spl.net.impl.rci.RemoteCommandInvocationProtocol;
import bgu.spl.net.messages.MyMessage;
import bgu.spl.net.srv.Server;

public class ReactorMain {
	public static void main(String[] args) {
		
		final Supplier<BidiMessagingProtocol<MyMessage>> protocolFactory;
		final Supplier<MessageEncoderDecoder<MyMessage>> encdecFactory;

		Server.reactor(Integer.parseInt(args[1]),Integer.parseInt(args[0]),
				protocolFactory, encdecFactory).serve();

	}

}
