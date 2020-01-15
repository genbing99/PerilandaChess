import java.util.*;

//Leow Yen Siang,Ngiu Jin Tian Ci,Liew Jun Xian
public class Advancer extends Piece
{
    private String type="Advancer";
    public static ArrayList<Position> positionList= new ArrayList<Position>();
    private boolean direction=true; //used to determine the way it faces
    private boolean captureDirection=true; //used to determine the way it moves
    Advancer(int x, int y, int player){
        super(x,y,player);
    }
    public String getImage(){
        if (getPlayer()==1 && direction==true)
            return "image/10.png"; //orange face down
        else if (getPlayer()==1 && direction==false)
            return "image/13.png"; //orange face up
        else if (getPlayer()==0 && direction==false)
            return "image/11.png"; //green face down
        else
            return "image/9.png"; //green face up
    }
    
    //need to implement up and down
    public ArrayList <Position> captureFreeMoves(Piece board[][], Position curr)
    {
        if(captureDirection){//if true, moves upwards
            positionList = captureFreeMovesUpward(board, positionList, curr.getX(), curr.getY(), getPlayer(), curr.getX()-2);
            return (positionList);
        }
        else{
            positionList = captureFreeMovesDownward(board, positionList, curr.getX(), curr.getY(), getPlayer(), curr.getX()+2);
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
