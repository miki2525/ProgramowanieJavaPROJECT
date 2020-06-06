package org.MK.PopRegister.tools;

import org.MK.PopRegister.model.Person;

import java.util.List;
/**
 * Klasa <code>Find</code> służy do wyszukiwania frazy w danej liście tablicowej
 * @author Mikołaj Kalata
 * @version 2.0.0.1
 */

public class Find {
    /**
     * Metoda<code>findThePhrase</code>przeszukuje listę osób, zawierającą podaną frazę (phrase)
     * @param phrase
     * @param sourceList
     * @param destList
     * @return zwraca listę osób zawierająca daną frazę ('phrase')
     */
    public static List<Person> findThePhrase(String phrase, List<Person> sourceList, List<Person> destList) {


        try{
        for (int i = 0; i < sourceList.size(); i++) {


                String postalCode = phrase;
                if (postalCode.charAt(2) == '-') {
                    postalCode = phrase.substring(0, 2) + phrase.substring(3);
                }

                if (phrase.toUpperCase().equals(sourceList.get(i).getName().toUpperCase())) {
                    destList.add(sourceList.get(i));
                }

                if (phrase.toUpperCase().equals(sourceList.get(i).getSurname().toUpperCase())) {
                    if (!destList.contains(sourceList.get(i))) {
                        destList.add(sourceList.get(i));
                    }
                }

                if (phrase.equals(sourceList.get(i).getPesel())) {
                    if (!destList.contains(sourceList.get(i))) {
                        destList.add(sourceList.get(i));
                    }
                }

                if (phrase.toUpperCase().equals(sourceList.get(i).getLocality().toUpperCase())) {
                    if (!destList.contains(sourceList.get(i))) {
                        destList.add(sourceList.get(i));
                    }
                }

                if (phrase.toUpperCase().equals(sourceList.get(i).getStreet().toUpperCase())) {
                    if (!destList.contains(sourceList.get(i))) {
                        destList.add(sourceList.get(i));
                    }
                }
                ////gdy użytkownik poda nazwe ulicy z numerem np. Długa 6b
                if (phrase.toUpperCase().equals(sourceList.get(i).toStringStreetNumber().toUpperCase())) {
                    if (!destList.contains(sourceList.get(i))) {
                        destList.add(sourceList.get(i));
                    }
                }


                if (postalCode.equals(sourceList.get(i).getPostalCode())) {
                    if (!destList.contains(sourceList.get(i))) {
                        destList.add(sourceList.get(i));
                    }
                }
                if (sourceList.get(i).getEmail() != null) {
                    if (phrase.toUpperCase().equals(sourceList.get(i).getEmail().toUpperCase())) {
                        if (!destList.contains(sourceList.get(i))) {
                            destList.add(sourceList.get(i));
                        }
                    }
                }
                if (sourceList.get(i).getPhoneNumber() != null) {
                    if (phrase.equals(sourceList.get(i).getPhoneNumber())) {
                        if (!destList.contains(sourceList.get(i))) {
                            destList.add(sourceList.get(i));
                        }
                    }
                }

            }
        return destList;
            }
        catch (StringIndexOutOfBoundsException e) {
            return null;

        }

    }
}