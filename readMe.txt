Card.java: Represents individual playing cards. 
Cards are encoded using integers for suits (1-4) and ranks (1-13).
Represents a playing card with a suit and a rank.
Suits are encoded as integers 1-4 (Clubs, Diamonds, Hearts, Spades), 
and ranks are 1-13 (Ace to King).
Implements Comparable for easy sorting and comparison based on poker rules.

Deck.java: Manages a deck of cards, 
providing functionality to shuffle and deal cards.
Manages a standard deck of 52 playing cards.
Features methods for shuffling and dealing cards, crucial for game mechanics.

Player.java: Represents a player, 
holding their current hand, managing their bankroll, 
and tracking bets and winnings.
Models a poker game player.
Maintains the player's hand, bankroll, and betting actions.
Methods for adding/removing cards and managing bets and winnings.

Game.java: The core of the application. 
It manages game flow, including dealing cards, 
enabling card replacements, and evaluating hands.
Orchestrates the overall game flow and logic.
Handles the initial dealing of cards, 
player decisions to hold or replace cards, 
and evaluates the final hand.
Includes a special test constructor for predefined hands, 
aiding in debugging and hand evaluation testing.

Modular design was chosen for clarity and maintainability. 
Each class has a single responsibility, 
making the codebase easier to understand and modify.
The game follows standard poker rules, 
assessing hand rankings from high to low.