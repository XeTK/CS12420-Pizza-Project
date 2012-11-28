package uk.ac.aber.dcs.cs12420.aberpizza.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Drinks;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Order;
import uk.ac.aber.dcs.cs12420.aberpizza.data.OrderItem;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Pizzas;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Sides;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Till;

public class BaseTest
{
	@Test
	public void getItemDescription()
	{
		assertEquals("Descript not right","CokaCola",Drinks.CokaCola.getDescription());
	}
	@Test
	public void getItemPrice()
	{
		assertEquals("Price not right",new BigDecimal(1.33),Drinks.CokaCola.getPrice());
	}
	@Test
	public void setItemPrice()
	{
		assertEquals("Price not right",new BigDecimal(1.33),Drinks.CokaCola.getPrice());
		Drinks.CokaCola.setPrice(new BigDecimal(9.99));
		assertEquals("Price not right",new BigDecimal(9.99),Drinks.CokaCola.getPrice());
	}
	@Test
	public void setItemDescription()
	{
		assertEquals("Descript not right","CokaCola",Drinks.CokaCola.getDescription());
		Drinks.CokaCola.setDescription("LOLTest");
		assertEquals("Descript not right","LOLTest",Drinks.CokaCola.getDescription());
	}
	@Test
	public void addToOrderItem()
	{
		OrderItem oi = new OrderItem(Drinks.CokaCola,1);
		assertEquals("Item not added correctly",Drinks.CokaCola,oi.getItem());
	}
	@Test
	public void changeOrderItemQuantity()
	{
		OrderItem oi = new OrderItem(Drinks.CokaCola,1);
		assertEquals("Item Quantity Incorrect",1,oi.getQuantity());
		oi.setQuantity(9);
		assertEquals("Item Quantity Incorrect",9,oi.getQuantity());
	}
	@Test
	public void addDrink()
	{
		Order oi = new Order();
		oi.addItem(Drinks.CokaCola, 1);
		assertEquals("Drink Not Added Correctly!",Drinks.CokaCola,oi.findOrderItem(Drinks.CokaCola).getItem());
	}
	@Test
	public void addPizza()
	{
		Order oi = new Order();
		oi.addItem(Pizzas.Large_Ham_And_Bacon, 1);
		assertEquals("Pizza Not Added Correctly!",Pizzas.Large_Ham_And_Bacon,oi.findOrderItem(Pizzas.Large_Ham_And_Bacon).getItem());
	}
	@Test
	public void addSide()
	{
		Order oi = new Order();
		oi.addItem(Sides.Chips, 1);
		assertEquals("Sides Not Added Correctly!",Sides.Chips,oi.findOrderItem(Sides.Chips).getItem());
	}
	@Test
	public void changeQuantity()
	{
		Order oi = new Order();
		oi.addItem(Drinks.CokaCola, 1);
		assertEquals("Quantity incorrect",1,oi.findOrderItem(Drinks.CokaCola).getQuantity());
		oi.updateItemQuantity(Drinks.CokaCola, 3);
		assertEquals("Quantity incorrect",3,oi.findOrderItem(Drinks.CokaCola).getQuantity());
	}
	@Test
	public void deleteOrder()
	{
		Order oi = new Order();
		oi.addItem(Drinks.CokaCola,1);
		assertEquals("item dosnt extist",Drinks.CokaCola,oi.findOrderItem((String)oi.returnItemsInOrder()[0]).getItem());
		oi.deleteOrder(oi.findOrderItem(Drinks.CokaCola));
		assertEquals("Item still existed", Drinks.CokaCola, oi.findOrderItem(Drinks.CokaCola).getItem());
	}
	@Test
	public void checkDiscounts()
	{
		 Order oi = new Order();
		 oi.addItem(Drinks.DrPepper, 3);
		 Double totalPrice = (Drinks.DrPepper.getPrice().doubleValue() * 3 - 1.33),
				 endPrice = oi.getSubtotal().doubleValue();
		 assertEquals("Price souldnt be the same",totalPrice,endPrice);
	}
	@Test
	public void setCustomer()
	{
		Order oi = new Order();
		oi.setCustomerName("Tom");
		assertEquals("Name isnt correct","Tom",oi.getCustomerName());
	}
	@Test
	public void addOrdertoTill()
	{
		Order oi = new Order();
		oi.addItem(Drinks.CokaCola, 3);
		oi.addItem(Drinks.DrPepper, 3);
		oi.setCustomerName("Tom");
		Till ti = new Till();
		ti.addOrder(oi);
		assertEquals("Object not equal", oi,ti.getOrders().toArray()[0]);
	}
	@Test
	public void getTotalForTheDay()
	{
		Order oi = new Order();
		oi.addItem(Drinks.CokaCola, 3);
		oi.addItem(Drinks.DrPepper, 3);
		Till ti = new Till();
		ti.addOrder(oi);
		assertEquals("Total Not Correct",oi.getSubtotal(),ti.getTotalForDay());
	}
	@Test
	public void saveLoad()
	{
		Order oi = new Order();
		oi.addItem(Drinks.CokaCola, 3);
		oi.addItem(Drinks.DrPepper, 3);
		Till ti = new Till();
		ti.addOrder(oi);
		ti.save(new File("Hello.xml"));
		Till ti1 = new Till();
		try
		{
			ti1 = Till.load(new File("Hello.xml"));
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("Objects dont match",ti.getOrders().get(0).getOrderItems().get(0).getItem(),ti1.getOrders().get(0).getOrderItems().get(0).getItem());
	}
}
