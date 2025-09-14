import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        // Aplicar Look & Feel del sistema (o Nimbus)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            // si falla, se ignora para seguir
        }

        SwingUtilities.invokeLater(() -> {
            WelcomeFrame frame = new WelcomeFrame();
            frame.setAppIcon("/icon.png"); // coloca icon.png en resources o en el classpath
            frame.setVisible(true);
        });
    }
}