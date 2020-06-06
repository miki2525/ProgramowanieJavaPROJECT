package org.MK.PopRegister.view;


import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import org.MK.PopRegister.PopRegisterApp;
import org.MK.PopRegister.model.Person;
import org.MK.PopRegister.tools.CurrentWorkingDirectoryPath;

import java.awt.event.KeyListener;
import java.io.File;
/**
 * Klasa <code>NewEditDialogController</code> jest kontrolerem dodającym nową lub edytującym wybraną osobę z listy
 * @author Mikołaj Kalata
 * @version 2.0.0.1
 */
public class PersonDataController  implements CurrentWorkingDirectoryPath {
    @FXML
    private TableView<Person> table;
    @FXML
    private TableColumn numberCol;
    @FXML
    private TableColumn<Person, Integer> idColumn;
    @FXML
    private TableColumn<Person, String> nameColumn;
    @FXML
    private TableColumn<Person, String> surnameColumn;


    @FXML
    private Label nameLabel;
    @FXML
    private Label surnameLabel;
    @FXML
    private Label birthdayLabel;
    @FXML
    private Label peselLabel;
    @FXML
    private Label localityLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label houseblockNumberLabel;
    @FXML
    private Label flatNumberLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label phoneNumberLabel;
    @FXML
    private ImageView personPhotoView;
    @FXML
    private Button restoreFullPersonListButton;


    private PopRegisterApp popRegisterApp;


    /**
     * Metoda<code>setPopregisterApp</code>  wywoływana przez <code>PopRegisterApp</code>,
     * by dać referencję do samej siebie
     *
     * @param app
     */
    //////uzysaknie dostępu do tej klasy i wypełnienie tabeli<TableView> danymi
    public void setPopRegisterApp(PopRegisterApp app) {
        this.popRegisterApp = app;
        table.setItems(popRegisterApp.getPersonList());
    }

    /**
     * Metoda <code>initialize</code> tworzy tabele osób z trzema kolumnami,
     * reagująca na zaznaczenie danego wiersza
     */
    @FXML
    private void initialize() {

        ////ustawienie numeracji kolumn
        numberCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Person, String> p) {
                return new ReadOnlyObjectWrapper(table.getItems().indexOf(p.getValue()) + 1);
            }
        });

        idColumn.setCellValueFactory(celldata -> celldata.getValue().idProperty().asObject());
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        surnameColumn.setCellValueFactory(celldata -> celldata.getValue().surnameProperty());

        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>() {
            @Override
            public void changed(ObservableValue<? extends Person> observable, Person oldValue, Person newValue) {
                showPersonDetails(newValue);
                //System.out.println(table.getSelectionModel().getSelectedItem());
            }
        });


    }

    /**
     * Metoda <code>cancelHandle</code> jest wywoływana w momencie
     * kliklnięcia przez użytkownika przycisku 'USUN'
     */
    @FXML
    public void deleteHandle() {

        int indexSelected = table.getSelectionModel().getSelectedIndex();
        if (indexSelected >= 0) {
            boolean confirmed = popRegisterApp.showDeletePersonDialog();
            if (confirmed) {
                table.getItems().remove(indexSelected);
                popRegisterApp.setPersonListForSearch(popRegisterApp.getPersonList());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("BLAD");
            alert.setHeaderText("Nie wybrano osoby");
            alert.setContentText("Proszę wybrać osobę z tabeli");
            alert.showAndWait();
        }
    }

    /**
     * Metoda <code>cancelHandle</code> jest wywoływana w momencie
     * kliklnięcia przez użytkownika przycisku 'EDYTUJ'
     */
    @FXML
    public void editHandle() {
        Person person = table.getSelectionModel().getSelectedItem();
        if (person != null) {
            boolean confirmed = popRegisterApp.showNewEditDialog(person);
            if (confirmed) {
                popRegisterApp.setPersonListForSearch(popRegisterApp.getPersonList());
                showPersonDetails(person);
            }
        }

    }

    /**
     * Metoda <code>cancelHandle</code> jest wywoływana w momencie
     * kliklnięcia przez użytkownika przycisku 'DODAJ'
     */
    @FXML
    public void addHandle() {

        Person temp = new Person();
        boolean confirmed = popRegisterApp.showNewEditDialog(temp);
        if (confirmed) {
            popRegisterApp.getPersonList().add(temp);
            popRegisterApp.setPersonListForSearch(popRegisterApp.getPersonList());
        }
    }

    /**
     * Metoda <code>cancelHandle</code> jest wywoływana w momencie
     * kliklnięcia przez użytkownika przycisku 'SZUKAJ'
     */
    @FXML
    public  void findHandle() {

        ObservableList<Person> tempList;
        tempList = (ObservableList<Person>) popRegisterApp.showSearchDialog();
        if (tempList != null && tempList.size() > 0) {
            restoreFullPersonListButton.setVisible(true);
            popRegisterApp.setPersonListForSearch(tempList);
            table.setItems(tempList);

        }
    }

    /**
     * Metoda <code>cancelHandle</code> jest wywoływana w momencie
     * kliklnięcia przez użytkownika przycisku 'Przywróć całą listę'
     */
    @FXML
    private void restoreFullPersonListHandle() {
        table.setItems(popRegisterApp.getPersonList());
        popRegisterApp.setPersonListForSearch(popRegisterApp.getPersonList());
        restoreFullPersonListButton.setVisible(false);
    }

    /**
     * Metoda <code>showPersonDetails</code> wywoływana jest po zaznaczeniu wybranego wiersza tabeli.
     * Wyświetla wartości(dane) wybranej osoby
     *
     * @param person
     */
    private void showPersonDetails(Person person) {
        try {

            nameLabel.setText(person.getName().toUpperCase());
            surnameLabel.setText(person.getSurname().toUpperCase());
            birthdayLabel.setText(person.getBirthday().toString());
            peselLabel.setText(person.getPesel());
            localityLabel.setText(person.getLocality().toUpperCase());
            streetLabel.setText(person.getStreet().toUpperCase());
            houseblockNumberLabel.setText(person.getHouseblockNumber());
            flatNumberLabel.setText(person.getFlatNumber());
            //////wyświetlanie kodu pocztowego z myślnikiem
            if (person.getPostalCode().charAt(2) == '-') {
                postalCodeLabel.setText(person.getPostalCode());
            } else {
                postalCodeLabel.setText(person.getPostalCode().substring(0, 2) + "-"
                        + person.getPostalCode().substring(2));
            }
            emailLabel.setText(person.getEmail());
            phoneNumberLabel.setText(person.getPhoneNumber());

            //////załadowanie aktualnej ścieżki do zdjęcia osoby
            String currentPath = pathToString(person);
            person.setPathToFile(currentPath);
            File file = new File(currentPath);

            if (file.exists()) {

                Image photo = new Image(file.toURI().toString());
                personPhotoView.setImage(photo);
            } else {
                file = null;
                person.setPathToFile("");
                personPhotoView.setImage(null);
            }
        } catch (NullPointerException e) {
            nameLabel.setText("");
            surnameLabel.setText("");
            birthdayLabel.setText("");
            peselLabel.setText("");
            localityLabel.setText("");
            streetLabel.setText("");
            houseblockNumberLabel.setText("");
            flatNumberLabel.setText("");
            postalCodeLabel.setText("");
            emailLabel.setText("");
            phoneNumberLabel.setText("");
        }
    }
}

