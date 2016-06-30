import java.io.*;
import java.util.Hashtable;
import java.util.ArrayList;

public class InitializeDB {

	public static void main(String[] args) {
		File file = new File("database.ser");
		if (file.exists()) {
			System.out.println("Database already exists");
			System.exit(0);
			return;
		}
		ChatDB database = new ChatDB();
		try {
        	FileOutputStream fileOut = new FileOutputStream("database.ser");
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(database);
	        out.close();
	        fileOut.close();
	    } catch(IOException i) {
	        i.printStackTrace();
	    }
	}

	public static void printDB(ChatDB database) {
		System.out.println("*Database Contents*");
		Hashtable<String, Replies> callResponsePairs = database.callResponsePairs;
		Replies responses = null;
		ArrayList<String> phrases = null;
		ArrayList<Double> weights = null;
		for (String call : callResponsePairs.keySet()) {
			System.out.println("'" + call + "'" + " :");
			responses = callResponsePairs.get(call);
			phrases = responses.phrases;
			weights = responses.weights;
			for (int i = 0; i < phrases.size(); i++) {
				System.out.println("   '" + phrases.get(i) + "' : " + weights.get(i));
			}
		}
	}
}
