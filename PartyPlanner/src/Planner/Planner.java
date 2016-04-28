package Planner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Planner<T extends Comparable<T>> {

	private boolean[][] relationshipMatrix;
	private int guestCount;
	private int invitationCriteria;
	private Map<Integer,Person<T>> individualMappings; //Used for looking up filtered people.

	public Planner(int guestCount, int invitationCriteria){
		this.guestCount = guestCount;   //Total unique guests in the network.
		this.invitationCriteria = invitationCriteria; //Cutoff criteria for filtering people.
		relationshipMatrix = new boolean[this.guestCount+1][this.guestCount+1]; //The final column is used to hold a flag indicating individual persons eligibility.
		individualMappings = new HashMap<Integer,Person<T>>();
	}

	public void establishRelations(List<Pair<T>> pairs){ //Code for forming adjacency matrix. Will hold true if Guest A knows B and vice versa.
		for(int i=0; i<pairs.size(); i++){
			Pair<T> pair = pairs.get(i);
			Person<T> person = pair.getGuestOne();
			Person<T> friend = pair.getGuestTwo();
			relationshipMatrix[person.getPersonID()][friend.getPersonID()] = true; 
			relationshipMatrix[friend.getPersonID()][person.getPersonID()] = true;
		}
	}

	public void establishMappings(List<Person<T>> persons){   //Populate hashmap to display final results. Use uniqueID as lookup key. 
		for(int i=0; i< persons.size(); i++){
			individualMappings.put(persons.get(i).getPersonID(), persons.get(i));
		}
	}

	public void computeCriteria(){  
		boolean converged = false;
		boolean isExcluded = true;
		while(!converged){  //Loop till convergence. COnvergence is achieved when we no-longer need to remove people from list.
			converged = true;
			for(int i=0; i<guestCount; i++) {          //i = rowIndex
				if(!relationshipMatrix[i][guestCount]){
					int count = 0;
					for(int j=0; j<guestCount; j++){    // j = coulmnIndex
						if(relationshipMatrix[i][j]){
							count++;
						}
					}
					if(count < invitationCriteria){       //This corresponding logic indicates that the current person doesnot meet the cutoff criteria. Set him/her as excluded and simultaneously update adjacency matrix for other friends.
						for(int k =0; k<guestCount; k++){
							relationshipMatrix[k][i] = false;
						}
						relationshipMatrix[i][guestCount] = isExcluded;  //By excluding the row from further computation, we exclude the entire person as well.
						converged = false;
					}
				}
			}
		}
	}

	public void filterPeople(Invitation<T> eventInvite){
		for(int i=0; i<guestCount; i++) {
			if(!relationshipMatrix[i][guestCount]){
				eventInvite.addGuest(individualMappings.get(i));
			}
		}
	}

	public void generateInvitation(List<Person<T>> individuals, List<Pair<T>> relationships, Invitation<T> invitationCanvas){
		if((individuals == null || individuals.isEmpty()) || (relationships == null || relationships.isEmpty()) || invitationCanvas == null){  //Checking for safety.
			System.out.println(Constants.ILLEGAL_DATA_INPUTS);
			return;
		}
		establishMappings(individuals);     //Basically an index.
		establishRelations(relationships); // Establish graph. 
		computeCriteria();                // Filter people as per business rules.
		filterPeople(invitationCanvas);  //Index based lookups. 
	}
	
}


	
	
