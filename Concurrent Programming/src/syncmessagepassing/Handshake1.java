package syncmessagepassing;

import semaphore.Semaphore2;

/**
 * A handshake implemented using general semaphores. This is a class with
 * methods send and receive defined using class semaphore2.
 * 
 * @author shivam
 * 
 */
public class Handshake1 {
	private Object local;
	Semaphore2 semin = new Semaphore2(0);
	Semaphore2 semout = new Semaphore2(0);

	public void send(Object x) {
		local = x; // Write message.
		semin.V(); // Signal that message is now written.
		semout.P(); // Wait here until message has been read.
	}

	public Object receive() {
		semin.P(); // Wait here until message has been written.
		semout.V(); // Signal that message has been read.
		return (local); // Read message. (Note : Java insists that the
						// `return' must be the last statement in a
						// returning method, which is why the last two
						// statements appear to be the wrong way round.)
	}
}
