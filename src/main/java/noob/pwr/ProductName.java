package noob.pwr;

public enum ProductName {
	Alchohol,
	Beverages,
	Bakery,
	Canned,
	Dairy,
	Dry,
	FrozenFoods,
	Meat,
	Eggs,
	Poultry,
	Produce,
	Cleaners,
	PaperGoods,
	PersonalCare,
	Other,
	Pyrotechnicks,
	Fish;
	
	public static boolean isThisFood(ProductName name)
	{
		switch(name)
		{
		case Alchohol:
			return false;
		case Bakery:
			return true;
		case Beverages:
			return false;
		case Canned:
			return false;
		case Cleaners:
			return false;
		case Dairy:
			return true;
		case Dry:
			return false;
		case Eggs:
			return true;
		case Fish:
			return true;
		case FrozenFoods:
			return true;
		case Meat:
			return true;
		case Other:
			return false;
		case PaperGoods:
			return false;
		case PersonalCare:
			return false;
		case Poultry:
			return true;
		case Produce:
			return false;
		case Pyrotechnicks:
			return false;
		default:
			return false;
		}
	}
}
