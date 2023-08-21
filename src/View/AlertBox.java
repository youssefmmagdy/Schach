package View;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Game.Game;

import static javafx.application.Application.launch;

public class AlertBox{

    public static void main(String[] args) {
        display();
    }

    static Stage window;
    static Label label;
    public static void display() {
        Button endGame,playAgain;

            window = new Stage();
            HBox box = new HBox();
            BackgroundImage myBI = new BackgroundImage(new Image("img_1.png", 500, 400, true, true),
                    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
             endGame = new Button("END GAME");
             playAgain = new Button("PLAY AGAIN");
            endGame.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 20; -fx-border-color: black; -fx-border-width: 3; -fx-border-radius: 10; -fx-background-radius: 10;");
            playAgain.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 20; -fx-border-color: black; -fx-border-width: 3; -fx-border-radius: 10; -fx-background-radius: 10;");
            DropShadow shadow = new DropShadow();
            shadow.setColor(Color.WHITE);
            shadow.setOffsetX(3);
            shadow.setOffsetY(3);
            endGame.setEffect(shadow);
            playAgain.setEffect(shadow);
            label = new Label(Game.winner == null ? "DRAW  DRAW  " + "KEINE" + "  BLOW" : "WINNER  WINNER  " + (Game.winner == Model.Color.WHITE ? "WHITE" : "RED") + "  DiINNER");
            label.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 20; -fx-border-color: black; -fx-border-width: 3; -fx-border-radius: 0; -fx-background-radius: 10;");
            box.getChildren().addAll(endGame, playAgain);
            StackPane stackPane = new StackPane();
            Background bg = new Background(myBI);
            stackPane.setMaxSize(500, 600);
            stackPane.setBackground(bg);
            stackPane.getChildren().addAll(box, label);
            label.setTranslateY(-75);
            box.setAlignment(Pos.CENTER);
            box.setTranslateY(100);
            window.setScene(new Scene(stackPane, 500, 300));
            playAgain.setOnMouseEntered(e -> {
                playAgain.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 20; -fx-border-color: white; -fx-border-width: 3; -fx-border-radius: 10; -fx-background-radius: 10;");

        });
            endGame.setOnMouseEntered(e -> {
                endGame.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 20; -fx-border-color: white; -fx-border-width: 3; -fx-border-radius: 10; -fx-background-radius: 10;");

        });
            playAgain.setOnMouseExited(e -> {
                playAgain.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 20; -fx-border-color: black; -fx-border-width: 3; -fx-border-radius: 10; -fx-background-radius: 10;");

        });
            endGame.setOnMouseExited(e -> {
                endGame.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 20; -fx-border-color: black; -fx-border-width: 3; -fx-border-radius: 10; -fx-background-radius: 10;");

        });
            playAgain.setOnMouseClicked(e -> {
                Spiel.stage.close();
                    window.close();
                    Eintrag.start();
        });
            endGame.setOnMouseClicked(e -> {
                Spiel.stage.close();
                    window.close();
        });

        label.setText(Game.winner == null ? "DRAW  DRAW  " + "KEINE" + "  BLOW" : "WINNER  WINNER  " + (Game.winner == Model.Color.WHITE ? "WHITE" : "RED") + "  DiINNER");
//        window.initModality(Modality.APPLICATION_MODAL);
        window.show();
    }

}
