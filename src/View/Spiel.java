package View;

import Game.*;
import Model.*;
//import Model.Color;
import com.sun.tools.javac.Main;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.nio.file.Paths;
import java.util.*;
import static Game.Game.*;
import static View.Eintrag.textField1;
import static View.Eintrag.textField2;

import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Spiel{
    public static void main(String[] args){

    }

    static CellBox[][] boxes;
    static GridPane grid;
    static boolean flag;
    static ImageView whiteKing = new ImageView(new Image("White_King.jpg",100,100,false,false));
    static ImageView whiteQueen = new ImageView(new Image("White_Queen.jpg",100,100,false,false));
    static ImageView whiteKnight = new ImageView(new Image("White_Knight.jpg",100,100,false,false));
    static ImageView whiteBishop = new ImageView(new Image("White_Bishop.jpg",100,100,false,false));
    static ImageView whiteRook = new ImageView(new Image("White_Rook.jpg",100,100,false,false));
    static ImageView redRook = new ImageView(new Image("Red_Rook.jpg",100,100,false,false));
    static ImageView redQueen = new ImageView(new Image("Red_Queen.jpg",100,100,false,false));
    static ImageView redKing = new ImageView(new Image("Red_King.jpg",100,100,false,false));
    static ImageView redBishop = new ImageView(new Image("Red_Bishop.jpg",100,100,false,false));
    static ImageView redKnight = new ImageView(new Image("Red_Knight.jpg",100,100,false,false));

    static Stage stage;
    static VBox turn;

//    static Thread th1;
//    static Thread th2;
    public static void changeTurnBG(){
        if(Game.turn == Model.Color.RED){
            turn.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        }else{
            turn.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }
    public static int redMin,redSec,whiteMin,whiteSec,totalRedSec,totalWhiteSec;
    static Timer timer1;
    static Timer timer2;
    public static void changeTimers(){
        if(Game.turn == Model.Color.RED) {
            timer2.cancel();
            timer1 = new Timer();
            timer1.schedule(new TimerTask() {
                @Override
                public void run() {
                    totalRedSec--;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            int i = totalRedSec;
                            redTimer.setText((i / 60) < 10 ? "0" + i / 60 + ":" + (i - (i / 60) * 60) : 0 + i / 60 + ":" + (i - (i / 60) * 60));
                            redMin = i / 60;
                            redSec = (i - (i / 60) * 60);
                        }
                    });
                    if(totalRedSec == 20){
                        oomMusic();
                    }
                    if(totalRedSec == 5){
                        a8esonyMusic();
                    }

                    if(totalRedSec <= 0){
                        timer1.cancel();
                        this.cancel();
                    }
                }
            }, 0, 1000);
        }
        else {
            timer1.cancel();
            timer2 = new Timer();
            timer2.schedule(new TimerTask() {


                @Override
                public void run() {
                    totalWhiteSec--;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            int i = totalWhiteSec;
                            whiteTimer.setText((i / 60) < 10 ? "0" + i / 60 + ":" + (i - (i / 60) * 60) : 0 + i / 60 + ":" + (i - (i / 60) * 60));
                            whiteMin = i / 60;
                            whiteSec = (i - (i / 60) * 60);
                        }
                    });
                    if(totalWhiteSec == 20){
                        oomMusic();
                    }
                    if(totalWhiteSec == 5){
                        a8esonyMusic();
                    }

                    if(totalWhiteSec <= 0){
                        timer2.cancel();
                        this.cancel();
                    }
                }
            },0,1000);
        }
    }
    static Label redTimer;
    static Label whiteTimer;
    static HBox dRed1;
    static HBox dWhite1;
    static HBox dRed2;
    static HBox dWhite2;
    static HBox dRed3;
    static HBox dWhite3;
    static HBox dRed4;
    static HBox dWhite4;
    static int cntReds;
    static int cntWhites;
    static VBox dReds;
    static VBox dWhites;

    static Label redPoints;
    static Label whitePoints;
    public static void start(){
        mediaPlayers = new LinkedList<>();
        Spiel.bingoMusic();
        Game.startGame();
        flag = false;
        cntWhites = 0;
        cntReds = 0;

        mediaPlayers.add(mediaPlayer1);mediaPlayers.add(mediaPlayer2);mediaPlayers.add(mediaPlayer3);mediaPlayers.add(mediaPlayer4);
        mediaPlayers.add(mediaPlayer5);mediaPlayers.add(mediaPlayer6);mediaPlayers.add(mediaPlayer7);mediaPlayers.add(mediaPlayer8);
        mediaPlayers.add(mediaPlayer9);mediaPlayers.add(mediaPlayer10);mediaPlayers.add(mediaPlayer11);mediaPlayers.add(mediaPlayer12);

        redTimer = new Label();
        whiteTimer = new Label();
        timer1 = new Timer();
        timer2 = new Timer();

        if(Eintrag.flag1){
            redSec=0;
            redMin = 1;
            totalRedSec = 60;
            whiteSec = 0;
            whiteMin = 1;
            totalWhiteSec = 60;
            if(Game.turn == Model.Color.RED){
                whiteTimer.setText("01:00");
            }else{
                redTimer.setText("01:00");
            }
        }else{
            if(Game.turn == Model.Color.RED){
                whiteTimer.setText("10:00");
            }else{
                redTimer.setText("10:00");
            }
            redSec=0;
            redMin = 10;
            whiteSec = 0;
            whiteMin = 10;
            totalRedSec = 600;
            totalWhiteSec = 600;
        }

        StackPane stackPane = new StackPane();
        redPoints = new Label(""+Game.redPoints);
        redPoints.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 30; -fx-background-radius: 10;");

        whitePoints = new Label(""+Game.whitePoints);
        whitePoints.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 30;-fx-background-radius: 10;");
        redTimer.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 30; -fx-border-color: white; -fx-border-width: 3; -fx-border-radius: 0; -fx-background-radius: 10;");
        whiteTimer.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 30; -fx-border-color: white; -fx-border-width: 3; -fx-border-radius: 0; -fx-background-radius: 10;");
        Label player1=null,player2=null;
        if(textField1 != null && textField2 != null) {
            player1 = new Label(textField1.getText());
            player2 = new Label(textField2.getText());
            player1.setAlignment(Pos.CENTER);
            player1.setTranslateX(-870);
            player1.setTranslateY(-510);
            player2.setAlignment(Pos.CENTER);
            player2.setTranslateX(-870);
            player2.setTranslateY(510);
            player1.setBorder(new Border(new BorderStroke(
                    Color.WHITE,
                    BorderStrokeStyle.DASHED,
                    new CornerRadii(5),
                    new BorderWidths(5)
            )));
            player2.setBorder(new Border(new BorderStroke(
                    Color.WHITE,
                    BorderStrokeStyle.DASHED,
                    new CornerRadii(5),
                    new BorderWidths(5)
            )));
            player1.setFont(new Font("Impact", 35));
            player2.setFont(new Font("Impact", 35));
        }
        changeTimers();

        stackPane.getChildren().addAll(redTimer,whiteTimer);
        redTimer.setAlignment(Pos.CENTER);
        redTimer.setTranslateX(870);
        redTimer.setTranslateY(-400);
        whiteTimer.setAlignment(Pos.CENTER);
        whiteTimer.setTranslateX(870);
        whiteTimer.setTranslateY(400);

        stage = new Stage();
        grid = new GridPane();
        boxes = new CellBox[8][8];
        setPieces();
        stackPane.getChildren().addAll(redPoints,whitePoints);
        if(textField2 != null && textField1 != null)
            stackPane.getChildren().addAll(player1,player2);
        redPoints.setAlignment(Pos.CENTER);
        redPoints.setTranslateX(-870);
        redPoints.setTranslateY(-250);
        whitePoints.setAlignment(Pos.CENTER);
        whitePoints.setTranslateX(-870);
        whitePoints.setTranslateY(250);
        redPoints.setBorder(new Border(new BorderStroke(
                Color.WHITE,
                BorderStrokeStyle.DASHED,
                new CornerRadii(5),
                new BorderWidths(5)
        )));
        whitePoints.setBorder(new Border(new BorderStroke(
                Color.WHITE,
                BorderStrokeStyle.DASHED,
                new CornerRadii(5),
                new BorderWidths(5)
        )));

        dRed1 = new HBox();
        dWhite1 = new HBox();
        dRed2 = new HBox();
        dWhite2 = new HBox();
        dRed3 = new HBox();
        dWhite3 = new HBox();
        dRed4 = new HBox();
        dWhite4 = new HBox();
        dReds = new VBox(dRed1,dRed2,dRed3,dRed4);
        dWhites = new VBox(dWhite1,dWhite2,dWhite3,dWhite4);
        dReds.setBorder(new Border(new BorderStroke(
                Color.BLACK,
                BorderStrokeStyle.SOLID,
                new CornerRadii(5),
                new BorderWidths(5)
        )));
        dWhites.setBorder(new Border(new BorderStroke(
                Color.BLACK,
                BorderStrokeStyle.SOLID,
                new CornerRadii(5),
                new BorderWidths(5)
        )));
        dReds.setMaxSize(200,200);
        dWhites.setMaxSize(200,200);


        stackPane.getChildren().addAll(dReds,dWhites);
        dReds.setAlignment(Pos.CENTER);
        dReds.setTranslateX(-855);
        dReds.setTranslateY(-380);
        dWhites.setAlignment(Pos.CENTER);
        dWhites.setTranslateX(-855);
        dWhites.setTranslateY(213.75/ratio);

        turn = new VBox();
        turn.setBackground(new Background(new BackgroundFill(Game.turn==Model.Color.RED?Color.RED:Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        turn.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));


        stackPane.getChildren().add(turn);
        turn.setAlignment(Pos.CENTER);
        turn.setTranslateX(489.375/ratio);
        turn.setMaxSize(56.25/ratio,56.25/ratio);
        stackPane.setBackground(new Background(new BackgroundFill(Color.gray(0.7),
                CornerRadii.EMPTY,
                Insets.EMPTY)));

        grid.setAlignment(Pos.CENTER);
        grid.setTranslateX(MainView.height/ MainView.width*28.125 );
        stackPane.getChildren().addAll(grid);

        Scene scene = new Scene(stackPane,562.5/ratio,337.5/ratio);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
    public static double ratio = MainView.height/ MainView.width;
    static void setPieces(){

        for(int i = 0;i<8;i++){
            for(int j = 0;j<8;j++){
                boxes[i][j] = new CellBox(new Cell(Game.map[i][j].getPiece(),new Point(j,i)));
                grid.add(boxes[i][j],j,i);
            }
        }
    }

    public static LinkedList<MediaPlayer> mediaPlayers;
    static MediaPlayer mediaPlayer1,mediaPlayer2,mediaPlayer3,mediaPlayer4,mediaPlayer5,mediaPlayer6,mediaPlayer7,mediaPlayer8
            ,mediaPlayer9,mediaPlayer10,mediaPlayer11,mediaPlayer12;
    public static void checkMateMusic() {
        Media media = new Media(Paths.get("src/View/checkMate.mp4").toUri().toString());
        mediaPlayer1= new MediaPlayer(media);
        for(int i = 0;i<mediaPlayers.size();i++)
            if(mediaPlayers.get(i) != null)
                mediaPlayers.get(i).stop();
        mediaPlayer1.play();
//        mediaPlayer1.seek(Duration.seconds(0));
    }

    public static void moveMusic(){
        Media media2 = new Media(Paths.get("src/move3.mp4").toUri().toString());
        mediaPlayer2 = new MediaPlayer(media2);
        for(int i = 0;i<mediaPlayers.size();i++)
            if(mediaPlayers.get(i) != null)
                mediaPlayers.get(i).stop();
        mediaPlayer2.play();
    }


    public static void pawnMusic(){
        Media media3 = new Media(Paths.get("src/View/pawnShot1.mp4").toUri().toString());
        mediaPlayer3 = new MediaPlayer(media3);
        mediaPlayer3.setVolume(0.2);
        for(int i = 0;i<mediaPlayers.size();i++)
            if(mediaPlayers.get(i) != null)
                mediaPlayers.get(i).stop();
        mediaPlayer3.play();
    }

    public static void knightMusic(){
        Media media4 = new Media(Paths.get("src/View/knightShot.mp4").toUri().toString());
        mediaPlayer4 = new MediaPlayer(media4);
        for(int i = 0;i<mediaPlayers.size();i++)
            if(mediaPlayers.get(i) != null)
                mediaPlayers.get(i).stop();
        mediaPlayer4.play();
    }

    public static void rookMusic(){
        Media media5 = new Media(Paths.get("src/View/rookShot.mp4").toUri().toString());
        mediaPlayer5 = new MediaPlayer(media5);
        for(int i = 0;i<mediaPlayers.size();i++)
            if(mediaPlayers.get(i) != null)
                mediaPlayers.get(i).stop();
        mediaPlayer5.play();
    }

    public static void queenMusic(){
        Media media6 = new Media(Paths.get("src/View/queenShot.mp4").toUri().toString());
        mediaPlayer6 = new MediaPlayer(media6);
        for(int i = 0;i<mediaPlayers.size();i++)
            if(mediaPlayers.get(i) != null)
                mediaPlayers.get(i).stop();
        mediaPlayer6.play();
    }

    public static void winMusic(){
        Media media7 = new Media(Paths.get("src/WIN.mp4").toUri().toString());
        mediaPlayer7 = new MediaPlayer(media7);
        for(int i = 0;i<mediaPlayers.size();i++)
            if(mediaPlayers.get(i) != null)
                mediaPlayers.get(i).stop();
        mediaPlayer7.play();
    }
    public static void oomMusic(){
        Media media8 = new Media(Paths.get("src/View/2oom.mp4").toUri().toString());
        mediaPlayer8 = new MediaPlayer(media8);
        for(int i = 0;i<mediaPlayers.size();i++)
            if(mediaPlayers.get(i) != null)
                mediaPlayers.get(i).stop();
        mediaPlayer8.play();
    }
    public static void myTimeMusic(){
        Media media9 = new Media(Paths.get("src/Model/myTime.mp4").toUri().toString());
        mediaPlayer9 = new MediaPlayer(media9);
        for(int i = 0;i<mediaPlayers.size();i++)
            if(mediaPlayers.get(i) != null)
                mediaPlayers.get(i).stop();
        mediaPlayer9.play();
    }
    public static void castleMusic(){
        Media media10 = new Media(Paths.get("src/Model/catsle.mp4").toUri().toString());
        mediaPlayer10 = new MediaPlayer(media10);
        for(int i = 0;i<mediaPlayers.size();i++)
            if(mediaPlayers.get(i) != null)
                mediaPlayers.get(i).stop();
        mediaPlayer10.play();
    }
    public static void a8esonyMusic(){
        Media media11 = new Media(Paths.get("src/a8esony.mp4").toUri().toString());
        mediaPlayer11 = new MediaPlayer(media11);
        for(int i = 0;i<mediaPlayers.size();i++)
            if(mediaPlayers.get(i) != null)
                mediaPlayers.get(i).stop();
        mediaPlayer11.play();
    }
    public static void bingoMusic(){

        Media media12 = new Media(Paths.get("src/View/Bingoo.mp4").toUri().toString());
        mediaPlayer12 = new MediaPlayer(media12);
        for(int i = 0;i<mediaPlayers.size();i++)
            if(mediaPlayers.get(i) != null)
                mediaPlayers.get(i).stop();
        mediaPlayer12.play();
    }

}
