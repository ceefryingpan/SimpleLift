import java.awt.*;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.image.*;
import java.util.*;
/**
 * GraphComponent generates a graph based on existing data in a .csv
 * file.
 *
 * @author Charles Pan
 * @version March 2018
 */
public class GraphComponent extends JPanel
{
    FinishedExerciseModel mod;
    String option;

    public int verticalunits = 10;
    public int horizontalunits = 5;
    public int xscale = 120;
    public int yscale = 45;

    String[] xlabels;
    int[] ypoints;
    String[] ylabels = new String[verticalunits];
    Point[] points = new Point[horizontalunits];

    int xoffset = 40;
    int yoffset = 10;
    int bottom = (verticalunits * yscale) + yoffset;
    /**
     * Constructor for objects of class GraphComponent
     */
    public GraphComponent(FinishedExerciseModel mod, String yaxisoption)
    {
        this.mod = mod;
        this.option = yaxisoption;
    }

    public void updateModel(FinishedExerciseModel mod)
    {
        this.mod = mod;
        repaint();
    }

    public void updateOption(String option)
    {
        this.option = option;
        repaint();
    }

    public void paintComponent( Graphics g )
    {
        super.paintComponent(g);
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        populateXPoints();
        if (option.equals("Max Weight"))
        {
            populateYPointsMaxWeight();
        }
        else if (option.equals("Total Weight"))
        {
            populateYPointsTotalWeight();
        }
        drawAxes(g); //draw x and y axes
        drawXLabels(g);
        drawYLabels(g);
        drawPoints(g);
        drawLines(g);
    }

    public void drawAxes( Graphics g )
    {
        g.setColor(Color.black);
        g.drawLine(xoffset, yoffset, xoffset, bottom); //draw y-axis
        g.drawLine(xoffset, bottom, horizontalunits * xscale + xoffset, bottom); //draw x-axis
    }

    public void drawXLabels( Graphics g )
    {
        g.setColor(Color.red);
        for (int n = 1; n < horizontalunits + 1; n++)
        {
            g.drawRect((n * xscale) + xoffset, bottom, 4, 4);
            g.drawString(xlabels[n - 1], (n * xscale) + xoffset - 10, bottom + 15);
        }
    }

    public void drawYLabels( Graphics g )
    {
        populateYLabels();
        g.setColor(Color.blue);
        for (int n = 1; n < verticalunits + 1; n++)
        {
            g.drawRect(xoffset, ((verticalunits - n) * yscale) + yoffset, 4, 4);
            g.drawString(ylabels[verticalunits - n], xoffset - 38, ((n - 1) * yscale) + yoffset + 5);
        }
    }

    public void drawPoints( Graphics g )
    {
        g.setColor(Color.green);
        for (int n = 1; n < horizontalunits + 1; n++)
        {
            double ratio = (double) (ypoints[n - 1] - getMin(ypoints))/(getMax(ypoints) - getMin(ypoints));
            double height = ratio * (verticalunits - 1) * yscale ;
            int integeroff = (int) Math.round(height);
            g.drawRect((n * xscale) + xoffset, (bottom) - integeroff - yscale, 4, 4);
            points[n - 1] = new Point ((n * xscale) + xoffset, (bottom) - integeroff - yscale);
        }
    }

    public void drawLines( Graphics g )
    {
        g.setColor(Color.green);
        for (int n = 1; n < points.length; n ++)
        {
            g.drawLine(points[n - 1].x, points[n - 1].y, points[n].x, points[n].y);
        }
    }

    public void populateXPoints()
    {
        ArrayList<String> set = new ArrayList<String>();
        for (FinishedExercise elem : mod.data)
        {
            set.add(elem.getTimeStamp());
        }
        if (set.size() > horizontalunits)
        {
            xlabels = new String[5];
            for (int n = set.size() - 5; n < set.size(); n ++)
            {
                xlabels[n - set.size() + 5] = set.get(n).substring(0, 10);
            }
        }
        else
        {
            xlabels = new String[set.size()];
            for (int n = 0; n < set.size(); n ++)
            {
                xlabels[n] = set.get(n).substring(0, 10);
            }
        }
    }

    public void populateYPointsMaxWeight()
    {
        ArrayList<Integer> set = new ArrayList<Integer>();
        for (int n = mod.data.size() - horizontalunits; n < mod.data.size(); n ++)
        {
            int max = getMax(mod.data.get(n).getWeight());
            set.add(max);
        }
        populateYHelper( set );
    }

    public void populateYPointsTotalWeight()
    {
        ArrayList<Integer> set = new ArrayList<Integer>();
        for (int n = mod.data.size() - horizontalunits; n < mod.data.size(); n ++)
        {
            int total = 0;
            for (int x = 0; x < mod.data.get(n).getReps().length; x ++)
            {
                total = total + (mod.data.get(n).getReps()[x] * mod.data.get(n).getWeight()[x]);
            }
            set.add(total);
        }
        populateYHelper( set );
    }

    public void populateYLabels()
    {
        int max = getMax(ypoints);
        int min = getMin(ypoints);
        double increment = (double) (max - min) / (verticalunits - 1);
        increment = round(increment, 1);
        if ( increment == 0 )
        {
            increment = 1;
        }
        for (int n = 0; n < ylabels.length; n ++)
        {
            double num = min + (n * increment);
            ylabels[n] = Double.toString(num);
        }
    }

    public void populateYHelper(ArrayList<Integer> set)
    {
        if (set.size() > 10)
        {
            ypoints = new int[10];
            for (int n = set.size() - 10; n < set.size(); n ++)
            {
                ypoints[n - set.size() + 10] = set.get(n);
            }
        }
        else
        {
            ypoints = new int[set.size()];
            for (int n = 0; n < set.size(); n ++)
            {
                ypoints[n] = set.get(n);
            }
        }
    }

    public static int getMax(int[] set)
    {
        int max = 0;
        for(int elem : set)
        {
            if (elem > max)
            {
                max = elem;
            }
        }
        return max;
    }

    public static int getMin(int[] set)
    {
        int min = set[0];
        for(int elem : set)
        {
            if (elem < min)
            {
                min = elem;
            }
        }
        return min;
    }

    public static double round (double number, int places) 
    {
        if (places < 0) 
        {
            throw new IllegalArgumentException();
        }
        long factor = (long) Math.pow(10, places);
        number = number * factor;
        long elem = Math.round(number);
        return (double) elem / factor;
    }
}
