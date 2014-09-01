package readerwriter;

import semaphore.Semaphore;

/**
 * Solving readers/writers problems using semaphores mutex and writing.
 * @author shivam	
 *
 */
class Database1 {
	// database goes here
}

class ReaderWriterProcess1 extends Thread {

	static Database1 d;
	int i;
	static Semaphore mutex = new Semaphore(1);
	static Semaphore writing = new Semaphore(1);
	static int readers = 0;

	public ReaderWriterProcess1(int i) {
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
		System.out.println(i + " is" + s);
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
			mutex.P(); 				// Reading pre protocol
			readers++;						 // |
			if (readers == 1)				 // |	
				writing.P(); 				 // |
			mutex.V(); 						// \|/

			busy(i, " reading"); 		  // Reading

			mutex.P(); 				// Reading post protocol
			readers--; 						 // |
			if (readers == 0)				 // |
				writing.V(); 				 // |
			mutex.V(); 						// \|/

			writing.P(); 			// Writing pre protocol

			busy(i, " writing"); 		  // Writing

			writing.V(); 			// Writing post protocol

		} while (true);
	}
}

public class ReaderWriter1 {
	static int N = 3;

	public static void main(String[] args) {
		ReaderWriterProcess1 p[] = new ReaderWriterProcess1[N];

		for (int i = 0; i < N; i++) {
			p[i] = new ReaderWriterProcess1(i);
			p[i].start();
		}
	}
}