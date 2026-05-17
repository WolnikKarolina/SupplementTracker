package ui;

import app.SupplementsApp;
import data.Supplement;
import enums.MenuOption;
import enums.TimeOfDay;
import exceptions.NoSuchOptionException;

import java.io.IOException;
import java.util.*;


public class SupplementsUI {
    private final SupplementsApp app;
    private final Printer printer;
    private final DataReader dataReader;
    private final String fileName = "resources/supplements.csv";


    public SupplementsUI(SupplementsApp app) {
        this.app = app;
        this.printer = new Printer();
        this.dataReader = new DataReader(printer);

    }

    public void start() {
        try {
            app.loadFromFile(fileName);
        } catch (IOException e) {
            printer.printLine("Nie udało się wczytać pliku: " + e.getMessage());
        }
        boolean running = true;
        while (running) {
            showMenu();
            int choice = dataReader.readInt("Wybierz numer");
            try {
                MenuOption option = MenuOption.fromCode(choice);
                switch (option) {
                    case ADD_SUPPLEMENT -> addSupplement();
                    case DISPLAY_ALL -> displayAll();
                    case DISPLAY_BY_TIME -> displayByTime();
                    case DELETE_SUPPLEMENT -> deleteSupplement();
                    case EXIT -> {
                        try {
                            app.saveToFile((fileName));
                        } catch (IOException e) {
                            printer.printLine("Nie udało się zapisać pliku: " + e.getMessage());
                        }
                        printer.printLine("Do widzenia!");
                        running = false;
                    }
                }
            } catch (NoSuchOptionException e) {
                printer.printLine("Niepoprawny wybór, spróbuj ponownie");
            }
        }
    }

    private void deleteSupplement() {
        printer.printLine("Wpisz nazwę suplementu który chcesz usunąć");
        String name = dataReader.readString();
        if (app.deleteSupplementByName(name)){
            printer.printLine("Suplement został usunięty");
        }else {
            printer.printLine("Nie znaleziono suplementu o podanej nazwie");
        }
    }

    private void displayByTime() {
        TimeOfDay.printMenu();
        int choice = dataReader.readInt("Wybierz porę dnia");
        try {
            TimeOfDay time = TimeOfDay.fromCode(choice);
            printer.printLine("Suplementy według pory dnia: " + time.getDescription());
            app.displaySupplementsByTime(time);
        } catch (NoSuchOptionException e) {
            printer.printLine(e.getMessage());
        }
    }

    private void displayAll() {
        printer.printLine("--- Wszystkie suplementy ---");
        app.displayAllSupplements();
        printer.printLine("Ilość wszystkich suplementów: " + app.getSupplements().size());
    }

    private void addSupplement() {
        printer.printLine("--- Dodawanie suplementu ---");
        printer.printLine("Podaj nazwę suplementu");
        String name = dataReader.readString();
        int dose = dataReader.readInt("Podaj dawkę");
        Set<TimeOfDay> times = addTimes();
        if (app.addSupplement(new Supplement(name, dose, times))) {
            printer.printLine("Dodano nowy suplement:" + name);
        }else {
            printer.printLine("Suplement o tej nazwie już istnieje");
        }
    }

    private Set<TimeOfDay> addTimes() {
        Set<TimeOfDay> times = new HashSet<>();
        boolean addingTimes = true;
        while (addingTimes) {
            TimeOfDay.printMenu();
            getTimeChoice(times);
            addingTimes = addingAnotherTime();
        }
        return times;
    }

    private boolean addingAnotherTime() {
        while (true) {
        int another = dataReader.readInt("Czy chcesz dodać kolejną porę dnia? 1 - tak, 2 - nie");
            if (another == 1) {
                return true;
            } else if (another == 2) {
                return false;
            } else {
                printer.printLine("Niepoprawny wybór. Wpisz 1 lub 2 ");
            }
        }
    }

    private void getTimeChoice(Set<TimeOfDay> times) {
        int choice = dataReader.readInt("Wybierz porę dnia");
        try {
            TimeOfDay time = TimeOfDay.fromCode(choice);
            if (times.add(time)) {
                printer.printLine(time.getDescription() + " dodane");
            } else {
                printer.printLine("Ta pora dnia już została dodana");
            }
        } catch (NoSuchOptionException e) {
            printer.printLine(e.getMessage());
        }
    }

    private void showMenu() {
        printer.printLine("----MENU GŁÓWNE---");
        printer.printLine("Wybierz opcję:");
        MenuOption.printMenu();
    }


}
