package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Set;

public class TransportShip extends MyAbstractSpaceShip{
	
	private int cargoCap;
	private int passengerCap;

	public TransportShip(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers, int cargoCapacity, int passengerCapacity){
		super(name, commissionYear, maximalSpeed, crewMembers);
		this.cargoCap = cargoCapacity;
		this.passengerCap = passengerCapacity;
	}
	
	public int getFirePower() {
		return super.getBasicFirePower();
	}
	
	public int getCargoCapacity() {
		return this.cargoCap;
	}
	
	public void setCargoCapacity(int cap) {
		this.cargoCap = cap;
	}
	
	public int getPassengerCapacity() {
		return this.passengerCap;
	}
	
	public void setPassengerCapacity(int cap) {
		this.cargoCap = cap;
	}
	
	public int getAnnualMaintenanceCost() {
		int basicTransportCost = 3000;
		return basicTransportCost+5*this.getCargoCapacity()+3*this.getPassengerCapacity();
	}
	
	public String toString() {
		return 
		"TransportShip" + System.lineSeparator()+"\t"+"Name="+this.getName()+
		System.lineSeparator()+"\t"+"CommissionYear="+this.getCommissionYear()+
		System.lineSeparator()+"\t"+"MaximalSpeed="+this.getMaximalSpeed()+
		System.lineSeparator()+"\t"+"FirePower="+this.getFirePower()+System.lineSeparator()+
		"\t"+"CrewMembers="+this.getCrewMembers().size()+System.lineSeparator()+
		"\t"+"AnnualMaintenanceCost="+this.getAnnualMaintenanceCost()+System.lineSeparator()+
		"\t"+"CargoCapacity="+this.getCargoCapacity()+System.lineSeparator()+"\t"+
		"PassengerCapacity="+this.getPassengerCapacity();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + cargoCap;
		result = prime * result + passengerCap;
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
		TransportShip other = (TransportShip) obj;
		if (cargoCap != other.cargoCap)
			return false;
		if (passengerCap != other.passengerCap)
			return false;
		return true;
	}

}
