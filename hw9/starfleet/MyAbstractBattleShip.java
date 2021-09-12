package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public abstract class MyAbstractBattleShip extends MyAbstractSpaceShip {
	
	private List<Weapon> weaponList;
	
	
	public MyAbstractBattleShip(String newname, int commyear, float speed, Set<? extends CrewMember> crew, List<Weapon> wlist) {
		super(newname, commyear, speed, crew);
		this.weaponList = wlist;
	}
	
	public List<Weapon> getWeapon() {
		return this.weaponList;
	}
	
	public void setWeapon(List<Weapon> wlist) {
		this.weaponList = wlist;
	}
	
	public int getFirePower() {
		int fpsum = 0;
		fpsum+= super.getBasicFirePower();
		for (Weapon weapon : this.weaponList) {
			fpsum+=weapon.getFirePower();
		}
		return fpsum;
	}
	
	public int getWeaponsCost() {
		int totalCost = 0;
		for (Weapon weapon : this.weaponList) {
			totalCost+=weapon.getAnnualMaintenanceCost();
		}
		return totalCost;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((weaponList == null) ? 0 : weaponList.hashCode());
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
		MyAbstractBattleShip other = (MyAbstractBattleShip) obj;
		if (weaponList == null) {
			if (other.weaponList != null)
				return false;
		} else if (!weaponList.equals(other.weaponList))
			return false;
		return true;
	}
	

}
