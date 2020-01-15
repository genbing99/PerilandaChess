import java.util.*;

//Leow Yen Siang,Ngiu Jin Tian Ci,Liew Jun Xian
public class Excel extends Piece
{
    private String type="Excel";
    private ArrayList<Position> positionList= new ArrayList<Position>(); //store available moves
    Excel(int x, int y, int player){
        super(x,y,player);
    }
    public String getImage(){
        if (getPlayer()==1 )
            return "image/6.png"; //orange 
        else 
            return "image/2.png"; //green
    }
    //only implement diagonal moves
    public ArrayList <Position> captureFreeMoves(Piece board[][], Position curr)
    {
        positionList = captureFreeMovesDiagonal(board, positionList, curr.getX(), curr.getY(), getPlayer(), 0, 6, 0, 6);
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
