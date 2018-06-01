import java.util.*;
import java.util.concurrent.TimeUnit;
import java.sql.Timestamp;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * RecordController is the controller component in the MVC framework,
 * manipulating the RecordWorkout view.
 *
 * @author Charles Pan
 * @version March 2018
 */
public class RecordController implements ActionListener, Observer
{
    FinishedExerciseModel model;
    RecordWorkout view;
    String username;
    String path = "data/User Data.txt";

    public RecordController( FinishedExerciseModel m, RecordWorkout v, String u)
    {
        this.model = m;
        this.view = v;
        this.username = u;

        model.addObserver( this );

        view.addActionListeners( this );
    }

    public void actionPerformed ( ActionEvent e )
    {
        Object o = e.getSource();
        if (o == view.save)
        {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Exercise chosen = (Exercise) view.ex.getSelectedItem();
            int sets = view.setslider.getValue();
            String replongstring = view.reps.getText().replace(" ","");
            String[] repstrings = replongstring.split(",");
            String weightlongstring = view.weight.getText().replace(" ","");
            String[] weightstrings = weightlongstring.split(",");
            if (repstrings.length == sets && weightstrings.length == sets)
            {
                FinishedExercise ex = getInput(chosen, view.setslider.getValue(), repstrings, weightstrings, timestamp.toString());
                SaveData.writeNewExercise(ex, path);
                model.loadData();
            }
            else
            {
                System.out.println("Error");
            }
        }
        else if (o == view.back)
        {
            SecondScreen screen = new SecondScreen ( username );
            view.dispose();
        }
        else if (o == view.exit)
        {
            view.dispose();
        }
    }

    public FinishedExercise getInput(Exercise exercise, int sets, String[] repstrings, String[] weightstrings, String timestamp)
    {
        int[] repset = new int[sets];
        int[] weightset = new int[sets];
        for (int n = 0; n < sets; n ++)
        {
            repset[n] = Integer.parseInt(repstrings[n]);
            weightset[n] = Integer.parseInt(weightstrings[n]);
        }
        FinishedExercise input = new FinishedExercise( username, exercise, sets, repset, weightset, timestamp );
        return input;
    }

    public void update (Observable o, Object obj)
    {
        FinishedExercise elem = model.data.get(model.data.size() - 1);
        view.recordheader.setText("Saved! " + elem.getTimeStamp());
    }
}
