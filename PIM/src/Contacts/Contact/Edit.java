package Contacts.Contact;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Edit extends JFrame implements ActionListener{

	private static final long serialVersionUID = -2790985670939580606L;
	private JButton editContact;
	private JButton back;
	private JTextField contactName;
	private JTextField contactNumber;
	private JTextField contactEmail;
	private JTextField contactAddress;
	private JTextField contactGroup;
	private Contact contact;
	
	Container canvas = getContentPane();
	
	public Edit(Contact contact) {
		this.contact = contact;
		windowInit();
		actionButtons();
		textFields();
		setVisible(true);
	}
	
	
	private void textFields() {
		canvas.setLayout(null);
		
		contactName = new JTextField(contact.getContactName());
		contactName.setBounds(350, 50,300,50);
		canvas.add(contactName);
		
		contactNumber = new JTextField(contact.getContactNumber());
		contactNumber.setBounds(350, 105,300,50);
		canvas.add(contactNumber);

		contactEmail = new JTextField(contact.getContactEmail());
		contactEmail.setBounds(350, 160,300,50);
		canvas.add(contactEmail);
		
		contactAddress = new JTextField(contact.getContactAddress());
		contactAddress.setBounds(350, 215,300,50);
		canvas.add(contactAddress);
		
		contactGroup = new JTextField(contact.getContactGroup());
		contactGroup.setBounds(350, 270,300,50);
		canvas.add(contactGroup);
	}


	private void actionButtons() {
		canvas.setLayout(null);
		editContact = new JButton("Edit Contact");
		editContact.setBounds(70, 50, 250, 150);
		canvas.add(editContact);
		editContact.addActionListener(this);
		
		back = new JButton("Back");
		back.setBounds(924, 0, 100, 50);
		canvas.add(back);
		back.addActionListener(this);
	}
	
	private void windowInit() {
		super.setTitle("Edit Contact");
		setSize(1024, 768);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == editContact){
			Contact c = new Contact(contactName.getText(), contactNumber.getText(), contactEmail.getText(), contactAddress.getText(), contactGroup.getText());
			Contacts.GUI.logic.editContact(contact, c);
			setVisible(false);
			Contacts.GUI gui = new Contacts.GUI();
		}else if(e.getSource() == back){
			setVisible(false);
			Contacts.GUI gui = new Contacts.GUI();
		}
	}
}

