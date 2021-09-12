package il.ac.tau.cs.software1.ip;

public class IPAddressString implements IPAddress {
	
	private String IPaddress;

	IPAddressString(String address) {
		this.IPaddress = address;
	}

	@Override
	public String toString() {
		return this.IPaddress;
	}

	@Override
	public boolean equals(IPAddress other) {
		return (this.toString().equals(other.toString()));
	}

	@Override
	public int getOctet(int index) {
		String[] indexes = this.IPaddress.split("\\.");
		int number = Integer.parseInt(indexes[index]);
		return number;
	}

	@Override
	public boolean isPrivateNetwork(){
		int first_index = this.getOctet(0);
		int second_index = this.getOctet(1);
		if ((first_index==10)||(first_index==172&&(second_index<=31&&second_index>=16))) {//first & second cases
			return true;
		}
		if ((first_index==192&&second_index==168)||(first_index==169&&second_index==254)) {//third and fourth cases
			return true;
		}
		return false;
	}
	
}
