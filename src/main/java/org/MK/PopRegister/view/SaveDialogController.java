package org.MK.PopRegister.view;

import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Klasa <code>SaveDialogController</code> jest kontrolerem potwierdzającym zapis do pliku
 * @author Mikołaj Kalata
 * @version 2.0.0.1
 **/
public class SaveDialogController {
    private Stage dialogStage;
    private boolean clicked = false;

    ///Dostep do okna tej klasy
    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }

    /**
     * Metoda <code>yesHandle</code> jest wywoływana w momencie kliklnięcia przez użytkownika przycisku 'TAK'
     */
    @FXML
    private void yesHandle(){
        clicked = true;
        dialogStage.close();
    }

    /**
     * Metoda <code>noHandle</code> jest wywoływana w momencie kliklnięcia przez użytkownika przycisku 'NIE'
     */
    @FXML
    private void noHandle(){
        dialogStage.close();
    }

    /////sprawdzenie czy kliknieto przycisk 'zatwierdz'
    public boolean isClicked(){
        return clicked;
    }
}
