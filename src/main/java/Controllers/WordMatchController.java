package Controllers;

import Common.Constants;
import Services.WordMatchService;
import java.io.IOException;

/**
 * @author twinkledhanak on 02/07/24
 * @project Illumio
 */

public class WordMatchController {
    // ** Adding only higher level calls to hide all job config and details at this level **
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        try {
            WordMatchService service = new WordMatchService();
            boolean responseCode = service.setJobConfig();
            System.exit( responseCode? Constants.JOB_SUCCESS : Constants.JOB_FAILURE);
        }
        catch(Exception e){
            System.out.println("Exception while running the Word Matcher Job!. Details: "+e);
        }

    }
}
