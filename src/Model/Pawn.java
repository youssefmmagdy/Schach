package Model;

import java.awt.*;
import Game.*;
public class Pawn extends Piece{

    public Pawn(Color c, Point l){
        super("P",c,l,1);
    }

    @Override
    public boolean isLegal(Point loc) {
        if((loc.x==getLocation().x&&loc.y==getLocation().y) || ((Game.map[loc.y][loc.x]).getPiece()!=null && getColor() == (Game.map[loc.y][loc.x]).getPiece().getColor())){

            return false;
        }

        if(Game.lower != getColor()){
            if(loc.x<8 && loc.y<8 && loc.x>=0 && loc.y>=0 && (loc.x-getLocation().x==1 || loc.x-getLocation().x==-1 || loc.x-getLocation().x==0)){
                if((Game.map[loc.y][loc.x]).getPiece() != null && loc.x-getLocation().x == 0){
                    return false;
                }
                if(getLocation().y == 1 && (loc.x-getLocation().x == 0 && ((loc.y-getLocation().y == 1 && Game.map[loc.y][loc.x].getPiece()==null) || (loc.y-getLocation().y == 2 && Game.map[loc.y-1][loc.x].getPiece()==null)))){
                    return true;
                }else if(getLocation().y >=1 && loc.y-getLocation().y==1 && Game.map[loc.y][loc.x].getPiece()!=null && (loc.x-getLocation().x == 1 || loc.x-getLocation().x == -1)){
                    return true;
                }else if(getLocation().y >1 && loc.y-getLocation().y==1 && loc.x == getLocation().x){
                    return true;
                }
            }
        }else{
            if(loc.x<8 && loc.y<8 && loc.x>=0 && loc.y>=0 && (loc.x-getLocation().x==-1 || loc.x-getLocation().x==1 || loc.x-getLocation().x==0)){
                if((Game.map[loc.y][loc.x]).getPiece() != null && loc.x-getLocation().x == 0)return false;
                if(getLocation().y == 6 && (loc.x-getLocation().x == 0 && ((loc.y-getLocation().y == -1 && Game.map[loc.y][loc.x].getPiece()==null) || (loc.y-getLocation().y == -2 && Game.map[loc.y+1][loc.x].getPiece()==null)))){
                    return true;
                }else if(getLocation().y <=6 && loc.y-getLocation().y==-1 && Game.map[loc.y][loc.x].getPiece()!=null && (loc.x-getLocation().x == 1 || loc.x-getLocation().x == -1)){
                    return true;
                }else if(getLocation().y<6 && loc.y-getLocation().y==-1 && loc.x == getLocation().x){
                    return true;
                }
            }
        }
        return false;
    }
}
