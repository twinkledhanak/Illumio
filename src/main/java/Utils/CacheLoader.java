package Utils;

import Common.Constants;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

/**
 * @author twinkledhanak on 02/07/24
 * @project Illumio
 */
public class CacheLoader {
    // ** Added Capacity and Load factor for better performance **
    private static Set<String> preDefinedWords = new HashSet<>(Constants.INITIAL_CAP, Constants.LOAD_FACTOR);

    public static Set<String> createCache(){
        System.out.println("Creating a cache from PreDefined words!");
        long startTime = System.currentTimeMillis();
        try (BufferedReader reader = new BufferedReader(new FileReader(Constants.PREDEF_WORDS_FILE_PATH))) {
            String word = "";
            while ((word = reader.readLine()) != null) {
                // Processing words and adding them to the cache for faster lookup
                word = word.replaceAll(Constants.NON_ALPHABETS_FILTER, Constants.SPACE).trim().toLowerCase();
                if(word.length() > 0)
                    preDefinedWords.add(word);
            }
        }catch(Exception e){
            System.out.println("Exception while creating cache from PreDefined words. Details: "+e);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Cache creation complete. Time elapsed (msec): "+(endTime-startTime));
        return preDefinedWords;
    }

    public static Set<String> getCache(){
        if(preDefinedWords.size() == 0)
            createCache();
        return preDefinedWords;
    }

}
