import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

//Liong Gen Bing,Leow Yen Siang,Ngiu Jin Tian Ci
public class Move implements ActionListener{
    Board board;
    Game game;
    public Move(Board board, Game game){
        this.board=board;
        this.game=game;
    }
    public void actionPerformed(ActionEvent e){ //listen to buttons everytime user clicks a button
        JButton button = (JButton)e.getSource() ;
        int temp = Integer.parseInt(button.getName()) ;
        int i=temp/10 ,j = temp%10;
        //when user havent clicked a piece.
        if (board.getClickPiece()==0 && board.getPiece(i,j)!=null && board.getPiece(i,j).getPlayer()==board.getTurn()){
            board.setAvailablePositions(board.getPiece(i,j).captureFreeMoves(board.getPieces(), new Position(i,j)));
            for (int c=0; c<board.getAvailablePositions().size(); c++){
                game.setColorView(board.getAvailablePositions().get(c).getX(), board.getAvailablePositions().get(c).getY(), Color.yellow);
            }
            board.setClickPiece(1);
        }//when user clicked a piece but change
        else if (board.getClickPiece()==1 && board.getPiece(i,j)!=null && board.getPiece(i,j).getPlayer()==board.getTurn()){
            for (int c=0; c<board.getAvailablePositions().size(); c++){ 
                game.setColorView(board.getAvailablePositions().get(c).getX(), board.getAvailablePositions().get(c).getY(), Color.white);
            }//reset the color to white
            board.clearAvailablePositions();//clear the old calculated moves
            board.setAvailablePositions(board.getPiece(i,j).captureFreeMoves(board.getPieces(), new Position(i,j))); //calculate the new available moves
            for (int c=0; c<board.getAvailablePositions().size(); c++){
                game.setColorView(board.getAvailablePositions().get(c).getX(), board.getAvailablePositions().get(c).getY(), Color.yellow);
            }//set the colors again
        }
        else{
            //if selected piece is inside the 
            if (board.getPiece(board.getSelectedPosition().getX(),board.getSelectedPosition().getY()).validMove(board.getAvailablePositions(), i, j)==true){
                boolean result = board.checkResult(i,j);    //check if the chief has been captured in this move
                board.setPiece(i,j,board.getSelectedPosition().getX(),board.getSelectedPosition().getY()); //set the piece to new location
                board.setPieceNull(board.getSelectedPosition().getX(),board.getSelectedPosition().getY()); //set current location to null
                if ((i==0 || i==6) && (board.getPiece(i,j).getType()=="Advancer" || board.getPiece(i,j).getType()=="Trident") 
                && board.getSelectedPosition().getX()!=0 && board.getSelectedPosition().getX()!=6){
                    board.getPiece(i,j).changeDirection();       //if the piece reach boundary,reset image as it turn around
                    board.getPiece(i,j).changeCaptureDirection(); //the piece has to move downward after turn around
                }
                boolean checkMate=false;
                if (result!=true) //check if checkmate
                    checkMate = board.checkMate(i,j);
                board.transferPiece(); //transfer the type between Tercel and Excel
                board.flipBoard();      //flip the board
                game.initializePiece(); //initialize every piece to new location after flipboard
                board.setClickPiece(0); //not selecting an piece
                if (board.getTurn()==1)
                    board.setTurn(0);
                else
                    board.setTurn(1);
                if (result==true)
                    game.displayMessage("Player " + (board.getTurn()+1) + " Win!\nPlease create new game");
                else if (checkMate==true)
                    game.displayMessage("Check Mate!");
            }
        }
        board.setSelectedPosition(new Position(i,j));
        game.setTurnLabelView();
    }
    
}