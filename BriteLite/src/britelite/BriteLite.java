package britelite;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Breuer
 */
public class BriteLite extends JFrame
{
    int row =20;
    int column=20;
    int bulb=30;
    public BriteLite()
    {
        
       setLayout(new GridLayout(row,column));

        for (int i = 0; i < row*column; i++)
        {
            add( new Bulb());
        }
        setSize(row*bulb, column*bulb);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("Brite Game");
    }

    public static void main(String[] args)
    {
        new BriteLite();
    }

}
