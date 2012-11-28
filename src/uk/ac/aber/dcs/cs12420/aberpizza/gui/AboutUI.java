package uk.ac.aber.dcs.cs12420.aberpizza.gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**This forms main goal in life is to inform people about stuff mostly about why this whole project exists*/
public class AboutUI extends JFrame implements ActionListener
{
	//decleration for the items in the frame
	private JPanel
					jP = new JPanel();
	private JButton
					jBOk = new JButton("Ok");
	private JLabel
					jLText = new JLabel(),
					jLPicture = new JLabel();
	/**The Default constructor updates the layout and sixe of the items along with adding the action listener for the button*/
	public AboutUI()
	{
		//set layout of jpanel to null
		jP.setLayout(null);
		//add it to the frame
		add(jP);
		
		//set name, title, its relitive location, its close action and if its visible
		setSize(300,350);
		setTitle("About");
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		
		//set sizes of components
		jBOk.setSize(250,25);
		jLPicture.setSize(128,128);
		jLText.setSize(250,140);
		
		//set locations of components
		jBOk.setLocation(25,300);
		jLText.setLocation(25,128);
		jLPicture.setLocation((getWidth() / 2) - 64,0);
		//set image as a pizza
		jLPicture.setIcon(new ImageIcon(ClassLoader.getSystemResource("pizza_slice.png")));
		
		//set the text to equal something of interest i have a small bit of markup with a decleration on it
		jLText.setText( 
				"<html><center><b>Aber Pizza Assignment</b><br>" + 
		        "<b><i>CS12420 Project</i></b><br>" + 
		        "This assignment was created by Tom Rosier<br>" + 
		        "I 'Declare' that all content is my own or is copyright free<br>" + 
		        "Any problems please email thr2@aber.ac.uk<br>" + 
		        "&#169Tom Rosier 2012</center></html>");
		//add a action liserner to the button
		jBOk.addActionListener(this);
		// add components to panel
		jP.add(jBOk);
		jP.add(jLText);
		jP.add(jLPicture);
	}
	/**this method closes the form when the button is pressed*/
	public void actionPerformed(ActionEvent e)
	{
		//kill the form
		this.dispose();
	}
}
