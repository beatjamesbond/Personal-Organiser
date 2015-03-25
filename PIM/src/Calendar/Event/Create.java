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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.util.Date;

public class Create implements ActionListener {
	JFrame frame, DlgFrame;
	JPanel NewEventPane;
    JTextField TxtTitle, TxtLocation;
    JLabel lblTitle, lblLocation;
    JButton btnSubmitNewEvent, btnCancelEvent;
    JComboBox<Integer> CombBoxDay, CombBoxYear;
	JComboBox<String> CombBoxMonth;
	JOptionPane ErrorDialog;
    
    
	public Create(JFrame inFrame) {
		frame = inFrame;
		//add new components to new frame.
		NewEventPane = new JPanel();
		NewEventPane.setBorder(BorderFactory.createEmptyBorder(35,385,25,385));
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
    	
    	//String[] dayStrings = {"1", "2", "3", "4", "5","6", "7", "8", "9", "10","11", "12", "13", "14", "15","16", "17", "18", "19", "20","21", "22", "23", "24", "25","26", "27", "28", "29", "30", "31"};
    	Integer[] dayInts = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
    	CombBoxDay = new JComboBox<Integer>(dayInts);
    	CombBoxDay.addActionListener(this);
    	NewEventPane.add(CombBoxDay);
    	
    	String[] monthStrings = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    	CombBoxMonth = new JComboBox<String>(monthStrings);
    	CombBoxMonth.addActionListener(this);
    	NewEventPane.add(CombBoxMonth);
		
    	//String[] yearStrings = {"2015", "2016", "2017"};
    	Integer[] yearInts = {2015, 2016, 2017};
    	CombBoxYear = new JComboBox<Integer>(yearInts);
    	CombBoxYear.addActionListener(this);
    	NewEventPane.add(CombBoxYear);
    	
    	btnCancelEvent = new JButton("Cancel");
    	btnCancelEvent.setActionCommand("CancelEventPress");
    	btnCancelEvent.addActionListener((ActionListener) this);
    	NewEventPane.add(btnCancelEvent);
    	
    	
    	btnSubmitNewEvent = new JButton("Save New Event");
    	btnSubmitNewEvent.setActionCommand("SubmitNewEventPress");
    	btnSubmitNewEvent.addActionListener((ActionListener) this);
    	NewEventPane.add(btnSubmitNewEvent);
	}
	
	public void actionPerformed(ActionEvent event) {
    	String eventName = event.getActionCommand();
    	
    	if (eventName.equals("SubmitNewEventPress")) {
    		
    		if (EventIsValid()) {
    			//Save
        		Calendar.Logic objLogicCal = new Calendar.Logic(frame);
        		
        		Calendar.GUI.showNewEvent("New event saved with the following values: ("+TxtTitle.getText()+"), at ("+TxtLocation.getText()+"), on ("+(Integer) CombBoxDay.getSelectedItem()+" "+(String) CombBoxMonth.getSelectedItem()+" "+(Integer) CombBoxYear.getSelectedItem()+").");
        		//And also save data to database.
    		}
    	}
    	
    	if (eventName.equals("CancelEventPress")) {
    		Calendar.Logic objLogicCal = new Calendar.Logic(frame);
    	}
	}
	
	@SuppressWarnings("deprecation")
	public Boolean EventIsValid() {
		String title, location;
		int day, month, year;
		Date today = new Date();
		Date event = new Date();
		
		title = TxtTitle.getText();
		location = TxtLocation.getText();
		day = (int) CombBoxDay.getSelectedItem();
		month = 0;
		year = (int) CombBoxYear.getSelectedItem();
		
		switch ((String) CombBoxMonth.getSelectedItem()) {
			case "Jan": month = 1;
			break;
			case "Feb": month = 2;
			break;
			case "Mar": month = 3;
			break;
			case "Apr": month = 4;
			break;
			case "May": month = 5;
			break;
			case "Jun": month = 6;
			break;
			case "Jul": month = 7;
			break;
			case "Aug": month = 8;
			break;
			case "Sep": month = 9;
			break;
			case "Oct": month = 10;
			break;
			case "Nov": month = 11;
			break;
			case "Dec": month = 12;
			break;
		}
		
		event.setYear(year-1900);
		event.setMonth(month-1);
		event.setDate(day);
		
		System.out.println(today.getDate()+", "+today.getMonth()+", "+today.getYear());
		System.out.println(event.getDate()+", "+event.getMonth()+", "+event.getYear());
		System.out.println();
		
		// Check that title exists.
		if (title.equals("")) {
			ErrorDialog.showMessageDialog(DlgFrame,"Event title was left empty...\n\nAn event must have a title!", "Invalid Title", JOptionPane.ERROR_MESSAGE);
			return (false);
		}
		
		//		(1) Check that day exists in calendar (rem. leap years)
		if (month==2) {
			if (day>28) {
				if ((year!=2016)||(day!=29)) {	//2016 is a Leap year
					ErrorDialog.showMessageDialog(DlgFrame,"Chosen day does not exist in calendar...\n\nCheck your date.", "Invalid Date", JOptionPane.ERROR_MESSAGE);
					return(false);
				}
			}
		}
		if (day==31) {
			if ((month==2)||(month==4)||(month==6)||(month==9)||(month==11)) {
				ErrorDialog.showMessageDialog(DlgFrame,"Chosen day does not exist in calendar...\n\nCheck your date.", "Invalid Date", JOptionPane.ERROR_MESSAGE);
				return(false);
			}
		}
		
		//		(2) Check that date is in the future.
		if (today.compareTo(event)> 0) {
			ErrorDialog.showMessageDialog(DlgFrame,"Event occurs in the past...\n\nEvents cannot occur in the past.", "Invalid Date", JOptionPane.ERROR_MESSAGE);
			return(false);
		}
		
		return(true);
	}
	
}
