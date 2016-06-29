import java.io.*;

public class InitializeDB {

	public static void main(String[] args) {
		File file = new File("database.ser");
		if (file.exists()) {
			System.out.println("Database already exists");
			System.exit(0);
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
