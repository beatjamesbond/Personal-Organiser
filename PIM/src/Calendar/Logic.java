package Calendar;

import javax.swing.JFrame;


public class Logic {
	JFrame frame;
	
	
	public Logic (JFrame inFrame) {
		frame = inFrame;
		Calendar.GUI objGUICal = new Calendar.GUI(frame);
	}
}
