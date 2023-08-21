package Model;

import Game.Game;

import java.awt.*;

public class Bishop extends Piece{

    public Bishop(Color c, Point l){
        super("B",c,l,3);
    }

    @Override
    public boolean isLegal(Point loc) {
        if(loc.x<8 && loc.y<8 && loc.x>=0 && loc.y>=0 &&
                ((Game.map[loc.y][loc.x]).getPiece() ==null ||
                        ((Game.map[loc.y][loc.x]).getPiece() !=null && getColor() != (Game.map[loc.y][loc.x]).getPiece().getColor())) && freePathB(new Point(getLocation().x, getLocation().y),loc)){
            return true;
        }
        return false;
    }
    static boolean freePathB(Point loc, Point loc2){
        if(loc2.y-loc.y > 0 &&  loc2.x-loc.x > 0){
            int i = loc.x+1, j = loc.y+1;
            while(i != loc2.x && j != loc2.y){
                if(Game.map[j][i].getPiece()!=null)return false;
            i++;j++;}
            if(i == loc2.x && j == loc2.y)
            return true;
        }else if(loc2.y-loc.y > 0 &&  loc2.x-loc.x < 0){
            int i = loc.x-1, j = loc.y+1;
            while(i != loc2.x && j != loc2.y){
                if(Game.map[j][i].getPiece()!=null)return false;
                i--;j++;}
            if(i == loc2.x && j == loc2.y)
            return true;
        }else if(loc2.y-loc.y < 0 &&  loc2.x-loc.x > 0){
            int i = loc.x+1, j = loc.y-1;
            while(i != loc2.x && j != loc2.y){
                if(Game.map[j][i].getPiece()!=null)return false;
                i++;j--;}
            if(i == loc2.x && j == loc2.y)
            return true;
        }else if(loc2.y-loc.y < 0 &&  loc2.x-loc.x < 0){
            int i = loc.x-1, j = loc.y-1;
            while(i != loc2.x && j != loc2.y){
                if(Game.map[j][i].getPiece()!=null)return false;
                i--;j--;}
            if(i == loc2.x && j == loc2.y)
            return true;
        }

        return false;
    }
}
