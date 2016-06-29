import java.util.ArrayList;

public class Replies implements java.io.Serializable {

	public static ArrayList<String> phrases;
	public static ArrayList<Double> weights;

	public Replies() {
		phrases = new ArrayList<String>();
		weights = new ArrayList<Double>();
	}

	/* If the response is already in the replies, then increase its weight. Otherwise, add it with weight 1. */
	public static void add(String response) {
		if (phrases.contains(response)) {
			int index = phrases.indexOf(response);
			weights.set(index, weights.get(index) + 1);
		} else {
			phrases.add(response);
			weights.add(1.0d);
		}
	}

	/* Return a weighted random response. */
	public static String getResponse() {
		double total = 0.0d;
		for (double w : weights) {
			total += w;
		}
		int randomIndex = 0;
		double random = Math.random() * total;
		for (int i = 0; i < phrases.size(); i++) {
			random -= weights.get(i);
			if (random <= 0.0d) {
				randomIndex = i;
				break;
			}
		}
		return phrases.get(randomIndex);
	}

}