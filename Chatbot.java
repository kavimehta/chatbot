import java.util.*;

public class Chatbot {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String call = "";
		String response = "";
		while (in.hasNext()) {
			String phrase = in.nextLine();
			if (phrase.equals("quit")) {
				System.out.println("Roger: Goodbye, friend");
				in.close();
				break;
			} else {
				response = phrase;
				store(call, response);
				System.out.println("Roger: " + phrase);
				call = phrase;
			}
		}
	}

	private static void store(String call, String response) {

	}

	private static void writeDB() {

	}

	private static void loadDB() {
		
	}
}
