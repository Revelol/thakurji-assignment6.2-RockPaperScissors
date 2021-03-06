import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class RockPaperScissorsFrame extends JFrame implements Strategy {
    JPanel main, top, result, stats;
    JLabel lUserWins, lComputerWins, lDraws, lComputerMove,lGametotal;
    JButton quitBtn,rockBtn, paperBtn, scissorsBtn;
    JTextArea results;
    JScrollPane scrollPane;
    int iUserWins, iComputerWins, iDraws, playerChoice, computerChoice, rCnt, pCnt, sCnt,ilastUsed, gameCnt, cheatCnt;
    String strategy;


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
        rockBtn = new JButton();
        ImageIcon temp = new ImageIcon(this.getClass().getResource("rocksign.jpg"));
        ImageIcon rocksign = new ImageIcon(temp.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));
        rockBtn.setIcon(rocksign);

        rockBtn.addActionListener((ActionEvent ae) -> {
            playerChoice = 0;
            rCnt++ ;
            determineMove();
        });

        top.add(rockBtn);
        //paper button
        paperBtn = new JButton();
        ImageIcon temp1 = new ImageIcon(this.getClass().getResource("handsign.jpg"));
        ImageIcon handsign = new ImageIcon(temp1.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));
        paperBtn.setIcon(handsign);

        paperBtn.addActionListener((ActionEvent ae) -> {
            playerChoice =1;
            pCnt++;
            determineMove();
        });

        top.add(paperBtn);
        //scissors  button
        scissorsBtn = new JButton();
        ImageIcon temp2 = new ImageIcon(this.getClass().getResource("scissorssign.jpg"));
        ImageIcon scissorssign = new ImageIcon(temp2.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));
        scissorsBtn.setIcon(scissorssign);

        scissorsBtn.addActionListener((ActionEvent ae) -> {
            playerChoice =2;
            sCnt++;
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

        top.setBorder(BorderFactory.createLineBorder(Color.green));

    }
    private void createResultsPanel()
    {
        results = new JTextArea();
        scrollPane = new JScrollPane(results);
    }
    private void createStatsPanel(){
        stats = new JPanel();
        stats.setLayout(new GridLayout(3,4));
        JLabel userWin = new JLabel("User Wins");
        stats.add(userWin);
        JLabel comprWin = new JLabel("Computer Wins");
        stats.add(comprWin);
        JLabel draws = new JLabel("Draws");
        stats.add(draws);
        JLabel total = new JLabel("Total Games");
        stats.add(total);
        lUserWins = new JLabel(""+iUserWins);
        stats.add(lUserWins);
        lComputerWins = new JLabel(""+iComputerWins);
        stats.add(lComputerWins);
        lDraws = new JLabel(""+iDraws);
        stats.add(lDraws);
        lGametotal = new JLabel(""+gameCnt);
        stats.add(lGametotal);
        JLabel label =  new JLabel("Computer's Move: ");
        stats.add(label);
        lComputerMove = new JLabel();
        stats.add(lComputerMove);
    }
    public void determineMove(){
        determineStrategy();
        determineResult();
        getComputerMoveInText();
    }
    private void determineStrategy(){
        String [] strategies= {"Least Used", "Most Used", "Last Used", "Random", "Cheat"};
        Random random = new Random();
        int i = random.nextInt(strategies.length);
        strategy = strategies[i];
        determineComputerMove();
    }

    private void determineComputerMove() {
        if (gameCnt == 0 && strategy.equals("Last Used")) {
            determineStrategy();
        } else if (gameCnt !=0 &&  strategy.equals("Cheat") && (double)(cheatCnt/gameCnt) > 0.1){
            System.out.println("cheat-fraction " + (double)(cheatCnt/gameCnt));
            determineStrategy();
        } else {
            if (strategy.equals("Random")) {
                determineRandomMove();
            } else if (strategy.equals("Least Used")) {
                determineLeastUsed();
            } else if (strategy.equals("Most Used")) {
                determineMostUsed();
            } else if (strategy.equals("Last Used")) {
                determineLastUsed();
            } else if (strategy.equals("Cheat")) {
                determineCheat();
                cheatCnt++;
            }
        }

    }
    private void determineRandomMove(){
        Random r = new Random();
        computerChoice = r.nextInt(3);
    }
    private void determineLeastUsed(){
        if(rCnt < pCnt && rCnt < sCnt){
            computerChoice = 1;
        }else if(pCnt < rCnt && pCnt < sCnt){
            computerChoice = 2;
        }else{
            computerChoice=0;
        }

    }
    private void determineMostUsed(){
        if(rCnt > pCnt && rCnt > sCnt){
            computerChoice = 1;
        }else if(pCnt > rCnt && pCnt > sCnt){
            computerChoice = 2;
        }else{
            computerChoice=0;
        }

    }
    private void determineLastUsed(){
        computerChoice = ilastUsed;
    }
    private void determineCheat(){
        if(playerChoice == 0){
            computerChoice = 1;
        }else if(playerChoice == 1){
            computerChoice = 2;
        }else{
            computerChoice = 0;
        }

    }
    private void determineResult(){
        if(playerChoice == computerChoice){
            iDraws++;
            results.append("It is a Tie ("+strategy+")\n");
            lDraws.setText(""+iDraws);
        }
        else if (playerChoice==0 && computerChoice==2) {
            iUserWins++;
            results.append("Rock breaks scissors (Player wins " +strategy+")\n" );
            lUserWins.setText(""+iUserWins);
        } else if (playerChoice==1 && computerChoice==0) {
            iUserWins++;
            results.append("Paper covers rock (Player wins " +strategy+")\n");
            lUserWins.setText(""+iUserWins);
        } else if (playerChoice ==2 && computerChoice == 1) {
            iUserWins++;
            results.append("Scissors cuts paper (Player wins " +strategy+")\n");
            lUserWins.setText(""+iUserWins);
        } else if (playerChoice==2 && computerChoice==0) {
            iComputerWins++;
            results.append("Rock breaks scissors (Computer wins " +strategy+")\n");
            lComputerWins.setText(""+iComputerWins);
        } else if (playerChoice==0 && computerChoice==1) {
            iComputerWins++;
            results.append("Paper covers rock (Computer wins " +strategy+")\n");
            lComputerWins.setText(""+iComputerWins);
        } else if (playerChoice ==1 && computerChoice == 2) {
            iComputerWins++;
            results.append("Scissors cuts paper (Computer wins " +strategy+")\n");
            lComputerWins.setText(""+iComputerWins);
        }
        ilastUsed = playerChoice;
        gameCnt++;
        lGametotal.setText(""+gameCnt);
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
