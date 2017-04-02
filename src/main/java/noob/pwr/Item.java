package noob.pwr;

public class Item {
	private ProductName type;
	private int quanity;
	
	public Item(ProductName type, int quanity)
	{
		this.type = type;
		this.quanity = quanity;
	}
	
	public boolean checkType(ProductName toCompare)
	{
		return toCompare.equals(getType());
	}
	
	public boolean IsValid()
	{
		return type !=null && quanity > 0;
	}
	
	public int getWeight()
	{
		return ProductWeight.getWeightOfProduct(getType(), getQuanity());
	}
	
	public boolean checkIfEnought(int itemCount)
	{
		return quanity >= itemCount;
	}
	
	public boolean canUseThatItem(Item item)
	{
		return checkType(item.type) && checkIfEnought(item.quanity);
	}
	
	public void addToItem(int count)
	{
		this.quanity+=count;
	}
	
	public void useItem(int count)
	{
		if(checkIfEnought(count))
			quanity-=count;
	}
	
	public Item getPartOfItem(int count)
	{
		if(!checkIfEnought(count))
			return null;
		
		useItem(count);
		return new Item(getType(),count);
	}
	
	public ProductName getType() {
		return type;
	}
	public void setType(ProductName type) {
		this.type = type;
	}
	public int getQuanity() {
		return quanity;
	}
	public void setQuanity(int quanity) {
		this.quanity = quanity;
	}
}
