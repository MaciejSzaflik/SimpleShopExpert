package noob.pwr;

public class CurrentWeather {
	public Weather currentWeather;
	public TripleState canPyroBeTransported;
	
	public CurrentWeather()
	{
		currentWeather = Weather.ThunderStorm;
		canPyroBeTransported = TripleState.Undefined;
	}
	
}
