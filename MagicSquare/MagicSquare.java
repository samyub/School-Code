import java.util.Scanner;


public class MagicSquare {
   // This is the variable for the side length of the square.
   static int LENGTH = 0;
   // This is the static 2D Array that will be used to generate the square.
   static int[][] square;
   // the value of the cell in the array
   static int num = 1;
   // this is the sum of all the values in the given row or col
   //static int sum = 0;
   // This variable contains the command line input for the row length of the square.
   static int ROW_INT = 0;
   // This variable contains the command line input for the column length of the square.
   static int COL_INT = 0;
   // This variable is the number row and is used to increment through the array
   static int row = 0;
   // this is the number col that is used to increment through the array
   static int col;
   // This is the 2D array for the creation of a part of a doubly even square
   static int[][] otherSquare;

   /*
	Sets the values of LENGTH, ROW_INT, and COL_INT using args and using the value of LENGTH, it runs either 
  oddSquare() if LENGTH is odd or doubleEvenSquare() if LENGTH is divisible by 4.

	@param   args   takes the CLI/User input and assigns the values in it to LENGTH, ROW_INT, and COL_INT
	*/
   public static void main (String[] args) {
   	LENGTH = Integer.parseInt(args[0]);
	ROW_INT = Integer.parseInt(args[1]);
	COL_INT = Integer.parseInt(args[2]);

	col = LENGTH/2;

	square = new int[LENGTH][LENGTH];
    otherSquare = new int[LENGTH][LENGTH];

    /*
	Link: https://www.geeksforgeeks.org/java/throw-throws-java/
	If the LENGTH inputted is less than 0, then it will throw an error, informing the user to fix their input
    */
    if (LENGTH <= 0) throw new IllegalArgumentException("Length must be > 0"); 
    square = new int[LENGTH][LENGTH];
    otherSquare = new int[LENGTH][LENGTH];

    if (LENGTH % 2 == 1) {
        num = 1;
        row = 0;
        col = LENGTH / 2;
        oddSquare();
    } 
    else if (LENGTH % 4 == 0) {
        num = 1;
        doubleEvenSquare();
    } 
    else {
    	/*
		Link: https://www.geeksforgeeks.org/java/throw-throws-java/
		If the number inputted is not odd or divisible by 4, then an error will be thrown telling them 
        why their input it wrong and how to fix it
    	*/
        throw new IllegalArgumentException("Your number is not supported by this program because (n % 4 == 2). Please enter either an number that is odd or divisible by 4!"); 
    }
   }


   /*
	This method calls makeOddSquare which creates the odd square and then it prints out the sums of the 
  chosen row and column and then the sum of both diagonals

	pre: LENGTH is an odd number
	post: all the parts necessary for odd square, including the sums, are printed
   */
   public static void oddSquare() {
       makeOddSquare();
       System.out.println("Row " + ROW_INT);
       rowSum();
       System.out.println("Column " + COL_INT);
       colSum();
       System.out.println("Diagonals: ");
       diagonalSum();
       otherDiagonalSum();
   }

   /*
	This method runs all of the parts necessary to create the odd square. More specifically, it calls 
  different methods such as noRow and noCol depending on what number row and col the computer is on 
  in the array. 

	pre: LENGTH is an odd number
	post: The oddSquare itself, nothing else. 
   */
   public static void makeOddSquare() {
       square[0][LENGTH / 2] = 1;


       for (int i = 2; i < (LENGTH * LENGTH) + 1; i++) {
           if ((row - 1 < 0) && (col + 1 > LENGTH - 1)) {
               num = i;
               neitherColOrRow();
           }
           else if ((row - 1 < 0) && (col + 1 < LENGTH)) {
               num = i;
               noRow();
           }
           else if ((row - 1 >= 0) && (col + 1 > LENGTH - 1)) {
               num = i;
               noCol();
           }
           else if (square[row-1][col+1] != 0) {
               num = i;
               neitherColOrRow();
           }else{


               row = row -1;
               col= col+1;
               square[row][col] = i;
           }
       }
       printSquare();
   }

   /*
	This method goes to the first value of the row below it and changes that index to whatever num 
  is at that point of the code

	pre: There is no column in the intended area to move
	post: Array square has been changed, a new spot has the value of num
   */
   public static void noCol () {
       square[row - 1][0] = num;
       row -= 1;
       col = 0;
   }

   /*
	This method goes to the last row and the column before it and changes that index to whatever num 
  is at that point of the code

	pre: there is no row in the inteded space to move
	post: array square has been changed, a new spot has the value of num
   */
   public static void noRow () {
       //num++;
       square[LENGTH - 1][col + 1] = num;
       row = LENGTH - 1;
       col += 1;
   }

   /*
    This method goes to the cell right below it and changes that index to whatever num is at that 
    point of the code

    pre: there is no row or column in the inteded space to move
    post: array square has been changed, a new spot has the value of num
   */
   public static void neitherColOrRow() {
       row = (row + 1) % LENGTH;
       square[row][col] = num;
   }

   /*
	This method prints out all the values of array square

	pre: all the methods such as noRow and noCol have been called and each cell in square has a value
	post: the array square has been printed out
   */
   public static void printSquare() {
       String biggestValue = String.valueOf(num);
       int biggestValueLength = biggestValue.length() + 1;
       for (int row = 0; row < square.length; row++) {
           for (int col = 0; col < square[0].length; col++) {
               System.out.print(square[row][col] + " "); 
           }
           System.out.println();
       }
   }

   // This method calculates the sum of the diagonal going from the top left cell to the bottom right cell
   public static void diagonalSum() {
       int sum = square[0][0];
       String process = String.valueOf(square[0][0]);
       for (int i = 1; i < square.length; i++) {
           process +=  " + " + square[i][i];
           sum += square[i][i];
       }
       process += " = " + sum;
       System.out.println(process);
   }

   // This method calcualtes the sum of the square array diagonal going from the top right cell to the 
   bottom left cell
   public static void otherDiagonalSum() {
       int sum = square[LENGTH - 1][LENGTH - 1];
       String process = String.valueOf(square[LENGTH - 1][LENGTH - 1]);
       for (int i = square.length - 1; 0 <= i; i--) {
           process +=  " + " + String.valueOf(square[i][i]);
           sum += square[i][i];
       }
       process += " = " + sum;
       System.out.println(process);
   }


   // This method calculates the sum of all the values in the row that the user chose in the CLI
   public static void rowSum() {
       int sum = square[ROW_INT][0];
       String process = String.valueOf(square[ROW_INT][0]);
       for (int i = 1; i < square.length; i++) {
           process +=  " + " + square[ROW_INT][i];
           sum += square[ROW_INT][i];
       }
       process += " = " + sum;
       System.out.println(process);
   }

   // This method calcualtes the sum of all the values in the column that the user chose in the CLI
   public static void colSum() {
       int sum = square[0][COL_INT];
       String process = String.valueOf(square[0][COL_INT]);
       for (int i = 1; i < square.length; i++) {
           process +=  " + " + square[i][COL_INT];
           sum += square[i][COL_INT];
       }
       process += " = " + sum;
       System.out.println(process);
   }

   /*
	This method runs to call all the methods necessary to print out the double even function. 
    It makes the square, then changes all the sides to fit the rules of a double even square
    and lastly, it prints out all the sums.

	pre: LENGTH is divisible by 4
	post: the square in printed along with the sums of the given row, col, and both diagonals
   */
   public static void doubleEvenSquare() {
       makeEvenSquare();
       otherSquare();
       leftSide();
       rightSide();
       topSide();
       bottomSide();
       printSquare();
       System.out.println("Row " + ROW_INT);
       rowSum();
       System.out.println("Column " + COL_INT);
       colSum();
       System.out.println("Diagonals: ");
       diagonalSum();
       otherDiagonalSum();
   }

   // This method makes an array and assigns values from 1 to (LENGTH x LENGTH)
   public static void makeEvenSquare() {
       for (int r = 0; r < LENGTH; r++) {
           for (int c = 0; c < LENGTH; c++) {
               square[r][c] = num;
               num++;
           }
       }
   }

   // This method makes an array and assigns valyes (LENGTH x LENGTH) to 1
   public static void otherSquare() {
       num--;
       for (int r = 0; r < LENGTH; r++) {
           for (int c = 0; c < LENGTH; c++) {
               otherSquare[r][c] = num;
               num--;
           }
       }
   }

   /* 
   This method changes all the values on the left side of the square. For all the values in array 
   square that are below 1/4 of LENGTH and above 3/4 of LENGTH, the method takes the number in the 
   same area from array otherSquare and changes the values in array square.
    */
   public static void leftSide() {
       for (int r = (LENGTH/4); r < (LENGTH * 3)/4; r++) {
           for (int c = 0; c < (LENGTH / 4); c++) {
               square[r][c] = otherSquare[r][c];
           }
       }
   }

   /* 
   This method changes all the values on the right side of the square. For all the values in 
   array square that are below 1/4 of LENGTH and above 3/4 of LENGTH, the method takes the number 
   in the same area from array otherSquare and changes the values in array square.
    */
   public static void rightSide() {
       for (int r = (LENGTH/4); r < (LENGTH * 3)/4; r++) {
           for (int c = (LENGTH * 3)/4; c < LENGTH; c++) {
               square[r][c] = otherSquare[r][c];
           }
       }
   }

   /* 
   This method changes all the values on the top side of the square. For all the values in array square 
   that are below 1/4 of LENGTH and above 3/4 of LENGTH, the method takes the number in the same area 
   from array otherSquare and changes the values in array square.
    */
   public static void topSide() {
       for (int c = (LENGTH/4); c < (LENGTH * 3)/4; c++) {
           for (int r = 0; r < (LENGTH/4); r++) {
               square[r][c] = otherSquare[r][c];
           }
       }
   }

   /* 
   This method changes all the values on the bottom side of the square. For all the values in array 
   square that are below 1/4 of LENGTH and above 3/4 of LENGTH, the method takes the number in the 
   same area from array otherSquare and changes the values in array square.
    */
   public static void bottomSide() {
       for (int c = (LENGTH/4); c < (LENGTH * 3)/4; c++) {
           for (int r = (LENGTH * 3)/4; r < LENGTH; r++) {
               square[r][c] = otherSquare[r][c];
           }
       }
   }
}









