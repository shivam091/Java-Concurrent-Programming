package sync;

/**
 * Synchronized block to solve Mutual Exclusion Problem. Suppose we have
 * a set of clients which read from and update a server. Two or more clients may
 * read concurrently, but no two clients may update the server concurrently. We
 * use a synchronized block to ensure that the updates are mutually exclusive.
 * 
 * @author shivam
 *
 */
class ServerProcess1 {

	int store;

	public int read() {
		return store;
	}

	public void update(int n) {
		synchronized (this) {
			System.out.println("begin update");
			store = n;
			System.out.println("ending update");
		}
	}
}

class Client1 extends Thread {

	static ServerProcess1 d;

	@SuppressWarnings("static-access")
	public Client1(ServerProcess1 d2) {
		this.d = d2;
	}

	private int random(int n) {
		return (int) Math.round(n * Math.random() - 0.5);
	}

	public void run() {
		do {
			System.out.println(d.read());
			d.update(random(10));
		} while (true);
	}

}

public class Server1 {
	static ServerProcess1 d = new ServerProcess1();
	static Client1 c[] = new Client1[5];

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			c[i] = new Client1(d);
			c[i].start();
		}
		;
	}
}