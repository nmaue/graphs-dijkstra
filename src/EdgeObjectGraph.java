/**
 * This class defines all the required method to create a graph with the edges as objects
 * @param <V> The vertex object
 * @param <E> The edge object
 *
 * @author Nathan Maue
 */
public class EdgeObjectGraph<V, E> implements EdgeObjectGraphInterface<V, E> {
	private static final int DEFCAP = 50;  // default capacity
	private int numVertices;
	private int maxVertices;
	private V[] vertices;
	private E[][] edges;
	private boolean[] marks;  // marks[i] is mark for vertices[i]

	/**
	 * Constructor using default capacity
	 */
	@SuppressWarnings("unchecked")
	public EdgeObjectGraph() {
		numVertices = 0;
		maxVertices = DEFCAP;
		vertices = (V[]) new Object[DEFCAP];
		marks = new boolean[DEFCAP];
		edges = (E[][]) new Object[DEFCAP][DEFCAP];
	}

	/**
	 * Constructor specifying the max capacity
	 * @param maxV An int of the maximum number of vertices
	 */
	@SuppressWarnings("unchecked")
	public EdgeObjectGraph(int maxV) {
		numVertices = 0;
		maxVertices = maxV;
		vertices = (V[]) new Object[maxV];
		marks = new boolean[maxV];
		edges = (E[][]) new Object[maxV][maxV];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return numVertices == 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isFull() {
		return numVertices == maxVertices;
	}

	/**
	 * {@inheritDoc}
	 */
	public void addVertex(V vertex) throws GraphOverflowException{
		if (isFull()) {
			throw new GraphOverflowException();
		}
		vertices[numVertices] = vertex;
		numVertices++;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasVertex(V vertex) {
		if (indexIs(vertex) >= 0) {
			return true;
		}
		return false;
	}

	/**
	 * Get index of vertex
	 * @param vertex Object type V to find index of
	 * @return int of the index, if not in graph: -1
	 */
	private int indexIs(V vertex) {
		for (int index = 0; index < vertices.length; index++) {
			if (vertex.equals(vertices[index])) {
				return index;
			}
		}
		return -1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addEdge(V fromVertex, V toVertex, E edge) {
		int row;
		int column;

		row = indexIs(fromVertex);
		column = indexIs(toVertex);
		edges[row][column] = edge;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E getEdge(V fromVertex, V toVertex) {
		int row;
		int column;

		row = indexIs(fromVertex);
		column = indexIs(toVertex);
		return edges[row][column];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public QueueInterface<V> getToVertices(V vertex) {
		LinkedQueue<V> adjVertices = new LinkedQueue<V>();
		int fromIndex;
		int toIndex;
		fromIndex = indexIs(vertex);
		for (toIndex = 0; toIndex < numVertices; toIndex++) {
			if (edges[fromIndex][toIndex] != null) {
				adjVertices.enqueue(vertices[toIndex]);
			}
		}
		return adjVertices;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clearMarks() {
		for (int i = 0; i < numVertices; i++) {
			marks[i] = false;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void markVertex(V vertex) {
		int index = indexIs(vertex);
		marks[index] = true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isMarked(V vertex) {
		int index = indexIs(vertex);
		return marks[index];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public V getUnmarked() {
		for (int i = 0; i < numVertices; i++) {
			if (!marks[i]) {
				return vertices[i];
			}
		}
		return null;
	}

	/**
	 * Returns if edge exists between 2 vertices
	 * Assumes the 2 vertices are in the set of vertices. (Otherwise throws an array out of bounds exception)
	 * @param fromVertex Object type V that is start vertex
	 * @param toVertex Object type V that is end vertex
	 * @return boolean of if edge exists between the 2 vertices
	 */
	public boolean edgeExists(V fromVertex, V toVertex) {
		return (edges[indexIs(fromVertex)][indexIs(toVertex)] != null);
	}

	/**
	 * Removes edge and returns true on successful removal
	 * Assumes the 2 vertices are in the set of vertices. (Otherwise throws an array out of bounds exception)
	 * @param fromVertex Object of type V that is start vertex
	 * @param toVertex Object of type V that is end vertex
	 * @return true if there was an edge and it has been removed, false if no edge existed
	 */
	public boolean removeEdge(V fromVertex, V toVertex) {
		boolean existed = edgeExists(fromVertex, toVertex);
		edges[indexIs(fromVertex)][indexIs(toVertex)] = null;
		return existed;
	}

	/**
	 * Returns the weight of the shortest path (by weight)
	 * @param start Object of type V that is start vertex
	 * @param end Object of type V that is end vertex
	 * @return int of weight of shortest path (by weight)
	 */
	public int getShortestPathWeight(V start, V end) {
		int[] weights = new int[numVertices];
		for (int i = 0; i < weights.length; i++) {
			weights[i] = -1; // So we're clear it's unknown, we use -1
		}
		this.clearMarks();
		V current = start;
		weights[indexIs(start)] = 0; // Path from start to start is 0
		while (true) {
			this.markVertex(current);
			int currentWeight = weights[indexIs(current)];
			QueueInterface<V> adjacent = this.getToVertices(current);
			while (!adjacent.isEmpty()) {
				V next = adjacent.dequeue();
				E edge = this.getEdge(current, next);
				int weight;
				try {
					weight = edge.getClass().getField("weight").getInt(edge);
				} catch (NoSuchFieldException | IllegalAccessException e) {
					weight = 1;
				}
				int nextWeight = currentWeight + weight;
				int nextIndex = indexIs(next);
				if (nextWeight < weights[nextIndex] || weights[nextIndex] == -1) {
					weights[nextIndex] = nextWeight;
				}
			}
			V nextToVisit = null;
			for (int i = 0; i < numVertices; i++) {
				if(marks[i] || weights[i] == -1) { // If marked or weight unknown, we can't go there next
					continue;
				}
				if (nextToVisit == null || weights[i] < weights[indexIs(nextToVisit)]) {
					nextToVisit = vertices[i];
				}
			}
			if (nextToVisit == null) {break;} // All of the vertices have been visited (that can be)
			current = nextToVisit;
		}
		return weights[indexIs(end)];
	}

	/**
	 * Returns the number of edge changes in the shortest path (by edge changes)
	 * @param start Object of type V that is start vertex
	 * @param end Object of type V that is end vertex
	 * @return int of number of edge changes
	 */
	@SuppressWarnings("unchecked")
	public int getShortestPathEdgeChanges(V start, V end) {
		int[] changes = new int[numVertices];
		E[] previousEdges = (E[]) new Object[numVertices];
		for (int i = 0; i < changes.length; i++) {
			changes[i] = -1; // So we're clear it's unknown, we use -1
		}
		this.clearMarks();
		V current = start;
		changes[indexIs(start)] = 0; // Path from start to start is 0
		while (true) {
			this.markVertex(current);
			int index = indexIs(current);
			int currentChanges = changes[index];
			QueueInterface<V> adjacent = this.getToVertices(current);
			while (!adjacent.isEmpty()) {
				V next = adjacent.dequeue();
				E edge = this.getEdge(current, next);
				int change = 1;
				if (previousEdges[index] != null && previousEdges[index].equals(edge)) {
					change = 0;
				}
				int nextChanges = currentChanges + change;
				int nextIndex = indexIs(next);
				if (nextChanges < changes[nextIndex] || changes[nextIndex] == -1) {
					changes[nextIndex] = nextChanges;
					previousEdges[nextIndex] = edge;
				}
			}
			V nextToVisit = null;
			for (int i = 0; i < numVertices; i++) {
				if(marks[i] || changes[i] == -1) { // If marked or changes unknown, we can't go there next
					continue;
				}
				if (nextToVisit == null || changes[i] < changes[indexIs(nextToVisit)]) {
					nextToVisit = vertices[i];
				}
			}
			if (nextToVisit == null) {break;} // All of the vertices have been visited (that can be)
			current = nextToVisit;
		}
		return changes[indexIs(end)] - 1; // We are counting the initial change (From null to some thing) so we subtract 1 to compensate
	}
}
