package org.MK.PopRegister.view;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.MK.PopRegister.PopRegisterApp;
import org.MK.PopRegister.model.Person;
import org.MK.PopRegister.tools.Find;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
/**
 * Klasa <code>SearchDialogController</code> jest kontrolerem umożliwiającym wyszukanie osoby z tabeli
 * @author Mikołaj Kalata
 * @version 2.0.0.1
 */
public class SearchDialogController {

    @FXML
    private TextField searchField;
    @FXML
    private Text noScores;

    private Stage dialogStage;
    private List<Person> personListForSearch;
    private List<Person> scoresList = FXCollections.observableArrayList();


    /**
     * Metoda<code>setDialogStage</code> wywoływana przez <code>PopRegisterApp</code>,
     * w celu uzyskania kontroli nad tym oknem
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Metoda<code>setPersonListForSearch</code> wywoływana przez <code>PopRegisterApp</code>
     * w celu załadowania listy tymczasowej do przeszukania
     * @param personListForSearch
     */
    public void setPersonListForSearch(List<Person> personListForSearch) {
        this.personListForSearch = personListForSearch;
    }

    public List<Person> getScoresList() {
        return scoresList;
    }

    /**
     * Metoda <code>findHandle</code> jest wywoływana w momencie
     * kliklnięcia przez użytkownika przycisku 'SZUKAJ'
     */
    @FXML
    public void findHandle(){
        noScores.setVisible(false);
        String findMe = searchField.getText();
        scoresList = Find.findThePhrase(findMe, personListForSearch, scoresList);

        if(scoresList == null || scoresList.size() == 0) {
            noScores.setVisible(true);
        }
        else {
            noScores.setVisible(false);
            dialogStage.close();
        }

    }
    /**
     * Metoda <code>setSearchFieldHandle</code> jest wywoływana w momencie
     * naciśniecia na pole tekstowe searchField
     */

    @FXML
    public void setSearchFieldHandle(){
        noScores.setVisible(false);
    }


}