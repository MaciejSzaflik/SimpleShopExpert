package noob.pwr;

public class Truck {
	public static int idGenerator = 0;
	public int id;
	public int speed;
	public int capacity;
	public TruckState status;
	
	public Truck(int capacity)
	{
		id = idGenerator++;
		this.capacity = capacity;
		status = TruckState.Idle;
	}
}
