package d_322358466;

import java.util.Vector;

public class BallotBox {
	public static enum Type {
		corona, army, normal, armyAndCorona
	}

	protected int serialNum;
	protected String adress;
	protected Vector<Citizen> citizensPremission;
	protected int result;
	private Vector<Integer> politicalPartyVotes;
	private Type type = Type.normal;

	public BallotBox(String adress) {
		this.adress = adress;
		citizensPremission = new Vector<Citizen>();
		politicalPartyVotes = new Vector<Integer>();
	}
	
	public BallotBox(String adress, Type type) throws Exception {
		this.adress = adress;
		this.type = type;
		citizensPremission = new Vector<Citizen>();
		politicalPartyVotes = new Vector<Integer>();
		
		checkAdress(adress);
		checkType(type);
	}

	public void askCitizen() {// בודק אם רוצים להבציע ואם מצביעים אז מוסיף 1 למפלגה שנבחרה
		int voteForChoosenParty, chosenParty;// מספר קולות למפלגה,מפלגה נבחרת
		for (Citizen citizen : citizensPremission) {/// עובר על המערך של האזרחים עם רשות
			if (citizen.isEnableToVote()) {// אם האזרח מצביע וגם מוגן
				chosenParty = citizen.getChosenPartyIndex(politicalPartyVotes.size());// בוחר מספר של מפלגה רנדומלי מבין
																						// רשימת המפלגות
				voteForChoosenParty = politicalPartyVotes.get(chosenParty);// תכניס את הערך של מספר הקולות
				vote(chosenParty, voteForChoosenParty);
			}
		}
	}

	public void vote(int chosenParty, int voteForChoosenParty) {
		politicalPartyVotes.set(chosenParty, voteForChoosenParty + 1);// תעדכן את מספר הקולות של המפלגה
	}

	public int resultAtPoliticalParty(int num) {
		return politicalPartyVotes.get(num);
	}

	public void addPoliticalParty(politicalParty party) {// מוסיף את המפלגה למערך של מפלגות לפי סדר המספר הסידורי שלהן
		politicalPartyVotes.add(0);
		party.setNumAtBox(politicalPartyVotes.size() - 1);
	}

	public void addCitizen(Citizen people) {
		citizensPremission.add(people);
	}

	public boolean equal(Object other) {
		if (other instanceof BallotBox) {
			BallotBox ballotBox1 = (BallotBox) other;
			if (!(ballotBox1.getAdress().equals(adress)))
				return false;
			if (!(ballotBox1.getSerialNum() == serialNum))
				return false;
			if (!(ballotBox1.getResult() == result))
				return false;

		}
		return true;
	}

	public int getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(int serialNum) {
		this.serialNum = serialNum;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Vector<Citizen> getCitizenPremission() {
		return citizensPremission;
	}

	public void setCitizenPremission(Vector<Citizen> citizensPremission) {
		this.citizensPremission = citizensPremission;
	}

	public double getPercentVoters() {
		int allVoters, counter = 0;
		allVoters = citizensPremission.size();
		for (int i = 0; i < allVoters - 1; i++) {
			if (citizensPremission.get(i).isVote)
				counter++;
		}

		return (counter / allVoters) * 100;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	public boolean isType(Type type) {
		return this.type.equals(type);
	}

	public String toString() {
		StringBuffer str = new StringBuffer("");
		str.append("ballot box number " + serialNum + " at " + adress + "\nlist of citizen with premission:\n");
		for (int i = 0; i < citizensPremission.size(); i++) {
			str.append(citizensPremission.get(i).toString() + "\n");
		}
		return str.toString();
	}
	
	private void checkAdress(String adress) throws Exception {
		if(adress.isEmpty()) {
			throw new Exception("enter the ballot box adress");
		}
	}
	
	private void checkType(Type type) throws Exception {
		if(type == null) {
			throw new Exception("choose ballot box type");
		}
	}

}
