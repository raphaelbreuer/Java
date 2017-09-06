
package billcalculator;

/**
 *
 * @author Breuer
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SubMeterGui
{
      boolean wasEntered;
     SubMeter eb=new SubMeter();
    public SubMeterGui()
    {
        JFrame frame = new JFrame();
        //new gui panels
        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();

        //panel layout
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        //new buttons and sizing
        JButton clear = new JButton("clear");
        JButton clear1 = new JButton("clear");
        JButton TotalKw = new JButton("totalKw");
        JButton LastRead = new JButton("lastRead");
        JButton ThisRead = new JButton("thisRead");
        JButton BillAmount = new JButton("billAmount");
        JButton Total = new JButton("total");
        JTextArea tarea = new JTextArea(10, 20);
        JTextField tf = new JTextField(10);
        //placing in panels
        panel1.add(new JLabel("amount"));
        panel1.add(tf);
        panel1.add(clear1);
        panel2.add(tarea);
        panel2.add(clear);
        panel3.add(TotalKw);
        panel3.add(LastRead);
        panel3.add(ThisRead);
        panel3.add(BillAmount);
        panel4.add(Total);
        //placing panels in panel
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        //setting frame and view
        frame.setTitle("Calculator");
        frame.add(new JLabel("ElectricBill",
                SwingConstants.CENTER), BorderLayout.PAGE_START);
        frame.add(panel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        TotalKw.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    double parsedDouble = Double.parseDouble(tf.getText());
                    tf.setText("");
                    eb.setTotalKw(parsedDouble);
                    tarea.append(eb.totalKw + " was entered for total killowats\n");
                } catch (Exception w)
                {

                    tf.setText("not valid");
                    tf.setForeground(Color.red);

                }

            }
        });
        LastRead.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                    if(!tf.getText().isEmpty())
                    {
                     try   
                    {
                   double parsedDouble = Double.parseDouble(tf.getText());
                    tf.setText("");
                    eb.setLastRead(parsedDouble);
                   
                } catch (Exception w)
                {

                    tf.setText("not valid");
                    tf.setForeground(Color.red);

                }
                    }else
                    {
                        eb.readFile();
                    }
                
                      tarea.append(eb.lastRead + " was entered for last reading\n");
            }
        });
        ThisRead.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                       if(wasEntered)
                {
                   JOptionPane.showMessageDialog(null, " please enter lastread manually", null, JOptionPane.INFORMATION_MESSAGE);
                }
              
                try
                {
                    double parsedDouble = Double.parseDouble(tf.getText());
                    tf.setText("");
                    eb.setThisRead(parsedDouble);
                    tarea.append(eb.thisRead + " was entered for this reading\n");
                    eb.sendToFile();
                    wasEntered=true;
                } catch (Exception w)
                {

                    tf.setText("not valid");
                    tf.setForeground(Color.red);

                }
               
            }
        });
        BillAmount.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    double parsedDouble = Double.parseDouble(tf.getText());
                    tf.setText("");
                    eb.setBillAmount(parsedDouble);
                    tarea.append(eb.billAmount + " was entered for total bill Amount\n");
                } catch (Exception w)
                {
                    tf.setText("not valid");
                    tf.setForeground(Color.red);
                }

            }
        });
        Total.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                tarea.append(eb.totalOwed());
               
            }
        });
        clear.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                tarea.setText("");
                

            }
        });
          clear1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                tf.setText("");
                tf.setForeground(Color.BLACK);

            }
        });

    }

   public static void main(String[] args)
    {
      new SubMeterGui();
    }
}
    
    

