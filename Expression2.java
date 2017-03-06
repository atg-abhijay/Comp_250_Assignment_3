import java.io.*;
import java.util.*;

public class Expression2 {
	static String delimiters="+-*%()";
	
	
	// Returns the value of the arithmetic Expression described by expr
	// Throws an exception if the Expression is malformed
	static Integer evaluate(String expr) throws Exception {
	    /* The code below gives you an example of utilization of the
	     * StringTokenizer class to break the Expression string into
	     * its components */
	    //StringTokenizer st = new StringTokenizer( expr , delimiters , true );    
        
	    /* This is just an example of how to use the StringTokenizer */
	    /* while ( st.hasMoreTokens() ) {
		String element = st.nextToken();
		System.out.println("Element ="+element);
	    } */  
        
	    /* YOU WRITE YOUR CODE HERE */
        // st will just contain the numbers
        StringTokenizer st = new StringTokenizer(expr, delimiters, false);
        String delimiters2 = "0123456789";
        // st2 will contain all the symbols
        StringTokenizer st2 = new StringTokenizer(expr, delimiters2, false);

        Stack<String> symbols = new Stack<String>();
        Stack<Integer> numbers = new Stack<Integer>();

        Stack<String> symbolsInOrder = new Stack<String>();
        Stack<Integer> numbersInOrder = new Stack<Integer>();

        // filling numbers stack
        while(st.hasMoreTokens()) {
            numbers.push(st.nextToken());
        }
        /* making the stack in which the
           numbers are in the correct order */
        while(!numbers.empty()) {
            numbersInOrder.push(numbers.pop());
        }

        // filling the symbols stack
        while(st2.hasMoreTokens()) {
            symbols.push(st2.nextToken());
        }
        /* making the stack in which the
           symbols are in the correct order */ 
        while(!symbols.empty()) {
            symbolsInOrder.push(symbols.pop());
        }





	    // change this
	    return ;
	}	
		
	public static void main(String args[]) throws Exception {
		String line;
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
                                      	                        
		do {
			line=stdin.readLine();
			if (line.length()>0) {
				try {
					Integer x=evaluate(line);
					System.out.println(" = " + x);
				}
				catch (Exception e) {
					System.out.println("Malformed Expression: "+e);
				}
			}
		} while (line.length()>0);
	}
}