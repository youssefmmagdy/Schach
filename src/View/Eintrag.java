package View;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Font;


import java.nio.file.Paths;

import static javafx.application.Application.launch;

public class Eintrag{

    public static TextField textField1;
    public static TextField textField2;
    static boolean flag1;
    static boolean flag2;
    public static Button time1,time2;

    public static void start(){
        flag2 = false;
        flag1 = false;
        Stage stage = new Stage();
        textField1 = new TextField();
        textField1.setPromptText("First CHAMP");
        textField1.setFocusTraversable(false);

        textField2 = new TextField();
        textField2.setPromptText("Second CHAMP");
        textField2.setFocusTraversable(false);

        textField1.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-border-color: white;-fx-font-size: 30;");
        textField2.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-border-color: white;-fx-font-size: 30;");

        Button b1 = new Button("بينجو");
        Polygon trapezium = new Polygon(50, 50, 150, 50, 200, 100, 0, 100);
        b1.setShape(trapezium);
        b1.setStyle("-fx-background-color: white; -fx-text-fill: black;-fx-font-size: 40px;");

        b1.setOnMouseEntered(e -> {
            b1.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-border-color: white;-fx-font-size: 40px;");
        });
        b1.setOnMouseExited(e -> {
            b1.setStyle("-fx-background-color: white; -fx-text-fill: black;-fx-font-size: 40px;");

        });
        b1.setOnMouseClicked(e -> {
            if(textField1.getText().length() > 0 && textField2.getText().length() > 0 && (flag1||flag2)) {
                Spiel.start();
                stage.close();
            }
        });


        textField2.setMaxSize(500,100);
        textField1.setMaxSize(500,100);
        textField1.setAlignment(Pos.CENTER);
        textField2.setAlignment(Pos.CENTER);
        textField2.setTranslateX(300);
        textField2.setTranslateY(-100);
        textField1.setTranslateX(-300);
        textField1.setTranslateY(-100);

        time1 = new Button("1 | 1");
        time2 = new Button("10:00");
        time1.setAlignment(Pos.CENTER);
        time1.setTranslateY(200);
        time1.setMaxSize(300,150);
        time1.setTranslateX(-100);
        time2.setTranslateX(100);
        time2.setAlignment(Pos.CENTER);
        time2.setTranslateY(200);
        time2.setMaxSize(300,150);
        time1.setMaxSize(200,100);
        time2.setMaxSize(200,100);

        time1.setStyle("-fx-shape: \"M0,0 H100 V100 H0 Z\";-fx-font-size: 30;-fx-text-fill: white;-fx-background-color: black;-fx-border-color: black; -fx-border-width: 8; -fx-border-radius: 10; -fx-background-radius: 10;");
        time2.setStyle("-fx-shape: \"M0,0 H100 V100 H0 Z\";-fx-font-size: 30;-fx-text-fill: white;-fx-background-color: black;-fx-border-color: black; -fx-border-width: 8; -fx-border-radius: 10; -fx-background-radius: 10;");

        time1.setOnMouseEntered(e -> {
            time1.setStyle("-fx-shape: \"M0,0 H100 V100 H0 Z\";-fx-text-fill: white;-fx-font-size: 30;-fx-background-color: black;-fx-border-color: yellow; -fx-border-width: 8; -fx-border-radius: 10; -fx-background-radius: 10;");
        });
        time1.setOnMouseExited(e -> {
            if(!flag1) {
                time1.setStyle("-fx-shape: \"M0,0 H100 V100 H0 Z\";-fx-text-fill: white;-fx-font-size: 30;-fx-background-color: black;-fx-border-color: black; -fx-border-width: 8; -fx-border-radius: 10; -fx-background-radius: 10;");
            }
        });
        time2.setOnMouseExited(e -> {
            if(!flag2) {
                time2.setStyle("-fx-shape: \"M0,0 H100 V100 H0 Z\";-fx-text-fill: white;-fx-font-size: 30;-fx-background-color: black;-fx-border-color: black; -fx-border-width: 8; -fx-border-radius: 10; -fx-background-radius: 10;");
            }
        });
        time1.setOnMouseClicked(e -> {
            if(flag2){
                time2.setStyle("-fx-shape: \"M0,0 H100 V100 H0 Z\";-fx-text-fill: white;-fx-font-size: 30;-fx-background-color: black;-fx-border-color: black; -fx-border-width: 8; -fx-border-radius: 10; -fx-background-radius: 10;");
                flag2 = false;
            }
            time1.setStyle("-fx-shape: \"M0,0 H100 V100 H0 Z\";-fx-text-fill: white;-fx-font-size: 30;-fx-background-color: black;-fx-border-color: yellow; -fx-border-width: 8; -fx-border-radius: 10; -fx-background-radius: 10;");
            flag1 = true;
        });
        time2.setOnMouseClicked(e -> {
            if(flag1){
                time1.setStyle("-fx-shape: \"M0,0 H100 V100 H0 Z\";-fx-text-fill: white;-fx-font-size: 30;-fx-background-color: black;-fx-border-color: black; -fx-border-width: 8; -fx-border-radius: 10; -fx-background-radius: 10;");
                flag1 = false;
            }
            time2.setStyle("-fx-shape: \"M0,0 H100 V100 H0 Z\";-fx-text-fill: white;-fx-font-size: 30;-fx-background-color: black;-fx-border-color: yellow; -fx-border-width: 8; -fx-border-radius: 10; -fx-background-radius: 10;");
            flag2 = true;
        });

        time2.setOnMouseEntered(e -> {
            time2.setStyle("-fx-shape: \"M0,0 H100 V100 H0 Z\";-fx-text-fill: white;-fx-font-size: 30;-fx-background-color: black;-fx-border-color: yellow; -fx-border-width: 8; -fx-border-radius: 10; -fx-background-radius: 10;");
        });
        StackPane stackPane  = new StackPane(b1,textField2,textField1,time1,time2);
        b1.setAlignment(Pos.CENTER);
        b1.setTranslateY(400);
        b1.setMaxSize(300,150);
        stackPane.setBackground(new Background(new BackgroundImage(
                new Image("back.jpeg",
                        1920,1080,false,false),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
        stage.setScene(new Scene(stackPane,1000,600));
        stage.setFullScreen(true);
        stage.show();


    }



    public static void main(String[] args){
        launch(args);
    }
}
