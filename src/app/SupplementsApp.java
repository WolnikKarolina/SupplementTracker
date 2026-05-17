package app;

import data.Supplement;
import enums.TimeOfDay;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
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

    public List<Supplement> sortByName(){
        return supplements.stream()
                .sorted(Comparator.comparing(Supplement::getName))
                .collect(Collectors.toList());

    }

    public void displayAllSupplements() {
            sortByName().forEach(System.out::println);
    }

    public void displaySupplementsByTime(TimeOfDay time) {
        supplements.stream()
                .filter(s -> s.getTimes().contains(time))
                .forEach(System.out::println);
    }

    public void saveToFile(String fileName) throws IOException {
        Path path = Path.of(fileName);
        List<String> sup = supplements.stream()
                .map(s -> s.getName() + ";" +
                        s.getDose() + ";" +
                        s.getTimes().stream()
                                .map(Enum::name)
                                .collect(Collectors.joining(","))
                ).toList();
        Files.write(path, sup, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public void loadFromFile(String fileName) throws IOException {
        Path path = Path.of(fileName);
        if (!Files.exists(path))
            return;
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
    }
}
