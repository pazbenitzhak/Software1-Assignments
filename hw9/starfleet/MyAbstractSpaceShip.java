package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Set;

public abstract class MyAbstractSpaceShip implements Spaceship {
	
	private String name;
	private int commissionYear;
	private float maxSpeed;
	private int basicFirePower = 10;
	private Set<? extends CrewMember> crewList;
	
	
	public MyAbstractSpaceShip(String newname, int commyear, float speed, Set<? extends CrewMember> crew) {
		this.name = newname;
		this.commissionYear = commyear;
		this.maxSpeed = speed;
		this.crewList = crew;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String newname) {
		this.name = newname;
	}
	
	public int getCommissionYear() {
		return this.commissionYear;
	}
	
	public void setCommissionYear(int year) {
		this.commissionYear = year;
	}
	
	public float getMaximalSpeed() {
		return this.maxSpeed;
	}
	
	public void setMaximalSpeed(float speed) {
		this.maxSpeed = speed;
	}
	
	public Set<? extends CrewMember> getCrewMembers() {
		return this.crewList;
	}
	
	public void setCrewMembers(Set<? extends CrewMember> crew) {
		this.crewList = crew;
	}
	
	public int getBasicFirePower() {
		return this.basicFirePower;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + basicFirePower;
		result = prime * result + commissionYear;
		result = prime * result + ((crewList == null) ? 0 : crewList.hashCode());
		result = prime * result + Float.floatToIntBits(maxSpeed);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyAbstractSpaceShip other = (MyAbstractSpaceShip) obj;
		if (basicFirePower != other.basicFirePower)
			return false;
		if (commissionYear != other.commissionYear)
			return false;
		if (crewList == null) {
			if (other.crewList != null)
				return false;
		} else if (!crewList.equals(other.crewList))
			return false;
		if (Float.floatToIntBits(maxSpeed) != Float.floatToIntBits(other.maxSpeed))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
