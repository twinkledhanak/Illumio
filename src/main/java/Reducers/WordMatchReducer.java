package Reducers;

import Common.Constants;
import org.apache.hadoop.io.Text;
import java.io.IOException;
import java.util.*;

/**
 * @author twinkledhanak on 02/07/24
 * @project Illumio
 */
public class WordMatchReducer extends org.apache.hadoop.mapreduce.Reducer<Text, Text, Text, Text>{
    // ** Added maxHeap for better performance to get wordlist in desc order of frequency **
    public static PriorityQueue<WordRecord> maxHeap = new PriorityQueue<>((a,b) -> b.frequency - a.frequency);

    public void reduce(Text key, Iterable<Text> frequency, Context context) throws IOException, InterruptedException {
        // ReducerKey: ai ,sentences: org.apache.hadoop.mapreduce.ReduceContext$ValueIterable@69016618 ,context: org.apache.hadoop.mapreduce.Reducer$Context@5cbdfb9a
        try {
            int sum = 0;
            for (Text val : frequency) {
                sum += Integer.parseInt(String.valueOf(val));
            }

            // We maintain a Desc order of frequency for every Pair: (word,frequency) obtained from Mapper task
            maxHeap.offer(new WordRecord(String.valueOf(key), sum)); // WordRecord: (ai,2)
        }catch(Exception e){
            System.out.println("Exception while completing Reducer() task. Details: "+e);
        }
    }

    protected void cleanup(Context context) throws IOException, InterruptedException {
        //System.out.println("Cleanup is called");
        try {
            while (!maxHeap.isEmpty()) {
                WordRecord wr = maxHeap.poll();
                //System.out.println("Wr: Word Key: "+wr.preDefinedWord+" and frequency: "+wr.frequency);
                context.write(new Text(wr.preDefinedWord), new Text(String.valueOf(wr.frequency)));
            }
        }catch(Exception e){
            System.out.println("Exception while completing Cleanup() task. Details: "+e);
        }

    }

    private class WordRecord{
        String preDefinedWord;
        int frequency;

        public WordRecord(String preDefinedWord, int frequency){
            this.preDefinedWord = preDefinedWord;
            this.frequency = frequency;
        }
    }
}
