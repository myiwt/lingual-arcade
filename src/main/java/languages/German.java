package languages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * Stores data for the German phrases, their English translations and pronounciations.
 * These enum values are used to create Question objects which is used to define
 * the test questions and multiple-choice answers.
 * 
 * @author ghq8692
 */
public enum German implements Phrase {
    HELLO("HALLO","HA-LOH"),
    GOOD_DAY("GUTEN TAG","GOOH-TEN TAHK"),
    GOOD_EVENING("GUTEN ABEND","GOOH-TEN AH-BENT"),
    GOODBYE("AUF WIEDERSEHEN!","OUF VEE-DER-ZEY-EN!"),
    PLEASE_OR_YOURE_WELCOME("BITTE","BIH-TUH"),
    THANK_YOU("DANKE","DÂN-KE"),
    EXCUSE_ME("ENTSCHULDIGUNG","ENT-SHOOL-DE-GEN ZEE"),
    MY_NAME_IS("ICH HEIßE….","IH HI-SUH…."),
    PLEASED_TO_MEET_YOU("FREUT MICH","FROYT MIH"),
    DO_YOU_SPEAK_ENGLISH("SPRECHEN SIE ENGLISCH","SHPREH-EN ZEE ANG-LISH"),
    HOW_ARE_YOU("WIE GEHT'S?","VEE GETS"),
    WOULD_YOU_HELP_ME_PLEASE("WÜRDEN SIE MIR BITTE HELFEN?","VUER-DEN ZEE MEER BI-TEHÊL-FEN?"),
    WHATS_YOUR_NAME("WIE HEIßEN SIE?","VEE HAYS-EN ZEE?"),
    WHAT_TIME_IS_IT("WIE VIEL UHR IST ES?","VEE FEEL OOHR IST ÊS?"),
    WHATS_THE_WEATHER_LIKE("WIE IST DAS WETTER?","EE IST DÂS VÊT-ER?"),
    HOW_MUCH_DOES_THIS_COST("WIE VIEL KOSTET . . .?","VEE FEEL KOS-TET…?"),
    WHERE_DO_I_FIND("WO FINDE ICH . . .?","VOH FIN-DE IH…?"),
    WHERE_ARE_THE_BATHROOMS("WO SIND DIE TOILETTEN?","VOH ZINT DEE TOY-LÊT-EN?"),
    DO_YOU_HAVE("HABEN SIE…?","HAH-BEN ZEE…?"),
    WHERE_IS("WO IST…?","VOH IST…?"),
    COULD_YOU_PLEASE_TALK_MORE_SLOWLY("KÖNNEN SIE BITTE LANGSAMER SPRECHEN?","KERN-EN ZEE BI-TE LÂNG-ZÂM-ER SHPRÊH-EN?"),
    COULD_YOU_REPEAT_THAT_PLEASE("KÖNNEN SIE DAS BITTE WIEDERHOLEN?","KERN-EN ZEE DÂS BI-TEVEE-DER-HOH-LEN?"),
    THE_MENU_PLEASE("DIE SPEISEKARTE BITTE","DEE SHPAY-ZE KÂR-TE BI-TE"),
    ID_LIKE("ICH MÖCHTE GERN….","IH MERH-TE GERN…."),
    COULD_YOU_RECOMMEND_SOMETHING("KÖNNTEN SIE ETWAS EMPFEHLEN?","KERN-TEN ZEE ÊT-VAS ÊM-PFEY-LEN?"),
    ANOTHER_ONE_PLEASE("NOCH EIN BITTE","NOH AYN BI-TE"),
    THE_CHECK_PLEASE("DIE RECHNUNG BITTE","DEE RÊH-NOONG BI-TE"),
    A_RECEIPT_PLEASE("EINE QUITTUNG BITTE","AYN-E KVI-TOONG BI-TE"),
    ENJOY_YOUR_MEAL("GUTEN APPETIT","GOOH-TEN ÂP-E-TEET"),
    HELP("HILFE!","HILF-E!"),
    POLICE("POLIZEI!","PO-LI-TSAY!"),
    FIRE("FEUER!","FOY-ER!"),
    GET_A_DOCTOR("HOLEN SIE EINEN ARZT!","HOHL-EN ZEE AYN-EN ÂRTST!"),
    I_AM_SICK("ICH BIN KRANK","IH BIN KRANK"),
    I_DONT_KNOW_MY_WAY_AROUND_HERE("ICH KENNE MICH HIER NICHT AUS","IH KEN-EH MIH HEER NIHT OUS"),
    I_AM("ICH BIN... ","ICH BIN"),
    A_ROOM("EIN ZIMMER ","EYE-N TSIM-AIR"),
    A_RENTAL_CAR("EIN MIETWAGEN ","EYE-N MEET-VAHGEN"),
    A_BANK("EINE BANK ","EYE-NUH BAHNK"),
    THE_POLICE("DIE POLIZEI ","DEE PO-LIT-ZYE"),
    THE_TRAIN_STATION("DER BAHNHOF ","DARE BAHN-HOF"),
    THE_AIRPORT("DER FLUGHAFEN ","DARE FLOOG-HAFEN"),
    WHERES_THE_RESTROOM("WO IST DIE TOILETTE? ","VO IST DEE TOY-LET-UH"),
    LEFT("LINKS","LINX"),
    RIGHT("RECHTS","REXTS"),
    DOWNSTAIRS("UNTEN","OON-TIN"),
    UPSTAIRS("OBEN","OH-BIN"),
    ZERO("NULL","NOOL"),
    ONE("EINS","AYNS"),
    TWO("ZWEI","TSVAY"),
    THREE("DREI","DRAY"),
    FOUR("VIER","FEER"),
    FIVE("FÜNF","FUENF"),
    SIX("SECHS","ZEKS"),
    SEVEN("SIEBEN","ZEE-BEN"),
    EIGHT("ACHT","ACKHT"),
    NINE("NEUN","NOYN"),
    TEN("ZEHN","TSEYN"),
    MONDAY("MONTAG","MOHN-TAHK"),
    TUESDAY("DIENSTAG","DEENS-TAHK"),
    WEDNESDAY("MITTWOCH","MIT-VOH"),
    THURSDAY("DONNERSTAG","DON-ERS-TAHK"),
    FRIDAY("FREITAG","FRY-TAHK"),
    SATURDAY("SAMSTAG / SONNABEND","ZAMS-TAHK / ZON-AH-BENT"),
    SUNDAY("SONNTAG","ZON-TAHK");
    
    private final String foreignPhrase;
    private final String pronounciation;

    private German(String foreignPhrase, String pronounciation) {
        this.foreignPhrase = foreignPhrase;
        this.pronounciation = pronounciation;
    }
    
    /**
     * Returns a list of all Phrases in this Enum class as an ArrayList of Phrases.
     * @return Returns a list of all Phrases in this Enum class as an ArrayList of Phrases.
     */
    public static List<Phrase> getAllPhrases() {
        List<Phrase> allPhrases = new ArrayList<>();
        allPhrases.addAll(Arrays.asList(German.values()));
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
