
public class LinkedQueue<T> implements QueueInterface<T> {
	protected LLNode<T> front;     // reference to the front of this queue
	protected LLNode<T> rear;      // reference to the rear of this queue
	protected int numElements = 0; // number of elements in this queue

	/**
	 * Constructor for the queue
	 */
	public LinkedQueue() {
		front = null;
		rear = null;
	}

	/**
	 * Adds element to end of queue
	 * @param element Object type T to add to queue
	 */
	public void enqueue(T element) {

		LLNode<T> newNode = new LLNode<T>(element);
		if (rear == null) {
			front = newNode;
		} else {
			rear.setLink(newNode);
		}
		rear = newNode;
		numElements++;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException("Dequeue attempted on empty queue.");
		} else {
			T element;
			element = front.getInfo();
			front = front.getLink();
			if (front == null) {
				rear = null;
			}
			numElements--;
			return element;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return (front == null);
	}

	/**
	 * Returns false, a linked queue is never full
	 * @return false
	 */
	@Override
	public boolean isFull() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return numElements;
	}

}

