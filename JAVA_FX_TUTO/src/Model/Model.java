package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {

    private ObservableList<Contact> contactsList;
    private Contact currentContact;

    public Model(){
        this.contactsList =  FXCollections.observableArrayList();
        this.currentContact = new Contact();
    }

    /**
     *
     * Ajoute un contact à la liste
     */
    public void addContact(Contact c) {contactsList.add(c);}

    /**
     *
     * GETTER AND SETTER
     */
    public ObservableList<Contact> getContactsList(){ return contactsList;}
    public void setContactsList(ObservableList<Contact> contactsList){ this.contactsList = contactsList; }

    public Contact getCurrentContact() { return currentContact; }
    public void setCurrentContact(Contact currentContact) { this.currentContact = currentContact; }

}