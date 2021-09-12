package il.ac.tau.cs.sw1.ex9.starfleet;
import java.util.Comparator;

public class SpaceshipComparator implements Comparator<Spaceship> {
	public int compare(Spaceship firstShip, Spaceship secondShip) {
		if (firstShip.getFirePower()<secondShip.getFirePower()) {
			return 1;
		}
		if (firstShip.getFirePower()>secondShip.getFirePower()) {
			return -1;
		}
		else {//they are equal
			if (firstShip.getCommissionYear()<secondShip.getCommissionYear()) {
				return 1;
			}
			if (firstShip.getCommissionYear()>secondShip.getCommissionYear()) {
				return -1;
			}
			else {//year is also equal
				return firstShip.getName().compareTo(secondShip.getName());
			}
			
		}
	}

}
