import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;

public class GraphicMain extends Application {

    public static BorderPane root;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        //Setting Up Main Scene
        root = new BorderPane();
        Scene rootScene = new Scene(root, 600, 400);
        primaryStage.setScene(rootScene);
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setTitle("mRNABase");


        //Loading Login Page
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("UserInterface.fxml"));
        root.setBottom(loginLoader.load());

    }
}
