package core;

import java.io.File;
import java.sql.SQLException;
import storage.DBStorage;
import ui.ConsoleUI;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author rando
 */
public class Main {

    public static void main(String[] args) {
        try {
            DBStorage storage = new DBStorage();
            UIController controller = new UIController(new ConsoleUI(), storage);
            playDolphinSound();
            controller.startProgram();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void playDolphinSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Dolphins.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(1000);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
}
