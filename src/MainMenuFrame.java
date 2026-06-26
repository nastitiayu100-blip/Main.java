import javax.swing.*;
import java.awt.*;

public class MainMenuFrame extends JFrame {

    private Player currentPlayer;
    private JButton btnStartGame;
    private JButton btnStatistics;
    private JButton btnTopScorers;
    private JButton btnExit;

    public MainMenuFrame(Player player) {
        this.currentPlayer = player;

        setTitle("Main Menu - Selamat datang " + player.getUsername());
        setSize(350, 280);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1, 10, 10));

        JLabel lblWelcome = new JLabel("Halo, " + player.getUsername() + "!", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("SansSerif", Font.BOLD, 16));

        btnStartGame = new JButton("Start Game");
        btnStatistics = new JButton("View My Statistics");
        btnTopScorers = new JButton("View Top 5 Scorers");
        btnExit = new JButton("Exit");

        add(lblWelcome);
        add(btnStartGame);
        add(btnStatistics);
        add(btnTopScorers);
        add(btnExit);

        btnStartGame.addActionListener(e -> {
            GameFrame gameFrame = new GameFrame(currentPlayer);
            gameFrame.setVisible(true);
            this.dispose();
        });

        btnStatistics.addActionListener(e -> {
            StatisticsFrame statisticsFrame = new StatisticsFrame(currentPlayer);
            statisticsFrame.setVisible(true);
        });

        btnTopScorers.addActionListener(e -> {
            TopScorersFrame topFrame = new TopScorersFrame();
            topFrame.setVisible(true);
        });

        btnExit.addActionListener(e -> System.exit(0));
    }
}