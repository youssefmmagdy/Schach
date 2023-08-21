package Game;

import Model.Color;
import Model.Piece;

import java.awt.*;
import java.awt.event.PaintEvent;
import java.util.LinkedList;

public class Cell {
    public LinkedList<Piece> threats;
    private boolean isProtected;
    private Piece piece;

    private Point location;


    public Cell(Piece p,Point location){
        piece = p;
        threats = new LinkedList<>();
        this.location = location;
    }

    public Piece getPiece(){
        return piece;
    }

    public void setPiece(Piece p){
        piece = p;
    }

    public LinkedList<Piece> isThreat(){
        return threats;
    }

    public void addThreat(Piece f){
        threats.add(f);
    }
    public boolean isProtected(){
        return isProtected;
    }
    public void setProtected(boolean f){
        isProtected = f;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

}
