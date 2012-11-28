package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
/**The Order Class is a object for holding OrderItems for a set customer*/
public class Order
{
	//the order holds a date
	private Date date;
	//the customers name
	private String customerName = "";
	//and a list of orderitems
	private ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
	/**Default Order Constructor sets a new date on creation of the order*/
	public Order()
	{
		//of creation of the order it adds the current date
		this.date = new Date();
	}
	//getcustomer name
	public String getCustomerName()
	{
		return this.customerName;
	}
	//set customers name
	public void setCustomerName(String customerName)
	{
		this.customerName = customerName;
	}
	//get date
	public Date getDate()
	{
		return date;
	}
	public void setDate(Date date)
	{
		this.date = date;
	}
	/**Add new Item to a order*/
	public void addItem(Item item, int quantity) 
	{
		orderItems.add(new OrderItem(item,quantity));
	}
	/**Update A Previously made items */
	public boolean updateItemQuantity(Item item, int quantity)
	{
		//loops through all the items
		for (int i = 0; i < orderItems.size();i++)
		{
			//gets the item and compairs it to the item that you want to update
			if (orderItems.get(i).getItem().equals(item))
			{
				//if sucessfull sets the new quantity
				orderItems.get(i).setQuantity(quantity);
				//return a true value to say its completed
				return true;
			}
		}
		//return false if it couldnt update the value
		return false;
	}
	/**Remove Item from Order*/
	public void deleteOrder(OrderItem tempOrder)
	{
		//passed in order item is searched for and removed this is handled by the arraylist api
		orderItems.remove(tempOrder);
	}
	public void setOrderItems(ArrayList<OrderItem> orderItems)
	{
		this.orderItems = orderItems;
	}
	//get arraylist of orders
	public ArrayList<OrderItem> getOrderItems()
	{
		return orderItems;
	}
	/**method for returning the order item from a string given*/
	public OrderItem findOrderItem(String inString)
	{
		//loop through all the order items
		for (int i =0;i< orderItems.size();i++)
		{
			//gets the quantity from the string given
			int quantity = Integer.parseInt(inString.substring(0,String.valueOf(orderItems.get(i).getQuantity()).length()).replace(" ", ""));
			//gets the price from the string given
			double price = Double.parseDouble(inString.substring((inString.length() - (String.valueOf(orderItems.get(i).getOrderItemTotal().doubleValue()).replace(".00", ".0").replace(".", "0.")).length()), inString.length()).replace("£", ""));
			//after working out the quantity and price it substracts them from the string just leaving the name of the item
			String itemName = inString.substring(String.valueOf(quantity).length() + 3 , inString.length() - (String.valueOf(price).length() + 2)).replace(" ", "");
			//if the strings match the a item already existing in the array it returns the 1 at the given index
			if (orderItems.get(i).getQuantity() == quantity && String.valueOf(orderItems.get(i).getItem()).equals(itemName)&& orderItems.get(i).getOrderItemTotal().doubleValue() == price)
				return orderItems.get(i);
		}
		//else return null
		return null;
	}
	/**finding orderitem from a item given*/
	public OrderItem findOrderItem(Item inItem)
	{
		// loop through all, check if anything matches the item required if it exists return it
		for (int i =0;i < orderItems.size();i++)
			if (orderItems.get(i).getItem().equals(inItem))
				return orderItems.get(i);
		// else create a new order item
		return new OrderItem(inItem,1);
	}
	/**The method below returns a bigdouble with the subtotal in it*/
	public BigDecimal getSubtotal()
	{
		//temp big decimal to hold the total before it was returned
		BigDecimal total = new BigDecimal(0);
		//loop through all the values and get the combined total of all the items
		for (int i = 0;i < orderItems.size();i++)
			total = total.add(orderItems.get(i).getOrderItemTotal());
		//minus the discounts from the total price
		total = total.subtract(getDiscount());
		//return the ttoal
		return total;
	}
	/**This method gets the discounts for all the object then returns them*/
	public BigDecimal getDiscount()
	{
		//create a new bigdecimal
		BigDecimal tempDiscount = new BigDecimal(0);
		//get list of discounts for things to check agenst
		Discounts[] tempDiscounts = Discounts.values();
		//loop through all the orderitems to check if they apply for a discount
		for (int i = 0;i < orderItems.size();i++)
			for (int j = 0;j < tempDiscounts.length;j++)
				if (orderItems.get(i).getItem() == tempDiscounts[j].getItem())
					if (orderItems.get(i).getQuantity() >= tempDiscounts[j].getQuantity())
						for (int k = 0; k < (orderItems.get(i).getQuantity() / tempDiscounts[j].getQuantity()) ;k++)
							tempDiscount = tempDiscount.add(new BigDecimal(tempDiscounts[j].getPrice()));
		//return discount ammount at the end
		return tempDiscount;
	}
	/**The Method below returns a string with all the receipt information in it*/
	public String getReceipt()
	{
		//set up a decimal format to use for formating the strings
		DecimalFormat f = new DecimalFormat("#0.00");
		//create a new temp string holding the date and a new line + customer name and a new line
		String tmpstr = date.toString() + "\nCustomer: " + getCustomerName() + "\n";
		//loop through all the orders and add them to the string
		for (int i = 0;i< orderItems.size();i++)
			tmpstr += orderItems.get(i).getQuantity() + " X " + orderItems.get(i).getItem().getDescription() + " £" + f.format(orderItems.get(i).getItem().getPrice().doubleValue()) + " Total: £" + f.format(orderItems.get(i).getOrderItemTotal().doubleValue()) + "\n";
		
		//add the final price to the end of the string formated correctly
		tmpstr += "Total: £" + (new DecimalFormat("#0.00")).format(getSubtotal().doubleValue());
		//return the string
		return tmpstr;
	}
	/**The method below returns a object list of the value of a string saying the objects held within the class*/
	public Object[] returnItemsInOrder()
	{
		//create arraylist of strings
		ArrayList<String> temp = new ArrayList<String>();
		//loop through orderitem lists and add the item to string to the list
		for (int i = 0; i < orderItems.size();i++)
			temp.add(orderItems.get(i).getQuantity() + " X " + orderItems.get(i).getItem()  + " £" + (new DecimalFormat("#0.00")).format(orderItems.get(i).getOrderItemTotal()));
		//get discount value
		Double discount = getDiscount().doubleValue();
		//if greater than 0 then add it to the end of the list
		if (discount > 0)
			temp.add("Discount: -£" + new DecimalFormat("#0.00").format(discount));
		//return arraylist to array so it cannot be made any larger
		return temp.toArray();
	}
}
