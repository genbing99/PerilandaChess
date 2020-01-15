import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;

//Liong Gen Bing
public class Board{
    private static Board singleInstance=null;
    public static int movedTime=0; //used to know 3 moves
    public int clickPiece=0; //know source of piece
    private Piece[][] pieces = new Piece[7][7]; //store individual piece
    private ArrayList<Position> availablePositions = new ArrayList<Position>() ; //store available Positions
    private Position selectedPosition = new Position(); //used to store selected piece at actionlistener
    private int turn=0;
    public Board(){}
    public void setTurn(int turn){
        this.turn=turn;
    }
    public int getTurn(){
        return turn;
    }
    public int getClickPiece(){
        return clickPiece;
    }
    public Piece getPiece(int i, int j){
        return pieces[i][j];
    }
    public Piece[][] getPieces(){
        return pieces;
    }
    public ArrayList<Position> getAvailablePositions(){
        return availablePositions;
    }
    public void setAvailablePositions(ArrayList<Position> availablePositions){
        this.availablePositions=availablePositions;
    }
    public void setClickPiece(int clickPiece){
        this.clickPiece=clickPiece;
    }
    public void clearAvailablePositions(){
        availablePositions.clear();
    }
    public Position getSelectedPosition(){
        return selectedPosition;
    }
    public void setPiece(int oriX, int oriY, int newX, int newY){
        pieces[oriX][oriY]=pieces[newX][newY];
    }
    public void setPieceNull(int x, int y){
        pieces[x][y]=null;
    }
    public void setSelectedPosition(Position position){
        selectedPosition=position;
    }
    public void resetBoard(){ //used to reset when new game
        pieces=new Piece[7][7];
        movedTime=0;
        clickPiece=0; 
        selectedPosition = new Position();
        availablePositions.clear();
    }
    public static Board Singleton()
    {
        if (singleInstance==null)
            singleInstance=new Board();
        return singleInstance;
    }
    
    public void loadPiece(String className, int i, int j, int player, int direction, int turn) //when loading fro 
    {
        if (className.equals("Tercel"))
            pieces[i][j]=new Tercel(i,j,player);
        else if (className.equals("Excel"))
            pieces[i][j]=new Excel(i, j, player);
        else if (className.equals("Trident")){
            pieces[i][j]=new Trident(i,j,player);
            if (turn==1)
                pieces[i][j].changeDirection();
            if (direction!=(turn+1)){
                pieces[i][j].changeDirection();
                pieces[i][j].changeCaptureDirection();
            }
        }
        else if (className.equals("Chief"))
            pieces[i][j]=new Chief(i, j, player);
        else if (className.equals("Advancer")){
            pieces[i][j]=new Advancer(i,j,player);
            if (turn==1)
                pieces[i][j].changeDirection();
            if (direction!=(turn+1)){
                pieces[i][j].changeDirection();
                pieces[i][j].changeCaptureDirection();
            }
        }
            
    }
    
    public void createChessBoard()
    {
        pieces[0][0]=new Tercel(0,0,1);
        pieces[0][1]=new Excel(0,1,1);
        pieces[0][2]=new Trident(0,2,1);
        pieces[0][3]=new Chief(0,3,1);
        pieces[0][4]=new Trident(0,4,1);
        pieces[0][5]=new Excel(0,5,1);
        pieces[0][6]=new Tercel(0,6,1);
        for (int i=0; i<7; i++){
            pieces[1][i]=new Advancer(1,i,1);
            pieces[5][i]=new Advancer(5,i,0);
        }
        pieces[6][0]=new Tercel(6,0,0);
        pieces[6][1]=new Excel(6,1,0);
        pieces[6][2]=new Trident(6,2,0);
        pieces[6][3]=new Chief(6,3,0);
        pieces[6][4]=new Trident(6,4,0);
        pieces[6][5]=new Excel(6,5,0);
        pieces[6][6]=new Tercel(6,6,0);
        for (int i=2; i<5; i++)
            for (int j=0; j<7; j++)
                pieces[i][j]=null;
    }
    
    public boolean checkMate(int i,  int j){ //checking checkMate every turn
        availablePositions.clear();
        availablePositions = pieces[i][j].captureFreeMoves(pieces, new Position(i,j));
        for (int c=0;c<availablePositions.size();c++){
            if (pieces[availablePositions.get(c).getX()][availablePositions.get(c).getY()] != null)
                if (pieces[availablePositions.get(c).getX()][availablePositions.get(c).getY()].getType()=="Chief")
                {
                    availablePositions.clear();
                    return true;
                }
        }
        availablePositions.clear();
        return false;
    }
    public boolean checkResult(int i, int j){ 
        if (pieces[i][j]!=null)
            if (pieces[i][j].getType()=="Chief")
                return true;
        return false;
    }
    public void transferPiece(){//change from excel to tercel vice versa
        movedTime++;
        if (movedTime==5 && turn==0){
            for (int c=0; c<7; c++)
                for (int c1=0; c1<7; c1++)
                    if (pieces[c][c1]!=null)
                        if (pieces[c][c1].getType()=="Excel" && pieces[c][c1].getPlayer()==0)
                            pieces[c][c1]=new Tercel(c, c1, 0);
                        else if (pieces[c][c1].getType()=="Tercel" && pieces[c][c1].getPlayer()==0)
                            pieces[c][c1]=new Excel(c, c1, 0);
        }
        else if (movedTime==6 && turn==1){
            for (int c=0; c<7; c++)
                for (int c1=0; c1<7; c1++)
                    if (pieces[c][c1]!=null)
                        if (pieces[c][c1].getType()=="Excel" && pieces[c][c1].getPlayer()==1)
                            pieces[c][c1]=new Tercel(c, c1, 1);
                        else if (pieces[c][c1].getType()=="Tercel" && pieces[c][c1].getPlayer()==1)
                            pieces[c][c1]=new Excel(c, c1, 1);
        movedTime=0;
        }
    }
    public void flipBoard(){
        for (int i=0; i<4; i++){
            for (int j=0; j<7; j++){
                if (i==3 && j==3){
                    if (pieces[i][j]!=null)
                        pieces[3][3].changeDirection();
                    break;
                }
                Piece temp = pieces[i][j];
                pieces[i][j]=pieces[6-i][6-j];
                pieces[6-i][6-j]=temp;
                if (pieces[i][j]!=null)
                    pieces[i][j].changeDirection();
                if (pieces[6-i][6-j]!=null)
                    pieces[6-i][6-j].changeDirection();
            }
        }
    }
    
}
