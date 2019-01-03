package bgu.spl.net.srv;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import bgu.spl.net.api.MessageEncoderDecoderImp;
import bgu.spl.net.api.bidi.Connections;
import bgu.spl.net.api.bidi.MyMessagingProtocol;
import bgu.spl.net.messages.MyMessage;
import bgu.spl.net.srv.bidi.ConnectionHandler;

public class MyNonBlockingConnectionHandler implements ConnectionHandler<MyMessage> {

	private static final int BUFFER_ALLOCATION_SIZE = 1 << 13; // 8k
	private static final ConcurrentLinkedQueue<ByteBuffer> BUFFER_POOL = new ConcurrentLinkedQueue<>();

	private final MyMessagingProtocol protocol;
	private final MessageEncoderDecoderImp encdec;
	private final Queue<ByteBuffer> writeQueue = new ConcurrentLinkedQueue<>();
	private final SocketChannel chan;
	private final Reactor<MyMessage> reactor;

	public MyNonBlockingConnectionHandler(MessageEncoderDecoderImp reader, MyMessagingProtocol protocol, SocketChannel chan,
			Reactor<MyMessage> reactor,Connections<MyMessage> connections,int ConnectionID) {
		this.chan = chan;
		this.encdec = reader;
		this.protocol = protocol;
		this.reactor = reactor;
		protocol.start(ConnectionID, connections);
	}

	public Runnable continueRead() {
		ByteBuffer buf = leaseBuffer();

		boolean success = false;
		try {
			success = chan.read(buf) != -1;
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		if (success) {
			buf.flip();
			return () -> {
				try {
					while (buf.hasRemaining()) {
						MyMessage nextMessage = encdec.decodeNextByte(buf.get());
						if (nextMessage != null) {
							protocol.process(nextMessage);
							// if (response != null) { // the change
							// writeQueue.add(ByteBuffer.wrap(encdec.encode(response)));
							// reactor.updateInterestedOps(chan, SelectionKey.OP_READ |
							// SelectionKey.OP_WRITE);
							// }
						}
					}
				} finally {
					releaseBuffer(buf);
				}
			};
		} else {
			releaseBuffer(buf);
			close();
			return null;
		}

	}

	public void close() {
		try {
			chan.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public boolean isClosed() {
		return !chan.isOpen();
	}

	public void continueWrite() {
		while (!writeQueue.isEmpty()) {
			try {
				ByteBuffer top = writeQueue.peek();
				chan.write(top);
				if (top.hasRemaining()) {
					return;
				} else {
					writeQueue.remove();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
				close();
			}
		}

		if (writeQueue.isEmpty()) {
			if (protocol.shouldTerminate())
				close(); // add remove from connection
			else
				reactor.updateInterestedOps(chan, SelectionKey.OP_READ);
		}
	}

	private static ByteBuffer leaseBuffer() {
		ByteBuffer buff = BUFFER_POOL.poll();
		if (buff == null) {
			return ByteBuffer.allocateDirect(BUFFER_ALLOCATION_SIZE);
		}

		buff.clear();
		return buff;
	}

	private static void releaseBuffer(ByteBuffer buff) {
		BUFFER_POOL.add(buff);
	}

	@Override
	public void send(MyMessage msg) {
		writeQueue.add(ByteBuffer.wrap(encdec.encode(msg)));
		reactor.updateInterestedOps(chan, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

	}

}
