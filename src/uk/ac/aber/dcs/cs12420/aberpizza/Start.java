package uk.ac.aber.dcs.cs12420.aberpizza;
import uk.ac.aber.dcs.cs12420.aberpizza.gui.MainFrame;
/**This Class is the startup class for the project without this the project would not have a start space*/
public class Start
{
	/**this class is to instantiate a new instance of the program and is the start point for*/
	public static void main(String[] args)
	{
		//Setting Settings Mac OS X
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Aber Pizza");
		//Catch exception if any accure
		try 
		{
			// create a new instance of mainFrame for the end user to see
			new MainFrame();
		}
		catch (Exception ex)
		{
			System.err.println(ex.toString());
		}
	}
}
