package noob.pwr;

public class Truck {
	public static int idGenerator = 0;
	public int id;
	public int speed;
	public int capacity;
	
	public Truck()
	{
		id = idGenerator++;
	}
}
