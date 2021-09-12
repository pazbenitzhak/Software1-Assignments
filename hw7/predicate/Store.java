package il.ac.tau.cs.software1.predicate;

import java.util.List;

public class Store<T extends Product> {
	private List<T> inventory;
	
	public Store(List <T> inventory) {
		this.inventory = inventory;
	}

	public List<T> getInventory() {
		return inventory;
	}

	public String getInventoryDescription() { // Q4
		if (this.inventory.isEmpty()) {
			return "";
		}
		String collective = "";
		for (T element: this.inventory) {
			collective +=element.getDescription() + "";
		}
		return collective; 
	}

	public void transform(Predicate<T> pred, Action<T> action) { // Q5
		for (T element: this.inventory) {
			if (pred.test(element)) {
				action.performAction(element);
			}
		}
	}
}
