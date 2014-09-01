package syncmessagepassing;

public class MessageTest {

	public static void main(String[] args) {

		System.out.println("Passing Messages Synchronously");

		Handshake2 h = new Handshake2();

		Sender s1 = new Sender("s1", h);
		Receiver r1 = new Receiver("r1", h);
		Receiver r2 = new Receiver("r2", h);

		s1.start();
		r1.start();
		r2.start();
	}
}