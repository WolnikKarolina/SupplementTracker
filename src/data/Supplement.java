package data;

import enums.TimeOfDay;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class Supplement {
    private String name;
    private int dose;
    private Set<TimeOfDay> times;
    private int quantity;
    private LocalDate startDate;

    public Supplement(String name, int dose, Set<TimeOfDay> times, int quantity) {
        this.name = name;
        this.dose = dose;
        this.times = times;
        this.quantity = quantity;
        this.startDate = LocalDate.now();
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return name + ": dawka: " + dose + ", pora dawkowania: " +  times;
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
