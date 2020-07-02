import java.util.*;
/**
 * The FinishedExerciseModel class is the model component of the
 * MVC framework, and can be manipulated by a controller (see 
 * GraphController or RecordController).
 *
 * @author Charles Pan
 * @version March 2018
 */
public class FinishedExerciseModel extends Observable
{
    ArrayList<FinishedExercise> data = new ArrayList<FinishedExercise>();
    String filepath = "data/User Data.txt";

    public FinishedExerciseModel()
    {
        
    }

    public void addFinishedExercise ( FinishedExercise elem )
    {
        data.add ( elem );
    }

    public void deleteLastExercise ()
    {
        System.out.println(data.size());
        data.remove ( data.size() - 1 );
    }

    public void loadData()
    {
        String[] myLines = TextFileUtil.readLines(filepath);
        for (int n = 0; n < myLines.length; n ++) 
        {
            String[] fields = myLines[n].split(",");
            int[] reps = getIntArray(fields, 2);
            int[] weights = getIntArray(fields, 4);
            Exercise ex = new Exercise (fields[1]);
            FinishedExercise temp = new FinishedExercise (fields[0], fields[1], Integer.parseInt(fields[3]), reps, weights, fields[5]);
            data.add(temp);
        }
        setChanged();
        notifyObservers();
    }

    public void loadExercise( String username, Exercise input )
    {
        String[] myLines = TextFileUtil.readLines(filepath);
        for (int n = 0; n < myLines.length; n ++) 
        {
            String[] fields = myLines[n].split(",");
            if (fields[1].equals(input.getName()) && fields[0].equalsIgnoreCase(username))
            {
                int[] reps = getIntArray(fields, 2);
                int[] weights = getIntArray(fields, 4);
                FinishedExercise temp = new FinishedExercise (fields[0], fields[1], Integer.parseInt(fields[3]), reps, weights, fields[5]);
                data.add(temp);
            }
        }
    }

    public void saveData()
    {
        SaveData.writeLines(data, filepath);
        setChanged();
        notifyObservers();
    }

    public static int[] getIntArray(String[] fields, int index)
    {
        String[] element = fields[index].split(">");
        int[] set = new int[element.length];
        for(int i = 0; i < element.length; i++)
        {
            set[i] = Integer.parseInt(element[i]);
        }
        return set;
    }

    public String toString()
    {
        String modelInfo =
            String.format(
                "FinishedExerciseModel filepath=%s, total records=%d",
                filepath, data.size() );
        return modelInfo;
    }
}
