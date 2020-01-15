import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//Liong Gen Bing
public class GameController implements ActionListener
{
    Game game;
    public GameController(Game game){
        this.game=game;
    }
    public void actionPerformed(ActionEvent e){
        JButton button = (JButton) e.getSource() ;
        if (button.getName()=="New Game"){
            newGame();
        }
        if (button.getName()=="Save Game"){
            saveGame();
        }
        if (button.getName()=="Load Game"){
            loadGame();
        }
    }
    public void newGame(){ //used when user clicks new game
        Board board = Board.Singleton();
        board.resetBoard();
        board.createChessBoard();
        game.initializePiece();
        board.setTurn(0);
        game.setTurnLabelView();
    }
    public void saveGame(){ //used when user clicks save game
        Board board = Board.Singleton();
        try{
            File deleteFile= new File ("boardGame.txt");
            if (deleteFile.exists())
                deleteFile.delete();
            BufferedWriter fileWrite = new BufferedWriter(new FileWriter("boardGame.txt", true));
            fileWrite.append(Integer.toString(board.getTurn()+1) + ' ' + Board.movedTime + '\n');
            for(int i=0; i<7; i++){
                for (int j=0; j<7; j++)
                    if (board.getPiece(i,j)!=null)
                        fileWrite.append(board.getPiece(i,j).getType() + ' ' + board.getPiece(i,j).getPlayer() 
                         + ' ' + board.getPiece(i,j).getDirection() + ' ');
                    else
                        fileWrite.append("null" + ' ');
                fileWrite.append('\n');
            }
            fileWrite.close();
            game.displayMessage("Save Game Successfully!");
        }
        catch(IOException msg){
            System.out.println("Error in saving game");
        }
    }
    public void loadGame(){ //used when user clicks load game
        Board board = Board.Singleton();
        board.resetBoard();
        board.Singleton();
        try{
            Scanner scan = new Scanner(new File("boardGame.txt"));
            int turn = scan.nextInt();
            turn=turn-1;
            int boardMovedTime=scan.nextInt();
            Board.movedTime=boardMovedTime;
            int i=0;
            int j=0;
            while (scan.hasNext()){
                String className=scan.next();
                if (className.length()>4){
                    int player=scan.nextInt();
                    int direction=scan.nextInt();
                    board.loadPiece(className, i, j, player, direction, turn);
                }
                j++;
                if (j==7){
                    i++;
                    j=0;
                }
                if (i==7)
                    break;
            }
            scan.close();
            game.initializePiece();
            board.setTurn(turn);
            game.setTurnLabelView();
            game.displayMessage("Load Game Successfully!");
        }
        catch (IOException i){
            System.out.println("Error in loading game");
        }
    }

}
