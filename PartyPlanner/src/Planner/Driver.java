package Planner;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Driver {
	
	private static Scanner myScanner = null;
	private static List<Person<Integer>> Persons = null;
	private static List<Pair<Integer>> Pairs = null;
	
	public static void main(String[] args) {
		createGuests(34); //Here 34 indicates the number of nodes in the network. 
		processData();
		Invitation<Integer> invite = new Invitation<Integer>();
		Planner<Integer> planner = new Planner<Integer>(34,5); //TEST WITH VARIED CRITERIA CHOICES. [TEST FOR CRITERIA RANGING FROM 3 TO 5].
		planner.generateInvitation(Persons, Pairs, invite); //Invitation is a canvas object and mimics a real world invitation. Persons list holds all unique guests. Pairs list holds bi-directional relationships.
		invite.printQualifiedGuestsList(); //Prints out the filtered guest list.
	} 
	
	
	public static void createGuests(int guestCount){
		Driver.Persons = new LinkedList<Person<Integer>>();  //Loop through network and create individual guests.
		for(int i=0; i<guestCount; i++){ //NOTE: Always start with an index of 0 (for denoting personID).
			Persons.add(new Person<Integer>((i+1), i));
		}
	}
	
	public static void processData(){  //A simple method to establish relationships. The populated list is consumed by Planner.java class to create adjacency matrix.
		String inputPath = "Data/karate.txt";   //More details of Karate network could be found here : http://www.stat.cmu.edu/~jiashun/Research/software/NetworkData/Karate/
		Driver.Pairs = new LinkedList<Pair<Integer>>();
		try{
			myScanner = new Scanner(new FileInputStream(inputPath));
			String input = "";
			int rowIndex = 0;
			while(myScanner.hasNextLine()){
				input = myScanner.nextLine();
				String values[] = input.split("\\s+");
				for(int i=0; i<values.length; i++){
					if(values[i].trim().equals("1")){
						Pairs.add(new Pair<Integer>(Persons.get(rowIndex), Persons.get(i)));
					}
				}
				rowIndex++;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			myScanner.close();
		}
	}

}
