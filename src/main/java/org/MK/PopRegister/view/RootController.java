package org.MK.PopRegister.view;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import org.MK.PopRegister.PopRegisterApp;
import org.MK.PopRegister.tools.FileChooserWithExt;

import java.io.File;

/**
 * Obiekt <code>RootController</code> jest kontrolerem umożliającym zapis do pliku, bądź odczyt z pliku XML.
 * @author Mikołaj Kalata
 * @version 2.0.0.1
 */
public class RootController implements FileChooserWithExt {

    private PopRegisterApp popRegisterApp;

    /**
     * Metoda<code>setPopregisterApp</code> wywoływana przez <code>PopRegisterApp</code>,
     * by dać referencję do samej siebie
     *
     * @param popRegisterApp
     */
    public void setPopRegisterApp(PopRegisterApp popRegisterApp) {
        this.popRegisterApp = popRegisterApp;
    }

    /**
     * Metoda <code>newHandle</code> jest wywoływana w momencie
     * kliklnięcia przez użytkownika przycisku 'Nowy'
     */
    @FXML
    private void newHandle() {
        popRegisterApp.getPersonList().clear();
        popRegisterApp.setPathFilePreference(null);
    }

    /**
     * Metoda <code>openHandle</code> jest wywoływana w momencie
     * kliklnięcia przez użytkownika przycisku 'Otwórz'
     */
    @FXML
    private void openHandle() {
        FileChooser fileChooser = createFileChooserWithExtXML();
        File file = fileChooser.showOpenDialog(popRegisterApp.getPrimaryStage());

        if (file != null) {
            //////zastąpienie obecnej listy osób
            popRegisterApp.getPersonList().clear();
            popRegisterApp.importDataFromFile(file);

        }

    }

    /**
     * Metoda <code>importHandle</code> jest wywoływana w momencie
     * kliklnięcia przez użytkownika przycisku 'Importuj'
     */
    @FXML
    private void importHandle() {
        FileChooser fileChooser = createFileChooserWithExtXML();
        File file = fileChooser.showOpenDialog(popRegisterApp.getPrimaryStage());

        if (file != null) {
            /////dołączenie danych do obecnej listy
            popRegisterApp.importDataFromFile(file);

        }
    }

    /**
     * Metoda <code>saveHandle</code> jest wywoływana w momencie
     * kliklnięcia przez użytkownika przycisku 'Zapisz'
     */
    @FXML
    private void saveHandle() {

        File file = popRegisterApp.getPathFilePreference();

        if (file != null) {
            boolean confirmed = popRegisterApp.showSaveDialog();
            if (confirmed == true) {
                popRegisterApp.saveDataToFile(file);
            }
        } else {
            saveAsHandle();
        }
    }

    /**
     * Metoda <code>saveAsHandle</code> jest wywoływana w momencie
     * kliklnięcia przez użytkownika przycisku 'Zapisz jako...'
     */
    @FXML
    private void saveAsHandle() {
        FileChooser fileChooser = createFileChooserWithExtXML();
        File file = fileChooser.showSaveDialog(popRegisterApp.getPrimaryStage());

        if (file != null) {
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            popRegisterApp.saveDataToFile(file);
        }
    }

    /**
     * Metoda <code>infoHandle</code> jest wywoływana w momencie
     * kliklnięcia przez użytkownika przycisku 'Informacje'
     */
    @FXML
    private void infoHandle() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("PopulationRegister");
        alert.setHeaderText("Informacje");
        alert.setContentText("Ver. 2.0.0.1\nAutor: Mikołaj Kalata\n https://github.com/s20157-pj/");

        alert.showAndWait();
    }

    /**
     * Metoda <code>exitHandle</code> jest wywoływana w momencie
     * kliklnięcia przez użytkownika przycisku 'Zamknij'
     */
    @FXML
    private void exitHandle() {
        boolean confirmed = popRegisterApp.showCloseDialog();
        if (confirmed) {
            System.exit(0);
        }
    }
}
