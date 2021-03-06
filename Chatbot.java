import java.util.*;
import java.io.*;

public class Chatbot {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String rogerMessage = "";
		String userMessage = "";
		ChatDB database = loadDB();
		while (in.hasNext()) {
			String phrase = in.nextLine();
			if (phrase.equals("quit")) {
				writeDB(database);
				in.close();
				return;
			} else {
				userMessage = phrase;
				store(database, rogerMessage, userMessage);
				rogerMessage = getResponse(database, userMessage);
				System.out.println("Roger: " + rogerMessage);
			}
		}
	}

	/* Store the call-response pair in the given database. */
	private static void store(ChatDB database, String call, String response) {
		database.add(call, response);
	}

	/* Get a response for call in the given database. */
	private static String getResponse(ChatDB database, String call) {
		return database.getResponse(call);
	}

	/* Serialize the given database to a file in the current directory. */
	private static void writeDB(ChatDB database) {
		try {
        	FileOutputStream fileOut = new FileOutputStream("database.ser");
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(database);
	        out.close();
	        fileOut.close();
	        System.out.println("\nDatabase written");
	    } catch(IOException i) {
	        i.printStackTrace();
	        System.out.println("Failed to write database");
	    }
	}

	/* Deserialize the file database.ser from the current directory. */
	private static ChatDB loadDB() {
		ChatDB database = null;
		try {
	        FileInputStream fileIn = new FileInputStream("database.ser");
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	        database = (ChatDB) in.readObject();
	        in.close();
	        fileIn.close();
	        System.out.println("Database loaded\n");
	        return database;
	    } catch(IOException i) {
	        i.printStackTrace();
	        System.out.println("Failed to load database");
	        System.exit(0);
	        return database;
	    } catch(ClassNotFoundException c) {
	        c.printStackTrace();
	        System.out.println("Failed to load database");
	        System.exit(0);
	        return database;
	    }
	}
}
