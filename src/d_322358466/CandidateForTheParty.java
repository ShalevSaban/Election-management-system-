package d_322358466;


public class CandidateForTheParty extends Citizen implements Comparable<CandidateForTheParty> {
	protected String politicalParty;
	protected int placeAtPrimary;

	public CandidateForTheParty(String name, String id, int yearOfBirth, boolean insulation,
			String politicalParty, int placeAtPrimary,int numOfBllotBox,int sicknessDays) throws Exception {
		super(name, id, yearOfBirth, insulation,numOfBllotBox, sicknessDays);
		this.politicalParty = politicalParty;
		this.placeAtPrimary = placeAtPrimary;
	}
	
	
	public int getPlaceAtPrimary() {
		return placeAtPrimary;
	}

	public void setPlaceAtPrimary(int placeAtPrimary) {
		this.placeAtPrimary = placeAtPrimary;
	}
	

	@Override
	public String toString() {
		String s =  " ,politicalParty = " + politicalParty + ", number of ballotBox = " + numOfBallotBox + ", placeAtPrimary = " + placeAtPrimary;
		String Father = super.toString();
		return Father + s;
	}

	public String getPoliticalParty() {
		return politicalParty;
	}

	
	

	@Override
    public int compareTo(CandidateForTheParty other) {
		if(this.placeAtPrimary<other.placeAtPrimary)
			return -1;
		if(this.placeAtPrimary>other.placeAtPrimary)
			return 1;
		else 
			return 0;	
	}
}
