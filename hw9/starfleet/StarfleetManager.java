package il.ac.tau.cs.sw1.ex9.starfleet;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class StarfleetManager {

	/**
	 * Returns a list containing string representation of all fleet ships, sorted in descending order by
	 * fire power, and then in descending order by commission year, and finally in ascending order by
	 * name
	 */
	public static List<String> getShipDescriptionsSortedByFirePowerAndCommissionYear (Collection<Spaceship> fleet) {
		Spaceship[] array = fleet.toArray(new Spaceship[fleet.size()]);
		List<Spaceship> spaceshipList = Arrays.asList(array);
		Collections.sort(spaceshipList, new SpaceshipComparator());
		List<String> sortedList = new ArrayList<String>();
		for (Spaceship spaceship : spaceshipList) {
			sortedList.add(spaceship.toString());
		}
		return sortedList;
	}

	/**
	 * Returns a map containing ship type names as keys (the class name) and the number of instances created for each type as values
	 */
	public static Map<String, Integer> getInstanceNumberPerClass(Collection<Spaceship> fleet) {
		HashMap<String, Integer> instanceMap = new HashMap<String, Integer>();
		for (Spaceship spaceship: fleet) {
			String className = spaceship.getClass().getSimpleName();
			if (instanceMap.containsKey(className)) {
				instanceMap.put(className, instanceMap.get(className)+1);
			}
			else {//classtype not in list
				instanceMap.put(className, 1);
			}
		}
		return instanceMap;

	}


	/**
	 * Returns the total annual maintenance cost of the fleet (which is the sum of maintenance costs of all the fleet's ships)
	 */
	public static int getTotalMaintenanceCost (Collection<Spaceship> fleet) {
		int totalSum = 0;
		for (Spaceship spaceship : fleet) {
			totalSum+=spaceship.getAnnualMaintenanceCost();
		}
		return totalSum;

	}


	/**
	 * Returns a set containing the names of all the fleet's weapons installed on any ship
	 */
	public static Set<String> getFleetWeaponNames(Collection<Spaceship> fleet) {
		HashSet<String> weaponsNames = new HashSet<String>();
		for (Spaceship spaceship : fleet) {
			if (spaceship.getClass().getSimpleName().equals("TransportShip")) {//doesn't have a weapon
				continue;
			}
			MyAbstractBattleShip castShip = (MyAbstractBattleShip) spaceship;
			for (Weapon weapon : castShip.getWeapon()) {
				weaponsNames.add(weapon.getName());
			}
		}
		return weaponsNames;

	}

	/*
	 * Returns the total number of crew-members serving on board of the given fleet's ships.
	 */
	public static int getTotalNumberOfFleetCrewMembers(Collection<Spaceship> fleet) {
		int crewSum = 0;
		for (Spaceship spaceship : fleet) {
			crewSum+=spaceship.getCrewMembers().size();
		}
		return crewSum;

	}


	/*
	 * Returns the average age of all officers serving on board of the given fleet's ships. 
	 */
	public static float getAverageAgeOfFleetOfficers(Collection<Spaceship> fleet) {
		List<Integer> ages = new ArrayList<Integer>();
		for (Spaceship spaceship: fleet) {
			Set<? extends CrewMember> set = spaceship.getCrewMembers();
			for (CrewMember member : set) {
				if (member.getClass().getSimpleName().equals("Officer")) {
					ages.add(member.getAge());
				}
			}
		} //now we have a list of ages
		float sum = 0;
		for (Integer age: ages) {
			sum+=age;
		}
		float averageAge = sum/ages.size();
		return averageAge;
	}

	/*
	 * Returns a map mapping the highest ranking officer on each ship (as keys), to his ship (as values).
	 */
	public static Map<Officer, Spaceship> getHighestRankingOfficerPerShip(Collection<Spaceship> fleet) {
		HashMap<Officer, Spaceship> officersMap = new HashMap<Officer, Spaceship>(); 
		for (Spaceship spaceship: fleet) {
			Officer highestRanked = new Officer("Blank$",0,0,OfficerRank.Ensign);
			for (CrewMember member : spaceship.getCrewMembers()) {
				if (member.getClass().getSimpleName().equals("Officer")) {
					Officer tempOfficer = (Officer) member;
					if (tempOfficer.getRank().compareTo(highestRanked.getRank())>0) {
						highestRanked = tempOfficer;
					}
				}
			}
		if (!highestRanked.getName().equals("Blank$")) {
			officersMap.put(highestRanked, spaceship);
		}
		}
		return officersMap;

	}

	/*
	 * Returns a List of entries representing ranks and their occurrences.
	 * Each entry represents a pair composed of an officer rank, and the number of its occurrences among starfleet personnel.
	 * The returned list is sorted ascendingly based on the number of occurrences.
	 */
	public static List<Map.Entry<OfficerRank, Integer>> getOfficerRanksSortedByPopularity(Collection<Spaceship> fleet) {
		HashMap<OfficerRank, Integer> rankToNum = new HashMap<OfficerRank, Integer>();
		for (Spaceship spaceship: fleet) {
			for (CrewMember member : spaceship.getCrewMembers()) {
				if (member.getClass().getSimpleName().equals("Officer")) {
					Officer tempOfficer = (Officer) member;
					OfficerRank rank = tempOfficer.getRank();
					if (rankToNum.containsKey(rank)) {
						rankToNum.put(rank, rankToNum.get(rank)+1);
					} else {//rank ain't in Map 
						rankToNum.put(rank, 1);
					}
				}
			}
	}//up here we filled rankToNum with all the ranks
		Set<Map.Entry<OfficerRank, Integer>> rankset = rankToNum.entrySet();
		Map.Entry<OfficerRank, Integer>[] array = rankset.toArray((Map.Entry<OfficerRank, Integer>[]) new Map.Entry[rankset.size()]);
		List<Map.Entry<OfficerRank, Integer>> ranksList = Arrays.asList(array);
		Collections.sort(ranksList, new OfficerRanksComparator());
		return ranksList;
	}

}
