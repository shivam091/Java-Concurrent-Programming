package readerwriter;

import semaphore.Semaphore;

/**
 * Solving reader/writer problem using a synchronized block and writing.
 * @author shivam
 *
 */
class Database2 {
	// database goes here
}

class ProcessForReaderWriter2 extends Thread {

	static Database2 d;
	int i;
	static final Object lock = new Object();
	static Semaphore writing = new Semaphore(1);
	static int readers = 0;

	public ProcessForReaderWriter2(int i) {
		this.i = i;
	}

	/**
	 * method for random number generator.
	 * 
	 * @param n
	 * @return randomly generated integer.
	 */
	private int random(int n) {
		return (int) Math.round(n * Math.random() - 0.5);
	}

	/**
	 * This method is used to 'kill time' while a philosopher thinks or eats.
	 * 
	 * @param i
	 *            process number.
	 * @param s
	 *            operation being performed by process i.
	 */
	private void busy(int i, String s) {
		System.out.println(i + " is " + s);
		try {
			sleep(random(1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		;
		System.out.println(i + s + " done");
	}

	public void run() {
		do {
			synchronized (lock) { // Reader's pre protocol
				readers++; // |
				if (readers == 1)
					writing.P(); // |
			}
			; // \|/

			busy(i, " reading"); // Reading

			synchronized (lock) { // Reading post protocol
				readers--; // |
				if (readers == 0)
					writing.V(); // |
			}
			; // \|/

			writing.P(); // Writing pre protocol

			busy(i, " writing"); // Writing

			writing.V(); // Writing post protocol

		} while (true);
	}
}

public class ReaderWriter2 {

	static int N = 3;

	public static void main(String[] args) {

		ProcessForReaderWriter2 p[] = new ProcessForReaderWriter2[N];

		for (int i = 0; i < N; i++) {
			p[i] = new ProcessForReaderWriter2(i);
			p[i].start();
		}
	}

}