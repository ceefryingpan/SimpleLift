import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * GraphController is the Controller part of the MVC framework, 
 * in charge of communicating between FinishedExerciseModel, 
 * GraphView, and StatsScreen.
 *
 * @author Charles Pan
 * @version March 2018
 */
public class GraphController implements ActionListener
{
    GraphView view;
    StatsScreen screen;
    FinishedExerciseModel model;
    String username;

    public GraphController (GraphView v, StatsScreen s, String username)
    {
        this.view = v;
        this.screen = s;
        this.username = username;

        screen.addActionListeners( this );
    }

    public void actionPerformed( ActionEvent e )
    {
        if (e.getSource() == screen.exit)
        {
            view.dispose();
            screen.dispose();
        }
        else if (e.getSource() == screen.back)
        {
            view.dispose();
            screen.dispose();
            SecondScreen screen = new SecondScreen( username );
        }
        else if (e.getSource() == screen.action)
        {
            String option = (String) screen.yaxis.getSelectedItem();
            if (!(option == null))
            {
                Exercise chosen = (Exercise) screen.action.getSelectedItem();
                model = new FinishedExerciseModel();
                model.loadExercise( username, chosen );
                if (model.data.size() < 5)
                {
                    screen.header.setText("Not enough data!");
                }
                else
                {
                    view.dispose();
                    view = new GraphView( model, option );
                }
            }
        }
        else if (e.getSource() == screen.yaxis)
        {
            Exercise chosen = (Exercise) screen.action.getSelectedItem();
            if (!(chosen == null))
            {
                model = new FinishedExerciseModel();
                model.loadExercise( username, chosen );
                String option = (String) screen.yaxis.getSelectedItem();
                if (model.data.size() < 5)
                {
                    System.out.println("Not enough data!");
                }
                else
                {
                    view.dispose();
                    view = new GraphView( model, option );
                }
            }
        }
    }
}
