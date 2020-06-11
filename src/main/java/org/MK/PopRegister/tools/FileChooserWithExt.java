package org.MK.PopRegister.tools;

import javafx.stage.FileChooser;
/**
 * Interfejs <code>FileChooserWithExt</code> służy do wywoływania obiektów FileChooser
 * z wybranymi filtrami rozszerzeń plików
 * @author Mikołaj Kalata
 * @version 2.0.0.1
 */
public interface FileChooserWithExt {
    /**
     * Metoda <code>createFileChooserWithExtXML</code> zwraca obiekt FileChooser z włączonym filtrem dla plików XML
     * @return zwraca obiekt FileChooser z włączonym filtrem dla plików XML
     */
    default FileChooser createFileChooserWithExtXML() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter
                ("Pliki XML (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extensionFilter);
        return fileChooser;
    }
    /**
     * Metoda <code>createFileChooserWithExtXML</code> zwraca obiekt FileChooser z włączonym filtrem dla plików XML
     * @return zwraca obiekt FileChooser z włączonym filtrem dla plików XML
     */
    default FileChooser createFileChooserWithImageExt() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter
                ("Pliki obrazu (*.jpg, *jpeg, *png, *gif, *jfif)",
                        "*.jpg", "*.jpeg", "*.png", "*.gif", "*jfif");
        fileChooser.getExtensionFilters().add(extensionFilter);
        return fileChooser;
    }
}
