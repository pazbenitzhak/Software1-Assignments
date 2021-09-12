package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class ColonialViper  extends Fighter{

	public ColonialViper(String name, int commissionYear, float maximalSpeed, Set<CrewWoman> crewMembers,
			List<Weapon> weapons) {
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
	}
	
	public int getAnnualMaintenanceCost() {
		int viperCost = 4000;
		int weaponsCost = super.getWeaponsCost();
		int crewCost = 500*super.getCrewMembers().size();
		float engineCost = this.getMaximalSpeed()*500;
		return viperCost+weaponsCost+crewCost+(int)engineCost;
	}
	
	public String toString() {
		return 
				"ColonialViper" + System.lineSeparator()+"\t"+"Name="+this.getName()+
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
