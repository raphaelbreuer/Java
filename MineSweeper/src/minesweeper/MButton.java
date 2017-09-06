package minesweeper;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 *
 * @author Breuer
 */
public class MButton extends JButton
{

    MineSweeper1 ms;
    int r;//number of array row(assigned in for loop of array)
    int c;//number of array column to pass in open blank method
    static int cellOpenCounter;//checks for win
    boolean isOpen;//only once open and more imp not to recurse too much
    int nextToBomn;//how many bomns nearby
    static boolean gameOver = true;//to click boxes left after win w/o generating loss
    static int timerCounter;
    static String formatTime;
    static boolean Started;//only start on 1st click
    boolean isBomn;//track bomns
    boolean flagged;//flag unflag

    public MButton(MineSweeper1 ms)
    {
        this.ms = ms;
        setOpaque(true);
        setBackground(Color.BLUE);
        setBorder(BorderFactory.createLineBorder(Color.CYAN));
        setFont(getFont().deriveFont(25F));
        setHorizontalAlignment(SwingConstants.CENTER);
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                ms.stop.setVisible(true);
                ms.newGame.setVisible(false);
                ms.chooser.setVisible(false);
              
                if (!Started)
                {
                    gameOver = false;
                    Started = true;
                    timer();
                }
                  if(gameOver){  ms.newGame.setVisible(true);
                ms.chooser.setVisible(true); ms.stop.setVisible(false);}
                if (isOpen || cellOpenCounter < 1 && (isBomn) && e.getButton() != MouseEvent.BUTTON3)//not to lose on first try
                {
                    return;
                }
                if (e.getButton() == MouseEvent.BUTTON1)
                {
                    if (isBomn)
                    {
                        if (gameOver)//just open bomns per click not blow up like lose
                        {
                            try
                            {
                                open();
                            } catch (IOException ex)
                            {
                                Logger.getLogger(MButton.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else if (!gameOver)
                        {
                            try
                            {
                                ms.lose();
                            } catch (IOException ex)
                            {

                            }
                        }
                    } else
                    {
                        try
                        {
                            ms.openBlanks(r, c);
                        } catch (IOException ex)
                        {
                            Logger.getLogger(MButton.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else if (e.getButton() == MouseEvent.BUTTON3)
                {
                    if (flagged)
                    {
                        setIcon(ms.blankIcon);
                        setBackground(ms.defaultColor);
                    } else
                    {
                        setIcon(ms.flagIcon);
                        setBackground(ms.flagColor);
                    }
                }
                flagged = !flagged;
            }
        });

    }

    public void open() throws IOException
    {

        if (isOpen)
        {
            return;
        }
        isOpen = true;
        cellOpenCounter++;
        setIcon(ms.blankIcon);
        setText("" + nextToBomn);
        if (nextToBomn == 0)
        {
            setText("");

        }
        setBackground(Color.WHITE);
        if (isBomn)
        {
            setIcon(ms.bomnIcon);
            setBackground(ms.bomnColor);
        }
        if (gameOver)
        {
            return;
        }
        if (cellOpenCounter == ms.cellRows * ms.cellColumns - ms.bomnAmount)
        {
            gameOver = true;
            ms.score1.score = timerCounter;//how many seconds to win
            {
                ms.winName();
            }

        }
    }

    public void timer()
    {
        new Thread(()
                -> 
                {
                    while (!gameOver)
                    {
                        invokeTimer();
                        try
                        {

                            Thread.sleep(1000);
                            timerCounter++;

                        } catch (InterruptedException ex)
                        {

                        }

                    }
        }).start();
    }

    public void invokeTimer()
    {
        SwingUtilities.invokeLater(()
                -> 
                {
                    {
                        int minutes = (timerCounter % 3600) / 60;
                        int seconds = timerCounter % 60;
                        formatTime = String.format("%02d:%02d", minutes, seconds);
                        ms.label.setText(formatTime);
                    }
        });
    }

}
