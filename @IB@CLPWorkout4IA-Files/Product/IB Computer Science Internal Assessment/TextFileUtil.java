import javax.swing.*;
import java.io.*;

/**
 * TextFileUtil provides functions that make it easy to 
 * read the contents of plain text files.  It cannot be
 * used to read complex files like Excel, Word, etc.
 * 
 * Original version: October 25, 2016
 * 
 * @author Andrew Alford 
 * @version May 16, 2017
 */
public class TextFileUtil
{
    private TextFileUtil(){}

    /**
     * Reads the contents of the plain text file, returning
     * it as a single String object.
     * 
     * @param path The file you want to read
     * @return The contents of the file as one (possibly very long!) string
     */
    public static String read( String path )
    {
        File f = new File( path );
        if ( !f.exists() )
        {
            String messageTemplate = "The file named\n\"%s\"\ndoes not exist.\n\n"
                + "Double check your spelling and try again?";
            String actualMessage = String.format( messageTemplate, path );
            JOptionPane.showMessageDialog( null, actualMessage );
            return "";
        }
        
        String contents = "";
        FileReader fin = null;
        BufferedReader reader = null;
        try 
        {
            fin = new FileReader( f );
            reader = new BufferedReader( fin );

            for ( String line = reader.readLine(); line != null; line = reader.readLine() )
            {
                contents = contents + line + "\n";
            }
            
            reader.close();
            fin.close();
            reader = null;
            fin = null;
        }
        catch ( Exception ex )
        {
            String messageTemplate = "TextFileUtil.read exception\nA problem occurred while attempting to read\n\"%s\"\n\n"
                + ex.getMessage();
            String actualMessage = String.format( messageTemplate, path );
            JOptionPane.showMessageDialog( null, actualMessage );
            return "";
        }
        
        return contents;
    }

    /**
     * Reads the contents of the plain text file, returning
     * it as an array of String objects.  Note that blank lines
     * in the file count as lines!  (Which may not have occurred
     * to you intuitively!)
     * 
     * @param path The file you want to read
     * @return An array of String objects, one for every line in the file.
     */
    public static String[] readLines( String path )
    {
        String contents = TextFileUtil.read( path );
        String[] lines = contents.split( "\n" );
        return lines;
    }
    
    public static boolean write( String path, String text )
    {
        boolean succeeded = false;
        FileWriter buffer = null;
        PrintWriter out = null;
        File f = new File( path );
        try 
        {
            buffer = new FileWriter( path );
            out = new PrintWriter( buffer );
            out.println( text );
            succeeded = true;
        }
        catch ( Exception e )
        {
            System.out.println( "TextFileUtil.write exception >> " + e.getMessage() );            
        }
        finally 
        {
            close( out );
            close( buffer );
        }   
        
        return succeeded;
    }
    
    public static boolean writeLines( String path, String[] lines )
    {
        boolean succeeded = false;
        FileWriter buffer = null;
        PrintWriter out = null;
        File f = new File( path );
        try 
        {
            buffer = new FileWriter( path );
            out = new PrintWriter( buffer );
            for ( String line : lines )
            {
                out.println( line );
            }
            succeeded = true;
        }
        catch ( Exception e )
        {
            System.out.println( "TextFileUtil.writeLines exception >> " + e.getMessage() );            
        }
        finally 
        {
            close( out );
            close( buffer );
        }   
        
        return succeeded;
    }
    
    public static boolean writeNewLine( String path, String text )
    {
        boolean succeeded = false;
        FileWriter buffer = null;
        PrintWriter out = null;
        File f = new File( path );
        try 
        {
            buffer = new FileWriter( path, true );
            out = new PrintWriter( buffer );
            out.println( text );
            succeeded = true;
        }
        catch ( Exception e )
        {
            System.out.println( "TextFileUtil.write exception >> " + e.getMessage() );            
        }
        finally 
        {
            close( out );
            close( buffer );
        }   
        
        return succeeded;
    }
    
    public static void close( FileWriter resource )
    {
        try 
        {
            resource.close();
            resource = null;
        }
        catch ( Exception e )
        {
            System.out.println( "TextFileUtil.close(FileWriter) exception >> " + e.getMessage() );
        }
    }
    
    public static void close( PrintWriter resource )
    {
        try 
        {
            resource.close();
            resource = null;
        }
        catch ( Exception e )
        {
            System.out.println( "TextFileUtil.close(PrintWriter) exception >> " + e.getMessage() );
        }
    }
    public static void main( String[] args )
    {
        String myCode = TextFileUtil.read( "TextFileUtil.java" );
        System.out.println( myCode );
        
        // 
        String[] linesOfCode = TextFileUtil.readLines( "TextFileUtil.java" );
        System.out.println( linesOfCode[11] );
    }
    
}
