
package slideshow;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 *
 * @author Breuer
 */
public class SlideShow extends JFrame
{
   int timerCounter=-1;
   ImageIcon image1 = new ImageIcon("C:\\rimages\\c2.png");
   ArrayList<ImageIcon>images = new ArrayList();
   JLabel label = new JLabel();
    public SlideShow()
    {
        images.add(image1);
        images.add(new ImageIcon("C:\\rimages\\h.png"));
        images.add(new ImageIcon("C:\\rimages\\s.png"));
        images.add(new ImageIcon("C:\\rimages\\sky2.jpg"));
        images.add(new ImageIcon("C:\\rimages\\r1.jpg"));
        images.add(new ImageIcon("C:\\rimages\\w.png"));
       
        
        label.setBackground(Color.BLUE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label);
        label.setOpaque(true);
        setSize(600,600);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        timer();
    }
   
    public void timer()
    {
        new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                while (timerCounter <=images.size())
                {

                    try
                    {
                         
                        
                         timerCounter++;
                        invokeTimer();
                        Thread.sleep(1000);
                        

                    } catch (InterruptedException ex)
                    {

                    }

                }
            }

        }).start();
    }

    public void invokeTimer()
    {
        SwingUtilities.invokeLater(new Runnable()
        {

            @Override
            public void run()
            {
                {
                    if (timerCounter ==images.size())
                    {
                        timerCounter = 0;
                    }
                    label.setIcon(images.get(timerCounter));
                }
            }

        });
    }
    public static void main(String[] args)
    {
       new SlideShow(); 
    }
    
}
