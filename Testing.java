import java.io.*;
import java.util.*;

public class Testing {
  static String delimiters="()";


  public static void main(String[] args) {
    Integer five = new Integer(5);
    System.out.println(five + 7);
    for(int i = 0; i < 15; i++) {
      char[] brackets = new char[10];
      for(int j = 0; j < brackets.length; j++) {
        double number = Math.random()*2;
        if(number > 1) {
          brackets[j] = '(';
        }
        else {
          brackets[j] = ')';
        }
        if (j == 0) {
          System.out.print((i+1) + ". ");
        }
        System.out.print(brackets[j]);
      }
      System.out.println();
      System.out.println(parenthMatching(brackets));
      System.out.println();
    }
    Scanner reader = new Scanner(System.in);
    String test = "6+5";
    char[] input = test.toCharArray();
    System.out.println(test + "\n" + parenthMatching(input));
    //String expr = reader.next();
    //attempt(expr);

    /* String test = "alpha";
    char c = 'b';
    if (test.contains(c)) {
      System.out.println("test contains " + c);
    }
    else {
      System.out.println("test does not contain " + c);
    } */
  }

  public static boolean parenthMatching(char[] input) {
    //char[] input = expr.toCharArray();
    int track = 0;
    for(int i = 0; i < input.length; i++) {
      if (track < 0) {
        System.out.print("Track: " + track + " ");
        return false;
      }
      if(input[i] == '(') {
        track++;
      }
      else if(input[i] == ')') {
        track--;
      }
    }

    System.out.print("Track: " + track + " ");
    if (track == 0) {
      return true;
    }
    else {
      return false;
    }

  }

  public static Integer attempt(String expr) {
    // STEP 1: Parentheses checking

    char[] exprAsChar = expr.toCharArray();
    boolean match = parenthMatching(exprAsChar);
    if (!match) {
      throw new IllegalArgumentException("The parentheses are not matched!");
    }

    // STEP 2: 

    StringTokenizer st = new StringTokenizer(expr, delimiters, false);
    String modifiedExpression = "";

    while(st.hasMoreTokens()) {
      System.out.println(st.nextToken());
    }
    /* while(st.hasMoreTokens()) {
      String element = st.nextToken();
      
      if(!element.equals("(") && !element.equals(")")) {
        modifiedExpression += element;
      }

      else {
        int track = 0;
        while(track != 0) {
          if(element.equals("(")) {
            track++;
          }
          if(element.equals(")")) {
            track--;
          }

        }
      }

    } */
    return 0;
  }
}