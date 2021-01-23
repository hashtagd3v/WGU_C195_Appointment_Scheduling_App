package Model;

/** This is the Contact class.*/
public class Contact {

    private int contactId;
    private String contactName;
    private String email;

    public Contact(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }

    /** @return Returns the contact ID.*/
    public int getContactId() {
        return contactId;
    }

    /** @return Returns the contact name.*/
    public String getContactName() {
        return contactName;
    }

    /** @return Returns the contact ID.*/
    public String geteMail() {
        return email;
    }

    /** @return Returns contact id and contact name in String format.*/
    @Override
    public String toString() {
        return "[" + contactId + "] " + contactName;
    }

}
