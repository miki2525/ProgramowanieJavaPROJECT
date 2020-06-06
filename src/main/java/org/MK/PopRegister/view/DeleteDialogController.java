package org.MK.PopRegister.view;

import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.awt.*;
/**
 * Klasa <code>DeleteDialogController</code> jest kontrolerem potwierdzającym usunięcie wybranej osobę z listy
 * @author Mikołaj Kalata
 * @version 2.0.0.1
 */
public class DeleteDialogController {
    private Stage dialogStage;
    private boolean clicked = false;

    ///Dostep do okna tej klasy
    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }
/**
 * Metoda <code>handleYes</code> jest wywoływana w momencie kliklnięcia przez użytkownika przycisku 'TAK'
 */
    @FXML
    private void handleYes(){
        clicked = true;
        dialogStage.close();
    }
    /**
     * Metoda <code>handleYes</code> jest wywoływana w momencie kliklnięcia przez użytkownika przycisku 'ANULUJ'
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


