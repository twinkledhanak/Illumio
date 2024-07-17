package Utils;

/**
 * @author twinkledhanak on 04/07/24
 * @project Illumio
 */

import Common.Constants;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import java.io.IOException;

public class WordMatchOutputFormat<K, V> extends TextOutputFormat<K, V> {
    // ** We need a custom format class to add header to output file and then let hdfs handle rest of the file output **
    @Override
    public RecordWriter<K, V> getRecordWriter(TaskAttemptContext job) throws IOException {
        FSDataOutputStream fileOut = null;
        try {
            Path file = getDefaultWorkFile(job, Constants.OUTPUT_FILE_EXTENSION);
            FileSystem fs = file.getFileSystem(job.getConfiguration());
            fileOut = fs.create(file, false);
            fileOut.writeBytes(Constants.FILE_COLUMNS);
        }catch(Exception e){
            System.out.println("Exception while setting custom output format. Details: "+e);
        }

        return new LineRecordWriter<>(fileOut, "\t");
    }

}
