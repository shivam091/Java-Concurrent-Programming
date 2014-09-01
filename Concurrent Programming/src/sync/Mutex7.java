package sync;

/**
 * Synchronized block solution to solve Mutual Exclusion Problem. This version
 * incorporates the pre and post protocols into a synchronized block with an
 * object lock.
 * 
 * @author shivam
 *
 */
class Process7 extends Thread {

	private int id;

	static final Object lock = new Object();

	public Process7(int i) {
		id = i;
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
		System.out.println("Process " + id + " is NON critical.");
		busy();
	}

	private void critical() {
		System.out.println("Process " + id + " is entering critical section.");
		busy();
		System.out.println("Process " + id + " is leaving critical section.");
	}

	public void run() {
		do {
			non_critical();
			synchronized (lock) {
				critical();
			}
			;
		} while (true);
	}
}

public class Mutex7 {
	static int N = 2; // Number of processes.

	public static void main(String[] args) {
		Process7 p[] = new Process7[N];
		// Configure and start processes p[0] ... p[N-1]
		for (int i = 0; i < N; i++) {
			p[i] = new Process7(i);
			p[i].start();
		}
	}
}
