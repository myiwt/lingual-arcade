package game;

import java.util.*;
import languages.*;

/**
 * COMP603: Program Design and Construction: Assessment 1
 * 1 September 2020
 * Semester 2 2020
 * 
 * The Cards object represents a group of Card objects that are used when playing
 * a CardMatchingGame. The Cards are laid out in rows and columns in the CUI and
 * the display shows which cards have already been matched and which ones have 
 * not been matched when playing a CardMatchingGame. This class also checks if 
 * two flipped Card objects are correct matches or not.
 * 
 * @author Megan Teh ghq8692
 */
public class Cards {
    
    private List<Phrase> phraseBank;
    private final int nQuestions = 16; // Total number of cards in a CardMatchingGame
    // cardRows and cardColumns define the layout of the Cards as rows and columns
    private final int cardRows = 4;
    private final int cardColumns = 4;
    private HashMap<String,String> phrasesInGame;
    private List<Card> cards;
    private HashMap<String, Card> cardLocationMap;
    
    public Cards() {
        phrasesInGame = new HashMap<String, String>(); // Represents the text for the matching card pairs
        phraseBank = new ArrayList<Phrase>();
        cards = new ArrayList<Card>();
        cardLocationMap = new HashMap<String,Card>();
        selectPhrases();
        generateCards();
        mapCardLocations();
    }
    
    /**
     * Randomly selects Phrases to use for the matching cards.
     */
    private void selectPhrases() {
        addAllPhrases();
        Collections.shuffle(phraseBank);
        for (int i = 0; i < nQuestions/2; i++) {
            Phrase phrase = phraseBank.get(i);
            String english = phrase.toString();
            String foreignPhrase = phrase.getForeignPhrase();
            phrasesInGame.put(english,foreignPhrase);
        }
    }
    
    /**
     * Generates the Cards and the matching card labels (the text on a matching card
     * that is revealed when a card is flipped in the game).
     */
    private void generateCards() {
        for (Map.Entry<String,String> entry: phrasesInGame.entrySet()) {
            cards.add(new Card(entry.getKey()));
            cards.add(new Card(entry.getValue()));
        }
        Collections.shuffle(cards);
    }
    
    /**
     * Maps the Cards to rows and letter locations so that the user can call a 
     * location in the CUI when choosing Cards to flip when playing a 
     * CardMatchingGame. Rows are represented by letters and columns by numbers
     * (example of card locations are A2, B4, C2)
     */
    private void mapCardLocations() {
        int rowInt = 65;
        Iterator iter = cards.iterator();
        for (int i = 0; i < cardRows; i++) {
            String rowLetter = String.valueOf((char) (rowInt+i));
            for (int j = 1; j <= cardColumns; j++) {
                String location = rowLetter + j;
                cardLocationMap.put(location, (Card) iter.next());
            }
        }
    }
    
    /**
     * Compiles the phraseBank which represents all Phrases that can be used to
     * create the matching cards. The phraseBank will consist of all Phrases
     * available of all languages.
     */
    public void addAllPhrases() {
        phraseBank.addAll(Arrays.asList(Spanish.values()));
        phraseBank.addAll(Arrays.asList(French.values()));
        phraseBank.addAll(Arrays.asList(German.values()));
    }
    
    /**
     * Returns a list of Card objects
     * @return Returns a list of Card objects
     */
    public List<Card> getCards() {
        return cards;
    }
    
    /**
     * Returns a HashMap representing Card objects and their 'grid locations'
     * when the Cards are displayed in the CUI as a grid.
     * @return Returns a HashMap representing Card objects and their 'mapped 
     * locations' when the Cards are displayed in the CUI as a grid.
     */
    public HashMap<String,Card> getCardLocationMap() {
        return cardLocationMap;
    }
    
    /**
     * Returns all the matching card pairs in a CardMatchingGame as key-value
     * pairs in a HashMap.
     * @return Returns all the matching card pairs in a CardMatchingGame as 
     * key-value pairs in a HashMap.
     */
    public HashMap<String,String> getPhrasesInGame() {
        return phrasesInGame;
    }
    
    /**
     * Returns the Card object found at the grid location, as the Cards are 
     * organised in a grid layout.
     * @param cardLabel String representing a row/column grid location (e.g. A1, B2)
     * @return Returns the Card object found at the input grid location.
     */
    public Card findCard(String cardLabel) {
        Card card = null;
        for (Card c: cards) {
            if (c.getLabel().equalsIgnoreCase(cardLabel)) {
                card = c;
                break;
            }
        }
        return card;
    }

    /**
     * Cards are displayed in the GUI as a grid, with the rows and columns labelled.
     * The symbol representations of each card show whether or not a Card has already
     * been matched or not in a CardMatchingGame.
     * @return The text representation of Cards for the CUI.
     */
    @Override
    public String toString() {
        Iterator iter = cards.iterator();
        int rowLetter = 65;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < cardRows + 1; i++) {
            sb.append("    "+i);
        }
        sb.append("\n");
        for (int i = 0; i < cardRows; i++) {
            sb.append((char) (rowLetter+i) +"  ");
            for (int j = 0; j < cardColumns; j++) {
                sb.append(iter.next()+"  ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
