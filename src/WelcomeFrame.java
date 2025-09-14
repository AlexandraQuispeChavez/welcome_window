import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class WelcomeFrame extends JFrame {
    private JButton showGreetingBtn;

    public WelcomeFrame() {
        setTitle("Bienvenido");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(480, 250);
        setLocationRelativeTo(null);
        setAppIcon("/icono.png");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(245, 245, 245));

        showGreetingBtn = new JButton("Mostrar Saludo");
        showGreetingBtn.setPreferredSize(new Dimension(200, 60));
        showGreetingBtn.setFocusPainted(false);
        showGreetingBtn.setBackground(new Color(60, 140, 250));
        showGreetingBtn.setForeground(new Color(15, 30, 80));
        showGreetingBtn.setFont(new Font("Arial", Font.BOLD, 18));

        showGreetingBtn.addActionListener(e -> {
            String name = GreetingService.askName(this);

            if (name == null || name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        GreetingService.getMessage("nameEmpty"),
                        "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                String message = GreetingService.buildGreeting(name);
                showCustomGreetingDialog(this, message);
            }
        });

        mainPanel.add(showGreetingBtn);
        this.add(mainPanel);
    }

    private void showCustomGreetingDialog(Component parent, String message) {
        JDialog customDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(parent), "Saludo Especial", true);
        customDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        customDialog.setSize(450, 220);
        customDialog.setLocationRelativeTo(parent);

        JPanel dialogPanel = new JPanel(new BorderLayout(20, 20));
        dialogPanel.setBackground(new Color(230, 245, 255)); // **Celeste cielo**
        dialogPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 20, 25));

        ImageIcon customIcon = null;
        try {
            URL iconUrl = getClass().getResource("/icono.png");
            if (iconUrl != null) {
                Image img = Toolkit.getDefaultToolkit().getImage(iconUrl);
                Image scaledImg = img.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                customIcon = new ImageIcon(scaledImg);
            }
        } catch (Exception ex) {
            System.err.println("Error cargando icono para el diálogo: " + ex.getMessage());
        }

        JLabel iconLabel = new JLabel(customIcon);
        if (customIcon == null) {
            iconLabel.setText("✨");
            iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 50));
        }
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel messageLabel = new JLabel("<html><center>" + message + "</center></html>");
        messageLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        messageLabel.setForeground(new Color(30, 30, 30));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel topPanel = new JPanel(new BorderLayout(15, 0));
        topPanel.setOpaque(false);
        topPanel.add(iconLabel, BorderLayout.WEST);
        topPanel.add(messageLabel, BorderLayout.CENTER);

        JButton okButton = new JButton("Entendido!");
        okButton.setPreferredSize(new Dimension(120, 45));
        okButton.setFont(new Font("Arial", Font.BOLD, 16));
        okButton.setBackground(new Color(60, 140, 250));
        okButton.setForeground(new Color(15, 30, 80)); // **Azul noche**
        okButton.setFocusPainted(false);
        okButton.setBorder(BorderFactory.createLineBorder(new Color(40, 120, 230), 2));
        okButton.addActionListener(e -> customDialog.dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.add(okButton);

        dialogPanel.add(topPanel, BorderLayout.CENTER);
        dialogPanel.add(buttonPanel, BorderLayout.SOUTH);

        customDialog.add(dialogPanel);
        customDialog.setResizable(false);
        customDialog.setVisible(true);
    }

    public void setAppIcon(String resourcePath) {
        try {
            URL imgUrl = getClass().getResource(resourcePath);
            if (imgUrl != null) {
                Image img = Toolkit.getDefaultToolkit().getImage(imgUrl);
                setIconImage(img);
            } else {
                System.err.println("Icono no encontrado en: " + resourcePath);
            }
        } catch (Exception ex) {
            System.err.println("Error cargando icono: " + ex.getMessage());
        }
    }
}