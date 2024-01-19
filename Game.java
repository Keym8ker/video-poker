
// Game class to manage the poker game

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Game {
	
	private Player p;
	private Deck cards;
	private boolean isTestMode = false;
	
	
	public Game(String[] testHand){
		// This constructor is to help test your code.
		// use the contents of testHand to
		// make a hand for the player
		// use the following encoding for cards
		// c = clubs
		// d = diamonds
		// h = hearts
		// s = spades
		// 1-13 correspond to ace-king
		// example: s1 = ace of spades
		// example: testhand = {s1, s13, s12, s11, s10} = royal flush

		//Test constructor
		p = new Player();
		cards = new Deck();
		for(String cardName : testHand){
            char suitName = cardName.charAt(0);
			int rank = Integer.parseInt(cardName.substring(1));
			int suit;
			switch(suitName){
				case 'c':
                  suit = 1; 
                  break;
                case 'd':
                  suit = 2;
                  break;
                case 'h':
				  suit = 3;
				  break;
				case 's':
				  suit = 4;
				  break;
				default:
				  throw new IllegalArgumentException("Wrong input!");
			}
			p.addCard(new Card(suit, rank));
			isTestMode = true;
        }

		
	}
	
	public Game(){
		// This no-argument constructor is to actually play a normal game
		// constructor for a normal game
		p = new Player();
		cards = new Deck();
		cards.shuffle();
		
	}
	
	public void play(){
		// this method should play the game	
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to the GAME!!!");
		System.out.println("Now you have " + p.getBankroll() + " tokens.");
		System.out.print("How many tokens you want to bet? (1-5): ");
		double betNumber = input.nextDouble();
		p.bets(betNumber);

		// Deal 5 cards to hand, first deal
		// Don't do normal dealing if in test mode
        if (!isTestMode) {
            // Deal 5 cards to hand, first deal
            for (int i = 1; i <= 5; i++) {
                p.addCard(cards.deal());
            }
        }

		// Make a copy of original hand
		ArrayList<Card> firstHand = new ArrayList<>(p.getHand());
		System.out.println("Your hand is: " + p.getHand());
        System.out.print("How many cards do you want to replace? ");
		int cardsRep = input.nextInt();

		for(int i =0; i < cardsRep; i++){
			System.out.println("Your current hand: " + p.getHand());
			System.out.println("Which card you want to replace? (1-5)");
			int cardIndex = input.nextInt() - 1;

			// Check if the card at the specified index is part of the original hand
			if(firstHand.contains(p.getHand().get(cardIndex))){
				firstHand.remove(p.getHand().get(cardIndex)); // Remove the card from the original hand list
				p.removeCard(p.getHand().get(cardIndex));
				p.addCard(cards.deal());
			}else{
				System.out.println("You cannot replace this card, choose another one.");
				i--;
			}
		}

		// check what you have in hand
		String myHand = checkHand(p.getHand());
		System.out.println("Your hand: " + p.getHand());
        System.out.println("Result: " + myHand);

		// Calculate winnings
		double winnings = calculatePayout(myHand)*betNumber;
        p.winnings(winnings);
        System.out.println("You now have " + p.getBankroll() + " tokens.");
		input.close();

	}

	// get the ranks of hand
	private int[] getRanks(ArrayList<Card> hand){
		int[] ranks = new int[14]; // use 14 is becausw it is easier to index
		for(Card card : hand){
			ranks[card.getRank()]++; // track how many times each rank appears in the hand.
		}
		return ranks;

	}
	
	public String checkHand(ArrayList<Card> hand){
		// this method should take an ArrayList of cards
		// as input and then determine what evaluates to and
		// return that as a String
		
		Collections.sort(hand);

		boolean isFlush = checkFlush(hand);
		boolean isStraight = checkStraight(hand);
		int[] ranks = getRanks(hand);

		if(isFlush && isStraight){
			if(hand.get(0).getRank() == 1){
				return "Royal Flush";
			}else{
				return "Straight Flush";
			}
		}

		if (isFourOfAKind(ranks)) return "Four of a Kind";
        if (isFullHouse(ranks)) return "Full House";
        if (isFlush) return "Flush";
        if (isStraight) return "Straight";
        if (isThreeOfAKind(ranks)) return "Three of a Kind";
        if (isTwoPairs(ranks)) return "Two Pairs";
        if (isOnePair(ranks)) return "One Pair";
        return "No Pair";

	}

	// Methods of checkHand
	// when checking, start with the most hard situation
	// if not this harderone go check the next easier One

	private boolean checkFlush(ArrayList<Card> hand){
		int suit = hand.get(0).getSuit();
		for(Card card : hand){
			if(card.getSuit() != suit){
				return false;
			}
		}
		return true;
	}

	private boolean checkStraight(ArrayList<Card> hand){
		int firstRank = hand.get(0).getRank();
		if(hand.get(0).getRank() == 1 && hand.get(1).getRank() == 10 
		&& hand.get(2).getRank() == 11 && hand.get(3).getRank() == 12
		&& hand.get(4).getRank() == 13){
			return true;
		}else{
			for(int i = 1; i < hand.size(); i++){
				if(hand.get(i).getRank() != firstRank + i){
					return false;
				}
			}
		}
		return true;
	}

	private boolean isFourOfAKind(int[] ranks){
		for(int i : ranks){
			if(i == 4){
				return true;
			}
		}
		return false;
	}

	private boolean isFullHouse(int[] ranks){
		boolean threePair = false;
		boolean twoPair = false;
		for(int i : ranks){
			if(i == 3){
				threePair = true;
			}
			if(i == 2){
				twoPair = true;
			}
		}
		return threePair && twoPair;
	}

	private boolean isThreeOfAKind(int[] ranks) {
        for (int rank : ranks) {
            if (rank == 3) return true;
        }
        return false;
    }

	private boolean isTwoPairs(int[] ranks) {
        int pairs = 0;
        for (int i : ranks) {
            if (i == 2){
				pairs++;
			}
		}
        return pairs == 2;
	}

	private boolean isOnePair(int[] ranks){
		for(int i : ranks){
			if(i == 2){
				return true;
			}
		}
		return false;
	}

	// Calculate payout
	private double calculatePayout(String myHand) {
        switch (myHand) {
            case "Royal Flush": return 250;
            case "Straight Flush": return 50;
            case "Four of a Kind": return 25;
            case "Full House": return 6;
            case "Flush": return 5;
            case "Straight": return 4;
            case "Three of a Kind": return 3;
            case "Two Pairs": return 2;
            case "One Pair": return 1;
            default: return 0;
        }
    }

}
