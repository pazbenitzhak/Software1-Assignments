package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class CylonRaider extends Fighter{

	public CylonRaider(String name, int commissionYear, float maximalSpeed, Set<Cylon> crewMembers,
			List<Weapon> weapons) {
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
	}
	
	public int getAnnualMaintenanceCost() {
		int cylonCost = 3500;
		int weaponsCost = super.getWeaponsCost();
		int crewCost = 500*super.getCrewMembers().size();
		float engineCost = this.getMaximalSpeed()*1200;
		return cylonCost+weaponsCost+crewCost+(int)engineCost;
	}
	
	public String toString() {
		return 
				"CylonRaider" + System.lineSeparator()+"\t"+"Name="+this.getName()+
				System.lineSeparator()+"\t"+"CommissionYear="+this.getCommissionYear()+
				System.lineSeparator()+"\t"+"MaximalSpeed="+this.getMaximalSpeed()+
				System.lineSeparator()+"\t"+"FirePower="+this.getFirePower()+System.lineSeparator()+
				"\t"+"CrewMembers="+this.getCrewMembers().size()+System.lineSeparator()+
				"\t"+"AnnualMaintenanceCost="+this.getAnnualMaintenanceCost()+System.lineSeparator()+
				"\t"+"WeaponArray="+this.getWeapon().toString();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

	

}
