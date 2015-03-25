package Calendar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI implements ActionListener {
	JFrame frame;
    JPanel CalContentPane;
    static JLabel lblWelcome;
    JButton newEventBtn, backBtn;
	
	public GUI(JFrame inFrame) {
		frame = inFrame;
    	CalContentPane = new JPanel();
		CalContentPane.setBorder(BorderFactory.createEmptyBorder(35,15,25,15));
		addComponents();
		
    	frame.setContentPane(CalContentPane);
    	frame.setVisible(true);
	}

	public void addComponents() {
		lblWelcome = new JLabel("Welcome to the calendar!");
		lblWelcome.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		lblWelcome.setBorder(BorderFactory.createEmptyBorder(5,400,20,400));
		CalContentPane.add(lblWelcome);
		
		newEventBtn = new JButton("Add New Event");
    	newEventBtn.setActionCommand("NewEventPress");
    	newEventBtn.addActionListener((ActionListener) this);
    	CalContentPane.add(newEventBtn);
    	
		backBtn = new JButton("Back");
    	backBtn.setActionCommand("backPress");
    	backBtn.addActionListener((ActionListener) this);
    	CalContentPane.add(backBtn);
	}

	public void actionPerformed(ActionEvent event) {
    	String eventName = event.getActionCommand();
		
    	if (eventName.equals("NewEventPress")) {
    		Calendar.Event.Create newEventObj = new Calendar.Event.Create(frame);	//logic made to happen here?
    	} 
    	
    	if (eventName.equals("backPress")) {
    		GUI newGUIObj = new GUI(frame);			//CANNOT DECLARE OBJECT FOR CLASS OUTSIDE CURRENT PACKAGE (WHATS THE SYNTAX FOR 'ROOT' PACKAGE?)
    		//StudentPO newObj = new StudentPO();
    	} 
	}
	
	public static void showNewEvent(String str) {
		lblWelcome.setText(str);
	}

}
