package Calendar.Event;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.sql.*;

public class Event implements ActionListener {
	JFrame frame;
	JPanel EventPane;
    JLabel lblTitle, lblLocation, lblDate, lblStrtTime, lblEndTime;
    JLabel lblTxtOn, lblTxtAt;
    JButton btnEditEvent, btnBack;
    int thisEventID;
    
	public Event(JFrame inFrame, int eventID) {
		frame = inFrame;
		//add new components to new frame.
		EventPane = new JPanel();
		EventPane.setBorder(BorderFactory.createEmptyBorder(35,385,25,385));
		addEventComponents(eventID);
		thisEventID = eventID;
		
		frame.setContentPane(EventPane);
    	frame.setVisible(true);
	}
	
	public void addEventComponents(int eventID) {
		String title="", location="", MonthStr="";
		int day=0, month=0, year = 0, startTime=0, endTime=0;
		
		try (
		         // Step 1: Allocate a database "Connection" object
		         Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EventListTest", "root", ""); // MySQL
		 
		         // Step 2: Allocate a "Statement" object in the Connection
		         Statement stmt = conn.createStatement();
				 
		      ) {
		         // Step 3: Execute a SQL SELECT query, the query result
		         //  is returned in a "ResultSet" object.
		         String strSelect = "select id, title, location, day, month, year, startTime, endTime from Events";
		 
		         ResultSet rset = stmt.executeQuery(strSelect);
		 
		         // Step 4: Process the ResultSet by scrolling the cursor forward via next().
		         //  For each row, retrieve the contents of the cells with getXxx(columnName).
		         while(rset.next()) {   // Move the cursor to the next row
		            if (rset.getInt("id")==eventID) {
		            	title = rset.getString("title");
		            	location = rset.getString("location");
		            	day   = rset.getInt("day");
		            	month = rset.getInt("month");
		            	year = rset.getInt("year");
		            	startTime = rset.getInt("startTime");
		            	endTime = rset.getInt("endTime");
		            }
		         }
		 
		      } catch(SQLException ex) {
		         ex.printStackTrace();
		      }
		      // Step 5: Close the resources - Done automatically by try-with-resources
		
		
		switch (month) {
		 	case 1:MonthStr="January"; break;
		 	case 2:MonthStr="February"; break;
		 	case 3:MonthStr="March"; break;
		 	case 4:MonthStr="April"; break;
		 	case 5:MonthStr="May"; break;
		 	case 6:MonthStr="June"; break;
		 	case 7:MonthStr="July"; break;
		 	case 8:MonthStr="August"; break;
		 	case 9:MonthStr="September"; break;
		 	case 10:MonthStr="October"; break;
		 	case 11:MonthStr="November"; break;
		 	case 12:MonthStr="December"; break;
		}
		
		lblTitle = new JLabel("<HTML><u>"+title+"</u></HTML>");
		lblTitle.setFont(new Font("default", Font.BOLD, 18));
		lblTitle.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		lblTitle.setBorder(BorderFactory.createEmptyBorder(10,75,10,75));
		EventPane.add(lblTitle);
		
		if (!(location.equals(""))) {
			lblTxtAt = new JLabel("<HTML><i>at</HTML></i>");
			lblTxtAt.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			lblTxtAt.setBorder(BorderFactory.createEmptyBorder(20,75,20,75));
			EventPane.add(lblTxtAt);
	    	
			lblLocation = new JLabel(location);
			lblLocation.setFont(new Font("default", Font.BOLD, 16));
			lblLocation.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			lblLocation.setBorder(BorderFactory.createEmptyBorder(10,75,10,75));
			EventPane.add(lblLocation);
		}
		
		lblTxtOn = new JLabel("<HTML><i>on</HTML></i>");
		lblTxtOn.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		lblTxtOn.setBorder(BorderFactory.createEmptyBorder(20,75,20,75));
		EventPane.add(lblTxtOn);
		
		lblDate = new JLabel(MonthStr+" "+day+", "+year);
		lblDate.setFont(new Font("default", Font.BOLD, 16));
		lblDate.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		lblDate.setBorder(BorderFactory.createEmptyBorder(10,75,10,75));
		EventPane.add(lblDate);
		
		if (startTime!=-1) {
			lblStrtTime = new JLabel((startTime/60)+":"+(startTime%60));
			lblStrtTime.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			lblStrtTime.setBorder(BorderFactory.createEmptyBorder(10,30,50,30));
			EventPane.add(lblStrtTime);
		}
		
		if (endTime!=-1) {
			lblEndTime = new JLabel((endTime/60)+":"+(endTime%60));
			lblEndTime.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			lblEndTime.setBorder(BorderFactory.createEmptyBorder(10,30,50,30));
			EventPane.add(lblEndTime);
		}
			
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
    		Calendar.Event.Edit objEditEvent = new Calendar.Event.Edit(frame, thisEventID);	//Here you'll also have to pass through an event identifier.
    	}
    	
    	if (eventName.equals("BackEventPress")) {
    		Calendar.Logic objLogicCal = new Calendar.Logic(frame);
    	}
	}
}
