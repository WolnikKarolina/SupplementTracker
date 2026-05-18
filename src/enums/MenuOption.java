package enums;

import exceptions.NoSuchOptionException;
import ui.Printer;

public enum MenuOption {
    EXIT(0, "Wyjście z programu"),
    ADD_SUPPLEMENT(1, "Dodaj suplement"),
    DISPLAY_ALL(2, "Wyświetl wszystkie suplementy"),
    DISPLAY_BY_TIME(3, "Wyświetl suplementy według pory dnia"),
    DELETE_SUPPLEMENT(4, "Usuń suplement");

    private final int code;
    private final String description;

    MenuOption(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static MenuOption fromCode(int code) throws NoSuchOptionException {
        for (MenuOption option : values()) {
            if (option.code == code) {
                return option;
            }
        }
        throw new NoSuchOptionException("Niepoprawna opcja");
    }

    public static void printMenu(Printer printer) {
        for (MenuOption option : values()) {
            printer.printLine(option.getCode() + " - " + option.description);
        }
    }
}
