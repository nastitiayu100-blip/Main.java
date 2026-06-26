import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private PlayerService playerService;

    public LoginFrame() {
        playerService = new PlayerService();

        setTitle("Login - Tic Tac Toe");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblUsername = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblUsername, gbc);

        txtUsername = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtUsername, gbc);

        JLabel lblPassword = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblPassword, gbc);

        txtPassword = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtPassword, gbc);

        btnLogin = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnLogin, gbc);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText().trim();
                String password = new String(txtPassword.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginFrame.this,
                            "Username dan password tidak boleh kosong!",
                            "Login Gagal", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Player player = playerService.login(username, password);

                if (player != null) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Login berhasil!");
                    MainMenuFrame menuFrame = new MainMenuFrame(player);
                    menuFrame.setVisible(true);
                    LoginFrame.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this,
                            "Username atau password salah!",
                            "Login Gagal", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}