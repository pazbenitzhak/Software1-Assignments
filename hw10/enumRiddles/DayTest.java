package enumRiddles;

enum Day {
	   MONDAY(1),
	   TUESDAY(2),
	   WEDNESDAY(3),
	   THURSDAY(4),
	   FRIDAY(5),
	   SATURDAY(6),
	   SUNDAY(7);
	 
	private int number;
	private Day nextDay;
	
	Day(int number) {
		this.number = number;
	}
	   public Day next(){return this.values()[this.number%7];}
	 
	   int getDayNumber() {
		   return this.number;
	   }
	}
	   
	public class DayTest {
	   public static void main(String[] args) {
	      for (Day day : Day.values()) {
	         System.out.printf("%s (%d), next is %s\n", day, day.getDayNumber(), day.next());
	      }
	   }
	}
