package app;

import data.Supplement;
import enums.TimeOfDay;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SupplementsApp {
    private Set<Supplement> supplements =  new HashSet<>();


    public Set<Supplement> getSupplements() {
        return supplements;
    }

    public boolean addSupplement(Supplement s) {
        return supplements.add(s);
    }
    public boolean deleteSuplementByName (String name) {
        Iterator<Supplement> it = supplements.iterator();
        while (it.hasNext()) {
            Supplement s = it.next();
            if ( s.getName().equalsIgnoreCase(name)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public void displayAllSupplements() {
        supplements.forEach(System.out::println);
    }

    public void displaySupplementsByTime(TimeOfDay time) {
        supplements.stream()
                .filter(s -> s.getTimes().contains(time))
                .forEach(System.out::println);
    }

    public void saveToFile ( String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(supplements);
            System.out.println("Dane zapisane do pliku: " + fileName);
        } catch (IOException e) {
            System.out.println("Nie udało się zapisac danych do pliku: " + e.getMessage());
        }
    }

    public void loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            supplements = (Set<Supplement>) ois.readObject();
            System.out.println("Dane wczytane z pliku: " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Błąd wczytywania danych: " + e.getMessage());
        }
    }

}
