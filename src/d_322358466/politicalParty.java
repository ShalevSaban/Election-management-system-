package d_322358466;

import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

public class politicalParty {

	private TypesOfTheParty type;
	protected String politicalPartyName;
	protected int yearOfStartThePoliticalParty;
	protected Vector<CandidateForTheParty> allCandidate;

	private int NumAtBox;

	public static enum TypesOfTheParty {
		Right, Left, Center
	}

	public politicalParty(String politicalPartyName, TypesOfTheParty type, int yearOfStartThePoliticalParty) {
		allCandidate = new Vector<CandidateForTheParty>();
		this.politicalPartyName = politicalPartyName;
		this.type = type;
		this.yearOfStartThePoliticalParty = yearOfStartThePoliticalParty;
	}

	public void addCandidate(CandidateForTheParty candidate) {
		allCandidate.add(candidate);
		Collections.sort(allCandidate);
	}

	public String getPoliticalPartyName() {
		return politicalPartyName;
	}

	public TypesOfTheParty getType() {
		return type;
	}

	public int getYearOfStartThePoliticalParty() {
		return yearOfStartThePoliticalParty;
	}

	public boolean equal(Object other) {
		if (other instanceof politicalParty) {
			politicalParty politicalParty1 = (politicalParty) other;
			if (!(politicalParty1.getPoliticalPartyName().equals(politicalPartyName)))
				return false;
			if (!(politicalParty1.getYearOfStartThePoliticalParty() == yearOfStartThePoliticalParty))
				return false;
			if (!(politicalParty1.getType().equals(type)))
				return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer("");
		str.append("political party name is: " + getPoliticalPartyName() + "\n"
				+ "the year of starting the political party is: " + getYearOfStartThePoliticalParty() + "\n"
				+ "the type of the political party is: " + getType() + "\n");
		for (int i = 0; i < allCandidate.size(); i++) {
			str.append(allCandidate.get(i).toString() + "\n");

		}
		return str.toString();
	}

	public int getNumAtBox() {
		return NumAtBox;
	}

	public void setNumAtBox(int numAtBox) {
		NumAtBox = numAtBox;
	}

}
