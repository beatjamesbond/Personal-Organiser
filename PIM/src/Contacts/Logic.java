package Contacts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Contacts.Contact.Contact;

public class Logic {
	
	private static List<Contact> allContacts;

	public static void main(String[] args){
		new Logic();
	}
	
	public Logic() {
		allContacts = retrieveContacts();
	}
	/**
	 * Gets contacts from storage and returns them as an ArrayList
	 */
	private ArrayList<Contact> retrieveContacts() {
		Contact c1 = new Contact("Contact 1", "1234567", "1@contact.com", "Contact 1 Address", "Contacts");
		Contact c2 = new Contact("Contact 2", "12345678", "2@contact.com", "Contact 2 Address", "Contacts");
		Contact c3 = new Contact("Contact 3", "123456789", "3@contact.com", "Contact 3 Address", "Contacts");
		return new ArrayList<Contact>(Arrays.asList(c1, c2, c3));
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
