package noob.pwr;

import java.util.List;
import java.util.ArrayList;

public class Order {
	public String desicion;
	public List<Item> items;
	
	public Order(Item[] order)
	{
		desicion = "alfa";
		items = new ArrayList<Item>();
		for(Item item : order)
		{
			items.add(item);
		}
	}

	public Order() {
		// TODO Auto-generated constructor stub
	}
}
