package listeners;

import java.util.Vector;

import d_322358466.BallotBox;
import d_322358466.CandidateForTheParty;
import d_322358466.Citizen;
import d_322358466.politicalParty;

public interface ViewListenable {
	void addObjectToModelFailed(String message);

	void addCitizenToModel(Citizen citizen) throws Exception;

	void getCitizenFromController();

	void doElection();

	void addBallotBoxToModel(BallotBox ballotBox) throws Exception;

	void addPoliticalPartyToModel(politicalParty newPoliticalParty) throws Exception;

	void addCandidateToModel(CandidateForTheParty newCandidate) throws Exception;

	String getAllBox();

	String getAllParties();

	String getAllCitizen();

	String getResult();

	Integer getNumOfBox(String ballotBoxName);

	Vector<String> getAllPoliticalPartiesFromModel();

	Vector<String> getAllBallotBoxAdressFromModel();

	Vector<String> getAllTypeBoxAdressFromModel(int ordinalNum);

}
