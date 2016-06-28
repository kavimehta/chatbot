import java.util.ArrayList;

public class Replies {

	public static ArrayList<String> phrases;
	public static ArrayList<Integer> weights;

	public Replies() {
		phrases = new ArrayList<String>();
		weights = new ArrayList<Integer>();
	}

	public static void add(String response) {
		if (phrases.contains(response)) {
			int index = phrases.indexOf(response);
			weights.set(index, weights.get(index) + 1);
		} else {
			phrases.add(response);
			weights.add(1);
		}
	}
}