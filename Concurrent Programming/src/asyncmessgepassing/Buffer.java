package asyncmessgepassing;

import semaphore.Semaphore;

/**
 * Semaphore implementation of a bounded buffer.
 * 
 * @author shivam
 * 
 */
public class Buffer {

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