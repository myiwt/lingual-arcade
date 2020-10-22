package languages;

/**
 * 
 * This interface contains methods to access the Phrase information for various
 * languages. 
 * 
 * This interface also as a superclass so that the language Enum classes
 * will need to declare these interface methods, as Enum classes by definition, 
 * are final, so they cannot inherit classes (but they can implement interfaces).
 * 
 * @author ghq8692
 */
public interface Phrase {
    
    /**
     * Returns the pronounication of the (foreign) phrase.
     * @return Returns the pronounication of the (foreign) phrase.
     */
    public String getPronounciation();
    
    /**
     * Returns the Enum value which represents the English phrase, formatted to a 
     * readable String format.
     * 
     * @return Returns the Enum value which represents the English phrase, formatted 
     * to a readable String format.
     */
    @Override
    public String toString();
    
    /**
     * Returns the name of the language and is used for users to select languages of
     * Phrases to be tested on when running a Standard game.
     * @return Returns the name of the language in String format.
     */
    public String getLanguage();
    
    
    /**
     * Returns the phrase in the foreign language.
     * @return Returns the phrase in the foreign language.
     */
    public String getForeignPhrase();
}
