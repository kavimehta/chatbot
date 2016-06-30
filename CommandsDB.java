import java.io.*;
import java.util.Hashtable;
import java.util.ArrayList;

public class CommandsDB {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("You must provide an argument - either print or initialize");
		} else if (args[0].equals("print")) {
			printDB();
			return;
		} else if (args[0].equals("initialize")) {
			initializeDB();
			return;
		}
	}

	public static void printDB() {
		ChatDB database = null;
		try {
	        FileInputStream fileIn = new FileInputStream("database.ser");
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	        database = (ChatDB) in.readObject();
	        in.close();
	        fileIn.close();
	    } catch(IOException i) {
	        i.printStackTrace();
	        System.out.println("Failed to load database");
	        System.exit(0);
	        return;
	    } catch(ClassNotFoundException c) {
	        c.printStackTrace();
	        System.out.println("Failed to load database");
	        System.exit(0);
	        return;
	    }

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

	public static void initializeDB() {
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
}
