import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
/**
 * GeneratorSecondScreen provides a view for the user to indicate
 * whether they have access to a gym and their level
 * 
 * @author Charles Pan
 * @version March 2018
 */
public class GeneratorSecondScreen extends JFrame implements ActionListener
{
    JLabel gymheader = new JLabel ("Do you have access to a gym?");
    JLabel levelheader = new JLabel ("How would you describe your level?");

    JComboBox gym = new JComboBox ( new String[] {"Yes", "No"});
    JComboBox level = new JComboBox ( new String[] {"Beginner", "Intermediate", "Difficult"});

    JButton continuebutton = new JButton ("Continue");
    JButton back = new JButton ("Back");
    JButton exit = new JButton ("Exit");
    
    String username;

    ArrayList<String> muscles = new ArrayList<String>();

    public GeneratorSecondScreen ( ArrayList<String> muscles, String username )
    {
        this.muscles = muscles;
        this.username = username;

        JPanel title1Pan = new JPanel (new BorderLayout());
        title1Pan.add(gymheader, BorderLayout.NORTH);
        title1Pan.add(gym, BorderLayout.CENTER);

        JPanel title2Pan = new JPanel (new BorderLayout());
        title2Pan.add(levelheader, BorderLayout.NORTH);
        title2Pan.add(level, BorderLayout.CENTER);

        Container c = getContentPane();
        c.setLayout ( new GridLayout(4, 1) );
        c.add(title1Pan);
        c.add(title2Pan);
        c.add(continuebutton);
        c.add(back);
        c.add(exit);

        continuebutton.addActionListener( this );
        back.addActionListener( this );
        exit.addActionListener( this );

        setTitle ("Workout Dialog");
        setSize( 500, 500 );
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
        setVisible( true );
    }

    public void actionPerformed( ActionEvent e )
    {
        Object source = e.getSource();
        String difficulty = level.getSelectedItem().toString();
        String boolgym = gym.getSelectedItem().toString();
        if ( source == continuebutton )
        {
            if (boolgym.equals("Yes"))
            {
                WorkoutDisplay view = new WorkoutDisplay (muscles, true, difficulty, username);
            }
            else if (boolgym.equals("No"))
            {
                WorkoutDisplay view = new WorkoutDisplay (muscles, false, difficulty, username);
            }
            this.dispose();
        }
        else if (source == back)
        {
            GeneratorFirstScreen screen = new GeneratorFirstScreen( username );
            this.dispose();
        }
        else if (source == exit)
        {
            this.dispose();
        }
    }
}
