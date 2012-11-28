package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;
/**The Item interface is for sub items to interface from*/
public interface Item
{
	//Variables
	BigDecimal price = new BigDecimal(0);
	String description = "";
	//Methods
	BigDecimal getPrice();
	void setPrice(BigDecimal price);
	String getDescription();
	void setDescription(String description);
}
