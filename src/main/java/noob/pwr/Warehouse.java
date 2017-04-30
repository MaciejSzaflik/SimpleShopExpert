package noob.pwr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Warehouse {
	
	public Status status;
	public Position2D position;
	public HashMap<ProductName,Item> items;
	public HashMap<Integer,Truck> fleet;
	
	public Set<ProductName> whatCannotBeSold;
	public TripleState isFoodIsolated;
	public TripleState isThereSecurity;
	public TripleState isThereContaminationProgram;
	public TripleState isPossibleToClean;
	
	public TripleState meatCheck;
	
	public TripleState alcholCheck;
	public TripleState designatedLiquorSpace;
	public TripleState licenseSaleLiquor;
	public TripleState haveFederalEmployerIdentification;
	public TripleState TTBPermit;
	
	public TripleState pyroPermit;
	public TripleState isPyroMarked;
	public TripleState saveFromSparks;
	public TripleState necEletric;
	public TripleState areSwichesOutside;
	
	public TripleState isElectricDocumented;
	public TripleState monitoringOfTemperature;
	
	public TripleState fishCertified;
	public TripleState eggCertified;
	public TripleState meatCertified;
	
	public TripleState alcoholCheck;
	public TripleState eggCheck;
	public TripleState fishCheck;
	public TripleState pyroCheck;
	
	public TripleState globalMeatCheck;
	
	
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
		ClearAllChecks();
	}
	
	public void ClearAllChecks()
	{
		this.status = Status.Undefined;
		this.whatCannotBeSold = new HashSet<ProductName>();

		this.meatCheck = TripleState.Undefined;
		this.alcoholCheck = TripleState.Undefined;
		this.pyroCheck = TripleState.Undefined;
		this.eggCheck = TripleState.Undefined;
		this.fishCheck = TripleState.Undefined;
		this.globalMeatCheck = TripleState.Undefined;
	}
	
	public boolean CanSell(ProductName check)
	{
		return whatCannotBeSold.contains(check);
	}
	
	public void AddRestrictedProduct(ProductName toAdd)
	{
		whatCannotBeSold.add(toAdd);
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
	
	public boolean FillOrder(Order order)
	{
		if(!checkOrderInWarehouse(order))
			return false;
		
		for(Item item : order.items)
		{
			getItemFromMagazine(item.getType(),item.getQuanity());
		}
		order.filled = true;
		return true;
	}
	
	public Item getItemFromMagazine(ProductName type, int quanity)
	{
		if(!items.containsKey(type))
			return null;
		
		return items.get(type).getPartOfItem(quanity);
	}
	
	public boolean checkOrderInWarehouse(Order order)
	{
		if(order.items == null)
			return false;
		
		for(Item item : order.items)
		{
			if(checkItemInMagazine(item))
				continue;
			else
				return false;		
		}
		
		return true;
	}
	
	public boolean checkItemInMagazine(Item item)
	{
		if(!items.containsKey(item.getType()) || this.whatCannotBeSold.contains(item.getType()))
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
	
	public void addTrucks(Truck[] toAdd)
	{
		for(Truck truck : toAdd)
		{
			addTruck(truck);
		}
	}
	
	public void addTruck(Truck truck)
	{
		if(!fleet.containsKey(truck.id))
		{
			fleet.put(truck.id,truck);
		}
	}
	
	
	
}
