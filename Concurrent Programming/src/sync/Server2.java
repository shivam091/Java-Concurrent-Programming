package sync;

/**
 * Synchronized method to solve Mutual Exclusion Problem. Suppose we have a set
 * of clients which read from and update a server. Two or more clients may read
 * concurrently, but no two clients may update the server concurrently. We use a
 * synchronized method to ensure that the updates are mutually exclusive.
 * 
 * @author shivam
 *
 */
class ServerProcess2 {

	int store;

	public int read() {
		return store;
	}

	public synchronized void update(int n) {
		System.out.println("begin update");
		store = n;
		System.out.println("ending update");
	}
}

class Client2 extends Thread {

	static ServerProcess2 d;

	@SuppressWarnings("static-access")
	public Client2(ServerProcess2 d) {
		this.d = d;
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

public class Server2 {

	static ServerProcess2 d = new ServerProcess2();

	static Client2 c[] = new Client2[5];

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			c[i] = new Client2(d);
			c[i].start();
		}
		;
	}
}
