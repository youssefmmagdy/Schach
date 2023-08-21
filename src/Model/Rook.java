package Model;

import Game.Game;

import java.awt.*;

public class Rook extends Piece{

    public Rook(Color c, Point l){
        super("R",c,l,5);
    }

    @Override
    public boolean isLegal(Point loc) {
        if(loc.x<8 && loc.y<8 && loc.x>=0 && loc.y>=0 &&
                ((Game.map[loc.y][loc.x]).getPiece() ==null ||
                        ((Game.map[loc.y][loc.x]).getPiece() !=null && getColor() != (Game.map[loc.y][loc.x]).getPiece().getColor())) && freePathR(new Point(getLocation().x,getLocation().y),loc)){
            return true;
        }


        return false;
    }
    public static boolean  freePathR(Point loc2,Point loc){
        if(loc2.x-loc.x==0){
            if(loc.y>loc2.y){
                for(int i = loc2.y+1;i<loc.y;i++){
                    if(Game.map[i][loc.x].getPiece()!=null)return false;
                }
                return true;
            }else if(loc.y<loc2.y){
                for(int i = loc2.y-1;i>loc.y;i--){
                    if(Game.map[i][loc.x].getPiece()!=null)return false;
                }return true;
            }
        }else if(loc2.y-loc.y==0){
            if(loc.x>loc2.x){
                for(int i = loc2.x+1;i<loc.x;i++){
                    if(Game.map[loc.y][i].getPiece()!=null)return false;
                }return true;
            }else if(loc.x<loc2.x){
                for(int i = loc2.x-1;i>loc.x;i--){
                    if(Game.map[loc.y][i].getPiece()!=null)return false;
                }return true;
            }
        }

        return false;
    }
}
