package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Formular");
        primaryStage.setScene(new Scene(root, 700, 500));     /*zasto ne radi ovo za computed???*/
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
