package org.MK.PopRegister.tools;

import org.MK.PopRegister.model.Person;

import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * Interfejs <code>CurrentWorkingDirectoryPath</code> służy do "wydobycia" aktualne ścieżki folderu roboczego
 * @author Mikołaj Kalata
 * @version 2.0.0.1
 */
public interface CurrentWorkingDirectoryPath {
    /**
     * Metoda <code>pathToString</code> zwraca aktualną ścieżke do folderu roboczego
     * @param person
     * @return ścieżke folderu roboczego
     */
    default String pathToString(Person person){
        Path CurrentRelativePath = Paths.get("");
        String currentPath = CurrentRelativePath.toAbsolutePath().toString()
                + "/src/main/IDs/" + person.getPesel() + ".jpg";

        return currentPath;
   }

}
