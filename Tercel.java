import java.util.*;

//Leow Yen Siang,Ngiu Jin Tian Ci,Liew Jun Xian
public class Tercel extends Piece 
{
    private String type="Tercel";
    private ArrayList<Position> positionList= new ArrayList<Position>();
    Tercel(int x, int y, int player){
        super(x,y,player);
    }
    public String getImage(){
        if (getPlayer()==1 )
            return "image/5.png"; //orange 
        else 
            return "image/1.png"; //green
    }
    //needs to implement horizontal ,up and down movement
    public ArrayList <Position> captureFreeMoves(Piece board[][], Position curr)
    {
       positionList = captureFreeMovesHorizontal(board, positionList, curr.getX(), curr.getY(), getPlayer(), 0, 6);
       positionList = captureFreeMovesUpward(board, positionList, curr.getX(), curr.getY(), getPlayer(), 0);
       positionList = captureFreeMovesDownward(board, positionList, curr.getX(), curr.getY(), getPlayer(), 6);
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
