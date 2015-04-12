package Calendar;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

public class GUI implements ActionListener {
	JFrame frame;
    JPanel ContentPane, CalPane, MonthPane;
    static JLabel lblWelcome;
    int selectedMonth, selectedYear;
	
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
		
		lblWelcome = new JLabel("Welcome to the calendar!");
		lblWelcome.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		lblWelcome.setBorder(BorderFactory.createEmptyBorder(5,400,20,400));
		ContentPane.add(lblWelcome);
		
		editEventBtn = new JButton("Edit Event");
		editEventBtn.setActionCommand("EditEventPress");
		editEventBtn.addActionListener((ActionListener) this);
    	ContentPane.add(editEventBtn);
    	
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
		CalPane.add(MonthPane);
		
		
		//Add button or something to switch into week view?
	}
	
	public void addMonthCalComponents() {
		JButton btn1a,btn2a,btn3a,btn4a,btn5a,btn6a,btn7a,btn8a,btn9a,btn10a,btn11a,btn12a,btn13a,btn14a,btn15a,btn16a,btn17a,btn18a,btn19a,btn20a,btn21a,btn22a,btn23a,btn24a,btn25a,btn26a,btn27a,btn28a,btn29a,btn30a,btn31a;
		JButton btn1b,btn2b,btn3b,btn4b,btn5b,btn6b,btn7b,btn8b,btn9b,btn10b,btn11b,btn12b,btn13b,btn14b,btn15b,btn16b,btn17b,btn18b,btn19b,btn20b,btn21b,btn22b,btn23b,btn24b,btn25b,btn26b,btn27b,btn28b,btn29b,btn30b,btn31b;
		JButton btn1c,btn2c,btn3c,btn4c,btn5c,btn6c,btn7c,btn8c,btn9c,btn10c,btn11c,btn12c,btn13c,btn14c,btn15c,btn16c,btn17c,btn18c,btn19c,btn20c,btn21c,btn22c,btn23c,btn24c,btn25c,btn26c,btn27c,btn28c,btn29c,btn30c,btn31c;
		JPanel p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14,p15,p16,p17,p18,p19,p20,p21,p22,p23,p24,p25,p26,p27,p28,p29,p30,p31;
		Date event = new Date();	//First of the month
		
		
		
		
		
		
		
		//add empty lines to fill up boxes until day 1 of month corresponds with correct column.
		event.setYear(selectedYear-1900);
		event.setMonth(selectedMonth-1);
		event.setDate(1);
		
		System.out.println(event.getDay());
		
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
		
		
		
		
		//Use database entries to populate correct 'Jlists'.
		
		p1 = new JPanel();
		//p1.setPreferredSize(new Dimension(112, 85));
        p1.setBorder(BorderFactory.createTitledBorder("1"));
        p1.setLayout(new GridLayout(0,1));
			btn1a = new JButton("OneA");
			btn1a.setActionCommand("1aPress");
			btn1a.addActionListener((ActionListener) this);
			p1.add(btn1a);
			
			btn1b = new JButton("OneB");
			btn1b.setActionCommand("1bPress");
			btn1b.addActionListener((ActionListener) this);
			p1.add(btn1b);
			
			btn1c = new JButton("OneC");
			btn1c.setActionCommand("1cPress");
			btn1c.addActionListener((ActionListener) this);
			p1.add(btn1c);
		MonthPane.add(p1);
		
		p2 = new JPanel();
        p2.setBorder(BorderFactory.createTitledBorder("2"));
        p2.setLayout(new GridLayout(0,1));
			btn2a = new JButton("TwoA");
			btn2a.setActionCommand("2aPress");
			btn2a.addActionListener((ActionListener) this);
			p2.add(btn2a);
			
			btn2b = new JButton("TwoB");
			btn2b.setActionCommand("2bPress");
			btn2b.addActionListener((ActionListener) this);
			p2.add(btn2b);
			
			btn2c = new JButton("TwoC");
			btn2c.setActionCommand("2cPress");
			btn2c.addActionListener((ActionListener) this);
			p2.add(btn2c);
		MonthPane.add(p2);
		
		p3 = new JPanel();
        p3.setBorder(BorderFactory.createTitledBorder("3"));
        p3.setLayout(new GridLayout(0,1));
			btn3a = new JButton("ThreeA");
			btn3a.setActionCommand("3aPress");
			btn3a.addActionListener((ActionListener) this);
			p3.add(btn3a);
			
			btn3b = new JButton("ThreeB");
			btn3b.setActionCommand("3bPress");
			btn3b.addActionListener((ActionListener) this);
			p3.add(btn3b);
			
			btn3c = new JButton("ThreeC");
			btn3c.setActionCommand("3cPress");
			btn3c.addActionListener((ActionListener) this);
			p3.add(btn3c);
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
    		Calendar.Event.Create newEventObj = new Calendar.Event.Create(frame);
    	} 
    	
    	if (eventName.equals("EditEventPress")) {
    		Calendar.Event.Edit editEventObj = new Calendar.Event.Edit(frame);
    	} 
    	
    	if (eventName.equals("backPress")) {
    		Root.GUI newGUIObj = new Root.GUI(frame);
    	} 
    	
    	
    	if (eventName.equals("BackMonthPress")) {
    		if (selectedMonth == 1) {
    			selectedMonth = 12;
    			selectedYear = selectedYear -1;
    			//ADD SOMETHING TO MAKE SURE THEY DONT GO OUT OF YEAR-BOUNDS
    		} else {
    			selectedMonth = selectedMonth-1;
    		}
    		
    		CalPane.removeAll();
    		addCalComponents();
    		frame.setContentPane(ContentPane);
    	}
    	
    	if (eventName.equals("ForwardMonthPress")) {
    		if (selectedMonth == 12) {
    			selectedMonth = 1;
    			selectedYear = selectedYear +1;
    			//ADD SOMETHING TO MAKE SURE THEY DONT GO OUT OF YEAR-BOUNDS
    		} else {
    			selectedMonth = selectedMonth+1;
    		}
    		
    		CalPane.removeAll();
    		addCalComponents();
    		frame.setContentPane(ContentPane);
    	} 
	}
	
	
	public static void showNewEvent(String str) {
		lblWelcome.setText(str);
	}

}
