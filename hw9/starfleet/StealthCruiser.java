package il.ac.tau.cs.sw1.ex9.starfleet;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class StealthCruiser extends Fighter{
	
	static int stealthNumber;
	
	public StealthCruiser(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers, List<Weapon> weapons) {
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
		stealthNumber++;
	}

	public StealthCruiser(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers){
		this(name, commissionYear, maximalSpeed, crewMembers, defaultWeaponsList());
	}
	
	public static List<Weapon> defaultWeaponsList() {
		List<Weapon> weapons = new ArrayList<Weapon>();
		weapons.add(new Weapon("Laser Cannons",10,100));
		return weapons;
	}

	@Override
	public int getAnnualMaintenanceCost() {
		int fighterCost = this.getFighterCost();
		return fighterCost+50*stealthNumber;
		
	}
	
	public int getFighterCost() {
		int basicfightercost = 2500;
		int weaponsCost = super.getWeaponsCost();
		float engineCost = 1000*super.getMaximalSpeed();
		int finalengineCost = (int) engineCost;
		return basicfightercost+weaponsCost+finalengineCost;
	}
	
	
	public String toString() {
		return 
				"StealthCruiser" + System.lineSeparator()+"\t"+"Name="+this.getName()+
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
