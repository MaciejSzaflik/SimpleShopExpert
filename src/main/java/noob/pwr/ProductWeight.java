package noob.pwr;

public class ProductWeight {

	public static int getWeightOfProduct(ProductName name, int count)
	{
		switch(name)
		{
		case Beverages:
			return 3*count;
		case Bakery:
			return 1*count;
		case Canned:
			return 2*count;
		case Cleaners:
			return 2*count;
		case Dairy:
			return 2*count;
		case Dry:
			return 1*count;
		case FrozenFoods:
			return 2*count;
		case Meat:
			return 3*count;
		case Other:
			return 3*count;
		case PaperGoods:
			return 2*count;
		case PersonalCare:
			return 2*count;
		case Produce:
			return 1*count;
		default:
			return count;
		}
	}
}
