package model_election;

import java.util.Vector;

import d_322358466.AllBallotBox;
import d_322358466.AllPoliticalParty;
import d_322358466.BallotBox;
import d_322358466.CandidateForTheParty;
import d_322358466.Citizen;
import d_322358466.CitizenSet;
import d_322358466.politicalParty;
import d_322358466.politicalParty.TypesOfTheParty;
import listeners.modelListenable;

public class ElectionModel implements modelListenable {

	private AllPoliticalParty allPoliticalParty;
	private AllBallotBox allBallotBox;
	private CitizenSet selctorsBook;
	private Vector<modelListenable> listeners;

	TypesOfTheParty type = TypesOfTheParty.Left;
	TypesOfTheParty type1 = TypesOfTheParty.Right;
	TypesOfTheParty type2 = TypesOfTheParty.Center;

	public ElectionModel() {
		allPoliticalParty = new AllPoliticalParty();
		allBallotBox = new AllBallotBox();
		selctorsBook = new CitizenSet();
		listeners = new Vector<modelListenable>();

		try {
			setup();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void registerListener(modelListenable listener) {
		listeners.add(listener);
	}

	public void addCandidate(politicalParty likud, politicalParty blueAndWhite, politicalParty merez,
			BallotBox ballotBox1, CitizenSet selectorBook) throws Exception {
		// הוספת מועמדים למפלגת ליכוד
		CandidateForTheParty candidate1 = new CandidateForTheParty("bibi", "208493677", 2000, true, "likud", 2, 1, 0);
		likud.addCandidate(candidate1);
		selctorsBook.addCitizen(candidate1);
		ballotBox1.addCitizen(candidate1);

	}

	public void addCitizen(Citizen newCitizen) throws Exception {
		Vector<BallotBox> arrayBallotBox = allBallotBox.getAllBallotBox();
		selctorsBook.addCitizen(newCitizen);
//		System.out.println("arrayBallotBox.size:" + arrayBallotBox.size());
//		System.out.println("i:" + newCitizen.getNumOfBallotBox());
		arrayBallotBox.get(newCitizen.getNumOfBallotBox() - 1).addCitizen(newCitizen);
		for (modelListenable l : listeners) {
			l.addingNewCitizen();/// מעדכן את הקונטרולר שהוספתי אזרח
		}
	}

	public void addPartiesToBox(AllBallotBox allBallotBox, AllPoliticalParty allPoliticalParty) {
		Vector<BallotBox> arrayBox = allBallotBox.getAllBallotBox();
		Vector<politicalParty> arrayParty = allPoliticalParty.getAllParties();
		for (int i = 0; i < allPoliticalParty.getNumOfAllParty(); i++) {
			arrayBox.get(allBallotBox.getNumOfAllBox() - 1).addPoliticalParty(arrayParty.get(i));
		}
	}

	public void addPoliticalParty(politicalParty newPoliticalPary) {
		allPoliticalParty.addPoliticalParty(newPoliticalPary);
		allBallotBox.addPolticialParty(newPoliticalPary);
		for (modelListenable l : listeners) {
			l.addingNewPoliticalParty();
		}
	}

	public void addBallotBox(BallotBox newBallotBox) {
		allBallotBox.addBallotBox(newBallotBox);
		addPartiesToBox(allBallotBox, allPoliticalParty);
		for (modelListenable l : listeners) {
			l.addingNewBallotBox();
		}
	}

	public void addCandidate(CandidateForTheParty newCandidate) throws Exception {
		selctorsBook.addCitizen(newCandidate);
		allPoliticalParty.getPoliticalParty(newCandidate.getPoliticalParty()).addCandidate(newCandidate);
		allBallotBox.getAllBallotBox().get(newCandidate.getNumOfBallotBox()).addCitizen(newCandidate);
		for (modelListenable l : listeners) {
			l.addingNewCandidate();/// מעדכן את הקונטרולר שהוספתי אזרח
		}
	}

	@Override
	public void addingNewCitizen() {
		///

	}

	public int getNumOfAllBox() {
		return allBallotBox.getNumOfAllBox();
	}

	private void setup() throws Exception {

		TypesOfTheParty type = TypesOfTheParty.Left;
		TypesOfTheParty type1 = TypesOfTheParty.Right;
		TypesOfTheParty type2 = TypesOfTheParty.Center;

		// הוספת קלפיות
		BallotBox ballotBox0 = new BallotBox("tel aviv");
		allBallotBox.addBallotBox(ballotBox0);
		BallotBox ballotBox1 = new BallotBox("ramatGan");
		allBallotBox.addBallotBox(ballotBox1);
		BallotBox ballotBox2 = new BallotBox("modiin");
		ballotBox2.setType(BallotBox.Type.army);
		allBallotBox.addBallotBox(ballotBox2);
		BallotBox ballotBox3 = new BallotBox("netanya");
		ballotBox3.setType(BallotBox.Type.corona);
		allBallotBox.addBallotBox(ballotBox3);
		BallotBox ballotBox4 = new BallotBox("beerSheva");
		ballotBox4.setType(BallotBox.Type.armyAndCorona);
		allBallotBox.addBallotBox(ballotBox4);

		// הוספת מפלגות
		politicalParty likud = new politicalParty("likud", type1, 2000);
		allPoliticalParty.addPoliticalParty(likud);
		allBallotBox.addPolticialParty(likud);
		politicalParty blueAndWhite = new politicalParty("blueAndWhite", type2, 2018);
		allPoliticalParty.addPoliticalParty(blueAndWhite);
		allBallotBox.addPolticialParty(blueAndWhite);
		politicalParty merez = new politicalParty("merez", type, 2005);
		allPoliticalParty.addPoliticalParty(merez);
		allBallotBox.addPolticialParty(merez);

		// הוספת מועמדים למפלגת ליכוד
		CandidateForTheParty candidate1 = new CandidateForTheParty("bibi", "208493677", 2000, true, "likud", 2, 1, 0);
		likud.addCandidate(candidate1);
		getSelctorsBook().addCitizen(candidate1);
		ballotBox1.addCitizen(candidate1);

		CandidateForTheParty candidate2 = new CandidateForTheParty("miri", "055555555", 1960, true, "likud", 1, 2, 0);
		likud.addCandidate(candidate2);
		getSelctorsBook().addCitizen(candidate2);
		ballotBox0.addCitizen(candidate2);

		// הוספת מועמדים למפלגת ללכחול לבן
		CandidateForTheParty candidate3 = new CandidateForTheParty("beni", "666666666", 1998, true, "blueAndWhite", 1,
				2, 0);
		blueAndWhite.addCandidate(candidate3);
		getSelctorsBook().addCitizen(candidate3);
		ballotBox0.addCitizen(candidate3);

		CandidateForTheParty candidate4 = new CandidateForTheParty("yair", "666777777", 1990, true, "blueAndWhite", 2,
				1, 0);
		blueAndWhite.addCandidate(candidate4);
		getSelctorsBook().addCitizen(candidate4);
		ballotBox1.addCitizen(candidate4);

		// הוספת מועמדים למפלגת למרץ
		CandidateForTheParty candidate5 = new CandidateForTheParty("tibi", "444444444", 1987, true, "merez", 2, 1, 0);
		merez.addCandidate(candidate5);
		getSelctorsBook().addCitizen(candidate5);
		ballotBox0.addCitizen(candidate5);
		CandidateForTheParty candidate6 = new CandidateForTheParty("sara", "333333333", 1963, true, "merez", 1, 2, 0);
		merez.addCandidate(candidate6);
		getSelctorsBook().addCitizen(candidate6);
		ballotBox0.addCitizen(candidate6);

		// הוספת אזרחים
		Citizen citizen1 = new Citizen("tom", "123456789", 1015, false, 0, 0);
		getSelctorsBook().addCitizen(citizen1);
		ballotBox0.addCitizen(citizen1);
		Citizen citizen2 = new Citizen("mike", "234567890", 1019, false, 1, 0);
		getSelctorsBook().addCitizen(citizen2);
		ballotBox1.addCitizen(citizen2);
		Citizen citizen3 = new Citizen("moshe", "345678901", 1011, false, 0, 0);
		getSelctorsBook().addCitizen(citizen3);
		ballotBox0.addCitizen(citizen3);
		Citizen citizen4 = new Citizen("oron", "777777777", 1010, false, 1, 0);
		getSelctorsBook().addCitizen(citizen4);
		ballotBox1.addCitizen(citizen4);
		Citizen citizen5 = new Citizen("doron", "456789205", 1013, false, 1, 0);
		getSelctorsBook().addCitizen(citizen5);
		ballotBox1.addCitizen(citizen5);
	}

	public CitizenSet getSelctorsBook() {
		return selctorsBook;
	}

	public Vector<BallotBox> getBoxType(int ordinalNum) {
		return allBallotBox.getTypeBox(ordinalNum);
	}

	@Override
	public void addingNewBallotBox() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addingNewPoliticalParty() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addingNewCandidate() {
		// TODO Auto-generated method stub

	}

	public Vector<BallotBox> getAllBallotBox() {
		return allBallotBox.getAllBallotBox();
	}

	public Vector<politicalParty> getAllPoliticalParty() {
		return allPoliticalParty.getAllParties();
	}

	public String showCitizen() {
		return selctorsBook.toString();
	}

	public void ElectionMode() {
		allBallotBox.ElectionMode();
		for (modelListenable l : listeners) {
			l.electionDone();
		}
	}

	@Override
	public void electionDone() {
		// TODO Auto-generated method stub

	}

	public String showResult() {
		StringBuffer result = new StringBuffer("");
		int numOfParties = allPoliticalParty.getNumOfAllParty();
		int[] totalResult = new int[numOfParties];
		for (int i = 0; i < allBallotBox.getNumOfAllBox(); i++) {
			result.append("result at ballot box number:" + i + "\n");
			for (int j = 0; j < numOfParties; j++) {
				result.append("number of votes to " + allPoliticalParty.getNameOfParty(j));
				result.append(" " + allBallotBox.showResultAtBallotBox(i, j) + "\n");
				totalResult[j] = totalResult[j] + allBallotBox.showResultAtBallotBox(i, j);
			}
		}
		result.append("total result: \n");
		for (int k = 0; k < totalResult.length; k++) {
			result.append("number of votes to " + allPoliticalParty.getNameOfParty(k) + ": ");
			result.append(totalResult[k] + "\n");
		}
		return result.toString();
	}

}
