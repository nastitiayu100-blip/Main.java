import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private Player currentPlayer;
    private PlayerService playerService;
    private GameLogic gameLogic;
    private JButton[] buttons;
    private JLabel lblStatus;
    private boolean gameOver;

    public GameFrame(Player player) {
        this.currentPlayer = player;
        this.playerService = new PlayerService();
        this.gameLogic = new GameLogic();
        this.gameOver = false;

        setTitle("Tic Tac Toe - " + player.getUsername());
        setSize(400, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        lblStatus = new JLabel("Giliranmu (X)", SwingConstants.CENTER);
        lblStatus.setFont(new Font("SansSerif", Font.BOLD, 14));
        add(lblStatus, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel(new GridLayout(3, 3, 5, 5));
        buttons = new JButton[9];

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("SansSerif", Font.BOLD, 32));
            final int index = i;
            buttons[i].addActionListener(e -> handlePlayerMove(index));
            boardPanel.add(buttons[i]);
        }

        add(boardPanel, BorderLayout.CENTER);
    }

    private void handlePlayerMove(int index) {
        if (gameOver) {
            return;
        }

        boolean valid = gameLogic.makeMove(index, 'X');
        if (!valid) {
            // Sel sudah terisi, abaikan klik
            return;
        }
        buttons[index].setText("X");

        if (gameLogic.checkWinner('X')) {
            lblStatus.setText("Kamu menang!");
            finishGame("WIN");
            return;
        }
        if (gameLogic.isDraw()) {
            lblStatus.setText("Seri!");
            finishGame("DRAW");
            return;
        }

        // Giliran komputer
        int computerIndex = gameLogic.computerMove();
        if (computerIndex != -1) {
            gameLogic.makeMove(computerIndex, 'O');
            buttons[computerIndex].setText("O");

            if (gameLogic.checkWinner('O')) {
                lblStatus.setText("Komputer menang!");
                finishGame("LOSE");
                return;
            }
            if (gameLogic.isDraw()) {
                lblStatus.setText("Seri!");
                finishGame("DRAW");
                return;
            }
        }

        lblStatus.setText("Giliranmu (X)");
    }

    private void finishGame(String result) {
        gameOver = true;
        for (JButton b : buttons) {
            b.setEnabled(false);
        }

        playerService.updateStatistics(currentPlayer, result);
        Player updatedPlayer = playerService.getPlayerById(currentPlayer.getId());

        JOptionPane.showMessageDialog(this, "Hasil permainan: " + result);

        MainMenuFrame menuFrame = new MainMenuFrame(
                updatedPlayer != null ? updatedPlayer : currentPlayer);
        menuFrame.setVisible(true);
        this.dispose();
    }
}