/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import storage.Storage;
import ui.UI;

/**
 *
 * @author rando
 */
public class UIController {

    private UI ui;
    private Storage storage;

    public UIController(UI ui, Storage storage) {
        this.ui = ui;
        this.storage = storage;
    }

    public void startProgram() {
        int choice = 0;
        while (choice != 9) {
            showHeader();
            ui.println("");
            ui.println("1) Adm. Medlemmer");
            ui.println("2) Adm. Kontingenter");
            ui.println("3) Resultater");
            ui.println("\n9) afslut");

            choice = parseMenuChoice();
            switch (choice) {
                case 1:
                    admMemberMenu();
                    break;
                case 2:
                    admKontingenterMenu();
                    break;
                case 3:
                    break;
            }
        }
    }

    private void admMemberMenu() {
        int choice = 0;
        while (choice != 9) {
            showHeader();
            ui.println("------------Adm. Medlemmer------------\n");
            ui.println("1) Tilføj");
            ui.println("2) Rediger");
            ui.println("3) Fjern");
            ui.println("\n9) Tilbage");

            choice = parseMenuChoice();
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
        }
    }
    
    
    
    
    
    
    
    
    

    private void admKontingenterMenu() {
        int choice = 0;
        while (choice != 9) {
            showHeader();
            ui.println("-----------Adm. Kontingenter----------\n");
            ui.println("1) Se medlemmer i restance");
            ui.println("2) Registrer betaling");
            ui.println("\n9) Tilbage");

            choice = parseMenuChoice();
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
            }
        }
    }
    
    private void resultaterMenu(){
        int choice = 0;
        while (choice != 9) {
            showHeader();
            ui.println("--------------Resultater--------------\n");
            ui.println("1) Se resultater");
            ui.println("2) Indskriv resultater");
            ui.println("\n9) Tilbage");

            choice = parseMenuChoice();
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
            }
        }
    }

    private void showHeader() {
        ui.println("---------------Delfinen---------------");
    }

    private int parseMenuChoice() {
        int value = 0;
        boolean running = true;
        while (running) {
            try {
                value = Integer.parseInt(ui.getUserInput());
                running = false;
            } catch (NumberFormatException e) {
                ui.println("Du er en skovl. Brug et tal.");
            }
        }
        return value;
    }

}
