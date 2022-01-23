package election_view;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.sun.javafx.webkit.ThemeClientImpl;

import d_322358466.AllBallotBox;
import d_322358466.AllPoliticalParty;
import d_322358466.BallotBox;
import d_322358466.BallotBox.Type;
import d_322358466.CandidateForTheParty;
import d_322358466.Citizen;
import d_322358466.CitizenSet;
import d_322358466.ManagerClass;
import d_322358466.Menu;
import d_322358466.politicalParty;
import d_322358466.politicalParty.TypesOfTheParty;
import election_controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import listeners.ViewListenable;

public class ClassView implements ViewListenable {
//	FormView grCitizen = new FormView();
	FormsStack formsStack;

	private Label lblEnterToElction;
	private Vector<ViewListenable> allListeners = new Vector<ViewListenable>();
	private Button btnNewBallotBox;
	private Button btnNewPoliticalParty;
	private Button btnNewCandidate;
	private Button btnNewCitizen;
	private Button btnShowAllCitizen;
	private Button btnShowAllBallotBox;
	private Button btnShowAllPolliticalParty;
	private Button btnShowParties;
	private Button btnElection;
	private Button btnShowResult;
	private Button btnExit;
	private VBox vbMianButton;
	private boolean solider = false;
	private boolean insulation = false;
	private boolean carryWeapon = false;
	private boolean askForWeapon = false;

	// add citizen
	private FormView fvCitizen;

	private TextField tfPersonName;

	private TextField tfId;

	// private ComboBox<String> cbInsulation; = new ComboBox<String>();
	// private ComboBox<String> cbIfSick = new ComboBox<String>();
	private ComboBox<String> cbInsulation;
	private ComboBox<String> cbIfSick;
	private ComboBox<String> cbCarryWeapon;
	private TextField tfDaySick;
	private TextField tfYearOfBirth;
	boolean isInsulation = false;

	// add ballot box
	private TextField tfAdressBallotBox;

	// aad political party
	private TextField tfPoliticalPartyName;
	private ComboBox<String> cbTypeOfPoliticalParty = new ComboBox<String>();

	public ClassView(Stage primaryStage) {

		primaryStage.setTitle("Election");

		// create menu
		vbMianButton = new VBox();
		vbMianButton.setSpacing(10);
		vbMianButton.setPadding(new Insets(10));
		vbMianButton.setAlignment(Pos.CENTER_LEFT);
		btnNewCitizen = new Button("Add new Citizen");
		btnNewBallotBox = new Button("Add new ballot box");
		btnNewPoliticalParty = new Button("Add new political party");
		btnShowParties = new Button("show all political party");
		btnNewCandidate = new Button("add new candidate");
		btnShowAllBallotBox = new Button("show all ballot box");
		btnShowAllCitizen = new Button("show all citizen");
		btnShowAllPolliticalParty = new Button("show all political party");
		btnShowResult = new Button("show election result");
		btnElection = new Button("do the election");
		btnExit = new Button("Exit");
		vbMianButton.getChildren().addAll(btnNewBallotBox, btnNewCitizen, btnNewPoliticalParty, btnNewCandidate,
				btnShowAllBallotBox, btnShowAllCitizen, btnShowParties, btnElection, btnShowResult);

		// add ballot box
		FormView fvBallotBox = new FormView();
		tfAdressBallotBox = fvBallotBox.addField("adress:");
		ComboBox cbBallotBoxType = fvBallotBox.addComboBox("type of ballot box:", "normal ballot box",
				"army ballot box", "corona ballot box", "corona and army ballot box");
		Button btnEnterBallotBox = fvBallotBox.addOkButton("Enter");

		btnNewBallotBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				formsStack.show(fvBallotBox);

				btnEnterBallotBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					public void handle(MouseEvent me) {
						int typeIndex = cbBallotBoxType.getSelectionModel().getSelectedIndex();
						Type typeOfTheBallotBox = null;

						switch (typeIndex) {
						case 0:
							typeOfTheBallotBox = Type.normal;
							break;
						case 1:
							typeOfTheBallotBox = Type.army;
							break;
						case 2:
							typeOfTheBallotBox = Type.corona;
							break;
						case 3:
							typeOfTheBallotBox = Type.armyAndCorona;
							break;
						}

						String adress = tfAdressBallotBox.getText();

						try {
							BallotBox newBallotBox = new BallotBox(adress, typeOfTheBallotBox);
							addBallotBoxToModel(newBallotBox);
						} catch (Exception e) {
							addObjectToModelFailed(e.getMessage());
						}
					}
				});
			}
		});

		// add citizen
		fvCitizen = new FormView();
		tfPersonName = fvCitizen.addField("Enter Name:");
		tfId = fvCitizen.addField("id:");
		tfYearOfBirth = fvCitizen.addField("year of birth:");
		cbInsulation = fvCitizen.addComboBox("are you in insulation", "yes", "no");
		ComboBox<String> CitizenBallotBox = fvCitizen.addComboBox("choose your ballot box:");
		CitizenBallotBox.setDisable(true);
		cbIfSick = fvCitizen.addComboBox("are you sick?", "yes", "no");
		cbIfSick.setDisable(true);
		cbCarryWeapon = fvCitizen.addComboBox("do you carry weapon?", "yes", "no");
		cbCarryWeapon.setDisable(true);
		tfDaySick = fvCitizen.addField("how many days you sick?");
		tfDaySick.setDisable(true);
		Button btnEnterCitizen = fvCitizen.addOkButton("Enter");

		btnNewCitizen.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				formsStack.show(fvCitizen);
				// CitizenBallotBox.getItems().addAll(getAllTypeBoxAdressFromModel(0));
				tfYearOfBirth.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						int year = Integer.parseInt(tfYearOfBirth.getText());
						if ((year > 1998) && (year < 2003)) {
							cbCarryWeapon.setDisable(false);
							solider = true;
							askForWeapon = true;
						}
					}
				});

				cbInsulation.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						CitizenBallotBox.setDisable(true);
						if (cbInsulation.getValue().equals("yes")) {
							isInsulation = true;
							cbIfSick.setDisable(false);
							tfDaySick.setDisable(false);
						} else if (cbInsulation.getValue().equals("no")) {
							cbIfSick.setDisable(true);
							tfDaySick.setDisable(true);
						}
						CitizenBallotBox.setDisable(false);
						CitizenBallotBox.getItems().clear();
						if ((solider) && (isInsulation)) {
							CitizenBallotBox.getItems().addAll(getAllTypeBoxAdressFromModel(3));
							solider = false;
							isInsulation = false;
						} else {
							if ((solider) && (!isInsulation)) {
								CitizenBallotBox.getItems().addAll(getAllTypeBoxAdressFromModel(1));
								solider = false;
							} else {
								if ((isInsulation) && (!solider)) {
									CitizenBallotBox.getItems().addAll(getAllTypeBoxAdressFromModel(0));
									isInsulation = false;
									solider = false;
								} else
									CitizenBallotBox.getItems().addAll(getAllTypeBoxAdressFromModel(2));
							}
						}
					}
				});

				cbIfSick.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						if (cbIfSick.getValue().equals("yes")) {
							tfDaySick.setDisable(false);
						} else if (cbIfSick.getValue().equals("no")) {
							tfDaySick.setDisable(true);
						}
					}
				});

				btnEnterCitizen.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent me) {
						String id, name;
						boolean insulation = false;
						int numOfBox, sickDays = 0, yearOfBirth;

						name = tfPersonName.getText();
						id = tfId.getText();

						if (tfYearOfBirth.getText().equals("")) {
							yearOfBirth = 0;
						} else {
							yearOfBirth = Integer.parseInt(tfYearOfBirth.getText());
						}
						if (cbInsulation.getValue().equals("yes")) {
							insulation = true;
							if (cbIfSick.getValue().equals("yes")) {
								sickDays = Integer.parseInt(tfDaySick.getText());
							}
						}

						if (CitizenBallotBox.getValue() == null) {
							numOfBox = 0;
						} else {
							numOfBox = getNumOfBox(CitizenBallotBox.getValue());
						}

						try {
							Citizen newCitizen = new Citizen(name, id, yearOfBirth, insulation, numOfBox, sickDays);
							if (askForWeapon) {
								if (cbCarryWeapon.getValue().equals("true"))
									newCitizen.setCarryWeapon(true);
							}
							addCitizenToModel(newCitizen);
							ClearCitizenField();
						} catch (Exception e) {
							addObjectToModelFailed(e.getMessage());
						}
					}
				});
			}
		});

		// add political party
		FormView fvPoliticalParty = new FormView();
		TextField tfPoliticalPartyName = fvPoliticalParty.addField("political party name:");
		TextField tfPoliticalPartyYear = fvPoliticalParty.addField("political party year:");
		ComboBox cbPoliticalPartyType = fvPoliticalParty.addComboBox("political party type:", "right", "left",
				"center");
		Button btnEnterPoliticalParty = fvPoliticalParty.addOkButton("Enter");

		btnNewPoliticalParty.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				formsStack.show(fvPoliticalParty);

				btnEnterPoliticalParty.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						String politicalPartyName = tfPoliticalPartyName.getText();
						int politicalPartyYear = Integer.parseInt(tfPoliticalPartyYear.getText());
						int typeIndex = cbPoliticalPartyType.getSelectionModel().getSelectedIndex();
						TypesOfTheParty typeOfPoliticalParty = null;

						switch (typeIndex) {
						case 0:
							typeOfPoliticalParty = TypesOfTheParty.Left;
							break;
						case 1:
							typeOfPoliticalParty = TypesOfTheParty.Right;
							break;
						case 2:
							typeOfPoliticalParty = TypesOfTheParty.Center;
							break;
						}

						try {
							politicalParty newPoliticalParty = new politicalParty(politicalPartyName,
									typeOfPoliticalParty, politicalPartyYear);
							addPoliticalPartyToModel(newPoliticalParty);
						} catch (Exception _e) {
							addObjectToModelFailed(_e.getMessage());
						}
					}
				});
			}
		});

		// add candidate
		FormView fvCandidate = new FormView();
		TextField tfCandidatName = fvCandidate.addField("Enter Name:");
		TextField tfCandidatId = fvCandidate.addField("id:");
		TextField tfCandidatYear = fvCandidate.addField("year of birth:");
		ComboBox<String> cbCandidatePoliticalPartyName = fvCandidate.addComboBox("choose your political party:");
		TextField tfCandidatPrimary = fvCandidate.addField(" place at  primary:");
		ComboBox<String> cbCandidateInsulation = fvCandidate.addComboBox("are you in insulation?", "yes", "no");
		ComboBox<String> cbCandidatBallotBox = fvCandidate.addComboBox("choose your ballot box:");
		cbCandidatBallotBox.setDisable(true);
		ComboBox<String> cbCandidateIfSick = fvCandidate.addComboBox("do you sick?", 1, "yes", "no");
		cbCandidateIfSick.setDisable(true);
		TextField tfCandidatDaysSick = fvCandidate.addField("how many days do you sick?");
		tfCandidatDaysSick.setDisable(true);
		Button btnEnterCandidate = fvCandidate.addOkButton("Enter");

		btnNewCandidate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				formsStack.show(fvCandidate);
				// cbCandidatBallotBox.getItems().addAll(getAllBallotBoxAdressFromModel());
				cbCandidatePoliticalPartyName.getItems().addAll(getAllPoliticalPartiesFromModel());

				cbCandidateInsulation.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						if (cbCandidateInsulation.getValue().equals("yes")) {
							cbCandidateIfSick.setDisable(false);
							tfCandidatDaysSick.setDisable(false);
							isInsulation = true;
						} else if (cbCandidateInsulation.getValue().equals("no")) {
							cbCandidateIfSick.setDisable(true);
							tfCandidatDaysSick.setDisable(true);
						}
						cbCandidatBallotBox.setDisable(false);
						cbBallotBoxType.getItems().clear();
						if (isInsulation)
							cbCandidatBallotBox.getItems().addAll(getAllTypeBoxAdressFromModel(0));
						else
							cbCandidatBallotBox.getItems().addAll(getAllTypeBoxAdressFromModel(2));

					}
				});

				cbCandidateIfSick.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						if (cbCandidateIfSick.getValue().equals("yes")) {
							tfCandidatDaysSick.setDisable(false);
						} else if (cbCandidateIfSick.getValue().equals("no")) {
							tfCandidatDaysSick.setDisable(true);
						}
					}
				});

				btnEnterCandidate.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent me) {
						String id, name;
						boolean insulation = false;
						int numOfBox, sickDays = 0, yearOfBirth;

						name = tfCandidatName.getText();
						id = tfCandidatId.getText();

						int placeAtPrimary;

						if (tfCandidatPrimary.getText().isEmpty()) {
							placeAtPrimary = 1;
						} else {
							placeAtPrimary = Integer.parseInt(tfCandidatPrimary.getText());
						}

						if (tfCandidatYear.getText().equals("")) {
							yearOfBirth = 0;
						} else {
							yearOfBirth = Integer.parseInt(tfCandidatYear.getText());
						}
						if (cbCandidateInsulation.getValue().equals("yes")) {
							insulation = true;
							if (cbCandidateIfSick.getValue().equals("yes")) {
								sickDays = Integer.parseInt(tfCandidatDaysSick.getText());
							}
						}

						String nameOfPoliticalParty = cbCandidatePoliticalPartyName.getValue();

						if (cbCandidatBallotBox.getValue() == null) {
							numOfBox = 0;
						} else {
							numOfBox = getNumOfBox(cbCandidatBallotBox.getValue());
						}

						try {
							CandidateForTheParty newCandidate = new CandidateForTheParty(name, id, yearOfBirth,
									insulation, nameOfPoliticalParty, placeAtPrimary, numOfBox, sickDays);
							addCandidateToModel(newCandidate);
							isInsulation = false;
							cbCandidateIfSick.setDisable(true);
							cbBallotBoxType.getItems().clear();
							tfCandidatId.setText("");
							tfCandidatName.setText("");
							tfCandidatYear.setText("");
							tfCandidatDaysSick.setDisable(true);
						} catch (Exception e) {
							addObjectToModelFailed(e.getMessage());
						}
					}
				});
			}
		});
		// show all box
		FormView fvShowBallotBox = new FormView();
		btnShowAllBallotBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				fvShowBallotBox.clear();
				fvShowBallotBox.addLabel(getAllBox());
				formsStack.show(fvShowBallotBox);
			}
		});
		// show parties
		FormView fvShowAllPoliticalParties = new FormView();
		btnShowParties.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				fvShowAllPoliticalParties.clear();
				fvShowAllPoliticalParties.addLabel(getAllParties());
				formsStack.show(fvShowAllPoliticalParties);
			}
		});

		// show citizen
		FormView fvShowAllCitizen = new FormView();
		btnShowAllCitizen.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				fvShowAllCitizen.clear();
				fvShowAllCitizen.addLabel(getAllCitizen());
				formsStack.show(fvShowAllCitizen);
			}
		});

		btnElection.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				doElection();
			}
		});

		// show result
		FormView fvShowResult = new FormView();
		btnShowResult.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				fvShowResult.clear();
				fvShowResult.addLabel(getResult());
				formsStack.show(fvShowResult);
			}
		});

		formsStack = new FormsStack();
		formsStack.addForms(fvCitizen, fvCandidate, fvPoliticalParty, fvShowBallotBox, fvBallotBox, fvShowAllCitizen,
				fvShowAllPoliticalParties, fvShowResult);

		BorderPane brRoot = new BorderPane();
		brRoot.setLeft(vbMianButton);
		brRoot.setCenter(formsStack);
		primaryStage.setScene(new Scene(brRoot, 500, 500));
		primaryStage.show();
	}

	public void showInfoDialog(String title, String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);

		alert.showAndWait();
	}

	public void showWarnDialog(String title, String message) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);

		alert.showAndWait();
	}

	public void UpdateNewCitizen() {
		showInfoDialog("Add Citizen", "New citizen has been added");
	}

	public void UpdateNewBallotBox() {
		showInfoDialog("Add BallotBox", "New ballot box has been added.");
	}

	public void UpdateNewPopliticalParty() {
		showInfoDialog("Add Political Party", "New political party has been added.");
	}

	public void UpdateNewCandidate() {
		showInfoDialog("Add Candidate", "New candidate has been added.");
	}

	public void addPoliticalPartyToModel(politicalParty newPoliticalParty) throws Exception {
		for (ViewListenable l : allListeners)
			l.addPoliticalPartyToModel(newPoliticalParty);
	}

	public void addBallotBoxToModel(BallotBox newBallotBox) throws Exception {
		/// מקבל את הנתונים על האזרח ומעדכן עם ליסנטר של וויו את הקונטרולר
		for (ViewListenable l : allListeners)
			l.addBallotBoxToModel(newBallotBox);
	}

	@Override
	public void addCitizenToModel(Citizen newCitizen) throws Exception {
		/// מקבל את הנתונים על האזרח ומעדכן עם ליסנטר של וויו את הקונטרולר
		for (ViewListenable l : allListeners)
			l.addCitizenToModel(newCitizen);
	}

	public void addObjectToModelFailed(String message) {
		showWarnDialog("Error", message);
	}

	public void registerListener(ViewListenable listener) {
		allListeners.add(listener);
	}

	@Override
	public void getCitizenFromController() {
		// TODO Auto-generated method stub

	}

	public Integer getNumOfBox(String ballotBoxAdress) {
		return allListeners.get(0).getNumOfBox(ballotBoxAdress);
	}

	@Override
	public void addCandidateToModel(CandidateForTheParty newCandidate) throws Exception {
		for (ViewListenable l : allListeners) {
			l.addCandidateToModel(newCandidate);
		}
	}

	@Override
	public Vector<String> getAllPoliticalPartiesFromModel() {
		return allListeners.get(0).getAllPoliticalPartiesFromModel();
	}

	@Override
	public Vector<String> getAllBallotBoxAdressFromModel() {
		return allListeners.get(0).getAllBallotBoxAdressFromModel();
	}

	@Override
	public String getAllBox() {
		return allListeners.get(0).getAllBox();
	}

	@Override
	public String getAllParties() {
		return allListeners.get(0).getAllParties();
	}

	@Override
	public String getAllCitizen() {
		return allListeners.get(0).getAllCitizen();
	}

	@Override
	public void doElection() {
		for (ViewListenable l : allListeners) {
			l.doElection();
		}

	}

	public void sendMessage() {
		showInfoDialog("Finish", "Random election done-press the button: show result");
	}

	@Override
	public String getResult() {
		return allListeners.get(0).getResult();
	}

	@Override
	public Vector<String> getAllTypeBoxAdressFromModel(int ordinalNum) {
		return allListeners.get(0).getAllTypeBoxAdressFromModel(ordinalNum);
	}

	public void ClearCitizenField() {
		tfPersonName.setText("");
		tfId.setText("");
		tfYearOfBirth.setText("");
		tfDaySick.setText("");
		tfDaySick.setDisable(true);
		cbCarryWeapon.setDisable(true);
		cbIfSick.setDisable(true);

	}

}
