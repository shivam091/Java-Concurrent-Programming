package mutualexclusion;

/**
 * Busy waiting attempts to solve the mutual exclusion problem using a `turn'
 * variable to ensure mutual exclusion, and Java's yield() from class Thread for
 * being busy. First attempt at solving Mutual Exclusion Problem using a 'turn'
 * variable as a baton.
 * 
 * @author shivam
 *
 */
class Process1a extends Thread {

	static int turn = 0;
	private int id;
	static int N;

	public Process1a(int i, int n) {
		id = i;
		N = n;
	}

	private int random(int n) {
		return (int) Math.round(n * Math.random() - 0.5);
	}

	private void busy() {
		try {
			sleep(random(500));
		} catch (InterruptedException e) {
		}
	}

	private void noncritical() {
		System.out.println("Process " + id + " is NON critical.");
		busy();
	}

	private void critical() {
		System.out.println("Process " + id + " is entering critical section.");
		busy();
		System.out.println("Process " + id + " is leaving critical section.");
	}

	private void preprotocol() {
		while (turn != id)
			yield();
	}

	private void postprotocol() {
		turn = random(N);
		System.out.println("turn = " + turn);
	}

	public void run() {
		do {
			noncritical();
			preprotocol();
			critical();
			postprotocol();
		} while (true);
	}
}

public class Mutex1a {
	static int N = 2; // Number of processes.

	public static void main(String[] args) {
		System.out.println("Busy Waiting Example:");
		Process1a p[] = new Process1a[N];
		// Configure and start processes.
		for (int i = 0; i < N; i++) {
			p[i] = new Process1a(i, N);
			p[i].start();
		}
	}
}