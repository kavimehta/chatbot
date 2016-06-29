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

	/* If the call is in this database, return a response. Otherwise, return the call. */
	/* [TO DO] Use levenshtein distance to use a response if the call is "close enough" to an existing one. */
	public String getResponse(String call) {
		if (callResponsePairs.containsKey(call)) {
			return callResponsePairs.get(call).getResponse();
		} else {
			return call;
		}
	}

}
