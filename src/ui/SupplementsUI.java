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
    private final Scanner sc;
    private final String fileName = "resources/supplements.csv";


    public SupplementsUI(SupplementsApp app) {
        this.app = app;
        this.sc = new Scanner(System.in);
    }

    public void start() {
        try {
            app.loadFromFile(fileName);
        } catch (IOException e) {
            print("Nie udało się wczytać pliku: " + e.getMessage());
        }
        boolean running = true;
        while (running) {
            showMenu();
            int choice = readInt("Wybierz numer");
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
                            print("Nie udało się zapisać pliku: " + e.getMessage());
                        }
                        print("Do widzenia!");
                        running = false;
                    }
                }
            } catch (NoSuchOptionException e) {
                print("Niepoprawny wybór, spróbuj ponownie");
            }
        }
    }

    private void deleteSupplement() {
        print("Wpisz nazwę suplementu który chcesz usunąć");
        String name = sc.nextLine();
        if (app.deleteSupplementByName(name)){
            print("Suplement został usunięty");
        }else {
            print("Nie znaleziono suplementu o podanej nazwie");
        }
    }

    private void displayByTime() {
        TimeOfDay.printMenu();
        int choice = readInt("Wybierz porę dnia");
        TimeOfDay time = TimeOfDay.fromCode(choice);
        if (time == null) {
            print("Niepoprawny wybór, spróbuj ponownie");
            return;
        }
        print("Suplementy według pory dnia: " + time.getDescription());
        app.displaySupplementsByTime(time);
    }

    private void displayAll() {
        print("--- Wszystkie suplementy ---");
        app.displayAllSupplements();
        print("Ilość wszystkich suplementów: " + app.getSupplements().size());
    }

    private void addSupplement() {
        print("--- Dodawanie suplementu ---");
        print("Podaj nazwę suplementu");
        String name = sc.nextLine();
        int dose = readInt("Podaj dawkę");
        Set<TimeOfDay> times = addTimes();

        if (app.addSupplement(new Supplement(name, dose, times))) {
            print("Dodano nowy suplement:" + name);
        }else {
            print("Suplement o tej nazwie już istnieje");
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
        int another = readInt("Czy chcesz dodać kolejną porę dnia? 1 - tak, 2 - nie");
            if (another == 1) {
                return true;
            } else if (another == 2) {
                return false;
            } else {
                print("Niepoprawny wybór. Wpisz 1 lub 2 ");
            }
        }
    }

    private void getTimeChoice(Set<TimeOfDay> times) {
        int choice = readInt("Wybierz porę dnia");
        TimeOfDay time = TimeOfDay.fromCode(choice);
        if (time != null) {
            if (times.add(time)) {
                print(time.getDescription() + " dodane");
            } else {
                print("Ta pora dnia została dodana");
            }
        } else {
            print("Niepoprawny wybór");
        }
    }

    private int readInt(String prompt) {
        while (true) {
            print(prompt);
            try {
                int number = Integer.parseInt(sc.nextLine());
                if (number >= 0) {
                    return number;
                }else {
                    print("Niepoprawny wybór");
                }
            } catch (NumberFormatException e) {
                print("Niepoprawny format, wpisz liczbę całkowitą");
            }
        }
    }

    private void showMenu() {
        print("----MENU GŁÓWNE---");
        print("Wybierz opcję:");
        MenuOption.printMenu();
    }

    private void print(String message) {
        System.out.println(message);
    }
}
