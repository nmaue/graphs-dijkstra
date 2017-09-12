/**
 * The node of a single linked list
 * @param <T> The type of object to hold in the list
 *
 * @author Nathan Maue
 */
public class LLNode<T> {
	protected LLNode<T> link;
	protected T info;

	/**
	 * Constructor for node
	 * @param info Object type T to hold in node
	 */
	public LLNode(T info) {
		this.info = info;
		link = null;
	}

	/**
	 * Returns object in node
	 * @return Object type T contained in node
	 */
	public T getInfo() {
		return info;
	}

	/**
	 * Sets object in node
	 * @param info Object type T to store in node
	 */
	public void setInfo(T info) {
		this.info = info;
	}

	/**
	 * Get the next node in list
	 * @return LLNode object of type T next in list
	 */
	public LLNode<T> getLink() {
		return link;
	}

	/**
	 * Set next node in list
	 * @param link LLNode object of type T to make next in list
	 */
	public void setLink(LLNode<T> link) {
		this.link = link;
	}
}
 
 