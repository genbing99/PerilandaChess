import java.util.*;

//Leow Yen Siang,Ngiu Jin Tian Ci,Liew Jun Xian
public class Chief extends Piece
{
    private String type="Chief";
    private ArrayList<Position> positionList= new ArrayList<Position>();
    Chief(int x, int y, int player){
        super(x,y,player);
    }
    public String getImage(){
        if (getPlayer()==1 )
            return "image/8.png"; //orange 
        else 
            return "image/4.png"; //green
    }
    //needs to implement up down,sides and diagonal
    public ArrayList <Position> captureFreeMoves(Piece board[][], Position curr)
    {
        positionList = captureFreeMovesHorizontal(board, positionList, curr.getX(), curr.getY(), getPlayer(), curr.getY()-1, 
                                                  curr.getY()+1);
        positionList = captureFreeMovesUpward(board, positionList, curr.getX(), curr.getY(), getPlayer(), curr.getX()-1);
        positionList = captureFreeMovesDownward(board, positionList, curr.getX(), curr.getY(), getPlayer(), curr.getX()+1);
        positionList = captureFreeMovesDiagonal(board, positionList, curr.getX(), curr.getY(), getPlayer(), curr.getX()-1, 
                                                curr.getX()+1, curr.getY()-1, curr.getY()+1);
        return positionList;
    }
    public String getType()
    {
        return type ;
    }
    public void changeDirection(){}
    public void changeCaptureDirection(){}
    public int getDirection(){
        return 3;
    }
}
