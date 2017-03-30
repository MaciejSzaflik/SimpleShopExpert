package noob.pwr;

public class Shop {
	public static int idGenerator = 0;
	public int id;
	public Position2D position;
	
	public Shop(Position2D position)
	{
		id = idGenerator++;
		this.position = position;
	}
}
