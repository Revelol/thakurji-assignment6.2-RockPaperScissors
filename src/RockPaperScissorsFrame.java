import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class RockPaperScissorsFrame {
    JPanel main, top, result, stats;
    JLabel lUserWins, lComputerWins, lDraws;
    JButton rockBtn, paperBtn, scissorsBtn, quitBtn;
    JTextArea results;
    JScrollPane scrollPane;
    int iUserWins, iComputerWins, iDraws;

    public RockPaperScissorsFrame()
    {
        super("Fortune Teller");
        // configure the GUI
        main = new JPanel();

        createTopPanel();
        createResultsPanel();
        createStatsPanel();


        main.setLayout(new BorderLayout());
        main.add(top,BorderLayout.NORTH);
        main.add(scrollPane,BorderLayout.CENTER);
        main.add(bottom,BorderLayout.SOUTH);

        // And add Main to the JFrame
        add(main);

        generateFortune();
        setSize(400, 400);
        //frame.pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }
    private void createTopPanel()
    {
        // Top panel

        top = new JPanel();
        //rock button
        rockBtn = new JButton();
        ImageIcon temp = new ImageIcon(this.getClass().getResource("rocksign.jpg"));
        ImageIcon rocksign = new ImageIcon(temp.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH));


        rockBtn.setIcon(rocksign);
        rockBtn.setText("Rock!");
        rockBtn.setFont(new Font("Helvetica", Font.PLAIN, 36));
        rockBtn.setForeground(Color.orange);
        top.add(rockBtn);

    }
    private void createResultsPanel()
    {
        results = new JTextArea();
        this.scrollPane = new JScrollPane(results);
    }
    private void createStatsPanel(){
        stats = new JPanel();
        stats.setLayout(new GridLayout(2,3));
        JLabel userWin = new JLabel("User Wins");
        stats.add(userWin);
        JLabel comprWin = new JLabel("Computer Wins");
        stats.add(comprWin);
        JLabel draws = new JLabel("Draws");
        stats.add(draws);
        lUserWins = new JLabel(""+iUserWins);
        stats.add(lUserWins);
        lComputerWins = new JLabel(""+iComputerWins);
        stats.add(lComputerWins);
        lDraws = new JLabel(""+iDraws);
        stats.add(lDraws);




    }

}
