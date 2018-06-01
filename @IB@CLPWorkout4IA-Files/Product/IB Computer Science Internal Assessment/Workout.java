import java.util.*;
/**
 * The workout class includes a set of exercises, the difficulty of
 * the workout, whether the workout can be done at home, and the
 * number of exercises.
 * 
 * @author Charles Pan
 * @version March 2018
 */
public class Workout
{
    private int number; // number of exercises in the workout 
    private ArrayList<Exercise> exercises; // the actual exercises
    private String difficulty; // the difficulty of the overall workout
    private boolean home; // whether the workout can be performed at home or not

    public Workout( ArrayList<Exercise> exercises )
    {
        setExercises(exercises);
        setNumber(exercises.size());
        setDifficulty(exercises);
        setHome(exercises);
    }

    public void setExercises( ArrayList<Exercise> exercises )
    {
        this.exercises = exercises;
    }

    public void setDifficulty( ArrayList<Exercise> exercises )
    {   // requires that every exercises
        this.difficulty = "Beginner";
        for (Exercise elem : exercises)
        {
            if (elem.getDifficulty().equals("Difficult"))
            {
                this.difficulty = "Difficult";
                return;
            }
            if (elem.getDifficulty().equals("Intermediate"))
            {
                this.difficulty = "Intermediate";
            }
        }
    }

    public void setHome( ArrayList<Exercise> exercises )
    {
        this.home = true;
        for (Exercise elem : exercises)
        {
            if (!elem.getHome())
            {
                this.home = false;
                return;
            }
        }
    }
    
    public void setNumber( int number )
    {
        this.number = number;
    }
    
    public int getNumber()
    {
        return number;
    }
    
    public ArrayList<Exercise> getExercises()
    {
        return exercises;
    }
    
    public String getDifficulty()
    {
        return difficulty;
    }
    
    public boolean getHome()
    {
        return home;
    }
    
    public String stringExercises()
    {
        String set = "";
        for (Exercise elem : exercises)
        {
            set = set + "\n" + elem;
        }
        return set;
    }
    
    public String jLabelExercises()
    {
        String set = "";
        for (Exercise elem : exercises)
        {
            set = set + "<br>" + elem;
        }
        set = set.substring(0, set.length());
        set = set + "</html>";
        return set;
    }
    
    public String toString()
    {
        return "This workout has these exercises: " + stringExercises();
    }
    
    public String toJLabel()
    {
        return "<html>This workout has these exercises: " + jLabelExercises();
    }
}