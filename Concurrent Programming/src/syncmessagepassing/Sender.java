package syncmessagepassing;

/**
 * Sender class used to test our handshake class Handshake2.
 * 
 * @author shivam
 * 
 */
public class Sender extends Thread {

	private int pause = 50; // Milliseconds between sending messages.
	private String id;
	private Handshake2 handshake2;

	public Sender(String s, Handshake2 h) {
		id = s;
		handshake2 = h;
	}

	public void run() {
		int i = 1;

		do {
			try {
				sleep((int) Math.round(pause * Math.random()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			;
			handshake2.send(new Integer(i));
			System.out.println(id + " has sent " + i++);
		} while (true);
	}
}