/**
 * Exception thrown if a queue is empty
 *
 * @author Nathan Maue
 */
public class QueueUnderflowException extends RuntimeException {
	/**
	 * Exception constructor
	 */
	public QueueUnderflowException() {
		super();
	}

	/**
	 * Exception constructor with message
	 * @param message String of message to pass to exception
	 */
	public QueueUnderflowException(String message) {
		super(message);
	}
}