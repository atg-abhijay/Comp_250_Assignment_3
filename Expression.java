import java.io.*;
import java.util.*;

public class Expression {
	static String delimiters="+-*%()";
	
	
	// Returns the value of the arithmetic Expression described by expr
	// Throws an exception if the Expression is malformed
	static Integer evaluate(String expr) throws Exception {
	    /* The code below gives you an example of utilization of the
	     * StringTokenizer class to break the Expression string into
	     * its components */
	    // StringTokenizer st = new StringTokenizer( expr , delimiters , true );    
        
	    /* This is just an example of how to use the StringTokenizer */
	    /* while ( st.hasMoreTokens() ) {
		String element = st.nextToken();
		System.out.println("Element ="+element);
	    } */
        
	    /* YOU WRITE YOUR CODE HERE */

        /* checking for the correct number
           and formatting of parentheses */
        /* char[] exprAsChar = expr.toCharArray();
        boolean match = parenthMatching(exprAsChar);
        if (!match) {
            throw new IllegalArgumentException("The parentheses are not matched!");
        } */

		StringTokenizer st = new StringTokenizer(expr, delimiters, true);    
		Stack<String> symbols = new Stack<String>();
		Stack<Integer> numbers = new Stack<Integer>();

        int countOperator = 1;
	    while (st.hasMoreTokens()) {
		  String element = st.nextToken();

		  try {
			int num = Integer.parseInt(element);
			Integer value = new Integer(num);
			numbers.push(value);
			//System.out.println("Number: " + numbers.peek());
            countOperator--;
		  }

		  catch(NumberFormatException e) {
			symbols.push(element);
			//System.out.println("Operator: " + symbols.peek());
            if (!element.equals(")") && !element.equals("(")) {
                countOperator++;
            }
            /* if there are no more tokens left after executing
               this catch block and the last token just added 
               is not a '(' or a ')', then that means that the
               last token was one of +-*% (it can't be a number 
               because we came into the catch block) and therefore
               we throw an exception */
            /*  if (st.countTokens() == 0 && !element.equals(")") && !element.equals("(")) {
                throw new IllegalArgumentException("The expression cannot end with an operator!");
            } */
          }

          if (countOperator == 2) {
              throw new IllegalArgumentException("The expression does not have symbols in the right way!");
          }

	    }

        Stack<String> symbolsInOrder = new Stack<String>();
        Stack<Integer> numbersInOrder = new Stack<Integer>();
        while(!numbers.empty()) {
            numbersInOrder.push(numbers.pop());
        }
        while(!symbols.empty()) {
            symbolsInOrder.push(symbols.pop());
        }

        /* if the expression is only a single number
           (without parentheses) then we push a plus 
           sign to the symbols stack because the number
           by itself cannot be evaluated. e.g. 5 is 
           written as 5+0. below we push the number 0 
           to the numbers stack */
        if (symbolsInOrder.size() == 0) {
            symbolsInOrder.push("+");
        }

        if (numbersInOrder.size() == 1 && symbolsInOrder.size() == 2 && symbolsInOrder.peek().equals("(")) {
            return numbersInOrder.peek();
        }
        //System.out.println("Number of operations to do: " + numOperations);
        Integer answer = new Integer(0);
        Integer firstNum = numbersInOrder.peek();
        //while(!numbers.empty() || !symbols.empty()) {
        //System.out.println("symbolsInOrder size: " + symbolsInOrder.size());
        int numsymbolsInOrder = symbolsInOrder.size();
        for(int i = 0; i < numsymbolsInOrder; i++) {
            System.out.print(firstNum + " ");
            if (i == 0) {
                numbersInOrder.pop();
            }   
            String operator = symbolsInOrder.peek();
            /* if (operator.equals("(")) {
                symbolsInOrder.pop();
                String nextOperator = symbolsInOrder.peek();
                Stack<Integer> numbersWithin = new Stack<Integer>();
                Stack<String> symbolsWithin = new Stack<String>();

            } */
            System.out.print(operator + " ");
            symbolsInOrder.pop();
            /* if the expression is a single number
               (without parentheses) we push a 0 so 
               that it can act as secondNum and the 
               expr can now be evaluated */
            if (numbersInOrder.empty()) {
                numbersInOrder.push(0);
            }
            Integer secondNum = numbersInOrder.peek();
            System.out.print(secondNum + " ");
            numbersInOrder.pop();
            answer = performOperation(firstNum, operator, secondNum);
            firstNum = answer;
            System.out.println();
        }
	    return answer;
	}

    private static Integer attempt(String expr) throws Exception {
    // STEP 1: Parentheses checking

    char[] exprAsChar = expr.toCharArray();
    boolean match = parenthMatching(exprAsChar);
    if (!match) {
      throw new IllegalArgumentException("The parentheses are not matched!");
    }

    // STEP 2: 
    String delimiters2 = "()";
    StringTokenizer st = new StringTokenizer(expr, delimiters2, false);
    String[] pieces = new String[st.countTokens()];
    int i = 0;
    while(st.hasMoreTokens()) {
        pieces[i] = st.nextToken();
        System.out.println(pieces[i]);
        i++;
    }
    String modifiedExpression = "0+";
    String delimiters3 = " +-*%";
    StringTokenizer st4 = new StringTokenizer(modifiedExpression, delimiters3, false);
    int numWords = st4.countTokens();
    while(numWords != 1) {
        for(int j = 0; j < pieces.length; j++) {
            try{
                Integer answer = evaluate(pieces[j]);
                String number = "";
                if (answer < 0) {
                    char[] modExpr = modifiedExpression.toCharArray();
                    char lastChar = modExpr[modExpr.length - 1];
                    if (lastChar == '+') {
                        number = "0" + answer;
                    }
                    if (lastChar == '-') {
                        answer = answer * (-1);
                        number = "0+" + answer;
                    }
                    if (lastChar == '*') {
                        //modifiedExpression += "-";
                    
                    }
                    if (lastChar == '%') {

                    }
                    //modifiedExpression += "(";
                    modifiedExpression += number;
                    //modifiedExpression += ")";
                    if (j == pieces.length - 1) {
                        modifiedExpression += ")";
                    }
                }

                else {
                    //modifiedExpression += "(";
                    modifiedExpression += answer;
                    //modifiedExpression += ")";
                    /* if (j == pieces.length - 1) {
                        modifiedExpression += ")";
                    } */
                }
            }

            catch(IllegalArgumentException e) {
                /* char[] piece = pieces[j].toCharArray();
                if (piece[0] == '+' || piece[0] == '-' || piece[0] == '*' || piece[0] == '%') {
                    modifiedExpression += ")";
                } */

                modifiedExpression += pieces[j];

                /* if (piece[piece.length - 1] == '+' || piece[piece.length - 1] == '-' || piece[piece.length - 1] == '*' || piece[piece.length - 1] == '%') {
                    modifiedExpression += "(";
                } */
            }
            return evaluate(modifiedExpression);

        }
    }

    System.out.println(modifiedExpression);
    StringTokenizer st3 = new StringTokenizer(modifiedExpression, delimiters2, false);
    while(st3.hasMoreTokens()) {
        System.out.println(st3.nextToken());
    }
    Integer answer = evaluate(modifiedExpression);
    System.out.println("Answer: " + answer);
    return answer;

    // sample : 2+3*(52-71+(9-4-1)*(5-7)+2*4) Answer:s 640
  }

    private static Integer performOperation(Integer number1, String operation, Integer number2) {
        if(operation.equals("+")) {
            return number1 + number2; 
        }
        else if(operation.equals("-")) {
            return number1 - number2;
        }
        else if(operation.equals("*")) {
            return number1 * number2;
        }
        else if(operation.equals("%")) {
            return number1 / number2;
        }
        else {
            return 0;
        }
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

    /* private static Integer breakExpression(String expression) throws Exception {
        char[] exprAsChar = expression.toCharArray();
        Stack<String> parentheses = new Stack<String>();
        for(int i = 0; i < exprAsChar.length; i++) {
            if (exprAsChar[i] == '(') {
                parentheses.push(exprAsChar[i]);
            }
            else if (exprAsChar[i] == ')') {
                if (parentheses.isEmpty()) {
                    return false;
                }
            }
        }
        if (parentheses.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    } */
		
	public static void main(String args[]) throws Exception {
		String line;
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
                                      	                        
		do {
			line=stdin.readLine();
			if (line.length()>0) {
				try {
					Integer x=attempt(line);
					System.out.println(" = " + x);
				}
				catch (Exception e) {
					System.out.println("Malformed Expression: "+e);
				}
			}
		} while (line.length()>0);
	}
}