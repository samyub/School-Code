/*
	Author: Sam Bodduluri
	Terminal program for extra credit
*/
public class Terminal {
	// holds the new variables and the for loop which decides what to print
	//String[] args represents all the values that are inputted into the terminal
	public static void main(String[] args) {
		String numberInTerminal = args[0];
		int numForIterating = Integer.parseInt(numberInTerminal);

		//Checks what to print based on what index/how many times something has been printed already
		for (int i = 1; i < numForIterating + 1; i++) {
			if (i % 3 == 0 && i % 5 == 0) {
				System.out.println("Samyuktha Bodduluri");
			}
			else if (i % 3 == 0) {
				System.out.println("Sam");
			}
			else if (i % 5 == 0) {
				System.out.println("Bodduluri");
			}
			else {
				System.out.println("Hello World");
			}
		}
	}
}
