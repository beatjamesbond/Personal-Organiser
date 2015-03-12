import logic.Logic;
import GUI.GUI;

public class StudentPO {
	private static GUI GUI;
	private Logic logic;
	
	public static void main(String[] args) {
		inApp();
	}
	
	private static void inApp() {
		GUI = new GUI(1024, 768);
	}
}
