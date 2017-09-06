package billcalculator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;



/**this program calculates how much money a sub meter owes to electric bill 
 *
 * @author Breuer
 */
public class SubMeter
{
   
    double totalKw;
    double lastRead;
    double thisRead;
    double billAmount;

    public void setTotalKw(double totalKw)
    {
        this.totalKw = totalKw;
    }

    public void setLastRead(double lastRead)
    {
        this.lastRead = lastRead;
    }

    public void setThisRead(double thisRead)
    {
        this.thisRead = thisRead;
    }

    public void setBillAmount(double billAmount)
    {
        this.billAmount = billAmount;
    }

    public String totalOwed()
    {
        double totalKwUsed = totalKw / (thisRead - lastRead);
        double owes = billAmount / totalKwUsed;
        return "owes" + owes;
    }
     
          
           String property = "user.home";
    String userPath = System.getProperty(property);
    File file = new File(userPath + File.separatorChar + "Documents/EBill/Ebill.txt");
    public SubMeter()
      {
    {
      if (!file.exists())
        {
            try
            {
                File folder = new File(userPath + File.separatorChar + "Documents/EBill");
                //make the folders
                folder.mkdirs(); 
                file.createNewFile();
            } catch (IOException ex)
            {
               
            }
        }
      }
}
      public void sendToFile() throws IOException
    {

        FileWriter fw = new FileWriter(file, true);
        PrintWriter pw = new PrintWriter(fw);

        {
            pw.println(thisRead);//saves for next month 
        }

        fw.flush();
    }
       public void readFile()
    {
        try
        {
            Scanner scan = new Scanner(new FileInputStream(file));
            while (scan.hasNext())
            {
                try
                {
                    if (scan.hasNextDouble());
                    double nextDouble = scan.nextDouble();
                    if(nextDouble!=thisRead)
                    lastRead=nextDouble;//gives last thisread wich is now lastread
                } catch (NoSuchElementException ex)
                {

                }
            }
        } catch (FileNotFoundException ex)
        {
            
        }

    }
}