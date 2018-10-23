package Controller;

import Model.*;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class MainController {
    private Model model;
    private ListChangeListener<Contact> contactsListListener;

    @FXML private TreeView<Object> treeViewContact;
    @FXML private TextField prenom, nom;
    @FXML private DatePicker date;
    @FXML private AnchorPane contactPane;
    @FXML private Text prenomPrinted, nomPrinted, datePrinted;

    /**
     *  initialize est privilégié par rapport à un constructeur car un constructeur est
     *  appelé en tout premier alors qu'initialize est appelé après les FXML et pourra y avoir accès
     */
    public void initialize() {
        this.model = new Model();
        initializeTreeView();
        contactsListListener();
        this.contactPane.visibleProperty().set(false);
        binding();
    }

    /**
     * intialise le treeview qui permettra d'afficher la liste des contacts et de les selectionner
     */
    public void initializeTreeView(){
        treeViewContact.setEditable(true);
        treeViewContact.setRoot(new TreeItem<Object>("Carnet de contact"));
    }

    /**
     * Cette fonction permet de mettre à jour la vue quand un contact est ajouté au model
     */
    public void contactsListListener(){
        contactsListListener = change -> {
            change.next();
            if(change.wasAdded()){
                Contact g = (Contact) change.getAddedSubList().get(0);
                TreeItem<Object> itemContact = new TreeItem<Object>(g);
                this.treeViewContact.getRoot().getChildren().add(itemContact);
            }
            else if( change.wasRemoved()){
                TreeItem<Object> currentItem =this.treeViewContact.getSelectionModel().getSelectedItem();
                currentItem.getParent().getChildren().remove(currentItem);
                this.contactPane.visibleProperty().set(false);
            }
        };
        model.getContactsList().addListener(contactsListListener);
    }

    /**
     * Cette fonction met à jour la fiche contact lorsqu'on clique sur un nom dans la liste
     */
    @FXML public void onTreeViewContactCliked(){
        TreeItem<Object> item = this.treeViewContact.getSelectionModel().getSelectedItem();
        if(item!=null){
            Object contactSelected = this.treeViewContact.getSelectionModel().getSelectedItem().getValue();
            if(contactSelected instanceof Contact){
                this.contactPane.visibleProperty().set(true);
                this.prenomPrinted.setText(((Contact) contactSelected).getPrenom());
                this.nomPrinted.setText(((Contact) contactSelected).getNom());
                this.datePrinted.setText(((Contact) contactSelected).getDate().toString());
            }
            else this.contactPane.visibleProperty().set(false);
        }
        else this.contactPane.visibleProperty().set(false);
    }

    /**
     * FONCTION qui est déclanché quand on clique sur le bouton ajouter
     */
    @FXML public void onAddContactCliked(){
        Contact currentContact = this.model.getCurrentContact();
        Contact newContact;
        newContact = currentContact.getCopyContact();
        this.model.addContact(newContact);
        currentContact.resetContact();
    }

    /**
     * FONCTION qui supprime un contact de la liste du modèle et du treeview de la vue
     */
    @FXML public void onDeleteContactClicked (){
        Object contactSelected = this.treeViewContact.getSelectionModel().getSelectedItem().getValue();
        if(contactSelected instanceof Contact) this.model.getContactsList().remove(contactSelected);
    }

    /**
     * effectue un binding entre la vue et les champs de l'objet contact
     */
    public void binding(){
        Contact c = this.model.getCurrentContact();
        this.prenom.textProperty().bindBidirectional(c.prenomProperty());
        this.nom.textProperty().bindBidirectional(c.nomProperty());
        this.date.valueProperty().bindBidirectional(c.dateProperty());
    }
}