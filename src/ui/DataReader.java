package ui;

import java.util.Scanner;



public class DataReader {
    private final Scanner sc = new Scanner(System.in);
    private final Printer printer;

    public DataReader(Printer printer) {
        this.printer = printer;
    }

    public String readNonEmptyString(String orompt) {
        printer.printLine(prompt);
        String text;
        while (true) {
            text = sc.nextLine();
            if (!text.isEmpty()) {
                break;
            }
            printer.printLine("Pole nie może być puste");
        }
        return text;
    }

    public int readInt(String prompt) {
        while (true) {
            printer.printLine(prompt);
            try {
                int number = Integer.parseInt(sc.nextLine());
                if (number >= 0) {
                    return number;
                }else {
                    printer.printLine("Niepoprawny wybór");
                }
            } catch (NumberFormatException e) {
                printer.printLine("Niepoprawny format, wpisz liczbę całkowitą");
            }
        }
    }
}
