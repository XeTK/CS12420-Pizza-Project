package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Order;
/**This Class is a gui element for displaying the global till to the end user
 * you have the ability to click on the items to bring it back to the main form where it can be used to make a new order the same as the last
 */
public class TillFrame extends JFrame implements MouseListener
{
	// declares the components on the form
	private JPanel 
					jP = new JPanel();
	private JList
					jLTill = new JList();
	private JScrollPane
					jSPTill = new JScrollPane();
	private JLabel 
					jLTotalForDay = new JLabel("Total for the day: £0");
	private MainFrame 
					globalFrame = null;
	/**Default Constuctor for the class its sets all the layout and sizes for the form**/
	public TillFrame(MainFrame globalFrame)
	{
		this.globalFrame = globalFrame;
		//instansiation of the form and setting layout
		//set layout for the jpanel
		jP.setLayout(null);
		//add jpanel to the jframe form
		add(jP);
		//set size of the scroll panel
		jSPTill.setSize(new Dimension(400,400));
		//set the location of the scroll panel
		jSPTill.setLocation(10,45);
		//add the list of items to the scroll panel
		jSPTill.getViewport().add(jLTill);
		//set size of the jlabel
		jLTotalForDay.setSize(new Dimension(200,25));
		//sets its location
		jLTotalForDay.setLocation(10,10);
		//add components to the jpanel
		jP.add(jLTotalForDay);
		jP.add(jSPTill);
		//set the tooltip for the scrollpanel
		jSPTill.setToolTipText("Here is where the till is held");
		jLTill.addMouseListener(this);
		//lock size of the form
		setMinimumSize(new Dimension(420,475));
		setMaximumSize(new Dimension(420,475));
		setSize(420,475);
		setResizable(false);
		//set its location on the screen so it can easierly be seen by the end user
		setLocation(50, 50);
		//set title of the till
		setTitle("Todays Till");
		//define its close action
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//set the form as visible
		setVisible(true);
		setEnabled(false);
	}
	/**Update GlobalTillFrame method is used for updating the frame with the finished orders**/
	public void updateGlobalTillFrame(ArrayList<Order> inOrders)
	{
		setEnabled(true);
		//new arraylist created for holding the temp items to show the end user
		ArrayList tempAL = new ArrayList();
		//loop throgh and add finished orders to an arraylist
		for (int i =0; i< inOrders.size();i++)
			tempAL.add(inOrders.get(i).getDate().toString() + ": " + inOrders.get(i).getCustomerName() + ": £" + (new DecimalFormat("#0.00")).format(inOrders.get(i).getSubtotal()));
		//redeclare the jlist with the object list
		jLTill = new JList(tempAL.toArray());
		//set the viewport for the scroll panel
		jSPTill.getViewport().add(jLTill);
		jLTill.addMouseListener(this);
	}
	/**this method is a public void for updating the label holding the total*/
	public void updateTotalsTotal(BigDecimal inTotal)
	{
		//sets text on the label to a big decimal
		jLTotalForDay.setText("Total for the day: £" + new DecimalFormat("#0.00").format(inTotal.doubleValue()));
	}
	@Override
	public void mouseClicked(MouseEvent e){}
	@Override
	public void mouseEntered(MouseEvent arg0){}
	@Override
	public void mouseExited(MouseEvent arg0){}
	@Override
	public void mousePressed(MouseEvent e)
	{
		//get order back to the screen
		if (e.getComponent() == jLTill)
		{
			ArrayList<Order> tempOL = globalFrame.getTill().getOrders();
			globalFrame.setOrder(tempOL.get(jLTill.getSelectedIndex()));
			globalFrame.updateOrderItemList();
			globalFrame.updateOrderList();
		}
	}
	@Override
	public void mouseReleased(MouseEvent arg0){}
}
