package noob.pwr;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Truck {
	public static int idGenerator = 0;
	public int id;
	public int speed;
	public int capacity;
	public TruckState status;
	public Position2D currentPosition;
	
	public Date whenIdle;
	
	
	public TripleState cleanState;
	public TripleState conditionState;
	public TripleState materialsUsedValidForFood;
	public TripleState airRotationPossible;
	public TripleState truckerHaveCertificat;
	public Date truckerCertificatDate;
	public TripleState coolingAgregate;
	
	public TripleState usedOnlyForFood;
	
	
	public TripleState wasPreCooled;
	public TripleState wholeTruckTheSameTemperature;
	
	public TripleState pyroPermission;
	public TripleState truckerPyroCertificat;
	public TripleState truckerCanUseFireEstinguiser;
	public TripleState packageForPyro;
	public TripleState haveFireEstingisher;
	public TripleState truckHaveStoppers;
	public TripleState haveTorch;
	public TripleState fireInstrucion;
	public TripleState pyroDocumentation;
	public TripleState pyroInsurance;
	public TripleState pyroMarked;
	public TripleState onlyPyro;
	
	public TripleState meatCheck;
	public TripleState preCooledCheck;
	public TripleState pyroCheck;
	
	public Set<ProductName> whatCannotBeDelivered;
	
	
	public void SetAllGood()
	{
		cleanState = TripleState.Good;
		conditionState = TripleState.Good;
		materialsUsedValidForFood = TripleState.Good;
		airRotationPossible = TripleState.Good;
		truckerHaveCertificat = TripleState.Good;
		coolingAgregate = TripleState.Good;

		usedOnlyForFood = TripleState.Good;
		wasPreCooled = TripleState.Good;
		wholeTruckTheSameTemperature = TripleState.Good;
		pyroPermission = TripleState.Good;
		truckerPyroCertificat = TripleState.Good;
		truckerCanUseFireEstinguiser = TripleState.Good;
		packageForPyro = TripleState.Good;
		haveFireEstingisher = TripleState.Good;
		truckHaveStoppers = TripleState.Good;
		haveTorch = TripleState.Good;
		fireInstrucion = TripleState.Good;
		pyroDocumentation = TripleState.Good;
		pyroInsurance = TripleState.Good;
		pyroMarked = TripleState.Good;
		onlyPyro = TripleState.Good;
	}
	
	public Truck(int capacity)
	{
		id = idGenerator++;
		this.capacity = capacity;
		status = TruckState.Idle;
		ClearChecks();
		SetAllGood();
		speed = 1;
		whenIdle = new Date();
	}
	
	public boolean CanPerformThisOrder(Order order)
	{
		for(Item item : order.items)
		{
			if(this.whatCannotBeDelivered.contains(item.getType()))
				return false;
		}
		
		return true;
	}
	
	public void ClearChecks()
	{
		meatCheck = TripleState.Undefined;
		preCooledCheck = TripleState.Undefined;
		pyroCheck = TripleState.Undefined;
		truckerCertificatDate = new Date();
		this.whatCannotBeDelivered = new HashSet<ProductName>();
	}
	
	
	public void AddRestrictedProduct(ProductName toAdd)
	{
		whatCannotBeDelivered.add(toAdd);
	}
	
	public boolean CheckRestricted(ProductName toAdd)
	{
		return whatCannotBeDelivered.contains(toAdd);
	}
	
	public void SetFoodInvalid()
	{
		meatCheck = TripleState.Bad;
		AddRestrictedProduct(ProductName.Meat);
		AddRestrictedProduct(ProductName.Poultry);
		AddRestrictedProduct(ProductName.Eggs);
		AddRestrictedProduct(ProductName.Fish);
	}
	
	public boolean IsTruckerCertificateValid()
	{
	    Date currentDate = new Date();
		return Util.getDiffYears(currentDate, truckerCertificatDate) > 1;
	}
	
	public Date GetTimeToTravel(Position2D position)
	{
		int time = Math.round(position.getTimeToTravel(currentPosition, speed));
		Calendar cal = Calendar.getInstance();
        cal.setTime(whenIdle);
        cal.add(Calendar.MINUTE, time);
        return cal.getTime();
	}

	public void FullfillOrder(Order order,Position2D shopPosition) {
		whenIdle = GetTimeToTravel(shopPosition);
        currentPosition = shopPosition;
       
	}
}
