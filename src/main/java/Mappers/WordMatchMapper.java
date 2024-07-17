package Mappers;

import Common.Constants;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;
import static Services.WordMatchService.localCache;


/**
 * @author twinkledhanak on 02/07/24
 * @project Illumio
 */
public class WordMatchMapper extends Mapper<Object, Text, Text, Text> {

    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        // Sample values in mapper(): Key: 0 ,value: Hey there! My name is Twinkle Dhanak. ,context: org.apache.hadoop.mapreduce.Mapper$Context@5512e2c5

        try {
            // Pre-processing on every line
            String[] records = value.toString().split(Constants.NEWLINE_FILTER);
            String line = records[0];
            line = line.replaceAll(Constants.NON_ALPHABETS_FILTER, Constants.SPACE).trim().toLowerCase();
            String[] inputWords = line.split(Constants.SPACE_FILTER);

            // Filtering out words based on PreDefined words in cache
            for (String inputWord : inputWords) {
                //System.out.println("word: " + inputWord);
                if(localCache != null && localCache.size() > 0){
                    if (localCache.contains(inputWord)) {
                        context.write(new Text(inputWord), new Text("1")); // (word,count) where initial count of every predefined word is sent as 1
                    }
                }
            }
        }
        catch(Exception e){
            System.out.println("Exception while executing the Mapper() task. Details: "+e);
        }

    }
}
