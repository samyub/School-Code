//Sam and Suhani
import java.util.Scanner;
import java.util.InputMismatchException;

public class Bitwise {
	//The first integer in the equation 
	private static int num1 = 0;
	//The second integer in the equation
	private static int num2 = 0;
	//The operator for the equation 
	private static String operator;
	//The result in the equation 
	private static int result = 0;
	//String represents whether or not the user continues to play
	private static String continuePlay;

	//Assigns num1 and num2 to a random number and calls randomize
	public static void main (String[] args) {
		Scanner input = new Scanner(System.in);
		num1 = (int)((Math.random() * 10) + 1);
		num2 = (int)((Math.random() * 10) + 1);
		do {
			randomize();
			System.out.print("Would you like to quit? Y or N: ");
			continuePlay = input.nextLine();
		} while (continuePlay.equals("N"));
	}


	//result and operator are assigned to a number from 1-4
	public static void randomize() {
		int randOperator = (int)((Math.random() * 4) + 1);
		switch (randOperator) {
			case 1: result = num1 | num2;
					operator = "|";
					break;
			case 2: result = num1 & num2;
					operator = "&";
					break;
			case 3: result = num1 ^ num2;
					operator = "^";
					break;
			case 4: result = num1 << num2;
					operator = "<<";
					break;
			default: result = 0;
					break;
		}
		randomizeBlank();
	}

	
	//Calls the method randomly on what the user needs to guess 
	public static void randomizeBlank() {
		int blank = (int)((Math.random() * 4) + 1); // for some reason guessSecondInt() isn't getting called
		switch (blank) {
			case 1: guessFirstInt();
					break;
			case 2: guessOperator();
					break;
			case 3: guessSecondInt();
					break;
			case 4: guessResult();
					break;
			default: System.out.println("Wrong");
					break;
		}
	}

	//Prints out the numbers and what the result is and checks if the user guessed the correct operator
	public static void guessOperator(){
		Scanner input = new Scanner(System.in);
		System.out.println(num1 + " _____ " + num2 + " == " + (result));

		
			String userInput = input.nextLine();
			if(userInput.equals(operator)){
				System.out.println("That works!");
			}
			else {
				checkUserResult(userInput);
			}
		

	}

	/*

	Prints out the numbers and the operator and checks if the user guessed the correct result

	exception: if user input is not an integer, then the user is told they were supposed to enter an integer
	*/
	public static void guessResult(){
		Scanner input = new Scanner(System.in);
		System.out.println(num1 + " " + operator + " " + num2 + " == " + "______");

		try {
			int userInput = input.nextInt();
			if(userInput == result){
				System.out.println("That works!");
			}
			else {
				System.out.println("That doesn't work! The actual answer is: " + result);
			}
		} catch (InputMismatchException e) {
			System.out.println("That is not a valid answer. You needed to enter an integer.");
		}
	}

	/*

	Prints out the operator, the second number and result and checks if the user guessed the correct first number
	
	exception: if user input is not an integer, then the user is told they were supposed to enter an integer
	*/
	public static void guessFirstInt() {
		Scanner input = new Scanner(System.in);
		System.out.println(" _____ " + operator + " " + num2 + " == " + (result));

		try {
			int userInput = input.nextInt();
			if(userInput == num1){
				System.out.println("That works!");
			}
			else {
				checkFirstInt(userInput);
			}
		} catch (InputMismatchException e) {
			System.out.println("That is not a valid answer. You needed to enter an integer.");
		}
	}

	/*

	Prints out the operator, the first number and result and checks if the user guessed the correct second number

	exception: if user input is not an integer, then the user is told they were supposed to enter an integer
	*/ 
	public static void guessSecondInt() {
		Scanner input = new Scanner(System.in);
		System.out.println(num1 + " " + operator + " _____ " + " == " + (result));

		try {
			int userInput = input.nextInt();
			if(userInput == num2){
				System.out.println("That works!");
			}
			else {
				checkSecondInt(userInput);
			}
		} catch (InputMismatchException e) {
			System.out.println("That is not a valid answer. You needed to enter an integer.");
		}
	}

	/*
	
	@param   String userInput    what the user inputs

	Checks to see if what the user inputted that can have the same result even if it isn't equal to what was assigned to 
	operator
	*/
	public static void checkUserResult(String userInput) {
		int result2 = 0;
		switch (userInput) {
			case "|": result2 = num1 | num2;
					break;
			case "&": result2 = num1 & num2;
					break;
			case "^": result2 = num1 ^ num2;
					break;
			case "<<": result2 = num1 << num2;
					break;
			default: System.out.println("That isn't an option.");
					return;
		}
		if (result == result2) {
			System.out.println("That works! Another possible answer is: " + operator);
		}
		else {
			System.out.println("That doesn't work! The actual answer is: " + operator);
		}
	}

	/*
	
	@param   int userInput    what the user inputs

	Checks to see if what to user inputted also yields the same result as to what was assigned to num1
	*/
	public static void checkFirstInt(int userInput) {
		int result2 = 0;
		switch (operator) {
			case "|": result2 = userInput | num2;
					break;
			case "&": result2 = userInput & num2;
					break;
			case "^": result2 = userInput ^ num2;
					break;
			case "<<": result2 = userInput << num2;
					break;
			default: System.out.println("That isn't an option");
					break;
		}
		if (result == result2) {
			System.out.println("That works! Another possible answer is: " + num1);
		}
		else {
			System.out.println("That doesn't work! The actual answer is: " + num1);
		}
	}

	/*
	
	@param   int userInput    what the user inputs

	Checks to see if what to user inputted also yields the same result as to what was assigned to num2
	*/
	public static void checkSecondInt(int userInput) {
		int result2 = 0;
		switch (operator) {
			case "|": result2 = num1 | userInput;
					break;
			case "&": result2 = num1 & userInput;
					break;
			case "^": result2 = num1 ^ userInput;
					break;
			case "<<": result2 = num1 << userInput;
					break;
			default: System.out.println("That isn't an option");
					break;
		}
		if (result == result2) {
			System.out.println("That works! Another possible answer is: " + num2);
		}
		else {
			System.out.println("That doesn't work! The actual answer is: " + num2);
		}
	}
}
