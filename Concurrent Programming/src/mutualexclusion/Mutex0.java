package mutualexclusion;

/**
 * Busy waiting attempts to solve mutual exclusion problem with no
 * synchronization. When program is run, critical sections will overlap as there
 * is no pre and post protocol.
 * 
 * @author shivam
 * 
 */

class Process extends Thread {

	private int id;

	public Process(int i) {
		id = i;
	}

	private int random(int n) {
		return (int) Math.round(n * Math.random() - 0.5);
	}

	private void busy() {
		try {
			sleep(random(500));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void non_critical() {
		System.out.println("Process " + id + " is NON critical");
		busy();
	}

	private void pre_protocol() {
	}

	private void critical() {
		System.out.println("Process " + id + " is entering critical section.");
		busy();
		System.out.println("Process " + id + " is leaving critical section.");
	}

	private void post_protocol() {
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

public class Mutex0 {
	static int N = 3; // Number of processes participating.

	public static void main(String[] args) {
		Process p[] = new Process[N];
		// Configure and start processes p[0] ... p[N-1]
		for (int i = 0; i < N; i++) {
			p[i] = new Process(i);
			p[i].start();
		}
	}
}