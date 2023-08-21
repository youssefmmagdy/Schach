package Model;

import Game.Game;

import java.awt.*;

public class Knight extends Piece{

    public Knight(Color c, Point l){
        super("N",c,l,3);
    }

    @Override
    public boolean isLegal(Point loc) {
        if(loc.x<8 && loc.y<8 && loc.x>=0 && loc.y>=0 && ((Game.map[loc.y][loc.x]).getPiece() == null || ((Game.map[loc.y][loc.x]).getPiece()!=null && getColor() != (Game.map[loc.y][loc.x]).getPiece().getColor()))){
            if(loc.x- getLocation().x==1&&loc.y- getLocation().y==2 || loc.x- getLocation().x==-1&&loc.y- getLocation().y==2
            || loc.x- getLocation().x==1&&loc.y- getLocation().y==-2 || loc.x- getLocation().x==-1&&loc.y- getLocation().y==-2
            || loc.x- getLocation().x==2&&loc.y- getLocation().y==1 || loc.x- getLocation().x==2&&loc.y- getLocation().y==-1
            || loc.x- getLocation().x==-2&&loc.y- getLocation().y==-1 || loc.x- getLocation().x==-2&&loc.y- getLocation().y==1)
                return true;
        }
        return false;
    }
}
