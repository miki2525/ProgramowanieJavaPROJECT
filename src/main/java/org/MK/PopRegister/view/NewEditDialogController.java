package org.MK.PopRegister.view;

import com.sun.imageio.plugins.jpeg.JPEGImageWriterResources;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.MK.PopRegister.PopRegisterApp;
import org.MK.PopRegister.model.Person;
import org.MK.PopRegister.tools.CurrentWorkingDirectoryPath;
import org.MK.PopRegister.tools.FileChooserWithExt;
import org.MK.PopRegister.tools.Validator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
/**
 * Klasa <code>NewEditDialogController</code> jest kontrolerem umożliwiającym dodanie nowej lub edytowanie wybranej osoby z listy
 * @author Mikołaj Kalata
 * @version 2.0.0.1
 */

public class NewEditDialogController implements Validator, FileChooserWithExt, CurrentWorkingDirectoryPath {


    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private DatePicker birthdayField;
    @FXML
    private TextField peselField;
    @FXML
    private TextField localityField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField housblockNumberField;
    @FXML
    private TextField flatNumberField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private Label photoLabel;
    @FXML
    private Label pathToFileLabel;


    private Stage dialogStage;
    private Person person;
    private boolean clicked = false;


    /**
     * Metoda<code>setDialogStage</code> wywoływana przez <code>PopRegisterApp</code>,
     * w celu uzyskania kontroli nad tym oknem
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Metoda<code>isClicked</code> sprawdza czy użytkownik wcisnął przycisk
      * @return zwraca true lub false w zależności czy użytkownik nacisnał przycisk
     */
    public boolean isClicked(){
        return clicked;
    }

    /**
     * załadowanie i wyświetlenie wyników wybranej osoby
     * @param person
     */
    public void setPerson(Person person) {
        this.person = person;

        nameField.setText(person.getName());
        surnameField.setText(person.getSurname());
        birthdayField.setValue(person.getBirthday());
        peselField.setText(person.getPesel());
        localityField.setText(person.getLocality());
        streetField.setText(person.getStreet());
        housblockNumberField.setText(person.getHouseblockNumber());
        flatNumberField.setText(person.getFlatNumber());
        postalCodeField.setText(person.getPostalCode());
        emailField.setText(person.getEmail());
        phoneNumberField.setText(person.getPhoneNumber());

    }
    /**
     * Metoda <code>cancelHandle</code> jest wywoływana w momencie
     * kliklnięcia przez użytkownika przycisku 'ANULUJ'
     */

    @FXML
    private void cancelHandle(){
        person.setNextID();
        dialogStage.close();
    }

    /**
     * Metoda <code>confirmHandle</code> jest wywoływana w momencie
     * kliklnięcia przez użytkownika przycisku 'POTWIERDŹ'
     */

    @FXML
    private void confirmHandle() {
        //flaga if ( prawda, że wszystkie pola są poprawnie wypelnione)
       if (inputValidator()) {
            person.setName(nameField.getText());
            person.setSurname(surnameField.getText());
            person.setBirthday(birthdayField.getValue());
            person.setPesel(peselField.getText());
            person.setLocality(localityField.getText());
            person.setStreet(streetField.getText());
            person.setHouseblockNumber(housblockNumberField.getText());
            person.setFlatNumber(flatNumberField.getText());
            /////domyślnie zapis kodu pocztowego z myślnikiem
            if(postalCodeField.getText().charAt(2) != '-'){
                postalCodeField.setText(postalCodeField.getText().substring(0,2) +
                        "-" + postalCodeField.getText().substring(2));
                person.setPostalCode(postalCodeField.getText());
            }
            else{
            person.setPostalCode(postalCodeField.getText());
            }
            person.setEmail(emailField.getText());
            person.setPhoneNumber(phoneNumberField.getText());


            ////jeśli wybrano plik, kopiowanie go do folderu "photos"
            if(!person.getPathToFile().equals("")){
                ////uzyskanie ścieżki do obecnego folderu roboczego oraz
                //utworzenie ścieżki zapisu pliku, nazwa pliku = pesel osoby
                String currentPath = pathToString(person);

                ////kopiowanie pliku
                File source = new File(person.getPathToFile());
                File destination = new File(currentPath);
                try {
                    Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Nie udało się wysłać pliku"); }

                ////przypisanie ścieżki do zdjęcia do danej osoby
                person.setPathToFile(currentPath);
                }

                clicked = true;
                dialogStage.close();
       }
    }
    /**
     * Metoda <code>addPhotoHandle</code> jest wywoływana w momencie kliklnięcia przez użytkownika przycisku 'Dodaj'
     */

    @FXML
    private void addPhotoHandle(){
        FileChooser fileChooser = createFileChooserWithImageExt();
        File file = fileChooser.showOpenDialog(dialogStage);
        if(file != null){
            person.setPathToFile(file.getPath());
            photoLabel.setText("Udało się załadować plik!");
            pathToFileLabel.setText(file.getPath());
        }
    }

    /**
     * Metoda <code>deletePhotoHandle</code> jest wywoływana w momencie kliklnięcia przez użytkownika przycisku 'Usuń'
     */

    @FXML
    private void deletePhotoHandle(){
        person.setPathToFile("");
        photoLabel.setText("(opcjonalne) Dodaj zdjęcie");
        pathToFileLabel.setText("Brak pliku");
    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////             NADPISANE METODY Z INTERFEJSU VALIDATOR            /////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Metoda<code>inputValidator</code> sprawdza czy użytkownik podał prawidłowo dane
     * @return zwraca true gdy wszystkie instrukcje walidujące zwrócą true, w przeciwnym wypadku false
     */
    public boolean inputValidator() {
        String errorMessage = "";

            if (nameField.getText() == null || nameField.getText().length() == 0 ||
                    isContainingLetters(nameField.getText()) == false) {
                errorMessage += "Niepoprawne imię!\n";
            }
            if (surnameField.getText() == null || surnameField.getText().length() == 0 ||
                    validateSurname(surnameField.getText()) == false) {
                errorMessage += "Niepoprawne nazwisko!\n";
            }
            if (peselField.getText() == null || peselField.getText().length() == 0 ||
                    isContainingLong(peselField.getText()) == false || peselField.getText().length() != 11){
                errorMessage += "Niepoprawny pesel!\n";
            }

            if (localityField.getText() == null || localityField.getText().length() == 0 ||
                    isContainingLetters(localityField.getText()) == false) {
                errorMessage += "Niepoprawna miejscowość!\n";
            }
            if (streetField.getText() == null || streetField.getText().length() == 0 ||
                    isContainingLetters(streetField.getText()) == false) {
                errorMessage += "Niepoprawna ulica!\n";
            }
            if (housblockNumberField.getText() == null || housblockNumberField.getText().length() == 0 ||
                    isContainingIntLet(housblockNumberField.getText()) == false){
                errorMessage += "Niepoprawny numer domu/bloku!\n";
            }
            if (flatNumberField.getText() == null || flatNumberField.getText().length() == 0){}
            else if (isContainingInt(flatNumberField.getText()) == false ||
                    Integer.parseInt(flatNumberField.getText()) == 0){
                errorMessage += "Niepoprawny numer mieszkania!\n";
                    }
            if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0 ||
            isPostalValid(postalCodeField.getText()) == false) {
                errorMessage += "Niepoprawny kod pocztowy!\n";
            }
            if (emailField.getText() == null || emailField.getText().length() == 0) {
             }
            else if (isEmailValid(emailField.getText()) == false){
                errorMessage += "Niewłaściwy e-mail!\n";
            }
            if (phoneNumberField.getText() == null || phoneNumberField.getText().length() == 0){}
            else if (isContainingInt(phoneNumberField.getText()) == false ||
                    Integer.parseInt(phoneNumberField.getText()) == 0){
                errorMessage += "Niepoprawny numer telefonu!\n";
            }

            if (errorMessage.length() == 0) {
                return true;

            } else {
                //// Wyświetl błędy do poprawy
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Niektóre pola zawierają błędy");
                alert.setContentText(errorMessage);
                alert.showAndWait();

                return false;
            }
        }

    /**
     * Metoda<code>isEmailValid</code> sprawdza poprawność wpisania adresu e-mail
     * @param s
     * @return zwraca true dla prawidłowego adresu, w przeciwnym wypadku false
     */
    @Override
    public boolean isEmailValid(String s) {
            return s.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
        }
    /**
     * Metoda<code>isContainingInt</code> sprawdza czy użytkownik wprawadził tylko cyfry,
     * dla numeru telefonu oraz numeru mieszkania
     * @param s
     * @return zwraca true dla prawidłowych danych, w przeciwnym wypadku false
     */
    @Override
    public boolean isContainingInt(String s){
        try{
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    /**
     * Metoda<code>isContainingLong</code> sprawdza czy użytkownik wprawadził tylko liczbę(typu long),
     * dla numer PESEL
     * @param s
     * @return zwraca true dla prawidłowej wartośći, w przeciwnym wypadku false
     */
    @Override
    public boolean isContainingLong(String s){
        try{
            Long.parseLong(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    /**
     * Metoda<code>isContainingLetter</code> sprawdza czy użytkownik wprawadził tylko słowa,
     * nie zawierające cyfr, dla nazwy miejscowości, ulicy oraz imienia
     * @param s
     * @return zwraca true dla prawidłowych wartości, w przeciwnym wypadku false
     */
    @Override
    public boolean isContainingLetters(String s) {
        return s.matches("(?i)[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]+([ ]?[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]+)*");
    }

    /**
     * Metoda<code>validateSurname</code> sprawdza czy użytkownik wprawadził prawidłowo nazwisko
     * @param s
     * @return zwraca true dla prawidłowego nazwiska, w przeciwnym wypadku false
     */
    @Override
    public boolean validateSurname(String s){
        return s.matches("(?i)[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]+([ '-][A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]+)*");
    }

    /**
     * Metoda<code>isContainingIntLet</code> sprawdza czy użytkownik wprawadził tylko cyfry, z
     * entualną jedną literą na końcu, dla numeru bloku/domu
     * @param s
     * @return zwraca true dla prawidłowego numeru bloku/domu, w przeciwnym wypadku false
     */
    @Override////czy zawiera liczby a po nich maksymalnie jedną literę
    public boolean isContainingIntLet(String s){
        return s.matches("(?i)[0-9]+([a-z])?");
    }
    /**
     * Metoda<code>isPostalValid</code> sprawdza czy użytkownik wprawadził prawidłowo kod pocztowy
     * @param s
     * @return zwraca true dla prawidłowego kodu pocztowego, w przeciwnym wypadku false
     */
    @Override
    public boolean isPostalValid(String s) {
        {
            return s.matches("[0-9][0-9]-?[0-9][0-9][0-9]$");

        }
    }
}




