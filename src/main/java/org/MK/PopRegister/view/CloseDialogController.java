package org.MK.PopRegister.view;

import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Klasa <code>CloseDialogController</code> jest kontrolerem potwierdzającym zamknięcie programu
 * @author Mikołaj Kalata
 * @version 2.0.0.1
 **/

public class CloseDialogController {
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
        dialogStage.close();
        clicked = true;
    }

    /**
     * Metoda <code>cancelHandle</code> jest wywoływana w momencie kliklnięcia przez użytkownika przycisku 'ANULUJ'
     */
    @FXML
    private void cancelHandle(){
        dialogStage.close();
    }

    /////sprawdzenie czy kliknieto przycisk 'zatwierdz'
    public boolean isClicked(){
        return clicked;
    }

}
