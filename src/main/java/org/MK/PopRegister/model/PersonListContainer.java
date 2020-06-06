package org.MK.PopRegister.model;



import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Obiekt <code>PersonListContainer</code> zawiera liste typu Person, umożliwiająca zapis/odczyt do/z plików XML.
 * @author Mikołaj Kalata
 * @version 2.0.0.1
 */

@XmlRootElement(name = "persons")
public class PersonListContainer {

    private List<Person> containerPersons;
    @XmlElement(name = "person")
    public List<Person> getContainerPersons(){
        return containerPersons;
    }

    public void setContainerPersons(List<Person> personList) {
        this.containerPersons = personList;
    }




}
