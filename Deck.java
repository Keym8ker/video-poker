
// Deck class, deck of playing cards

import java.util.Random;

public class Deck {
	
	private Card[] cards;
	private int top;     // the index of the top of the deck
	
	public Deck(){
		// make a 52 card deck here
        cards = new Card[52];
        
        // index of the card
        int i = 0;
        for(int s = 1; s <= 4; s++){         // suits
            for(int r = 1; r <= 13; r++){   //ranks
                cards[i++] = new Card(s,r);     //add card to array
            }
        }

        top = 0;
	}
	
	public void shuffle(){
		// shuffle the deck here
        Random rand = new Random();
        for(int i = cards.length - 1; i > 0; i--){   //this decides how many changes
            int a = rand.nextInt(i + 1);
            Card temp = cards[i];
            cards[i] = cards[a];
            cards[a] = temp;
        }
        top = 0;
	}
	
	public Card deal(){
		// deal the top card in the deck
        if(top < cards.length){
            return cards[top++];
        }else{
            return null;  // no card left
        }
	}
	

}
