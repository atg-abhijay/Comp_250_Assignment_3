import java.util.*;

class CardDeck {
    LinkedList<Integer> deck;

    // constructor, creates a deck with n cards, placed in increasing order
    CardDeck(int n) {
	deck = new LinkedList<Integer> ();
	for (int i=1;i<=n;i++) deck.addLast(new Integer(i));
    }

    // executes the card trick
    public void runTrick() {

	while (!deck.isEmpty()) {
	    // remove the first card and remove it
	    Integer topCard = deck.removeFirst();
	    System.out.println("Showing card "+topCard);

	    // if there's nothing left, we are done
	    if (deck.isEmpty()) break;
	    
	    // otherwise, remove the top card and place it at the back.
	    Integer secondCard = deck.removeFirst();
	    deck.addLast(secondCard);

	    System.out.println("Remaining deck: "+deck);

	}
    }



    public void setupDeck(int n) {
	/* WRITE YOUR CODE HERE */
		Stack<Integer> firstN = new Stack<Integer>();
		/* stack that stores the first
		   n numbers in decreasing order
		   (1 is at the bottom of the stack) */
		for(int i = 1; i <= n; i++) {
			Integer num = new Integer(i);
			firstN.push(num);
		}

		/* if the deck contains nothing
		   then we add the first (also the biggest)
		   number from the stack to the deck */
		if (this.deck.size() == 0) {
			this.deck.addFirst(firstN.pop());
		}

		/* we perform the following operations
		   until there are no more numbers left
		   in the stack */
		while(!firstN.empty()) {
			/* we obtain the last 'card' in
			   the deck and move it to the top
			   of the deck */
			Integer move = this.deck.pollLast();
			this.deck.addFirst(move);
			/* after the previous step, we add
			   the number at the top of the stack
			   to the top of the deck */
			this.deck.addFirst(firstN.pop());
		}

    }



    public static void main(String args[]) {
	// this is just creating a deck with cards in increasing order, and running the trick. 
	CardDeck d = new CardDeck(10);
	d.runTrick();

	// this is calling the method you are supposed to write, and executing the trick.
	CardDeck e = new CardDeck(0);
	e.setupDeck(10);
	e.runTrick();
    }
}