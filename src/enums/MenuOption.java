package enums;

public enum MenuOption {
    ADD_SUPPLEMENT(1, "Dodaj suplement"),
    DISPLAY_ALL(2, "Wyświetl wszystkie suplementy"),
    DISPLAY_BY_TIME(3, "Wyświetl suplementy według pory dnia"),
    DELETE_SUPPLEMENT(4, "Usuń suplement"),
    EXIT(0, "Wyjście z programu");

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

    public static MenuOption fromCode(int code) {
        for (MenuOption option : values()) {
            if (option.code == code) {
                return option;
            }
        }
        return null;
    }

    public static void printMenu() {
        for (MenuOption option : values()) {
            System.out.println(option.getCode() + " - " + option.description);
        }
    }
}
