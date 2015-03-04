package group132015.pim.start;

import group132015.pim.gui.GUI;
import group132015.pim.logic.Logic;

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
