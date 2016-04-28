package Planner;

public class Person<T extends Comparable<T>> {

	private T personName;
	private int personID;  //Start with an index of 0. Would simplify the process of looping through related data structures.
	
	public Person(T personName, int personID){
		this.personName = personName;
		this.personID = personID;
	}

	public T getPersonName() {
		return personName;
	}

	public void setPersonName(T personName) {
		this.personName = personName;
	}

	public int getPersonID() {
		return personID;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}
	
}
