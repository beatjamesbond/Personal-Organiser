package Calendar;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI implements ActionListener {
	JFrame frame;
    JPanel ContentPane, CalPane, MonthPane;
    JPanel p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14,p15,p16,p17,p18,p19,p20,p21,p22,p23,p24,p25,p26,p27,p28,p29,p30,p31;
    int selectedMonth, selectedYear;
    int highestID;
    int NE1, NE2, NE3, NE4, NE5, NE6, NE7, NE8, NE9, NE10,NE11, NE12, NE13, NE14, NE15,NE16, NE17, NE18, NE19, NE20,NE21, NE22, NE23, NE24, NE25, NE26, NE27, NE28, NE29, NE30, NE31;
	int counter = 0;
	
	public GUI(JFrame inFrame) {
		Date today = new Date();
    	selectedMonth = today.getMonth()+1;
    	selectedYear = today.getYear()+1900;

		frame = inFrame;
    	ContentPane = new JPanel();
		ContentPane.setBorder(BorderFactory.createEmptyBorder(35,15,25,15));
		addComponents();
		
    	frame.setContentPane(ContentPane);
    	frame.setVisible(true);
	}

	public void addComponents() {
		JButton newEventBtn, editEventBtn, backBtn;
    	
		newEventBtn = new JButton("Add New Event");
    	newEventBtn.setActionCommand("NewEventPress");
    	newEventBtn.addActionListener((ActionListener) this);
    	ContentPane.add(newEventBtn);
    	
		backBtn = new JButton("Back");
    	backBtn.setActionCommand("backPress");
    	backBtn.addActionListener((ActionListener) this);
    	ContentPane.add(backBtn);
    	
		CalPane = new JPanel();
		CalPane.setPreferredSize(new Dimension(900, 580));
		CalPane.setBorder(BorderFactory.createEmptyBorder(35,15,25,15));
        CalPane.setBorder(BorderFactory.createTitledBorder(""));
        addCalComponents();
		ContentPane.add(CalPane);
	}

	public void addCalComponents() {	
		JButton backMonthBtn = new JButton("<<");
		backMonthBtn.setActionCommand("BackMonthPress");
		backMonthBtn.addActionListener((ActionListener) this);
    	CalPane.add(backMonthBtn);		
    	
		JLabel MonthLbl = new JLabel(""); //TODAY'S month.
		MonthLbl.setBorder(BorderFactory.createEmptyBorder(5,100,15,100));
		String MonthStr = " ";
	 	switch (selectedMonth) {
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
	 	
	 	switch (selectedYear) {
	 		case 2015: MonthLbl.setText(MonthStr+" - 2015");break;
	 		case 2016: MonthLbl.setText(MonthStr+" - 2016");break;
	 		case 2017: MonthLbl.setText(MonthStr+" - 2017");break;
	 	}
		CalPane.add(MonthLbl);
		
		JButton forwardMonthBtn = new JButton(">>");
		forwardMonthBtn.setActionCommand("ForwardMonthPress");
		forwardMonthBtn.addActionListener((ActionListener) this);
    	CalPane.add(forwardMonthBtn);
		
		JLabel WeekdaysLbl = new JLabel("Monday                Tuesday              Wednesday          Thursday                Friday               Saturday               Sunday"); //Fix spacing by configuring mon-tues, copy&p and then adjusting.
		WeekdaysLbl.setBorder(BorderFactory.createEmptyBorder(5,50,15,50));
		CalPane.add(WeekdaysLbl);
	
		
		MonthPane = new JPanel();
		MonthPane.setLayout(new GridLayout(0,7));
		MonthPane.setPreferredSize(new Dimension(800, 450));
		MonthPane.setBorder(BorderFactory.createEmptyBorder(5,10,10,10));
		MonthPane.setBorder(BorderFactory.createTitledBorder(""));
		addMonthCalComponents();
		connectDatabase();
		CalPane.add(MonthPane);
		
		
		//Add button or something to switch into week view?
	}
	
	public void addMonthCalComponents() {
		//Change this so that the JButtons are ONLY declared inside the if(event) statements. That way you consume less memory.
		Date event = new Date();	//First of the month
		
		
		//add empty lines to fill up boxes until day 1 of month corresponds with correct column.
		event.setYear(selectedYear-1900);
		event.setMonth(selectedMonth-1);
		event.setDate(1);

		JLabel spaceLbl1 = new JLabel("");
		JLabel spaceLbl2 = new JLabel("");
		JLabel spaceLbl3 = new JLabel("");
		JLabel spaceLbl4 = new JLabel("");
		JLabel spaceLbl5 = new JLabel("");
		JLabel spaceLbl6 = new JLabel("");
		switch (event.getDay()) {
			case 0: MonthPane.add(spaceLbl1);
			case 6: MonthPane.add(spaceLbl2);
			case 5: MonthPane.add(spaceLbl3);
			case 4:	MonthPane.add(spaceLbl4);
			case 3:	MonthPane.add(spaceLbl5);
			case 2:	MonthPane.add(spaceLbl6);
		}
		
		//Use database entries to populate buttons.
		
		p1 = new JPanel();
		//p1.setPreferredSize(new Dimension(112, 85));
        p1.setBorder(BorderFactory.createTitledBorder("1"));
        p1.setLayout(new GridLayout(0,1));
		MonthPane.add(p1);
		
		p2 = new JPanel();
        p2.setBorder(BorderFactory.createTitledBorder("2"));
        p2.setLayout(new GridLayout(0,1));
		MonthPane.add(p2);
		
		p3 = new JPanel();
        p3.setBorder(BorderFactory.createTitledBorder("3"));
        p3.setLayout(new GridLayout(0,1));
		MonthPane.add(p3);
		
		
		p4 = new JPanel();
        p4.setBorder(BorderFactory.createTitledBorder("4"));
        p4.setLayout(new GridLayout(0,1));	
		MonthPane.add(p4);
		
		p5 = new JPanel();
        p5.setBorder(BorderFactory.createTitledBorder("5"));
        p5.setLayout(new GridLayout(0,1));	
		MonthPane.add(p5);
		
		p6 = new JPanel();
        p6.setBorder(BorderFactory.createTitledBorder("6"));
        p6.setLayout(new GridLayout(0,1));
		MonthPane.add(p6);
		
		p7 = new JPanel();
        p7.setBorder(BorderFactory.createTitledBorder("7"));
        p7.setLayout(new GridLayout(0,1));	
		MonthPane.add(p7);
		
		p8 = new JPanel();
        p8.setBorder(BorderFactory.createTitledBorder("8"));
        p8.setLayout(new GridLayout(0,1));
		MonthPane.add(p8);
		
		p9 = new JPanel();
        p9.setBorder(BorderFactory.createTitledBorder("9"));
        p9.setLayout(new GridLayout(0,1));
		MonthPane.add(p9);
		
		p10 = new JPanel();
        p10.setBorder(BorderFactory.createTitledBorder("10"));
        p10.setLayout(new GridLayout(0,1));
		MonthPane.add(p10);
		
		p11 = new JPanel();
        p11.setBorder(BorderFactory.createTitledBorder("11"));
        p11.setLayout(new GridLayout(0,1));
		MonthPane.add(p11);		
		
		p12 = new JPanel();
        p12.setBorder(BorderFactory.createTitledBorder("12"));
        p12.setLayout(new GridLayout(0,1));	
		MonthPane.add(p12);		
		
		p13 = new JPanel();
        p13.setBorder(BorderFactory.createTitledBorder("13"));
        p13.setLayout(new GridLayout(0,1));	
		MonthPane.add(p13);		
		
		p14 = new JPanel();
        p14.setBorder(BorderFactory.createTitledBorder("14"));
        p14.setLayout(new GridLayout(0,1));	
		MonthPane.add(p14);		
		
		p15 = new JPanel();
        p15.setBorder(BorderFactory.createTitledBorder("15"));
        p15.setLayout(new GridLayout(0,1));	
		MonthPane.add(p15);		
		
		p16 = new JPanel();
        p16.setBorder(BorderFactory.createTitledBorder("16"));
        p16.setLayout(new GridLayout(0,1));
		MonthPane.add(p16);		
		
		p17 = new JPanel();
        p17.setBorder(BorderFactory.createTitledBorder("17"));
        p17.setLayout(new GridLayout(0,1));
		MonthPane.add(p17);
		
		p18 = new JPanel();
        p18.setBorder(BorderFactory.createTitledBorder("18"));
        p18.setLayout(new GridLayout(0,1));
		MonthPane.add(p18);
		
		p19 = new JPanel();
        p19.setBorder(BorderFactory.createTitledBorder("19"));
        p19.setLayout(new GridLayout(0,1));
		MonthPane.add(p19);
		
		p20 = new JPanel();
        p20.setBorder(BorderFactory.createTitledBorder("20"));
        p20.setLayout(new GridLayout(0,1));
		MonthPane.add(p20);
		
		p21 = new JPanel();
        p21.setBorder(BorderFactory.createTitledBorder("21"));
        p21.setLayout(new GridLayout(0,1));
		MonthPane.add(p21);
		
		p22 = new JPanel();
        p22.setBorder(BorderFactory.createTitledBorder("22"));
        p22.setLayout(new GridLayout(0,1));
		MonthPane.add(p22);
		
		p23 = new JPanel();
        p23.setBorder(BorderFactory.createTitledBorder("23"));
        p23.setLayout(new GridLayout(0,1));
		MonthPane.add(p23);
		
		p24 = new JPanel();
        p24.setBorder(BorderFactory.createTitledBorder("24"));
        p24.setLayout(new GridLayout(0,1));
		MonthPane.add(p24);
		
		p25 = new JPanel();
        p25.setBorder(BorderFactory.createTitledBorder("25"));
        p25.setLayout(new GridLayout(0,1));
		MonthPane.add(p25);
		
		p26 = new JPanel();
        p26.setBorder(BorderFactory.createTitledBorder("26"));
        p26.setLayout(new GridLayout(0,1));
		MonthPane.add(p26);
		
		p27 = new JPanel();
        p27.setBorder(BorderFactory.createTitledBorder("27"));
        p27.setLayout(new GridLayout(0,1));
		MonthPane.add(p27);
		
		p28 = new JPanel();
        p28.setBorder(BorderFactory.createTitledBorder("28"));
        p28.setLayout(new GridLayout(0,1));
		MonthPane.add(p28);
		
		if ((selectedMonth!=2)||((selectedMonth==2)&&(selectedYear==2016))) {
			p29 = new JPanel();
			p29.setBorder(BorderFactory.createTitledBorder("29"));
			p29.setLayout(new GridLayout(0,1));
			MonthPane.add(p29);
		
			if (selectedMonth != 2) {
				p30 = new JPanel();
				p30.setBorder(BorderFactory.createTitledBorder("30"));
				p30.setLayout(new GridLayout(0,1));
				MonthPane.add(p30);
				
				if ((selectedMonth==1)||(selectedMonth==3)||(selectedMonth==5)||(selectedMonth==7)||(selectedMonth==8)||(selectedMonth==10)||(selectedMonth==12)) {
					p31 = new JPanel();
					p31.setBorder(BorderFactory.createTitledBorder("31"));
					p31.setLayout(new GridLayout(0,1));
					MonthPane.add(p31);
				}
			}
		}
		
	}
	
	public void actionPerformed(ActionEvent event) {
    	String eventName = event.getActionCommand();
		
    	if (eventName.equals("NewEventPress")) {
    		Calendar.Event.Create newEventObj = new Calendar.Event.Create(frame, highestID);
    	} 
    	
    	if (eventName.equals("backPress")) {
    		Root.GUI newGUIObj = new Root.GUI(frame);
    	} 
    	
    	
    	if (eventName.equals("BackMonthPress")) {
    		if (selectedMonth == 1) {
    			if (selectedYear!=2015) {
    				selectedMonth = 12;
    				selectedYear = selectedYear -1;
    			}
    		} else {
    			selectedMonth = selectedMonth-1;
    		}
    		
    		CalPane.removeAll();
    		addCalComponents();
    		frame.setContentPane(ContentPane);
    	}
    	
    	if (eventName.equals("ForwardMonthPress")) {
    		if (selectedMonth == 12) {
    			if (selectedYear!=2017) {
    				selectedMonth = 1;
    				selectedYear = selectedYear +1;
    			}
    		} else {
    			selectedMonth = selectedMonth+1;
    		}
    		
    		CalPane.removeAll();
    		addCalComponents();
    		frame.setContentPane(ContentPane);
    	} 
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	if (eventName.equals("1aPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(1,selectedMonth,selectedYear,0));
    	}
    	
    	if (eventName.equals("1bPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(1,selectedMonth,selectedYear,1));
    	}
    	
    	if (eventName.equals("1cPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(1,selectedMonth,selectedYear,2));
    	}
    	
    	
    	if (eventName.equals("2aPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(2,selectedMonth,selectedYear,0));
    	}
    	
    	if (eventName.equals("2bPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(2,selectedMonth,selectedYear,1));
    	}
    	
    	if (eventName.equals("2cPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(2,selectedMonth,selectedYear,2));
    	}
    	
    	
    	if (eventName.equals("3aPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(3,selectedMonth,selectedYear,0));
    	}
    	
    	if (eventName.equals("3bPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(3,selectedMonth,selectedYear,1));
    	}
    	
    	if (eventName.equals("3cPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(3,selectedMonth,selectedYear,2));
    	}
    	
    	
    	
    	if (eventName.equals("4aPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(4,selectedMonth,selectedYear,0));
    	}
    	
    	if (eventName.equals("4bPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(4,selectedMonth,selectedYear,1));
    	}
    	
    	if (eventName.equals("4cPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(4,selectedMonth,selectedYear,2));
    	}
    	
    	
    	
    	
    	if (eventName.equals("5aPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(5,selectedMonth,selectedYear,0));
    	}
    	
    	if (eventName.equals("5bPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(5,selectedMonth,selectedYear,1));
    	}
    	
    	if (eventName.equals("5cPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(5,selectedMonth,selectedYear,2));
    	}
    	
    	
    	
    	if (eventName.equals("6aPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(6,selectedMonth,selectedYear,0));
    	}
    	
    	if (eventName.equals("6bPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(6,selectedMonth,selectedYear,1));
    	}
    	
    	if (eventName.equals("6cPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(6,selectedMonth,selectedYear,2));
    	}
    	
    	
    	
    	if (eventName.equals("7aPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(7,selectedMonth,selectedYear,0));
    	}
    	
    	if (eventName.equals("7bPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(7,selectedMonth,selectedYear,1));
    	}
    	
    	if (eventName.equals("7cPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(7,selectedMonth,selectedYear,2));
    	}
    	
    	
    	
    	if (eventName.equals("8aPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(8,selectedMonth,selectedYear,0));
    	}
    	
    	if (eventName.equals("8bPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(8,selectedMonth,selectedYear,1));
    	}
    	
    	if (eventName.equals("8cPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(8,selectedMonth,selectedYear,2));
    	}
    	
    	
    	
    	if (eventName.equals("9aPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(9,selectedMonth,selectedYear,0));
    	}
    	
    	if (eventName.equals("9bPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(9,selectedMonth,selectedYear,1));
    	}
    	
    	if (eventName.equals("9cPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(9,selectedMonth,selectedYear,2));
    	}
    	
    	
    	
    	if (eventName.equals("10aPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(10,selectedMonth,selectedYear,0));
    	}
    	
    	if (eventName.equals("10bPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(10,selectedMonth,selectedYear,1));
    	}
    	
    	if (eventName.equals("10cPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(10,selectedMonth,selectedYear,2));
    	}
    	
    	
    	
    	if (eventName.equals("11aPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(11,selectedMonth,selectedYear,0));
    	}
    	
    	if (eventName.equals("11bPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(11,selectedMonth,selectedYear,1));
    	}
    	
    	if (eventName.equals("11cPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(11,selectedMonth,selectedYear,2));
    	}
    	
    	
    	
    	if (eventName.equals("12aPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(12,selectedMonth,selectedYear,0));
    	}
    	
    	if (eventName.equals("12bPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(12,selectedMonth,selectedYear,1));
    	}
    	
    	if (eventName.equals("12cPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(12,selectedMonth,selectedYear,2));
    	}
    	
    	
    	
    	if (eventName.equals("13aPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(13,selectedMonth,selectedYear,0));
    	}
    	
    	if (eventName.equals("13bPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(13,selectedMonth,selectedYear,1));
    	}
    	
    	if (eventName.equals("13cPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(13,selectedMonth,selectedYear,2));
    	}
    	
    	
    	
    	if (eventName.equals("14aPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(14,selectedMonth,selectedYear,0));
    	}
    	
    	if (eventName.equals("14bPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(14,selectedMonth,selectedYear,1));
    	}
    	
    	if (eventName.equals("14cPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(14,selectedMonth,selectedYear,2));
    	}
    	
    	
    	
    	if (eventName.equals("15aPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(15,selectedMonth,selectedYear,0));
    	}
    	
    	if (eventName.equals("15bPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(15,selectedMonth,selectedYear,1));
    	}
    	
    	if (eventName.equals("15cPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(15,selectedMonth,selectedYear,2));
    	}
    	
    	
    	
    	if (eventName.equals("16aPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(16,selectedMonth,selectedYear,0));
    	}
    	
    	if (eventName.equals("16bPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(16,selectedMonth,selectedYear,1));
    	}
    	
    	if (eventName.equals("16cPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(16,selectedMonth,selectedYear,2));
    	}
    	
    	
    	
    	if (eventName.equals("17aPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(17,selectedMonth,selectedYear,0));
    	}
    	
    	if (eventName.equals("17bPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(17,selectedMonth,selectedYear,1));
    	}
    	
    	if (eventName.equals("17cPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(17,selectedMonth,selectedYear,2));
    	}
    	
    	
    	
    	if (eventName.equals("18aPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(18,selectedMonth,selectedYear,0));
    	}
    	
    	if (eventName.equals("18bPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(18,selectedMonth,selectedYear,1));
    	}
    	
    	if (eventName.equals("18cPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(18,selectedMonth,selectedYear,2));
    	}
    	
    	
    	
    	if (eventName.equals("19aPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(19,selectedMonth,selectedYear,0));
    	}
    	
    	if (eventName.equals("19bPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(19,selectedMonth,selectedYear,1));
    	}
    	
    	if (eventName.equals("19cPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(19,selectedMonth,selectedYear,2));
    	}
    	

    	
    	if (eventName.equals("20aPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(20,selectedMonth,selectedYear,0));
    	}
    	
    	if (eventName.equals("20bPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(20,selectedMonth,selectedYear,1));
    	}
    	
    	if (eventName.equals("20cPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(20,selectedMonth,selectedYear,2));
    	}
    	
    	
    	
    	if (eventName.equals("21aPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(21,selectedMonth,selectedYear,0));
    	}
    	
    	if (eventName.equals("21bPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(21,selectedMonth,selectedYear,1));
    	}
    	
    	if (eventName.equals("21cPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(21,selectedMonth,selectedYear,2));
    	}
    	
    	
    	
    	if (eventName.equals("22aPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(22,selectedMonth,selectedYear,0));
    	}
    	
    	if (eventName.equals("22bPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(22,selectedMonth,selectedYear,1));
    	}
    	
    	if (eventName.equals("22cPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(22,selectedMonth,selectedYear,2));
    	}
    	
    	
    	
    	if (eventName.equals("23aPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(23,selectedMonth,selectedYear,0));
    	}
    	
    	if (eventName.equals("23bPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(23,selectedMonth,selectedYear,1));
    	}
    	
    	if (eventName.equals("23cPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(23,selectedMonth,selectedYear,2));
    	}
    	
    	
    	
    	if (eventName.equals("24aPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(24,selectedMonth,selectedYear,0));
    	}
    	
    	if (eventName.equals("24bPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(24,selectedMonth,selectedYear,1));
    	}
    	
    	if (eventName.equals("24cPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(24,selectedMonth,selectedYear,2));
    	}
    	
    	
    	
    	if (eventName.equals("25aPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(25,selectedMonth,selectedYear,0));
    	}
    	
    	if (eventName.equals("25bPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(25,selectedMonth,selectedYear,1));
    	}
    	
    	if (eventName.equals("25cPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(25,selectedMonth,selectedYear,2));
    	}
    	
    	
    	
    	if (eventName.equals("26aPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(26,selectedMonth,selectedYear,0));
    	}
    	
    	if (eventName.equals("26bPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(26,selectedMonth,selectedYear,1));
    	}
    	
    	if (eventName.equals("26cPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(26,selectedMonth,selectedYear,2));
    	}
    	
    	
    	
    	if (eventName.equals("27aPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(27,selectedMonth,selectedYear,0));
    	}
    	
    	if (eventName.equals("27bPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(27,selectedMonth,selectedYear,1));
    	}
    	
    	if (eventName.equals("27cPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(27,selectedMonth,selectedYear,2));
    	}
    	
    	
    	
    	if (eventName.equals("28aPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(28,selectedMonth,selectedYear,0));
    	}
    	
    	if (eventName.equals("28bPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(28,selectedMonth,selectedYear,1));
    	}
    	
    	if (eventName.equals("28cPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(28,selectedMonth,selectedYear,2));
    	}
    	
    	
    	
    	if (eventName.equals("29aPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(29,selectedMonth,selectedYear,0));
    	}
    	
    	if (eventName.equals("29bPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(29,selectedMonth,selectedYear,1));
    	}
    	
    	if (eventName.equals("29cPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(29,selectedMonth,selectedYear,2));
    	}
    	
    	
    	
    	if (eventName.equals("30aPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(30,selectedMonth,selectedYear,0));
    	}
    	
    	if (eventName.equals("30bPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(30,selectedMonth,selectedYear,1));
    	}
    	
    	if (eventName.equals("30cPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(30,selectedMonth,selectedYear,2));
    	}
    	
    	
    	
    	if (eventName.equals("31aPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(31,selectedMonth,selectedYear,0));
    	}
    	
    	if (eventName.equals("31bPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(31,selectedMonth,selectedYear,1));
    	}
    	
    	if (eventName.equals("31cPress")) {
    		Calendar.Event.Event showEventObj = new Calendar.Event.Event(frame, getEventID(31,selectedMonth,selectedYear,2));
    	} 	
	}
	
	public void connectDatabase() {
		NE1 = 0; NE2 = 0; NE3 = 0; NE4 = 0; NE5 = 0; NE6 = 0; NE7 = 0; NE8 = 0; NE9 = 0; NE10 = 0; NE11 = 0; NE12 = 0; NE13 = 0; NE14 = 0; NE15 = 0;
		NE16 = 0; NE17 = 0; NE18 = 0; NE19 = 0; NE20 = 0; NE21 = 0; NE22 = 0; NE23 = 0; NE24 = 0; NE25 = 0; NE26 = 0; NE27 = 0; NE28 = 0; NE29 = 0; NE30 = 0; NE31 = 0;
		
		try (
		         // Step 1: Allocate a database "Connection" object
		         Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EventListTest", "root", ""); // MySQL
		 
		         // Step 2: Allocate a "Statement" object in the Connection
		         Statement stmt = conn.createStatement();
				 
		      ) {
		         // Step 3: Execute a SQL SELECT query, the query result
		         //  is returned in a "ResultSet" object.
		         String strSelect = "select id, title, day, month, year from Events";
		 
		         ResultSet rset = stmt.executeQuery(strSelect);
		      
		         
		         // Step 4: Process the ResultSet by scrolling the cursor forward via next().
		         //  For each row, retrieve the contents of the cells with getXxx(columnName).
		         
		         highestID=0;
		         while(rset.next()) {   // Move the cursor to the next row
		             if (rset.getInt("id")>highestID) {
		            	highestID=rset.getInt("id");
		             }
		        	 if ((rset.getInt("month")==selectedMonth)&&(rset.getInt("year")==selectedYear)) {
			        	
		            	String title = rset.getString("title");
		            	
			            switch (rset.getInt("day")) {
		            		case 1: addDayOneButtons(title); break;
		            		case 2: addDayTwoButtons(title); break;
		            		case 3: addDayThreeButtons(title); break;
		            		case 4: addDayFourButtons(title); break;
		            		case 5: addDayFiveButtons(title); break;
		            		case 6: addDaySixButtons(title); break;
		            		case 7: addDaySevenButtons(title); break;
		            		case 8: addDayEightButtons(title); break;
		            		case 9: addDayNineButtons(title); break;
		            		case 10: addDayTenButtons(title); break;
		            		case 11: addDayElevenButtons(title); break;
		            		case 12: addDayTwelveButtons(title); break;
		            		case 13: addDayThirteenButtons(title); break;
		            		case 14: addDayFourteenButtons(title); break;
		            		case 15: addDayFifteenButtons(title); break;
		            		case 16: addDaySixteenButtons(title); break;
		            		case 17: addDaySeventeenButtons(title); break;
		            		case 18: addDayEighteenButtons(title); break;
		            		case 19: addDayNineteenButtons(title); break;
		            		case 20: addDayTwentyButtons(title); break;
		            		case 21: addDayTwenty1Buttons(title); break;
		            		case 22: addDayTwenty2Buttons(title); break;
		            		case 23: addDayTwenty3Buttons(title); break;
		            		case 24: addDayTwenty4Buttons(title); break;
		            		case 25: addDayTwenty5Buttons(title); break;
		            		case 26: addDayTwenty6Buttons(title); break;
		            		case 27: addDayTwenty7Buttons(title); break;
		            		case 28: addDayTwenty8Buttons(title); break;
		            		case 29: addDayTwenty9Buttons(title); break;
		            		case 30: addDayThirtyButtons(title); break;
		            		case 31: addDayThirty1Buttons(title); break;
			            }
		            }
		         }
		 
		      } catch(SQLException ex) {
		         ex.printStackTrace();
		      }
		      // Step 5: Close the resources - Done automatically by try-with-resources
	}
	
	public int getEventID(int day, int month, int year, int number) {
		try (
		         Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EventListTest", "root", ""); // MySQL 
		         Statement stmt = conn.createStatement();
		      ) {
		         String strSelect = "select id, day, month, year from Events";
		         ResultSet rset = stmt.executeQuery(strSelect);
		        
		         while(rset.next()) {   // Move the cursor to the next row

		        	 if ((rset.getInt("month")==month)&&(rset.getInt("year")==year)&&(rset.getInt("day")==day)) {
			        	if (counter==number) {
			        		return(rset.getInt("id"));
			        	} else {
			        		counter = counter + 1;
			        	}
		        	 } 
		         }
		 
		      } catch(SQLException ex) {
		         ex.printStackTrace();
		         return -1;
		      }
		
			  return -1;
		
	}
	
	
	
	public void addDayOneButtons(String title) { 
		
		if (NE1==0) {
			JButton btn1a;
			btn1a = new JButton(title);
			btn1a.setActionCommand("1aPress");
			btn1a.addActionListener((ActionListener) this);
			p1.add(btn1a);
			NE1=1;
		} else if (NE1==1) {	
			JButton btn1b;
			btn1b = new JButton(title);
			btn1b.setActionCommand("1bPress");
			btn1b.addActionListener((ActionListener) this);
			p1.add(btn1b);
			NE1=2;
		} else if (NE2==2) {	
			JButton btn1c;
			btn1c = new JButton(title);
			btn1c.setActionCommand("1cPress");
			btn1c.addActionListener((ActionListener) this);
			p1.add(btn1c);
			NE1=3;
		}
	}
	
	public void addDayTwoButtons(String title) { 
		
		if (NE2==0) {
			JButton btn2a;
			btn2a = new JButton(title);
			btn2a.setActionCommand("2aPress");
			btn2a.addActionListener((ActionListener) this);
			p2.add(btn2a);
			NE2=1;
		} else if (NE2==1) {	
			JButton btn2b;
			btn2b = new JButton(title);
			btn2b.setActionCommand("2bPress");
			btn2b.addActionListener((ActionListener) this);
			p2.add(btn2b);
			NE2=2;
		} else if (NE2==2) {	
			JButton btn2c;
			btn2c = new JButton(title);
			btn2c.setActionCommand("2cPress");
			btn2c.addActionListener((ActionListener) this);
			p2.add(btn2c);
			NE2=3;
		}
	}
	
	public void addDayThreeButtons(String title) { 
		
		if (NE3==0) {
			JButton btn3a;
			btn3a = new JButton(title);
			btn3a.setActionCommand("3aPress");
			btn3a.addActionListener((ActionListener) this);
			p3.add(btn3a);
			NE3=1;
		} else if (NE3==1) {	
			JButton btn3b;
			btn3b = new JButton(title);
			btn3b.setActionCommand("3bPress");
			btn3b.addActionListener((ActionListener) this);
			p3.add(btn3b);
			NE3=2;
		} else if (NE2==2) {	
			JButton btn3c;
			btn3c = new JButton(title);
			btn3c.setActionCommand("3cPress");
			btn3c.addActionListener((ActionListener) this);
			p3.add(btn3c);
			NE3=3;
		}
	}
	
	public void addDayFourButtons(String title) { 
		
		if (NE4==0) {
			JButton btn4a;
			btn4a = new JButton(title);
			btn4a.setActionCommand("4aPress");
			btn4a.addActionListener((ActionListener) this);
			p4.add(btn4a);
			NE4=1;
		} else if (NE4==1) {	
			JButton btn4b;
			btn4b = new JButton(title);
			btn4b.setActionCommand("4bPress");
			btn4b.addActionListener((ActionListener) this);
			p4.add(btn4b);
			NE4=2;
		} else if (NE2==2) {	
			JButton btn4c;
			btn4c = new JButton(title);
			btn4c.setActionCommand("4cPress");
			btn4c.addActionListener((ActionListener) this);
			p4.add(btn4c);
			NE4=3;
		}
	}
		
	public void addDayFiveButtons(String title) { 
		
		if (NE5==0) {
			JButton btn5a;
			btn5a = new JButton(title);
			btn5a.setActionCommand("5aPress");
			btn5a.addActionListener((ActionListener) this);
			p5.add(btn5a);
			NE5=1;
		} else if (NE5==1) {	
			JButton btn5b;
			btn5b = new JButton(title);
			btn5b.setActionCommand("5bPress");
			btn5b.addActionListener((ActionListener) this);
			p5.add(btn5b);
			NE5=2;
		} else if (NE2==2) {	
			JButton btn5c;
			btn5c = new JButton(title);
			btn5c.setActionCommand("5cPress");
			btn5c.addActionListener((ActionListener) this);
			p5.add(btn5c);
			NE5=3;
		}
	}
		
	public void addDaySixButtons(String title) { 
		
		if (NE6==0) {
			JButton btn6a;
			btn6a = new JButton(title);
			btn6a.setActionCommand("6aPress");
			btn6a.addActionListener((ActionListener) this);
			p6.add(btn6a);
			NE6=1;
		} else if (NE6==1) {	
			JButton btn6b;
			btn6b = new JButton(title);
			btn6b.setActionCommand("6bPress");
			btn6b.addActionListener((ActionListener) this);
			p6.add(btn6b);
			NE6=2;
		} else if (NE2==2) {	
			JButton btn6c;
			btn6c = new JButton(title);
			btn6c.setActionCommand("6cPress");
			btn6c.addActionListener((ActionListener) this);
			p6.add(btn6c);
			NE6=3;
		}
	}
			
	public void addDaySevenButtons(String title) { 
		
		if (NE7==0) {
			JButton btn7a;
			btn7a = new JButton(title);
			btn7a.setActionCommand("7aPress");
			btn7a.addActionListener((ActionListener) this);
			p7.add(btn7a);
			NE7=1;
		} else if (NE7==1) {	
			JButton btn7b;
			btn7b = new JButton(title);
			btn7b.setActionCommand("7bPress");
			btn7b.addActionListener((ActionListener) this);
			p7.add(btn7b);
			NE7=2;
		} else if (NE2==2) {	
			JButton btn7c;
			btn7c = new JButton(title);
			btn7c.setActionCommand("7cPress");
			btn7c.addActionListener((ActionListener) this);
			p7.add(btn7c);
			NE7=3;
		}
	}
		
	public void addDayEightButtons(String title) { 
		
		if (NE8==0) {
			JButton btn8a;
			btn8a = new JButton(title);
			btn8a.setActionCommand("8aPress");
			btn8a.addActionListener((ActionListener) this);
			p8.add(btn8a);
			NE8=1;
		} else if (NE8==1) {	
			JButton btn8b;
			btn8b = new JButton(title);
			btn8b.setActionCommand("8bPress");
			btn8b.addActionListener((ActionListener) this);
			p8.add(btn8b);
			NE8=2;
		} else if (NE2==2) {	
			JButton btn8c;
			btn8c = new JButton(title);
			btn8c.setActionCommand("8cPress");
			btn8c.addActionListener((ActionListener) this);
			p8.add(btn8c);
			NE8=3;
		}
	}
		
	public void addDayNineButtons(String title) { 
		
		if (NE9==0) {
			JButton btn9a;
			btn9a = new JButton(title);
			btn9a.setActionCommand("9aPress");
			btn9a.addActionListener((ActionListener) this);
			p9.add(btn9a);
			NE9=1;
		} else if (NE9==1) {	
			JButton btn9b;
			btn9b = new JButton(title);
			btn9b.setActionCommand("9bPress");
			btn9b.addActionListener((ActionListener) this);
			p9.add(btn9b);
			NE9=2;
		} else if (NE2==2) {	
			JButton btn9c;
			btn9c = new JButton(title);
			btn9c.setActionCommand("9cPress");
			btn9c.addActionListener((ActionListener) this);
			p9.add(btn9c);
			NE9=3;
		}
	}
	
	public void addDayTenButtons(String title) { 
		
		if (NE10==0) {
			JButton btn10a;
			btn10a = new JButton(title);
			btn10a.setActionCommand("10aPress");
			btn10a.addActionListener((ActionListener) this);
			p10.add(btn10a);
			NE10=1;
		} else if (NE10==1) {	
			JButton btn10b;
			btn10b = new JButton(title);
			btn10b.setActionCommand("10bPress");
			btn10b.addActionListener((ActionListener) this);
			p10.add(btn10b);
			NE10=2;
		} else if (NE10==2) {	
			JButton btn10c;
			btn10c = new JButton(title);
			btn10c.setActionCommand("10cPress");
			btn10c.addActionListener((ActionListener) this);
			p10.add(btn10c);
			NE10=3;
		}
	}

	
	
	
	
	public void addDayElevenButtons(String title) { 
		
		if (NE11==0) {
			JButton btn11a;
			btn11a = new JButton(title);
			btn11a.setActionCommand("11aPress");
			btn11a.addActionListener((ActionListener) this);
			p11.add(btn11a);
			NE11=1;
		} else if (NE11==1) {	
			JButton btn11b;
			btn11b = new JButton(title);
			btn11b.setActionCommand("11bPress");
			btn11b.addActionListener((ActionListener) this);
			p11.add(btn11b);
			NE11=2;
		} else if (NE11==2) {	
			JButton btn11c;
			btn11c = new JButton(title);
			btn11c.setActionCommand("11cPress");
			btn11c.addActionListener((ActionListener) this);
			p11.add(btn11c);
			NE11=3;
		}
	}
	
	public void addDayTwelveButtons(String title) { 
		
		if (NE12==0) {
			JButton btn12a;
			btn12a = new JButton(title);
			btn12a.setActionCommand("12aPress");
			btn12a.addActionListener((ActionListener) this);
			p12.add(btn12a);
			NE12=1;
		} else if (NE12==1) {	
			JButton btn12b;
			btn12b = new JButton(title);
			btn12b.setActionCommand("12bPress");
			btn12b.addActionListener((ActionListener) this);
			p12.add(btn12b);
			NE12=2;
		} else if (NE12==2) {	
			JButton btn12c;
			btn12c = new JButton(title);
			btn12c.setActionCommand("12cPress");
			btn12c.addActionListener((ActionListener) this);
			p12.add(btn12c);
			NE12=3;
		}
	}

	public void addDayThirteenButtons(String title) { 
	
		if (NE13==0) {
			JButton btn13a;
			btn13a = new JButton(title);
			btn13a.setActionCommand("13aPress");
			btn13a.addActionListener((ActionListener) this);
			p13.add(btn13a);
			NE13=1;
		} else if (NE13==1) {	
			JButton btn13b;
			btn13b = new JButton(title);
			btn13b.setActionCommand("13bPress");
			btn13b.addActionListener((ActionListener) this);
			p13.add(btn13b);
			NE13=2;
		} else if (NE13==2) {	
			JButton btn13c;
			btn13c = new JButton(title);
			btn13c.setActionCommand("13cPress");
			btn13c.addActionListener((ActionListener) this);
			p13.add(btn13c);
			NE13=3;
		}
}

	public void addDayFourteenButtons(String title) { 
	
		if (NE14==0) {
			JButton btn14a;
			btn14a = new JButton(title);
			btn14a.setActionCommand("14aPress");
			btn14a.addActionListener((ActionListener) this);
			p14.add(btn14a);
			NE14=1;
		} else if (NE14==1) {	
			JButton btn14b;
			btn14b = new JButton(title);
			btn14b.setActionCommand("14bPress");
			btn14b.addActionListener((ActionListener) this);
			p14.add(btn14b);
			NE14=2;
		} else if (NE14==2) {	
			JButton btn14c;
			btn14c = new JButton(title);
			btn14c.setActionCommand("14cPress");
			btn14c.addActionListener((ActionListener) this);
			p14.add(btn14c);
			NE14=3;
		}
	}

	public void addDayFifteenButtons(String title) { 
	
		if (NE15==0) {
			JButton btn15a;
			btn15a = new JButton(title);
			btn15a.setActionCommand("15aPress");
			btn15a.addActionListener((ActionListener) this);
			p15.add(btn15a);
			NE15=1;
		} else if (NE15==1) {	
			JButton btn15b;
			btn15b = new JButton(title);
			btn15b.setActionCommand("15bPress");
			btn15b.addActionListener((ActionListener) this);
			p15.add(btn15b);
			NE15=2;
		} else if (NE15==2) {	
			JButton btn15c;
			btn15c = new JButton(title);
			btn15c.setActionCommand("15cPress");
			btn15c.addActionListener((ActionListener) this);
			p15.add(btn15c);
			NE15=3;
		}
	}

	public void addDaySixteenButtons(String title) { 
	
		if (NE16==0) {
			JButton btn16a;
			btn16a = new JButton(title);
			btn16a.setActionCommand("16aPress");
			btn16a.addActionListener((ActionListener) this);
			p16.add(btn16a);
			NE16=1;
		} else if (NE16==1) {	
			JButton btn16b;
			btn16b = new JButton(title);
			btn16b.setActionCommand("16bPress");
			btn16b.addActionListener((ActionListener) this);
			p16.add(btn16b);
			NE16=2;
		} else if (NE16==2) {	
			JButton btn16c;
			btn16c = new JButton(title);
			btn16c.setActionCommand("16cPress");
			btn16c.addActionListener((ActionListener) this);
			p16.add(btn16c);
			NE16=3;
		}
	}

	public void addDaySeventeenButtons(String title) { 
	
		if (NE17==0) {
			JButton btn17a;
			btn17a = new JButton(title);
			btn17a.setActionCommand("17aPress");
			btn17a.addActionListener((ActionListener) this);
			p17.add(btn17a);
			NE17=1;
		} else if (NE17==1) {	
			JButton btn17b;
			btn17b = new JButton(title);
			btn17b.setActionCommand("17bPress");
			btn17b.addActionListener((ActionListener) this);
			p17.add(btn17b);
			NE17=2;
		} else if (NE17==2) {	
			JButton btn17c;
			btn17c = new JButton(title);
			btn17c.setActionCommand("17cPress");
			btn17c.addActionListener((ActionListener) this);
			p17.add(btn17c);
			NE17=3;
		}
	}

	public void addDayEighteenButtons(String title) { 
	
		if (NE18==0) {
			JButton btn18a;
			btn18a = new JButton(title);
			btn18a.setActionCommand("18aPress");
			btn18a.addActionListener((ActionListener) this);
			p18.add(btn18a);
			NE18=1;
		} else if (NE18==1) {	
			JButton btn18b;
			btn18b = new JButton(title);
			btn18b.setActionCommand("18bPress");
			btn18b.addActionListener((ActionListener) this);
			p18.add(btn18b);
			NE18=2;
		} else if (NE18==2) {	
			JButton btn18c;
			btn18c = new JButton(title);
			btn18c.setActionCommand("18cPress");
			btn18c.addActionListener((ActionListener) this);
			p18.add(btn18c);
			NE18=3;
		}
	}

	public void addDayNineteenButtons(String title) { 
	
		if (NE19==0) {
			JButton btn19a;
			btn19a = new JButton(title);
			btn19a.setActionCommand("19aPress");
			btn19a.addActionListener((ActionListener) this);
			p19.add(btn19a);
			NE19=1;
		} else if (NE19==1) {	
			JButton btn19b;
			btn19b = new JButton(title);
			btn19b.setActionCommand("19bPress");
			btn19b.addActionListener((ActionListener) this);
			p19.add(btn19b);
			NE19=2;
		} else if (NE19==2) {	
			JButton btn19c;
			btn19c = new JButton(title);
			btn19c.setActionCommand("19cPress");
			btn19c.addActionListener((ActionListener) this);
			p19.add(btn19c);
			NE19=3;
		}
	}

	public void addDayTwentyButtons(String title) { 
	
		if (NE20==0) {
			JButton btn20a;
			btn20a = new JButton(title);
			btn20a.setActionCommand("20aPress");
			btn20a.addActionListener((ActionListener) this);
			p20.add(btn20a);
			NE20=1;
		} else if (NE20==1) {	
			JButton btn20b;
			btn20b = new JButton(title);
			btn20b.setActionCommand("20bPress");
			btn20b.addActionListener((ActionListener) this);
			p20.add(btn20b);
			NE20=2;
		} else if (NE20==2) {	
			JButton btn20c;
			btn20c = new JButton(title);
			btn20c.setActionCommand("20cPress");
			btn20c.addActionListener((ActionListener) this);
			p20.add(btn20c);
			NE20=3;
		}
	}
	
	
	public void addDayTwenty1Buttons(String title) { 
		
		if (NE21==0) {
			JButton btn21a;
			btn21a = new JButton(title);
			btn21a.setActionCommand("21aPress");
			btn21a.addActionListener((ActionListener) this);
			p21.add(btn21a);
			NE21=1;
		} else if (NE21==1) {	
			JButton btn21b;
			btn21b = new JButton(title);
			btn21b.setActionCommand("21bPress");
			btn21b.addActionListener((ActionListener) this);
			p21.add(btn21b);
			NE21=2;
		} else if (NE21==2) {	
			JButton btn21c;
			btn21c = new JButton(title);
			btn21c.setActionCommand("21cPress");
			btn21c.addActionListener((ActionListener) this);
			p21.add(btn21c);
			NE21=3;
		}
	}	

	public void addDayTwenty2Buttons(String title) { 
		
		if (NE22==0) {
			JButton btn22a;
			btn22a = new JButton(title);
			btn22a.setActionCommand("22aPress");
			btn22a.addActionListener((ActionListener) this);
			p22.add(btn22a);
			NE22=1;
		} else if (NE22==1) {	
			JButton btn22b;
			btn22b = new JButton(title);
			btn22b.setActionCommand("22bPress");
			btn22b.addActionListener((ActionListener) this);
			p22.add(btn22b);
			NE22=2;
		} else if (NE22==2) {	
			JButton btn22c;
			btn22c = new JButton(title);
			btn22c.setActionCommand("22cPress");
			btn22c.addActionListener((ActionListener) this);
			p22.add(btn22c);
			NE22=3;
		}
	}	
	
	public void addDayTwenty3Buttons(String title) { 
		
		if (NE23==0) {
			JButton btn23a;
			btn23a = new JButton(title);
			btn23a.setActionCommand("23aPress");
			btn23a.addActionListener((ActionListener) this);
			p23.add(btn23a);
			NE23=1;
		} else if (NE23==1) {	
			JButton btn23b;
			btn23b = new JButton(title);
			btn23b.setActionCommand("23bPress");
			btn23b.addActionListener((ActionListener) this);
			p23.add(btn23b);
			NE23=2;
		} else if (NE23==2) {	
			JButton btn23c;
			btn23c = new JButton(title);
			btn23c.setActionCommand("23cPress");
			btn23c.addActionListener((ActionListener) this);
			p23.add(btn23c);
			NE23=3;
		}
	}	
	
	public void addDayTwenty4Buttons(String title) { 
		
		if (NE24==0) {
			JButton btn24a;
			btn24a = new JButton(title);
			btn24a.setActionCommand("24aPress");
			btn24a.addActionListener((ActionListener) this);
			p24.add(btn24a);
			NE24=1;
		} else if (NE24==1) {	
			JButton btn24b;
			btn24b = new JButton(title);
			btn24b.setActionCommand("24bPress");
			btn24b.addActionListener((ActionListener) this);
			p24.add(btn24b);
			NE24=2;
		} else if (NE24==2) {	
			JButton btn24c;
			btn24c = new JButton(title);
			btn24c.setActionCommand("24cPress");
			btn24c.addActionListener((ActionListener) this);
			p24.add(btn24c);
			NE24=3;
		}
	}	
	
	public void addDayTwenty5Buttons(String title) { 
		
		if (NE25==0) {
			JButton btn25a;
			btn25a = new JButton(title);
			btn25a.setActionCommand("25aPress");
			btn25a.addActionListener((ActionListener) this);
			p25.add(btn25a);
			NE25=1;
		} else if (NE25==1) {	
			JButton btn25b;
			btn25b = new JButton(title);
			btn25b.setActionCommand("25bPress");
			btn25b.addActionListener((ActionListener) this);
			p25.add(btn25b);
			NE25=2;
		} else if (NE25==2) {	
			JButton btn25c;
			btn25c = new JButton(title);
			btn25c.setActionCommand("25cPress");
			btn25c.addActionListener((ActionListener) this);
			p25.add(btn25c);
			NE25=3;
		}
	}	
	
	public void addDayTwenty6Buttons(String title) { 
		
		if (NE26==0) {
			JButton btn26a;
			btn26a = new JButton(title);
			btn26a.setActionCommand("26aPress");
			btn26a.addActionListener((ActionListener) this);
			p26.add(btn26a);
			NE26=1;
		} else if (NE26==1) {	
			JButton btn26b;
			btn26b = new JButton(title);
			btn26b.setActionCommand("26bPress");
			btn26b.addActionListener((ActionListener) this);
			p26.add(btn26b);
			NE26=2;
		} else if (NE26==2) {	
			JButton btn26c;
			btn26c = new JButton(title);
			btn26c.setActionCommand("26cPress");
			btn26c.addActionListener((ActionListener) this);
			p26.add(btn26c);
			NE26=3;
		}
	}	
	
	public void addDayTwenty7Buttons(String title) { 
		
		if (NE27==0) {
			JButton btn27a;
			btn27a = new JButton(title);
			btn27a.setActionCommand("27aPress");
			btn27a.addActionListener((ActionListener) this);
			p27.add(btn27a);
			NE27=1;
		} else if (NE27==1) {	
			JButton btn27b;
			btn27b = new JButton(title);
			btn27b.setActionCommand("27bPress");
			btn27b.addActionListener((ActionListener) this);
			p27.add(btn27b);
			NE27=2;
		} else if (NE27==2) {	
			JButton btn27c;
			btn27c = new JButton(title);
			btn27c.setActionCommand("27cPress");
			btn27c.addActionListener((ActionListener) this);
			p27.add(btn27c);
			NE27=3;
		}
	}	
	
	public void addDayTwenty8Buttons(String title) { 
		
		if (NE28==0) {
			JButton btn28a;
			btn28a = new JButton(title);
			btn28a.setActionCommand("28aPress");
			btn28a.addActionListener((ActionListener) this);
			p28.add(btn28a);
			NE28=1;
		} else if (NE28==1) {	
			JButton btn28b;
			btn28b = new JButton(title);
			btn28b.setActionCommand("28bPress");
			btn28b.addActionListener((ActionListener) this);
			p28.add(btn28b);
			NE28=2;
		} else if (NE28==2) {	
			JButton btn28c;
			btn28c = new JButton(title);
			btn28c.setActionCommand("28cPress");
			btn28c.addActionListener((ActionListener) this);
			p28.add(btn28c);
			NE28=3;
		}
	}	
	
	public void addDayTwenty9Buttons(String title) { 
		
		if (NE29==0) {
			JButton btn29a;
			btn29a = new JButton(title);
			btn29a.setActionCommand("29aPress");
			btn29a.addActionListener((ActionListener) this);
			p29.add(btn29a);
			NE29=1;
		} else if (NE29==1) {	
			JButton btn29b;
			btn29b = new JButton(title);
			btn29b.setActionCommand("29bPress");
			btn29b.addActionListener((ActionListener) this);
			p29.add(btn29b);
			NE29=2;
		} else if (NE29==2) {	
			JButton btn29c;
			btn29c = new JButton(title);
			btn29c.setActionCommand("29cPress");
			btn29c.addActionListener((ActionListener) this);
			p29.add(btn29c);
			NE29=3;
		}
	}	
	
	public void addDayThirtyButtons(String title) { 
		
		if (NE30==0) {
			JButton btn30a;
			btn30a = new JButton(title);
			btn30a.setActionCommand("30aPress");
			btn30a.addActionListener((ActionListener) this);
			p30.add(btn30a);
			NE30=1;
		} else if (NE30==1) {	
			JButton btn30b;
			btn30b = new JButton(title);
			btn30b.setActionCommand("30bPress");
			btn30b.addActionListener((ActionListener) this);
			p30.add(btn30b);
			NE30=2;
		} else if (NE30==2) {	
			JButton btn30c;
			btn30c = new JButton(title);
			btn30c.setActionCommand("30cPress");
			btn30c.addActionListener((ActionListener) this);
			p30.add(btn30c);
			NE30=3;
		}
	}	
	
	public void addDayThirty1Buttons(String title) { 
		
		if (NE31==0) {
			JButton btn31a;
			btn31a = new JButton(title);
			btn31a.setActionCommand("31aPress");
			btn31a.addActionListener((ActionListener) this);
			p31.add(btn31a);
			NE31=1;
		} else if (NE31==1) {	
			JButton btn31b;
			btn31b = new JButton(title);
			btn31b.setActionCommand("31bPress");
			btn31b.addActionListener((ActionListener) this);
			p31.add(btn31b);
			NE31=2;
		} else if (NE31==2) {	
			JButton btn31c;
			btn31c = new JButton(title);
			btn31c.setActionCommand("31cPress");
			btn31c.addActionListener((ActionListener) this);
			p31.add(btn31c);
			NE31=3;
		}
	}	

	
}
