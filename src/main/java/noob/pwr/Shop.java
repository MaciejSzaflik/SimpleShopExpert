package noob.pwr;

public class Shop {
	public static int idGenerator = 0;
	public int id;
	public Position2D position;
	
	public TripleState washHandsPossible;
	public TripleState deliveryOfFoodPossible;
	public TripleState haveCooler;
	
	public Shop(Position2D position)
	{
		id = idGenerator++;
		this.position = position;
		ClearCheck();
		
		washHandsPossible = TripleState.Good;
		haveCooler = TripleState.Good;
	}
	
	public void ClearCheck()
	{
		deliveryOfFoodPossible = TripleState.Undefined;
	}
	
	public boolean CheckIfCanSellThis(Order order)
	{
		if(deliveryOfFoodPossible != TripleState.Bad)
			return true;
		
		for(Item item : order.items)
		{
			if(ProductName.isThisFood(item.getType()))
				return false;
		}
		
		return true;
	}
}
