package mutualexclusion;

/**
 * Busy waiting attempts to solve the mutual exclusion problem using a `turn'
 * variable to ensure mutual exclusion, and a while (true) {...} loop for being
 * busy. This is the first attempt at solving Mutual Exclusion Problem using a
 * 'turn' variable as a baton.
 * 
 * @author shivam
 *
 */
class Process1 extends Thread {

	static int turn = 0;
	private int id;
	static int N;

	public Process1(int i, int n) {
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

	private void non_critical() {
		System.out.println("Process " + id + " is NON critical");
		busy();
	}

	private void critical() {
		System.out.println("Process " + id + " is entering critical section.");
		busy();
		System.out.println("Process " + id + " is leaving critical section.");
	}

	private void pre_protocol() {
		do {
		} while (turn != id);
	}

	private void post_protocol() {
		turn = random(N);
		System.out.println("turn = " + turn);
	}

	public void run() {
		do {
			non_critical();
			pre_protocol();
			critical();
			post_protocol();
		} while (true);
	}
}

public class Mutex1 {
	static int N = 2; // Number of processes.

	public static void main(String[] args) {
		Process1 p[] = new Process1[N];
		// Configure and start processes.
		for (int i = 0; i < N; i++) {
			p[i] = new Process1(i, N);
			p[i].start();
		}
	}
}