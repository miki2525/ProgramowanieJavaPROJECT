package org.MK.PopRegister;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.WindowEvent;
import org.MK.PopRegister.model.Person;
import org.MK.PopRegister.model.PersonListContainer;
import org.MK.PopRegister.view.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.prefs.Preferences;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
/**
 * Główna klasa aplikacji
 * @author Mikołaj Kalata
 * @version 2.0.0.1
 */

public class PopRegisterApp extends Application {

            private Stage primaryStage;
            private BorderPane root;
            private ObservableList<Person> personList = FXCollections.observableArrayList();
            private ObservableList<Person> personListForSearch = FXCollections.observableArrayList();

            public ObservableList<Person> getPersonList(){
               return personList;
             }
            public void setPersonListForSearch(ObservableList<Person> personListForSearch) {
                this.personListForSearch = personListForSearch; }
            public Stage getPrimaryStage(){
                return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Ewidencja ludności");

        createScene();
        showcontent();

       primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            /**
             * Otwarcie okna z potwierdzeniem zamkniecia programu
             * @param event parametr obslugujacy zdarzenie
             */
            @Override
            public void handle(WindowEvent event) {

                    if(showCloseDialog()){
                        System.exit(0);
                    }
                    /////anuluj zamnkięcie gdy użytkownik kliknie 'ANULUJ'
                    else{
                    event.consume();
                }
                }
            });

        ////zaladowanie ostatnio zamkniętego pliku
       File file = getPathFilePreference();
       if(file != null && file.exists()) {
            importDataFromFile(file);
        }
       else{
           setPathFilePreference(null);
       }

    }

    /**
     * Tworzenie okna root
     */
        ////tworzenie Stage'a z oknem root
        public void createScene() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/root.fxml"));
            root = loader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

            RootController ctrl = loader.getController();
            ctrl.setPopRegisterApp(this);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Wyswietlenie danych wewnątrz okna root
     */
        /////załadowanie w root'cie okna personData
        public void showcontent(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/personData.fxml"));
            AnchorPane persondata =  loader.load();
            root.setCenter(persondata);

            PersonDataController ctrl = loader.getController();
            ctrl.setPopRegisterApp(this);
            ///////obsługa zdarzeń, słuchacz klawiszy
            persondata.requestFocus();
            persondata.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (event.getCode() == KeyCode.DELETE) {
                        ctrl.deleteHandle();
                    }
                    if (event.getCode() == KeyCode.F) {
                        ctrl.findHandle();
                    }
                    if (event.getCode() == KeyCode.D) {
                        ctrl.addHandle();
                    }
                    if (event.getCode() == KeyCode.E) {
                        ctrl.editHandle();
                    }
                }
            });
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Wyswietlenie potwierdzenia zamknięcia okna
     */
    public boolean showCloseDialog() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/closeDialog.fxml"));
            AnchorPane closeDialog = loader.load();


            Stage dialogStage = new Stage();
            dialogStage.setTitle("Potwierdzenie zamknięcia");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.setScene(new Scene(closeDialog));

            CloseDialogController ctrl = loader.getController();
            ctrl.setDialogStage(dialogStage);
            dialogStage.showAndWait();
            return ctrl.isClicked();
        }
        catch (IOException e) {
            e.printStackTrace();
            return  false;
        }

    }
    public boolean showSaveDialog(){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/saveDialog.fxml"));
                Pane saveDialog = loader.load();

                Stage dialogStage = new Stage();
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(primaryStage);
                dialogStage.setScene(new Scene(saveDialog));

                SaveDialogController ctrl = loader.getController();
                ctrl.setDialogStage(dialogStage);
                dialogStage.showAndWait();

                return ctrl.isClicked();

            }catch (IOException e) {
                e.printStackTrace();
                return false;
            }
    }
    /**
     * Otwieranie okna edycji. Metoda wywołana przez kontroler <code>PersonDataController</code>
     * @param person
     * @return zwraca true jeśli użykownik kliknął 'ZATWIERDŹ', false w innym wypadku
     */
    ////otwieranie okna edycji
    public boolean showNewEditDialog(Person person){
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/newEditDialog.fxml"));
            AnchorPane newEditDialog =  loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edycja danych");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.setScene(new Scene(newEditDialog));

            NewEditDialogController ctrl = loader.getController();
            ctrl.setDialogStage(dialogStage);
            ctrl.setPerson(person);
            dialogStage.showAndWait();

            return ctrl.isClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Otwieranie okna potwierdzenia usunięcia. Metoda wywołana przez kontroler <code>PersonDataController</code>
     * @return zwraca true jeśli użykownik kliknął 'TAK', false w innym wypadku
     */
    ////otwieranie okna z potwierdzeniem usuwania
    public boolean showDeletePersonDialog() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/deleteDialog.fxml"));
            AnchorPane deleteDialog = loader.load();


            Stage dialogStage = new Stage();
            dialogStage.setTitle("Potwierdzenie usunięcia");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.setScene(new Scene(deleteDialog));

            DeleteDialogController ctrl = loader.getController();
            ctrl.setDialogStage(dialogStage);
            dialogStage.showAndWait();

            return ctrl.isClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Otwieranie okna wyszukiwania. Metoda wywołana przez kontroler <code>PersonDataController</code>
     * @return zwraca listę osób pasujących do wyszukiwanej frazy
     */
    public List<Person> showSearchDialog(){
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/searchDialog.fxml"));
                    AnchorPane searchDialog = loader.load();

                    Stage dialogStage = new Stage();
                    dialogStage.setTitle("Wyszukiwanie");
                    dialogStage.initModality(Modality.WINDOW_MODAL);
                    dialogStage.initOwner(primaryStage);
                    dialogStage.setScene(new Scene(searchDialog));

                    SearchDialogController ctrl = loader.getController();
                    ctrl.setPersonListForSearch(personListForSearch);
                    ctrl.setDialogStage(dialogStage);
                    dialogStage.showAndWait();

                    return ctrl.getScoresList();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
    }

    /**
     * Wczytywanie listy osób z pliku XML. Metoda wywołana przez kontroloer <code>RootController</code>
     * @param file
     */
    /////import danych z pliku
    public void importDataFromFile(File file) {
        try {

            JAXBContext context = JAXBContext.newInstance(PersonListContainer.class);
            Unmarshaller unmarshal = context.createUnmarshaller();
            PersonListContainer container = (PersonListContainer) unmarshal.unmarshal(file);


            personList.addAll(container.getContainerPersons());
            personListForSearch.clear();
            personListForSearch.addAll(personList);
            ////zapisz ścieżke do pliku Preferences
            setPathFilePreference(file);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Potwierdzenie");
            alert.setHeaderText("Udało się!");
            alert.setContentText("Pomyślnie zaimportowano dane!");
            alert.show();
            primaryStage.setTitle("Ewidencja ludności       Otwarty plik: " + file.getPath());


        } catch (JAXBException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Nie można załadować danych z pliku: " + file.getPath());
            alert.showAndWait();
        }

    }

    /**
     * Zapisanie listy osób do pliku XML. Metoda wywołana przez kontroloer <code>RootController</code>
     * @param file
     */
    ////zapis do pliku
    public void saveDataToFile(File file){
                    try{
                    JAXBContext context = JAXBContext.newInstance(PersonListContainer.class);
                    Marshaller marshaller = context.createMarshaller();
                    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                    PersonListContainer container = new PersonListContainer();
                    container.setContainerPersons(personList);
                    marshaller.marshal(container, file);

                    ////zapisz ścieżke do pliku Preferences
                    setPathFilePreference(file);

                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Potwierdzenie");
                    alert.setHeaderText("Udało się!");
                    alert.setContentText("Pomyślnie zapisano dane do pliku!");
                    alert.show();
                    primaryStage.setTitle("Ewidencja Ludności       Otwarty plik: " + file.getPath());


        } catch (JAXBException e) {
                        e.printStackTrace();
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setContentText("Nie można zapisać danych do pliku: " + file.getPath());
                        alert.showAndWait();
                    }
        }

    /**
     * Pobranie ścieżki pliku, jeśli ścieżka nie istnieje zwracany jest null
     * @return zwraca File z wybraną ścieżką, null jeśli ścieżka nie istnieje
     */
    /////getter ścieżki pliku Preference
        public File getPathFilePreference(){
                Preferences node = Preferences.userNodeForPackage(PopRegisterApp.class);
                String filepath = node.get("filepath", null);
                if (filepath != null){
                    return new File(filepath);
                }
                else{
                    return null;
                }
    }

    /**
     * Ustawienie ścieżki pliku. Jeśli plik nie ma ścieżki, ustawienie ścieżki na null
     * @param file
     */
        /////setter ścieżki pliku Preference
        public void setPathFilePreference(File file){
                Preferences node = Preferences.userNodeForPackage(PopRegisterApp.class);
                if(file != null){
                    node.put("filepath", file.getPath());

                }
                else{
                    node.remove("filepath");
                }

        }

    public static void main(String[] args) {

        launch(args);
    }

}
