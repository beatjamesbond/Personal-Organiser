/** StudentOrganiser.java
 * StudentOrganiser Application
 * MAIN Window
 * Group 13 
 * v0.01 04/03/2015
 */
 
import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.Toolkit;
 
public class StudentOrganiser implements ActionListener {
    JFrame homeFrame;
    JPanel homeContentPane;
    JLabel welcomeLabel, spaceLabel1, spaceLabel2;
    JButton btnCAL, btnREM, btnCONT, btnNOTES, btnO; 
	
    public static void main(String[] args) {
    	javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				runGUI();
			}
		});
    }
	
	private static void runGUI() {
    	JFrame.setDefaultLookAndFeelDecorated(true);
    	StudentOrganiser greeting = new StudentOrganiser();
    }
	
    public StudentOrganiser() {
		homeFrame = new JFrame("StudentOrganiser - Main Window");
    	homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSizeAndLocation();
		
    	homeContentPane = new JPanel();
		homeContentPane.setBorder(BorderFactory.createEmptyBorder(35,15,25,15));
		
		addComponents();
		
    	homeFrame.setContentPane(homeContentPane);
    	homeFrame.setVisible(true);
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
	
	public void setSizeAndLocation() {
		int x, y;
    	homeFrame.setSize(1024,768);
		homeFrame.validate();
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		x = (dim.width - 1024)/2;
		y = (dim.height - 768)/2;
		homeFrame.setLocation(x, y);
	}
	
	public void addComponents() {
		welcomeLabel = new JLabel("Welcome to StudentOrganiser!");
		welcomeLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
    	welcomeLabel.setBorder(BorderFactory.createEmptyBorder(5,10,20,10));
		homeContentPane.add(welcomeLabel);
		
		btnCAL = new JButton("Calendar");
    	btnCAL.setActionCommand("CalPress");
    	btnCAL.addActionListener(this);
    	homeContentPane.add(btnCAL);
		
    	btnREM = new JButton("Reminders");
    	btnREM.setActionCommand("RemPress");
    	btnREM.addActionListener(this);
    	homeContentPane.add(btnREM);
		 
    	spaceLabel1 = new JLabel("                                                 ");
    	homeContentPane.add(spaceLabel1);
		
		btnCONT = new JButton("Contacts");
    	btnCONT.setActionCommand("ContPress");
    	btnCONT.addActionListener(this);
    	homeContentPane.add(btnCONT);
		
		btnNOTES = new JButton("Notes");
    	btnNOTES.setActionCommand("NotesPress");
    	btnNOTES.addActionListener(this);
    	homeContentPane.add(btnNOTES);
		
		spaceLabel1 = new JLabel("                                                 ");
    	homeContentPane.add(spaceLabel1);
		
		btnO = new JButton("Options");
    	btnO.setActionCommand("Op");
    	btnO.addActionListener(this);
    	homeContentPane.add(btnO);
	}
}
 