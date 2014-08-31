package dpp;

import semaphore.Semaphore;

/**
 * Attempt to solve dining philsopers problem. The approach used is to represent
 * each fork by a (binary) semaphore. This version uses a semaphore MAX
 * initialized to one less than the number of philosopers to avoid deadlock.
 * 
 * @author shivam
 * 
 */
class Philsoper2 extends Thread {

	Semaphore left, right, MAX;
	int i;

	public Philsoper2(int i, Semaphore left, Semaphore right, Semaphore MAX) {
		this.i = i;
		this.left = left;
		this.right = right;
		this.MAX = MAX;
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
			MAX.P();
			left.P();
			System.out.println(i + " gets left fork");
			right.P();
			System.out.println(i + " gets right fork");
			busy(i, "eating");
			left.V();
			System.out.println(i + " releases left fork");
			right.V();
			System.out.println(i + " releases right fork");
			MAX.V();
		} while (true);
	}

}

public class DiningPhilsopersProblem2 {
	static int N = 5;
	static Semaphore fork[] = new Semaphore[N];
	static Semaphore MAX = new Semaphore(N - 1);
	static Philsoper2 p[] = new Philsoper2[N];

	public static void main(String[] args) {
		for (int i = 0; i < N; i++)
			fork[i] = new Semaphore(1);
		for (int i = 0; i < N; i++) {
			p[i] = new Philsoper2(i, fork[i], fork[(i + 1) % N], MAX);
			p[i].start();
		}
	}
}