import java.util.Hashtable;
import java.io.*;

public class ChatDB implements java.io.Serializable {

	public static Hashtable<String, Replies> callResponsePairs;

	public ChatDB() {
		callResponsePairs = new Hashtable<String, Replies>();
	}

	public static void add(String call, String response) {
		if (callResponsePairs.containsKey(call)) {
			callResponsePairs.get(call).add(response);
		} else {
			Replies temp = new Replies();
			temp.add(response);
			callResponsePairs.put(call, temp);
		}
	}

	public static String getResponse(String call) {
		if (callResponsePairs.containsKey(call)) {
			return callResponsePairs.get(call).getResponse();
		} else {
			return call;
		}
	}

}