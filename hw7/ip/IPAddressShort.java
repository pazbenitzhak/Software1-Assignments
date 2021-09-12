package il.ac.tau.cs.software1.ip;

import java.util.Arrays;

public class IPAddressShort implements IPAddress {
	
	private short[] IPaddress;


	IPAddressShort(short[] address) {
		this.IPaddress = address;
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0;i<this.IPaddress.length;i++) {
			if (i==this.IPaddress.length-1) {
				s+=String.valueOf(this.IPaddress[i]);
			} else {
			s+=String.valueOf(this.IPaddress[i])+".";
			}
		}
		return s;
	}

	@Override
	public boolean equals(IPAddress other) {
		return this.toString().equals(other.toString());
	}

	@Override
	public int getOctet(int index) {
		return this.IPaddress[index];
	}

	@Override
	public boolean isPrivateNetwork(){
		short[]indexes = this.IPaddress;
		if ((indexes[0]==10)||(indexes[0]==172&&(indexes[1]<=31&&indexes[1]>=16))) {//first & second cases
			return true;
		}
		if ((indexes[0]==192&&indexes[1]==168)||(indexes[0]==169&&indexes[1]==254)) {//third and fourth cases
			return true;
		}
		return false;
	}
	
}
