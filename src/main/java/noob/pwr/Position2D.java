package noob.pwr;

public class Position2D {
	public float x;
	public float y;
	
	public Position2D(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public int getManhatanDistance(Position2D point)
	{
		return (int) (Math.abs(this.x - point.x) + Math.abs(this.y - point.y));
	}
	
	public float getTimeToTravel(Position2D to, int speed)
	{
		return getManhatanDistance(to)/speed;
	}
}
