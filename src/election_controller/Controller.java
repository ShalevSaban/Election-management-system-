package election_controller;

import java.util.Vector;

import d_322358466.BallotBox;
import d_322358466.CandidateForTheParty;
import d_322358466.Citizen;
import d_322358466.ManagerClass;
import d_322358466.politicalParty;
import election_view.ClassView;
import listeners.ViewListenable;
import listeners.modelListenable;
import model_election.ElectionModel;

public class Controller implements modelListenable, ViewListenable {
	private ClassView theView;
	private ElectionModel theModel;

	public Controller(ElectionModel m, ClassView v) {
		theModel = m;
		theView = v;

		theModel.registerListener(this);
		theView.registerListener(this);

	}

	@Override
	public void addingNewCitizen() {
		theView.UpdateNewCitizen(); /// שולח פעולה לוויו להציג הודעה על אזרח חדש
	}

	@Override
	public void addCitizenToModel(Citizen newCitizen) throws Exception {
		theModel.addCitizen(newCitizen); /// עושה פעולה במודל להוסיף אזרח בפעולת אדד סיטיזן
	}

	@Override
	public void getCitizenFromController() {

	}

	@Override
	public void addObjectToModelFailed(String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addBallotBoxToModel(BallotBox ballotBox) throws Exception {
		theModel.addBallotBox(ballotBox); /// עושה פעולה במודל להוסיף אזרח בפעולת אדד סיטיזן
	}

	@Override
	public void addingNewBallotBox() {
		theView.UpdateNewBallotBox(); /// שולח פעולה לוויו להציג הודעה על אזרח חדש
	}

	@Override
	public void addPoliticalPartyToModel(politicalParty newPoliticalParty) {
		theModel.addPoliticalParty(newPoliticalParty);
	}

	@Override
	public void addingNewPoliticalParty() {
		theView.UpdateNewPopliticalParty();
	}

	@Override
	public void addCandidateToModel(CandidateForTheParty newCandidate) throws Exception {
		theModel.addCandidate(newCandidate);
	}

	@Override
	public void addingNewCandidate() {
		theView.UpdateNewCandidate();
	}

	@Override
	public Integer getNumOfBox(String ballotBoxAdress) {
		if (ballotBoxAdress == null) {
			return null;
		}
		for (BallotBox ballotBox : theModel.getAllBallotBox()) {
			if (ballotBox.getAdress().equals(ballotBoxAdress)) {
				return ballotBox.getSerialNum();
			}
		}
		return null;
	}

	@Override
	public Vector<String> getAllPoliticalPartiesFromModel() {
		Vector<String> names = new Vector<>(0);
		for (politicalParty _politicalParty : theModel.getAllPoliticalParty()) {
			names.add(_politicalParty.getPoliticalPartyName());
		}
		return names;
	}

	@Override
	public Vector<String> getAllBallotBoxAdressFromModel() {
		Vector<String> adresses = new Vector<>(0);
		for (BallotBox ballotBox : theModel.getAllBallotBox()) {
			adresses.add(ballotBox.getAdress());
		}
		return adresses;
	}

	public String getAllBox() {
		StringBuffer bufferBox = new StringBuffer("");
		for (BallotBox ballotBox : theModel.getAllBallotBox()) {
			bufferBox.append(ballotBox.toString() + "\n");
		}
		return bufferBox.toString();
	}

	public String getAllParties() {
		StringBuffer bufferParty = new StringBuffer("");
		for (politicalParty politicalParty : theModel.getAllPoliticalParty()) {
			bufferParty.append(politicalParty.toString() + "\n");
		}
		return bufferParty.toString();

	}

	@Override
	public String getAllCitizen() {
		return theModel.showCitizen();
	}

	@Override
	public void doElection() {
		theModel.ElectionMode();

	}

	@Override
	public void electionDone() {
		theView.sendMessage();

	}

	@Override
	public String getResult() {
		return theModel.showResult();
	}

	@Override
	public Vector<String> getAllTypeBoxAdressFromModel(int ordinalNum) {
		Vector<String> adresses = new Vector<>(0);
		for (BallotBox ballotBox : theModel.getBoxType(ordinalNum)) {
			adresses.add(ballotBox.getAdress());
		}
		return adresses;
	}

}
