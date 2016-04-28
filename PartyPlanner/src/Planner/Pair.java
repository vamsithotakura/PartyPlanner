package Planner;

public class Pair<T extends Comparable<T>> {
	
	private Person<T> guestOne;
	private Person<T> guestTwo;
	
	public Pair(Person<T> guestOne, Person<T> guestTwo){
		this.guestOne = guestOne;
		this.guestTwo = guestTwo;
	}
	
	public Person<T> getGuestOne(){
		return this.guestOne;
	}
	
	public Person<T> getGuestTwo(){
		return this.guestTwo;
	}
	
}
