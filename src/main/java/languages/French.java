package languages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * Stores data for the French phrases, their English translations and pronounciations.
 * These enum values are used to create Question objects which is used to define
 * the test questions and multiple-choice answers.
 * 
 * @author ghq8692
 */
public enum French implements Phrase {
    HELLO("SALUT","SAH-LOO"),
    GOOD_MORNING("BONJOUR","BOHN-JUR"),
    GOOD_EVENING("BONSOIR","BOHN-SWAH"),
    GOOD_NIGHT("BONNE NUIT","BOHN NWEE"),
    GOODBYE("AU REVOIR","OH REV-WAR"),
    HOW_ARE_YOU("COMMENT ALLEZ-VOUS?","KOHM-MOHN AHL-LEH-VOO?"),
    IM_WELL_AND_YOU("ÇA VA BIEN, ET VOUS?","SA VA BYEN, EH VOO?"),
    GOOD_THANKS("ÇA VA BIEN, MERCI","SA VA BYEN, MER-SEE"),
    PLEASE("SIL VOUS PLAÎT","SIL VOO PLEH"),
    THANK_YOU("MERCI","MER-SEE"),
    YOURE_WELCOME("DE RIEN","DEH REE-EN"),
    YES("OUI","WEE"),
    NO("NO","NOH"),
    EXCUSE_ME("PARDONNEZ-MOI","PAR-DON-EH-MWAH"),
    IM_SORRY("JE SUIS DÉSOLÉ","JEH SWEE DEH-ZOL-LEH"),
    I_DONT_UNDERSTAND("JE NE COMPRENDS PAS","JEHN KOM-PRON PAH"),
    DO_YOU_SPEAK_ENGLISH("PARLEZ-VOUS ANGLAIS?","PAR-LEH-VOO ON-GLEH"),
    HOW_MUCH_IS_("COMBIEN COÛTE?","KOHM-BYEN KOOT"),
    WHERE_IS("OÙ CEST?","OO SEHT?"),
    WHEN("QUAND?","KOHN?"),
    MAY_I_PLEASE_HAVE("EST-CE QUE JE POURRAIS AVOIR...?","ES-KERH JEH POO-RAY AV-WAR...?"),
    BEER("BIÈRE","BYAIR"),
    WINE("VIN","VAHN"),
    WATER("EAU","OH"),
    I_DONT_EAT("JE NE MANGE PAS DE….","JEHN MONJ PAH DUH"),
    IM_A_VEGETARIAN("JE SUIS VÉGÉTARIEN (MASC.) / VÉGÉTARIENNE (FEM.)","JE SWEE VE-JEH-TAIR-REE-AN / VE-JEH-TAIR-REE-EN"),
    THE_BILL_PLEASE("LADDITION, SIL VOUS PLAÎT","LAH-DEE-ZYON, SIL VOO PLEH"),
    LEFT("GAUCHE","GAWSH"),
    RIGHT("DROITE","DRWAH"),
    STRAIGHT_AHEAD("TOUT DROITE","TOO DWAT"),
    TURN_LEFT("TOURNEZ À GAUCHE","TOR-NEY AH GAWSH"),
    TURN_RIGHT("TOURNEZ À DROITE","TOR-NEY AH DRWAT"),
    BUS_STOP("ARRÊT DE BUS","AH-RET DEH BOOS"),
    TRAIN_STATION("GARE","GAAR"),
    AIRPORT("AÉROPORT","AH-EH-RO-POR"),
    ENTRANCE("ENTRÉE","OHN-TREH"),
    EXIT("SORTIE","SOR-TEE"),
    ONE("UN","UHN"),
    TWO("DEUX","DURH"),
    THREE("TROIS","TWAH"),
    FOUR("QUATRE","KAHTRE"),
    FIVE("CINQUE","SAHNK"),
    SIX("SIX","SEES"),
    SEVEN("SEPT","SET"),
    EIGHT("HUIT","WEET"),
    NINE("NEUF","NURF"),
    TEN("DIX","DEES"),
    TWENTY("VINGT","VAHN"),
    THIRTY("TRENTE","TRONT"),
    FORTY("QUARANTE","KA-RONT"),
    FIFTY("CINQUANTE","SANK-ONT"),
    SIXTY("SOIXANTE","SWA-SONT"),
    SEVENTY("SOIXANTE-DIX","SWA-SONT-DEES"),
    EIGHTY("QUATRE-VINGT","KAHR-RA-VAHN"),
    NINETY("QUATRE-VINGT-DIX","KAHR-RA-VAHN-DEES"),
    ONE_HUNDRED("CENT","SOHN"),
    MONDAY("LUNDI","LUHN-DEE"),
    TUESDAY("MARDI","MAHR-DEE"),
    WEDNESDAY("MERCREDI","MEHR-KRUH-DEE"),
    THURSDAY("JEUDI","ZHUH-DEE"),
    FRIDAY("VENDREDI","VAHN-DRUH-DEE"),
    SATURDAY("SAMEDI","SAHM-DEE"),
    SUNDAY("DIMANCHE","DEE-MAHNSH"),
    HELP("À LAIDE","AH LED"),
    I_NEED_A_DOCTOR("JAI BESOIN DUN MÉDICIN","JAY BEZ-WAHN DOHN MEH-DEH-SAHN"),
    I_DONT_FEEL_WELL("JE NE ME SENS PAS BIEN","JEH NEH MEH SAHN PAH BYEN"),
    CALL_THE_POLICE("APPELEZ LA POLICE","AP-LEH LAH PO-LEES");

    private final String foreignPhrase;
    private final String pronounciation;

    private French(String foreignPhrase, String pronounciation) {
        this.foreignPhrase = foreignPhrase;
        this.pronounciation = pronounciation;
    }

    /**
     * Returns a list of all Phrases in this Enum class as an ArrayList of Phrases.
     * @return Returns a list of all Phrases in this Enum class as an ArrayList of Phrases.
     */
    public static List<Phrase> getAllPhrases() {
        List<Phrase> allPhrases = new ArrayList<>();
        allPhrases.addAll(Arrays.asList(French.values()));
        return allPhrases;
    }
    
    /**
     * Returns the pronounication of the (foreign) phrase.
     * @return Returns the pronounication of the (foreign) phrase.
     */
    @Override
    public String getPronounciation() {
        return this.pronounciation;
    }
    
    /**
     * Returns the Enum value which represents the English phrase, formatted to a 
     * readable String format.
     * 
     * @return Returns the Enum value which represents the English phrase, formatted 
     * to a readable String format.
     */
    @Override
    public String toString() {
        return this.name().replace("_", " ");
    }

    /**
     * Returns the name of the language and is used for users to select languages of
     * Phrases to be tested on when running a Standard game.
     * @return Returns the name of the language in String format.
     */
    @Override
    public String getLanguage() {
        return this.getClass().getSimpleName();
    }
    
    /**
     * Returns the phrase in the foreign language.
     * @return Returns the phrase in the foreign language.
     */
    @Override
    public String getForeignPhrase() {
        return this.foreignPhrase;
    }

}
