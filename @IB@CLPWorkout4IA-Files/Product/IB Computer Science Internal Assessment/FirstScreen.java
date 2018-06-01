import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * The FirstScreen displays a view for the user to input their name.
 * 
 * @author Charles Pan
 * @version March 2018
 */
public class FirstScreen extends JFrame implements ActionListener
{
    JLabel header = new JLabel ("Please enter your name:");
    JTextField nameTF = new JTextField ("");
    
    JLabel namelbl = new JLabel ("Name:");
    
    JButton continuebutton = new JButton("Continue");
    JButton exit = new JButton ("Exit");
    
    public FirstScreen ()
    {
        JPanel title1Pan = new JPanel ( new BorderLayout() );
        title1Pan.add(namelbl, BorderLayout.NORTH );
        title1Pan.add(nameTF, BorderLayout.CENTER );
        
        Container c = getContentPane();
        c.setLayout ( new GridLayout(4, 1) );
        c.add(header);
        c.add(title1Pan);
        c.add(continuebutton);
        c.add(exit);
        
        continuebutton.addActionListener( this );
        exit.addActionListener( this );
        
        setTitle ("Workout Dialog");
        setSize( 500, 500 );
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        setVisible( true );
    }
    
    public void actionPerformed( ActionEvent e )
    {
        Object source = e.getSource();
        if ( source == continuebutton)
        {
            SecondScreen view = new SecondScreen (nameTF.getText());
            this.dispose();
        }
        else if (source == exit)
        {
            this.dispose();
        }
    }
}
