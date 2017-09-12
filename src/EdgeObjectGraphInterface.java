/**
 * This interface lists all the required method to create a graph with the edges as objects
 * @param <V> The vertex object
 * @param <E> The edge object
 *
 * @author Nathan Maue
 */
public interface EdgeObjectGraphInterface<V, E> {

	/**
	 * Returns if graph is empty
	 * @return boolean of if graph empty
	 */
	boolean isEmpty();

	/**
	 * Returns if graph is full
	 * @return boolean of if graph full
	 */
	boolean isFull();

	/**
	 * Adds vertex to graph
	 * Vertex should not be null or already in graph
	 * @param vertex Object of type V to add to graph
	 * @throws GraphOverflowException if graph is full
	 */
	void addVertex(V vertex) throws GraphOverflowException;

	/**
	 * Checks if vertex in Graph
	 * @param vertex Object type V to see if in graph
	 * @return boolean of if vertex in graph
	 */
	boolean hasVertex(V vertex);

	/**
	 * Adds edge object to graph
	 * Assumes the 2 vertices are in the set of vertices. (Otherwise throws an array out of bounds exception)
	 * @param fromVertex Object type V that is start vertex of edge
	 * @param toVertex Object type V that is end vertex of edge
	 * @param edge Object type E that is the edge to add
	 */
	void addEdge(V fromVertex, V toVertex, E edge);

	/**
	 * Returns edge of graph between 2 vertices
	 * Assumes the 2 vertices are in the set of vertices. (Otherwise throws an array out of bounds exception)
	 * @param fromVertex Object type V that is start of edge
	 * @param toVertex Object of type V that is end of edge
	 * @return Object type E between 2 vertices, null if doesn't exist
	 */
	E getEdge(V fromVertex, V toVertex);

	/**
	 * Returns a queue of vertices that are adjacent to a vertex
	 * @param vertex Object type V to get adjacent vertices of
	 * @return A Linked Queue of V objects
	 */
	QueueInterface<V> getToVertices(V vertex);

	/**
	 * Unmarks all vertices
	 */
	void clearMarks();

	/**
	 * Marks input vertex
	 * Assumes the vertex is in the set of vertices. (Otherwise throws an array out of bounds exception)
	 * @param vertex Object type V that is vertex to mark
	 */
	void markVertex(V vertex);

	/**
	 * Returns boolean of if given vertex marked
	 * Assumes the vertex is in the set of vertices. (Otherwise throws an array out of bounds exception)
	 * @param vertex Object type V that is vertex to check if marked
	 * @return boolean of if vertex marked
	 */
	boolean isMarked(V vertex);

	/**
	 * Returns an unmarked vertex if one exists, otherwise returns null
	 * @return Object type V of unmarked vertex or null if none exist
	 */
	V getUnmarked();
}
