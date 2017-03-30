package noob.pwr;

import java.util.HashMap;

public class ScheduleMaker {
	public Magazine mainMagazine;
	public HashMap<Integer,Truck> trucks;
	public HashMap<Integer,Shop> shops;
	public HashMap<Integer,Order> requestedOrders;
	
	
	public ScheduleMaker()
	{
		
	}
	
	public void Initalize(
			Magazine magazine,
			Truck[] vehicles,
			Shop[] requestsPoints,
			Order[] orders)
	{
		mainMagazine = magazine;
				
		trucks = new HashMap<Integer,Truck>();
		shops = new HashMap<Integer,Shop>();
		requestedOrders = new HashMap<Integer,Order>();
		
		for(Truck truck : vehicles)
		{
			if(!trucks.containsKey(truck.id))
				trucks.put(truck.id, truck);
		}
		
		for(Shop shop : requestsPoints)
		{
			if(!shops.containsKey(shop.id))
				shops.put(shop.id, shop);
		}
		
		for(Order order : orders)
		{
			if(!requestedOrders.containsKey(order.id))
				requestedOrders.put(order.id, order);
		}
	}
	
	
	
}
