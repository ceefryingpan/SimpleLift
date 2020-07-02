import java.util.*;
/**
 * The FinishedExercise class extends the Exercise class and adds
 * a few more fields that are to be inputted after the user finishes
 * the exercise, including sets, reps, weight, and a timestamp.
 * 
 * @author Charles Pan
 * @version March 2018
 */

public class FinishedExercise extends Exercise
{
    private String username;
    private int sets; // number of sets
    private int[] reps = new int[sets]; // number of repetitions of the exercise per set
    private int[] weight = new int[sets]; //weight for each set
    private String timestamp;
    
    public FinishedExercise ( String username, Exercise ex, int sets, int[] reps, int[] weight, String timestamp)
    {
        super(ex.getName(), ex.getMuscles(), ex.getDifficulty(), ex.getHome());
        setUsername(username);
        setSets(sets);
        setReps(reps);
        setWeight(weight);
        setTimeStamp( timestamp );
    }
    
    /**
     * This second constructor makes it easier for the
     * FinishedExerciseModel class to read data from the .csv file,
     * since the file does not store data such as muscles, difficulty,
     * etc.
     */
    public FinishedExercise ( String username, String name, int sets, int[] reps, int[] weight, String timestamp)
    {
        super(name);
        setUsername(username);
        setSets(sets);
        setReps(reps);
        setWeight(weight);
        setTimeStamp( timestamp );
    }
    
    public void setUsername( String username )
    {
        this.username = username;
    }
    
    public void setSets( int sets )
    {
        this.sets = sets;
    }
    
    public void setReps( int[] reps )
    {
        this.reps = reps;
    }
    
    public void setWeight( int[] weight )
    {
        this.weight = weight;
    }
    
    public void setTimeStamp( String timestamp )
    {
        this.timestamp = timestamp;
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public int getSets()
    {
        return sets;
    }
    
    public int[] getReps()
    {
        return reps;
    }
    
    public int[] getWeight()
    {
        return weight;
    }
    
    public String getTimeStamp()
    {
        return timestamp;
    }
    
    public String toString()
    {
        return "This " + getName() + " was done at " + timestamp + " with " + sets + " sets.";
    }
}