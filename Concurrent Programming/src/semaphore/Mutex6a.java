package semaphore;

/**
 * Solution to solve mutual exclusion problem using a semaphore as implemented
 * in the class Sempahore2.
 * 
 * @author shivam
 * 
 */
class Process6a extends Thread {

	private int id;
	private Semaphore2 sem;
	static int N;

	public Process6a(int i, int n, Semaphore2 s) {
		id = i;
		N = n;
		sem = s;
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

	public void run() {
		do {
			noncritical();
			sem.P();
			critical();
			sem.V();
		} while (true);
	}
}

public class Mutex6a {

	static int N = 2; // Number of processes.

	public static void main(String[] args) {
		System.out.println("Busy Waiting Example:");
		Semaphore2 sem = new Semaphore2(1);
		Process6a p[] = new Process6a[N];

		// Configure and start processes.
		for (int i = 0; i < N; i++) {
			p[i] = new Process6a(i, N, sem);
			p[i].start();
		}
	}
}