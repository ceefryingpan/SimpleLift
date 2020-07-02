import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
/**
 * GraphView provides a view for the graph in GraphComponent.
 *
 * @author Charles Pan
 * @version March 2018
 */
public class GraphView extends JFrame
{
    GraphComponent com = null;
    
    public GraphView()
    {
        Container c = getContentPane();
        //c.setLayout ( new GridLayout(4, 1) );
        
        setTitle ("Workout Dialog");
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
        setSize(800, 500);
        setVisible( true );
    }
    
    public GraphView( FinishedExerciseModel mod, String option )
    {
        com = new GraphComponent( mod, option );
        
        Container c = getContentPane();
        //c.setLayout ( new GridLayout(4, 1) );
        c.add(com);
        
        setTitle ("Workout Dialog");
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
        setSize(800, 500);
        setVisible( true );
    }
    
    public void updateExercise(FinishedExerciseModel mod)
    {
        com.updateModel( mod );
        this.revalidate();
    }
    
    public void updateOption(String option)
    {
        com.updateOption( option );
        this.revalidate();
    }
}
