package game;

import java.util.HashMap;
import java.util.Scanner;
import main.Scoreboard;

/**
 * COMP603: Program Design and Construction: Assessment 1
 * 1 September 2020
 * Semester 2 2020
 * 
 * A CardMatchingGame object is used to run a Card Matching Game in the application.
 * This class uses interactive input to manage the game. The user selects the cards 
 * to flip by entering input row/column grid locations of cards into the CUI. When 
 * a CardMatchingGame ends the score is saved into the ScoreBoard.
 * 
 * @author ghq8692 Megan Teh
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
    
    /**
     * Runs a CardMatchingGame by using interactive input to flip the cards by
     * entering referenced row/column locations of the cards displayed as a grid.
     * This method handles invalid input of invalid grid locations.
     */
    @Override
    public void runCUIGame() {
        HashMap<String, Card> cardLocationMap = cards.getCardLocationMap();
        HashMap<String,String> phrasesInGame = cards.getPhrasesInGame();
        int nMatches = 0;
        int nAttempts = 0;
        System.out.println("Starting a card matching game\n");
        
        while (nMatches < totalMatches) {
            System.out.println(cards.toString());
            String cardLocationInput1 = "";
            String cardLocationInput2 = "";
            Card card1 = null;
            Card card2 = null;
            String card1Label = "";
            String card2Label = "";
            boolean matched = false;
            boolean validCards = true;
            boolean cardAlreadyMatched = false;
            
            try {
                System.out.println("Please enter the first card you would like to flip");
                cardLocationInput1 = keyboard.nextLine();
                card1 = cardLocationMap.get(cardLocationInput1.toUpperCase());
                card1Label = card1.getLabel();
                if (card1.getIsMatched()) {
                    System.out.println("Invalid selection - this card has already been flipped - please try again.");
                    continue;
                }
                System.out.println("Card flipped: " + card1Label);
                System.out.println("Please enter the second card you would like to flip");
                cardLocationInput2 = keyboard.nextLine();
                card2 = cardLocationMap.get(cardLocationInput2.toUpperCase());
                card2Label = card2.getLabel();
                if (card2.getIsMatched()) {
                    System.out.println("Invalid selection - this card has already been flipped - please try again.");
                    continue;
                }
                System.out.println("Card flipped: " + card2Label);
            } catch (NullPointerException e) {
                System.out.println("Invalid cards selected, please try again");
                validCards = false;
            }
            if (!validCards) {
                continue;
            }
            if (phrasesInGame.containsKey(card1Label)) {
                if (phrasesInGame.get(card1Label).equals(card2Label)) {
                    matched = true;
                }
            } 
            if (phrasesInGame.containsKey(card2Label)) {
                if (phrasesInGame.get(card2Label).equals(card1Label)) 
                    matched = true;
            }
            
            if (cardAlreadyMatched) {
                System.out.println("Invalid card selection - please select cards that have not already been matched.");
                continue;
            }
            
            if (matched) {
                System.out.println("Match found!");
                
                for (Card card: cards.getCards()) {
                    if (card.getLabel().equalsIgnoreCase(card1Label)) {
                        card.setIsMatched(true);
                    }
                    if (card.getLabel().equalsIgnoreCase(card2Label)) {
                        card.setIsMatched(true);
                    }
                }
                nMatches++;
            } else {
                System.out.println("No match made");
            }
            nAttempts++;
        }
        saveScoreForCUI(nAttempts);
        System.out.println("Press any key to return to the main manu");
        keyboard.nextLine();
    }
    
    /**
     * At the end of the game, the score is saved into the Scoreboard. The score is
     * represented as an inverse relationship to the number of attempts that were
     * made to match all cards.
     * @param nAttempts integer representing the number of tries made to complete all
     * matches in a Card Matching Game.
     */
    @Override
    protected void saveScoreForCUI(int nAttempts) {
        score = 40 - nAttempts;
        if (score < 0)
            score = 0;
        System.out.println("Congratulations - you have matched all the cards in "+ nAttempts+" attempts.");
        System.out.println("Your score is: "+score);
        Scoreboard.saveScore("Card Game", score);
        System.out.println("This score has been saved to the scoreboard");
    }
}
