import java.util.*;

public class Chatbot {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			String phrase = in.nextLine();
			if (phrase.equals("quit")) {
				System.out.println("Roger: Goodbye, friend");
				in.close();
				break;
			} else {
				System.out.println("Roger: " + phrase);
			}
		}
	}
}
