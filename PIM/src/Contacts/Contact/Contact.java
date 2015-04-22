package Contacts.Contact;

public class Contact {

	private String contactName, contactNumber, contactEmail, contactAddress, contactGroup;
	
	public Contact(String contactName, String contactNumber, String contactEmail, String contactAddress, String contactGroup){
		this.contactName = contactName;
		this.contactNumber = contactNumber;
		this.contactEmail = contactEmail;
		this.contactAddress = contactAddress;
		this.setContactGroup(contactGroup);
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getContactGroup() {
		return contactGroup;
	}

	public void setContactGroup(String contactGroup) {
		this.contactGroup = contactGroup;
	}
}
