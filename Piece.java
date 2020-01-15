import java.util.*;

//Leow Yen Siang,Ngiu Jin Tian Ci,Liew Jun Xian
public abstract class Piece
{
    private Position position;
    private int player;
    public static ArrayList<Position> positionList;
    public Piece(int x, int y, int player){
        position=new Position(x,y);
        this.player=player;
    }
    
    public abstract String getType() ;
    public abstract String getImage();
    
    public int getPlayer()
    {
        return player;
    }
    
    // ***start,end,topB,downB,leftB,rightB, : value changes based on passed argument ***

    public ArrayList<Position> captureFreeMovesUpward(Piece board[][], ArrayList<Position> positionList, int x, int y, int player, int end)
    {
        if(x>0){//if piece is at top boundary, no need to check
            if (end<0)
                end=0;
            for (int i=x-1; i>=end; i--)
            {
                //System.out.print(i +" "+ y) ;
                if (board[i][y]==null)
                {
                    positionList.add(new Position(i,y)); // add into positionList if piece is empty
                }
                else if (board[i][y].getPlayer()==player)
                {
                    break; //if own piece is blocked in front, break the loop
                }
                else 
                {
                    positionList.add(new Position(i, y)); //if opponent piece is in front, add it into the list and break
                    break;
                }
            }
            return (positionList) ;
        }
        return positionList;
    }
    public ArrayList<Position> captureFreeMovesDownward(Piece board[][], ArrayList<Position> positionList, int x, int y, int player, int end)
    {
        if(x<6){ //if piece is at lower boundary, no need to check
            if (end>6)
                end=6;
            for (int i=x+1; i<=end; i++)
                if (board[i][y]==null)
                     positionList.add(new Position(i, y)); // add into positionList if piece is empty
                else if (board[i][y].getPlayer()==player)
                    break; //if own piece is blocked in front, break the loop
                else
                {
                    positionList.add(new Position(i, y)); //if opponent piece is in front, add it into the list and break
                    break;
                }
        }
        return positionList;
    }
    public ArrayList<Position> captureFreeMovesHorizontal(Piece board[][], ArrayList<Position> positionList, int x, int y, 
                                                          int player, int start, int end)
    {
        if(y>0){ //if piece is at left boundary,no need check left available moves
            for (int i=y-1; i>=start; i--) //check left available moves
                if (board[x][i]==null)
                    positionList.add(new Position(x, i)); // add into positionList if piece is empty
                else if (board[x][i].getPlayer()==player)
                    break; //if own piece is blocked in front, break the loop
                else
                {
                    positionList.add(new Position(x, i)); //if opponent piece is in front, add it into the list and break
                    break;
                }
        }
        if(y<6){ //if piece is at right boundary,no need check right available moves
            for (int i=y+1; i<=end; i++) //check right available moves
                if (board[x][i]==null)
                    positionList.add(new Position(x, i)); // add into positionList if piece is empty
                else if (board[x][i].getPlayer()==player)
                    break; //if own piece is blocked in front, break the loop
                else
                {
                    positionList.add(new Position(x, i)); //if opponent piece is in front, add it into the list and break
                    break;
                }
        }
        return positionList;
    }
    public ArrayList<Position> captureFreeMovesDiagonal(Piece board[][], ArrayList<Position> positionList, int x, int y, 
                                                        int player, int topB, int downB, int leftB ,int rightB)
    {
        if(x>0 && y>0){ //if pices is at top and left boundary, no need check top left boundary
            for(int i=x-1,j=y-1;(i>=topB && j>=leftB);i--,j--){ //checking of top left boundary
                if(board[i][j]==null){
                    positionList.add(new Position(i,j)); // add into positionList if piece is empty
                }
                else if (board[i][j].getPlayer()==player)
                    break; //if own piece is blocked in front, break the loop
                else
                {
                    positionList.add(new Position(i, j)); //if opponent piece is in front, add it into the list and break
                    break;
                }
            }
        }
        if(x>0 && y<6){ //if pices is at top and right boundary, no need check top right boundary
            for(int i=x-1,j=y+1;(i>=topB && j<=rightB);i--,j++){ //checking of top right boundary
                if(board[i][j]==null){
                    positionList.add(new Position(i,j)); // add into positionList if piece is empty
                }
                else if (board[i][j].getPlayer()==player)
                    break; //if own piece is blocked in front, break the loop
                else
                {
                    positionList.add(new Position(i, j)); //if opponent piece is in front, add it into the list and break
                    break;
                }
            }
        }
        if(x<6 && y>0){ //if pieces is at bottom and left boundary, no need check bottom left boundary
            for(int i=x+1,j=y-1;(i<=downB && j>=leftB);i++,j--){ //checking of bottom left boundary
                if(board[i][j]==null){
                    positionList.add(new Position(i,j)); // add into positionList if piece is empty
                }
                else if (board[i][j].getPlayer()==player)
                    break; //if own piece is blocked in front, break the loop
                else
                {
                    positionList.add(new Position(i, j)); //if opponent piece is in front, add it into the list and break
                    break;
                }
            }
        }
        if(x<6 && y<6){ //if pieces is at bottom and left boundary, no need check bottom left boundary
            for(int i=x+1,j=y+1;(i<=downB && j<=rightB);i++,j++){ //checking of bottom right boundary
                if(board[i][j]==null){
                    positionList.add(new Position(i,j)); // add into positionList if piece is empty
                }
                else if (board[i][j].getPlayer()==player)
                    break; //if own piece is blocked in front, break the loop
                else
                {
                    positionList.add(new Position(i, j)); //if opponent piece is in front, add it into the list and break
                    break;
                }
            }
        }
        return positionList;
    }    
    public abstract ArrayList<Position> captureFreeMoves(Piece board[][], Position curr);
    public boolean validMove(ArrayList<Position> positionList, int x, int y){ 
        for(int i=0;i<positionList.size();i++){ 
            if(x==positionList.get(i).getX() && y==positionList.get(i).getY()){
                positionList.clear();
                return true ;
            }
        }
        return false ;
    }
    public abstract void changeDirection();
    public abstract void changeCaptureDirection();
    public abstract int getDirection();
}
