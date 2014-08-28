package semaphore;

/**
 * An implementation of a general semaphore adapted from Doug Lea's book on
 * concurrent programming in Java. A while(condition) {..wait()..} loop is used
 * to suspend the process invoking a P().
 * 
 * @author shivam
 *
 */
public class Semaphore2 {

	private int value;

	public Semaphore2(int i) {
		value = i;
	}

	public synchronized void P() {
		while (value <= 0)
			try {
				wait();
			} catch (InterruptedException e) {
			}
		value--;
	}

	public synchronized void V() {
		value++;
		notify();
	}
}