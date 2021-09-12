package il.ac.tau.cs.sw1.ex9.starfleet;

public class Cylon extends MyAbstractCrewMember {

	private int modelNum;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + modelNum;
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
		Cylon other = (Cylon) obj;
		if (modelNum != other.modelNum)
			return false;
		return true;
	}

	public Cylon(String name, int age, int yearsInService, int modelNumber) {
		super(name, age, yearsInService);
		this.modelNum = modelNumber;
	}
	
	public int getModelNumber() {
		return this.modelNum;
	}
	
	public void setModelNumber(int newnum) {
		this.modelNum = newnum;
	}
	

}
