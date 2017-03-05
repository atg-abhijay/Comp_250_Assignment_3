public class Testing {
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
}