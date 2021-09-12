package il.ac.tau.cs.software1.ip;

import java.nio.ByteBuffer;

public class IPAddressInt implements IPAddress {
	
	private int IPaddress;

	IPAddressInt(int address) {
		this.IPaddress = address;
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0;i<4;i++) {
			if (i==3) {
				s+=Integer.toString(this.getOctet(i));
			} else {
			s+=Integer.toString(this.getOctet(i))+".";
			}
		}
		return s;
	}

	@Override
	public boolean equals(IPAddress other) {
		return (this.toString().equals(other.toString()));
	}

	@Override
	public int getOctet(int index) {
		ByteBuffer b = ByteBuffer.allocate(4);
		b.putInt(this.IPaddress);
		byte index_byte = b.get(index);
		int correct_num = (int) (index_byte & 0xFF);
		return correct_num;
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
