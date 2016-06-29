import java.util.*;
import java.io.*;

public class Chatbot {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String call = "";
		String response = "";
		ChatDB database = loadDB();
		while (in.hasNext()) {
			String phrase = in.nextLine();
			if (phrase.equals("quit")) {
				System.out.println("Roger: Goodbye, friend");
				in.close();
				break;
			} else {
				response = phrase;
				store(database, call, response);
				System.out.println("Roger: " + phrase);
				call = phrase;
			}
		}
	}

	/* Store the call-response pair in the given database. */
	private static void store(ChatDB database, String call, String response) {

	}

	/* Serialize the given database to a file in the current directory. */
	private static void writeDB(ChatDB database) {
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

	/* Deserialize the file database.ser from the current directory. */
	private static ChatDB loadDB() {
		ChatDB database = null;
		try {
	        FileInputStream fileIn = new FileInputStream("database.ser");
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	        database = (ChatDB) in.readObject();
	        in.close();
	        fileIn.close();
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
