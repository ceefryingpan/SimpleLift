import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
/**
 * RecordWorkout provides a view for users to record their workouts.
 * 
 * @author Charles Pan
 * @version March 2018
 */
public class RecordWorkout extends JDialog
{
    JLabel recordheader = new JLabel ("Record your workouts here:");
    JLabel repsheader = new JLabel ("Reps: (use , to separate)");
    JLabel setsheader = new JLabel ("Sets:");
    JLabel weightheader = new JLabel ("Weights: (use , to separate)");
    
    JComboBox ex;
    JTextField reps = new JTextField("");
    JTextField weight = new JTextField("");
    JSlider setslider = new JSlider(1, 10);
    
    JButton save = new JButton ("Save");
    JButton back = new JButton ("Back");
    JButton exit = new JButton ("Exit");
    
    public RecordWorkout()
    {
        ArrayList<Exercise> set = Generator.generateExercises();
        set.add(0, null);
        Exercise[] exercises = set.toArray(new Exercise[0]);
        ex = new JComboBox(exercises);
        
        setslider.setPaintLabels(true);
        setslider.setPaintTicks(true);
        setslider.setSnapToTicks(true);
        setslider.setMajorTickSpacing(1);
        
        JPanel title1Pan = new JPanel (new BorderLayout());
        title1Pan.add(recordheader, BorderLayout.NORTH);
        title1Pan.add(ex, BorderLayout.CENTER);
        
        JPanel title2Pan = new JPanel (new BorderLayout());
        title2Pan.add(repsheader, BorderLayout.NORTH);
        title2Pan.add(reps, BorderLayout.CENTER);
        
        JPanel title3Pan = new JPanel (new BorderLayout());
        title3Pan.add(setsheader, BorderLayout.NORTH);
        title3Pan.add(setslider, BorderLayout.CENTER);
        
        JPanel title4Pan = new JPanel (new BorderLayout());
        title4Pan.add(weightheader, BorderLayout.NORTH);
        title4Pan.add(weight, BorderLayout.CENTER);
        
        Container c = getContentPane();
        c.setLayout ( new GridLayout(4, 1) );
        c.add(title1Pan);
        c.add(title2Pan);
        c.add(title3Pan);
        c.add(title4Pan);
        c.add(save);
        c.add(back);
        c.add(exit);
        
        setTitle ("Workout Dialog");
        setSize( 500, 500 );
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
        setVisible( true );
    }
    
    public void addActionListeners( ActionListener list )
    {
        save.addActionListener( list );
        back.addActionListener( list );
        exit.addActionListener( list );
    }
}