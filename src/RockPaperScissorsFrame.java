import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class RockPaperScissorsFrame extends JFrame implements Strategy {
    JPanel main, top, result, stats;
    JLabel lUserWins, lComputerWins, lDraws, lComputerMove;
    GameButton rockBtn, paperBtn, scissorsBtn;
    JButton quitBtn;
    JTextArea results;
    JScrollPane scrollPane;
    int iUserWins, iComputerWins, iDraws, playerChoice, computerChoice;


    public RockPaperScissorsFrame()
    {
        super("Rock Paper Scissors Game");
        // configure the GUI
        main = new JPanel();

        createTopPanel();
        createResultsPanel();
        createStatsPanel();


        main.setLayout(new BorderLayout());
        main.add(top,BorderLayout.NORTH);
        main.add(scrollPane,BorderLayout.CENTER);
        main.add(stats,BorderLayout.SOUTH);

        // And add Main to the JFrame
        add(main);


        setSize(400, 400);
        //frame.pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }
    private void createTopPanel()
    {
        // Top panel

        top = new JPanel();
        //rock button
        rockBtn = new GameButton(0, "Rock");
        ImageIcon temp = new ImageIcon(this.getClass().getResource("rocksign.jpg"));
        ImageIcon rocksign = new ImageIcon(temp.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));
        rockBtn.setIcon(rocksign);

        rockBtn.addActionListener((ActionEvent ae) -> {
            playerChoice = 0;
            determineMove();
        });

        top.add(rockBtn);
        //paper button
        paperBtn = new GameButton(1,"Paper");
        ImageIcon temp1 = new ImageIcon(this.getClass().getResource("handsign.jpg"));
        ImageIcon handsign = new ImageIcon(temp1.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));
        paperBtn.setIcon(handsign);

        paperBtn.addActionListener((ActionEvent ae) -> {
            playerChoice =1;
            determineMove();
        });

        top.add(paperBtn);
        //scissors  button
        scissorsBtn = new GameButton(2,"Scissors");
        ImageIcon temp2 = new ImageIcon(this.getClass().getResource("scissorssign.jpg"));
        ImageIcon scissorssign = new ImageIcon(temp2.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));
        scissorsBtn.setIcon(scissorssign);

        scissorsBtn.addActionListener((ActionEvent ae) -> {
            playerChoice =2;
            determineMove();
        });

        top.add(scissorsBtn);
        quitBtn = new JButton("Quit");
        quitBtn.addActionListener((ActionEvent ae) -> {
            System.exit(0);
        });
        quitBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        quitBtn.setForeground(Color.red);
        top.add(quitBtn);

    }
    private void createResultsPanel()
    {
        results = new JTextArea();
        scrollPane = new JScrollPane(results);
    }
    private void createStatsPanel(){
        stats = new JPanel();
        stats.setLayout(new GridLayout(3,3));
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
        JLabel label =  new JLabel("Computer's Move: ");
        stats.add(label);
        lComputerMove = new JLabel();
        stats.add(lComputerMove);
    }
    public void determineMove(){
        determineComputerMove();
        determineResult();
    }

    private void determineComputerMove() {
        Random r = new Random();
        computerChoice = r.nextInt(3);

    }
    private void determineResult(){
        if(playerChoice == computerChoice){
            iDraws++;
            results.append("It is a Tie\n");
            lDraws.setText(""+iDraws);
        }
        else if (playerChoice==0 && computerChoice==2) {
            iUserWins++;
            results.append("Rock breaks scissors (Player wins)\n");
            lUserWins.setText(""+iUserWins);
        } else if (playerChoice==1 && computerChoice==0) {
            iUserWins++;
            results.append("Paper covers rock (Player wins)\n");
            lUserWins.setText(""+iUserWins);
        } else if (playerChoice ==2 && computerChoice == 1) {
            iUserWins++;
            results.append("Scissors cuts paper (Player wins)\n");
            lUserWins.setText(""+iUserWins);
        } else if (playerChoice==2 && computerChoice==0) {
            iComputerWins++;
            results.append("Rock breaks scissors (Computer wins)\n");
            lComputerWins.setText(""+iComputerWins);
        } else if (playerChoice==0 && computerChoice==1) {
            iComputerWins++;
            results.append("Paper covers rock (Computer wins)\n");
            lComputerWins.setText(""+iComputerWins);
        } else if (playerChoice ==1 && computerChoice == 2) {
            iComputerWins++;
            results.append("Scissors cuts paper (Computer wins)\n");
            lComputerWins.setText(""+iComputerWins);
        }
    }

    private void getComputerMoveInText() {
        if (computerChoice ==0){
            lComputerMove.setText("Rock");
        }else if(computerChoice ==1){
            lComputerMove.setText("Paper");
        }else{
            lComputerMove.setText("Scissors");
        }
    }
}
