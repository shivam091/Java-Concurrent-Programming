package dpp;

import semaphore.Semaphore;

/**
 * Attempt to solve dining philsopers problem. The approach used is to represent
 * each fork by a (binary) semaphore. This version picks up both forks at once
 * by putting the two actions in a synchronized block.
 * 
 * @author shivam
 * 
 */
class Philsoper3 extends Thread {

	static final Object lock = new Object();

	Semaphore left, right;
	int i;

	public Philsoper3(int i, Semaphore left, Semaphore right) {
		this.i = i;
		this.left = left;
		this.right = right;
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
			sleep(random(0));
		} catch (InterruptedException e) {
		}
	}

	public void run() {
		do {
			busy(i, "thinking");
			synchronized (lock) {
				left.P();
				System.out.println(i + " gets left fork");
				right.P();
				System.out.println(i + " gets right fork");
				busy(i, "eating");
			}
			left.V();
			System.out.println(i + " releases left fork");
			right.V();
			System.out.println(i + " releases right fork");
		} while (true);
	}
}

public class DiningPhilsopersProblem3 {
	static int N = 5;
	static Semaphore fork[] = new Semaphore[N];
	static Philsoper3 p[] = new Philsoper3[N];

	public static void main(String[] args) {
		for (int i = 0; i < N; i++)
			fork[i] = new Semaphore(1);
		for (int i = 0; i < N; i++) {
			p[i] = new Philsoper3(i, fork[i], fork[(i + 1) % N]);
			p[i].start();
		}
	}
}