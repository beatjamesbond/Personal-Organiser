package Root;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI implements ActionListener {
	JFrame frame;
    JPanel homeContentPane;
    JLabel welcomeLabel;
    JButton btnCAL, btnREM, btnCONT, btnNOTES, btnO; 
	
	public GUI(JFrame inFrame) {
		frame = inFrame;
		
		if (frame.getSize().getHeight()!=768) {
			initialiseGUI();
		}
			
    	homeContentPane = new JPanel();
		homeContentPane.setBorder(BorderFactory.createEmptyBorder(55,435,25,435));
		
		addComponents();
		
    	frame.setContentPane(homeContentPane);
    	frame.setVisible(true);
	}
	
	public void initialiseGUI() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
			}
		});
		
		setSizeAndLocation(1024, 768);
	}
	
	public void setSizeAndLocation(int width, int height) {
		int x, y;
    	frame.setSize(width, height);
		frame.validate();
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		x = (dim.width - 1024)/2;
		y = (dim.height - 768)/2;
		frame.setLocation(x, y);
	}
    
	public void actionPerformed(ActionEvent event) {
    	String eventName = event.getActionCommand();
		
    	if (eventName.equals("CalPress")) {
			Calendar.Logic objLogicCal = new Calendar.Logic(frame);
    	} 
		
		if (eventName.equals("RemPress")) {
			Reminders.Logic objLogicRem = new Reminders.Logic(frame);
    	} 
		
		if (eventName.equals("ContPress")) {
			frame.setVisible(false);
	//		ContClass objCont = new ContClass();
    	} 
		
		if (eventName.equals("NotesPress")) {
			frame.setVisible(false);
	//		NotesClass objNotes = new NotesClass();
    	} 
		
		if (eventName.equals("Op")) {
			frame.setVisible(false);
	//		Options objO = new Options();
    	} 
	}
	
	public void addComponents() {
		welcomeLabel = new JLabel("Welcome to StudentOrganiser!");
		welcomeLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
    	welcomeLabel.setBorder(BorderFactory.createEmptyBorder(5,10,20,10));
		homeContentPane.add(welcomeLabel);
		
		btnCAL = new JButton("Calendar");
    	btnCAL.setActionCommand("CalPress");
    	btnCAL.addActionListener((ActionListener) this);
    	homeContentPane.add(btnCAL);
		
    	btnREM = new JButton("Reminders");
    	btnREM.setActionCommand("RemPress");
    	btnREM.addActionListener((ActionListener) this);
    	homeContentPane.add(btnREM);
		
		btnCONT = new JButton("Contacts");
    	btnCONT.setActionCommand("ContPress");
    	btnCONT.addActionListener((ActionListener) this);
    	homeContentPane.add(btnCONT);
		
		btnNOTES = new JButton("Notes");
    	btnNOTES.setActionCommand("NotesPress");
    	btnNOTES.addActionListener((ActionListener) this);
    	homeContentPane.add(btnNOTES);
	
		btnO = new JButton("Options");
    	btnO.setActionCommand("Op");
    	btnO.addActionListener((ActionListener) this);
    	homeContentPane.add(btnO);
	}
}
