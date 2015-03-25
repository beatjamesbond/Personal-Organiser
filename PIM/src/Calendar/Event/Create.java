package Calendar.Event;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Create implements ActionListener {
	JFrame frame;
	JPanel NewEventPane;
    JTextField TxtTitle, TxtLocation;
    JLabel lblTitle, lblLocation;
    JButton btnSubmitNewEvent, btnCancelEvent;
    JComboBox<String> CombBoxDay, CombBoxMonth, CombBoxYear;
    
    
	public Create(JFrame inFrame) {
		frame = inFrame;
		//add new components to new frame.
		NewEventPane = new JPanel();
		NewEventPane.setBorder(BorderFactory.createEmptyBorder(35,375,25,375));
		addNewEventComponents();
		
		frame.setContentPane(NewEventPane);
    	frame.setVisible(true);
	}
	
	public void addNewEventComponents() {
		lblTitle = new JLabel("Title:     ");
		lblTitle.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		lblTitle.setBorder(BorderFactory.createEmptyBorder(5,10,20,10));
		NewEventPane.add(lblTitle);
		
		TxtTitle = new JTextField(12);
    	NewEventPane.add(TxtTitle);
    	
		lblLocation = new JLabel("Location:     ");
		lblLocation.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		lblLocation.setBorder(BorderFactory.createEmptyBorder(5,10,20,10));
		NewEventPane.add(lblLocation);
		
		TxtLocation = new JTextField(12);
    	NewEventPane.add(TxtLocation);
    	
    	String[] dayStrings = {"1", "2", "3", "4", "5","6", "7", "8", "9", "10","11", "12", "13", "14", "15","16", "17", "18", "19", "20","21", "22", "23", "24", "25","26", "27", "28", "29", "30", "31"};
    	CombBoxDay = new JComboBox<String>(dayStrings);
    	CombBoxDay.addActionListener(this);
    	NewEventPane.add(CombBoxDay);
    	
    	String[] monthStrings = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    	CombBoxMonth = new JComboBox<String>(monthStrings);
    	CombBoxMonth.addActionListener(this);
    	NewEventPane.add(CombBoxMonth);
		
    	String[] yearStrings = {"2015", "2016", "2017"};
    	CombBoxYear = new JComboBox<String>(yearStrings);
    	CombBoxYear.addActionListener(this);
    	NewEventPane.add(CombBoxYear);
    	
    	btnCancelEvent = new JButton("Cancel");
    	btnCancelEvent.setActionCommand("CancelEventPress");
    	btnCancelEvent.addActionListener((ActionListener) this);
    	NewEventPane.add(btnCancelEvent);
    	
    	
    	btnSubmitNewEvent = new JButton("Submit New Event");
    	btnSubmitNewEvent.setActionCommand("SubmitNewEventPress");
    	btnSubmitNewEvent.addActionListener((ActionListener) this);
    	NewEventPane.add(btnSubmitNewEvent);
	}
	
	public void actionPerformed(ActionEvent event) {
    	String eventName = event.getActionCommand();
    	
    	if (eventName.equals("SubmitNewEventPress")) {
    		Calendar.Logic objLogicCal = new Calendar.Logic(frame);
    		
    		Calendar.GUI.showNewEvent("New event saved with the following values: ("+TxtTitle.getText()+"), at ("+TxtLocation.getText()+"), on ("+(String) CombBoxDay.getSelectedItem()+" "+(String) CombBoxMonth.getSelectedItem()+" "+(String) CombBoxYear.getSelectedItem()+").");
    		//And also save data to database.
    	}
    	
    	if (eventName.equals("CancelEventPress")) {
    		Calendar.Logic objLogicCal = new Calendar.Logic(frame);
    	}
	}
}
