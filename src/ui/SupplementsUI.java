package ui;

import app.SupplementsApp;
import data.Supplement;
import enums.MenuOption;
import enums.TimeOfDay;
import java.time.LocalDate;
import java.util.*;


public class SupplementsUI {
    private SupplementsApp app;
    private Scanner sc;


    public SupplementsUI(SupplementsApp app) {
        this.app = app;
        this.sc = new Scanner(System.in);
    }

    public void start() {
        app.loadFromFile("resources/suplementy.dat");
        boolean running = true;
        while (running) {
            showMenu();
            int choice = getUserChoice();
            MenuOption option = MenuOption.fromCode(choice);
            if (option == null) {
                System.out.println("Niepoprawny wybór, spróbuj ponownie");
                continue;
            }
            switch (option) {
                case ADD_SUPPLEMENT -> addSupplement();
                case DISPLAY_ALL -> displayAll();
                case DISPLAY_BY_TIME -> displayByTime();
                case DELETE_SUPPLEMENT -> deleteSupplement();
                case EXIT -> {
                    app.saveToFile("resources/suplementy.dat");
                    System.out.println("Do widzenia!");
                    running = false;
                }
            }

        }
    }

    private void deleteSupplement() {
        System.out.println("Wpisz nazwę suplementu który chcesz usunąć");
        String name = sc.nextLine();
        if (app.deleteSuplementByName(name)){
            System.out.println("Suplement został usunięty");
        }else {
            System.out.println("Nie znaleziono suplementu o podanej nazwie");
        }
    }

    private void displayByTime() {
        TimeOfDay.printMenu();
        int choice = readInt("Wybierz porę dnia");
        TimeOfDay time = TimeOfDay.fromCode(choice);
        if (time == null) {
            System.out.println("Niepoprawny wybór, spróbuj ponownie");
            return;
        }
        System.out.println("Suplementy według pory dnia: " + time.getDescription());
        app.displaySupplementsByTime(time);
    }

    private void displayAll() {
        Set<Supplement> allSupplements = app.getSupplements();
        if (allSupplements.isEmpty()) {
            System.out.println("Brak suplementów w systemie");
        }else {
            System.out.println("Lista wszystkich suplementów: ");
            allSupplements.forEach(System.out::println);
            int size = allSupplements.size();
            System.out.println("Ilośc: " + size);
        }
    }

    private void addSupplement() {
        System.out.println("--- Dodawanie suplementu ---");
        System.out.println("Podaj nazwę suplementu");
        String name = sc.nextLine();
        int dose = readInt("Podaj dawkę");
        Set<TimeOfDay> times = addTimes();
        int quantity = readInt("Podaj ilość");
        app.addSupplement(new Supplement(name, dose, times, quantity));
        System.out.println("Dodano nowy suplement:" + name);
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
                System.out.println("Niepoprawny wybór. Wpisz 1 lub 2 ");
            }
        }
    }

    private void getTimeChoice(Set<TimeOfDay> times) {
        int choice = readInt("Wybierz porę dnia");
        TimeOfDay time = TimeOfDay.fromCode(choice);
        if (time != null) {
            if (times.add(time)) {
                System.out.println(time.getDescription() + " dodane");
            } else {
                System.out.println("Ta pora dnia została dodana");
            }
        } else {
            System.out.println("Niepoprawny wybór");
        }
    }

    private int getUserChoice() {
        return readInt("Wybierz numer");
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                int number = Integer.parseInt(sc.nextLine());
                if (number >= 0) {
                    return number;
                }else {
                    System.out.println("Wybierz jedną z liczb:");
                    MenuOption.printMenu();
                }
            } catch (NumberFormatException e) {
                System.out.println("Niepoprawny format, wpisz liczbę całkowitą");
            }
        }
    }


    private void showMenu() {
        System.out.println("----MENU GŁÓWNE---");
        System.out.println("Wybierz opcję:");
        MenuOption.printMenu();
    }


}
