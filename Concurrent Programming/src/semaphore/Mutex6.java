package semaphore;

/**
 * Solution to solve mutual exclusion problem using a semaphore as implemented
 * in the class Sempahore1.
 * 
 * @author shivam
 *
 */
class Process6 extends Thread {

	private int id;
	private Semaphore1 sem;
	static int N;

	public Process6(int i, int n, Semaphore1 s) {
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

public class Mutex6 {

	static int N = 2;

	public static void main(String[] args) {
		System.out.println("Busy Waiting Example:");

		Semaphore1 sem = new Semaphore1(1);

		Process6 p[] = new Process6[N];

		for (int i = 0; i < N; i++) {
			p[i] = new Process6(i, N, sem);
			p[i].start();
		}
	}
}