package Game;

import Model.Color;
import Model.Piece;

public class bool {
    Piece piece;

    public bool( Piece c) {
        piece = c;
    }

    public Piece getPiece(){
        return piece;
    }
    public void setPiece(Piece c){
        piece = c;
    }
    public String toString(){
        return ""+piece+piece.getLocation();
    }
}