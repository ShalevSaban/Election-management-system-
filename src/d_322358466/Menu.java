package d_322358466;

import java.util.Scanner;

import d_322358466.politicalParty.TypesOfTheParty;

public class Menu {

	public static void main(String[] args) throws Exception {
		ManagerClass manager = new ManagerClass();
		TypesOfTheParty type = TypesOfTheParty.Left;
		TypesOfTheParty type1 = TypesOfTheParty.Right;
		TypesOfTheParty type2 = TypesOfTheParty.Center;

		AllPoliticalParty allPoliticalParty = new AllPoliticalParty();
		AllBallotBox allBallotBox = new AllBallotBox();
		CitizenSet selctorsBook = new CitizenSet();
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
		selctorsBook.addCitizen(candidate1);
		ballotBox1.addCitizen(candidate1);

		CandidateForTheParty candidate2 = new CandidateForTheParty("miri", "055555555", 1960, true, "likud", 1, 2, 0);
		likud.addCandidate(candidate2);
		selctorsBook.addCitizen(candidate2);
		ballotBox0.addCitizen(candidate2);

		// הוספת מועמדים למפלגת ללכחול לבן
		CandidateForTheParty candidate3 = new CandidateForTheParty("beni", "666666666", 2008, true, "blueAndWhite", 1,
				2, 0);
		blueAndWhite.addCandidate(candidate3);
		selctorsBook.addCitizen(candidate3);
		ballotBox0.addCitizen(candidate3);

		CandidateForTheParty candidate4 = new CandidateForTheParty("yair", "666777777", 1990, true, "blueAndWhite", 2,
				1, 0);
		blueAndWhite.addCandidate(candidate4);
		selctorsBook.addCitizen(candidate4);
		ballotBox1.addCitizen(candidate4);

		// הוספת מועמדים למפלגת למרץ
		CandidateForTheParty candidate5 = new CandidateForTheParty("tibi", "444444444", 1987, true, "merez", 2, 1, 0);
		merez.addCandidate(candidate5);
		selctorsBook.addCitizen(candidate5);
		ballotBox0.addCitizen(candidate5);
		CandidateForTheParty candidate6 = new CandidateForTheParty("sara", "333333333", 1963, true, "merez", 1, 2, 0);
		merez.addCandidate(candidate6);
		selctorsBook.addCitizen(candidate6);
		ballotBox0.addCitizen(candidate6);

		// הוספת אזרחים
		Citizen citizen1 = new Citizen("tom", "123456789", 2015, false, 0, 0);
		selctorsBook.addCitizen(citizen1);
		ballotBox0.addCitizen(citizen1);
		Citizen citizen2 = new Citizen("mike", "234567890", 2019, false, 1, 0);
		selctorsBook.addCitizen(citizen2);
		ballotBox1.addCitizen(citizen2);
		Citizen citizen3 = new Citizen("moshe", "345678901", 2011, false, 0, 0);
		selctorsBook.addCitizen(citizen3);
		ballotBox0.addCitizen(citizen3);
		Citizen citizen4 = new Citizen("oron", "777777777", 2010, false, 1, 0);
		selctorsBook.addCitizen(citizen4);
		ballotBox1.addCitizen(citizen4);
		Citizen citizen5 = new Citizen("doron", "456789205", 2013, false, 1, 0);
		selctorsBook.addCitizen(citizen5);
		ballotBox1.addCitizen(citizen5);

		Messageble ui = new ConsoleUI();
	//Messageble ui = new GraphicalUI();
		int num = 0;

		do {
			num = ui.getInt("enter:\n"
					+ "1- for a new ballot box\n2-for a new Citizen\n3-for a new political party\n4-for a new candidate\n"
					+ "5-show all ballotbox\n6-show all citizens\n7-show all parties\n8-for election\n9-show the result\n10-exit ");

			switch (num) {
			case 1:
				manager.addBallotBoxManager(allBallotBox, allPoliticalParty);
				break;

			case 2:
				manager.addCitizenManager(selctorsBook, allBallotBox);
				break;

			case 3:
				manager.addPoliticalPartyManager(allPoliticalParty, allBallotBox);
				break;

			case 4:
				manager.addCandidateManager(selctorsBook, allBallotBox, allPoliticalParty);
				ui.showMessage("your new candidate has been successfully add");
				break;

			case 5:
				ui.showMessage(allBallotBox.toString());
				break;

			case 6:
				ui.showMessage(selctorsBook.toString());
				break;

			case 7:
				ui.showMessage(allPoliticalParty.toString());
				break;

			case 8:
				allBallotBox.ElectionMode();
				ui.showMessage("random election finshed-tap 9 to see the result");
				break;

			case 9:
				manager.showResult(allPoliticalParty, allBallotBox);
				break;

			}
		} while (num != 10);
		ui.showMessage("we hope to see you next electiont");

	}
}
