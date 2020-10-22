package game;


import java.util.Scanner;

/**
 * A CardMatchingGame object is used to run a Card Matching Game in the application.
 * This class uses interactive input to manage the game. The user selects the cards 
 * to flip by entering input row/column grid locations of cards into the CUI. When 
 * a CardMatchingGame ends the score is saved into the ScoreBoard.
 * 
 * @author ghq8692
 */
public class CardMatchingGame extends Game {
    private Cards cards;
    private final int totalMatches;
    
    public CardMatchingGame(Scanner keyboard) {
        super();
        this.keyboard = keyboard;
        cards = new Cards();
        this.totalMatches = (cards.getCards().size())/2;
    }
    
}
