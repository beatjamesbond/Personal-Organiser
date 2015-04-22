package Contacts;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GUI extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	JPanel panel = new JPanel();
	private JButton addContact;
	private JButton editContact;
	private JButton deleteContact;
	private JButton back;
	private JList<Object> contactList;
	private String[] stringList;
	private ListSelectionListener listListener;
	private Logic logic;

	Container canvas = getContentPane();

	public static void main(String[] args){
		@SuppressWarnings("unused")
		GUI gui = new GUI();
	}
	public GUI() {
		logic = new Logic();
		windowInit();
		actionButtons();
		contactList();
		setVisible(true);
		contactList.addListSelectionListener(listListener);
	}
		
	public void valueChanged(ListSelectionEvent evt) {
		if (!evt.getValueIsAdjusting()) {
			contactList.removeAll();
		    contactList();
		    }
	}

	private void contactList() {
		canvas.setLayout(null);
		stringList = new String[logic.getAllContacts().size()];
		for(int i = 0; i < logic.getAllContacts().size(); i++){
			stringList[i] = logic.getAllContacts().get(i).getContactName() + " - " + logic.getAllContacts().get(i).getContactNumber();
		}
		contactList = new JList<Object>(stringList);
		contactList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		contactList.setBounds(350, 50,200,650);
		canvas.add(contactList);
	}

	private void actionButtons() {
		canvas.setLayout(null);
		addContact = new JButton("Add Contact");
		addContact.setBounds(70, 50, 250, 150);
		canvas.add(addContact);
		addContact.addActionListener(this);

		editContact = new JButton("Edit Contact");
		editContact.setBounds(70, 300, 250, 150);
		canvas.add(editContact);
		editContact.addActionListener(this);

		deleteContact = new JButton("Delete Contact");
		deleteContact.setBounds(70, 550, 250, 150);
		canvas.add(deleteContact);
		deleteContact.addActionListener(this);
		
		back = new JButton("Back");
		back.setBounds(924, 0, 100, 50);
		canvas.add(back);
		back.addActionListener(this);
	}

	private void windowInit() {
		super.setTitle("Contacts");
		setSize(1024, 768);
		setResizable(false);
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addContact) {
			setVisible(false);
		}else if(e.getSource() == editContact && contactList.getSelectedValue().toString() != null){
			setVisible(false);
		}else if(e.getSource() == deleteContact && contactList.getSelectedValue().toString() != null){
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(this, "Would you like to delete this contact?", "Delete Confirmation", dialogButton);
			if(dialogResult == JOptionPane.YES_OPTION) {
				logic.removeContact(logic.getAllContacts().get(contactList.getSelectedIndex()));
			} else {
			} 
		}
		
		else if(e.getSource() == back){
			setVisible(false);
			@SuppressWarnings("unused")
			Root.GUI rootGUI = new Root.GUI(new JFrame());
		}
	}

}
