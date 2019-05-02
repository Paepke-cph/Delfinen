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
            ui.println("------------Adm. Medlemmer------------");
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
                    removeMember();
                    break;
            }
        }
    }

    private void addMember() {
        //TODO: Hent næste member ID fra database.
        int id = 0;
        ui.print("Navn: ");
        String name = ui.getUserInput();
        ui.print("Alder: ");
        int age = parseUserInputToInt();
        Member member;
        if (!yesNoOption("Opret Træner?")) {
            boolean junior = (age < 18);
            CompetitionSwimmer comp = createCompetitiveSwimmer();
            boolean active = yesNoOption("Vil du have et aktivt medlemskab?");
            if (junior) {
                member = new JuniorMember(active, name, age, id, comp);//ID ER HER IKKE ENDNU
                memberHandler.addMember("JuniorMember", member);
            } else {
                member = new SeniorMember(active, name, age, id, comp);//ID ER HER IKKE ENDNU
                memberHandler.addMember("SeniorMember", member);
            }
            ui.println("\nNyt Medlem Oprettet");
            ui.println(member.toString());
        } else {
            member = new Member(name, age, id, null);
            memberHandler.addMember("Coach", member);
            ui.println("\nNyt Træner Oprettet");
            ui.println(member.toString());
        }
    }

    private CompetitionSwimmer createCompetitiveSwimmer() {
        if (yesNoOption("Kompetitiv svømmer?")) {
            ui.println("Vælg alle discipliner du ønsker: ");
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
                ui.println("");//Empty Line
            }
            // TODO: Hent træner fra database, ved brug af navn?
            return new CompetitionSwimmer(null, selectedDiscipline);

        }
        return null;
    }

    private void removeMember() {
        boolean notDone = true;
        while (notDone) {
            ui.print("Søg efter navn: ");
            ArrayList<Member> resultList = memberHandler.getMembersByName(ui.getUserInput());
            if (resultList.isEmpty()) {
                ui.println("Din søgning gav ingen resultater.");
            } else {
                int[] mID = new int[resultList.size() + 1];
                for (int i = 0; i < resultList.size(); i++) {
                    ui.println(resultList.get(i).toString());
                    mID[i] = resultList.get(i).getId();
                }
                mID[mID.length-1] = -1;
                ui.println("Du kan vælge et ID som skal fjernes, eller bruge \"-1\" for at gå tilbage");
                int choice = parseUserInputToInt(mID);
                if(choice == -1){
// TODO(Tobias): indsæt remove metoden når den er lavet.
                }
            }
            if (yesNoOption("\nEr du færdig med din søgning?")) {
                notDone = false;
            }
        }
    }

    private void admKontingenterMenu() {
        int choice = 0;
        while (choice != 9) {
            showHeader();
            ui.println("-----------Adm. Kontingenter----------");
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
            ui.println("--------------Resultater--------------");
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
            ui.print(question + " (ja/nej): ");
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
