package Model;

import Game.Game;

import java.awt.*;

import static Model.Bishop.freePathB;
import static Model.Rook.freePathR;

public class Queen extends Piece{

    public Queen(Color c, Point l){
        super("Q",c,l,9);
    }

    @Override
    public boolean isLegal(Point loc) {
        if(loc.x<8 && loc.y<8 && loc.x>=0 && loc.y>=0 &&
                ((Game.map[loc.y][loc.x]).getPiece() ==null ||
                        ((Game.map[loc.y][loc.x]).getPiece() !=null && getColor() != (Game.map[loc.y][loc.x]).getPiece().getColor()))
                && (((loc.y == getLocation().y || loc.x == getLocation().x) && freePathR(new Point(getLocation().x,getLocation().y),loc)) || (loc.y != getLocation().y || loc.x != getLocation().x) && freePathB(new Point(getLocation().x,getLocation().y),loc))){
            return true;
        }

        return false;
    }
    public static void main(String[] args){
//        Game.startGame();




    }
}
