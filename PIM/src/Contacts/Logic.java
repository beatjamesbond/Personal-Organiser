package Contacts;

import java.util.ArrayList;
import java.util.List;

import Contacts.Contact.Contact;

public class Logic {
	
	private static List<Contact> allContacts;

	@SuppressWarnings("unused")
	public Logic() {
		allContacts = retrieveContacts();
		GUI contactGUI = new GUI();
	}

	/**
	 * Gets contacts from storage and returns them as an ArrayList
	 */
	private ArrayList<Contact> retrieveContacts() {
		return null;
	}
	
	/**
	 * Synchronises the storage with allContacts
	 */
	private void synchroniseContacts() {
	}
	
	public List<Contact> getAllContacts() {
		return allContacts;
	}
	
	public void addContact(Contact c) {
		allContacts.add(c);
		synchroniseContacts();
	}
	
	public void removeContact(Contact c) {
		allContacts.remove(c);
		synchroniseContacts();	
	}
	
	public void editContact(Contact c) {
		
	}
	
}
