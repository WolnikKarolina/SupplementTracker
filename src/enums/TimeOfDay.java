package enums;

public enum TimeOfDay {
    MORNING(1, "Rano"),
    BREAKFAST(2, "Do śniadania"),
    NOON(3, "Do obiadu"),
    EVENING(4, "Do kolacji"),
    NIGHT(5, "Przed snem");

    private final int code;
    private final String description;

    TimeOfDay(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static TimeOfDay fromCode (int code) {
        for (TimeOfDay option : values()) {
            if ( option.code == code) {
                return option;
            }
        }
        return null;
    }

    public static void printMenu() {
        for (TimeOfDay option : values()) {
            System.out.println(option.code + " - " + option.description);

        }
    }
}
