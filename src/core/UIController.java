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

            choice = parseUserInputToInt(1, 2, 3, 9);
            switch (choice) {
                case 1:
                    admMemberMenu();
                    break;
                case 2:
                    admKontingenterMenu();
                    break;
                case 3:
                    resultsMenu();
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

            choice = parseUserInputToInt(1, 2, 3, 9);
            switch (choice) {
                case 1:
                    addMember();
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
        CompetitionSwimmer comp = createCompetitiveSwimmer();

        boolean active = yesNoOption("Vil du have et aktivt medlemskab?");
        if (junior) {
            Member member = new JuniorMember(active, name, age, 0, comp);//ID ER HER IKKE ENDNU
            memberHandler.addMember("JuniorMember", member);
            ui.println("Nyt Junior Medlem Oprettet");
            ui.println(member.toString());
        } else {
            Member member = new SeniorMember(active, name, age, 0, comp);//ID ER HER IKKE ENDNU
            memberHandler.addMember("SeniorMember", member);
            ui.println("Nyt Senior Medlem Oprettet");
            ui.println(member.toString());
        }
    }

    private CompetitionSwimmer createCompetitiveSwimmer() {
        if (yesNoOption("Kompetitiv svømmer?")) {
            boolean notDone = true;
            ArrayList<SwimmingDiscipline> discipline = SwimmingDiscipline.getDisciplinesAsList();
            ArrayList<SwimmingDiscipline> selectedDiscipline = new ArrayList<>();
            int[] options;
            while (notDone && discipline.size() > 0) {
                if (selectedDiscipline.size() > 0) {
                    options = new int[discipline.size() + 1];
                } else {
                    options = new int[discipline.size()];
                }
                for (int i = 0; i < discipline.size(); i++) {
                    ui.println(i + 1 + ") " + discipline.get(i).getDisciplineName());
                    options[i] = i + 1;
                }
                if (selectedDiscipline.size() > 0) {
                    ui.println("\n9) Fortsæt");
                    options[options.length - 1] = 9;
                }
                int choice = parseUserInputToInt(options);
                if (choice == 9) {
                    notDone = false;
                } else {
                    selectedDiscipline.add(discipline.remove(choice - 1)); // Remove fjerner og returnerer hvilken værdi der blev fjernet.
                }
            }
// TODO: Hent træner fra database, ved brug af navn?
            return new CompetitionSwimmer(null, selectedDiscipline);

        }
        return null;
    }

    private void admKontingenterMenu() {
        int choice = 0;
        while (choice != 9) {
            showHeader();
            ui.println("-----------Adm. Kontingenter----------\n");
            ui.println("1) Se medlemmer i restance");
            ui.println("2) Registrer betaling");
            ui.println("\n9) Tilbage");

            choice = parseUserInputToInt(1, 2, 9);
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
            }
        }
    }

    private void resultsMenu() {
        int choice = 0;
        while (choice != 9) {
            showHeader();
            ui.println("--------------Resultater--------------\n");
            ui.println("1) Se resultater");
            ui.println("2) Indskriv resultater");
            ui.println("\n9) Tilbage");

            choice = parseUserInputToInt(1, 2, 9);
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

    private int parseUserInputToInt(int... options) {
        int value = 0;
        boolean running = true;
        while (running) {
            try {
                value = Integer.parseInt(ui.getUserInput());
                if (options.length != 0) {
                    for (int option : options) {
                        if (option == value) {
                            running = false;
                        }
                    }
                } else {
                    running = false;
                }
            } catch (NumberFormatException e) {
                ui.println("Du er en skovl. Brug et tal.");
            }
        }
        return value;
    }

    private boolean yesNoOption(String question) {
        boolean notDone = true;
        while (notDone) {
            ui.print("\n" + question + " (ja/nej): ");
            String anw = ui.getUserInput();
            if (anw.equalsIgnoreCase("ja")) {
                return true;
            } else if (anw.equalsIgnoreCase("nej")) {
                return false;
            }
        }
        return false;
    }

    public HashMap<String, ArrayList<Member>> getAllMembers() {
        return memberHandler.getMembers();
    }

}
