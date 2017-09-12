/**
 * Exception thrown if a graph is full
 *
 * @author Nathan Maue
 */
public class GraphOverflowException extends Exception {

	/**
	 * Exception constructor
	 */
	public GraphOverflowException() {
		super();
	}

	/**
	 * Exception constructor with message
	 * @param message String of message to pass to exception
	 */
	public GraphOverflowException(String message) {
		super(message);
	}
}