package uk.ac.aber.dcs.cs12420.aberpizza.gui;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import uk.ac.aber.dcs.cs12420.aberpizza.data.*;
/**The Main Frame is the main GUI element that the end user interacts with
 *it also contains all the liserners and a few methods to make the base package work with the gui
 */
public class MainFrame extends JFrame implements ActionListener, MouseListener
{
	// create a new jpanel to house all the components
	private JPanel 
					jP = new JPanel();
	//create a menubar
	private JMenuBar 
					jMB = new JMenuBar();
	//create some jpopup menus
	private JPopupMenu
					jPM = new JPopupMenu(),
					jSHOW = new JPopupMenu();
	//create lots of components to put on the screen {
	private JMenu 
					jMFile = new JMenu("File"),
					jMAdmin = new JMenu("Admin-Options"),
					jMHelp = new JMenu("Help");
	
	private JButton 
					bMainMenu = new JButton("Main Menu"),
					bPlus = new JButton("+"),
					bMinus = new JButton("-"),
					bAddToOrder = new JButton("Add To Order"),
					bPay = new JButton("Pay"),
					bCancel = new JButton("Clear Order");
	
	private JMenuItem 
					jMISaveTill = new JMenuItem("Save Till"),
					jMILoadTill = new JMenuItem("Load Till"),
					jMSaveReceipt = new JMenuItem("Save Receipt"),
					jMIExit = new JMenuItem("Exit"),
					jMIStartNewTill = new JMenuItem("Start New Till"),
					jMICloseTill = new JMenuItem("Close Till"),
					//jMIHelp = new JMenuItem("Help"),
					jMIAbout = new JMenuItem("About"),
					jMIDelete = new JMenuItem("Delete"),
					jMIEditOrder = new JMenuItem("Edit Order");
	
	private JList 
					jLItems = new JList(ListItems.values()),
					jLCurrentOrder = new JList(),
					jLTotalOrder = new JList();
	
	private JScrollPane 
					jSPItems = new JScrollPane(),
					jSPCurrent = new JScrollPane(),
					jSPTotal = new JScrollPane();
	
	private JTextField
					jTBCustomers = new JTextField("Customers Name");
	
	private JLabel
					jLaItems = new JLabel("Items:"),

					jLaCurrentOrder = new JLabel("Current Order:"),
					jLCustomer = new JLabel("Order For:"),
					jLSubTotal = new JLabel("SubTotal: £0.00"),
					//jLDiscount = new JLabel("Discounts: £0.00"),
					jLTotal = new JLabel("Total Price: £0.00"),
					jLToolTip = new JLabel("");
	// }
	//declare the global variables for till, order, orderitem and tillfram
	private Till 
					globalTill = null;
	private Order 
					globalOrder = null;
	private OrderItem
					tempOrderItem = null;
	private TillFrame 
					globalTillFrame = new TillFrame(this);
	//JfileChooser for creating a save dialog and getting a location to save the recept to
	final JFileChooser 
					jFCSaveDialog = new JFileChooser();
	/**The Default constructor adds and sets all the components of the GUI it also adds actionliserners where there needed*/
	public MainFrame()
	{
		//Setup JPanel
		jP.setLayout(null);
		add(jP);
		
		//SetUp Buttons
		bMainMenu.setBounds(125,10,100,25);
		bMinus.setBounds(360,10,45,25);
		bPlus.setBounds(400,10,45,25);
		bAddToOrder.setBounds(325,500,120,25);
		bPay.setBounds(445,500,100,25);
		bCancel.setBounds(695,500,100,25);
		
		//JMenu 
		jMFile.add(jMISaveTill);
		jMFile.add(jMILoadTill);
		jMFile.add(jMSaveReceipt);
		jMFile.add(jMIExit);
		jMAdmin.add(jMIStartNewTill);
		jMAdmin.add(jMICloseTill);
		//jMHelp.add(jMIHelp);
		jMHelp.add(jMIAbout);
		jPM.add(jMIEditOrder);
		jPM.add(jMIDelete);
		jMB.add(jMFile);
		jMB.add(jMAdmin);
		jMB.add(jMHelp);
		
		
		//JScrollPanel	
		
		jSPItems.setSize(new Dimension(210,455));
		jSPCurrent.setSize(new Dimension(210,455));
		jSPTotal.setSize(new Dimension(340,455));
		jSPItems.setLocation(10, 40);
		jSPCurrent.setLocation(230, 40);
		jSPTotal.setLocation(450, 40);
		jSPItems.getViewport().add(jLItems);
		jSPCurrent.getViewport().add(jLCurrentOrder);
		jSPTotal.getViewport().add(jLTotalOrder);
		jLItems.setSelectedIndex(0);
		jLCurrentOrder.setSelectedIndex(0);
		jLTotalOrder.setSelectedIndex(0);
		
		//JTestFeild
		jTBCustomers.setLocation(540, 10);
		jTBCustomers.setSize(new Dimension(250, 25));
		
		//JLabel
		jLCustomer.setSize(new Dimension(100,25));
		jLTotal.setSize(new Dimension(120,25));
		//jLDiscount.setSize(new Dimension(210,25));
		jLaItems.setSize(new Dimension(100,25));
		jLaCurrentOrder.setSize(new Dimension(100,25));
		jLSubTotal.setSize(new Dimension(200,25));
		jLCustomer.setLocation(450,10);
		jLTotal.setLocation(550, 500);
		//jLDiscount.setLocation(10, 500);
		jLaItems.setLocation(10,10);
		jLaCurrentOrder.setLocation(230,10);
		jLSubTotal.setLocation(220,500);
		
		//Set Tooltips
		bMainMenu.setToolTipText("Return To Main Menu");
		bPlus.setToolTipText("+ 1 to Quantity");
		bMinus.setToolTipText("- 1 From Quantity");
		bAddToOrder.setToolTipText("Add Order to main bill");
		bPay.setToolTipText("Click here to Pay");
		bCancel.setToolTipText("Cancel the transaction");
		jMISaveTill.setToolTipText("Save Till Current Till");
		jMILoadTill.setToolTipText("Load Till A Previous Till");
		jMSaveReceipt.setToolTipText("Save Receipt");
		jMIExit.setToolTipText("Exit Application");
		jMIStartNewTill.setToolTipText("Clear Current Till And Start From Scratch");
		jMICloseTill.setToolTipText("Clear Current Till And Exit The Program");
//		jMIHelp.setToolTipText("Stuck Here's the Help");
		jMIAbout.setToolTipText("Find Out About This Program");
		
		//Actionliserners
		bMainMenu.addActionListener(this);
		bPlus.addActionListener(this);
		bMinus.addActionListener(this);
		bAddToOrder.addActionListener(this);
		bPay.addActionListener(this);
		bCancel.addActionListener(this);
		jMISaveTill.addActionListener(this);
		jMILoadTill.addActionListener(this);
		jMSaveReceipt.addActionListener(this);
		jMIExit.addActionListener(this);
		jMIStartNewTill.addActionListener(this);
		jMICloseTill.addActionListener(this);
//		jMIHelp.addActionListener(this);
		jMIAbout.addActionListener(this);
		jMIDelete.addActionListener(this);
		jMIEditOrder.addActionListener(this);	
		
		//Add Components To JPanel
		jP.add(bMainMenu);
		jP.add(bPlus);
		jP.add(bMinus);
		jP.add(bAddToOrder);
		jP.add(bPay);
		jP.add(bCancel);
		jP.add(jSPItems);
		jP.add(jSPCurrent);
		jP.add(jSPTotal);
		jP.add(jTBCustomers);
		jP.add(jLCustomer);
		jP.add(jLTotal);
		//jP.add(jLDiscount);
		jP.add(jLaItems);
		jP.add(jLaCurrentOrder);
		jP.add(jLSubTotal);

		
		//Setup Main Window
		setMinimumSize(new Dimension(800,575));
		setMaximumSize(new Dimension(800,575));
		setSize(800,575);
		setTitle("AberPizza CS12420 - By Tom Rosier (THR2)");
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setJMenuBar(jMB);
		setVisible(true);
		setEnabledComp(false);
		bAddToOrder.setEnabled(false);
		bPay.setEnabled(false);
		jMICloseTill.setEnabled(false);
		jLItems.addMouseListener(this);
		jLTotalOrder.addMouseListener(this);
	}
	/**Action Listener method for finding out what button was pressed*/
	public void actionPerformed(ActionEvent e)
	{
		//handles clicks on the mainmenu button
		if (e.getSource() == bMainMenu)
		{
			//sets the jlist to the mainmenu item to there items
			jLItems = new JList(ListItems.values());
			//set the jlist to the list previously
			jSPItems.getViewport().add(jLItems);
			//readd the action liserner to it as it gets destroyed when you create an new list
			jLItems.addMouseListener(this);
		} 
		//plus button event handerler
		else if (e.getSource() == bPlus)
		{
			//if the temp orderitem is not set to null then continue
			if (tempOrderItem != null)
			{
				//set the minus button to be true
				bMinus.setEnabled(true);
				//the orderitems quanitity gets edited by 1
				tempOrderItem.setQuantity(tempOrderItem.getQuantity() + 1);
				//update the order item list
				updateOrderItemList();
			}
		} 
		//event handerler for the minus button
		else if (e.getSource() == bMinus)
		{ 
			//if the temporderitem isnt null continue
			if (tempOrderItem != null)
			{
				// check quantity isnt less than 0 otherwise decreese the ammount by 1
				if (tempOrderItem.getQuantity() <= 0)
					tempOrderItem.setQuantity(0);
				else
					tempOrderItem.setQuantity(tempOrderItem.getQuantity() - 1);
				//update the orderitemlist for the end user to view
				updateOrderItemList();
				//if the quantity is equal to 1 then disable the minus button to prevent going lower than 0
				if (tempOrderItem.getQuantity() <= 1)
					bMinus.setEnabled(false);
				else
					bMinus.setEnabled(true);
			}
		} 
		//addto order button listener
		else if (e.getSource() == bAddToOrder)
		{
			//if order is set to null then create a new order object
			if (globalOrder == null)
				globalOrder = new Order();
			//if item already exists in a order then just update the quantity and if if fails create a new item
			if (globalOrder.updateItemQuantity(tempOrderItem.getItem(), tempOrderItem.getQuantity()) == false)
				globalOrder.addItem(tempOrderItem.getItem(), tempOrderItem.getQuantity());
			//set temp orderitem to null
			tempOrderItem = null;
			//update lists
			updateOrderList();
			updateOrderItemList();
		} 
		//event listener for paying
		else if (e.getSource() == bPay)
		{
			//if the text box text = customer name or nothing then show a message saying that the text is not valid
			if (jTBCustomers.getText().equals("Customers Name")||jTBCustomers.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null,"Please enter a 'VALID' Name before continuing");
			}
			else
			{
				//else set the customers name
				globalOrder.setCustomerName(jTBCustomers.getText());
				//create a new calculate chance form passing new information accross
				new ChangeCalcForm(globalOrder.getSubtotal().doubleValue(), globalTill, globalTillFrame, globalOrder, this);
			}
		} 
		//event listener for the cancel button
		else if (e.getSource() == bCancel)
		{
			//set the global order to be null
			globalOrder = null;
			//update the order list for people to see
			updateOrderList();
		} 
		//action listener for the save function
		else if (e.getSource() ==jMISaveTill)
		{
			jFCSaveDialog.setSelectedFile(new File("Till.XML"));
			//if ok on the form is pressed
	        if (jFCSaveDialog.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) 
	        {
	        	//save the till
	        	globalTill.save(jFCSaveDialog.getSelectedFile());
	        }
		}
		//listener for the load function to load the till back in
		else if (e.getSource() == jMILoadTill)
		{
			jFCSaveDialog.setSelectedFile(new File("Till.XML"));
			//if ok on the form is pressed
	        if (jFCSaveDialog.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) 
	        {
				//assign new till object
				globalTill = new Till();
				//create new order
				globalOrder = new Order();
				//enable components on the ui
				setEnabledComp(true);
				//disable paying
				bPay.setEnabled(false);
				//enable the ability to close the till
				jMICloseTill.setEnabled(true);
				//disable a few more components
				jMIStartNewTill.setEnabled(false);
				bMinus.setEnabled(false);
				bPlus.setEnabled(false);
				try
				{
					//set the global till to be the till loaded
					globalTill = Till.load(jFCSaveDialog.getSelectedFile());
					//update lists
					updateOrderList();
					updateOrderItemList();
					//update the global till display
					globalTillFrame.updateGlobalTillFrame(globalTill.getAlOrders());
					globalTillFrame.updateTotalsTotal(globalTill.getTotalForDay());
				} catch (IOException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
		}
		//listener for saving a Receipt for the end user to view
		else if (e.getSource() == jMSaveReceipt)
		{
			//gets the customer name and assigns it to the order
			globalOrder.setCustomerName(jTBCustomers.getText());
			//shows the select file dialog and sets the default file name to receipt.txt
			jFCSaveDialog.setSelectedFile(new File("Receipt.txt"));
			//if ok on the form is pressed
	        if (jFCSaveDialog.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) 
	        {
				try
				{
					//if the file already exists delete it
					if (jFCSaveDialog.getSelectedFile().exists())
						jFCSaveDialog.getSelectedFile().delete();
					//create a new buffer writter with the save dialogs save path
					BufferedWriter out = new BufferedWriter(new FileWriter(jFCSaveDialog.getSelectedFile()));
					//write to file
					out.write(globalOrder.getReceipt());
					//close the file
					out.close();
				} 
				catch (IOException e1)
				{
					e1.printStackTrace();
				}
	        } 
		}
		//program exit button
		else if (e.getSource() == jMIExit)
		{
			//terminates the program
			System.exit(0);
		}
		//this listener creates a new till
		else if (e.getSource() == jMIStartNewTill)
		{
			//assign new till object
			globalTill = new Till();
			//create new order
			globalOrder = new Order();
			//enable components on the ui
			setEnabledComp(true);
			//disable paying
			bPay.setEnabled(false);
			//enable the ability to close the till
			jMICloseTill.setEnabled(true);
			//disable a few more components
			jMIStartNewTill.setEnabled(false);
			bMinus.setEnabled(false);
			bPlus.setEnabled(false);
		}
		//closing till listener
		else if (e.getSource() ==jMICloseTill)
		{
			//clear a order
			setKillOrder();
			//disable componets
			setEnabledComp(false);
			//disable button
			jMICloseTill.setEnabled(false);
			//enable the ability to create a new till
			jMIStartNewTill.setEnabled(true);
		}
		//Listener to show the AboutUI
		else if (e.getSource() ==jMIAbout)
		{
			//Create an new instance of the about ui
			new AboutUI();
		} 
		//button to delete items from a order
		else if (e.getSource() == jMIDelete)
		{
			//check if a item contains discount and if its false then allow the person to edit the delete the order
			if (((String)jLTotalOrder.getSelectedValue()).contains("Discount") == false)
			{
				//call the delete order function on the order object
				globalOrder.deleteOrder(globalOrder.findOrderItem((String)jLTotalOrder.getSelectedValue()));
				//set temp order item to null
				tempOrderItem = null;
				//update lists
				updateOrderItemList();
				updateOrderList();
			}
		}
		//Listener for editing orders
		else if (e.getSource() == jMIEditOrder)
		{
			//if the selected value is not null then continue
			if (jLTotalOrder.getSelectedValue() != null)
			{
				//if the selected value is not a discount object then continue
				if (((String)jLTotalOrder.getSelectedValue()).contains("Discount") == false)
				{
					//find the selected object and put it to temp order
					tempOrderItem = globalOrder.findOrderItem((String)jLTotalOrder.getSelectedValue());
					//redraw the orderitemlist
					updateOrderItemList();
					//if quantity >1 enable the minus button
					if (tempOrderItem.getQuantity() > 1)
						bMinus.setEnabled(true);
				}
			}
		}
	}
	//method for quickly enabling and disabling components on the panel
	public void setEnabledComp(Boolean in)
	{
		bMainMenu.setEnabled(in);
		bPlus.setEnabled(in);
		bMinus.setEnabled(in);
		bPay.setEnabled(in);
		bCancel.setEnabled(in);
		jSPItems.setEnabled(in);
		jSPCurrent.setEnabled(in);
		jSPTotal.setEnabled(in);
		jTBCustomers.setEnabled(in);
		jLCustomer.setEnabled(in);
		jLTotal.setEnabled(in);
		//jLDiscount.setEnabled(in);
		jLaItems.setEnabled(in);
		jLaCurrentOrder.setEnabled(in);
		jMISaveTill.setEnabled(in);
		jMSaveReceipt.setEnabled(in);
		jLItems.setEnabled(in);
		jLCurrentOrder.setEnabled(in);
		jLTotalOrder.setEnabled(in);
		jLSubTotal.setEnabled(in);
	}
	//method for updating the order list on the ui
	public void updateOrderList()
	{
		try
		{
			//if global order isnt null then continue
			if (globalOrder != null)
			{
				//create a new jlist with the items from a global order
				jLTotalOrder = new JList(globalOrder.returnItemsInOrder());
				//set the set the text for the total label to the price formated to #0.##
				jLTotal.setText("Total: £" + (new DecimalFormat("#0.00")).format(globalOrder.getSubtotal()));
				//enable and disable components
				bPay.setEnabled(true);
				bMinus.setEnabled(false);
				bPlus.setEnabled(false);
			}
			else
			{
				//else create a new blank jlist to stop null exceptions;
				jLTotalOrder = new JList();
				//set the label to have a total or 0
				jLTotal.setText("Total: £0");
				//disable pay button
				bPay.setEnabled(false);
			}
			//set the scrollpanels object to be the jlist
			jSPTotal.getViewport().add(jLTotalOrder);
			//read the actionlistener to the list
			jLTotalOrder.addMouseListener(this);
		}
		catch (Exception ex)
		{
			System.err.println(ex.toString());
		}
	}
	/**The UpdateOrderItemList is a method for updating the jlist and jscrollpane on the gui element with the updated orderitems*/
	public void updateOrderItemList()
	{
		try
		{
			//if the temp order isnt null continue
			if (tempOrderItem != null)
			{
				//enable + button
				bPlus.setEnabled(true);
				//create a temporary array the same size as the quantity of the items
				Item[] temp = new Item[tempOrderItem.getQuantity()];
				//loop through and add the same thing for the amount of the quantity 
				for (int i = 0; i < tempOrderItem.getQuantity();i++)
					temp[i] = tempOrderItem.getItem();
				//set subtotal label to the price of the subtotal
				jLSubTotal.setText("SubTotal: £" + (new DecimalFormat("#0.00")).format(tempOrderItem.getOrderItemTotal()));
				//set the jlist to the new array
				jLCurrentOrder = new JList(temp);
				//enble order
				bAddToOrder.setEnabled(true);
			}
			else
			{
				//if the item is null create a new jlist to stop null pointers
				jLCurrentOrder = new JList();
				//disable the add to order button
				bAddToOrder.setEnabled(false);
			}
			jSPCurrent.getViewport().add(jLCurrentOrder);
		}
		catch (Exception ex)
		{
			System.err.println(ex.toString());
		}
	}
	/**This Method is for killing a order ready for a new order to be made*/
	public void setKillOrder() 
	{
		//set objects to be null
		globalOrder = null;
		tempOrderItem = null;
		//update ui lists
		updateOrderItemList();
		updateOrderList();
		//clear customer text
		jTBCustomers.setText("");
	}
	//mouse event handlers
	@Override
	public void mouseClicked(MouseEvent e)
	{
		//if double click is double click and the lists are enabled then continue
		if (e.getClickCount() == 2 && jLItems.isEnabled() == true && jLTotalOrder.isEnabled() == true)
		{
			//if the menus lists is clicked then continue
			if (e.getComponent() == jLItems)
			{
				// if the global order is null then create a new order
				if (globalOrder == null)
					globalOrder = new Order();
				//if selected value is drinks then set menu list to be drinks list and so on for sides and pizza
				if (jLItems.getSelectedValue() == ListItems.Drinks)
					jLItems = new JList(Drinks.values());
				else if (jLItems.getSelectedValue() == ListItems.Sides)
					jLItems = new JList(Sides.values());
				else if (jLItems.getSelectedValue() == ListItems.Pizzas)
					jLItems = new JList(Pizzas.values());
				else
				{
					//flag to check if to continue
					boolean cont = true;
					//loop through all pizza values if matches then add item to the global order and  set continue to false and break loop it also does this for sides and drinks
					for (int i = 0; i < Pizzas.values().length;i++)
						if (jLItems.getSelectedValue() == Pizzas.values()[i])
						{
							tempOrderItem = globalOrder.findOrderItem((Item)jLItems.getSelectedValue());
							cont = false;
							break;
						}
					if (cont)
						for (int i = 0; i < Sides.values().length;i++)
							if (jLItems.getSelectedValue() == Sides.values()[i])
							{
								tempOrderItem = globalOrder.findOrderItem((Item)jLItems.getSelectedValue());
								cont = false;
								break;
							}
					if (cont)
						for (int i = 0; i < Drinks.values().length;i++)
							if (jLItems.getSelectedValue() == Drinks.values()[i])
							{
								tempOrderItem = globalOrder.findOrderItem((Item)jLItems.getSelectedValue());
								cont = false;
								break;
							}
					jLItems = new JList(ListItems.values());
					updateOrderItemList();
				}
				//set the items panel to the new jlist items
				jSPItems.getViewport().add(jLItems);
				//read the action listener to it
				jLItems.addMouseListener(this);
			}
		}
	}
	//Unused methods {
	@Override
	public void mouseEntered(MouseEvent e){}
	@Override
	public void mouseExited(MouseEvent arg0){}
	@Override
	//}
	//on mouse pressed
	public void mousePressed(MouseEvent e)
	{
		//if the item clicked on is the items menu and the selected value is not a list item. if the selected item is not null then continue
		if (e.getComponent() == jLItems)
			if (jLItems.getSelectedValue() instanceof ListItems == false)
				if (jLItems.getSelectedValue() != null)
				{
					//set a jlabel to have the items description
					jLToolTip.setText(((Item)jLItems.getSelectedValue()).getDescription());
					//add the label to the jpopup menu
					jSHOW.add(jLToolTip);
					//show at coordinate of the mouse + 25
					jSHOW.show(e.getComponent(), e.getX() + 25, e.getY() + 25);
				}
	}
	@Override
	//on mouse release
	public void mouseReleased(MouseEvent e)
	{
		//if right click then show a jpopupmenu with item options in at coordinate of the mouse
		if (e.getButton() == MouseEvent.BUTTON3)
            jPM.show(e.getComponent(), e.getX(), e.getY());
	}
	//Set the till
	public void setOrder(Order globalOrder)
	{
		this.globalOrder = globalOrder;
	}
	public Till getTill()
	{
		return globalTill;
	}
}
