import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * The second screen displays a view for the user to select an option
 * of what they want to do.
 * 
 * @author Charles Pan
 * @version March 2018
 */
public class SecondScreen extends JFrame implements ActionListener
{
    JLabel header = new JLabel ("What do you want to do today?");

    JComboBox action = new JComboBox ( new String[] {"Generate Workout", "Record Workout", "View Stats"});

    JButton continuebutton = new JButton ("Continue");
    JButton back = new JButton ("Back");
    JButton exit = new JButton ("Exit");
    
    String username;

    public SecondScreen ( String username )
    {
        this.username = username;
        Container c = getContentPane();
        c.setLayout ( new GridLayout(4, 1) );
        c.add(header);
        c.add(action);
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
        if ( source == continuebutton )
        {
            if (action.getSelectedItem().equals("Generate Workout"))
            {
                GeneratorFirstScreen view = new GeneratorFirstScreen(username);
                this.dispose();
            }
            else if (action.getSelectedItem().equals("Record Workout"))
            {
                RecordWorkout view = new RecordWorkout();
                FinishedExerciseModel model = new FinishedExerciseModel();
                RecordController controller = new RecordController(model, view, username);
                this.dispose();
            }
            else if (action.getSelectedItem().equals("View Stats"))
            {
                StatsScreen screen = new StatsScreen();
                GraphView view = new GraphView();
                GraphController controller = new GraphController(view, screen, username);
                this.dispose();
            }
        }
        else if (source == back)
        {
            FirstScreen screen = new FirstScreen();
            this.dispose();
        }
        else if (source == exit)
        {
            this.dispose();
        }
    }
}
