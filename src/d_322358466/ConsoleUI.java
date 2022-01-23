package d_322358466;

import java.util.Scanner;

public class ConsoleUI implements Messageble {

	private Scanner scan = new Scanner(System.in);

	@Override
	public void showMessage(String msg) {
		System.out.println(msg);
	}

	@Override
	public int getInt(String msg) {
		System.out.println(msg);
		return scan.nextInt();
	}

	@Override
	public int getInt() {
		return scan.nextInt();
	}
	
	@Override
	public String getString(String msg) {
		System.out.println(msg);
		return scan.next();
	}

	@Override
	public String getString() {
		return scan.next();
	}

}
