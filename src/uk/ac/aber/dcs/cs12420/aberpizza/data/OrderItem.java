package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;
/**the order item is a wrapper for the item and quantity that then is held within order's array*/
public class OrderItem
{
	//delcare information about the item
	private int quantity;
	private Item item;
	//default constructor for the class setting its data
	/**Default constructor passing in Item and Quantity*/
	public OrderItem(Item item, int quantity)
	{
		this.item = item;
		this.quantity = quantity;
	}
	public OrderItem(){}
	public void setItem(Item item)
	{
		this.item = item;
	}
	// { Getter Setters for the information
	public Item getItem()
	{
		return item;
	}
	public int getQuantity()
	{
		return quantity;
	}
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
	// }
	/**return the orders total price by multiplying the items price by the quantity*/
	public BigDecimal getOrderItemTotal()
	{
		return item.getPrice().multiply(new BigDecimal(quantity));
	}
}
