package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;
/**This Enum holds the side orders*/
public enum Sides implements Item
{
	// Name Price Description
	Chips(new BigDecimal(1.88),"Chips"),
	Garlic_Bread(new BigDecimal(1.00),"Garlic Bread"),
	Potato_Wedges(new BigDecimal(1.44),"Porato Wedges"),
	Colesslaw(new BigDecimal(1.00),"Colesslaw");
	//the variables for the sides are instanciated below for description and price
	private String description = "";
	private BigDecimal price = new BigDecimal(0);
	//Custom Constructor for sides
	/**constructor for the class detailing the price and detail*/
	private Sides(BigDecimal price, String description)
	{
		this.price = price;
		this.description = description;
	}
	Sides(){}
	//Getter and setters {
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
	// }

}
