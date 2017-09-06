
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 *
 * @author Raphael Breuer
 */
public class DreidelGame extends JFrame
{

    int counter;

    public DreidelGame()
    {

        JLabel label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(Color.yellow);
        add(label);
        setSize(200, 200);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("Dreidel");

        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                if (counter == 1)
                {
                    label.setText("");
                    counter = 0;
                    return;
                }
                int theNumber = (int) (Math.random() * 4);

                switch (theNumber)
                {
                    case 1:
                        label.setForeground(Color.RED);
                        label.setText("Gimmel!");
                        break;
                    case 2:
                        label.setForeground(Color.BLUE);
                        label.setText("Hey!");
                        break;
                    case 3:
                        label.setForeground(Color.LIGHT_GRAY);
                        label.setText("shin!");
                        break;
                    default:
                        label.setForeground(Color.GREEN);
                        label.setText("nun!");

                }
                counter = 1;
            }
        });

    }

    public static void main(String[] args)
    {
        new DreidelGame();
//        System.out.println("Let's play dreidel!");
//        
//        while (true)
//        {
//            int theNumber = (int) (Math.random() * 4);
//            Scanner userInputThing = new Scanner(System.in);
//            System.out.println(" enter any number to spin dreidel press 0 to end game");
//
//            int spin = userInputThing.nextInt();
//
//            if (spin < 1)
//
//            {
//               break;
//                       
//            } else
//
//            {
//                switch (theNumber)
//                {
//                    case 1:
//                        System.out.println("Gimmel!");
//                        break;
//                    case 2:
//                        System.out.println("Hey!");
//                        break;
//                    case 3:
//                        System.out.println("shin!");
//                        break;
//                    default:
//                        System.out.println("nun!");
//                        
//                }
//            }
//        }
//        System.out.println("Happy Chanukah!");
//
    }

}
