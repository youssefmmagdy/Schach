package View;

import javafx.application.Application;

import javafx.stage.Screen;
import javafx.stage.Stage;



public class MainView extends Application{

    public static double width = Screen.getPrimary().getBounds().getWidth();
    public static double height = Screen.getPrimary().getBounds().getHeight();


    @Override
    public void start(Stage stage) throws Exception {
        Eintrag.start();
//        Spiel.start();
//        AlertBox.display();
    }
    public static void main(String[] args){
        launch(args);
    }
}
