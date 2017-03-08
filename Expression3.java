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
        StringTokenizer st = new StringTokenizer(expr, delimiters, true);
		String symbol = "";
        Integer num = new Integer(0);
		boolean firstEntry = true;
		Integer fromBrackets;
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
						//System.out.println("#Number: " + num);
					}
					//firstEntry = false;
				}

				else {
					if(isNum) {
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
						//System.out.println("Operator: " + symbol);
					}
				}
				firstEntry = false;
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
				if (symbol.equals("-")) {
					num = num - fromBrackets;
				}
				if (symbol.equals("*")) {
					num = num * fromBrackets;
				}
				if (symbol.equals("%")) {
					num = num / fromBrackets;
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