package org.MK.PopRegister.tools;

/**
 * Interfejs <code>Validator</code> zawiera zbiór metod do nadpisania, walidujących wprowadzone
 * dane przez użytkownika
 * @author Mikołaj Kalata
 * @version 2.0.0.1
 */
public interface Validator {
    boolean inputValidator();
    boolean validateSurname(String s);
    boolean isContainingLetters(String s);
    boolean isContainingLong(String s);
    boolean isContainingInt(String s);
    boolean isContainingIntLet(String s);
    boolean isEmailValid(String s);
    boolean isPostalValid(String s);
}
