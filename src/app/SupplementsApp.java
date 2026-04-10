package app;

import data.Supplement;
import enums.TimeOfDay;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SupplementsApp {
    private Set<Supplement> supplements =  new HashSet<>();


    public Set<Supplement> getSupplements() {
        return supplements;
    }

    public boolean addSupplement(Supplement s) {
        return supplements.add(s);
    }

    public boolean deleteSupplementByName (String name) {
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
            System.out.println("Ilość suplementów: " + supplements.size());
    }

    public void displaySupplementsByTime(TimeOfDay time) {
        supplements.stream()
                .filter(s -> s.getTimes().contains(time))
                .forEach(System.out::println);
    }

    public void saveToFile(String fileName) {
        Path path = Path.of(fileName);
        String content = supplements.stream()
                .map(s -> s.getName() + ";" +
                        s.getDose() + ";" +
                        s.getTimes().stream()
                                .map(Enum::name)
                                .collect(Collectors.joining(",")) + ";"
                )
                .collect(Collectors.joining("\n"));
        System.out.println("Dane zapisane do pliku: " + fileName);
        try {
            Files.writeString(path, content);
        } catch (IOException e) {
            System.out.println("Nie udało się zapisac danych do pliku: " + e.getMessage());
        }

    }

    public void loadFromFile(String fileName) {
        Path path = Path.of(fileName);
        if (!Files.exists(path))
            return;
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String[] parts = line.split(";");
                String name = parts[0];
                int dose = Integer.parseInt(parts[1]);
                Set<TimeOfDay> times = new HashSet<>();
                if (!parts[2].isEmpty()) {
                    for (String t : parts[2].split(",")) {
                        times.add(TimeOfDay.valueOf(t));
                    }
                }
                Supplement s = new Supplement(name, dose, times);
                supplements.add(s);
            }
            System.out.println("Dane wczytane z pliku: " + fileName);
        } catch (IOException e) {
            System.out.println("Błąd wczytywania danych: " + e.getMessage());
        }
    }



}
