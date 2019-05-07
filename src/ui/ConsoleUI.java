package ui;

import java.util.Scanner;

/**
 * @author Alexander
 * @author Benjamin
 * @author Mads
 * @author Tobias
 */
public class ConsoleUI implements UI {

    private Scanner scan = new Scanner(System.in);

    @Override
    public void println(String output) {
        System.out.println(output);
    }

    @Override
    public void printf(String output, Object... format) {
        System.out.printf(output, format);
    }

    @Override
    public String getUserInput() {
        return scan.nextLine();
    }

    @Override
    public void print(String output) {
        System.out.print(output);
    }

}
