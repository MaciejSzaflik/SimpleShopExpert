package noob.pwr;

import java.util.List;
import java.util.ArrayList;

public class Order {
	public static int idGenerator = 0;
	public int id;
	public OrderStatus status;
	public String decision;
	public List<Item> items;
	public long timeStamp;
	public int shopId = -1;
	
	public Order(Item[] order)
	{
		id = idGenerator++;
		decision = "alfa";
		status = OrderStatus.Undecided;
		items = new ArrayList<Item>();
		for(Item item : order)
		{
			items.add(item);
		}
	}
	
	public int getSize()
	{
		int size = 0;
		for(Item item : items)
		{
			size+=item.getWeight();
		}
		return size;
	}
	
	public boolean isRespondedSet()
	{
		return shopId >= 0;
	}
	
	public void printStatus(String ruleName)
	{
		System.out.println(ruleName + ": " + id + " is " + status);
	}
	
	public boolean ShouldCheck()
	{
		return status == OrderStatus.Undecided;
	}
	
	public boolean CheckIfItemsValid()
	{
		for(Item item : items)
		{
			if(!item.IsValid())
				return false;
		}
		
		return true;
		
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
