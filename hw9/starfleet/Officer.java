package il.ac.tau.cs.sw1.ex9.starfleet;

public class Officer extends CrewWoman {
	
	OfficerRank rank;
		
	public Officer(String name, int age, int yearsInService, OfficerRank rank) {
		super(age, yearsInService, name);
		this.rank = rank;
	}
	
	public OfficerRank getRank() {
		return this.rank;
	}
	
	public void setRank(OfficerRank newRank) {
		this.rank = newRank;
	}
	
	@Override
	public String toString() {
		return this.rank.toString() +"\t"+ super.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
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
		Officer other = (Officer) obj;
		if (rank != other.rank)
			return false;
		return true;
	}
	
	
}
