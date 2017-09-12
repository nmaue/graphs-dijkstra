/**
 * Lists all the methods necessary for a queue
 *
 * @param <T> The type of object hed in the queue
 *
 * @author Nathan Maue
 */
public interface QueueInterface<T> {
	/**
	 * Adds element to end of queue
	 * @param element Object type T to add to queue
	 * @throws QueueOverflowException if queue is full
	 */
	void enqueue(T element) throws QueueOverflowException;

	/**
	 * Removes and returns object from rear of queue
	 * @return Object type T from rear of queue
	 * @throws QueueUnderflowException if queue is empty
	 */
	T dequeue() throws QueueUnderflowException;

	/**
	 * Returns true if queue full
	 * @return boolean of if queue full
	 */
	boolean isFull();

	/**
	 * Returns true if queue empty
	 * @return boolean of if queue empty
	 */
	boolean isEmpty();

	/**
	 * Returns current size of queue
	 * @return int of number of items in queue
	 */
	int size();
}




