package il.ac.tau.cs.software1.ip;

import java.util.Arrays;

public class TestIPAddress {

	public static void main(String[] args) {
		int address1 = -1062731775; // 192.168.0.1
		short[] address2 = { 10, 1, 255, 1 }; // 10.1.255.1

		IPAddress ip1 = IPAddressFactory.createAddress(address1);
		IPAddress ip2 = IPAddressFactory.createAddress(address2);
		IPAddress ip3 = IPAddressFactory.createAddress("127.0.0.1");

		for (int i = 0; i < 4; i++) {
			System.out.println(ip1.getOctet(i));
		}
		
		for (int i = 0; i < 4; i++) {
			System.out.println(ip2.getOctet(i));
		}
		
		for (int i = 0; i < 4; i++) {
			System.out.println(ip3.getOctet(i));
		}
		
		System.out.println("ip1 String = " +ip1.toString());
		System.out.println("ip2 String = " +ip2.toString());

		System.out.println("equals: " + ip1.equals(ip2));
		System.out.println("Is private Network: " + ip1.isPrivateNetwork());

		if (ip1.equals(ip3)) {
			System.out.println("error no. 1");
		}
		if (ip2.equals(ip3)) {
			System.out.println("error no. 2");
		}
		
		short[] address3 = { 127, 0, 0, 1 };
		IPAddress ip4 = IPAddressFactory.createAddress(address3);
		
		if  (!ip3.equals(ip4)) {
			System.out.println("error no. 3");
		}
		
		if (!ip2.isPrivateNetwork()) {
			System.out.println("error no. 4");
		}
		
		if (ip3.isPrivateNetwork()) {
			System.out.println("error no. 5");
		}
		
		
	}
}
