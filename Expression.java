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
		Stack<String> operators = new Stack<String>();
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
			operators.push(element);
			//System.out.println("Operator: " + operators.peek());
            if (!element.equals(")") && !element.equals("(")) {
                countOperator++;
            }
            /* if there are no more tokens left after executing
               this catch block and the last token just added 
               is not a '(' or a ')', then that means that the
               last token was one of +-*% (it can't be a number 
               because we came into the catch block) and therefore
               we throw an exception */
            if (st.countTokens() == 0 && !element.equals(")") && !element.equals("(")) {
                throw new IllegalArgumentException("The expression cannot end with an operator!");
            }
          }

          if (countOperator == 2) {
              throw new IllegalArgumentException("The expression does not have operators in the right way!");
          }

	    }

        Stack<String> nonNumeric = new Stack<String>();
        Stack<Integer> numbersInOrder = new Stack<Integer>();
        while(!numbers.empty()) {
            numbersInOrder.push(numbers.pop());
        }
        while(!operators.empty()) {
            nonNumeric.push(operators.pop());
        }

        /* int i = 1;
        while(!numbersInOrder.empty()) {
              System.out.println(i + ". " + numbersInOrder.pop());
              i++;
        }
        
        System.out.println();
        int j = 1;
        while(!nonNumeric.empty()) {
              System.out.println(j + ". " + nonNumeric.pop());
              j++;
        } */
        
        //int operatorsSize = nonNumeric.size();

        /* String otherDelimiters = "0123456789()";
        StringTokenizer st2 = new StringTokenizer(expr, otherDelimiters, false);
        Stack<String> justOperators = new Stack<String>();
        while(st2.hasMoreTokens()) {
            justOperators.push(st2.nextToken());
        }

        Stack<String> justOperatorsInOrder = new Stack<String>();
        while(!justOperators.empty()) {
            justOperatorsInOrder.push(justOperators.pop());
        }

        int numOperations = justOperatorsInOrder.size(); */


        /* if the expression is only a single number
           (without parentheses) then we push a plus 
           sign to the operators stack because the number
           by itself cannot be evaluated. e.g. 5 is 
           written as 5+0. below we push the number 0 
           to the numbers stack */
        if (nonNumeric.size() == 0) {
            nonNumeric.push("+");
        }
        //System.out.println("Number of operations to do: " + numOperations);
        Integer answer = new Integer(0);
        Integer firstNum = numbersInOrder.peek();
        //while(!numbers.empty() || !operators.empty()) {
        //System.out.println("nonNumeric size: " + nonNumeric.size());
        int numNonNumeric = nonNumeric.size();
        for(int i = 0; i < numNonNumeric; i++) {
            System.out.print(firstNum + " ");
            if (i == 0) {
                numbersInOrder.pop();
            }   
            String operator = nonNumeric.peek();
            /* if (operator.equals("(")) {
                nonNumeric.pop();
                String nextOperator = nonNumeric.peek();
                Stack<Integer> numbersWithin = new Stack<Integer>();
                Stack<String> operatorsWithin = new Stack<String>();

            } */
            System.out.print(operator + " ");
            nonNumeric.pop();
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
    String delimiters = "()";
    StringTokenizer st = new StringTokenizer(expr, delimiters, false);
    String[] pieces = new String[st.countTokens()];
    int i = 0;
    while(st.hasMoreTokens()) {
        pieces[i] = st.nextToken();
        System.out.println(pieces[i]);
        i++;
    }
    String modifiedExpression = "0+";
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
                modifiedExpression += number;
            }

            else {
                modifiedExpression += answer;
            }
        }

        catch(IllegalArgumentException e) {
            modifiedExpression += pieces[j];
        }

    }

    Integer answer = evaluate(modifiedExpression);
    System.out.println("Answer: " + answer);
    return answer;

    // sample : 2+3*(52-71+(9-4-1)*(5-7)+2*4) Answer: 640
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