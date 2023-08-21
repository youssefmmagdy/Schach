package Game;

import Model.*;
import Model.Color;
import Model.Rook;
import View.AlertBox;
import View.Spiel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.awt.*;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.time.Period;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(System.out);
    public static Color upper;
    public static Color lower;
    public static Color turn;
    public static Cell[][] map;
    public static ArrayList<Piece> whitePieces;
    public static ArrayList<Piece> redPieces;
    public static int redPoints;
    public static int whitePoints;

    public static void setDeadPoints(Piece p){
        if(p.getColor() == Color.WHITE)
            redPoints+=p.getPoints();
        else whitePoints+=p.getPoints();
    }
    public static Color winner;
    public static void startGame(){
        redPoints = 0;
        whitePoints = 0;
        int x = (int)(Math.random()*2);
//        if(x==0){upper = Color.RED;lower = Color.WHITE;}
//        else {upper = Color.WHITE;lower = Color.RED;}
//        x = (int)(Math.random()*2);
//        if(x==0)turn = Color.RED;
//        else turn = Color.WHITE;
        turn = Color.WHITE;
        upper = Color.RED;
        lower = Color.WHITE;
        map = new Cell[8][8];
        whitePieces = new ArrayList<>();
        redPieces = new ArrayList<>();
        Rook r1 = new Rook(upper,new Point(0,0));
        map[0][0] = new Cell(r1,new Point(0,0));redPieces.add(r1);
        Rook r2 = new Rook(upper,new Point(7,0));
        map[0][7] = new Cell(r2,new Point(7,0));redPieces.add(r2);
        Knight n1 = new Knight(upper,new Point(1,0));
        map[0][1] = new Cell(n1,new Point(1,0));redPieces.add(n1);
        Knight n2 = new Knight(upper,new Point(6,0));
        map[0][6] = new Cell(n2,new Point(6,0));redPieces.add(n2);
        Bishop b1 = new Bishop(upper,new Point(2,0));
        map[0][2] = new Cell(b1,new Point(2,0));redPieces.add(b1);
        Bishop b2 = new Bishop(upper,new Point(5,0));
        map[0][5] = new Cell(b2,new Point(5,0));redPieces.add(b2);
        Queen q = new Queen(upper,new Point(3,0));
        map[0][3] = new Cell(q,new Point(3,0));redPieces.add(q);
        King k = new King(upper,new Point(4,0));
        map[0][4] = new Cell(k,new Point(4,0));redPieces.add(k);
        for(int i = 0;i<8;i++){
            Pawn p = new Pawn(upper,new Point(i,1));
            map[1][i] = new Cell(p,new Point(i,1));
            redPieces.add(p);
        }

        Rook r11 = new Rook(lower,new Point(0,7));
        map[7][0] = new Cell(r11,new Point(0,7));whitePieces.add(r11);
        Rook r22 = new Rook(lower,new Point(7,7));
        map[7][7] = new Cell(r22,new Point(7,7));whitePieces.add(r22);
        Knight n11 = new Knight(lower,new Point(1,7));
        map[7][1] = new Cell(n11,new Point(1,7));whitePieces.add(n11);
        Knight n22 = new Knight(lower,new Point(6,7));
        map[7][6] = new Cell(n22,new Point(6,7));whitePieces.add(n22);
        Bishop b11 = new Bishop(lower,new Point(2,7));
        map[7][2] = new Cell(b11,new Point(2,7));whitePieces.add(b11);
        Bishop b22 = new Bishop(lower,new Point(5,7));
        map[7][5] = new Cell(b22,new Point(5,7));whitePieces.add(b22);
        Queen qU = new Queen(lower,new Point(3,7));
        map[7][3] = new Cell(qU,new Point(3,7));whitePieces.add(qU);
        King kU = new King(lower,new Point(4,7));
        map[7][4] = new Cell(kU,new Point(4,7));whitePieces.add(kU);
        for(int i = 0;i<8;i++){
            Pawn p = new Pawn(lower,new Point(i,6));
            map[6][i] = new Cell(p,new Point(i,6));
            whitePieces.add(p);
        }

        for(int i = 0;i<8;i++){
            for(int j = 0;j<8;j++){
                if(map[i][j] == null)
                    map[i][j] = new Cell(null,new Point(j,i));
            }
        }

        Piece.setCellsThreat();
    }
    public static boolean checkEndGame(){

        int c=0;
        Piece k=null;
        for(Piece p : redPieces) {
            if(p instanceof King)
                k = p;
            if (p.suggestMoves().size() == 0)
                c++;
        }
        if(k!=null && c == redPieces.size() && map[k.getLocation().y][k.getLocation().x].isThreat().size()>0) {
            winner = Color.WHITE;
            return true;
        }
        if(c == redPieces.size())
            return true;
        c=0;
        k = null;
        for(Piece p : whitePieces) {
            if(p instanceof King)
                k = p;
            if (p.suggestMoves().size() == 0)
                c++;
        }
        if(k!=null && c == whitePieces.size() && map[k.getLocation().y][k.getLocation().x].isThreat().size()>0) {
            winner = Color.RED;
            return true;
        }
        if(c == whitePieces.size())
            return true;
        return false;
    }
    public static void main(String[] args){

        pw.flush();
    }

}
