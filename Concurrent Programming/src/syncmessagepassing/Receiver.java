package syncmessagepassing;

/**
 * Receiver class used to test our handshake class Handshake2.
 * 
 * @author shivam
 * 
 */
public class Receiver extends Thread {

	private int pause = 500; // Milliseconds between receiving messages.
	private String id;
	private Handshake2 handshake2;

	public Receiver(String s, Handshake2 h) {
		id = s;
		handshake2 = h;
	}

	public void run() {
		do {
			try {
				sleep((int) Math.round(pause * Math.random()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			;
			System.out.println(id + " has received " + handshake2.receive());
		} while (true);
	}
}