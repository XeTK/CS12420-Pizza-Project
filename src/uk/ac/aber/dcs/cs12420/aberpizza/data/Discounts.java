package uk.ac.aber.dcs.cs12420.aberpizza.data;
/**this enum holds the discounts for the items*/
public enum Discounts
{
	// discounts are held within this enum
	//special((new Item[1]{Pizzas.Large_Chicken_And_Mushrooms}), 1, "", Pizzas.Large_Chicken_And_Mushrooms),
	fiftypercentoff(Pizzas.Large_Chicken_And_Mushrooms, 1, "50% Off", Pizzas.Large_Chicken_And_Mushrooms.getPrice().doubleValue() / 2),
	threeforone(Drinks.DrPepper, 3, "3 4 1", (Drinks.DrPepper.getPrice().doubleValue()));
	private Item item;
	private int quantity;
	private String description;
	private double price;
	//custom constructor
	/**Default Constructor for the item quanity description and price after editing*/
	private Discounts(Item item, int quantity, String description, double price)
	{
		this.item = item;
		this.quantity = quantity;
		this.description = description;
		this.price = price;
	}
	//getter and setters {
	public Item getItem()
	{
		return item;
	}
	public int getQuantity()
	{
		return quantity;
	}
	public String getDescription()
	{
		return description;
	}
	public double getPrice()
	{
		return price;
	}
	//} 
}
