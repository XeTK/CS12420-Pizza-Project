package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
/**This Class is the global till object for the base code*/
public class Till
{
	//Arraylist of Orders
	private ArrayList<Order> alOrders;
	/**The default constructor creates a new arraylist on creation of the class*/
	public Till()
	{
		//set the global arraylist to be a new arraylist
		alOrders = new ArrayList<Order>();
	}
	/**This Method adds a new order to the till*/
	public void addOrder(Order order)
	{
		this.alOrders.add(order);
	}
	/**This method returns the bigdecimal of the days total*/
	public BigDecimal getTotalForDay()
	{
		//create new bigdecimal to hold a tempaory value for all the orders
		BigDecimal tempTotal = new BigDecimal(0);
		//loop through all the orders and add the subtotal from the orders to the other order to get the full total
		for (int i = 0; i< alOrders.size(); i++)
			tempTotal = tempTotal.add(alOrders.get(i).getSubtotal());
		//return the full value
		return tempTotal;
	}
	/**This Method saves the till to a xml object that the end user can then re import*/
	public void save(File inPath)
	{
		try
		{
			//create new encoder
			XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(inPath)));
			//set persistencedelegate to of bigdecimal to double
			encoder.setPersistenceDelegate(BigDecimal.class, encoder.getPersistenceDelegate(double.class));
			//write till object to file
			encoder.writeObject(this);
			//close encoder
			encoder.close();
		}
		catch (FileNotFoundException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	} 
	/**This Method loads in the xml exported till from earlier*/
	public static Till load(File file) throws IOException
	{
		//state a new xml decoder
	    XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(file)));
	    //create a new till order
	    Till till = (Till) decoder.readObject();
	    //close the decoder
	    decoder.close();
	    //return till object
	    return till;

	}
	public ArrayList<Order> getAlOrders()
	{
		return alOrders;
	}
	public void setAlOrders(ArrayList<Order> alOrders)
	{
		this.alOrders = alOrders;
	}
	//get a arraylist containing the orders
	public ArrayList<Order> getOrders()
	{
		return alOrders;
	}
}
