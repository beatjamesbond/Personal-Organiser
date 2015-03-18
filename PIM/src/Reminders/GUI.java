package Reminders;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI extends JFrame implements ActionListener {

	JButton newReminderButton = new JButton("New Reminder");
	JButton editReminderButton = new JButton ("Edit Reminder");
	JButton deleteReminderButton = new JButton ("Delete Reminder");
	JFrame reminderFrame = new JFrame();
	
	
	public GUI(){
		

		
		this.setTitle("Reminders");
		
		Container canvas = getContentPane();
		canvas.setLayout(new GridLayout(9,9));
		canvas.add(newReminderButton);
		canvas.add(editReminderButton);
		canvas.add(deleteReminderButton);
		
		newReminderButton.addActionListener(this);
		editReminderButton.addActionListener(this);
		deleteReminderButton.addActionListener(this);
		this.setSize(1024, 768);
		centerFrame();
		this.show();
		
	}
	
	 public void centerFrame() {

         Dimension windowSize = getSize();
         GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
         Point centerPoint = ge.getCenterPoint();

         int dx = centerPoint.x - windowSize.width / 2;
         int dy = centerPoint.y - windowSize.height / 2;    
         setLocation(dx, dy);
 }
	
	

	
	public static void Main (String[] args){
		GUI reminderGUI = new GUI();
		reminderGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newReminderButton){
			JOptionPane.showMessageDialog(reminderFrame, "New");
		}
		else if (e.getSource() == editReminderButton){
			JOptionPane.showMessageDialog(reminderFrame, "Edit");
		}
		else if (e.getSource() == deleteReminderButton){
			JOptionPane.showMessageDialog(reminderFrame, "Delete");
		}
		
	}

	
}
	



