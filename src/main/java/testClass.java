import java.util.function.Supplier;

import bgu.spl.net.api.MessageEncoderDecoder;
import bgu.spl.net.api.MessageEncoderDecoderImp;
import bgu.spl.net.api.bidi.BidiMessagingProtocol;
import bgu.spl.net.api.bidi.Connectionimpl;
import bgu.spl.net.api.bidi.Connections;
import bgu.spl.net.api.bidi.MyMessagingProtocol;
import bgu.spl.net.messages.MyMessage;
import bgu.spl.net.messages.Register;
import bgu.spl.net.srv.Reactor;
import bgu.spl.net.srv.Server;
import bgu.spl.net.srv.bidi.MyHandlerFactory;
import bgu.spl.net.srv.bidi.MySupplier;

public class testClass {

	public static void main(String agr[]) {
		

		new Reactor<MyMessage>(4, 7777, protocolFactory, readerFactory, HandlerFactory, myConnections);
	}

	private void test_2() {
		BidiMessagingProtocol<MyMessage> MP = new MyMessagingProtocol();

		int fill = 0;
		byte[] b = new byte[1 << 10];
		String word = "natai";
		for (byte c : word.getBytes()) {
			b[fill] = c;
			fill++;
		}

		b[fill] = '\0';
		fill++;

		String word2 = "passcode";
		for (byte c : word2.getBytes()) {
			b[fill] = c;
			fill++;
		}
		MyMessage logingmessage = new Register(b);

		MP.process(logingmessage);
	}

	private void test1() {
		System.out.println("stuff");

		int fill = 0;
		byte[] b = new byte[1 << 10];
		String word = "somthing";
		for (byte c : word.getBytes()) {
			b[fill] = c;
			fill++;
		}

		b[fill] = '\0';
		fill++;

		String word2 = "one else thing";
		for (byte c : word2.getBytes()) {
			b[fill] = c;
			fill++;
		}

		int start = 0;
		int end = 0;

		while (end < b.length && b[end] != '\0')
			end++;
		System.out.println(new String(b, start, end));
		end++;
		start = end;
		while (end < b.length && b[end] != '\0')
			end++;
		System.out.println(new String(b, start, end));
		// HHHH
	}

}
