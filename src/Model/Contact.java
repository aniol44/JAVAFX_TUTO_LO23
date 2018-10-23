package Model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Contact {
    private StringProperty nom, prenom;
    private ObjectProperty<LocalDate> date;

    public Contact() {
        this.nom = new SimpleStringProperty();
        this.prenom = new SimpleStringProperty();
        this.date = new SimpleObjectProperty<>();
    }

    /**
     * permet de reset un contact et vider les champs
     */
    public void resetContact(){
        this.prenom.setValue("");
        this.nom.setValue("");
        this.date.setValue(null);
    }

    /**
     *
     * retourne une copie de l'objet courant
     */
    public Contact getCopyContact(){
        Contact c = new Contact();
        c.prenom.setValue(this.getPrenom());
        c.nom.setValue(this.getNom());
        c.date.setValue(this.getDate());
        return c;
    }

    /**
     *
     * GETTER AND SETTER
     */
    public String getNom(){return nom.get();}
    public void setNom(String nom){this.nom.set(nom);}
    public StringProperty nomProperty(){return nom;}

    public void setPrenom(String prenom){this.prenom.set(prenom); }
    public String getPrenom(){return prenom.get();}
    public StringProperty prenomProperty(){return prenom;}

    public void setDate(LocalDate date){this.date.set(date); }
    public LocalDate getDate(){return date.get();}
    public ObjectProperty<LocalDate> dateProperty(){return date;}

    @Override
    public String toString() { return this.getPrenom() + " "+this.getNom(); }
}
