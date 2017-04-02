package noob.pwr;

import java.util.HashMap;

public class Warehouse {
	
	public Status status;
	public Position2D position;
	public HashMap<ProductName,Item> items;
	public HashMap<Integer,Truck> fleet;
	
	public Warehouse()
	{
		
	}
	
	public boolean check()
	{
		return true;
	}
	
	public Warehouse(Position2D position)
	{
		this.position = position;
		this.items = new HashMap<ProductName,Item>();
		this.fleet = new HashMap<Integer,Truck>();
		this.status = Status.Undefined;
	}
	
	public int numberOfTrucks()
	{
		return fleet.size();
	}
	
	public int numberOfProducts()
	{
		return items.size();
	}
	
	public void setStatus(Status status)
	{
		this.status = status;
	}
	
	public boolean ShouldCheck()
	{
		return status == Status.Undefined;
	}
	
	public void FillOrder(Order order)
	{
		for(Item item : order.items)
			getItemFromMagazine(item.getType(),item.getQuanity());
	}
	
	public Item getItemFromMagazine(ProductName type, int quanity)
	{
		if(!items.containsKey(type))
			return null;
		
		return items.get(type).getPartOfItem(quanity);
	}
	
	public boolean checkOrderInWarehouse(Order order)
	{
		for(Item item : order.items)
		{
			if(!checkItemInMagazine(item))
				return false;
		}
		return true;
	}
	
	public boolean checkItemInMagazine(Item item)
	{
		if(!items.containsKey(item.getType()))
			return false;
		
		return items.get(item.getType()).checkIfEnought(item.getQuanity());
	}
	
	public void addItems(Item[] toAdd)
	{
		for(Item item : toAdd)
		{
			addItem(item);
		}
	}
	
	public void addItem(Item item)
	{
		if(items.containsKey(item.getType()))
		{
			items.get(item.getType()).addToItem(item.getQuanity());
		}
		else
		{
			items.put(item.getType(), item);
		}
	}
	
	
	
}
