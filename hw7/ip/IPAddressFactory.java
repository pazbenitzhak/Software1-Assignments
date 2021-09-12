package il.ac.tau.cs.software1.ip;

public class IPAddressFactory {
	
	public static IPAddress createAddress(String ip) {
		IPAddress address = new IPAddressString(ip);
		return address;
	} 
	
	public static IPAddress createAddress(short[] ip) {
		IPAddress address = new IPAddressShort(ip);
		return address;
	} 
	
	public static IPAddress createAddress(int ip) {
		IPAddress address = new IPAddressInt(ip);
		return address;
	}
}