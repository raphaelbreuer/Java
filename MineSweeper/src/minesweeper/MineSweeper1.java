package minesweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;



/**
 *
 * @author Breuer
 */
public final class MineSweeper1 extends JFrame
{

    Color[] colors = new Color[]
    {
        Color.BLACK, Color.BLUE, Color.GREEN, Color.GRAY, Color.ORANGE
    };
    Scores score1 = new Scores();//name and score obj
    ImageIcon bomnIcon = new ImageIcon("https://rafibreuer.com/mineSweeper/bomn.PNG");
    ImageIcon flagIcon = new ImageIcon("https://rafibreuer.com/mineSweeper/flag.PNG");
    ImageIcon blankIcon = new ImageIcon("");
    Color bomnColor = Color.RED;
    Color flagColor = Color.YELLOW;
    Color defaultColor = Color.BLUE;
    int bomnAmount = 9;
    JButton stop = new JButton("End Game");
    JButton newGame = new JButton("New Game");
    int cellRows = 10;
    int cellColumns = 10;
    int cellSize = 25;
    JLabel label = new JLabel(String.format("%02d:%02d", 0, 0));
    public MButton[][] buttons = new MButton[cellRows][cellColumns];
    String[] level =
    {
        "Levels","Basic", "Very Easy", "Easy", "Hard", "Very Hard"
    };
    JComboBox chooser = new JComboBox(level);
    String property = "user.home";
    String userPath = System.getProperty(property);
    File file = new File(userPath + File.separatorChar + "Mn/mscr.txt");

    public MineSweeper1()//io makes u throw exceptions all over the place
    {
        if (!file.exists())
        {
            try
            {
                File folder = new File(userPath + File.separatorChar + "Mn");
                //make the folders
                folder.mkdirs();
                file.createNewFile();
            } catch (IOException ex)
            {
                Logger.getLogger(MineSweeper1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        {
            score1.level = 3;
            JPanel panel = new JPanel(new GridLayout(cellRows, cellColumns));
            JPanel panel1 = new JPanel();
            
            JButton hiScore = new JButton("hi scores");
            
            JLabel label1 = new JLabel("Click left to open right to flag");
            for (int i = 0; i < buttons.length; i++)
            {
                for (int j = 0; j < buttons[i].length; j++)
                {
                    buttons[i][j] = new MButton(this);
                    panel.add(buttons[i][j]);
                    buttons[i][j].r = i;
                    buttons[i][j].c = j;
                }
            }
            readFile();
            placeBomns();
            placeNumbers();
            panel1.add(chooser);
            panel1.add(label1);
            panel1.add(stop).setVisible(false);;
            panel1.add(newGame);
            panel1.add(hiScore);            
            panel1.add(label);
            add(panel1, BorderLayout.PAGE_START);
            add(panel);
            setSize(cellSize * cellSize, cellSize * cellSize);
            setResizable(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            setLocationRelativeTo(null);
            setTitle("Mine Sweeper");
             stop.addActionListener((ActionEvent ae) ->
            {
                MButton.gameOver=true;
                newGame.setVisible(true);
                chooser.setVisible(true);
                stop.setVisible(false);
            });
            newGame.addActionListener((ActionEvent ae) ->
            {
                if(!MButton.gameOver)
                    return;
                reset();
            });
            hiScore.addActionListener((ActionEvent ae) ->
            {
                JOptionPane.showMessageDialog(null, score1.print(), "Mine Sweeper top win scores", JOptionPane.INFORMATION_MESSAGE);
            });
           
            chooser.addActionListener((ActionEvent e) -> //change bomnamount per level
            {
                if(!MButton.gameOver)
                    return;
                {
                    String level1 = (String) chooser.getSelectedItem();
                    switch (level1)
                    {
                        case "Levels":
                            break;
                        case "Very Easy":
                            score1.scores.clear();
                            score1.level = 1;
                            bomnAmount = 5;
                            defaultColor = Color.GREEN;
                            readFile();
                            reset();
                            break;
                        case "Easy":
                            score1.scores.clear();
                            score1.level = 2;
                            bomnAmount = 7;
                            defaultColor = Color.magenta;
                            readFile();
                            reset();
                            break;
                        case "Basic":
                            score1.scores.clear();
                            score1.level = 3;
                            bomnAmount = 9;
                            defaultColor = Color.BLUE;
                            readFile();
                            reset();
                            break;
                        case "Hard":
                            score1.scores.clear();
                            score1.level = 4;
                            bomnAmount = 13;
                            defaultColor = Color.GRAY;
                            readFile();
                            reset();
                            break;
                        default:
                            score1.scores.clear();
                            score1.level = 5;
                            bomnAmount = 18;
                            defaultColor = Color.DARK_GRAY;
                            readFile();
                            reset();
                            break;
                    }
                }
            });

        }
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

                    if (scan.hasNextLine());
                    String nextLine = scan.nextLine();
                    if (scan.hasNextInt());
                    int nextInt = scan.nextInt();
                    if (scan.hasNextInt());
                    int nextInt2 = scan.nextInt();
                    score1.add(new Scores(nextLine, nextInt, nextInt2), score1.level);//populate when open game
                } catch (NoSuchElementException ex)
                {

                }
            }
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(MineSweeper1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void sendToFile(Scores s) throws IOException
    {

        FileWriter fw = new FileWriter(file, true);
        PrintWriter pw = new PrintWriter(fw);

        {
            score1.add(new Scores(s.name, s.score, s.level), score1.level);//to put in top 5 before closing
            pw.println(s.name);
            pw.println(s.score);
            pw.println(s.level);

        }

        fw.flush();
    }

   public void reset()
    {
        for (MButton[] button : buttons)
        {
            for (MButton button1 : button)
            {
                button1.setBackground(defaultColor);
                button1.setText("");
                button1.setIcon(blankIcon);
                button1.isOpen = false;
                button1.isBomn = false;
                button1.nextToBomn = 0;

            }
        }
        MButton.cellOpenCounter = 0;
        label.setText(String.format("%02d:%02d", 0, 0));
         
        MButton.timerCounter=0;
        MButton.Started =false;
        placeBomns();
        placeNumbers();
    }

    private void openAll() throws IOException
    {
        for (MButton[] button : buttons)
        {
            for (MButton button1 : button)
            {
                button1.open();
            }
        }
    }

    public void lose() throws IOException
    {
        newGame.setVisible(true);
        chooser.setVisible(true);
        stop.setVisible(false);
        MButton.gameOver=true;
        MButton.cellOpenCounter = -(bomnAmount + 1);//not to hit win first

        {
            openAll();
            JOptionPane.showMessageDialog(this, " a loss in "+(MButton.formatTime), "Mine Sweeper", JOptionPane.INFORMATION_MESSAGE);
            
        }

    }

    private void placeBomns()
    {
        int count = 0;
        while (count < bomnAmount)
        {
            int row = (int) (Math.random() * cellRows);
            int col = (int) (Math.random() * cellColumns);

            MButton bomn = buttons[row][col];
            if (!bomn.isBomn)
            {
                bomn.isBomn = true;
                ++count;
            }

        }
    }

    public void openBlanks(int row, int col) throws IOException
    {
        buttons[row][col].open();

        if (buttons[row][col].nextToBomn > 0)
        {
            return;
        }
        {

            for (int i = row - 1; i <= row + 1; i++)

            {

                for (int j = col - 1; j <= col + 1; j++)
                {
                    if (i < 0 || i >= cellRows || j < 0 || j >= cellColumns || buttons[i][j].isOpen)

                    {
                        continue;
                    }
                    buttons[i][j].open();
                    if (buttons[i][j].nextToBomn < 1)
                    {
                        openBlanks(i, j);
                    }

                }

            }

        }

    }

    private void placeNumbers()
    {

        for (int row = 0; row < buttons.length; row++)
        {
            for (int column = 0; column < buttons[row].length; column++)
            {
                if (!buttons[row][column].isBomn)
                {
                    buttons[row][column].nextToBomn = 0;
                    {
                        for (int i = row - 1; i <= row + 1; i++)
                        {
                            if (i < 0 || i >= cellRows)

                            {
                                continue;
                            }

                            for (int j = column - 1; j <= column + 1; j++)
                            {
                                if (j < 0 || j >= cellColumns)

                                {
                                    continue;
                                }

                                if (buttons[i][j].isBomn)
                                {
                                    buttons[row][column].nextToBomn++;
                                }

                            }

                        }
                    }
                }
                if (buttons[row][column].nextToBomn < colors.length)
                {
                    buttons[row][column].setForeground(colors[buttons[row][column].nextToBomn]);
                }
            }

        }  

    }
    public void winName() throws IOException
    {
        stop.setVisible(false);
        newGame.setVisible(true);
        chooser.setVisible(true);
        if(score1.scores.size()<3)
        {
             score1.name = JOptionPane.showInputDialog(null, "  a Win in "+(MButton.formatTime) + ", enter your name", "Hi score winner!", 3);
             if(score1.name==null)
            {
                score1.name="";
            }
             sendToFile(score1);
        }else if(score1.score<score1.scores.get(score1.scores.size()-1).score)
        {
           score1.name = JOptionPane.showInputDialog(null, "  a Win in " +(MButton.formatTime) + ", enter your name", "Hi score winner!", 3); 
        
            if(score1.name==null)
            {
                score1.name="";
            }
            sendToFile(score1);
            
        }else
        {
             JOptionPane.showMessageDialog(this, "  a Win in " +(MButton.formatTime) + "", "Mine Sweeper", JOptionPane.INFORMATION_MESSAGE);
        }
    }
 
    public static void main(String[] args) throws IOException
    {
        UIManager.LookAndFeelInfo[] plafs = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo info : plafs)
        {
            String className = info.getClassName();
            System.out.println(className);
        }

        try
        {
            // Set System L&F
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (UnsupportedLookAndFeelException e)
        {
            // handle exception
        } catch (ClassNotFoundException e)
        {
            // handle exception
        } catch (InstantiationException e)
        {
            // handle exception
        } catch (IllegalAccessException e)
        {
            // handle exception
        }
         java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                 new MineSweeper1().setVisible(true);
            }
        });
       

    }
}
