package group132015.pim.gui;

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
	JFrame homeFrame;
    JPanel homeContentPane;
    JLabel welcomeLabel, spaceLabel1, spaceLabel2;
    JButton btnCAL, btnREM, btnCONT, btnNOTES, btnO; 
	
	public GUI(int width, int height) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
			}
		});
		
		homeFrame = new JFrame("StudentOrganiser - Main Window");
    	homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSizeAndLocation(width, height);
		
    	homeContentPane = new JPanel();
		homeContentPane.setBorder(BorderFactory.createEmptyBorder(35,15,25,15));
		
		addComponents();
		
    	homeFrame.setContentPane(homeContentPane);
    	homeFrame.setVisible(true);
	}
	
	public void setSizeAndLocation(int width, int height) {
		int x, y;
    	homeFrame.setSize(width, height);
		homeFrame.validate();
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		x = (dim.width - 1024)/2;
		y = (dim.height - 768)/2;
		homeFrame.setLocation(x, y);
	}
    
	public void actionPerformed(ActionEvent event) {
    	String eventName = event.getActionCommand();
		
    	if (eventName.equals("CalPress") || eventName.equals("RemPress")) {
			homeFrame.setVisible(false);
	//		CalClass objCal = new CalClass();
    	} 
		
		if (eventName.equals("ContPress")) {
			homeFrame.setVisible(false);
	//		ContClass objCont = new ContClass();
    	} 
		
		if (eventName.equals("NotesPress")) {
			homeFrame.setVisible(false);
	//		NotesClass objNotes = new NotesClass();
    	} 
		
		if (eventName.equals("Op")) {
			homeFrame.setVisible(false);
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
		 
    	spaceLabel1 = new JLabel("                                                 ");
    	homeContentPane.add(spaceLabel1);
		
		btnCONT = new JButton("Contacts");
    	btnCONT.setActionCommand("ContPress");
    	btnCONT.addActionListener((ActionListener) this);
    	homeContentPane.add(btnCONT);
		
		btnNOTES = new JButton("Notes");
    	btnNOTES.setActionCommand("NotesPress");
    	btnNOTES.addActionListener((ActionListener) this);
    	homeContentPane.add(btnNOTES);
		
		spaceLabel1 = new JLabel("                                                 ");
    	homeContentPane.add(spaceLabel1);
		
		btnO = new JButton("Options");
    	btnO.setActionCommand("Op");
    	btnO.addActionListener((ActionListener) this);
    	homeContentPane.add(btnO);
	}
}
