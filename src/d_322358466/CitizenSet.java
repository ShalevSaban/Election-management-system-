package d_322358466;

import java.util.Vector;


public class CitizenSet {

	private Vector<Citizen> allCitizen;

	public CitizenSet() {
		allCitizen = new Vector<Citizen>();
	}
	
	public void addCitizen(Citizen people) throws Exception {
		for(int i=0;i<allCitizen.size();i++) {
			checkCitizenName(people.getName());
			checkId(people.getId());
		}
		allCitizen.add(people);
	}

	public String toString() {
		StringBuffer str = new StringBuffer("");
		str.append("list of citizen:" + "\n");
		for (int i = 0; i < allCitizen.size(); i++) {
			str.append(allCitizen.get(i).toString() + "\n");
		}
		return str.toString();
	}

	public Citizen getCitzen(int Place) {
		return allCitizen.get(Place);

	}

	public int getNumOfPeople() {
		return allCitizen.size();
	}

	public Vector<Citizen> getAllCitizen() {
		return allCitizen;
	}

	public void checkId(String id) throws Exception {
		for(int i=0;i<allCitizen.size();i++) {
			if(allCitizen.get(i).getId().equals(id))
				throw new Exception("There is already a citizen with this id " + id + ".");
		}
	}
	
	private void checkCitizenName(String name) throws Exception {
		for (Citizen citizen: allCitizen) {
			if (citizen.getName().equals(name)) {
				throw new Exception("There is already a citizen with the name " + name + ".");
			}
		}
	}
}
