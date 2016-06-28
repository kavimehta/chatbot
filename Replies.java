import java.util.ArrayList;

public class Replies implements java.io.Serializable {

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

	public static String getResponse() {
		int total = 0;
		for (int w : weights) {
			total += w;
		}
		int randomIndex = 0;
		double random = Math.random() * total;
		for (int i = 0; i < phrases.size(); i++) {
			random -= weights.get(i);
			if (random <= 0) {
				randomIndex = i;
				break;
			}
		}
		return phrases.get(randomIndex);
	}
	
}