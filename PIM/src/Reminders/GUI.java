package Reminders;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener {

	JPanel panel = new JPanel();
	private JButton addReminderBtn;
	private JButton editReminderBtn;
	private JButton deleteReminderBtn;
	private JButton backBtn;
	private JList reminderList;
	private String[] exampleList = new String[10];
	private JTextArea reminderPreviewArea;

	Container canvas = getContentPane();

	public GUI() {
		windowInit();
		actionButtons();
		reminderList();
		reminderPreview();
		setVisible(true);
		
		reminderList.addListSelectionListener(new ListSelectionListener() {
			  public void valueChanged(ListSelectionEvent evt) {
			    if (!evt.getValueIsAdjusting()) {
			    	int selectedIndex = reminderList.getSelectedIndex();
		            //refresh the content based on the index
		            setContent(selectedIndex);
			    }
			  }
			});
	}
	
	private void setContent(int contentID){
		if(contentID == 0){
			reminderPreviewArea.setText("This is reminder one.");
		}
		else if(contentID == 1){
			reminderPreviewArea.setText("This is reminder two.");
		}
	}

	
	
	private void reminderPreview() {
		canvas.setLayout(null);
		reminderPreviewArea = new JTextArea();
		reminderPreviewArea.setBounds(600, 50, 300, 650);
		reminderPreviewArea.setEditable(false);
		canvas.add(reminderPreviewArea);
		

	}

	private void reminderList() {
		canvas.setLayout(null);

		exampleList[0] = "One";
		exampleList[1] = "Two";
		exampleList[2] = "Three";
		exampleList[3] = "Four";
		reminderList = new JList(exampleList); // data has type Object[]
		reminderList
				.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		reminderList.setBounds(350, 50,200,650);
		canvas.add(reminderList);
	}

	private void actionButtons() {
		canvas.setLayout(null);
		addReminderBtn = new JButton("Add Reminder");
		addReminderBtn.setBounds(70, 50, 250, 150);
		canvas.add(addReminderBtn);
		addReminderBtn.addActionListener(this);

		editReminderBtn = new JButton("Edit Reminder");
		editReminderBtn.setBounds(70, 300, 250, 150);
		canvas.add(editReminderBtn);

		deleteReminderBtn = new JButton("Delete Reminder");
		deleteReminderBtn.setBounds(70, 550, 250, 150);
		canvas.add(deleteReminderBtn);
		
		backBtn = new JButton("Back");
		backBtn.setBounds(924, 0, 100, 50);
		canvas.add(backBtn);
		backBtn.addActionListener(this);
	}

	private void windowInit() {
		super.setTitle("Reminders");
		setSize(1024, 768);
		setResizable(false);
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addReminderBtn) {
			AddReminderGUI addReminder = new AddReminderGUI();
			setVisible(false);
		}else if(e.getSource() == backBtn){
			setVisible(false);
		}
	}

	public static void Main(String[] args) {
		GUI reminderGUI = new GUI();

	}

}
