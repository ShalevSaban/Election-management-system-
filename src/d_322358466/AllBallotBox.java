package d_322358466;

import java.util.Vector;

public class AllBallotBox {

	protected Vector<Integer> electionResult;
	protected Vector<Integer> politicalPartyVotes;
	protected Vector<BallotBox> allBallotBox;

	public AllBallotBox() {
		electionResult = new Vector<Integer>();
		politicalPartyVotes = new Vector<Integer>();
		allBallotBox = new Vector<BallotBox>();
	}

	public void addBallotBox(BallotBox newBallotBox) {
		allBallotBox.add(newBallotBox);
		newBallotBox.setSerialNum(allBallotBox.size() - 1);
	}

	public void addPolticialParty(politicalParty party) {
		politicalPartyVotes.add(0);

		for (int i = 0; i < allBallotBox.size(); i++) {
			allBallotBox.get(i).addPoliticalParty(party);
		}
	}

	public String toString() {
		StringBuffer str = new StringBuffer("");
		str.append("this is all the ballot box:" + "\n");
		for (int i = 0; i < allBallotBox.size(); i++) {
			str.append(allBallotBox.get(i).toString() + "\n");
		}
		return str.toString();
	}

	public void ElectionMode() {
		for (int i = 0; i < allBallotBox.size(); i++) {
			allBallotBox.get(i).askCitizen();
		}
	}

	public int showResultAtBallotBox(int numOfBallotBox, int numOfparty) {
		return allBallotBox.get(numOfBallotBox).resultAtPoliticalParty(numOfparty);
	}

	public Vector<BallotBox> getAllBallotBox() {
		return allBallotBox;
	}

	public double getPrecentVoters(int numOfBallot) {
		return allBallotBox.get(numOfBallot).getPercentVoters();
	}

	public int getNumOfAllBox() {
		return allBallotBox.size();
	}
	
	public Vector<BallotBox> get() {
		return allBallotBox;
	}
	
	public Vector<BallotBox> getTypeBox(int ordinalNum){
		Vector<BallotBox> TypeBox=new Vector<BallotBox>();
		for(int i=0;i<allBallotBox.size();i++) {
			if(allBallotBox.get(i).getType().ordinal()==ordinalNum)
            TypeBox.add(allBallotBox.get(i));
		}
		return TypeBox;
	}
	
	public Integer getNumOfBox(String ballotBoxAdress) {
		for (BallotBox ballotBox: allBallotBox) {
			if (ballotBox.getAdress().equals(ballotBoxAdress)) {
				return ballotBox.serialNum;
			}
		}
		return null;
	}

}
