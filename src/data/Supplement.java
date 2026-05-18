package data;

import enums.TimeOfDay;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Supplement {
    private String name;
    private int dose;
    private Set<TimeOfDay> times;


    public Supplement(String name, int dose, Set<TimeOfDay> times) {
        this.name = name.trim();
        this.dose = dose;
        this.times = times;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public Set<TimeOfDay> getTimes() {
        return times;
    }

    public void setTimes(Set<TimeOfDay> times) {
        this.times = times;
    }

    @Override
    public String toString() {
        String timesDescription = times.stream()
                .map(TimeOfDay::getDescription)
                .collect(Collectors.joining(", "));
        return name + ": dawka: " + dose + ": pora dnia: " + timesDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Supplement that = (Supplement) o;
        return Objects.equals(name.toLowerCase(), that.name.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name.toLowerCase());
    }
}
