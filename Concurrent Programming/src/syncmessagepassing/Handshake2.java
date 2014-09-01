package syncmessagepassing;

/**
 * A handshake implemented using Java's while (condition) {...wait()...}
 * suspension loop. This is a class with synchronized methods send and receive
 * defined using Java's while (condition) {...wait()...} suspension loop. Add
 * SR's st for peeking at messages to handshake2.
 * 
 * @author shivam
 * 
 */
public class Handshake2 {
	private Object local;
	private boolean writable = true;

	public synchronized void send(Object x) {
		while (!writable) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// Wait here until writable becomes true.
		}
		;
		local = x;
		writable = false;
		notify();
	}

	public synchronized Object receive() {
		while (writable) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// Wait here until writable becomes false.
			// Note, the message is readable if and only if
			// it is not writable.
		}
		;
		writable = true;
		notify();
		return (local);
	}
}
