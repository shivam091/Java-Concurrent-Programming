package mutualexclusion;

/**
 * Busy waiting attempts to solve the mutual exclusion problem using flag of
 * intension approach; this is UNSAFE attempt.
 * 
 * @author shivam
 *
 */

class Process2 extends Thread {

	static boolean flag[];
	private int id;
	static int N;

	public Process2(int i, int n) {
		id = i;
		N = n;
		flag = new boolean[N];
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

	private boolean wait(int i) {
		boolean r = false;

		for (int j = 0; j < N; j++)
			if (j != i)
				r = r | flag[j];

		return r;
	}

	private void preprotocol() {
		do {
		} while (wait(id));
		flag[id] = true;
	}

	private void postprotocol() {
		flag[id] = false;
	}

	public void run() {
		for (int i = 0; i < N; i++)
			flag[i] = false;

		do {
			noncritical();
			preprotocol();
			critical();
			postprotocol();
		} while (true);
	}
}

public class Mutex2 {

	static int N = 2; // Number of processes.

	public static void main(String[] args) {
		System.out.println("Busy Waiting Example:");
		Process2 p[] = new Process2[N];
		// Configure and start processes.
		for (int i = 0; i < N; i++) {
			p[i] = new Process2(i, N);
			p[i].start();
		}
	}
}