package d_322358466;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

public class Citizen {

	
	protected String name;
	protected String id;
	protected int YearOfBirth;
	protected boolean insulation;
	protected int numOfBallotBox;
	protected boolean isVote;
	protected int sicknessDays;
	protected String politicalPartyChoose;
	protected boolean protect;
	Random vote = new Random();
	protected boolean carryWeapon = false;

	public int getNumOfBallotBox() {
		return numOfBallotBox;
	}

	public void setNumOfBallotBox(int numOfBallotBox) {
		this.numOfBallotBox = numOfBallotBox;
	}

	public Citizen(String name, String id, int yearOfBirth, boolean insulation, int numOfBallotBox, int sicknessDays) throws Exception {
		this.id = id;
		this.name = name;
		YearOfBirth = yearOfBirth;
		this.insulation = insulation;
		this.numOfBallotBox = numOfBallotBox;
		this.sicknessDays = sicknessDays;
		isVote = vote.nextBoolean();
		protect = vote.nextBoolean();
		
		checkCitizenName(name);
		idCheck(id);
		yearOfBirthCheck(yearOfBirth);
	}

	public boolean isEnableToVote() {
		if (!insulation && isVote) {
			return true;
		} else if (insulation && isVote && protect) {
			return true;
		}
		return false;
	}

	public int getChosenPartyIndex(int numberOfParties) {
		Random random = new Random();
		return random.nextInt(numberOfParties);
	}

	public boolean vote(boolean vote, String yourPoliticalPartyChoose) {
		isVote = vote;
		if (isVote = false)
			return false;
		if (isVote = true)
			politicalPartyChoose = yourPoliticalPartyChoose;
		return true;
	}
	

	public String getName() {
		return name;
	}

	public String getPoliticalPartyChoose() {
		return politicalPartyChoose;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getYearOfBirth() {
		return YearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth) {
		YearOfBirth = yearOfBirth;
	}

	public boolean isInsulation() {
		return insulation;
	}

	public void setInsulation(boolean insulation) {
		this.insulation = insulation;
	}

	public boolean getIsVote() {
		return isVote;
	}

	public void setIsVote(boolean isVote) {
		this.isVote = isVote;
	}

	public boolean equals(Object other) {
		if (other instanceof Citizen) {
			Citizen citizen1 = (Citizen) other;
			if (Integer.parseInt(((Citizen) other).getId())==Integer.parseInt(id))
				return true;
		}
		return false;
	}

	public String toString() {
		StringBuffer str=new StringBuffer("");
		int age=2020-YearOfBirth;
		str.append("Citizen name = " + name + ", id=" + id + ", YearOfBirth = " + YearOfBirth + ", insulation = "
				+ insulation );
		if (((age > 17) && (age < 22)))
			str.append(",(Solider)carry weapon:"+carryWeapon+" ");
		if(sicknessDays>0)
			str.append(",sickness days"+sicknessDays);
	return str.toString();
	}
	

	public static boolean isIdValid(String id) {
		if (id.length() != 9)
			return false;
		return true;
	}

	public static boolean isYearOfBirthValid(long yearOfBirth) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		Date date = new Date();
		int currentYear = Integer.parseInt(formatter.format(date));
		return currentYear - yearOfBirth >= 18;
	}

	public static int countSicknessDays(LocalDate becameSick) {
		if (becameSick == null)
			return -1;

		Duration diff = Duration.between(LocalDate.now(), becameSick.atStartOfDay());
		int days = (int) diff.toDays();
		return days;

	}

	public boolean isCarryWeapon() {
		return carryWeapon;
	}

	public void setCarryWeapon(boolean carryWeapon) {
		this.carryWeapon = carryWeapon;
	}
	
	private void idCheck(String id) throws Exception {
		if (!Citizen.isIdValid(id)) {
			throw new Exception("id must be with 9 numbers");
		}
	}
	
	private void yearOfBirthCheck(int yearOfBirth) throws Exception {
		if (!Citizen.isYearOfBirthValid(yearOfBirth)) {
			throw new Exception("age must be 18 or older");
		}
		if (yearOfBirth == 0) {
			throw new Exception("please enter your year of birth");
		}
	}
	
	private void checkCitizenName(String name) throws Exception {
		if ("".equals(name)) {
			throw new Exception("please enter your name");
		}
	}
	
}
