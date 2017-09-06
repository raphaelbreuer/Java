package tictactoe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Breuer
 */
public class TTTLabel extends JLabel
{

    static int counter;//counter for turns and x o
    protected TicTacToe board;//to call other class

    public TTTLabel(TicTacToe board)
    {

        this.board = board;
        setOpaque(true);
        setBackground(Color.WHITE);
        setFont(getFont().deriveFont(210F));
        setHorizontalAlignment(SwingConstants.CENTER);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                if (!getText().isEmpty())
                {
                    return;
                }
                if (counter == 0)
                {
                    setText("X");
                    setBackground(Color.RED);
                    board.player.setText("Player O");
                    counter = 1;
                } else
                {
                    setText("O");
                    setBackground(Color.YELLOW);
                    board.player.setText("Player X");
                    counter = 0;
                }
                board.winnerCheck();

            }
        });
    }

}
