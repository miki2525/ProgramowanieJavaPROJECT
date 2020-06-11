package org.MK.PopRegister.model;

import javafx.beans.property.*;
import org.MK.PopRegister.tools.LocalDateXMLConverter;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.File;
import java.time.LocalDate;

/**
 * Obiekt <code>Person</code> posiada implementacje danych personalnych osoby. Klasa modelowa.
 * @author Mikołaj Kalata
 * @version 2.0.0.1
 */
public class Person {

    private final IntegerProperty id;
    private static Integer nextID = 1;
    private final StringProperty name;
    private final StringProperty surname;
    private final ObjectProperty<LocalDate> birthday;
    private final StringProperty pesel;
    private final StringProperty locality;
    private final StringProperty street;
    private final StringProperty houseblockNumber;
    private final StringProperty flatNumber;
    private final StringProperty postalCode;
    private final StringProperty email;
    private final StringProperty phoneNumber;
    private final ObjectProperty<File> photo;
    private final StringProperty pathtoFile;

    /**
     * Konstruktor bezargumentowy
     */
    public Person(){

        this.id = new SimpleIntegerProperty(nextID++);
        this.name = new SimpleStringProperty();
        this.surname = new SimpleStringProperty();
        this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.now());
        this.pesel = new SimpleStringProperty();
        this.locality = new SimpleStringProperty();
        this.street = new SimpleStringProperty();
        this.houseblockNumber = new SimpleStringProperty();
        this.flatNumber = new SimpleStringProperty();
        this.postalCode = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.phoneNumber = new SimpleStringProperty();
        this.photo = null;
        this.pathtoFile = new SimpleStringProperty("");
    }

    /**
     * Kontruktor przyjmujący wszystkie dane osoby
     * @param name imie
     * @param surname nazwisko
     * @param yyyy lata
     * @param MM miesiące
     * @param dd dni
     * @param pesel pesel
     * @param locality miejscowosc
     * @param street ulica
     * @param houseblock numer bloku lub domu
     * @param flat numer mieszkania
     * @param postal kod pocztowy
     * @param email adres e-mail
     * @param phone nr. telefonu
     */
    public Person (String name, String surname, int yyyy, int MM, int dd, String pesel,
                   String locality, String street, String houseblock, String flat,
                   String postal, String email, String phone)
    {
        this.id = new SimpleIntegerProperty(nextID++);
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(yyyy, MM, dd));
        this.pesel = new SimpleStringProperty(pesel);
        this.locality = new SimpleStringProperty(locality);
        this.street = new SimpleStringProperty(street);
        this.houseblockNumber = new SimpleStringProperty(houseblock);
        this.flatNumber = new SimpleStringProperty(flat);
        this.postalCode = new SimpleStringProperty(postal);
        this.email = new SimpleStringProperty(email);
        this.phoneNumber = new SimpleStringProperty(phone);
        this.photo = null;
        this.pathtoFile = new SimpleStringProperty("");
    }
    public void setNextID() {
        --nextID;
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    @XmlJavaTypeAdapter(LocalDateXMLConverter.class)
    public LocalDate getBirthday() {
        return birthday.get();
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    public String getPesel() {
        return pesel.get();
    }

    public StringProperty peselProperty() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel.set(pesel);
    }

    public String getLocality() {
        return locality.get();
    }

    public StringProperty localityProperty() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality.set(locality);
    }

    public String getStreet() {
        return street.get();
    }

    public StringProperty streetProperty() {
        return street;
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public String getHouseblockNumber() {
        return houseblockNumber.get();
    }

    public StringProperty houseblockNumberProperty() {
        return houseblockNumber;
    }

    public void setHouseblockNumber(String houseblockNumber) {
        this.houseblockNumber.set(houseblockNumber);
    }

    public String getFlatNumber() { return flatNumber.get(); }

    public StringProperty flatNumberProperty() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) { this.flatNumber.set(flatNumber); }

    public String getPostalCode() {
        return postalCode.get();
    }

    public StringProperty postalCodeProperty() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode.set(postalCode);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }


    /**
     * Metoda <code>getPathToFile</code> zwraca ściężkę do pliku(zdjęcia)
     * @return String - ścięzkę do pliku (zdjęcia)
     */
    public String getPathToFile() {
        return pathtoFile.get();
    }

    public StringProperty pathToFileProperty() {
        return pathtoFile;
    }

    /**
     * Metoda<code>setPathToFile</code> ustawia sciezke do pliku(zdjecia)
     * @param pathtoFile
     */
    public void setPathToFile(String pathtoFile) {
        this.pathtoFile.set(pathtoFile);
    }

    /**
     * Metoda <code>toStringStreetNumber</code> zscala nazwę ulicy z numerem bloku/domu
     *
     * @return zwraca nazwę ulicy z jej numerem bloku/domu
     */
    public String toStringStreetNumber(){
        String s = getStreet() + " " + getHouseblockNumber();
        return s;
    }
}
