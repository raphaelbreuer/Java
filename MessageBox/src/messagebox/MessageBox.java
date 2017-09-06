
package messagebox;
import java.awt.*;
import java.awt.event.*;    
import javax.swing.*;
/**
 *
 * @author Breuer
 */
public class MessageBox extends JFrame
{
    int placeholder=0;
    int timerCounter=-1;
    JLabel[] labels = new JLabel[9];
    JLabel label = new JLabel();
    JLabel labelsM=new JLabel();
    String[] comments =
    {
        "Click RED Button to add messages each message slot displays for 5 seconds in numbered order",null,null,null,null, null, null, null,null
    };
    String[] changeText =
    {
       "Message Input", "Message 1", "Message 2", "Message 3", "Message 4", "Message 5", "Message 6", "Message 7", "Message 8", "Message 9"
    };
    JComboBox chooser = new JComboBox(changeText);

    public MessageBox()
    {
        JPanel panel = new JPanel();
        label.setPreferredSize(new Dimension(20, 20));
        chooser.setBackground(Color.BLUE);
        label.setOpaque(true);
        label.setBackground(Color.RED);
        add(label, BorderLayout.BEFORE_LINE_BEGINS);
        //for (int i = 0; i < labels.length; i++)
        panel.add(labelsM);
//        {
//
//            //{
//                //labels[i] = new JLabel();
//               // labels[i].setForeground(Color.YELLOW);
//                //labels[i].setPreferredSize(new Dimension(160, 20));
//               //panel.add(labels[i]);
//           // }
//        }
//        labels[0].setForeground(Color.PINK);
//        labels[2].setForeground(Color.GREEN);
//        labels[4].setForeground(Color.PINK);
//        labels[6].setForeground(Color.GREEN);
//        labels[8].setForeground(Color.PINK);
        add(panel);
        setSize(1000,55);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("My Messages");
        timer();
        label.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                JFrame frame = new JFrame();
                frame.setSize(0,55);
                frame.add(chooser);
                frame.setResizable(false);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });
        chooser.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)//change bomnamount per level
            {
                String text = (String) chooser.getSelectedItem();
                switch (text)
                {
                    case "Message Input":
                        break;
                        
                    case "Message 1":
                        String s = JOptionPane.showInputDialog("");
                        if (s != null)
                        {
                            comments[0] = s;
                            labelsM.setText(s);
                        }
                        break;
                    case "Message 2":

                        s = JOptionPane.showInputDialog("");
                        if (s != null)
                        {
                            if(placeholder<1){placeholder=1;}
                            comments[1] = s;
                        }
                        break;
                    case "Message 3":
                        s = JOptionPane.showInputDialog("");
                        if (s != null)
                        {
                            if(placeholder<2){placeholder=2;}
                            comments[2] = s;
                        }
                        break;
                    case "Message 4":
                        s = JOptionPane.showInputDialog("");
                        if (s != null)
                        {
                            if(placeholder<3){placeholder=3;}
                            comments[3] = s;
                        }
                        break;
                    case "Message 5":
                        s = JOptionPane.showInputDialog("");
                        if (s != null)
                        {
                            if(placeholder<4){placeholder=4;}
                            comments[4] = s;
                        }
                        break;
                    case "Message 6":
                        s = JOptionPane.showInputDialog("");
                        if (s != null)
                        {
                            if(placeholder<5){placeholder=5;}
                            comments[5] = s;
                        }
                        break;
                    case "Message 7":
                        s = JOptionPane.showInputDialog("");
                        if (s != null)
                        {
                            if(placeholder<6){placeholder=6;}
                            comments[6] = s;
                        }
                        break;
                    case "Message 8":
                        s = JOptionPane.showInputDialog("");
                        if (s != null)
                        {
                            if(placeholder<7){placeholder=7;}
                            comments[7] = s;
                        }
                        break;
                    default:
                        s = JOptionPane.showInputDialog("");
                        if (s != null)
                        {
                            if(placeholder<8){placeholder=8;}
                            comments[8] = s;
                        }
                        break;
                }
            }
        });

    }

    public void timer()
    {
        new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                while (timerCounter <= comments.length)
                {

                    try
                    {
                         
                        
                        timerCounter++;
                        invokeTimer();
                        Thread.sleep(5000);

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
                //{
                    if (timerCounter >= comments.length || timerCounter>placeholder)
                    {
//                        labels[timerCounter - 1].setText(null);
                        timerCounter = 0;
                    }
                    //labels[timerCounter].setText(comments[timerCounter]);
                    if(comments[timerCounter]!=null){
                    labelsM.setText(comments[timerCounter]);
                    }
//                    if (timerCounter == 0)
//                    {
//                        labels[labels.length - 1].setText(null);
//                    } else
//                    {
//                        labels[timerCounter - 1].setText(null);
//                    }
               // }
            }

        });
    }

    public static void main(String[] args)
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
        new MessageBox();
    }

}
