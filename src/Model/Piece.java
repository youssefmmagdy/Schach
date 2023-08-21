package Model;

import java.awt.*;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;

import View.CellBox;
import View.Spiel;
import Game.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.Line;

import static Game.Game.*;
import static javafx.application.Application.launch;

abstract public class Piece {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(System.out);
    private String name;
    private Color color;
    private int points;
    private int moves;
    private Point location;
    public static Piece deadPiece;

    public Piece(String n,Color c,Point loc,int p){
        points = p;
        name = n;
        color = c;
        location = loc;
        moves = 0;
    }
    public int getPoints(){
        return points;
    }
    public void setPoints(int m){
        points = m;
    }
    public int getMoves(){
        return moves;
    }
    public void setMoves(int m){
        moves = m;
    }


    public String getName(){
        return name;
    }
    public Color getColor(){
        return color;
    }
    public Point getLocation(){
        return location;
    }
    public void setLocation(Point p){
        location = p;
    }

    public abstract boolean isLegal(Point loc);

    public LinkedList<Point> suggestMoves(){
        LinkedList<Point> l = new LinkedList<>();
        Piece piece = this;
        if(piece instanceof King && piece.getMoves() == 0 ){
            if(map[getLocation().y][0].getPiece()!=null && map[getLocation().y][0].getPiece().getMoves() == 0 && map[getLocation().y][3].getPiece()==null &&
                    map[getLocation().y][2].getPiece()==null && map[getLocation().y][1].getPiece()==null
//                    && (map[getLocation().y][0].isThreat().isEmpty() || (!map[getLocation().y][0].isThreat().isEmpty() && rev(map[getLocation().y][0].isThreat(),getColor())))
                    && (map[getLocation().y][1].isThreat().isEmpty() || (!map[getLocation().y][1].isThreat().isEmpty() && rev(map[getLocation().y][1].isThreat(),getColor())))
                    && (map[getLocation().y][2].isThreat().isEmpty() || (!map[getLocation().y][2].isThreat().isEmpty() && rev(map[getLocation().y][2].isThreat(),getColor()))) &&
                    (map[getLocation().y][3].isThreat().isEmpty() || (!map[getLocation().y][3].isThreat().isEmpty() && rev(map[getLocation().y][3].isThreat(),getColor())))
                    && (map[getLocation().y][4].isThreat().isEmpty() || (!map[getLocation().y][4].isThreat().isEmpty() && rev(map[getLocation().y][4].isThreat(),getColor())))){
                l.add(new Point(0,getLocation().y));
            }
            if(map[getLocation().y][7].getPiece()!=null && map[getLocation().y][7].getPiece().getMoves() == 0 && map[getLocation().y][6].getPiece()==null && map[getLocation().y][5].getPiece()==null &&
                    (map[getLocation().y][4].isThreat().isEmpty() || (!map[getLocation().y][4].isThreat().isEmpty() && rev(map[getLocation().y][4].isThreat(),getColor())))
                    && (map[getLocation().y][5].isThreat().isEmpty() || (!map[getLocation().y][5].isThreat().isEmpty() && rev(map[getLocation().y][5].isThreat(),getColor())))
                    && (map[getLocation().y][6].isThreat().isEmpty() || (!map[getLocation().y][6].isThreat().isEmpty() && rev(map[getLocation().y][6].isThreat(),getColor())))
//                    && (map[getLocation().y][7].isThreat().isEmpty() || (!map[getLocation().y][7].isThreat().isEmpty() && rev(map[getLocation().y][7].isThreat(),getColor())))
                    ){
                l.add(new Point(7,getLocation().y));
            }
        }
        for(int i = 0;i<8;i++){
            for(int j = 0;j<8;j++){
                Point p = new Point(j,i);
                if(isLegal(p) && willProtect(p)) {
                    l.add(p);
                }
            }
        }

        return l;
    }
    private boolean rev(LinkedList<Piece> l,Color c2){
        for(int i = 0;i<l.size();i++){
            if(l.get(i).getColor() != c2)
                return false;
        }
        return true;
    }
    public LinkedList<Point> suggest(){
        LinkedList<Point> l = new LinkedList<>();

        for(int i = 0;i<8;i++)
            for(int j = 0;j<8;j++){
                Point p = new Point(j,i);
                if(this instanceof Pawn && (map[p.y][p.x].getPiece() == null || (map[p.y][p.x].getPiece()!=null && getColor() != map[p.y][p.x].getPiece().getColor()))){
                    if(getColor() == Color.RED){
                        if(Math.abs(p.x-getLocation().x) == 1 && p.y- getLocation().y == 1){
                            l.add(p);
                        }
                    }else{
                        if(Math.abs(p.x-getLocation().x) == 1 && p.y- getLocation().y == -1){
                            l.add(p);
                        }
                    }
                }
                else if(!(this instanceof Pawn) && isLegal(p))
                    l.add(p);
            }
        return l;
    }
    public boolean willProtect(Point p){
        Point init = new Point(getLocation().x, getLocation().y);
        Piece old = null;
        if(map[p.y][p.x].getPiece() == null){
            int x = getLocation().x, y = getLocation().y;
            map[p.y][p.x].setPiece(this);
            map[y][x].setPiece(null);
        }else{
            int x = getLocation().x, y = getLocation().y;
            old = map[p.y][p.x].getPiece();
            map[p.y][p.x].setPiece(this);
            map[y][x].setPiece(null);
        }
        getLocation().x = p.x;
        getLocation().y = p.y;

        if(old!=null){
            if(getColor() != Color.RED){
                for(Piece piece : Game.redPieces){
                    if(piece.getLocation().x == old.getLocation().x && piece.getLocation().y == old.getLocation().y ) {
                        Game.redPieces.remove(old);
                        break;
                    }
                }
            }else{
                for(Piece piece : whitePieces){
                    if(piece.getLocation().x == old.getLocation().x && piece.getLocation().y == old.getLocation().y) {
                        whitePieces.remove(old);
                        break;
                    }
                }
            }
        }

        setCellsThreat();

        boolean flag = true;
        if(getColor() == Color.RED){
            for (Piece i : Game.redPieces){
                if(i instanceof King && !map[i.getLocation().y][i.getLocation().x].isThreat().isEmpty()){
                    flag = false;
                }
            }
        }else{
            for (Piece i : whitePieces){
                if(i instanceof King && !map[i.getLocation().y][i.getLocation().x].isThreat().isEmpty()){
                    flag = false;
                }
            }
        }
        int x = getLocation().x, y = getLocation().y;
        map[init.y][init.x].setPiece(this);
        map[y][x].setPiece(old);
        getLocation().x = init.x;
        getLocation().y = init.y;
        if(old!=null){
            if(getColor() != Color.RED){
                Game.redPieces.add(old);
            }else{
                whitePieces.add(old);
            }
        }
        setCellsThreat();

        pw.flush();
        return flag;
    }
    public static void changeTurn(){
        if(Game.turn == Color.RED)
            Game.turn = Color.WHITE;
        else Game.turn = Color.RED;
    }
    public boolean move(Point loc){
        deadPiece = null;
        if(Game.turn == getColor() && exist(loc,suggestMoves())){

            if(this instanceof King && getMoves() == 0){
                if(loc.x == 0){
                    int x = getLocation().x, y = getLocation().y;
                    map[loc.y][2].setPiece(this);
                    map[y][x].setPiece(null);
                    map[loc.y][3].setPiece(map[y][0].getPiece());
                    map[y][0].setPiece(null);
                    map[loc.y][2].getPiece().setLocation(new Point(2,loc.y));
                    map[loc.y][3].getPiece().setLocation(new Point(3,loc.y));
                    changeTurn();
                    Spiel.changeTurnBG();
                    setCellsThreat();
                    this.setMoves(getMoves()+1);
                    Spiel.castleMusic();
                    return true;

                }else if(loc.x == 7){
                    int x = getLocation().x, y = getLocation().y;
                    map[loc.y][6].setPiece(this);
                    map[y][x].setPiece(null);
                    map[loc.y][5].setPiece(map[y][7].getPiece());
                    map[y][7].setPiece(null);
                    map[loc.y][6].getPiece().setLocation(new Point(6,loc.y));
                    map[loc.y][5].getPiece().setLocation(new Point(5,loc.y));
                    changeTurn();
                    Spiel.changeTurnBG();
                    setCellsThreat();
                    this.setMoves(getMoves()+1);
                    Spiel.castleMusic();
                    return true;
                }
            }

            if(this instanceof Pawn && (loc.y == 7 || loc.y == 0)){
                int x = (int)(Math.random()*4);
                Piece p = null;
                if(getColor() == Color.RED) {
                    redPieces.remove(map[getLocation().y][getLocation().x].getPiece());
                }else {
                    whitePieces.remove(map[getLocation().y][getLocation().x].getPiece());
                }

                map[getLocation().y][getLocation().x].setPiece(null);
                getLocation().x = loc.x;
                getLocation().y = loc.y;

                if(map[loc.y][loc.x].getPiece() != null) {
                    if (map[loc.y][loc.x].getPiece().getColor() == Color.RED) {
                        redPieces.remove(map[loc.y][loc.x].getPiece());
                    } else {
                        whitePieces.remove(map[loc.y][loc.x].getPiece());
                    }
                    deadPiece = map[loc.y][loc.x].getPiece();
                    CellBox.addDeadPieces();
                    Game.setDeadPoints(map[loc.y][loc.x].getPiece());
                }
                switch (x) {
                    case 0 -> p = new Rook(getColor(), new Point(getLocation().x, getLocation().y));
                    case 1 -> p = new Queen(getColor(), new Point(getLocation().x, getLocation().y));
                    case 2 -> p = new Knight(getColor(), new Point(getLocation().x, getLocation().y));
                    case 3 -> p = new Bishop(getColor(), new Point(getLocation().x, getLocation().y));
                }
//                if(getColor() == Color.RED)
//                    redPoints--;
//                else whitePoints--;
                map[getLocation().y][getLocation().x].setPiece(p);
                if(getColor() == Color.RED)
                    redPieces.add(map[getLocation().y][getLocation().x].getPiece());
                else
                    whitePieces.add(map[getLocation().y][getLocation().x].getPiece());

                Spiel.myTimeMusic();

                changeTurn();
                Spiel.changeTurnBG();
                setCellsThreat();
                this.setMoves(getMoves()+1);
                return true;
            }else if(map[loc.y][loc.x].getPiece() == null){
                int x = getLocation().x, y = getLocation().y;
                map[loc.y][loc.x].setPiece(this);
                map[y][x].setPiece(null);
            }else{
                int x = getLocation().x, y = getLocation().y;
                if(Color.RED != getColor()) {
                    Game.redPieces.remove(map[loc.y][loc.x].getPiece());
                }else {
                    whitePieces.remove(map[loc.y][loc.x].getPiece());
                }
                Game.setDeadPoints(map[loc.y][loc.x].getPiece());
                deadPiece = map[loc.y][loc.x].getPiece();
                CellBox.addDeadPieces();
                map[loc.y][loc.x].setPiece(this);
                map[y][x].setPiece(null);
            }
            getLocation().x = loc.x;
            getLocation().y = loc.y;
            changeTurn();
            Spiel.changeTurnBG();
            setCellsThreat();
            this.setMoves(getMoves()+1);
            return true;
        }
        return false;

    }

    public static void setCellsThreat(){

        for(int i = 0;i<8;i++){
            for(int j = 0;j<8;j++) {
                map[i][j].threats = new LinkedList<>();
            }
        }

        for(Piece whitePiece : whitePieces){
            LinkedList<Point> l = whitePiece.suggest();
            for(int i = 0;i<l.size();i++) {
                map[l.get(i).y][l.get(i).x].addThreat(whitePiece);
            }
        }

        for(Piece redPiece : Game.redPieces){
            LinkedList<Point> l = redPiece.suggest();
            for(int  i = 0;i<l.size();i++) {
                map[l.get(i).y][l.get(i).x].addThreat(redPiece);
            }
        }

    }


    public static boolean exist(Point p,LinkedList<Point> list){
        for(int i = 0;i<list.size();i++){
            Point p1 = list.get(i);
            if(p.x == p1.x && p.y == p1.y)
                return true;
        }
        return false;
    }
    public String toString(){
        return name+getLocation().x+","+getLocation().y;
    }




        public static void main(String[] args){
        launch(args);
    }
}
