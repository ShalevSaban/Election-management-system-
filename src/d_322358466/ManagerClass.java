package d_322358466;

import java.util.Scanner;
import java.util.Vector;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import d_322358466.politicalParty.TypesOfTheParty;
import javax.swing.plaf.SliderUI;

public class ManagerClass {

	public static int currentYear;
	Messageble ui = new ConsoleUI();
//Messageble ui = new GraphicalUI();

	public ManagerClass() {
		currentYear = getCurrentYear();
	}

	public int getCurrentYear() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		Date date = new Date();
		return Integer.parseInt(formatter.format(date));
	}

	TypesOfTheParty type = TypesOfTheParty.Left;
	TypesOfTheParty type1 = TypesOfTheParty.Right;
	TypesOfTheParty type2 = TypesOfTheParty.Center;

	public void addBallotBoxManager(AllBallotBox allBllotBox, AllPoliticalParty allPoliticalParty) {
		int typeBox;
		String adress;
		adress = ui.getString("enter the adress of the ballot box");
		typeBox = ui.getInt("choose type of bllot box\n 1-corona\n2-army\n3-normal\n4-army and corona");
		BallotBox newBallotBox = new BallotBox(adress);
		switch (typeBox) {
		case 1:
			newBallotBox.setType(BallotBox.Type.corona);
			break;
		case 2:
			newBallotBox.setType(BallotBox.Type.army);
			break;
		case 3:
			newBallotBox.setType(BallotBox.Type.normal);
			break;
		case 4:
			newBallotBox.setType(BallotBox.Type.armyAndCorona);
			break;
		}
		allBllotBox.addBallotBox(newBallotBox);
		addPartiesToBox(allBllotBox, allPoliticalParty);

		ui.showMessage("ballot box number " + (allBllotBox.getNumOfAllBox() - 1) + " added");
	}

	public void addPartiesToBox(AllBallotBox allBallotBox, AllPoliticalParty allPoliticalParty) {
		Vector<BallotBox> arrayBox = allBallotBox.getAllBallotBox();
		Vector<politicalParty> arrayParty = allPoliticalParty.getAllParties();
		for (int i = 0; i < allPoliticalParty.getNumOfAllParty(); i++) {
			arrayBox.get(allBallotBox.getNumOfAllBox() - 1).addPoliticalParty(arrayParty.get(i));
		}

	}

	public void addCitizenManager(CitizenSet selctorsBook, AllBallotBox allBllotBox) throws Exception {
		Vector<BallotBox> arrayBallotBox = allBllotBox.getAllBallotBox();
		String name, insulationAns, id, weaponAns = "n";
		int yearOfBirth, numOfBox, age, sickDays = 0;
		boolean insulation = false, soldier = false;
		name = ui.getString("enter your name:");
		id = idCheckLoop(ui.getString("enter your id:"));
		id=idCheckLoop2( id, selctorsBook);
		yearOfBirth = yearOfBirthCheckLoop(ui.getInt("enter your year of birth:"));
		age = getCurrentYear() - yearOfBirth;
		insulationAns = ui.getString("are you in isolation?(y/n)");
		BallotBox.Type ballotBoxType = BallotBox.Type.normal;
		if (insulationAns.equalsIgnoreCase("y")) {
			insulation = true;
			char answer = ui.getString("do you  sick? (Y- YES, N - NO)").charAt(0);
			if (answer == 'Y' || answer == 'y') {
				sickDays = ui.getInt("how many days you sick?");
			}
		}
		if (((age > 17) && (age < 22)))
			soldier = true;
		if (insulation && soldier)
			ballotBoxType = BallotBox.Type.armyAndCorona;
		else if (insulation)
			ballotBoxType = BallotBox.Type.corona;
		else if (soldier)
			ballotBoxType = BallotBox.Type.army;
		else
			ballotBoxType = BallotBox.Type.normal;
		ui.showMessage("choose ballot box (" + ballotBoxType.name() + ")");
		for (BallotBox ballotBox : allBllotBox.get()) {
			if (ballotBox.isType(ballotBoxType)) {
				System.out.print(ballotBox.getSerialNum() + "-");
				ui.showMessage(ballotBox.getAdress());
			}
		}
		numOfBox = ui.getInt();
		Citizen newCitizen = new Citizen(name, id, yearOfBirth, insulation, numOfBox, sickDays);
		if (soldier)
			weaponAns = ui.getString("do you carray weapon? Y-yes ,N-no");
		if (weaponAns.equalsIgnoreCase("y"))
			newCitizen.setCarryWeapon(true);
		selctorsBook.addCitizen(newCitizen);
		arrayBallotBox.get(numOfBox).addCitizen(newCitizen);
		ui.showMessage("your new citizen has been successfully add");

	}

	public void addCandidateManager(CitizenSet selctorsBook, AllBallotBox allBllotBox,
			AllPoliticalParty allPoliticalParty) throws Exception {
		Vector<politicalParty> arrayParty = allPoliticalParty.getAllParties();
		Vector<BallotBox> arrayBallotBox = allBllotBox.getAllBallotBox();
		String name2, insulationAns, id2, weaponAns = "n";
		int yearOfBirth2, placeAtPrimary, age, numOfBox2, NumOfParty, sickDays2 = 0;
		boolean insulation = false, soldier = false;
		name2 = ui.getString("enter your name:");
		id2 = idCheckLoop(ui.getString("enter your id:"));
		id2=idCheckLoop2( id2, selctorsBook);
		yearOfBirth2 = yearOfBirthCheckLoop(ui.getInt("enter your year of birth:"));
		age = getCurrentYear() - yearOfBirth2;
		ui.showMessage("enter num of your political party");
		for (int f = 0; f < arrayParty.size(); f++) {
			System.out.print(arrayParty.get(f).getNumAtBox() + "-");
			ui.showMessage(arrayParty.get(f).getPoliticalPartyName());
		}
		NumOfParty = ui.getInt();
		placeAtPrimary = ui.getInt("enter your place at the primary");
		insulationAns = ui.getString("are you in isolation?(y/n)");

		BallotBox.Type ballotBoxType = BallotBox.Type.normal;
		if (insulationAns.equalsIgnoreCase("y")) {
			insulation = true;
			char answer = ui.getString("do you sick? (Y- YES, N - NO)").charAt(0);
			if (answer == 'Y' || answer == 'y') {
				sickDays2 = ui.getInt("how many days you sick?");
			}
		}
		if (((age > 17) && (age < 22)))
			soldier = true;
		if (insulation && soldier)
			ballotBoxType = BallotBox.Type.armyAndCorona;
		else if (insulation)
			ballotBoxType = BallotBox.Type.corona;
		else if (soldier)
			ballotBoxType = BallotBox.Type.army;
		else
			ballotBoxType = BallotBox.Type.normal;
		ui.showMessage("choose ballot box (" + ballotBoxType.name() + ")");
		for (BallotBox ballotBox : allBllotBox.get()) {
			if (ballotBox.isType(ballotBoxType)) {
				System.out.print(ballotBox.getSerialNum() + "-");
				ui.showMessage(ballotBox.getAdress());
			}
		}
		numOfBox2 = ui.getInt();

		CandidateForTheParty newCandidate = new CandidateForTheParty(name2, id2, yearOfBirth2, insulation,
				allPoliticalParty.getNameOfParty(NumOfParty), placeAtPrimary, numOfBox2, sickDays2);
		if (soldier)
		weaponAns = ui.getString("do you carray weapon? Y-yes ,N-no");
		if (weaponAns.equalsIgnoreCase("y"))
			newCandidate.setCarryWeapon(true);
		selctorsBook.addCitizen(newCandidate);
		arrayParty.get(NumOfParty).addCandidate(newCandidate);
		arrayBallotBox.get(numOfBox2).addCitizen(newCandidate);
	}

	public void addPoliticalPartyManager(AllPoliticalParty allPoliticalParty, AllBallotBox allBallotBox) {
		String nameParty;
		int yearFounded;
		String TypesOfTheParty;
		nameParty = ui.getString("enter the name of political party:");
		yearFounded = ui.getInt("enter year it was founded:");
		TypesOfTheParty = ui
				.getString("choose your political party type:" + "\n" + "left" + "\n" + "right" + "\n" + "center");

		if (TypesOfTheParty.equals("left")) {
			politicalParty newPoliticalParty = new politicalParty(nameParty, type, yearFounded);
			allPoliticalParty.addPoliticalParty(newPoliticalParty);
			allBallotBox.addPolticialParty(newPoliticalParty);
		}
		if (TypesOfTheParty.equals("right")) {
			politicalParty newPoliticalParty = new politicalParty(nameParty, type1, yearFounded);
			allPoliticalParty.addPoliticalParty(newPoliticalParty);
			allBallotBox.addPolticialParty(newPoliticalParty);

		}
		if (TypesOfTheParty.equals("center")) {
			politicalParty newPoliticalParty = new politicalParty(nameParty, type2, yearFounded);
			allPoliticalParty.addPoliticalParty(newPoliticalParty);
			allBallotBox.addPolticialParty(newPoliticalParty);
			;

		}
		ui.showMessage("new political party has been added");
	}

	public void showAllCitizenManager(CitizenSet selctorsBook) {
		selctorsBook.toString();
	}

	public void startElection(AllBallotBox allBllotBox) {
		allBllotBox.ElectionMode();
	}

	public void showResult(AllPoliticalParty allPoliticalParty, AllBallotBox allBllotBox) {
		int numOfParties = allPoliticalParty.getNumOfAllParty();
		int[] totalResult = new int[numOfParties];
		for (int i = 0; i < allBllotBox.getNumOfAllBox(); i++) {
			ui.showMessage("result at ballot box number:" + i);
			for (int j = 0; j < numOfParties; j++) {
				System.out.print("number of votes to " + allPoliticalParty.getNameOfParty(j));
				ui.showMessage(" " + allBllotBox.showResultAtBallotBox(i, j));
				totalResult[j] = totalResult[j] + allBllotBox.showResultAtBallotBox(i, j);
			}
		}
		ui.showMessage("Total restult:");
		for (int k = 0; k < totalResult.length; k++) {
			System.out.print("number of votes to " + allPoliticalParty.getNameOfParty(k));
			ui.showMessage(totalResult[k] + "");
		}
	}

	private String idCheckLoop(String id) {
		try {
			if (!Citizen.isIdValid(id)) 
				throw new Exception("id must be with 9 numbers");
		} catch (Exception e) {
			String message;
			while (!Citizen.isIdValid(id)) {
				message = e.getMessage();
				ui.showMessage(message);
				id = ui.getString("enter id again:");
			}
			
		}
		return id;
	}

	private int yearOfBirthCheckLoop(int yearOfBirth) {
		try {
			if (!Citizen.isYearOfBirthValid(yearOfBirth)) {
				throw new Exception("age must be 18 or older");
			}

		} catch (Exception e) {
			String message;
			while (!Citizen.isYearOfBirthValid(yearOfBirth)) {
				message = e.getMessage();
				ui.showMessage(message);
				yearOfBirth = ui.getInt("enter age again:");
			}
		}
		return yearOfBirth;
	}
	

	private String idCheckLoop2(String id,CitizenSet selctorsBook) {
		try {
			if (!selctorsBook.checkId(id)) 
				throw new Exception("id already Exists");
		} catch (Exception e) {
			String message;
			while (!selctorsBook.checkId(id)) {
				message = e.getMessage();
				ui.showMessage(message);
				id = ui.getString("enter id again:");
			}
			
		}
		return id;
	}
	
}