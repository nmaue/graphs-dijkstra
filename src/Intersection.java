import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Represents an intersection of 2 streets on our maps
 *
 * @author Nathan Maue
 */
public class Intersection {
	ArrayList<String> crossStreets = new ArrayList<String>();

	/**
	 * Constructor for the class
	 * @param streets A string of the streets at the intersection, separated by commas (",")
	 */
	public Intersection(String streets) {
		String[] streetArray = streets.split(",");
		crossStreets.addAll(Arrays.asList(streetArray));
		Collections.sort(crossStreets);
	}

	/**
	 * toString becomes a string of all streets in the intersection, separated commas (",")
	 * @return A string of all the streets in the intersection, separated by commas (",")
	 */
	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		for (String street : crossStreets) {
			output.append(street);
			if(!street.equals(crossStreets.get(crossStreets.size()-1))) {
				output.append(",");
			}
		}
		return output.toString();
	}

	/**
	 * Overrides equals, compares intersections' string representations
	 * @param obj The object to compare to
	 * @return boolean of if these intersections contain the same streets
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		} else {
			Intersection intersection = (Intersection) obj;
			return this.toString().equalsIgnoreCase(intersection.toString());
		}
	}
}
