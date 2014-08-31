package asyncmessgepassing;

import semaphore.Semaphore;

/**
 * Producer Consumer Problem.
 * @author shivam
 *
 */
class BufferForProducerConsumer {

	private int size = 5;

	private Object store[] = new Object[size];
	private int inptr = 0;
	private int outptr = 0;

	Semaphore spaces = new Semaphore(size);
	Semaphore elements = new Semaphore(0);

	public void deposit(Object value) {
		spaces.P();
		store[inptr] = value;
		inptr = (inptr + 1) % size;
		elements.V();
	}

	public Object fetch() {
		Object value;
		elements.P();
		value = store[outptr];
		outptr = (outptr + 1) % size;
		spaces.V();
		return value;
	}
}

class Producer extends Thread {
	BufferForProducerConsumer buf;
	
	public Producer(BufferForProducerConsumer buf) {
		this.buf = buf;
	}

	public void run() {
		for (int i = 1; i <= 50; i++)
			buf.deposit(new Integer(i));
	}
}

class Consumer extends Thread {
	BufferForProducerConsumer buf;

	public Consumer(BufferForProducerConsumer buf) {
		this.buf = buf;
	}

	public void run() {
		for (int i = 1; i <= 50; i++)
			System.out.println(buf.fetch());
	}
}

public class ProducerConsumerProblem {
	static BufferForProducerConsumer buf = new BufferForProducerConsumer();

	public static void main(String[] args) {
		Producer P = new Producer(buf);
		Consumer C = new Consumer(buf);
		P.start();
		C.start();
	}
}