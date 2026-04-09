package main;

import app.SupplementsApp;
import ui.SupplementsUI;

public class Main {
    public static void main(String[] args) {
        SupplementsApp app = new SupplementsApp();
        SupplementsUI ui = new SupplementsUI(app);

        ui.start();
    }
}
