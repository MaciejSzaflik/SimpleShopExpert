package noob.pwr;

public class Item {
	private String type;
	private int quanity;
	
	public Item(String type, int quanity)
	{
		this.type = type;
		this.quanity = quanity;
	}
	
	public boolean checkType(String toCompare)
	{
		return toCompare.equalsIgnoreCase(getType());
	}
	
	public boolean IsValid()
	{
		return type !=null && !type.isEmpty() && quanity > 0;
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
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getQuanity() {
		return quanity;
	}
	public void setQuanity(int quanity) {
		this.quanity = quanity;
	}
}
