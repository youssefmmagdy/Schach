package View;

import Game.*;
import Model.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;

import java.awt.*;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;
import java.io.*;
import java.util.LinkedList;

import static Game.Game.map;
import static View.Spiel.*;
import static javafx.application.Application.launch;

public class CellBox extends VBox{
    static int c1=0,c2=0;
    static Image whiteBackGround = new Image("WhiteBackGround.jpg");
    static Image redBackGround = new Image("RedBackgroung.jpg");
    static Piece temp;

    Cell cell;
    Border checkMate = new Border(new BorderStroke(
            Color.RED,
            BorderStrokeStyle.DASHED,
            new CornerRadii(5),
            new BorderWidths(5)
    ));
    ImageView imageView;
    StackPane stackPane;
    public CellBox(Cell cell){
        super();

        this.cell = cell;
        stackPane = new StackPane();

        imageView = setViews(this.cell.getPiece());

        if(imageView != null)
            stackPane.getChildren().add(imageView);
        getChildren().add(stackPane);
        setMinSize((MainView.width/10),MainView.height/8);
        setMaxSize((Screen.getPrimary().getBounds().getWidth()/10),Screen.getPrimary().getBounds().getHeight()/8);




        if(c2%8 == 0)c1++;
        c2++;
        BackgroundImage myBI;
        if(c1%2!=0)
            if(c2%2==0){
                myBI= new BackgroundImage(new Image("RedBackgroung.jpg",120,150,false,false),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
            }else{
                 myBI= new BackgroundImage(new Image("WhiteBackground.jpg",120,150,false,false),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
            }
        else
            if(c2%2==0){
                 myBI= new BackgroundImage(new Image("WhiteBackground.jpg",120,150,false,false),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
            }else{
                 myBI= new BackgroundImage(new Image("RedBackgroung.jpg",120,150,false,false),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
            }

        setAlignment(Pos.CENTER);
        setBackground(new Background(myBI));



            setOnMouseEntered(e -> {
                if(Spiel.totalWhiteSec == 0){
                    Game.winner = Model.Color.RED;
                    timer1.cancel();
                    timer2.cancel();
                    winMusic();
                    AlertBox.display();
                }else if(Spiel.totalRedSec == 0){
                    Game.winner = Model.Color.WHITE;
                    timer1.cancel();
                    timer2.cancel();
                    winMusic();
                    AlertBox.display();
                }

                if(this.cell.getPiece()!=null && !flag){
                    if(getBorder() == null) {
                        this.setBorder(new Border(new BorderStroke(
                                Color.BLACK,
                                BorderStrokeStyle.DASHED,
                                new CornerRadii(5),
                                new BorderWidths(5)
                        )));
                    }
                    LinkedList<Point> l = this.cell.getPiece().suggestMoves();
                    for (int i = 0; i < l.size(); i++) {
                        RadialGradient gradient = new RadialGradient(
                                0, 0, 0.5, 0.5, 1, true,
                                CycleMethod.NO_CYCLE,
                                new Stop(0, Color.BLACK),
                                new Stop(1, Color.TRANSPARENT)
                        );
                        Circle circle = new Circle(getWidth() / 6);
                        circle.setFill(gradient);
                        boxes[l.get(i).y][l.get(i).x].stackPane.getChildren().add(circle);
                    }

                }else if(flag){
                    this.setBorder(new Border(new BorderStroke(
                            Color.BLACK,
                            BorderStrokeStyle.DASHED,
                            new CornerRadii(5),
                            new BorderWidths(5)
                    )));
                }
            });
            setOnMouseExited(e -> {
                if(this.cell.getPiece()!=null && !flag) {
                    if(getBorder()!=null && !this.getBorder().equals(checkMate))
                        this.setBorder(null);
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            if (boxes[i][j].cell.getPiece() == null && boxes[i][j].stackPane.getChildren().size() == 1 && boxes[i][j].stackPane.getChildren().get(0) instanceof Circle) {
                                boxes[i][j].stackPane.getChildren().remove(0);
                            } else if (boxes[i][j].cell.getPiece() != null && boxes[i][j].stackPane.getChildren().size() == 2 && boxes[i][j].stackPane.getChildren().get(1) instanceof Circle) {
                                boxes[i][j].stackPane.getChildren().remove(1);
                                }
                            }
                        }
                }else if(flag && !(this.cell.getLocation().x == temp.getLocation().x && this.cell.getLocation().y == temp.getLocation().y)){
                    if(getBorder()!=null && !this.getBorder().equals(checkMate))
                        this.setBorder(null);
                }
            });
            setOnMouseClicked(e -> {
                if(this.cell.getPiece() != null && !flag){
                    temp = this.cell.getPiece();
                    flag = true;
                } else if(flag) {
                    if(temp.move(this.cell.getLocation())) {
                        if(cell.getPiece() instanceof Pawn) {
                            pawnMusic();
                        }
                        else if(cell.getPiece() instanceof Knight || cell.getPiece() instanceof Bishop) {
                            knightMusic();
                        }
                        else if(cell.getPiece() instanceof Queen) {
                            queenMusic();
                        }
                        else if(cell.getPiece() instanceof Rook) {
                            rookMusic();
                        }
                        moveMusic();

                        setPieces();

                        if(Eintrag.flag1){
                            if(Game.turn == Model.Color.RED){
                                totalWhiteSec++;
                                int i = totalWhiteSec;
                                whiteTimer.setText((i / 60) < 10 ? "0" + i / 60 + ":" + (i - (i / 60) * 60) : 0 + i / 60 + ":" + (i - (i / 60) * 60));
                            }else{
                                totalRedSec++;
                                int i = ++totalRedSec;
                                redTimer.setText((i / 60) < 10 ? "0" + i / 60 + ":" + (i - (i / 60) * 60) : 0 + i / 60 + ":" + (i - (i / 60) * 60));
                            }
                        }

                        if(Game.turn == Model.Color.RED) {
                            for (Piece p : Game.redPieces)
                                if (p instanceof King && map[p.getLocation().y][p.getLocation().x].isThreat().size() > 0) {
                                    checkMateMusic();
                                    boxes[p.getLocation().y][p.getLocation().x].setBorder(checkMate);
                                }else if(p instanceof King){
                                    if(boxes[p.getLocation().y][p.getLocation().x].getBorder()!=null&&boxes[p.getLocation().y][p.getLocation().x].getBorder().equals(checkMate))
                                        boxes[p.getLocation().y][p.getLocation().x].setBorder(null);
                                }
                        }else{
                            for (Piece p : Game.whitePieces)
                                if (p instanceof King && map[p.getLocation().y][p.getLocation().x].isThreat().size() > 0) {
                                    checkMateMusic();
                                    boxes[p.getLocation().y][p.getLocation().x].setBorder(checkMate);
                                }else if(p instanceof King){
                                    if(boxes[p.getLocation().y][p.getLocation().x].getBorder()!=null&&boxes[p.getLocation().y][p.getLocation().x].getBorder().equals(checkMate))
                                       boxes[p.getLocation().y][p.getLocation().x].setBorder(null);
                                }
                        }
                        redPoints.setText(""+Game.redPoints);
                        whitePoints.setText(""+Game.whitePoints);
                        if (Game.checkEndGame()) {
                            timer1.cancel();
                            timer2.cancel();
                            winMusic();
                            AlertBox.display();
                        }else
                            changeTimers();

                    } else{

                        for(int i = 0;i<8;i++)
                            for(int j = 0;j<8;j++)
                                if(boxes[i][j].getBorder() != null && !boxes[i][j].getBorder().equals(checkMate))
                                    boxes[i][j].setBorder(null);
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                if (boxes[i][j].cell.getPiece() == null && boxes[i][j].stackPane.getChildren().size() == 1 && boxes[i][j].stackPane.getChildren().get(0) instanceof Circle) {
                                    boxes[i][j].stackPane.getChildren().remove(0);
                                } else if (boxes[i][j].cell.getPiece() != null && boxes[i][j].stackPane.getChildren().size() == 2 && boxes[i][j].stackPane.getChildren().get(1) instanceof Circle) {
                                    boxes[i][j].stackPane.getChildren().remove(1);
                                }
                            }
                        }
                    }
                    flag = false;
                }
            });

    }

    static ImageView setViews(Piece cell){
        ImageView imageView = null;
        if(cell != null && cell.getName().equals("P"))
            imageView = cell.getColor() == Model.Color.RED?new ImageView(new Image("Red_Pawn.jpg",100,100,false,false))
                    :new ImageView(new Image("White_Pawn.jpg",100,100,false,false));
        else if(cell != null && cell.getName().equals("B"))
            imageView =  cell.getColor() == Model.Color.RED ? new ImageView(new Image("Red_Bishop.jpg",100,100,false,false)):
                new ImageView(new Image("White_Bishop.jpg",100,100,false,false));
        else if(cell != null && cell.getName().equals("Q"))
            imageView = cell.getColor() == Model.Color.RED? new ImageView(new Image("Red_Queen.jpg",100,100,false,false))
                    : new ImageView(new Image("White_Queen.jpg",100,100,false,false));
        else if(cell != null && cell.getName().equals("N"))
            imageView = cell.getColor() == Model.Color.RED? new ImageView(new Image("Red_Knight.jpg",100,100,false,false))
                    : new ImageView(new Image("White_Knight.jpg",100,100,false,false));
        else if(cell != null && cell.getName().equals("K"))
            imageView = cell.getColor() == Model.Color.RED?redKing:whiteKing;
        else if(cell != null && cell.getName().equals("R"))
            imageView = cell.getColor() == Model.Color.RED? new ImageView(new Image("Red_Rook.jpg",100,100,false,false))
                    : new ImageView(new Image("White_Rook.jpg",100,100,false,false));
        return imageView;
    }
    public static void addDeadPieces(){
        if(Game.turn == Model.Color.RED) {
            if (((HBox) dReds.getChildren().get(cntReds)).getChildren().size() == 4)
                cntReds++;
            if (Piece.deadPiece != null) {
                ImageView iv = setViews(Piece.deadPiece);
                iv.setFitHeight(50);
                iv.setFitWidth(50);
                ((HBox) (Spiel.dReds.getChildren().get(cntReds))).getChildren().add(iv);
            }
        }
        else{
            if(((HBox)dWhites.getChildren().get(cntWhites)).getChildren().size() == 4)
                cntWhites++;
            if (Piece.deadPiece != null) {
                ImageView iv = setViews(Piece.deadPiece);
                iv.setFitHeight(50);
                iv.setFitWidth(50);
                ((HBox) (Spiel.dWhites.getChildren().get(cntWhites))).getChildren().add(iv);
            }
        }
    }

    public static void main(String[] args){
        launch(args);
    }
}
