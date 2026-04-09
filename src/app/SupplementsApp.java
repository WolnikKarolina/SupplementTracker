package app;

import data.Supplement;
import enums.TimeOfDay;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SupplementsApp {
    private Set<Supplement> supplements =  new HashSet<>();

    public SupplementsApp() {
        this.supplements = new HashSet<>();
    }

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

}
