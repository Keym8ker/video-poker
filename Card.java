
// card with a suit and rank

public class Card implements Comparable<Card>{
	
	private int suit; // use integers 1-4 to encode the suit
	private int rank; // use integers 1-13 to encode the rank
	
	public Card(int s, int r){
		suit = s;
		rank = r;
	}
	
	public int compareTo(Card c){
		// use this method to compare cards so they 
		// may be easily sorted
		if (this.rank != c.rank){
			return this.rank - c.rank;
		}else {
			return this.suit - c.suit;
		}

	}
	
	public String toString(){
		// use this method to easily print a Card object
		String[] suits = {"Clubs","Diamonds","Hearts","Spades"};
		String[] ranks = {"Wrong","Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
		return ranks[rank] + " of " + suits[suit - 1];
	}
	// to get suit
	public int getSuit(){
		return suit;
	}
    // to get rank
	public int getRank(){
		return rank;
	}

}
