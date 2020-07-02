import java.util.*;
/**
 * WorkoutGenerator is a class that provides methods for generating
 * a set of exercises or a workout based on criteria.
 *
 * @author Charles Pan
 * @version March 2018
 */
public class Generator
{
    public static ArrayList<Exercise> generateExercises()
    {
        ArrayList<Exercise> exercises = new ArrayList<Exercise>();
        String[] myLines = TextFileUtil.readLines("data/Workout Exercises.csv");
        for (int n = 1; n < myLines.length; n ++) 
        {
            String[] fields = myLines[n].split(",");
            String[] muscles = fields[2].split("/");
            Exercise temp = new Exercise (fields[0], muscles, fields[3], Boolean.parseBoolean(fields[1]));
            exercises.add(temp);
        }
        return exercises;
    }
    
    public static ArrayList<String> generateExerciseStrings()
    {
        ArrayList<String> exercises = new ArrayList<String>();
        String[] myLines = TextFileUtil.readLines("data/Workout Exercises.csv");
        for (int n = 1; n < myLines.length; n ++) 
        {
            String[] fields = myLines[n].split(",");
            String[] muscles = fields[2].split("/");
            Exercise temp = new Exercise (fields[0], muscles, fields[3], Boolean.parseBoolean(fields[1]));
            exercises.add(temp.toString());
        }
        return exercises;
    }

    public static String[] getExerciseNames(ArrayList<Exercise> set)
    {
        String[] array = new String[set.size()];
        for (int n = 0; n < set.size(); n ++)
        {
            array[n] = set.get(n).getName();
        }
        return array;
    }

    public static Workout generateWorkout(ArrayList<Exercise> exercises, ArrayList<String> muscles, boolean home, String difficulty)
    {
        ArrayList<Exercise> matches = new ArrayList<Exercise>();
        boolean contains = false;
        for (String muscle : muscles)
        {
            for (Exercise exer : exercises)
            {
                if ( difficulty.equals ("Difficult") )
                {
                    for (String elem : exer.getMuscles())
                    {
                        if (muscle.equals(elem) && !matches.contains(exer) && exer.getHome() == home )
                        {
                            matches.add(exer);
                        }
                    }
                }
                else if ( difficulty.equals ("Intermediate") && exer.getDifficulty().equals("Intermediate") || exer.getDifficulty().equals("Beginner"))
                {
                    for (String elem : exer.getMuscles())
                    {
                        if (muscle.equals(elem) && !matches.contains(exer) && exer.getHome() == home )
                        {
                            matches.add(exer);
                        }
                    }
                }
                else if ( difficulty.equals ("Beginner") && exer.getDifficulty().equals("Beginner"))
                {
                    for (String elem : exer.getMuscles())
                    {
                        if (muscle.equals(elem) && !matches.contains(exer) && exer.getHome() == home )
                        {
                            matches.add(exer);
                        }
                    }
                }
            }
        }
        Random rand = new Random();
        int count = 0;
        ArrayList<Exercise> condensed = new ArrayList<Exercise>();
        if (matches.size() > muscles.size() * 3)
        {
            while (count < muscles.size() * 3)
            {
                int n = rand.nextInt(matches.size());
                if (!condensed.contains(matches.get(n)))
                {
                    condensed.add(matches.get(n));
                    count++;
                }
            }
        }
        else
        {
            condensed = matches;
        }
        Workout set = new Workout(condensed);
        return set;
    }
}