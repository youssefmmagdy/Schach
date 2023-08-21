package Test;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.nio.file.Paths;

import static javafx.application.Application.launch;

public class tst1 extends Application {
//    public static void main(String[] args){
//        launch(args);
//    }

    public void start(Stage s) throws Exception {

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root,600,400);
        Button b = new Button("JOE");
        root.getChildren().add(b);
        s.setScene(scene);
        s.setTitle("Playing music with JavaFX");
        s.show();
    }

}
