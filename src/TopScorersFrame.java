import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TopScorersFrame extends JFrame {

    private JTable table;
    private PlayerService playerService;

    public TopScorersFrame() {
        playerService = new PlayerService();

        setTitle("Top 5 Scorers");
        setSize(450, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {"Username", "Wins", "Losses", "Draws", "Score"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        List<Player> topPlayers = playerService.getTopFiveScorers();
        for (Player p : topPlayers) {
            model.addRow(new Object[]{
                    p.getUsername(), p.getWins(), p.getLosses(), p.getDraws(), p.getScore()
            });
        }

        table = new JTable(model);
        add(new JScrollPane(table));
    }
}