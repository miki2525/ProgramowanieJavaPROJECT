package org.MK.PopRegister.tools;

import java.time.LocalDate;
import javax.xml.bind.annotation.adapters.XmlAdapter;
/**
 * Obiekt <code>LocalDateXMLConverter</code> kowertuje LocalDate - String dla plików XML
 * @author Mikołaj Kalata
 * @version 2.0.0.1
 */

public class LocalDateXMLConverter extends XmlAdapter<String, LocalDate> {
    /**
     *Metoda <code>marshal</code>zamienia wartość typu LocalDate na String
     * @param s
     * @return
     * @throws Exception
     */
    @Override
    public String marshal(LocalDate s) throws Exception {
        return s.toString();
    }

    /**
     * Metoda<code>unmarshal</code> zamienia wartość typu String na LocalDate
     * @param s
     * @return
     * @throws Exception
     */
    @Override
    public LocalDate unmarshal(String s) throws Exception {
        return LocalDate.parse(s);
    }


}