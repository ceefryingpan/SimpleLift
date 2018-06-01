import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
/**
 * GeneratorFirstScreen displays a view for the user to select which
 * muscles they want to target in their generated workout.
 * 
 * @author Charles Pan
 * @version March 2018
 */
public class GeneratorFirstScreen extends JFrame implements ActionListener
{
    JLabel header = new JLabel ("What areas of your body would you like to target today?");

    JCheckBox calves= new JCheckBox ("Calves");
    JCheckBox quads= new JCheckBox ("Quadriceps");
    JCheckBox ham= new JCheckBox ("Hamstrings");
    JCheckBox glut= new JCheckBox ("Gluteus");
    JCheckBox hip= new JCheckBox ("Hip");
    JCheckBox lowback= new JCheckBox ("Lower Back");
    JCheckBox abs= new JCheckBox ("Abdominals");
    JCheckBox forearms= new JCheckBox ("Forearms");
    JCheckBox pecs= new JCheckBox ("Pectorals");
    JCheckBox delts= new JCheckBox ("Deltoids");
    JCheckBox lats= new JCheckBox ("Lats");
    JCheckBox triceps= new JCheckBox ("Triceps");
    JCheckBox biceps= new JCheckBox ("Biceps");
    JCheckBox traps= new JCheckBox ("Trapezius");
    
    String username;

    JButton continuebutton = new JButton ("Continue");
    JButton back = new JButton ("Back");
    JButton exit = new JButton ("Exit");

    public GeneratorFirstScreen ( String username )
    {
        this.username = username; 
        Container c = getContentPane();
        c.setLayout ( new GridLayout(4, 4) );
        JCheckBox[] set = {calves, quads, ham, glut, hip, lowback, abs, forearms, pecs, delts, lats, triceps, biceps, traps};
        for (JCheckBox elem : set)
        {
            c.add(elem);
        }
        c.add(continuebutton);
        c.add(back);
        c.add(exit);

        continuebutton.addActionListener( this );
        back.addActionListener( this );
        exit.addActionListener( this );

        setTitle ("Workout Dialog");
        setSize( 800, 800 );
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
        setVisible( true );
    }

    public void actionPerformed( ActionEvent e )
    {
        Object source = e.getSource();
        if ( source == continuebutton)
        {
            JCheckBox[] set = {calves, quads, ham, glut, hip, lowback, abs, forearms, pecs, delts, lats, triceps, biceps, traps};
            ArrayList<String> muscles = new ArrayList<String>();
            for (JCheckBox elem : set)
            {
                if (elem.isSelected())
                {
                    muscles.add(elem.getText());
                }
            }
            GeneratorSecondScreen view = new GeneratorSecondScreen (muscles, username);
            this.dispose();
        }
        else if (source == back)
        {
            SecondScreen screen = new SecondScreen ( username );
            this.dispose();
        }
        else if (source == exit)
        {
            this.dispose();
        }
    }
}
