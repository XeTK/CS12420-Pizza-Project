package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;
/**Pizza is a list of items that interface with items*/
public enum Pizzas implements Item
{
	//deleration of all the pizzas that can be used Pizza Name, Price, Description
	Personal_Pepperoni(new BigDecimal(6),"Personal Pepperoni Pizza"),
	Small_Pepperoni(new BigDecimal(9),"Snall Pepperoni Pizza"),
	Medium_Pepperoni(new BigDecimal(14),"Medium Pepperoni Pizza"),
	Large_Pepperoni(new BigDecimal(16),"Large Pepperoni Pizza"),
	Personal_Chicken_And_Mushrooms(new BigDecimal(6),"Personal Chicken And Mushroom Pizza"),
	Small_Chicken_And_Mushrooms(new BigDecimal(9),"Small Chicken And Mushroom Pizza"),
	Medium_Chicken_And_Mushrooms(new BigDecimal(14),"Medium Chicken And Mushroom Pizza"),
	Large_Chicken_And_Mushrooms(new BigDecimal(16),"Large Chicken And Mushroom Pizza"),
	Personal_Ham_And_Pineapple(new BigDecimal(6),"Personal Ham And Pineapple Pizza"),
	Small_Ham_And_Pineapple(new BigDecimal(9),"Small Ham And Pineapple Pizza"),
	Medium_Ham_And_Pineapple(new BigDecimal(14),"Medium Ham And Pineapple Pizza"),
	Large_Ham_And_Pineapple(new BigDecimal(16),"Large Ham And Pineapple Pizza"),
	Personal_Ham_And_Bacon(new BigDecimal(6),"Personal Ham And Bacon"),
	Small_Ham_And_Bacon(new BigDecimal(9),"Small Ham And Bacon"),
	Medium_Ham_And_Bacon(new BigDecimal(14),"Medium Ham And Bacon"),
	Large_Ham_And_Bacon(new BigDecimal(16),"Large Ham And Bacon"),
	Personal_Vegetarian(new BigDecimal(6),"Personal Vegetarian"),
	Small_Vegetarian(new BigDecimal(9),"Small Vegetarian"),
	Medium_Vegetarian(new BigDecimal(14),"Medium Vegetarian"),
	Large_Vegetarian(new BigDecimal(16),"Large Vegetarian");
	//Decleration of Description and price
	private String description = "";
	private BigDecimal price = new BigDecimal(0);
	//Custom Constructor for the items.
	/**Default constructor with price and string values*/
	private Pizzas(BigDecimal price, String description)
	{
		this.price = price;
		this.description = description;
		
	}
	Pizzas(){}
	//Getters and setters for the class {
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
	//} end getter and setters

}
