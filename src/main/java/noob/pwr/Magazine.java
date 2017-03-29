package noob.pwr;

import java.util.HashMap;

public class Magazine {
	public Position2D position;
	public HashMap<String,Item> items;
	
	public Magazine()
	{
		
	}
	
	public boolean check()
	{
		return true;
	}
	
	public Magazine(Position2D position)
	{
		this.position = position;
		this.items = new HashMap<String,Item>();
	}
	
	public Item getItemFromMagazine(String type, int quanity)
	{
		if(!items.containsKey(type))
			return null;
		
		return items.get(type).getPartOfItem(quanity);
	}
	
	public boolean checkOrderInMagazine(Order order)
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
