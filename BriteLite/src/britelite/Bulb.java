package britelite;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Breuer
 */
public class Bulb extends JLabel
{

    private int j;
    ArrayList<Color> colors = new ArrayList<Color>();

    
    {
        colors.add(Color.BLACK);
        colors.add(Color.GREEN);
        colors.add(Color.MAGENTA);
        colors.add(Color.ORANGE);
        colors.add(Color.WHITE);
    }

    public Bulb()
    {

        setOpaque(true);
        setBackground(colors.get(4));
        setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                if (colors.isEmpty())
                {
                    return;
                }

                if (j == colors.size())
                {
                    j = 0;
                }
                setBackground(colors.get(j++));
            }
        });
    }

}
