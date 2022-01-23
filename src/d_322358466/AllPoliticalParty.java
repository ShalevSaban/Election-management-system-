package d_322358466;


import java.util.Vector;

public class AllPoliticalParty {
private Vector<politicalParty> allPoliticalParty;


	public AllPoliticalParty() {
		allPoliticalParty=new Vector<politicalParty>();
	}
public int getNumOfAllParty() {
	return allPoliticalParty.size();
}
	public void addPoliticalParty(politicalParty newPoliticalParty) {
		allPoliticalParty.add(newPoliticalParty);
	}


	public Vector<politicalParty> getAllParties() {
		return allPoliticalParty;
	}

	public String getNameOfParty(int num) {
		return allPoliticalParty.get(num).getPoliticalPartyName();
	}
	
	public String toString() {
		StringBuffer str = new StringBuffer("");
		str.append("this is all the political party for this election" + "\n");
		for (int i = 0; i < allPoliticalParty.size(); i++) {
				str.append(allPoliticalParty.get(i).toString() + "\n");
		}
		return str.toString();
	}
	
	public politicalParty getPoliticalParty(String politicalPartyName) {
		for (politicalParty _politicalParty: allPoliticalParty) {
			if (_politicalParty.getPoliticalPartyName().equals(politicalPartyName)) {
				return _politicalParty;
			}
		}
		return null;
	}
}
