import java.util.*;

//Leow Yen Siang,Ngiu Jin Tian Ci,Liew Jun Xian
public class Trident extends Piece 
{
    private String type="Trident";
    private ArrayList<Position> positionList= new ArrayList<Position>();
    private boolean direction=true; //used to determine the way it faces
    private boolean captureDirection=true; //used to determine the way it moves
    Trident(int x, int y, int player){
        super(x,y,player);
    }
    public String getImage(){
        if (getPlayer()==1 && direction==true)
            return "image/7.png"; //orange facing down
        else if (getPlayer()==1 && direction==false)
            return "image/14.png"; //orange facing up
        else if (getPlayer()==0 && direction==false)
            return "image/12.png"; //green facing down
        else
            return "image/3.png"; //green facing up
    }
    
    //need to implement moves front,down, and sides
    public ArrayList <Position> captureFreeMoves(Piece board[][], Position curr)
    {
        positionList = captureFreeMovesHorizontal(board, positionList, curr.getX(), curr.getY(), getPlayer(), 0, 6);
        if(captureDirection){ //if true,moves upwards
            positionList = captureFreeMovesUpward(board, positionList, curr.getX(), curr.getY(), getPlayer(), curr.getX()-1);
            return positionList;
        }
        else{
            positionList = captureFreeMovesDownward(board, positionList, curr.getX(), curr.getY(), getPlayer(), curr.getX()+1);
            return positionList;
        }
    }
    public String getType()
    {
        return type ;
    }
    public void changeDirection(){
        direction=!direction;
    }
    public void changeCaptureDirection(){
        captureDirection=!captureDirection;
    }
    public int getDirection(){
        if (direction==true)
            return 1;
        else
            return 2;
    }
}
