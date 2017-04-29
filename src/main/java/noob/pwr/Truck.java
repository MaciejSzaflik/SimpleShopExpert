package noob.pwr;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Truck {
	public static int idGenerator = 0;
	public int id;
	public int speed;
	public int capacity;
	public TruckState status;
	
	public TripleState meatCheck;
	public TripleState cleanState;
	public TripleState conditionState;
	public TripleState materialsUsedValidForFood;
	public TripleState airRotationPossible;
	public TripleState truckerHaveCertificat;
	public Date truckerCertificatDate;
	public TripleState coolingAgregate;
	public Set<ProductName> whatCannotBeDelivered;
	public TripleState usedOnlyForFood;
	
	public TripleState pyroCheck;
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
	
	public TripleState preCooledCheck;
	
	public TripleState wasPreCooled;
	public TripleState wholeTruckTheSameTemperature;

	
	
	public Truck(int capacity)
	{
		id = idGenerator++;
		this.capacity = capacity;
		status = TruckState.Idle;
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
	}
	
	public boolean IsTruckerCertificateValid()
	{
	    Date currentDate = new Date();
		return Util.getDiffYears(currentDate, truckerCertificatDate) > 1;
	}
}
