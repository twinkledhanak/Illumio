package Common;

/**
 * @author twinkledhanak on 02/07/24
 * @project Illumio
 */
public class Constants {
    // Input, Output config
    /*
    * Sample path I used on my local machine:
    public static final String INPUT_FILE_PATH = "/Users/twinkledhanak/Documents/GitHub/Illumio/data/Input/Sentences.txt";
    public static final String PREDEF_WORDS_FILE_PATH = "/Users/twinkledhanak/Documents/GitHub/Illumio/data/Input/PreDefinedWords.txt";
    public static final String OUTPUT_FILE_PATH = "/Users/twinkledhanak/Documents/GitHub/Illumio/data/Output/";
    */

    public static final String INPUT_FILE_PATH = "<--YOUR PATH HERE-->"+"/Illumio/data/Input/Sentences.txt";
    public static final String PREDEF_WORDS_FILE_PATH = "<--YOUR PATH HERE-->"+"/Illumio/data/Input/PreDefinedWords.txt";
    public static final String OUTPUT_FILE_PATH = "<--YOUR PATH HERE-->"+"Illumio/data/Output/";

    public static final String OUTPUT_FILE_EXTENSION = ".csv";
    public static final String FILE_COLUMNS = "Predefined word\tMatch count\n";

    // Cache
    public static final int INITIAL_CAP = 15000;
    public static final float LOAD_FACTOR = 0.75f;  // We rehash elements after cache is 75% full

    // Job
    public static final String JOB_NAME = "Illumio Word Matcher";
    public static final int JOB_SUCCESS = 0;
    public static final int JOB_FAILURE = 1;

    // Mapper
    public static final String NEWLINE_FILTER = "\n";
    public static final String NON_ALPHABETS_FILTER = "[^a-zA-Z\\s]";
    public static final String SPACE = " ";
    public static final String SPACE_FILTER = "\\s+";


}
