import java.io.*;
import java.util.*;

public class Expression3 {
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

		/* checking if the given expression
			has matching parentheses or not */
		char[] input = expr.toCharArray();
		boolean brackets = parenthMatching(input);
		if (!brackets) {
			throw new IllegalArgumentException("The parentheses are not matched!");
		}


        StringTokenizer st = new StringTokenizer(expr, delimiters, true);
		String symbol = "";
        Integer num = new Integer(0);
		/* firstEntry is true only when
			we start looking at the tokens
			for the very first time */
		boolean firstEntry = true;
		/* fromBrackets stores the answers
			that we get from expressions
			that lie inside brackets */
		Integer fromBrackets;
		/* consecutiveOp is used to keep
			track that we don't have two
			operators one after the other */
		int consecutiveOp = 1;
		
        while(st.hasMoreTokens()) {
            String element = st.nextToken();
            boolean isNum = isNumber(element);
			boolean isOper = isOperator(element);

			/* this big if block is for when the
				expression does not contain any brackets */
			if (!element.equals("(") && !element.equals(")")) {
				/* if we are looking at the tokens
					for the very first time */
            	if(firstEntry) {
					if (isNum) {
						num = Integer.parseInt(element);
						consecutiveOp--;
					}
					/* since an expression cannot start
						with an operator, we throw an
						exception */
					else if (isOper) {
						throw new IllegalArgumentException("Cannot start expression with an operator!");
					}
				}

				/* if we are not looking at the
					tokens for the very first time */
				else {
					if(isNum) {
						
						consecutiveOp--;
						/* at this stage, we have a number,
							a symbol and another number.
							therefore we can perform an operation
							depending on the symbol that we have */
						if (symbol.equals("+")) {
							num = num + Integer.parseInt(element);
						}
						if (symbol.equals("-")) {
							num = num - Integer.parseInt(element);
						}
						if (symbol.equals("*")) {
							num = num * Integer.parseInt(element);
						}
						if (symbol.equals("%")) {
							num = num / Integer.parseInt(element);
						}

					}
					else if (isOper) {
						symbol = element;
						consecutiveOp++;
					}
				}
				/* if (consecutiveOp == 2) {
					throw new IllegalArgumentException("Two operators are present consecutively!");
				} */
				/* if (consecutiveOp == 1 && st.countTokens() == 0) {
					throw new IllegalArgumentException("Expression ends with an operator!");
				} */
				
			}

			/* this big else block is for when the
				given expression contains brackets */
			else {
				/* in this part, we make a new string
					'temp' which contains the expression
					that lies within brackets */
				int track = 0;
				if (element.equals("(")) {
					track++;
				}
				String temp = "";
				while(track != 0) {
					element = st.nextToken();
					if(element.equals("(")) {
						track++;
					}
					if(element.equals(")")) {
						track--;
					}
					if (track == 0) {
						break;
					}
					temp += element;
				}

				/* this is the recursive step where we
					send the string 'temp' for evaluation */
				fromBrackets = evaluate(temp);


				/* after we obtain the result of evaluating
					the string 'temp', we need to combine it
					with the already existing elements and
					evaluate it with them */

				if (symbol.equals("+")) {
					num = num + fromBrackets;
				}
				else if (symbol.equals("-")) {
					num = num - fromBrackets;
				}
				else if (symbol.equals("*")) {
					num = num * fromBrackets;
				}
				else if (symbol.equals("%")) {
					num = num / fromBrackets;
				}
				else {
					num = fromBrackets;
				}
			}

			/* after we have looked at the tokens
				for the very first time, firstEntry
				is made false so that we may not
				execute that small piece of code
				that is to be performed only at the
				beginning of our evaluation */
			firstEntry = false;

        }

	    return num;
	}

	// sample: (9-7*(3-5*2))*(1-(2+7-6*(100%15+17+(9-4*6))+(8*7))+9%3) Answer: 544
	// sample: 2+3*(52-71+(9-4-1)*(5-7)+2*4) Answer: 640

	/* method to check whether the
	   token is a number or not */
	private static boolean isNumber(String element) {
		try {
			Integer.parseInt(element);
			return true;
		}
		catch (NumberFormatException e) {
			return false;
		}
	}


	/* method tot check whether the
	   token is an operator or not
	   operators are +,-,*,% */
	private static boolean isOperator(String element) {
		if (element.equals("+") || element.equals("-") || element.equals("*") || element.equals("%")) {
			return true;
		}
		return false;
	}


	/* method to check whether the
	   given expression has matching
	   parentheses or not */
	private static boolean parenthMatching(char[] input) {
    	int track = 0;
   		for(int i = 0; i < input.length; i++) {
      		if (track < 0) {
        		return false;
      		}
     		if(input[i] == '(') {
        		track++;
      		}
      		else if(input[i] == ')') {
        		track--;
      		}
    	}

    	if (track == 0) {
    		return true;
    	}
    	else {
    		return false;
    	}

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