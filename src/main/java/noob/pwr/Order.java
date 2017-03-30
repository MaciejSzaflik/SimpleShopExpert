package noob.pwr;

import java.util.List;
import java.util.ArrayList;

public class Order {
	public String decision;
	public List<Item> items;
	
	public Order(Item[] order)
	{
		decision = "alfa";
		items = new ArrayList<Item>();
		for(Item item : order)
		{
			items.add(item);
		}
	}

	public Order() {
		// TODO Auto-generated constructor stub
	}
	
	public void setDecision(String decision)
	{
		this.decision = decision;
	}
	
	public int getNumberOfItems()
	{
		return items.size();
	}
}
