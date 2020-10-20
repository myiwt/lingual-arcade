package languages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * COMP603: Program Design and Construction: Assessment 1
 * 1 September 2020
 * Semester 2 2020
 * 
 * Stores data for the Spanish phrases, their English translations and pronounciations.
 * These enum values are used to create Question objects which is used to define
 * the test questions and multiple-choice answers.
 * 
 * @author ghq8692 Megan Teh
 */
public enum Spanish implements Phrase {
    HELLO("HOLA","O-LA"),
    GOOD_MORNING("BUENOS DIAS","BWAY-NOS DEE-AS"),
    GOOD_AFTERNOON_OR_GOOD_EVENING("BUENAS TARDES","BWAY-NAS TAR-DES"),
    GOOD_NIGHT("BUENAS NOCHES","BWAY-NAS NOH-CHAYS"),
    HOW_ARE_YOU_FORMAL("COMO ESTA?","KOH-MOH EH-STAH"),
    HOW_ARE_YOU_INFORMAL("COMO ESTAS?","KOH-MOH EH-STAHS"),
    IM_FINE_THANK_YOU("BIEN, GRACIAS","BEE-EN GRA-THEE-AS [SPAIN] / GRA-SEE-AS [LATIN AMERICA]"),
    WHATS_YOUR_NAME("COMO TE LLAMAS?","KOH-MOH TE YA-MAS?"),
    MY_NAME_IS("ME LLAMO…","MAY YA-MOH… "),
    NICE_TO_MEET_YOU("MUCHO GUSTO","MOO-CHOH GOO-STOH"),
    PLEASE("POR FAVOR","POR FA-VOR"),
    THANK_YOU("GRACIAS","GRA-THEE-AS [SPAIN] / GRA-SEE-AS [LATIN AMERICA]"),
    I_DONT_UNDERSTAND("YO (NO) ENTIENDO","YO NO EN-TEE-EN-DOH"),
    DO_YOU_SPEAK_ENGLISH("HABLA INGLES?","AB-LA IN-GLAYS"),
    I_WANT_A_MENU("YO QUIERO UN MENU","YO KEE-EH-RO OON ME-NOO"),
    I_WANT_A_TAXI("YO QUIERO UN TAXI","YO KEE-EH-RO OON TAXI"),
    I_WANT_A_BEER("YO QUIERO UNA CERVEZA","YO KEE-EH-RO OO-NA SER-VAY-ZA"),
    I_WOULD_LIKE("QUISIERA …","KEE-SEE-EH-RA…"),
    WHERE_IS_THE_BATHROOM("DONDE ESTA EL BANO?","DON-DAY ES-TAH EL BAH-NYO?"),
    WHERE_IS_THE_BANK("DONDE ESTA EL BANCO?","DON-DAY ES-TAH EL BAN-KOH?"),
    WHERE_IS_ALCALA_STREET("DONDE ESTA LA CALLE [DE ALCALA]?","DON-DAY ES-TAH LA KA-YAY DE AL-CAL-AH?"),
    EXCUSE_ME("DISCULPE","DIS-KUL-PAY"),
    PARDON_ME("CON PERMISO/PERDONAME","CON PER-MEE-SOH / PER-DOH-NAH-MAY"),
    IM_LOST("ESTOY PERDIDO","EH-STOY PER-DEE-DOH"),
    HERE("AQUI","AH-KEE"),
    THERE("ALLI","AY-EE"),
    ON_THE_RIGHT("A LA DERECHA","A LA DE-RE-CHA"),
    ON_THE_LEFT("A LA IZQUIERDA","A LA IZ-KEE-ER-DA"),
    STRAIGHT_AHEAD("DERECHO","DE-RE-CHO"),
    AT_THE_CORNER("EN LA ESQUINA","EN LA ES-KEE-NAH"),
    IN_ONE_BLOCK("A UNA CUADRA","A OO-NA KWAD-RAH"),
    WHERE_CAN_I_GET_A_TAXI("DONDE PUEDO ENCONTRAR UN TAXI?","DON-DAY PWAY-DOH EN-KON-TRAR OON TAXI?"),
    WHERE_IS_THE_NEAREST_BUS_STOP("DONDE ESTA LA PARADA DE AUTOBÚS MAS CERCA?","DON-DAY EH-STAH LA PA-RAH-DAH DE OWW-TO-BOOS MAS SER-KA?"),
    WHERE_IS_THE_NEAREST_RAILWAY_STATION("DONDE ESTA LA ESTACION DE FERROCARRIL MAS CERCA?","DON-DAY EH-STAH LA ES-TAH-SEE-ON DE FERRO-CARR-EEL MAS SER-KA?"),
    HOW_MUCH_DOES_A_TICKET_TO_SOMEWHERE_COST("CUANTO CUESTA UN BILLETE PARA … ?","KWAN-TA KWES-TA OON BEE-YET-AY PA-RA …"),
    A_TICKET_TO_SOMEWHERE_PLEASE("UN BILLETE PARA … , POR FAVOR.","OON BEE-YET-AY PA-RA … POR FA-VOR"),
    WOULD_YOU_LIKE_SOMETHING_TO_EAT("QUIERES ALGO PARA COMER?","KEE-EH-RES AL-GO PA-RA KOH-MER?"),
    WOULD_YOU_LIKE_SOMETHING_TO_DRINK("QUIERES ALGO PARA BEBER?","KEE-EH-RES AL-GO PA-RA BEH-BER?"),
    WHAT_WOULD_YOU_LIKE_TO_EAT("QUE QUIERES COMER?","KAY KEE-EH-RES KOH-MER?"),
    AN_APPETIZER("UNA ENTRADA","OO-NA EN-TRA-DA"),
    A_MAIN_DISH("UN PLATO PRINCIPAL","OON PLA-TOH PRIN-SI-PAL"),
    A_DESSERT("UN POSTRE","OON POS-TRAY"),
    A_DRINK("UNA BEBIDA","OO-NA BEH-BEE-DA"),
    SOUP("UNA SOPA","OO-NA SOH-PAH"),
    SALAD("UNA ENSALADA","OO-NA EN-SA-LA-DA"),
    CHICKEN("EL POLLO","EL POY-OH"),
    THE_MEAT("LA CARNE","LA CAR-NAY"),
    WATER("UNA AGUA","OO-NA AG-WA"),
    RED_WINE("UN VINO TINTO /BLANCO","OON VEE-NOH TIN-TOH / BLAN-KOH"),
    BEER("UNA CERVEZA","OO-NA SER-VAY-SA"),
    COFFEE("UN CAFE","OON KA-FAY"),
    WHAT_DO_YOU_RECOMMEND("QUE ME RECOMIENDA?","KAY MAY RE-KOM-EE-EN-DAH?"),
    I_AM_A_VEGETARIAN("SOY VEGETARIANO/A","SOY VE-HE-TAH-REE-AH-NOH/NAH"),
    I_HAVE_AN_ALLERGY_TO_NUTS("TENGO ALERGIA A LAS NUECES","TEN-GO AL-ER-HEE-AH A LAS NOO-EH-SES"),
    HOW_MUCH_IS_IT("CUANTO CUESTA?","KWAN-TO KWES-TA?"),
    THE_BILL_PLEASE("LA CUENTA, POR FAVOR","LA KWEN-TA POR FA-VOR"),
    WHO("QUIEN?","KEE-EN?"),
    WHAT("QUE?","KAY?"),
    WHERE("DONDE?","DON-DAY?"),
    WHEN("CUANDO?","KWAN-DOH?"),
    WHAT_TIME("A QUE HORA?","A KAY AW-RA?"),
    WHY("POR QUE?","POR KAY?"),
    HOW("COMO?","KOH-MOH?"),
    HOW_MUCH("CUANTO?","KWAN-TOH?"),
    HOW_MANY("CUANTOS?","KWAN-TOHS"),
    HOW_OFTEN("CADA CUANTO?","KAH-DAH KWAN-TOH?"),
    HOW_LONG("POR CUANTO TIEMPO?","POR KWAN-TOH TEE-EM-POH");

    private final String foreignPhrase;
    private final String pronounciation;
    
    private Spanish(String foreignPhrase, String pronounciation) {
        this.foreignPhrase = foreignPhrase;
        this.pronounciation = pronounciation;
    }
    
    /**
     * Returns a list of all Phrases in this Enum class as an ArrayList of Phrases.
     * @return Returns a list of all Phrases in this Enum class as an ArrayList of Phrases.
     */
    public static List<Phrase> getAllPhrases() {
        List<Phrase> allPhrases = new ArrayList<>();
        allPhrases.addAll(Arrays.asList(Spanish.values()));
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
