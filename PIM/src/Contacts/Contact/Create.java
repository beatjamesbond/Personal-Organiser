package Contacts.Contact;

import Contacts.Logic;

public class Create {
	
	private Logic logic;
	
	public void createContact(String[] contactDetails){
		String contactName = contactDetails[0];
		String contactNumber = contactDetails[1];
		String contactEmail = contactDetails[2];
		String contactAddress = contactDetails[3];
		String contactGroup = contactDetails[4];
		Contact c = new Contact(contactName, contactNumber, contactEmail, contactAddress, contactGroup);
		logic.addContact(c);
	}
}
