package app;

import data.Supplement;
import enums.TimeOfDay;

import java.util.HashSet;
import java.util.Set;

public class SupplementsApp {
    private Set<Supplement> supplements =  new HashSet<>();

    public boolean addSupplement(Supplement s) {
        return supplements.add(s);
    }
    public boolean removeSupplements (Supplement s) {
        return supplements.remove(s);
    }

    public void displayAllSupplements() {
        supplements.forEach(System.out::println);
    }

    public void displaySupplementsByTime(TimeOfDay time) {
        supplements.stream()
                .filter(s -> s.getTimes().contains(time))
                .forEach(System.out::println);
    }

}
