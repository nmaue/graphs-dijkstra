import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This main class runs the graph program.
 * It will return the shortest length of a path by number of turns
 * and a shortest length of the path by number of intersections
 *
 * @author Nathan Maue
 */
public class Main {

	/**
	 * The main method of the program
	 * @param args Unused
	 */
	public static void main(String[] args){
		String dataFile = JOptionPane.showInputDialog("Enter data file.");
		EdgeObjectGraph<Intersection, String> graph;
		try {
			graph = buildGraph("data/" + dataFile);
		} catch (FileNotFoundException e) {
			System.out.println("Data file not found, please enter full filename and inclue in 'data' folder");
			return;
		} catch (GraphOverflowException e) {
			System.out.println("There are too many vertices to handle, please check the code documentation to fix");
			return;
		}
		Intersection start = new Intersection(JOptionPane.showInputDialog("Enter start cross streets"));
		if (!graph.hasVertex(start)) {
			System.out.println("Start vertex does not exist.");
			return;
		}
		Intersection end = new Intersection(JOptionPane.showInputDialog("Enter end cross streets"));
		if (!graph.hasVertex(start)) {
			System.out.println("End vertex does not exist.");
			return;
		}
		int minPath = graph.getShortestPathWeight(start, end);
		int minTurns = graph.getShortestPathEdgeChanges(start, end);
		System.out.println("Origination: " + start.toString() + "\tDestination: " + end.toString());
		System.out.println("Minimum Path Length: " + minPath + "\tMinimum Turns: " + minTurns);
	}

	/**
	 * This method builds the graph object
	 * @param filename String of the filename to build graph from
	 * @return An EdgeObjectGraph object with intersection vertices and string edges
	 * @throws FileNotFoundException if the filename is not valid
	 * @throws GraphOverflowException if there are more vertices than the max of the graph.
	 * @see EdgeObjectGraph#EdgeObjectGraph(int)
	 */
	public static EdgeObjectGraph<Intersection, String> buildGraph(String filename)
			throws FileNotFoundException, GraphOverflowException {
		EdgeObjectGraph<Intersection, String> mygraph = new EdgeObjectGraph<Intersection, String>();

		File myfile = new File(filename);
		Scanner s = new Scanner(myfile);
		s.nextLine(); // Ignore header
		while (s.hasNextLine()) {
			String buffer = s.nextLine();
			String[] buffArray = buffer.split(";");
			for (int i = 0; i < buffArray.length; i++) {
				Intersection temp = new Intersection(buffArray[i]);
				if (!mygraph.hasVertex(temp)) {
					mygraph.addVertex(temp);
				}
			}
			String[] intersectionArray = buffArray[0].split(",");
			for (int i = 1; i < buffArray.length; i++) {
				String[] tempArray = buffArray[i].split(",");
				String edgeStreet = "";
				for (int j = 0; j < tempArray.length; j++) {
					for (int k = 0; k < intersectionArray.length; k++) {
						if (tempArray[j].equalsIgnoreCase(intersectionArray[k])) {
							edgeStreet = tempArray[j];
						}
					}
				}
				mygraph.addEdge(new Intersection(buffArray[0]), new Intersection(buffArray[i]), edgeStreet);
			}
		}
		return mygraph;
	}

}

 
