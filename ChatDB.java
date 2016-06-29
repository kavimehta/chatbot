import java.util.Hashtable;
import java.io.*;

public class ChatDB implements java.io.Serializable {

	public Hashtable<String, Replies> callResponsePairs;

	public ChatDB() {
		callResponsePairs = new Hashtable<String, Replies>();
	}

	public ChatDB(ChatDB database) {
		callResponsePairs = database.callResponsePairs;
	}

	/* Add the call-response pair to this database. */
	public void add(String call, String response) {
		if (callResponsePairs.containsKey(call)) {
			callResponsePairs.get(call).add(response);
		} else {
			Replies temp = new Replies();
			temp.add(response);
			callResponsePairs.put(call, temp);
		}
	}

	/* Return a response based on whether or not the call is in the database. */
	public String getResponse(String call) {
		if (callResponsePairs.containsKey(call)) {
			Replies options = callResponsePairs.get(call);
			// If there is only one option, there is a chance to return the call.
			if ((options.phrases.size() == 1) && (Math.random() < 0.15)) {
				return call;
			} else {
				return callResponsePairs.get(call).getResponse();
			}
		} else {
			int distance;
			int minDistance = Integer.MAX_VALUE;
			String closestMatch = "";
			for (String phrase : callResponsePairs.keySet()) {
				distance = Distance.LD(call, phrase);
				if (distance < minDistance) {
					minDistance = distance;
					closestMatch = phrase;
				}
			}
			// If the levenshtein distance is sufficiently small or by random chance, return the closest match.
			if ((minDistance <= call.length()/3) || (Math.random() < 0.35)) {
				return getResponse(closestMatch);
			} else {
				return call;
			}
		}
	}

}
