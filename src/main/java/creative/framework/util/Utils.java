package creative.framework.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {

	
    /**
     * Return a BufferedReader
     *
     * @param fileDescription
     * @return
     */
    public static BufferedReader getReader(String filePath) {
        try {
            return new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
    
}
