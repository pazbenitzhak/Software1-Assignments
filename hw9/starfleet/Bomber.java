package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class Bomber extends MyAbstractBattleShip{
	
	private int techNum;

	public Bomber(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers, List<Weapon> weapons, int numberOfTechnicians){
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
		this.techNum = numberOfTechnicians;
		}
	
	public int getNumberOfTechnicians() {
		return this.techNum;
	}
	
	public void setNumberOfTechnicians(int num) {
		this.techNum = num;
	}
	
	public int getAnnualMaintenanceCost() {
		int basicBomberCost = 5000;
		int weaponsCost = super.getWeaponsCost();
		double techniciansDiscout = 1-(0.1*this.getNumberOfTechnicians());
		double floorDiscount = techniciansDiscout*weaponsCost;
		double finalCost = basicBomberCost+floorDiscount;
		return (int) finalCost ;
	}
	
	public String toString() {
		return 
				"Bomber" + System.lineSeparator()+"\t"+"Name="+this.getName()+
				System.lineSeparator()+"\t"+"CommissionYear="+this.getCommissionYear()+
				System.lineSeparator()+"\t"+"MaximalSpeed="+this.getMaximalSpeed()+
				System.lineSeparator()+"\t"+"FirePower="+this.getFirePower()+System.lineSeparator()+
				"\t"+"CrewMembers="+this.getCrewMembers().size()+System.lineSeparator()+
				"\t"+"AnnualMaintenanceCost="+this.getAnnualMaintenanceCost()+System.lineSeparator()+
				"\t"+"WeaponArray="+this.getWeapon().toString()+System.lineSeparator()+
				"\t"+"NumberOfTechnicians="+this.getNumberOfTechnicians();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + techNum;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bomber other = (Bomber) obj;
		if (techNum != other.techNum)
			return false;
		return true;
	}


}
