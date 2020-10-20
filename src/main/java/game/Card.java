package game;

/**
 * COMP603: Program Design and Construction: Assessment 1
 * 1 September 2020
 * Semester 2 2020
 * 
 * A Card is used to build a Cards object for a Card Matching Game. 
 * A Card contains the text representing what is written on a Card when it is 
 * flipped when playing a CardMatchingGame. A Card also contains a boolean 
 * indicating whether or not the Card has been matched whilst playing a
 * CardMatchingGame.
 * 
 * @author ghq8692 Megan Teh
 */
public class Card {
    
    // A label represents the text written on the Card (A Phrase or an English translation)
    private String label; 
    private boolean isMatched;
    
    
    public Card(String label) {
        this.label = label;
        this.isMatched = false;
    }
    
    public String getLabel() {
        return label;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }
    
    public boolean getIsMatched() {
        return isMatched;
    }

    public void setIsMatched(boolean isMatched) {
        this.isMatched = isMatched;
    }
    
    /**
     * When a Card has not been matched, it is represented by the symbol [o]
     * When a Card has already been matched, it is represented by the symbol [x]
     * @return Returns the symbol representation of a Card when displayed in the CUI.
     */
    @Override
    public String toString() {
        return (isMatched ? "[o]":"[x]");
    }
    
}
