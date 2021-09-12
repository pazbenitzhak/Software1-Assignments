package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Comparator;
import java.util.Map;

public class OfficerRanksComparator implements Comparator<Map.Entry<OfficerRank, Integer>> {
	public int compare(Map.Entry<OfficerRank, Integer> firstMap, Map.Entry<OfficerRank, Integer> secondMap) {
		if (firstMap.getValue()<secondMap.getValue()) {
			return -1;
		}
		if (firstMap.getValue()>secondMap.getValue()) {
			return 1;
		}
		else {//num is equal
			return firstMap.getKey().compareTo(secondMap.getKey());
		}
		
	}

}
