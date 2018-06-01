import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
/**
 * The StatsScreen provides a view for the user to select an exercise
 * they want to view and the y-axis option for the graph.
 * 
 * @author Charles Pan
 * @version March 2018
 */
public class StatsScreen extends JFrame
{
    JLabel header = new JLabel ("Select an exercise.");

    JComboBox action;
    JComboBox yaxis = new JComboBox( new String[]{"Max Weight", "Total Weight"} );

    JButton back = new JButton ("Back");
    JButton exit = new JButton ("Exit");

    public StatsScreen()
    {
        ArrayList<Exercise> set = Generator.generateExercises();
        set.add(0, null);
        Exercise[] exercises = set.toArray(new Exercise[0]);
        action = new JComboBox(exercises);
        
        Container c = getContentPane();
        c.setLayout ( new GridLayout(4, 1) );
        c.add(header);
        c.add(action);
        c.add(yaxis);
        c.add(back);
        c.add(exit);

        setTitle ("Workout Dialog");
        setSize( 500, 800 );
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
        setVisible( true );
    }
    
    public void addActionListeners( ActionListener list )
    {
        action.addActionListener( list );
        yaxis.addActionListener( list );
        back.addActionListener ( list );
        exit.addActionListener( list );
    }
}
