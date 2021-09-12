package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class Fighter extends MyAbstractBattleShip{
	
	private int annualMaint;
	
	
	public Fighter(String name, int commissionYear, float maximalSpeed, Set<? extends CrewMember> crewMembers, List<Weapon> weapons){
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
		this.annualMaint = this.getAnnualMaintenanceCost();
	}
	
	public int getAnnualMaintenanceCost() {
		int basicfightercost = 2500;
		int weaponsCost = super.getWeaponsCost();
		float engineCost = 1000*super.getMaximalSpeed();
		int finalengineCost = (int) engineCost;
		return basicfightercost+weaponsCost+finalengineCost;
	}
	
	public int getFighterCost() {
		return this.annualMaint;
	}

	public String toString() {
		return 
				"Fighter" + System.lineSeparator()+"\t"+"Name="+this.getName()+
				System.lineSeparator()+"\t"+"CommissionYear="+this.getCommissionYear()+
				System.lineSeparator()+"\t"+"MaximalSpeed="+this.getMaximalSpeed()+
				System.lineSeparator()+"\t"+"FirePower="+this.getFirePower()+System.lineSeparator()+
				"\t"+"CrewMembers="+this.getCrewMembers().size()+System.lineSeparator()+
				"\t"+"AnnualMaintenanceCost="+this.getAnnualMaintenanceCost()+System.lineSeparator()+
				"\t"+"WeaponArray="+this.getWeapon().toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + annualMaint;
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
		Fighter other = (Fighter) obj;
		if (annualMaint != other.annualMaint)
			return false;
		return true;
	}

}
