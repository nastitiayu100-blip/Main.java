import javax.swing.*;
import java.awt.*;

public class StatisticsFrame extends JFrame {

    public StatisticsFrame(Player player) {
        PlayerService playerService = new PlayerService();
        Player latest = playerService.getPlayerById(player.getId());
        if (latest == null) {
            latest = player;
        }

        setTitle("My Statistics - " + latest.getUsername());
        setSize(300, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Username:"));
        add(new JLabel(latest.getUsername()));

        add(new JLabel("Wins:"));
        add(new JLabel(String.valueOf(latest.getWins())));

        add(new JLabel("Losses:"));
        add(new JLabel(String.valueOf(latest.getLosses())));

        add(new JLabel("Draws:"));
        add(new JLabel(String.valueOf(latest.getDraws())));

        add(new JLabel("Score:"));
        add(new JLabel(String.valueOf(latest.getScore())));
    }
}