/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import storage.Storage;
import ui.UI;

/**
 *
 * @author rando
 */
public class UIController {

    private UI ui;
    private Storage storage;
    private StorageController storageController;
    private final int EXIT_TOKEN = -1;

    public UIController(UI ui, Storage storage) {
        this.ui = ui;
        this.storage = storage;
        storageController = new StorageController(storage);
    }

    public void startProgram() {
        int choice = 0;
        while (choice != EXIT_TOKEN) {
            showHeader();
            ui.println("");
            ui.println("1) Adm. Medlemmer");
            ui.println("2) Adm. Kontingenter");
            ui.println("3) Resultater");
            ui.println("\n" + EXIT_TOKEN + ") Afslut");

            choice = parseUserInputToInt(1, 2, 3, EXIT_TOKEN);
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
        while (choice != EXIT_TOKEN) {
            showHeader();
            ui.println("------------Adm. Medlemmer------------");
            ui.println("1) Tilføj");
            ui.println("2) Rediger");
            ui.println("3) Fjern");
            ui.println("\n" + EXIT_TOKEN + ") Tilbage");

            choice = parseUserInputToInt(1, 2, 3, EXIT_TOKEN);
            switch (choice) {
                case 1:
                    addMember();
                    break;
                case 2:
                    editMember();
                    break;
                case 3:
                    removeMember();
                    break;
            }
        }
    }

    private void addMember() {
        int id = storage.getNextMemberID();
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
                member = new JuniorMember(active, name, age, id, LocalDate.now(), comp);
                storageController.addMember(StorageController.getJuniorCat(), member);
            } else {
                member = new SeniorMember(active, name, age, id, LocalDate.now(), comp);
                storageController.addMember(StorageController.getSeniorCat(), member);
            }
            ui.println("\nNyt Medlem Oprettet");
            ui.println(member.toString());
        } else {
            member = new Member(true, name, age, id, LocalDate.now(), null);
            ui.println("\nNy Træner Oprettet");
            ui.println(member.toString());
        }
        storage.createMember(member);
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
                    ui.println("\n" + EXIT_TOKEN + ") Fortsæt");
                    options[options.length - 1] = EXIT_TOKEN;
                }
                int choice = parseUserInputToInt(options);
                if (choice == EXIT_TOKEN) {
                    notDone = false;
                } else {
                    selectedDiscipline.add(discipline.remove(choice - 1)); // Remove fjerner og returnerer hvilken værdi der blev fjernet.
                }
            }
            ui.println("");
            int[] memberID = displayCoaches();
            ui.print("Vælg ID på den ønskede træner: ");
            int choice = parseUserInputToInt(memberID);
            Member coach = storageController.searchMemberById(choice);
            return new CompetitionSwimmer(coach, selectedDiscipline);
        }
        return null;
    }

    private void editMember() {
        int[] memberIDList = findMemberByName();
        memberIDList[memberIDList.length-1] = EXIT_TOKEN;
        ui.println("Vælg et ID for at ændre\nEller vælg \""+ EXIT_TOKEN + "\" for at gå tilbage");
        int memberID = parseUserInputToInt(memberIDList);
        if(memberID != EXIT_TOKEN) {
            Member currentMember = storageController.searchMemberById(memberID);
            Member newMember = new Member(currentMember.isActive(),currentMember.getName(),currentMember.getAge(),currentMember.getId(),currentMember.getArrears(),currentMember.getCompetition());
            boolean notDone = true;
            while(notDone) {
                ui.println("Vælg element der skal ændres:");
                ui.println("1) Navn");
                ui.println("2) Alder");
                ui.println("3) Medlemsskabs status");
                ui.println("4) Kompetitive svømmer");
                int choice = parseUserInputToInt(1,2,3,EXIT_TOKEN);
                if(choice != EXIT_TOKEN) {
                    switch (choice){
                        case 1:
                            ui.print("Skriv nyt navn: ");
                            newMember.setName(ui.getUserInput());
                            break;
                        case 2:
                            ui.print("Skriv ny alder: ");
                            newMember.setAge(parseUserInputToInt());
                            break;
                        case 3:
                            String act = currentMember.isActive() ? "inaktive" : "aktive";
                            if(yesNoOption("Sæt status til " + act)) {
                                newMember.setActive(!currentMember.isActive());
                            }
                            else {
                                newMember.setActive(currentMember.isActive());
                            }
                            break;
                    }
                    ui.println("Ændringer der bliver foretaget:\n");
                    ui.println("Navn:\t" + currentMember.getName() + " -> " + newMember.getName());
                    ui.println("Alder:\t" + currentMember.getAge() + " -> " + newMember.getAge());

                    String act = currentMember.isActive() ? "Aktivt" : "Inaktivt";
                    String newAct = newMember.isActive() ? "Aktivt" : "Inaktivt";
                    ui.println("Medlemsskab Status:\t" + act + " -> " + newAct);
                    if(yesNoOption("\nFærdig med ændre?")) {
                        notDone = false;
                    }
                }
            }
            String cat = "";
            Member finalMember = null;
            if(currentMember.calculatePrice() < 500) {
                finalMember = new Member(newMember.isActive(),newMember.getName(),newMember.getAge(),newMember.getId(),newMember.getArrears(),newMember.getCompetition());
                cat = StorageController.getCoachCat();
            }
            else {
                if(newMember.getAge() < 18) {
                    finalMember = new JuniorMember(newMember.isActive(),newMember.getName(),newMember.getAge(),newMember.getId(),newMember.getArrears(),newMember.getCompetition());
                    cat = StorageController.getJuniorCat();
                }
                else {
                    finalMember = new SeniorMember(newMember.isActive(),newMember.getName(),newMember.getAge(),newMember.getId(),newMember.getArrears(),newMember.getCompetition());
                    cat = StorageController.getSeniorCat();
                }
            }
            storageController.removeMember(cat, currentMember);
            storageController.addMember(cat,finalMember);
            storage.updateMember(finalMember);
        }
    }

    private void removeMember() {
        int[] mID = findMemberByName();
        if (mID != null) {
            ui.print("\nDu kan vælge et ID som skal fjernes,\neller bruge \"" + EXIT_TOKEN + "\" for at gå tilbage: ");
            int choice = parseUserInputToInt(mID);
            if (choice != EXIT_TOKEN) {
                Member member = storageController.searchMemberById(choice);
                storageController.removeMember(member);
                storage.removeMember(choice);
            }
        }
    }

    private void admKontingenterMenu() {
        int choice = 0;
        while (choice != EXIT_TOKEN) {
            showHeader();
            ui.println("-----------Adm. Kontingenter----------");
            ui.println("1) Se medlemmer i restance");
            ui.println("2) Registrer betaling");
            ui.println("\n" + EXIT_TOKEN + ") Tilbage");

            choice = parseUserInputToInt(1, 2, EXIT_TOKEN);
            switch (choice) {
                case 1:
                    restanceMembers();
                    break;
                case 2:
                    paymentDatesMembers();
                    break;
            }
        }
    }

    private void restanceMembers() {
        ArrayList<Member> arreasMembers = storageController.getArrears();
        int[] mID = new int[arreasMembers.size() + 1];
        for (int i = 0; i < arreasMembers.size(); i++) {
            ui.println(arreasMembers.toString());
            mID[i] = arreasMembers.get(i).getId();
        }
        mID[mID.length - 1] = EXIT_TOKEN;
        if (yesNoOption("\nSkal et af ovenstående medlemmer have rettet deres restance status?")) {
            ui.print("Skriv medlemmets ID: \neller bruge " + EXIT_TOKEN + " for at gå tilbage: ");
            int choice = parseUserInputToInt(mID);
            if (choice != EXIT_TOKEN) {
                Member member = storageController.searchMemberById(choice);
                if (yesNoOption("\nSkal " + member.getName() + " have rettet restance pr. dags dato?")) {
                    member.setArrears(LocalDate.now());
                    storage.updateMember(member);
                    ui.println("\n" + member.toString());
                    ui.println("Opdateret pr. dags dato.");
                } else {
                    member.setArrears(parseUserInputToLocalDate());
                    storage.updateMember(member);
                    ui.println("\n" + member.toString());
                    ui.println("Opdateret pr. indtastet dato.");
                }

            }
        }
    }

    private void paymentDatesMembers() {
        int[] mID = findMemberByName();
        if (mID != null) {
            ui.print("\nSkriv medlems id for at vælge \neller bruge " + EXIT_TOKEN + " for at gå tilbage: ");
            int choice = parseUserInputToInt(mID);
            if (choice != EXIT_TOKEN) {
                Member member = storageController.searchMemberById(choice);
                ui.print("\nMedlemskabet for " + member.getName() + " udløber d. " + member.getArrears().toString());
                ui.print("\n\nVælg mellem 1 eller 2 års forlængelse \neller bruge " + EXIT_TOKEN + " for at gå tilbage: ");
                choice = parseUserInputToInt(1, 2, EXIT_TOKEN);
                switch (choice) {
                    case 1:
                        LocalDate dateFormatOneYear = member.getArrears().plusYears(1);
                        member.setArrears(dateFormatOneYear);
                        break;
                    case 2:
                        LocalDate dateFormatTwoYears = member.getArrears().plusYears(2);
                        member.setArrears(dateFormatTwoYears);
                        break;
                }
                storage.updateMember(member);
                ui.println("\n" + member.toString());
                ui.println("Opdateret pr. oplyst antal år.");
            }
        }
    }

    private void resultsMenu() {
        int choice = 0;
        while (choice != EXIT_TOKEN) {
            showHeader();
            ui.println("--------------Resultater--------------");
            ui.println("1) Se resultater for medlem");
            ui.println("2) Se resultater indenfor given disciplin");
            ui.println("3) Indskriv resultater");
            ui.println("\n" + EXIT_TOKEN + ") Tilbage");

            choice = parseUserInputToInt(1, 2, 3, EXIT_TOKEN);
            switch (choice) {
                case 1:
                    memberResult();
                    break;
                case 2:
                    disciplineResult();
                    break;
                case 3:
                    addResult();
                    break;
            }
        }
    }

    private void memberResult() {
        int[] mID = findMemberByName();
        if (mID != null) {
            ui.print("\nSkriv medlems id for at vælge: \neller bruge " + EXIT_TOKEN + " for at gå tilbage: ");
            int choice = parseUserInputToInt(mID);
            if (choice != EXIT_TOKEN) {
                Member member = storageController.searchMemberById(choice);
                if (member.getCompetition() != null) {
                    ui.println("Competition Result: ");
                    for (CompetitionResult result : member.getCompetition().getCompetitionResult()) {
                        ui.println(result.toString());
                    }
                    ui.println("\nTraining Result: ");
                    for (TrainingResult result : member.getCompetition().getTrainingResult()) {
                        ui.println(result.toString());
                    }
                } else {
                    ui.println("Det valgte medlem er ikke en kompetitiv svømmer.\n");
                }
            }
        }
    }

    private void disciplineResult() {
        ArrayList<SwimmingDiscipline> disciplines = SwimmingDiscipline.getDisciplinesAsList();
        ui.println("Vælg resultats kategori:");
        ui.println("1) Trænings resultater:");
        ui.println("2) Kompetitive resultater:");
        ui.println("\n" + EXIT_TOKEN + ") Tilbage");
        int choice = 0;
        choice = parseUserInputToInt(1, 2, EXIT_TOKEN);
        boolean traningResults = (choice == 1);
        if (choice != EXIT_TOKEN) {
            choice = 0;
            while (choice != EXIT_TOKEN) {
                ui.println("Top 5:");
                for (int i = 0; i < disciplines.size(); i++) {
                    ui.println(i + 1 + ") " + disciplines.get(i).getDisciplineName());
                }
                ui.println("\n" + EXIT_TOKEN + ") Tilbage");
                choice = parseUserInputToInt(1, 2, 3, 4, EXIT_TOKEN);

                if (choice != EXIT_TOKEN) {
                    if (traningResults) {
                        getTop5TrainingResults(disciplines.get(choice - 1));
                    } else {
                        getTop5CompetitionResults(disciplines.get(choice - 1));
                    }
                }
            }
        }
    }

    private void addResult() {
        int[] mID = findMemberByName();
        int choice = 0;
        ui.print("\nDu kan vælge et ID,\neller bruge \"" + EXIT_TOKEN + "\" for at gå tilbage: ");
        choice = parseUserInputToInt(mID);
        if (choice != EXIT_TOKEN) {
            Member member = storageController.searchMemberById(choice);
            if (member.getCompetition() != null) {
                ui.println("Vælg resultats kategori:");
                ui.println("1) Trænings resultat:");
                ui.println("2) Kompetitive resultat:");
                ui.println("\n" + EXIT_TOKEN + ") Tilbage");
                choice = parseUserInputToInt(1, 2, EXIT_TOKEN);
                boolean traningResults = (choice == 1);
                if (choice != EXIT_TOKEN) {
                    choice = showDisciplineMenu("Vælg disciplin:");
                    SwimmingDiscipline swimmingDiscipline = SwimmingDiscipline.getDisciplinesAsList().get(choice - 1);
                    if (choice != EXIT_TOKEN) {
                        LocalDate localDate = parseUserInputToLocalDate();
                        LocalTime localTime = parseUserInputToLocalTime();
                        int id;
                        if (traningResults) {
                            id = storage.getNextTrainingID();
                            TrainingResult res = new TrainingResult(swimmingDiscipline, localDate, localTime, id);
                            storage.addTrainingResult(res, member.getId());
                        } else {
                            id = storage.getNextCompetitionID();
                            ui.print("Stævne: ");
                            String event = ui.getUserInput();
                            ui.print("Placering: ");
                            int placement = parseUserInputToInt();
                            CompetitionResult res = new CompetitionResult(event, placement, swimmingDiscipline, localDate, localTime, id);
                            storage.addCompResult(res, member.getId());
                        }
                    }
                }
            } else {
                ui.println("Medlemmet du har valgt er ikke en kompetitive svømmer, prøv igen");
                addResult();
            }
        }
    }

    private int showDisciplineMenu(String header) {
        int choice;
        ArrayList<SwimmingDiscipline> disciplines = SwimmingDiscipline.getDisciplinesAsList();
        ui.println(header);
        for (int i = 0; i < disciplines.size(); i++) {
            ui.println(i + 1 + ") " + disciplines.get(i).getDisciplineName());
        }
        ui.println("\n" + EXIT_TOKEN + ") Tilbage");
        choice = parseUserInputToInt(1, 2, 3, 4, EXIT_TOKEN);
        return choice;
    }

    private void getTop5TrainingResults(SwimmingDiscipline discipline) {
        ArrayList<HashMap<String, String>> dbResult = storage.getTopFiveTrainingResultsByDiscipline(discipline.ordinal() + 1);
        for (HashMap<String, String> hashMap : dbResult) {
            Member member = extractMember(hashMap);
            TrainingResult trainingResult = extractTrainingResult(hashMap, discipline);
            ui.println(trainingResult.toString());
            ui.println(member.toString());
        }
    }

    private void getTop5CompetitionResults(SwimmingDiscipline discipline) {
        ArrayList<HashMap<String, String>> dbResult = storage.getTopFiveCompetitionResultsByDiscipline(discipline.ordinal() + 1);
        for (HashMap<String, String> hashMap : dbResult) {
            Member member = extractMember(hashMap);
            CompetitionResult competitionResult = extractCompetitionResult(hashMap, discipline);
            ui.println(competitionResult.toString());
            ui.println(member.toString());
        }
    }

    private Member extractMember(HashMap<String, String> hashMap) throws NumberFormatException {
        // Member
        String mName = hashMap.get("member_name");
        int mAge = Integer.parseInt(hashMap.get("age"));
        int mID = Integer.parseInt(hashMap.get("member_id"));
        boolean mActive = Boolean.parseBoolean(hashMap.get("active"));
        LocalDate mArrears = LocalDate.parse(hashMap.get("arrears"));
        Member member = new Member(mActive, mName, mAge, mID, mArrears, null);
        return member;
    }

    private TrainingResult extractTrainingResult(HashMap<String, String> hashMap, SwimmingDiscipline discipline) throws NumberFormatException {
        // Training Result
        LocalDate rDate = LocalDate.parse(hashMap.get("training_date"));
        LocalTime rTime = LocalTime.parse(hashMap.get("best_time"));
        int rID = Integer.parseInt(hashMap.get("training_id"));
        TrainingResult trainingResult = new TrainingResult(discipline, rDate, rTime, rID);
        return trainingResult;
    }

    private CompetitionResult extractCompetitionResult(HashMap<String, String> hashMap, SwimmingDiscipline discipline) throws NumberFormatException {
        // Competition Result
        String cEvent = hashMap.get("event_name");
        int cPlacement = Integer.parseInt(hashMap.get("placement"));
        LocalDate cDate = LocalDate.parse(hashMap.get("event_date"));
        LocalTime cTime = LocalTime.parse(hashMap.get("best_time"));
        int cID = Integer.parseInt(hashMap.get("competition_id"));
        CompetitionResult competitionResult = new CompetitionResult(cEvent, cPlacement, discipline, cDate, cTime, cID);
        return competitionResult;
    }

    private int[] displayCoaches() {
        ArrayList<Member> coaches = storageController.getMembers().get(StorageController.getCoachCat());
        int[] coachesID = new int[coaches.size()];
        for (int i = 0; i < coaches.size(); i++) {
            ui.println(coaches.get(i).toString());
            coachesID[i] = coaches.get(i).getId();
        }
        return coachesID;
    }

    private int[] findMemberByName() {
        int[] mID = null;
        boolean notDone = true;
        while (notDone) {
            ui.print("Søg efter navn: ");
            ArrayList<Member> resultList = storageController.getMembersByName(ui.getUserInput());
            if (resultList.isEmpty()) {
                ui.println("Din søgning gav ingen resultater.");
            } else {
                mID = new int[resultList.size() + 1];
                for (int i = 0; i < resultList.size(); i++) {
                    ui.println(resultList.get(i).toString());
                    mID[i] = resultList.get(i).getId();
                }
                mID[mID.length - 1] = EXIT_TOKEN;
            }
            if (yesNoOption("\nEr du færdig med din søgning?")) {
                notDone = false;
            }
        }
        return mID;
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
                ui.println("Brug et heltal.");
            }
        }
        return value;
    }

    private LocalTime parseUserInputToLocalTime() {
        boolean notDone = true;
        LocalTime localTime = null;
        while (notDone) {
            ui.print("Tid(hh:mm:ss): ");
            String date = ui.getUserInput();
            try {
                localTime = LocalTime.parse(date);
                notDone = false;
            } catch (DateTimeParseException e) {
                ui.println("Brug en valid tid.");
            }

        }
        return localTime;
    }

    private LocalDate parseUserInputToLocalDate() {
        boolean notDone = true;
        LocalDate localDate = null;
        while (notDone) {
            ui.print("Dato(yyyy-mm-dd): ");
            String date = ui.getUserInput();
            try {
                localDate = LocalDate.parse(date);
                notDone = false;
            } catch (DateTimeParseException e) {
                ui.println("Brug en valid dato.");
            }

        }
        return localDate;
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
        return storageController.getMembers();
    }
}
