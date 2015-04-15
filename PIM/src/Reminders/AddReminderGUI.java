package Reminders;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.text.DateFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class AddReminderGUI extends JFrame  implements ActionListener {
	
	private JLabel nameOfReminderLbl;
	private JLabel timeOfReminderLbl;
	private JLabel dateOfReminderLbl;
	private JLabel locationOfReminderLbl;
	private JLabel freqOfReminderLbl;
	private JTextField nameOfReminderTF;
	private JComboBox hourOfReminderCB;
	private JComboBox minuteOfReminderCB;
	private JComboBox dayOfReminderCB;
	private JComboBox monthOfReminderCB;
	private JComboBox yearOfReminderCB;
	private JTextField locationOfReminderTF;
	private JComboBox freqOfReminderCB;
	private JButton saveReminderBtn;
	private JButton cancelReminderBtn;
	private String[] freqOfReminderList = { "Every 10 Mintues", "Every Hour", "Every 12 Hours", "Every Day", "Every Week" };
	JDatePickerImpl datePicker;
	
	Container canvas = getContentPane();

	public AddReminderGUI(){
		windowInit();
		nameInit();
		timeInit();
		dateInit();
		locationInit();
		freqInit();
		actionInit();
		setVisible(true);
	}
	
	
	private void actionInit() {
		saveReminderBtn = new JButton("Save");
		saveReminderBtn.setBounds(200, 380, 100, 50);
		canvas.add(saveReminderBtn);
		saveReminderBtn.addActionListener(this);
		
		cancelReminderBtn = new JButton("Cancel");
		cancelReminderBtn.setBounds(350, 380, 100, 50);
		canvas.add(cancelReminderBtn);
		cancelReminderBtn.addActionListener(this);
		
	}


	private void nameInit() {
		canvas.setLayout(null);
		nameOfReminderLbl = new JLabel("Name of Reminder:");
		Dimension D = nameOfReminderLbl.getPreferredSize();
		nameOfReminderLbl.setBounds(50, 50, D.width, D.height);
		canvas.add(nameOfReminderLbl);
		
		nameOfReminderTF = new JTextField();
		nameOfReminderTF.setEditable(true);
		nameOfReminderTF.setBounds(175, 50, 375, 20);
		canvas.add(nameOfReminderTF);
		
	}


	private void timeInit() {
		canvas.setLayout(null);
		timeOfReminderLbl = new JLabel("Time:");
		Dimension D = timeOfReminderLbl.getPreferredSize();
		timeOfReminderLbl.setBounds(50, 100, D.width, D.height);
		canvas.add(timeOfReminderLbl);		

		Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 24); // 24 == 12 PM == 00:00:00
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        SpinnerDateModel model = new SpinnerDateModel();
        model.setValue(calendar.getTime());

        JSpinner spinner = new JSpinner(model);

        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm:ss");
        DateFormatter formatter = (DateFormatter)editor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false); // this makes what you want
        formatter.setOverwriteMode(true);

        spinner.setEditor(editor);
        spinner.setBounds(100,100,75,20);
        canvas.add(spinner);
		
	}


	private void dateInit() {
		canvas.setLayout(null);
		dateOfReminderLbl = new JLabel("Date:");
		Dimension D = dateOfReminderLbl.getPreferredSize();
		dateOfReminderLbl.setBounds(50, 175, D.width, D.height);
		canvas.add(dateOfReminderLbl);
		
		UtilDateModel model = new UtilDateModel();
		//model.setDate(20,04,2014);
		// Need this...
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		// Don't know about the formatter, but there it is...
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, null);
		datePicker.setBounds(100,175,150,50);
		canvas.add(datePicker);
		
	}


	private void locationInit() {
		canvas.setLayout(null);
		locationOfReminderLbl = new JLabel("Location:");
		Dimension D = locationOfReminderLbl.getPreferredSize();
		locationOfReminderLbl.setBounds(50, 250, D.width, D.height);
		canvas.add(locationOfReminderLbl);
		
		locationOfReminderTF = new JTextField();
		locationOfReminderTF.setEditable(true);
		locationOfReminderTF.setBounds(125, 250, 375, 20);
		canvas.add(locationOfReminderTF);
		
	}


	private void freqInit() {
		canvas.setLayout(null);
		freqOfReminderLbl = new JLabel("Frequency of Reminding:");
		Dimension D = freqOfReminderLbl.getPreferredSize();
		freqOfReminderLbl.setBounds(50, 325, D.width, D.height);
		canvas.add(freqOfReminderLbl);
		
		freqOfReminderCB = new JComboBox(freqOfReminderList);
		freqOfReminderCB.setSelectedIndex(4);
		freqOfReminderCB.setBounds(200, 325, D.width, D.height);
		freqOfReminderCB.addActionListener(this);
		canvas.add(freqOfReminderCB);
	}


	private void windowInit() {
		super.setTitle("Add A Reminder");
		setSize(600, 480);
		setResizable(false);
		setLocationRelativeTo(null);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancelReminderBtn) {
			setVisible(false);
			Reminders.GUI objGUIRem = new Reminders.GUI();			
		}
		
	}
}
