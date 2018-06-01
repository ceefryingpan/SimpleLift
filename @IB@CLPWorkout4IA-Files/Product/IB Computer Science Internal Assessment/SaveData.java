import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
/**
 * SaveData provides methods that allows data to be written to a
 * .csv file, in order to store a user's data.
 *
 * @author Charles Pan
 * @version March 2018
 */
public class SaveData
{
    public static void writeLines(ArrayList<FinishedExercise> set, String path)
    {
        String[] lines = new String[set.size()];
        for (int n = 0; n < set.size(); n ++)
        {
            lines[n] = lineFinishedExercise(set.get(n));
        }
        TextFileUtil.writeLines(path, lines);
    }

    public static void writeNewExercise(FinishedExercise ex, String path)
    {
        TextFileUtil.writeNewLine(path, lineFinishedExercise(ex));
    }

    public static String lineFinishedExercise(FinishedExercise ex)
    {
        String reps = intToString(ex.getReps());
        String exerciseweight = intToString(ex.getWeight());
        String text = ex.getUsername() + "," + ex.getName() + "," + reps + "," + ex.getSets() + "," + exerciseweight + "," + ex.getTimeStamp();
        return text;
    }

    public static String intToString(int[] array) // separated with backslash
    {
        String answer = "";
        for (int n = 0; n < array.length; n ++)
        {
            answer = answer + array[n] + ">";
        }
        answer = answer.substring(0, answer.length() - 1);
        return answer;
    }

    public static String stringArraytoString(String[] set)
    {
        String answer = "";
        for (String elem : set)
        {
            answer = answer + elem + ",";
        }
        return answer.substring(0, answer.length() - 2);
    }
}
