package Root;


import javax.swing.JFrame;

public class StudentPO {
	private static GUI GUI;
	private Logic logic;
	
	public static void main(String[] args) {
		inApp();
	}
	
	private static void inApp() {
		JFrame frame = new JFrame("StudentOrganiser");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GUI = new GUI(frame);
	}
}
