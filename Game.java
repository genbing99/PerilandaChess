import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;

//Liong Gen Bing
public class Game
{
    private static Game singleInstance=null;
    private JPanel toolbutton = new JPanel();
    static JToolBar tb = new JToolBar();
    private static JButton[][] boardSquares = new JButton[7][7];
    private JPanel boardP = new JPanel();
    Board board = Board.Singleton();
    private JLabel turnLabel = new JLabel("Turn Now: Player " + (board.getTurn()+1));
    public Game(){
        JButton loadGame = new JButton("Load Game");
        loadGame.setName("Load Game");
        loadGame.addActionListener(new GameController(this));
        JButton newGame = new JButton("New Game");
        newGame.setName("New Game");
        newGame.addActionListener(new GameController(this));
        JButton saveGame = new JButton("Save Game");
        saveGame.setName("Save Game");
        saveGame.addActionListener(new GameController(this));
        toolbutton.add(loadGame);
        toolbutton.add(newGame);
        toolbutton.add(saveGame);
        toolbutton.add(turnLabel);
        tb.add(toolbutton);
        tb.setFloatable(false);
        boardP = new JPanel(new GridLayout(0, 7));
        boardP.setBorder(new LineBorder(Color.BLACK));
        Insets buttonMargin = new Insets(0,0,0,0);
        for (int i=0; i<7; i++)
            for (int j=0; j<7; j++){
                boardSquares[i][j]=new JButton();
                boardSquares[i][j].setName(Integer.toString(i) + Integer.toString(j)) ;
                boardSquares[i][j].addActionListener(new Move(board, this)) ;
            }
        initializePiece();
        for (int i=0; i<7; i++)
            for (int j=0; j<7; j++)
                boardP.add(boardSquares[i][j]);
    }
    public static Game Singleton()
    {
        if (singleInstance==null)
            singleInstance=new Game();
        return singleInstance;
    }
    
    public void setTurnLabelView(){
        turnLabel.setText("Turn Now: Player " + (board.getTurn()+1));
    }
    public void initializePiece()//draw the board everytime user makes a move
    {
        for (int i=0; i<7; i++)
            for (int j=0; j<7; j++){
                if (board.getPiece(i,j)!=null){
                    ImageIcon img = new ImageIcon(board.getPiece(i,j).getImage());
                    Image newImg = img.getImage().getScaledInstance(100,100,java.awt.Image.SCALE_SMOOTH );
                    img=new ImageIcon(newImg);
                    boardSquares[i][j].setIcon(img);
                }
                else
                    boardSquares[i][j].setIcon(null);
                setColorView(i, j, Color.white);
                boardSquares[i][j].setSize(100,100);
            }
    }
    public void setColorView(int x, int y, Color color){
        boardSquares[x][y].setBackground(color);
    }
    public final JComponent getChessBoard(){
        return boardP;
    }
    public void displayMessage(String msg){
        JOptionPane.showMessageDialog(boardP, msg);
    }
    public static void main(String[] args){
        Game game = Game.Singleton();
        Board board = Board.Singleton();
        JFrame frame = new JFrame("Perilanda Chess");
        frame.setSize(700, 700);
        frame.setLayout(new BorderLayout());
        frame.add(tb, BorderLayout.NORTH);
        frame.add(game.getChessBoard());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}