package Services;

import Common.Constants;
import Controllers.WordMatchController;
import Mappers.WordMatchMapper;
import Reducers.WordMatchReducer;
import Utils.CacheLoader;
import Utils.WordMatchOutputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.util.Set;

/**
 * @author twinkledhanak on 04/07/24
 * @project Illumio
 */
public class WordMatchService {
    public static Set<String> localCache = CacheLoader.getCache();

    public boolean setJobConfig(){
        long startTime = System.currentTimeMillis();
        System.out.println("Starting Job Execution.");
        boolean isJobComplete = false;
        try{
            // Set the config for job
            Configuration conf = new Configuration();

            // Setting mapper,reducer,combiner
            Job job = Job.getInstance(conf, Constants.JOB_NAME);
            job.setJarByClass(WordMatchController.class);
            job.setMapperClass(WordMatchMapper.class);
            job.setCombinerClass(WordMatchReducer.class);
            job.setReducerClass(WordMatchReducer.class);

            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Text.class);

            try{
                FileInputFormat.addInputPath(job, new Path(Constants.INPUT_FILE_PATH));
                FileSystem hdfs = FileSystem.get(conf);
                if (hdfs.exists(new Path(Constants.OUTPUT_FILE_PATH)))
                    hdfs.delete(new Path(Constants.OUTPUT_FILE_PATH), true);
                FileOutputFormat.setOutputPath(job, new Path(Constants.OUTPUT_FILE_PATH));
            }
            catch(Exception e){
                System.out.println("Exception while setting Input/Output file: "+e);
            }

            job.setOutputFormatClass(WordMatchOutputFormat.class);
            isJobComplete = job.waitForCompletion(true);
            long endTime = System.currentTimeMillis();
            System.out.println("Job execution complete. Time elapsed (msec): "+(endTime-startTime));

        }catch(Exception e){
            System.out.println("Exception while running Job: "+Constants.JOB_NAME+" e: "+e);
        }

        return isJobComplete;
    }
}
