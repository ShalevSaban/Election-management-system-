package d_322358466;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class GraphicalUI implements Messageble {

	private Scanner scan = new Scanner(System.in);

	@Override
	public void showMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	@Override
	public int getInt(String msg) {
		return Integer.valueOf(JOptionPane.showInputDialog(msg));
	}

	@Override
	public int getInt() {
		return Integer.valueOf(JOptionPane.showInputDialog(""));
	}
	
	@Override
	public String getString(String msg) {
		return JOptionPane.showInputDialog(msg);
	}

	@Override
	public String getString() {
		return JOptionPane.showInputDialog("");
	}

}
