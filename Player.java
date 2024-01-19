
// Player class represents a player in the poker game

import java.util.ArrayList;

public class Player {

	private ArrayList<Card> hand; // the player's cards
	private double bankroll;
    private double bet;

	// you may choose to use more instance variables
		
	public Player(){		
	    // create a player here
        hand = new ArrayList<Card>();
        bankroll = 50; // initial bankroll
	}

	public void addCard(Card c){
	    // add the card c to the player's hand
        hand.add(c);

	}

	public void removeCard(Card c){
	    // remove the card c from the player's hand
        hand.remove(c);
        }
		
    public void bets(double amt){
        // Check if the bet is within the valid range
        if (amt < 1 || amt > 5){
            throw new IllegalArgumentException("Invalid bet amount. Please bet between 1 and 5 tokens.");
        }
        
        // player makes a bet
        if(amt > bankroll){
             throw new IllegalArgumentException("You don't have enough tokens");
        }
        bet = amt;
        bankroll -= amt;
    }

    public void winnings(double odds){
        //	adjust bankroll if player wins
        bankroll += odds;

    }

    public double getBankroll(){
         // return current balance of bankroll
        return bankroll;
    }

    public ArrayList<Card> getHand(){
        return new ArrayList<Card>(hand);
    }

    // you may wish to use more methods here
}


