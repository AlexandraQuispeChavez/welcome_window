import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class GreetingService {

    public static String askName(Component parent) {
        String prompt = getMessage("promptName");
        String name = JOptionPane.showInputDialog(parent, prompt);
        return name;
    }

    public static String buildGreeting(String name) {
        if (name == null || name.isEmpty()) {
            return getMessage("nameEmpty");
        }
        Locale loc = Locale.getDefault();
        if (loc.getLanguage().equals(new Locale("es").getLanguage())) {
            return "¡Hola, " + name + "! ¡Buen trabajo!";
        } else {
            return "Hello, " + name + "! Good job!";
        }
    }

    public static String getMessage(String key) {
        Locale loc = Locale.getDefault();
        boolean es = loc.getLanguage().equals(new Locale("es").getLanguage());
        switch (key) {
            case "promptName": return es ? "Ingrese su nombre:" : "Enter your name:";
            case "nameEmpty": return es ? "El nombre no puede estar vacío." : "Name cannot be empty.";
            case "titleWarning": return es ? "Aviso" : "Warning";
            default: return "";
        }
    }
}