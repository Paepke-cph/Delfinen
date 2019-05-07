package ui;

/**
 * @author Alexander
 * @author Benjamin
 * @author Mads
 * @author Tobias
 */
public interface UI {

    public void println(String output);

    public void print(String output);

    public void printf(String output, Object... format);

    public String getUserInput();
}
