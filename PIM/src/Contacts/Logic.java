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
		Contact c = new Contact("aaa", null, null, null, null);
		return new ArrayList<Contact>(Arrays.asList(c));
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
