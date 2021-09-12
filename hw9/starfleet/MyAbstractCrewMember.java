package il.ac.tau.cs.sw1.ex9.starfleet;

public abstract class MyAbstractCrewMember implements CrewMember {
	
	private String name;
	private int age;
	private int serviceYears;
	
	public MyAbstractCrewMember(String aname, int anage, int numserviceYears) {
		this.name=aname;
		this.age = anage;
		this.serviceYears = numserviceYears;
	}
	

	public String getName() {
		return this.name;
	}
	
	public void setName(String newname) {
		this.name = newname;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public void setAge(int newage) {
		this.age = newage;
	}
	
	public int getYearsInService() {
		return this.serviceYears;
	}
	
	public void setYearsInService(int years) {
		this.serviceYears = years;
	}
	
	public String toString() {
		return this.name;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + serviceYears;
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
		MyAbstractCrewMember other = (MyAbstractCrewMember) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (serviceYears != other.serviceYears)
			return false;
		return true;
	}
	


}
