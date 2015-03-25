package Contacts.Contact;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Contacts.Logic;

public class Create implements ActionListener {
	
	private Logic logic;
	private JFrame createFrame;
	private JPanel createPanel;
	private List<String> contactDetails;
	private JTextField nameEntry, numberEntry, emailEntry, addressEntry, groupEntry;
	private JButton confirmEntries;
	
	
	public static void main(String[] args){
		Create create = new Create();
	}
	public Create() {
		createFrame = new JFrame();
		createPanel = new JPanel();
		createFrame.setSize(1024, 768);
		createFrame.setContentPane(createPanel);
		createPanel.setVisible(true);
		createFrame.setVisible(true);
		confirmEntries = new JButton("Enter");
		nameEntry = new JTextField();
		numberEntry = new JTextField();
		emailEntry = new JTextField();
		addressEntry = new JTextField();
		groupEntry = new JTextField();
		createPanel.add(nameEntry);
		createPanel.add(numberEntry);
		createPanel.add(emailEntry);
		createPanel.add(addressEntry);
		createPanel.add(groupEntry);
		createPanel.add(confirmEntries);
		confirmEntries.addActionListener(this);
	}
	
	public void createContact() {
		String contactName = contactDetails.get(0);
		String contactNumber = contactDetails.get(1);
		String contactEmail = contactDetails.get(2);
		String contactAddress = contactDetails.get(3);
		String contactGroup = contactDetails.get(4);
		Contact c = new Contact(contactName, contactNumber, contactEmail, contactAddress, contactGroup);
		logic.addContact(c);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		contactDetails = new ArrayList<String>(Arrays.asList(nameEntry.getText(), numberEntry.getText(), emailEntry.getText(), addressEntry.getText(), groupEntry.getText()));
		createContact();
	}
}
