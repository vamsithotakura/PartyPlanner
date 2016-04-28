package Planner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Invitation<T extends Comparable<T>> {
	
	private String company;
	private String venue;
	private String greeting;
	private Date eventDateTime;
	
	private List<Person<T>> guests;
	
	public Invitation() {
		guests = new ArrayList<Person<T>>();
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

	public Date getEventDateTime() {
		return eventDateTime;
	}

	public void setEventDateTime(Date eventDateTime) {
		this.eventDateTime = eventDateTime;
	}

	public List<Person<T>> getGuests() {
		return guests;
	}

	public void setGuests(List<Person<T>> guests) {
		this.guests = guests;
	}
	
	public void addGuest(Person<T> guest){
		this.guests.add(guest);
	}
	
	public void printQualifiedGuestsList(){
		if(this.guests.isEmpty()) {
			System.out.println(Constants.NO_REUSLTS_FOUND);
		} else {
			System.out.println(Constants.RESULTS_FOUND);
			for(Person<T> person : guests){
				System.out.print(person.getPersonName()+"	");
			}
		}
	}

}
