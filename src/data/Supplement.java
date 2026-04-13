package data;

import enums.TimeOfDay;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class Supplement implements Serializable {
    private String name;
    private int dose;
    private Set<TimeOfDay> times;


    public Supplement(String name, int dose, Set<TimeOfDay> times) {
        this.name = name;
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
        return name + ": dawka: " + dose;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Supplement that = (Supplement) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
