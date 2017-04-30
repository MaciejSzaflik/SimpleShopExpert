package noob.pwr;

public enum TripleState {
	Good,
	Bad,
	Undefined;
	
	public static TripleState FromBool(boolean value)
	{
		if(value)
			return TripleState.Good;
		else
			return TripleState.Bad;
	}
	
	public static boolean GetBool(TripleState value)
	{
		if(value == TripleState.Good)
			return true;
		else
			return false;
	}
}
