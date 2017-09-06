
package minesweeper;


import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

/**
 *
 * @author Breuer
 */
public class MineIO
{

    public MineIO() throws IOException
    {
         {
            // get the user's home directory
            String property = "user.home";
            String userPath = System.getProperty(property);
            File file = new File(userPath + File.separatorChar + "Documents/Mine/mine2.txt");
           // if (!file.exists())
            {
                File folder = new File(userPath + File.separatorChar + "Documents/Mine/");
                //make the folders
                boolean mkdirSuccess = folder.mkdirs();
                System.out.println("creating folders " + (mkdirSuccess ? "successfull" : "unsuccessfull"));
                boolean fileSuccess = file.createNewFile();
                System.out.println("creating file " + (fileSuccess ? "successfull" : "unsuccessfull"));
                //check file's modified date
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
                
                String format = sdf.format(file.lastModified());
                System.out.println("Last formated: " + format);
                
           FileWriter fw = new FileWriter(file);
           PrintWriter pw = new PrintWriter(fw);
           
            {
                pw.println("Hello");
                pw.println(67);
            }            

            fw.flush();
           
    }
            
         

    
}
    }
    public static void main(String[] args) throws IOException
    {
        new MineIO();
    }
}