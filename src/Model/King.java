package Model;

import Game.Game;

import java.awt.*;

public class King extends Piece{
    public King(Color c, Point l){
        super("K",c,l,0);
    }

    @Override
    public boolean isLegal(Point loc) {
        if(loc.x<8 && loc.y<8 && loc.x>=0 && loc.y>=0 && (Game.map[loc.y][loc.x].getPiece() == null ||
                (Game.map[loc.y][loc.x].getPiece()!=null && getColor() != Game.map[loc.y][loc.x].getPiece().getColor()))
                && Math.abs(getLocation().x-loc.x)<2 && Math.abs(getLocation().y-loc.y)<2 )return true;
        return false;
    }

}
