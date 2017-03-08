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
		char[] input = expr.toCharArray();
		boolean brackets = parenthMatching(input);
		if (!brackets) {
			throw new IllegalArgumentException("The parentheses are not matched!");
		}

        StringTokenizer st = new StringTokenizer(expr, delimiters, true);
		String symbol = "";
        Integer num = new Integer(0);
		boolean firstEntry = true;
		Integer fromBrackets;
		int consecutiveOp = 1;
		//int i = 1;
        while(st.hasMoreTokens()) {
			//System.out.println("Loop " + i);
            String element = st.nextToken();
            boolean isNum = isNumber(element);
			boolean isOper = isOperator(element);

			if (!element.equals("(") && !element.equals(")")) {
            	if(firstEntry) {
					if (isNum) {
						num = Integer.parseInt(element);
						consecutiveOp--;
						//System.out.println("#Number: " + num);
					}
					else if (isOper) {
						throw new IllegalArgumentException("Cannot start expression with an operator!");
					}
					//firstEntry = false;
				}

				else {
					if(isNum) {
						consecutiveOp--;
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
						//System.out.println("$Number: " + num);
					}
					else if (isOper) {
						symbol = element;
						consecutiveOp++;
						//System.out.println("Operator: " + symbol);
					}
				}
				firstEntry = false;
				/* if (consecutiveOp == 2) {
					throw new IllegalArgumentException("Two operators are present consecutively!");
				} */
				//i++;
			}

			else {
				int track = 0;
				if (element.equals("(")) {
					track++;
				}
				String temp = "";
				while(track != 0) {
					element = st.nextToken();
					if(element.equals("(")) {
						track++;
						//fromBrackets = evaluate(temp);
					}
					if(element.equals(")")) {
						track--;
					}
					if (track == 0) {
						break;
					}
					temp += element;
				}
				fromBrackets = evaluate(temp);

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

        }

	    // change this
	    return num;
	}

	private static boolean isNumber(String element) {
		boolean result;
		try {
			Integer.parseInt(element);
			return true;
		}
		catch (NumberFormatException e) {
			return false;
		}
	}

	private static boolean isOperator(String element) {
		boolean result;
		if (element.equals("+") || element.equals("-") || element.equals("*") || element.equals("%")) {
			return true;
		}
		return false;
	}

	private static boolean parenthMatching(char[] input) {
    //char[] input = expr.toCharArray();
    int track = 0;
    for(int i = 0; i < input.length; i++) {
      	if (track < 0) {
        	//System.out.print("Track: " + track + " ");
        	return false;
      	}
     	if(input[i] == '(') {
        	track++;
      	}
      	else if(input[i] == ')') {
        	track--;
      	}
    }

    //System.out.print("Track: " + track + " ");
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