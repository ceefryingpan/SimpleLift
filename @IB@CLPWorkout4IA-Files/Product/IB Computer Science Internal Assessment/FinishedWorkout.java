import java.util.*;
/**
 * The FinishedWorkout class is modeled after the Workout class,
 * but includes FinishedExercises instead of just exercises.
 * 
 * @author Charles Pan
 * @version March 2018
 */
public class FinishedWorkout
{
    private int number; // number of exercises in the FinishedWorkout 
    private ArrayList<FinishedExercise> exercises; // the actual exercises
    private String difficulty; // the difficulty of the overall FinishedWorkout
    private boolean home; // whether the FinishedWorkout can be performed at home or not

    public FinishedWorkout( ArrayList<FinishedExercise> exercises )
    {
        setFinishedExercises(exercises);
        setNumber(exercises.size());
        setDifficulty(exercises);
        setHome(exercises);
    }

    public void setFinishedExercises( ArrayList<FinishedExercise> exercises )
    {
        this.exercises = exercises;
    }

    public void setDifficulty( ArrayList<FinishedExercise> exercises )
    {  
        this.difficulty = "Beginner";
        for (FinishedExercise elem : exercises)
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

    public void setHome( ArrayList<FinishedExercise> exercises )
    {
        this.home = true;
        for (FinishedExercise elem : exercises)
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

    public ArrayList<FinishedExercise> getFinishedExercises()
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

    public String stringFinishedExercises()
    {
        String set = "";
        for (FinishedExercise elem : exercises)
        {
            set = set + System.lineSeparator() + elem;
        }
        return set;
    }

    public String toString()
    {
        return "This FinishedWorkout has these exercises: " + stringFinishedExercises();
    }
}