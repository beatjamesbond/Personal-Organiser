package Calendar.Event;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Event implements ActionListener {
	JFrame frame;
	JPanel EventPane;
    JLabel lblTitle, lblLocation, lblDate, lblStrtTime, lblEndTime;
    JLabel lblTxtOn, lblTxtAt;
    JButton btnEditEvent, btnBack;
    
    
	public Event(JFrame inFrame) {
		frame = inFrame;
		//add new components to new frame.
		EventPane = new JPanel();
		EventPane.setBorder(BorderFactory.createEmptyBorder(35,385,25,385));
		addEventComponents();
		
		frame.setContentPane(EventPane);
    	frame.setVisible(true);
	}
	
	public void addEventComponents() {
		lblTitle = new JLabel("<HTML><u>TITLE</u></HTML>");
		lblTitle.setFont(new Font("default", Font.BOLD, 18));
		lblTitle.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		lblTitle.setBorder(BorderFactory.createEmptyBorder(10,75,10,75));
		EventPane.add(lblTitle);
		
		lblTxtAt = new JLabel("<HTML><i>at</HTML></i>");
		lblTxtAt.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		lblTxtAt.setBorder(BorderFactory.createEmptyBorder(20,75,20,75));
		EventPane.add(lblTxtAt);
    	
		lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("default", Font.BOLD, 16));
		lblLocation.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		lblLocation.setBorder(BorderFactory.createEmptyBorder(10,75,10,75));
		EventPane.add(lblLocation);
		
		lblTxtOn = new JLabel("<HTML><i>on</HTML></i>");
		lblTxtOn.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		lblTxtOn.setBorder(BorderFactory.createEmptyBorder(20,75,20,75));
		EventPane.add(lblTxtOn);
		
		lblDate = new JLabel("Day, Month, Year");
		lblDate.setFont(new Font("default", Font.BOLD, 16));
		lblDate.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		lblDate.setBorder(BorderFactory.createEmptyBorder(10,75,10,75));
		EventPane.add(lblDate);
    	
		lblStrtTime = new JLabel("00:00");
		lblStrtTime.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		lblStrtTime.setBorder(BorderFactory.createEmptyBorder(10,30,50,30));
		EventPane.add(lblStrtTime);
		
		lblEndTime = new JLabel("00:00");
		lblEndTime.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		lblEndTime.setBorder(BorderFactory.createEmptyBorder(10,30,50,30));
		EventPane.add(lblEndTime);
    	
    	btnBack = new JButton("Back");
    	btnBack.setActionCommand("BackEventPress");
    	btnBack.addActionListener((ActionListener) this);
    	EventPane.add(btnBack);
    	  	
    	btnEditEvent = new JButton("Edit Event");
    	btnEditEvent.setActionCommand("EditEventPress");
    	btnEditEvent.addActionListener((ActionListener) this);
    	EventPane.add(btnEditEvent);
	}
	
	public void actionPerformed(ActionEvent event) {
    	String eventName = event.getActionCommand();
    	
    	if (eventName.equals("EditEventPress")) {
    		Calendar.Event.Edit objEditEvent = new Calendar.Event.Edit(frame);	//Here you'll also have to pass through an event identifier.
    	}
    	
    	if (eventName.equals("BackEventPress")) {
    		Calendar.Logic objLogicCal = new Calendar.Logic(frame);
    	}
	}
	
}
