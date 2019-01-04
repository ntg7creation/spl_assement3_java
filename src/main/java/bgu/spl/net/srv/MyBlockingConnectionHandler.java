package bgu.spl.net.srv;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

import bgu.spl.net.api.MessageEncoderDecoder;
import bgu.spl.net.api.bidi.BidiMessagingProtocol;
import bgu.spl.net.api.bidi.Connections;
import bgu.spl.net.messages.MyMessage;
import bgu.spl.net.srv.bidi.ConnectionHandler;

public class MyBlockingConnectionHandler implements Runnable, ConnectionHandler<MyMessage> {

	private final BidiMessagingProtocol<MyMessage> protocol;
	private final MessageEncoderDecoder<MyMessage> encdec;
	private final Socket sock;
	private BufferedInputStream in;
	private BufferedOutputStream out;
	private volatile boolean connected = true;
	private static int connectionID = 1;

	public MyBlockingConnectionHandler(Socket sock, MessageEncoderDecoder<MyMessage> reader,
			BidiMessagingProtocol<MyMessage> protocol, Connections<MyMessage> connections) {
		this.sock = sock;
		this.encdec = reader;
		this.protocol = protocol;
		protocol.start(connectionID, connections);
		connectionID++;
	}

	@Override
	public void run() {
		try (Socket sock = this.sock) { // just for automatic closing
			int read;

			in = new BufferedInputStream(sock.getInputStream());
			out = new BufferedOutputStream(sock.getOutputStream());

			while (!protocol.shouldTerminate() && connected && (read = in.read()) >= 0) {
				MyMessage nextMessage = encdec.decodeNextByte((byte) read);
				if (nextMessage != null) {
					protocol.process(nextMessage);
					// if (response != null) { // the change
					// out.write(encdec.encode(response));
					// out.flush();
					// }
				}
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void close() throws IOException {
		connected = false;
		sock.close();
	}

	@Override
	public void send(MyMessage msg) {
		try (Socket sock = this.sock) { // just for automatic closing
			out.write(encdec.encode(msg));
			out.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
