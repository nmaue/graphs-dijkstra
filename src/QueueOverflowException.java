/**
 * Exception thrown if a queue is full
 *
 * @author Nathan Maue
 */
public class QueueOverflowException extends Exception {

	/**
	 * Exception constructor
	 */
	public QueueOverflowException() {
		super();
	}

	/**
	 * Exception constructor with message
	 * @param message String of message to pass to exception
	 */
	public QueueOverflowException(String message) {
		super(message);
	}
}