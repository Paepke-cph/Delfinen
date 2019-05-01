/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.ArrayList;
import java.util.HashMap;
import storage.Storage;
import ui.UI;

/**
 *
 * @author rando
 */
public class UIController {

    private UI ui;
    private Storage storage;
    private Members memberHandler;

    public UIController(UI ui, Storage storage) {
        this.ui = ui;
        this.storage = storage;
        memberHandler = new Members(storage);
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

            choice = parseUserInputToInt();
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

            choice = parseUserInputToInt();
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

    private void addMember() {
        ui.print("Navn: ");
        String name = ui.getUserInput();
        ui.print("\nAlder: ");
        int age = parseUserInputToInt();
        boolean junior = (age < 18);
//TODO: Hent næste member ID fra database.
        boolean notDone = true;
        while (notDone) {
            ui.print("\nKompetitiv svømmer? (ja/nej): ");
            String competitiveSwimming = ui.getUserInput();
            if (competitiveSwimming.equalsIgnoreCase("ja")) {
                notDone = false;
                // Lav kompetitiv
                // Samler descipliner
                // Sætter træner
                // Laver kompetitivt element.
            } else if (competitiveSwimming.equalsIgnoreCase("nej")) {
                notDone = false;
            }
        }
        if(junior){
            Member memeber = new JuniorMember();
        }

//        ui.println("\nVælg svømme disciplin: ");
//        ui.println("1) " + SwimmingDiscipline.BUTTERFLY.getDisciplineName());
//        ui.println("2) " + SwimmingDiscipline.BREASTSTROKE.getDisciplineName());
//        ui.println("3) " + SwimmingDiscipline.BACKSTROKE.getDisciplineName());
//        ui.println("4) " + SwimmingDiscipline.CRAWL.getDisciplineName());
//        ui.print("\nDer kan vælges flere: ");
//        int disciplin = parseUserInputToInt();
    }

    private void admKontingenterMenu() {
        int choice = 0;
        while (choice != 9) {
            showHeader();
            ui.println("-----------Adm. Kontingenter----------\n");
            ui.println("1) Se medlemmer i restance");
            ui.println("2) Registrer betaling");
            ui.println("\n9) Tilbage");

            choice = parseUserInputToInt();
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
            }
        }
    }

    private void resultaterMenu() {
        int choice = 0;
        while (choice != 9) {
            showHeader();
            ui.println("--------------Resultater--------------\n");
            ui.println("1) Se resultater");
            ui.println("2) Indskriv resultater");
            ui.println("\n9) Tilbage");

            choice = parseUserInputToInt();
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

    private int parseUserInputToInt() {
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

    public HashMap<String, ArrayList<Member>> getAllMembers() {
        return memberHandler.getMembers();
    }

}
