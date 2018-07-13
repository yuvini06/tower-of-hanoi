/* Towers of Hanoi implementation in Java without recursion
 * Usage: java hanoi <from> <to> <via> <disks>
 * Example: java hanoi 'A' 'B' 'C' 5 
 */
import java.util.Stack;

public class hanoi {
	
	// Creating stacks for each tower
	static Stack<Integer> A = new Stack<Integer>();
	static Stack<Integer> B = new Stack<Integer>();
	static Stack<Integer> C = new Stack<Integer>();

	private static final String USAGE = "Usage:<from:char> <to:char> <via:char> <disks:int>";
	static int disks;
	// static int numberOfMoves = 1;

	public static void main(String[] args) {
	  	// Check if the command line arguments are correct 
		if(args.length!=4) {
			System.out.println("Invalid number of arguments\n" + USAGE); 
			return;
		}
	  
	  	// Check if the towers are defined as alphabetic characters
	  	if(!((args[0]).matches("[a-zA-Z]")) || !((args[1]).matches("[a-zA-Z]")) || !((args[2]).matches("[a-zA-Z]"))) {		  
			System.out.println("Invalid type of arguments\n" + USAGE); 
		  	return;
	  	}
	  
	  	// Check if the number of disks is a digit
	 	try { 
		  	disks = (Integer.parseInt(args[3]));
		  	if(disks<=0) {
			  	System.out.println("Number of disks must be greater than zero."); 
			  	return;	  
		  	}
	  	} catch (NumberFormatException e) {
		  	System.out.println("Invalid type of arguments\n" + USAGE); 
		  	return;
	  	}

	  	String from = args[0];
	 	String to   = args[1];  
	  	String via  = args[2];
		
		System.out.printf("Move %d disks from %s to %s via %s\n", disks, from, to, via);
		// Total number of moves = = 2^n − 1
		// int numberOfMoves = (int) java.lang.Math.pow(2,disks)-1;	//import java.lang.Math;
		
		// Moving all disks to the source tower initially
		for (int k=disks;k>=1;k--) {
			A.push(k);
		}

		/**************************************************************************************
		 * The direction of motion of the smallest disk depends on the number of disks (n)
		 * If n is odd  -> clockwise
		 * If n is even -> counter clockwise 
		 *************************************************************************************/
		
		// When n is odd
		if (disks%2 == 1) {
			// Do until the destination tower is filled
			while(B.size()!=disks) {
				validMove(A,B,from,to);
				// The above function can result in the destination tower getting filled -> check if full
				if (B.size()==disks)
					break;							
				validMove(A,C,from,via);
				validMove(B,C,to,via);
			}
		}
		
		// When n is even
		else { 
			// Do until the destination tower is filled
			while(B.size() != disks) {
				validMove(A,C,from,via);
				validMove(A,B,from,to);
				// The above function can result in the destination tower getting filled -> check if full
				if (B.size() == disks)
					break;
				validMove(B,C,to,via);

			}
		}

	}
	
	// Print the movement of a disk
	public static void printMove(String from, String to, int disks) {
		// System.out.printf("%d: ", numberOfMoves);
		// numberOfMoves++;
		System.out.printf("Move disk %d from %s to %s\n", disks, from, to);		
	}
	

	// Function to move a disk between two towers
	/****************************************************************************************
	 * A disk can move from tower S to tower T if,
	 *	- tower T is empty
	 * 	- Disk at the top of tower T is larger than that on top of tower S
	 * ------------------------------------------------------------------------------------ *
	 * peek(): returns the element at the top of the stack
	 * pop( ): removes and returns the element on the top of the stack
	 * push(Object element): pushes the element onto the stack
	****************************************************************************************/
	public static void validMove(Stack<Integer> S,Stack<Integer> T,String from, String to) {
		if (T.isEmpty() || (!S.isEmpty() && S.peek() < T.peek())) {
			printMove(from, to, S.peek());
			T.push(S.pop());					
		} else {
			printMove(to, from, T.peek());
			S.push(T.pop());			
		}
	}

}
