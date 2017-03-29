package noob.pwr;

public class Position2D {
	public int x;
	public int y;
	
	public int getManhatanDistance(Position2D point)
	{
		return Math.abs(this.x - point.x) + Math.abs(this.y - point.y);
	}
}
