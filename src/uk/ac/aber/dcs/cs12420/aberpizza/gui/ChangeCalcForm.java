package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Order;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Till;
/**This GUI Element is for calculating the change that is due for the customer*/
public class ChangeCalcForm extends JFrame implements ActionListener
{
	//declares new jpanel followed and component {
	private JPanel 
					jP = new JPanel();
	private JTextField 
					jTFTender = new JTextField();
	private JLabel
					jLTender = new JLabel("Enter Tender Given"),
					jLChangeDue = new JLabel("Total Change Due : £0.00");
	private JButton
					jBPay = new JButton("Pay");
	private JList
					jLiCurrencyDue = new JList();
	private JScrollPane
					jSPCurrencyDue = new JScrollPane();
	private double 
					totalPrice = 00.00;
	private Till
					globalTill;
	private TillFrame 
					globalTillFrame;
	private Order
					globalOrder;
	private MainFrame
					globalFM;
	//}
	/**The Default constructor sets up the the layout and size of objects, it also imports the objects for the previous forms*/
	public ChangeCalcForm(double totalPrice, Till globalTill, TillFrame globalTillFrame, Order globalOrder, MainFrame globalFM)
	{
		//pass values in from other parts of the program 
		this.totalPrice = totalPrice;
		this.globalTill = globalTill;
		this.globalTillFrame = globalTillFrame;
		this.globalOrder = globalOrder;
		this.globalFM = globalFM;
		//set layout to nothing
		jP.setLayout(null);
		//add the panel to the frame
		add(jP);
		
		//set size of components
		jTFTender.setSize(new Dimension(230,25));
		jLTender.setSize(new Dimension(230,25));
		jLChangeDue.setSize(new Dimension(230,25));
		jSPCurrencyDue.setSize(new Dimension(230,85));
		jBPay.setSize(new Dimension(230,25));
		//set location of the components
		jTFTender.setLocation(10, 35);
		jLTender.setLocation(10, 10);
		jLChangeDue.setLocation(10, 70);
		jSPCurrencyDue.setLocation(10, 95);
		jBPay.setLocation(10, 190);
		
		//add the jlist to the scrollpanel
		jSPCurrencyDue.getViewport().add(jLiCurrencyDue);
		
		//add tooltips
		jTFTender.setToolTipText("Enter Tender Given and Press Enter");
		
		//add components to the jpanel
		jP.add(jTFTender);
		jP.add(jLTender);
		jP.add(jLChangeDue);
		jP.add(jSPCurrencyDue);
		jP.add(jBPay);
		
		//add actionliserners
		jTFTender.addActionListener(this);
		jBPay.addActionListener(this);
		//set button to be false
		jBPay.setEnabled(false);
		//lock size of the form
		setMinimumSize(new Dimension(250,255));
		setMaximumSize(new Dimension(250,255));
		setSize(250,255);
		setResizable(false);
		//set up rest of form
		setTitle("Calculate Change");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	/**action listeners for the textbox and button*/
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		//action beformed for textbox
		if (arg0.getSource() == jTFTender)
		{
			//check if the text is either numbers or.
			if (jTFTender.getText().matches("[0-9.].*"))
			{
				//check if the tender given is greater from total price
				if (Double.parseDouble(jTFTender.getText()) < totalPrice)
				{
					//display a message saying theres not enough tender and to enter a larger ammount
					JOptionPane.showMessageDialog(null,"Tender given is Not Enough. Customer is £" + (new DecimalFormat("#0.00")).format((totalPrice - Double.parseDouble(jTFTender.getText()))) + " Short!!" );
					jBPay.setEnabled(false);
				}
				else
				{
					//sets the text of the totalcharge for the customer
					jLChangeDue.setText("Total Change Due : £" + (new DecimalFormat("#0.00")).format((Double.parseDouble(jTFTender.getText()) - totalPrice)));
					//create a new jlist with the values for the change and quantities
					jLiCurrencyDue = new JList(calculateChange(Double.parseDouble(jTFTender.getText()), totalPrice));
					//add jlist to scrollpanel
					jSPCurrencyDue.getViewport().add(jLiCurrencyDue);
					jBPay.setEnabled(true);
				}
			}
			//throws message if anything but numbers is entered
			else
				JOptionPane.showMessageDialog(null,"Currency can only be 'Numbers'");
		}
		//action lisner for the pay button
		else if (arg0.getSource() == jBPay)
		{
			//add order to the global till
			globalTill.addOrder(globalOrder);
			//set the global order to be null
			JOptionPane.showMessageDialog(null, globalOrder.getReceipt());
			globalOrder = null;
			//update the lists
			globalTillFrame.updateTotalsTotal(globalTill.getTotalForDay());
			globalTillFrame.updateGlobalTillFrame(globalTill.getOrders());
			//update frame lists
			globalFM.updateOrderList();
			globalFM.setKillOrder();
			//kill the form
			dispose();
		}
	}
	/**This Method is used for caluclating the right ammount of change to give to the customer*/
	private Object[] calculateChange(Double Tender, Double Price)
	{
		//minus the tender from the price
		double change = Tender - Price;
		//create a new string arraylist
		ArrayList<String> tempAL = new ArrayList<String>();
		//set i to 0 
		int i = 0;
		//loop till change is not higher than the note value
		while (change >= 50.00)
		{
			//++ count
			i++;
			//minus the note from the change
			change = change - 50.00;
		}
		//if i greater than 0 add new item to the array
		if (i > 0)
			tempAL.add(i + " X £50 Note");
		//reset and loop over again and again and again
		i = 0;
		while (change >= 20.00)
		{
			i++;
			change = change - 20.00;
		}
		if (i > 0)
			tempAL.add(i + " X £20 Note");
		i = 0;
		while (change >= 10.00)
		{
			i++;
			change = change - 10.00;
		}
		if (i > 0)
			tempAL.add(i + " X £10 Note");
		i = 0;
		while (change >= 5.00)
		{
			i++;
			change = change - 5.00;
		}
		if (i > 0)
			tempAL.add(i + " X £5 Note");
		i = 0;
		while (change >= 2.00)
		{
			i++;
			change = change - 2.00;
		}
		if (i > 0)
			tempAL.add(i + " X £2 Coin");
		i = 0;
		while (change >= 1.00)
		{
			i++;
			change = change - 1.00;
		}
		if (i > 0)
			tempAL.add(i + " X £1 Coin");
		i = 0;
		while (change >= 0.50)
		{
			i++;
			change = change - 0.50;
		}
		if (i > 0)
			tempAL.add(i + " X 50p Coin");
		i = 0;
		while (change >= 0.20)
		{
			i++;
			change = change - 0.20;
		}
		if (i > 0)
			tempAL.add(i + " X 20p Coin");
		i = 0;
		while (change >= 0.10)
		{
			i++;
			change = change - 0.10;
		}
		if (i > 0)
			tempAL.add(i + " X 10p Coin");
		i = 0;
		while (change >= 0.05)
		{
			i++;
			change = change - 0.05;
		}
		if (i > 0)
			tempAL.add(i + " X 5p Coin");
		i = 0;
		while (change >= 0.02)
		{
			i++;
			change = change - 0.02;
		}
		if (i > 0)
			tempAL.add(i + " X 2p Coin");
		i = 0;
		while (change >= 0.01)
		{
			i++;
			change = change - 0.01;
		}
		if (i > 0)
			tempAL.add(i + " X 1p Coin");
		i = 0;
		//return arraylist of strings
		return tempAL.toArray();
 	}
	
}
