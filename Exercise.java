import java.util.*;
/**
 * The Exercise class stores data on a single Exercise object,
 * such as the name of the exercise, list of muscles targeted by
 * the exercise, the difficulty of the exercise, and whether the
 * exercise can be performed without gym equipment.
 * 
 * @author Charles Pan
 * @version March 2018
 */
public class Exercise
{
    private String name; // name of the exercise
    private String[] muscles; // String array of muscles targeted in exercise 
    private String difficulty; // whether the exercise is beginner, intermediate, difficult
    private boolean home; // whether the exercise can be performed at home

    String[] musclelist = {"Calves", "Quadriceps", "Hamstrings", "Gluteus", "Hip", "Lower Back", "Abdominals", "Forearms", "Pectorals", "Deltoids", "Lats", "Triceps", "Biceps", "Trapezius"}; // list of valid muscles

    public Exercise(String name, String[] muscles, String difficulty, boolean home)
    {
        setName(name);
        setMuscles(muscles);
        setDifficulty(difficulty);
        setHome(home);
    }
    
    /**
     * This second constructor is provided for the FinishedExerciseModel
     * class, which does not necessary require the muscles, difficulty,
     * or home requirement of the other constructor.
     */
    public Exercise(String name)
    {
        setName(name);
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setMuscles(String[] muscles)
    {   // checking to see if muscles are in the list of valid muscles
        ArrayList<String> key = new ArrayList<String>();
        for (String elem : muscles)
        {
            for (String item : musclelist)
            {
                if (elem.equalsIgnoreCase(item))
                {
                    key.add(elem);
                }
            }
        }
        String[] temp = new String[key.size()];
        for (int n = 0; n < key.size(); n ++)
        {
            temp[n] = key.get(n);
        }
        this.muscles = temp;
    }

    public void setDifficulty(String difficulty)
    {
        this.difficulty = difficulty;
    }

    public void setHome(boolean home)
    {
        this.home = home;
    }
    
    public String getName()
    {
        return name;
    }
    
    public boolean getHome()
    {
        return home;
    }
    
    public String[] getMuscles()
    {
        return muscles;
    }
    
    public String getDifficulty()
    {
        return difficulty;
    }
    
    public String toString()
    {
        return name;
    }

}
