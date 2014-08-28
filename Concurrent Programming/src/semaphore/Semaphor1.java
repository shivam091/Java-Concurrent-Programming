package semaphore;

/**
 * This is an implementation of a general semaphore using Java's `wait' and
 * `notify' for suspending and re-awaking processes.
 * 
 * @author shivam
 *
 */
final class Semaphore1 {

	private int value;
	private int waiting = 0; // Number of process currently waiting.

	public Semaphore1(int i) {
		value = i;
	}

	synchronized void P() {
		if (value > 0)
			value--;
		else {
			waiting++;
			try {
				wait();
			} catch (InterruptedException e) {
			}
			;
			waiting--;
		}
		;
	}

	synchronized void V() {
		if (waiting == 0)
			value++;
		else
			notify();
	}
}
