import java.awt.*;
import javax.swing.*;
import javax.swing.JLabel;
import java.awt.event.*;
import java.util.*;
/**
 * WorkoutDisplay displays the generated workout based on user's
 * preferences.
 *
 * @author Charles Pan
 * @version March 2018
 */
public class WorkoutDisplay extends JFrame implements ActionListener
{
    JLabel exercises = new JLabel();
    
    ArrayList<String> muscles;
    String username;
    
    JButton back = new JButton ("Back");
    JButton exit = new JButton ("Exit");

    public WorkoutDisplay ( ArrayList<String> muscles, boolean gym, String difficulty, String username )
    {
        this.username = username;
        this.muscles = muscles;
        ArrayList<Exercise> exerciseset = Generator.generateExercises();
        Workout set = Generator.generateWorkout(exerciseset, muscles, gym, difficulty);
        
        exercises.setText(set.toJLabel());
        
        Container c = getContentPane();
        c.setLayout ( new GridLayout(2, 1) );
        c.add(exercises);
        c.add(exit);

        exit.addActionListener( this );

        setTitle ("Workout Display");
        setSize( 500, 500 );
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
        setVisible( true );
    }

    public void actionPerformed( ActionEvent e )
    {
        Object source = e.getSource();
        if (source == exit)
        {
            this.dispose();
        }
        else if (source == back)
        {
            GeneratorSecondScreen screen = new GeneratorSecondScreen(muscles, username);
            this.dispose();
        }
    }
}
