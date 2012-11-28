package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;
/**Drinks is a list of Enums that interfaces with Item*/
public enum Drinks implements Item
{
	// list {
	// Name of product, Price, Description
	CokaCola(new BigDecimal(1.33),"CokaCola"),
	Pepsi(new BigDecimal(1.22),"Pepsi"),
	Fanta(new BigDecimal(1.33),"Fanta"),
	DrPepper(new BigDecimal(1.33),"DrPepper"),
	Se7enUp(new BigDecimal(1.11),"Se7enUp"),
	LemonAde(new BigDecimal(0.88),"LemonAde"),
	OrangeJuice(new BigDecimal(0.88),"OrangeJuice"),
	Water(new BigDecimal(99.99),"the thing that springs from the earth");
	
	
	
	
	
	// } list
	
	// for custom constructor
	private BigDecimal price = new BigDecimal(0);
	private String description = "";
	/**The Default Constructor for Price And Description on a drink object*/
	private Drinks(BigDecimal price,String description)
	{
		this.price = price;
		this.description = description;
	}
	Drinks(){}
	@Override
	public BigDecimal getPrice()
	{
		return price;
	}
	@Override
	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}
	@Override
	public String getDescription()
	{
		return description;
	}
	@Override
	public void setDescription(String description)
	{
		this.description = description;
	}

}
