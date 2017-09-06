package tictactoe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Breuer
 */
public class TicTacToe extends JFrame
{

    protected TTTLabel[][] labels = new TTTLabel[3][3];
    private int TLabel = 29;
    protected JLabel player = new JLabel("Player");//for which player is next

    public TTTLabel[][] getLabels()
    {
        return labels;
    }

    public TicTacToe()
    {
        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        JButton exit = new JButton("End Game");
        JButton computer = new JButton("Computer Turn");
        JButton newGame = new JButton("New Game");
        panel.setLayout(new GridLayout(3, 3));
        player.setText("Lets Play!Click on box to start");
        //making labels 
        for (TTTLabel[] label : labels)
        {
            for (int j = 0; j < label.length; j++)
            {

                label[j] = new TTTLabel(this);
                panel.add(label[j]);
            }
        }
        panel1.add(player);
        panel1.add(newGame);
        panel1.add(computer);
        panel1.add(exit);

        newGame.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                for (TTTLabel[] label : labels)
                {
                    for (TTTLabel label1 : label)
                    {
                        label1.setText("");
                        label1.setBackground(Color.WHITE);
                    }
                }
                player.setText("Lets Play!Click on box to start");
                setCounter(0);
            }
        });
        exit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                System.exit(0);
            }
        });

        computer.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                //not to recurse when full
                for (TTTLabel[] label : labels)
                {
                    for (TTTLabel label1 : label)
                    {
                        if (label1.getText().isEmpty())
                        {
                            computerPlay();
                            return;
                        }
                    }
                }
            }
        });
        {
            add(panel1, BorderLayout.PAGE_START);
            add(panel);
            panel1.setBackground(Color.ORANGE);
            setSize(TLabel * TLabel, TLabel * TLabel);
            setResizable(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            setLocationRelativeTo(null);
            setTitle("TicTacToe");
        }
    }

    public static void main(String[] args)
    {
        new TicTacToe();
    }

    public void winnerCheck()
    {
        //loops and columns for straight
        for (int i = 0; i < getLabels().length; i++)

        {

            int rO = 0;
            int rX = 0;
            int cX = 0;
            int cO = 0;
            for (int j = 0; j < getLabels()[i].length; j++)

            {
                if (getLabels()[i][j].getText().equals("O"))
                {
                    rO++;
                } else if (getLabels()[i][j].getText().equals("X"))
                {
                    rX++;
                }
                if (getLabels()[j][i].getText().equals("O"))
                {
                    cO++;
                } else if (getLabels()[j][i].getText().equals("X"))
                {
                    cX++;
                }
                if (rX == 3 || cX == 3)
                {
                    JOptionPane.showMessageDialog(null, "Player X Wins!");
                    player.setText("Lets Play again!");
                          return;
                } else if (rO == 3 || cO == 3)
                {
                    JOptionPane.showMessageDialog(null, "Player O Wins!");
                    player.setText("Lets Play again!");
                          return;
                }

            }
            //loop for slant
            int l = 2;
            int sO = 0;
            int sO1 = 0;
            int sX = 0;
            int sX1 = 0;
            for (int k = 0; k < 3; k++, l--)
            {
                if (getLabels()[k][k].getText().equals("X"))
                {
                    sX++;
                } else if (getLabels()[k][k].getText().equals("O"))
                {
                    sO++;
                }
                if (getLabels()[l][k].getText().equals("X"))
                {
                    sX1++;
                } else if (getLabels()[l][k].getText().equals("O"))
                {
                    sO1++;
                }
            }
            if (sO == 3 || sO1 == 3)
            {
                JOptionPane.showMessageDialog(null, "Player O Wins!");
                player.setText("Lets Play again!");
                return;
            } else if (sX == 3 || sX1 == 3)
            {
                JOptionPane.showMessageDialog(null, "Player X Wins!");
                player.setText("Lets Play again!");
                return;
            }

        }
    }

    public void computerPlay()
    {

        TTTLabel t = labels[(int) (Math.random() * 3)][(int) (Math.random() * 3)];

        if ( labels[1][1].getText().equals("X") && labels[1][2].getText().equals("X")&&labels[1][0].getText().isEmpty())
        {
            labels[1][0].setText("O");

        } else if (labels[2][1].getText().equals("X") &&labels[2][2].getText().equals("X")&& labels[2][0].getText().isEmpty())
        {
            labels[2][0].setText("O");
        } else if ( labels[0][1].getText().equals("X") &&labels[1][1].getText().equals("X")&& labels[2][1].getText().isEmpty())
        {
            labels[2][1].setText("O");
        } else if (labels[0][0].getText().equals("X") &&labels[0][1].getText().equals("X")&& labels[0][2].getText().isEmpty())
        {
            labels[0][2].setText("O");
           } else if (labels[0][1].getText().equals("X") &&labels[0][2].getText().equals("X")&& labels[0][0].getText().isEmpty())
        {
            labels[0][0].setText("O");  

        } else if (t.getText().isEmpty())
        {
            t.setText("O");

        } else

        {
            computerPlay();

        }
        player.setText(" Player X");
        setCounter(0);
        winnerCheck();
    }

    public static void setCounter(int counter)
    {
        TTTLabel.counter = counter;
    }
}
